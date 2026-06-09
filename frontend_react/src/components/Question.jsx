import './../style/Question.css'
import Answer from './Answer'

export default function Question({question,name,saveSelectedAnswer,color,indexStyle}){

    return(
        <div className="question" style={{
               borderImage: color[indexStyle],
            }}>
            <div className="header">
            <p className="text">{question.text}</p>
            <p className="points">Points: {question.points}</p>
        </div>
        <div className="answers">
            {
                question.answers.map((answer,index)=>{
                    return <Answer answer={answer} key={answer.id} name={name} saveSelectedAnswer={saveSelectedAnswer}></Answer>
                })
            }
        </div>
</div>
    )
}