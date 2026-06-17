<script setup>
import { onMounted, reactive, ref } from 'vue'
import { getRanking, getRecData } from '../services/recruiterService'
//receive data from service

const recruitersList = ref([]);
const props = defineProps(["recruiterID"])
const currentRecruiter = ref()
let currentRecruiterPos = 0
onMounted(async () => {
    recruitersList.value = await getRanking()
    currentRecruiter.value = await getRecData(props.recruiterID)
    currentRecruiterPos = recruitersList.value.map(rec => rec.name).indexOf(currentRecruiter.value.name)
})

</script>

<template>
    <div class="ranking">
        <table>
            <thead>
                <tr>
                    <th>Name</th>
                    <th>Company</th>
                    <th>Achieved Points</th>
                </tr>
            </thead>
            <tbody>
                <tr v-for="(recruiter, index) in recruitersList" :key="index"
                    :class="{ highlighted: currentRecruiterPos == index }">
                    <td>
                        {{ recruiter.name }}
                    </td>
                    <td>
                        {{ recruiter.company }}

                    </td>
                    <td>
                        {{ recruiter.achievedPoints }}

                    </td>
                </tr>
            </tbody>
        </table>

    </div>

</template>

<style>
.highlighted {
    background: linear-gradient(rgba(28, 28, 224, 0.6),
            rgba(26, 198, 158, 0.6));
    color: white;
}

table {
    width: 100%;
    border-collapse: collapse;
    text-align: center;
    font-family: 'Times New Roman', Times, serif;
    border-bottom: 2px solid #333;
}

thead {
    border-top: 2px solid #333;
}

thead th {
    padding: 12px;
    border-bottom: 2px solid #333;
    font-weight: bold;
}

tbody td {
    padding: 10px;
}
</style>