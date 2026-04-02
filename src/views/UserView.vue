<template>
  <div>
    <!-- 搜索框 -->
    <div style="margin-bottom:10px; display:flex; gap:10px; align-items:center;">
      <el-input 
        v-model.number="searchId" 
        placeholder="输入用户ID查询" 
        style="width:200px;"
      />
      <el-button @click="searchById">查询</el-button>
      <el-button type="primary" @click="add">新增用户</el-button>
      <el-button @click="getList">显示全部</el-button>
    </div>

    <!-- 表格：空数据不渲染 -->
    <el-table v-if="list.length > 0" :data="list" border style="width:100%">
      <el-table-column prop="id" label="用户ID" />
      <el-table-column prop="username" label="用户名" />
      <el-table-column prop="phone" label="电话" />
      <el-table-column prop="password" label="密码" />
      <el-table-column label="操作">
        <template #default="scope">
          <el-button type="warning" size="mini" @click="edit(scope.row)">修改</el-button>
          <el-button type="danger" size="mini" @click="deleteItem(scope.row.id)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <!-- 空数据提示 -->
    <el-empty v-else description="暂无数据" />

    <el-dialog title="用户信息" :visible.sync="dialogVisible" width="500px">
      <el-form :model="form" label-width="80px">
        <el-form-item label="用户名"><el-input v-model="form.username" /></el-form-item>
        <el-form-item label="电话"><el-input v-model="form.phone" /></el-form-item>
        <el-form-item label="密码"><el-input v-model="form.password" show-password /></el-form-item>
      </el-form>
      <div slot="footer">
        <el-button @click="dialogVisible=false">取消</el-button>
        <el-button type="primary" @click="submit">确定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import request from '@/utils/request'
export default {
  data() {
    return {
      list: [],
      searchId: '',
      dialogVisible: false,
      form: { id: '', username: '', phone: '', password: '' }
    }
  },
  mounted() {
    this.getList()
  },
  methods: {
    async getList() {
      const res = await request.get('/api/user/list')
      this.list = res.data || []
    },
    async searchById() {
      if (!this.searchId) { this.$message.warning('请输入ID'); return }
      try {
        const res = await request.get(`/api/user/get/${this.searchId}`)
        this.list = res.data ? [res.data] : []
        if (!res.data) this.$message.info('未查询到该用户')
      } catch (e) {
        this.list = []
        this.$message.error('未查询到该用户')
      }
    },
    add() {
      this.form = { id: '', username: '', phone: '', password: '' }
      this.dialogVisible = true
    },
    edit(row) {
      this.form = { ...row }
      this.dialogVisible = true
    },
    async submit() {
      if (this.form.id) {
        await request.put('/api/user/update', this.form)
        this.$message.success('修改成功')
      } else {
        await request.post('/api/user/add', this.form)
        this.$message.success('新增成功')
      }
      this.dialogVisible = false
      this.getList()
    },
    async deleteItem(id) {
      this.$confirm('确定删除该用户？').then(async () => {
        await request.delete(`/api/user/delete/${id}`)
        this.$message.success('删除成功')
        this.getList()
      }).catch(() => {})
    }
  }
}
</script>