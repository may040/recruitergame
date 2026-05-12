<script setup>
import { ref, reactive, onMounted, watch, watchEffect } from 'vue'
import axios from 'axios';




const recruiter = reactive({ name: "", company: "", id: null })
const isrecruiterDataSaved = ref(false)
const color = reactive(["gray", "gray", "gray", "gray", "gray"])
const areQesAnswered = ref(false)
//Parallel structure
const selectedAnswers = reactive([[], [], [], [], []])
let questions = ref([])
let recruiterAnswers = []
let evaRecAnswers = ref([])
let areResultsLoaded = ref(false)

async function saveRecruiterData() {
  isrecruiterDataSaved.value = !isrecruiterDataSaved.value
  try {
    const res = await axios.post("http://localhost:8080/r/add", {
      name: recruiter.name,
      company: recruiter.company
    })
    recruiter.id = res.data
    questions.value = await getQuestions()

  } catch (error) {
    console.log(error)
  }
}

async function getQuestions() {
  try {
    let res = await axios.get(`http://localhost:8080/r/${recruiter.id}`)
    return res.data
  } catch (error) {
    console.log(error)
    return null
  }
}

function createRecruiterAnswer() {

  for (const [index, qes] of questions.value.entries()) {
    for (const answer of qes.answers) {
      if (selectedAnswers[index].includes(answer.text)) {
        recruiterAnswers.push({ answer_id: answer.id, selected: true, recruiter_id: recruiter.id })
      } else {
        recruiterAnswers.push({ answer_id: answer.id, selected: false, recruiter_id: recruiter.id })
      }
    }
  }
}

async function saveRecruiterAnswers() {
  try {
    const res = await axios.post(`http://localhost:8080/r/answers`, recruiterAnswers)
    getQuesResults()
  } catch (error) {
    console.log(error)
  }

}

async function getQuesResults() {
  const res = await axios.get(`http://localhost:8080/r/results/${recruiter.id}`)
  evaRecAnswers = res.data
  areResultsLoaded.value = true
  console.log(evaRecAnswers)
  for (let index = 0; index < color.length; index++) {
    color[index] = evaRecAnswers[index].answeredCorrect ? 'green' : 'red'
  }
}


function showResult() {
  areQesAnswered.value = true
  createRecruiterAnswer()
  saveRecruiterAnswers()


}

</script>

<template>
  <div class="game">
    <form class="recruiter_data" @submit.prevent="saveRecruiterData" v-show="!isrecruiterDataSaved">
      <p id="title">ENTER YOUR RECRUITER DETAILS</p>
      <input id="ip_name" v-model="recruiter.name" placeholder="Your Name"></input>
      <input id="ip_company" v-model="recruiter.company" placeholder="Your Company"></input>
      <button id="btn_submit" type="submit">Start Game</button>
    </form>
    <h1 v-show="isrecruiterDataSaved">Hello {{ recruiter.name }} from {{ recruiter.company }}, start your game</h1>
    <div class="questions" v-show="isrecruiterDataSaved">
      <div class="question" v-for="(qes, index) in questions" :style="{ backgroundColor: color[index] }" :key="index">
        <p>{{ qes.text }}</p>
        <p>{{ qes.points }}</p>
        <div class="answers" v-for="(answer, i) in qes.answers" :key="i">
          <input type="radio" :disabled="areQesAnswered" :value="answer.text" v-model="selectedAnswers[index]">{{
            answer.text }}</input>
        </div>
        <p v-if="areQesAnswered && areResultsLoaded.valueOf">Korrekte Antwort: {{ evaRecAnswers[index].correctAnswer }}
        </p>
      </div>
      <button @click="showResult">Check</button>
    </div>
  </div>
</template>

<style>
#app {
  background:
    linear-gradient(rgba(28, 28, 224, 0.6), rgba(26, 198, 158, 0.6));
  width: 100%;
  min-height: 97vh;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
}

.game {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  width: fit-content;
  margin: 0 auto;
  padding: 20px;
  font-size: 20px;
  font-family: 'Times New Roman', Times, serif;
  margin: 0 auto;
  background-color: white;


}

.recruiter_data {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
}

#title {
  margin-top: 10px;
  margin-bottom: 15px;

  color: rgb(108, 103, 103);

  font-size: 20px;
  font-weight: 700;
  letter-spacing: 2px;
  text-transform: uppercase;

  text-align: center;

  text-shadow:
    0 0 12px rgba(28, 28, 224, 0.6),
    0 0 24px rgba(26, 198, 158, 0.4);
}

#ip_name {
  margin-top: 10px;
  width: 200px;
  height: 20px;

  padding: 12px 18px;

  border: 2px solid rgba(26, 198, 158, 0.4);
  border-radius: 6px;

  background: rgba(255, 255, 255, 0.08);

  color: black;

  font-size: 14px;
  font-weight: 600;

  outline: none;

  transition: all 0.2s ease;

  box-shadow:
    0 4px 12px rgba(28, 28, 224, 0.2),
    0 2px 6px rgba(26, 198, 158, 0.15);
}



#ip_name:focus {
  border-color: rgb(26, 198, 158);

  box-shadow:
    0 0 0 3px rgba(26, 198, 158, 0.2),
    0 6px 18px rgba(28, 28, 224, 0.3);
}

#ip_company {
  margin-top: 5px;
  width: 200px;

  height: 20px;

  padding: 12px 18px;

  border: 2px solid rgba(26, 198, 158, 0.4);
  border-radius: 6px;

  background: rgba(255, 255, 255, 0.08);

  color: black;

  font-size: 14px;
  font-weight: 600;

  outline: none;

  transition: all 0.2s ease;

  box-shadow:
    0 4px 12px rgba(28, 28, 224, 0.2),
    0 2px 6px rgba(26, 198, 158, 0.15);
}



#ip_company:focus {
  border-color: rgb(26, 198, 158);

  box-shadow:
    0 0 0 3px rgba(26, 198, 158, 0.2),
    0 6px 18px rgba(28, 28, 224, 0.3);
}




#btn_submit {
  margin-top: 15px;
  padding: 9px 32px;

  border: none;
  border-radius: 6px;

  background: linear-gradient(135deg,
      rgb(28, 28, 224),
      rgb(26, 198, 158));

  color: white;

  font-size: 12px;
  font-weight: 700;
  letter-spacing: 1px;
  text-transform: uppercase;

  cursor: pointer;

  transition: all 0.2s ease;

  box-shadow:
    0 6px 18px rgba(28, 28, 224, 0.35),
    0 3px 8px rgba(26, 198, 158, 0.25);
}

#btn_submit:hover {
  transform: translateY(-3px);

  box-shadow:
    0 10px 24px rgba(28, 28, 224, 0.45),
    0 6px 12px rgba(26, 198, 158, 0.35);
}

#btn_submit:active {
  transform: translateY(1px);
}
</style>
