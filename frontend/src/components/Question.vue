<script setup>
import Answer from './Answer.vue';

const props = defineProps(['question', 'index'])

</script>

<template>
    <div class="header">
        <p class="text">{{ props.question.text }}</p>
        <p class="points">Points: {{ props.question.points }}</p>
        <Answer class="answer" v-for="(answer, i) in props.question.answers" :key="i" :index="i" :answer="answer">
        </Answer>
    </div>
</template>

<style></style>