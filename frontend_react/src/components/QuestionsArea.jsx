import {
  getPoints,
  getQuestions,
  saveRecruiterAnswers,
  getQuesResults,
} from "./../api/recruiter";
import { useMutation, useQuery } from "@tanstack/react-query";
import "./../style/QuestionsArea.css";
import Question from "./Question";
import { useEffect, useState } from "react";

export default function QuestionArea({ recruiterID, isRecDataSaved }) {
  const [areQuesAnswered, setQuesAnswered] = useState(false);

  const [selectedAnswers, setSelectedAnswers] = useState([[], [], [], [], []]);
  const [color, setColor] = useState([
    "white",
    "white",
    "white",
    "white",
    "white",
  ]);

  const saveSelectedAnswer = (index, answer) => {
    setSelectedAnswers((prev) => {
      const next = [...prev];
      next[index] = [answer];
      return next;
    });
  };
  let recruiterAnswers = [];

  function createRecruiterAnswer() {
    for (const [index, qes] of questions.entries()) {
      for (const answer of qes.answers) {
        if (selectedAnswers[index].includes(answer.text)) {
          recruiterAnswers.push({
            answer_id: answer.id,
            selected: true,
            recruiter_id: recruiterID,
          });
        } else {
          recruiterAnswers.push({
            answer_id: answer.id,
            selected: false,
            recruiter_id: recruiterID,
          });
        }
      }
    }
  }

  function determineBorderColor() {
    for (let index = 0; index < color.length; index++) {
      setColor((prev) => {
        let next = [...prev];
        next[index] = evaRecResults[index].answeredCorrect
          ? "linear-gradient(135deg,  rgb(34, 197, 94),  rgb(16, 185, 129),  rgb(74, 222, 128)) 1"
          : "linear-gradient(135deg,    rgb(239, 68, 68),  rgb(220, 38, 38),  rgb(248, 113, 113)) 1";
        return next;
      });
    }
  }

  const {
    data: questions = [],
    isLoading,
    error,
  } = useQuery({
    queryKey: ["questions", recruiterID],
    queryFn: () => getQuestions(recruiterID),
    enabled: recruiterID != 0 && isRecDataSaved,
  });

  const {
    data: achievedPoints,
    isLoading: isAchievedPointsLoading,
    error: achievedPointsError,
  } = useQuery({
    queryKey: ["achievedPoints"],
    queryFn: () => getPoints(recruiterID),
    enabled: areQuesAnswered,
  });

  const mutation = useMutation({
    mutationFn: saveRecruiterAnswers,
    onSuccess: () => setQuesAnswered(true),
  });

  const {
    data: evaRecResults = [],
    isLoading: isResultLoading,
    error: evaRecResultsError,
  } = useQuery({
    queryKey: ["evaRecResults"],
    queryFn: () => getQuesResults(recruiterID),
    enabled: areQuesAnswered,
  });

  const showResults = () => {
    createRecruiterAnswer();
    mutation.mutate(recruiterAnswers);
  };

  useEffect(() => {
        if (evaRecResults.length > 0) {
      determineBorderColor();
      console.log("INSIDE");
      console.log(color)
        console.log(typeof color[0])
    }
  }, [evaRecResults, areQuesAnswered]);

  useEffect(() => {
  console.log(color)
}, [color])

  //TODO borders
  return (
    <div className="question_list">
      {questions.map((qes, index) => {
        return (
          <Question
            question={qes}
            key={qes.id}
            name={index}
            saveSelectedAnswer={saveSelectedAnswer}
            color={color}
            indexStyle={index}
          ></Question>
        );
      })}
      {areQuesAnswered && <p>{achievedPoints} of 5 points</p>}
      <button id="btn_result" disabled={areQuesAnswered} onClick={showResults}>
        Check
      </button>
    </div>
  );
}
