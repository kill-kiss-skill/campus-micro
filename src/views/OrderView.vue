<template>
  <div>
    <div style="margin-bottom:10px; display:flex; gap:10px; align-items:center;">
      <el-input 
        v-model.number="searchId" 
        placeholder="输入订单ID查询" 
        style="width:200px;"
      />
      <el-button @click="searchById">查询</el-button>
      <el-button type="primary" @click="add">创建订单</el-button>
      <el-button @click="getList">显示全部</el-button>
    </div>

    <!-- 表格：空数据不渲染 -->
    <el-table v-if="list.length > 0" :data="list" border style="width:100%">
      <el-table-column prop="id" label="订单ID" />
      <el-table-column prop="userId" label="用户ID" />
      <el-table-column prop="productId" label="商品ID" />
      <el-table-column prop="price" label="价格" />
      <el-table-column label="操作">
        <template #default="scope">
          <el-button type="danger" size="mini" @click="deleteItem(scope.row.id)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <!-- 空数据提示 -->
    <el-empty v-else description="暂无数据" />

    <el-dialog title="创建订单" :visible.sync="dialogVisible" width="500px">
      <el-form :model="form" label-width="80px">
        <el-form-item label="用户ID"><el-input v-model.number="form.userId" /></el-form-item>
        <el-form-item label="商品ID"><el-input v-model.number="form.productId" /></el-form-item>
        <!-- 这一行修复了标签闭合 -->
        <el-form-item label="价格"><el-input v-model.number="form.price" /></el-form-item>
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
      form: { userId: '', productId: '', price: '' }
    }
  },
  mounted() {
    this.getList()
  },
  methods: {
    async getList() {
      const res = await request.get('/api/order/list')
      this.list = res.data || []
    },
    async searchById() {
      if (!this.searchId) { this.$message.warning('请输入ID'); return }
      try {
        const res = await request.get(`/api/order/get/${this.searchId}`)
        this.list = res.data ? [res.data] : []
        if (!res.data) this.$message.info('未查询到该订单')
      } catch (e) {
        this.list = []
        this.$message.error('未查询到该订单')
      }
    },
    add() {
      this.form = { userId: '', productId: '', price: '' }
      this.dialogVisible = true
    },
    async submit() {
      await request.post('/api/order/create', this.form)
      this.$message.success('创建成功')
      this.dialogVisible = false
      this.getList()
    },
    async deleteItem(id) {
      this.$confirm('确定删除该订单？').then(async () => {
        await request.delete(`/api/order/delete/${id}`)
        this.$message.success('删除成功')
        this.getList()
      }).catch(() => {})
    }
  }
}
</script>