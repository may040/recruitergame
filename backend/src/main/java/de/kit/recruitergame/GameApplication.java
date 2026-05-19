package de.kit.recruitergame;

import de.kit.recruitergame.Repository.AnswerRepository;
import de.kit.recruitergame.Repository.QuestionRepository;
import de.kit.recruitergame.model.Answer;
import de.kit.recruitergame.model.Question;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.List;

@SpringBootApplication
public class GameApplication {


    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(GameApplication.class, args);
        AnswerRepository answerRepository = context.getBean(AnswerRepository.class);
        QuestionRepository questionRepository = context.getBean(QuestionRepository.class);

        List<Question> standardQuestions = List.of(
                new Question("Where did he study?", 1),
                new Question("What type of experience does he have?", 1),
                new Question("What is his area of expertise in IT?", 1),
                new Question("Where was he born?", 1),
                new Question("What does he like the most?", 1)
        );

        List<Answer> uniAnswers = List.of(
                new Answer("HKA", standardQuestions.get(0), false),
                new Answer("KIT", standardQuestions.get(0), true),
                new Answer("TUM", standardQuestions.get(0), false)
        );

        List<Answer> expAnswers = List.of(
                new Answer("HiWi", standardQuestions.get(1), false),
                new Answer("Werki", standardQuestions.get(1), false),
                new Answer("Both", standardQuestions.get(1), true)
        );

        List<Answer> specAnswers = List.of(
                new Answer("Cloud Engineering", standardQuestions.get(2), false),
                new Answer("Software Engineering", standardQuestions.get(2), true),
                new Answer("Electric Engineering", standardQuestions.get(2), false)
        );

        List<Answer> bornAnswers = List.of(
                new Answer("Nah an Heidelberg", standardQuestions.get(3), false),
                new Answer("Nah an Heilbronn", standardQuestions.get(3), true),
                new Answer("Nah an Stuttgart", standardQuestions.get(3), false)
        );

        List<Answer> likeAnswers = List.of(
                new Answer("Vue", standardQuestions.get(4), false),
                new Answer("React", standardQuestions.get(4), false),
                new Answer("Spring Boot", standardQuestions.get(4), true)
        );

        if (questionRepository.findAll().isEmpty()) {
            questionRepository.saveAll(standardQuestions);
        }
        if (answerRepository.findAll().isEmpty()) {
            answerRepository.saveAll(uniAnswers);
            answerRepository.saveAll(expAnswers);
            answerRepository.saveAll(specAnswers);
            answerRepository.saveAll(bornAnswers);
            answerRepository.saveAll(likeAnswers);
        }

    }

}
