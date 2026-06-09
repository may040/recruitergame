import './../style/Answer.css'

export default function Answer({ answer,name,saveSelectedAnswer }) {

  return (
    <div className="answer">
      <label>
        <input className="r_answer" type="radio" value={answer.text} name={name} onClick={(e)=>saveSelectedAnswer(name,e.target.value)} />
          {answer.text}
      </label>
    </div>
  );
}
