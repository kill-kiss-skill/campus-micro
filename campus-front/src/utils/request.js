import axios from 'axios'

const request = axios.create({
  baseURL: 'http://localhost:8888',  // 网关地址
  timeout: 5000
})

// 直接返回后端的 data
request.interceptors.response.use(res => {
  return res.data
})

export default request