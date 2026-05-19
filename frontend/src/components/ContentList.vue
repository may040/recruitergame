<script setup>
import Question from './Question.vue';

const props = defineProps(['isrecruiterDataSaved', 'questions', 'color'])



</script>

<template>
    <div class="question_list" v-show="props.isrecruiterDataSaved">
        <Question class="question" v-for="(qes, index) in props.questions" :key="index" :question="qes"
            :style="{ borderImage: props.color[index] }" :index="index">


        </Question>


    </div>
</template>

<style>
.question_list {
    display: flex;
    flex-direction: column;
    flex-wrap: nowrap;
    justify-content: center;
    align-items: center;
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