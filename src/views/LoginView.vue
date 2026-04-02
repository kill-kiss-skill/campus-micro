<template  name="LoginView">
  <el-card style="width:400px;margin:100px auto;">
    <h3 style="text-align:center;margin-bottom:20px;">校园微服务平台登录</h3>
    <el-form @submit.native.prevent="login">
      <el-form-item label="用户名">
        <el-input v-model="form.username"></el-input>
      </el-form-item>
      <el-form-item label="密码">
        <el-input v-model="form.password" type="password"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" native-type="submit" style="width:100%">登录</el-button>
      </el-form-item>
    </el-form>
  </el-card>
</template>

<script>
// 引入你的请求工具
import request from '@/utils/request'

export default {
  name: 'LoginView',
  data() {
    return {
      form: {
        username: '',
        password: ''
      }
    }
  },
  methods: {
    async login() {
      try {
        // 调用你后端真实登录接口
        const res = await request.post('/api/user/login', this.form)
        
        // 保存token和用户信息
        sessionStorage.setItem('token', res.data.token)
        sessionStorage.setItem('user', JSON.stringify(res.data.user))

        this.$message.success('登录成功')
        this.$router.push('/user')
      } catch (err) {
        this.$message.error('用户名或密码错误')
      }
    }
  }
}
</script>