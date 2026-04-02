<template>
  <div id="app">
    <el-container style="height: 100vh;">
      <!-- 左侧侧边栏：登录页隐藏 -->
      <el-aside width="200px" v-if="$route.path !== '/login'">
        <el-menu router :default-active="$route.path" background-color="#304156" text-color="#fff" active-text-color="#ffd04b">
          <el-menu-item index="/user">用户管理</el-menu-item>
          <el-menu-item index="/product">商品管理</el-menu-item>
          <el-menu-item index="/order">订单管理</el-menu-item>
          <el-menu-item index="/notice">公告管理</el-menu-item>
        </el-menu>
      </el-aside>

      <el-container>
        <!-- 顶部栏：右上角放退出按钮，登录页隐藏 -->
        <el-header v-if="$route.path !== '/login'" height="60px" style="display:flex;justify-content:flex-end;align-items:center;padding:0 20px;border-bottom:1px solid #e6e6e6;">
          <el-button type="danger" size="small" @click="logout">退出登录</el-button>
        </el-header>

        <el-main>
          <router-view></router-view>
        </el-main>
      </el-container>
    </el-container>
  </div>
</template>

<script>
export default {
  name: 'App',
  methods: {
    logout() {
      this.$confirm('确定要退出登录吗？', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        // 清除登录状态
        sessionStorage.clear()
        this.$message.success('退出登录成功')
        // 跳回登录页
        this.$router.push('/login')
      }).catch(() => {})
    }
  }
}
</script>

<style>
*{margin:0;padding:0;}
.el-header{
  background-color: #fff;
}
</style>