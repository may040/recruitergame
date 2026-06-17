<script setup>
import { ref, reactive, watch, provide } from 'vue'
import RecruiterForm from './components/RecruiterForm.vue';
import { getQuestions, getQuesResults, saveRecruiterAnswers } from '@/services/recruiterService'
import ContentList from './components/ContentList.vue';

const recruiter = reactive({ name: "", company: "", id: null })
const isrecruiterDataSaved = ref(false)
let color = reactive(["white", "white", "white", "white", "white"])
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

async function determineBorderColor() {
  evaRecAnswers.value = await getQuesResults(recruiter.id)
  for (let index = 0; index < color.length; index++) {
    color[index] = evaRecAnswers.value[index].answeredCorrect ? 'linear-gradient(135deg,  rgb(34, 197, 94),  rgb(16, 185, 129),  rgb(74, 222, 128)) 1' : 'linear-gradient(135deg,    rgb(239, 68, 68),  rgb(220, 38, 38),  rgb(248, 113, 113)) 1'
  }
}


async function showResult() {
  createRecruiterAnswer()
  await saveRecruiterAnswers(recruiterAnswers)
  await determineBorderColor()
  areQesAnswered.value = true
  areResultsLoaded.value = true
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
  min-height: 99vh;
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
