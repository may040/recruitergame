<script setup>
import { ref, reactive, onMounted, watch, watchEffect } from 'vue'
import axios from 'axios';


//TODO aus questions berechnen und ausgeben nicht konstant
const answers = reactive([
  "KIT",
  "TUM",
  "HKA",
  "KIT",
  "KIT"])

const recruiter = reactive({ name: "", company: "", id: null })
const isrecruiterDataSaved = ref(false)
const color = reactive(["gray", "gray", "gray", "gray", "gray"])
const areQesAnswered = ref(false)
//Parallel structure
const selectedAnswers = reactive([[], [], [], [], []])
let questions = ref([])
let recruiterAnswers = []

async function saveRecruiterData() {
  isrecruiterDataSaved.value = !isrecruiterDataSaved.value
  try {
    const res = await axios.post("http://localhost:8080/r/add", {
      name: recruiter.name,
      company: recruiter.company
    })
    recruiter.id = res.data
    questions.value = await getQuestions()
    console.log(questions.value)

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
  console.log(recruiterAnswers)
}

async function saveRecruiterAnswers() {
  try {
    const res = await axios.post(`http://localhost:8080/r/answers`, recruiterAnswers)

  } catch (error) {
    console.log(error)
  }

}


function showResult() {
  areQesAnswered.value = true
  //evaCheckedAnswers()
  createRecruiterAnswer()
  saveRecruiterAnswers()
}

// function evaCheckedAnswers() {
//   for (let i = 0; i < questions.length; i++) {
//     if (questions[i].selected.length < 3 && questions[i].selected.includes(answers[i])) {
//       color[i] = "green";
//       result[i] = true;
//     } else {
//       color[i] = "red";
//       result[i] = false;
//     }
//   }
// }



// onMounted(async () => {
//   recruiterID.value = await saveRecruiterData()
// })

</script>

<template>
  <div class="game">
    <div class="recruiter_data">
      <form @submit.prevent="saveRecruiterData" v-show="!isrecruiterDataSaved">
        <p>Your Name</p>
        <input v-model="recruiter.name" placeholder="Name"></input>
        <p>Your Company</p>
        <input v-model="recruiter.company" placeholder="Company"></input>
        <br></br>
        <button type="submit">Save your Data</button>
      </form>
      <h1 v-show="isrecruiterDataSaved">Hello {{ recruiter.name }} from {{ recruiter.company }}, start your game</h1>

    </div>
    <div class="questions" v-show="isrecruiterDataSaved">
      <div class="question" v-for="(qes, index) in questions" :style="{ backgroundColor: color[index] }" :key="index">
        <p>{{ qes.text }}</p>
        <p>{{ qes.points }}</p>
        <div class="answers" v-for="(answer, i) in qes.answers" :key="i">
          <input type="checkbox" :disabled="areQesAnswered" :value="answer.text" v-model="selectedAnswers[index]">{{
            answer.text }}</input>
        </div>
        <p v-if="areQesAnswered">Korrekte Antwort: {{ answers[index] }} </p>
        <!-- <p v-if="areQesAnswered">Korrekte Antwort: {{ selectedAnswer }} </p> -->
      </div>
      <button @click="showResult">Check</button>
    </div>
  </div>
</template>

<style>
.game {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
}
</style>
