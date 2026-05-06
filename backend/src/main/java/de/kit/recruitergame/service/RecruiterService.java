package de.kit.recruitergame.service;

import de.kit.recruitergame.Repository.AnswerRepository;
import de.kit.recruitergame.Repository.QuestionRepository;
import de.kit.recruitergame.Repository.RecruiterAnswerRepository;
import de.kit.recruitergame.Repository.RecruiterRepository;
import de.kit.recruitergame.dto.AnswerDTO;
import de.kit.recruitergame.dto.QuestionDTO;
import de.kit.recruitergame.dto.RecruiterAnswerDTO;
import de.kit.recruitergame.model.Answer;
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


    public LinkedHashSet<QuestionDTO> getQuestionsWithAnswers(int recID) {
        Recruiter rec = recruiterRepository.findById(recID).get();
        Set<Question> questions = rec.getQuestons();

        return questions.stream().map(this::mapToQDTO).sorted((q1, q2) -> Long.compare(q1.getId(), q2.getId())).collect(Collectors.toCollection(LinkedHashSet::new));
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
        int recID = 0;
        for (RecruiterAnswerDTO recAnswer : recruiterAnswersDTO) {
            RecruiterAnswer answer = new RecruiterAnswer();
            answer.setRecruiterAnswer(recAnswer.isSelected());
            recID = recAnswer.getAnswer_id();
            answer.setAnswer(answerRepository.findById(recID).get());
            answer.setRecruiter(recruiterRepository.findById(recAnswer.getRecruiter_id()).get());
            recruiterAnswers.add(answer);
        }
        recruiterAnswerRepository.saveAll(recruiterAnswers);

        calculateAchievedPoints(recruiterAnswers,recID);

    }

    private void calculateAchievedPoints(List<RecruiterAnswer> recruiterAnswers, int recID) {
        //TODO achievedPoints von rec berechnen speicher
        //recruiterRepository.findById(recID).get().
        //TODO problem answers von einer qes müssen insgeamt betrachtet werden, also drei answers gleichzeitig
        Map<Long, Answer> groupedAnswers = recruiterAnswers.stream().collect(Collectors.toMap(ra -> ra.getAnswer().getQuestion().getId(), ra -> ra.getAnswer()));
        groupedAnswers.entrySet().forEach(x->System.out.println(x.getKey()+x.getValue().getText()));

        /*for (RecruiterAnswer recAnswer : recruiterAnswers) {
            recAnswer.getAnswer().getQuestion().getId();
            if(recAnswer.isRecruiterAnwser()==recAnswer.getAnswer().isCorrect()){

            }

        }*/
        }
}
