<script setup>
import { ref, reactive, onMounted, watch, watchEffect } from 'vue'
import axios from 'axios';

const questions = reactive([
  {
    question: "First Question?",
    answers: [
      { answer: "KIT" },
      { answer: "TUM" },
      { answer: "HKA" }
    ],
    selected: [""],
    isAnswered: false,
    points: 1
  },
  {
    question: "Second Question?",
    answers: [
      { answer: "KIT" },
      { answer: "TUM" },
      { answer: "HKA" }
    ],
    selected: [""],
    isAnswered: false,
    points: 1
  },
  {
    question: "Third Question?",
    answers: [
      { answer: "KIT" },
      { answer: "TUM" },
      { answer: "HKA" }
    ],
    selected: [""],
    isAnswered: false,
    points: 1
  },
  {
    question: "Fourth Question?",
    answers: [
      { answer: "KIT" },
      { answer: "TUM" },
      { answer: "HKA" }
    ],
    selected: [""],
    isAnswered: false,
    points: 1
  },
  {
    question: "Five Question?",
    answers: [
      { answer: "KIT" },
      { answer: "TUM" },
      { answer: "HKA" }
    ],
    selected: [""],
    isAnswered: false,
    points: 1
  }
])

const answers = reactive([
  "KIT",
  "TUM",
  "HKA",
  "KIT",
  "KIT"])

const result = reactive([false, false, false, false, false])

const recruiter = reactive({ name: "", company: "", id: null })
const isrecruiterDataSaved = ref(false)
const color = reactive(["gray", "gray", "gray", "gray", "gray"])

async function saveRecruiterData() {
  isrecruiterDataSaved.value = !isrecruiterDataSaved.value
  try {
    const res = await axios.post("http://localhost:8080/r/add", {
      name: recruiter.name,
      company: recruiter.company
    })
    recruiter.id = res.data
    getQuestions()

  } catch (error) {
    console.log(error)
  }

}

async function getQuestions() {
  try {
    const res = await axios.get(`http://localhost:8080/r/${recruiter.id}`)
    console.log(res.data)
  } catch (error) {

  }
}



function showResult() {
  questions.map(q => q.isAnswered = true)
  evaCheckedAnswers()
}

function evaCheckedAnswers() {
  for (let i = 0; i < questions.length; i++) {
    if (questions[i].selected.length < 3 && questions[i].selected.includes(answers[i])) {
      color[i] = "green";
      result[i] = true;
    } else {
      color[i] = "red";
      result[i] = false;
    }
  }
}



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
        <p>{{ qes.question }}</p>
        <p>{{ qes.points }}</p>
        <div class="answers" v-for="(answer, i) in qes.answers" :key="i">
          <input type="checkbox" :disabled="qes.isAnswered" :value="answer.answer" v-model="qes.selected">{{
            answer.answer }}</input>
        </div>
        <p v-if="qes.isAnswered">Korrekte Antwort: {{ answers[index] }} </p>
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
