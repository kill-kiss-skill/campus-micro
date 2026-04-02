<template>
  <div>
    <!-- 搜索栏 -->
    <div style="margin-bottom:10px; display:flex; gap:10px; align-items:center;">
      <el-input 
        v-model.number="searchId" 
        placeholder="输入公告ID查询" 
        style="width:200px;"
      />
      <el-button @click="searchById">查询</el-button>
      <el-button type="primary" @click="add">新增公告</el-button>
      <el-button @click="getList">显示全部</el-button>
    </div>

    <!-- 表格：有数据才显示 -->
    <el-table v-if="list.length > 0" :data="list" border style="width:100%">
      <el-table-column prop="id" label="公告ID" />
      <el-table-column prop="title" label="标题" />
      <el-table-column prop="content" label="内容" />

      <el-table-column label="操作">
        <template #default="scope">
          <el-button type="warning" size="mini" @click="edit(scope.row)">修改</el-button>
          <el-button type="danger" size="mini" @click="deleteItem(scope.row.id)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <!-- 空数据 -->
    <el-empty v-else description="暂无公告" />

    <!-- 新增/修改弹窗 -->
    <el-dialog title="公告信息" :visible.sync="dialogVisible" width="600px">
      <el-form :model="form" label-width="80px">
        <el-form-item label="标题">
          <el-input v-model="form.title" />
        </el-form-item>
        <el-form-item label="内容">
          <el-input type="textarea" :rows="4" v-model="form.content" />
        </el-form-item>
      </el-form>

      <div slot="footer">
        <el-button @click="dialogVisible = false">取消</el-button>
        <el-button type="primary" @click="submit">确定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import request from '../utils/request'
export default {
  name: "NoticeView",
  data() {
    return {
      list: [],
      searchId: '',
      dialogVisible: false,
      form: {
        id: '',
        title: '',
        content: ''
      }
    }
  },
  mounted() {
    this.getList()
  },
  methods: {
    // 查询全部
    async getList() {
      const res = await request.get('/api/notice/list')
      this.list = res.data || []
    },

    // 根据ID查询
    async searchById() {
      if (!this.searchId) {
        this.$message.warning('请输入ID')
        return
      }
      try {
        const res = await request.get(`/api/notice/get/${this.searchId}`)
        this.list = res.data ? [res.data] : []
        if (!res.data) {
          this.$message.info('未查询到该公告')
        }
      } catch (e) {
        this.list = []
        this.$message.error('未查询到该公告')
      }
    },

    // 新增
    add() {
      this.form = { id: '', title: '', content: '' }
      this.dialogVisible = true
    },

    // 修改
    edit(row) {
      this.form = { ...row }
      this.dialogVisible = true
    },

    // 提交（新增/修改）
    async submit() {
      if (this.form.id) {
        await request.put('/api/notice/update', this.form)
        this.$message.success('修改成功')
      } else {
        await request.post('/api/notice/add', this.form)
        this.$message.success('新增成功')
      }
      this.dialogVisible = false
      this.getList()
    },

    // 删除
    async deleteItem(id) {
      this.$confirm('确定删除该公告吗？').then(async () => {
        await request.delete(`/api/notice/delete/${id}`)
        this.$message.success('删除成功')
        this.getList()
      }).catch(() => {})
    }
  }
}
</script>