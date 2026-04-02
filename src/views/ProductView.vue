<template>
  <div>
    <!-- 顶部：搜索框 + 功能按钮 -->
    <div style="margin-bottom:10px; display:flex; gap:10px; align-items:center;">
      <el-input 
        v-model.number="searchId" 
        placeholder="输入商品ID查询" 
        style="width:200px;"
      />
      <el-button @click="searchById">查询</el-button>
      <el-button type="primary" @click="add">新增商品</el-button>
      <el-button @click="getList">显示全部</el-button>
    </div>

    <!-- 表格：v-if 控制，空数据不渲染操作列 -->
    <el-table v-if="list.length > 0" :data="list" border style="width:100%">
      <el-table-column prop="id" label="商品ID" />
      <el-table-column prop="name" label="商品名称" />
      <el-table-column prop="price" label="价格" />
      <el-table-column prop="stock" label="库存" />

      <el-table-column label="操作">
        <template #default="scope">
          <el-button type="warning" size="mini" @click="edit(scope.row)">修改</el-button>
          <el-button type="danger" size="mini" @click="deleteItem(scope.row.id)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <!-- 空数据提示 -->
    <el-empty v-else description="暂无数据" />

    <!-- 新增/修改弹窗 -->
    <el-dialog title="商品信息" :visible.sync="dialogVisible" width="500px">
      <el-form :model="form" label-width="80px">
        <el-form-item label="商品名称">
          <el-input v-model="form.name" />
        </el-form-item>
        <el-form-item label="价格">
          <el-input v-model.number="form.price" />
        </el-form-item>
        <el-form-item label="库存">
          <el-input v-model.number="form.stock" />
        </el-form-item>
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
      form: { id: '', name: '', price: '', stock: '' }
    }
  },
  mounted() {
    this.getList()
  },
  methods: {
    async getList() {
      const res = await request.get('/api/product/list')
      this.list = res.data || []
    },
    async searchById() {
      if (!this.searchId) {
        this.$message.warning('请输入ID')
        return
      }
      try {
        const res = await request.get(`/api/product/get/${this.searchId}`)
        this.list = res.data ? [res.data] : []
        if (!res.data) {
          this.$message.info('未查询到该商品')
        }
      } catch (e) {
        this.list = []
        this.$message.error('未查询到该商品')
      }
    },
    add() {
      this.form = { id: '', name: '', price: '', stock: '' }
      this.dialogVisible = true
    },
    edit(row) {
      this.form = { ...row }
      this.dialogVisible = true
    },
    async submit() {
      if (this.form.id) {
        await request.put('/api/product/update', this.form)
        this.$message.success('修改成功')
      } else {
        await request.post('/api/product/add', this.form)
        this.$message.success('新增成功')
      }
      this.dialogVisible = false
      this.getList()
    },
    async deleteItem(id) {
      this.$confirm('确定删除该商品？').then(async () => {
        await request.delete(`/api/product/delete/${id}`)
        this.$message.success('删除成功')
        this.getList()
      }).catch(() => {})
    }
  }
}
</script>