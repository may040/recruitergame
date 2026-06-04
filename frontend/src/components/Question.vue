<script setup>
import Answer from './Answer.vue';
import { inject, watch } from 'vue';

const props = defineProps(['question', 'index'])
const areResultsLoaded = inject('areResultsLoaded')
const evaRecAnswers = inject('evaRecAnswers')
const areQesAnswered = inject('areQesAnswered')

</script>

<template>
    <div class="question">
        <div class="header">
            <p class="text">{{ props.question.text }}</p>
            <p class="points">Points: {{ props.question.points }}</p>
        </div>
        <div class="answers">
            <Answer v-for="(answer, i) in props.question.answers" :key="i" :index="props.index" :answer="answer">
            </Answer>
        </div>
        <p id="correctAnswer" v-if="areQesAnswered && areResultsLoaded">Korrekte
            Antwort: {{
                evaRecAnswers[props.index].correctAnswer
            }}
        </p>
    </div>

</template>

<style>
.points {
    text-align: right;
    margin: 0px;

}

.text {
    margin: 0px;

}

.answers {
    display: flex;
    flex-direction: column;
    flex-wrap: nowrap;
    justify-content: center;
    align-items: flex-start;

}

.header {
    display: flex;
    flex-direction: row;
    justify-content: space-between;
    align-items: center;
    width: 100%;
    margin-bottom: 10px;
}


.question {
    margin: 12px auto;
    width: 400px;
    padding: 16px;

    border-radius: 10px;

    color: black;

    background: rgba(255, 255, 255, 0.14);

    border: 5px solid;


    border-image: linear-gradient(135deg,
            rgb(28, 28, 224),
            rgb(26, 198, 158)) 1;

    box-shadow:
        0 6px 10px rgba(0, 0, 0, 0.25),
        0 18px 40px rgba(0, 0, 0, 0.35);

    transition: transform 0.2s ease, box-shadow 0.2s ease;
}

.question:hover {
    transform: translateY(-6px);

    box-shadow:
        0 10px 18px rgba(0, 0, 0, 0.3),
        0 25px 60px rgba(0, 0, 0, 0.45);
}
</style>