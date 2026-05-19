<script setup>
import { inject } from 'vue';

const props = defineProps(['answer', 'index'])
const areQesAnswered = inject('areQesAnswered')
const selectedAnswers = inject('selectedAnswers')

</script>

<template>
    <div>
        <label>
            <input class="r_answer" type="radio" :disabled="areQesAnswered" :value="props.answer.text"
                v-model="selectedAnswers[props.index]">{{
                    props.answer.text }}</input>
        </label>
    </div>
</template>

<style>
.r_answer {
    appearance: none;
    -webkit-appearance: none;

    width: 18px;
    height: 18px;

    border-radius: 50%;

    border: 3px solid transparent;

    background:
        linear-gradient(white, white) padding-box,
        linear-gradient(135deg,
            rgb(28, 28, 224),
            rgb(26, 198, 158)) border-box;

    cursor: pointer;

    transition: all 0.2s ease;

    position: relative;

    margin-right: 10px;
}

.r_answer:checked::before {
    content: "";

    position: absolute;

    width: 8px;
    height: 8px;

    border-radius: 50%;

    background: linear-gradient(135deg,
            rgb(28, 28, 224),
            rgb(26, 198, 158));

    top: 50%;
    left: 50%;

    transform: translate(-50%, -50%);
}

.r_answer:hover {
    transform: scale(1.08);

    box-shadow:
        0 0 10px rgba(28, 28, 224, 0.35),
        0 0 14px rgba(26, 198, 158, 0.25);
}
</style>