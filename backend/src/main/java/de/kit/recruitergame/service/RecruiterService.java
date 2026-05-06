package de.kit.recruitergame.service;

import de.kit.recruitergame.Repository.AnswerRepository;
import de.kit.recruitergame.Repository.QuestionRepository;
import de.kit.recruitergame.Repository.RecruiterAnswerRepository;
import de.kit.recruitergame.Repository.RecruiterRepository;
import de.kit.recruitergame.dto.AnswerDTO;
import de.kit.recruitergame.dto.QuestionDTO;
import de.kit.recruitergame.dto.RecruiterAnswerDTO;
import de.kit.recruitergame.model.Question;
import de.kit.recruitergame.model.Recruiter;
import de.kit.recruitergame.model.RecruiterAnswer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
public class RecruiterService {

    @Autowired
    private RecruiterRepository recruiterRepository;

    @Autowired
    private AnswerRepository answerRepository;

    @Autowired
    private RecruiterAnswerRepository recruiterAnswerRepository;

    @Autowired
    private QuestionRepository questionRepository;

    public Long addRecruiter(Recruiter rec) {
        Recruiter recruiter = recruiterRepository.save(rec);
        Set<Question> standardQuestions = questionRepository.findAll().stream().collect(Collectors.toSet());
        recruiter.setQuestons(standardQuestions);
        recruiterRepository.save(recruiter);
        return recruiter.getId();
    }

    public List<Recruiter> getAllRecruiter() {
        return recruiterRepository.findAll();
    }


    public LinkedHashSet<QuestionDTO> getQuestionsWithAnswers(Long recID) {
        Recruiter rec = recruiterRepository.findById(recID).get();
        Set<Question> questions = rec.getQuestons();

        return questions.stream().map(this::mapToQDTO).sorted(Comparator.comparingLong(QuestionDTO::getId)).collect(Collectors.toCollection(LinkedHashSet::new));
    }

    private QuestionDTO mapToQDTO(Question q) {
        QuestionDTO dto = new QuestionDTO();
        dto.setId(q.getId());
        dto.setText(q.getText());
        dto.setPoints(q.getPoint());

        dto.setAnswers(q.getAnswers().stream()
                .map(a -> {
                    AnswerDTO ad = new AnswerDTO();
                    ad.setId(a.getId());
                    ad.setText(a.getText());
                    ad.setCorrect(a.isCorrect());
                    return ad;
                })
                .toList());

        return dto;
    }

    public void saveRecruiterAnswers(List<RecruiterAnswerDTO> recruiterAnswersDTO) {
        List<RecruiterAnswer> recruiterAnswers = new ArrayList<>();
        Long answerID = 0L;
        for (RecruiterAnswerDTO recAnswer : recruiterAnswersDTO) {
            RecruiterAnswer answer = new RecruiterAnswer();
            answer.setRecruiterAnswer(recAnswer.isSelected());
            answerID = recAnswer.getAnswer_id();
            answer.setAnswer(answerRepository.findById(answerID).get());
            answer.setRecruiter(recruiterRepository.findById(recAnswer.getRecruiter_id()).get());
            recruiterAnswers.add(answer);
        }
        recruiterAnswerRepository.saveAll(recruiterAnswers);
        Long recID = recruiterAnswers.get(0).getRecruiter().getId();
        int achievedPoints = calculateAchievedPoints(recruiterAnswers);
        recruiterRepository.findById(recID).get().setAchievedPoints(achievedPoints);

    }

    private int calculateAchievedPoints(List<RecruiterAnswer> recruiterAnswers) {
        Map<Long, List<RecruiterAnswer>> groupedRecAnswers = recruiterAnswers.stream().collect(Collectors.groupingBy(ra -> ra.getAnswer().getQuestion().getId()));
        int achievedPoints = 0;
        for (Map.Entry<Long,List<RecruiterAnswer>> entry : groupedRecAnswers.entrySet()) {
            boolean isRecAnswerCorrect = true;

            for (RecruiterAnswer recAnswer : entry.getValue()) {
                if (!recAnswer.isRecruiterAnwser() == recAnswer.getAnswer().isCorrect()) {
                    isRecAnswerCorrect = false;
                }


            }
            if (isRecAnswerCorrect) {
                achievedPoints += questionRepository.findById(entry.getKey()).get().getPoint();
            }

        }

        return achievedPoints;
    }

    public int getPointsOfRec(Long recID) {
        return recruiterRepository.findById(recID).get().getAchievedPoints();
    }
}
