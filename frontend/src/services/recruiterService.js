import axios from 'axios'

const apiClient = axios.create({
  baseURL: `http://localhost:8080/r`,
  timeout: 5000,
})

export async function saveRecruiterData(recruiter) {
  try {
    const res = await apiClient.post(`/add`, {
      name: recruiter.name,
      company: recruiter.company,
    })
    return res.data
  } catch (error) {
    console.log(error)
    return null
  }
}

export async function getQuestions(id) {
  try {
    const res = await apiClient.get(`/${id}`)
    return res.data
  } catch (error) {
    console.log(error)
    return null
  }
}

export async function getPoints(id) {
  try {
    let res = await apiClient.get(`/points/${id}`)
    return res.data
  } catch (error) {
    console.log(error)
    return null
  }
}

export async function getQuesResults(id) {
  try {
    const res = await apiClient.get(`/results/${id}`)
    return res.data
  } catch (error) {
    console.log(error)
    return null
  }
}

export async function saveRecruiterAnswers(recruiterAnswers) {
  try {
    const res = await apiClient.post(`/answers`, recruiterAnswers)
    return res.status
  } catch (error) {
    console.log(error)
    return null
  }
}

export async function getRanking() {
  try {
    const res = await apiClient.get(`/recruiter_list`)
    return res.data
  } catch (error) {
    console.log(error)
    return null
  }
}

export async function getRecData(id) {
  try {
    const res = await apiClient.get(`/recruiter_data/${id}`)
    return res.data
  } catch (error) {
    console.log(error)
    return null
  }
}
