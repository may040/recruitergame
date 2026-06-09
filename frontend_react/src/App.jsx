import { useEffect, useState } from "react";

import "./App.css";
import RecruiterForm from "./components/RecruiterForm";
import QuestionsArea from "./components/QuestionsArea";

function App() {
  const [recruiter, setRecruiter] = useState({ name: "", company: "", id: 0 });
  const [isRecDataSaved, setRecDataSaved] = useState(false);

  const updateRecruiter = (field, value) => {
    setRecruiter((prev) => ({ ...prev, [field]: value }));
  };

  const updateRecDataSaved = (value) => {
    setRecDataSaved(value);
  };

 

  // useEffect(() => {
  //   if(recruiter.id!=0){
  //   console.log(data);
  //   }
  // }, [data]);

  return (
    <div className="game">
      {!isRecDataSaved ? (
        <RecruiterForm
          recruiter={recruiter}
          updateRecruiter={updateRecruiter}
          updateRecDataSaved={updateRecDataSaved}
        ></RecruiterForm>
      ) : (
        <>
          <p id="title">
            Welcome {recruiter.name} from {recruiter.company}, <br /> start your
            questionnaire
          </p>
          <QuestionsArea recruiterID={recruiter.id} isRecDataSaved={isRecDataSaved}></QuestionsArea>
        </>
      )}
    </div>
  );
}

export default App;
