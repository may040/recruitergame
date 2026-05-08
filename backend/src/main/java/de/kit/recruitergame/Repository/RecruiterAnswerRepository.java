package de.kit.recruitergame.Repository;

import de.kit.recruitergame.model.RecruiterAnswer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RecruiterAnswerRepository extends JpaRepository<RecruiterAnswer,Long> {

    @Query("SELECT recAns FROM RecruiterAnswer recAns WHERE recAns.recruiter.id =:recID")
    List<RecruiterAnswer> findByRecID(Long recID);
}
