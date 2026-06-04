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
