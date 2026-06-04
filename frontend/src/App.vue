<script setup>
import { ref, reactive, onMounted, watch, watchEffect, provide } from 'vue'
import axios from 'axios';
import RecruiterForm from './components/RecruiterForm.vue';
import QuestionList from './components/ContentList.vue';
import { getQuestions } from '@/services/recruiterService'
import ContentList from './components/ContentList.vue';

const recruiter = reactive({ name: "", company: "", id: null })
const isrecruiterDataSaved = ref(false)
const color = reactive(["white", "white", "white", "white", "white"])
let areQesAnswered = ref(false)
const selectedAnswers = reactive([[], [], [], [], []])
let questions = ref([])
let recruiterAnswers = []
let evaRecAnswers = ref([])
let areResultsLoaded = ref(false)



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
    if (res.status == 200) {
      const resRes = await getQuesResults()

      areQesAnswered.value = true
      areResultsLoaded.value = true

    }
  } catch (error) {
    console.log(error)
  }
}

async function getQuesResults() {
  try {
    const res = await axios.get(`http://localhost:8080/r/results/${recruiter.id}`)
    evaRecAnswers.value = res.data

    for (let index = 0; index < color.length; index++) {
      color[index] = evaRecAnswers.value[index].answeredCorrect ? 'linear-gradient(135deg,  rgb(34, 197, 94),  rgb(16, 185, 129),  rgb(74, 222, 128)) 1' : 'linear-gradient(135deg,    rgb(239, 68, 68),  rgb(220, 38, 38),  rgb(248, 113, 113)) 1'
    }
  } catch (error) {
    console.log(error)
  }
}




function showResult() {
  createRecruiterAnswer()
  saveRecruiterAnswers()


}

watch(isrecruiterDataSaved, async (n, o) => {
  questions.value = await getQuestions(recruiter.id)
})

provide('areQesAnswered', areQesAnswered)
provide('selectedAnswers', selectedAnswers)
provide('areResultsLoaded', areResultsLoaded)
provide('evaRecAnswers', evaRecAnswers)

</script>




<template>
  <div class="game">
    <RecruiterForm :recruiter="recruiter" v-model:isrecruiterDataSaved="isrecruiterDataSaved"></RecruiterForm>
    <p id="title" v-show="isrecruiterDataSaved">Welcome {{ recruiter.name }} from {{ recruiter.company }},<br> start
      your
      questionnaire</p>
    <ContentList :isrecruiterDataSaved="isrecruiterDataSaved" :questions="questions" :color="color"
      :recruiterID="recruiter.id" @eva-rec-input="showResult"></ContentList>
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
  padding: 10px;

}

.game {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  width: fit-content;
  margin: 0 auto;
  font-size: 20px;
  font-family: 'Times New Roman', Times, serif;
  margin: 0 auto;
  background: white;
  padding: 20px 20px;
  border-radius: 10px;
  box-shadow:
    0 10px 30px rgba(0, 0, 0, 0.15);

}
</style>
