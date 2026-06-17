<script setup>
import Question from './Question.vue';
import { inject, watch, ref } from 'vue';
import { getPoints } from '@/services/recruiterService'
import RankingRecs from './RankingRecs.vue';

const props = defineProps(['isrecruiterDataSaved', 'questions', 'color', 'recruiterID'])

const areQesAnswered = inject('areQesAnswered')

const emit = defineEmits(['eva-rec-input'])

function showResult() {
    emit('eva-rec-input')
}
const achievedPoints = ref(0)

watch(areQesAnswered, async (n, o) => {
    achievedPoints.value = await getPoints(props.recruiterID)
})


</script>

<template>
    <div class="question_list" v-show="props.isrecruiterDataSaved">
        <Question v-for="(qes, index) in props.questions" :key="index" :question="qes"
            :style="{ borderImage: props.color[index] }" :index="index">
        </Question>

        <p v-if="areQesAnswered" id="result">{{ achievedPoints }} of 5 points</p>
        <RankingRecs v-if="areQesAnswered" :recruiterID="recruiterID"></RankingRecs>
        <button id="btn_result" :disabled="areQesAnswered" @click="showResult">Check</button>
    </div>
</template>

<style>
#result {
    text-align: center;
    font-weight: 700;
    color: rgb(92, 90, 90);
}

.question_list {
    display: flex;
    flex-direction: column;
    flex-wrap: nowrap;
    justify-content: center;
    align-items: center;
}



#btn_result {
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

#btn_result:hover {
    transform: translateY(-3px);

    box-shadow:
        0 10px 24px rgba(28, 28, 224, 0.45),
        0 6px 12px rgba(26, 198, 158, 0.35);
}

#btn_result:active {
    transform: translateY(1px);
}
</style>