import Vue from 'vue'
import Router from 'vue-router'
import LoginView from '@/views/LoginView'
import UserView from '@/views/UserView'
import ProductView from '@/views/ProductView'
import OrderView from '@/views/OrderView'
import NoticeView from '@/views/NoticeView'

Vue.use(Router)

const router = new Router({
  mode: 'history',
  routes: [
    { path: '/login', component: LoginView },
    { path: '/user', component: UserView, meta: { requireAuth: true } },
    { path: '/product', component: ProductView, meta: { requireAuth: true } },
    { path: '/order', component: OrderView, meta: { requireAuth: true } },
    { path: '/notice', component: NoticeView, meta: { requireAuth: true } },
    { path: '/', redirect: '/login' }
  ]
})

// 登录拦截（必须加）
router.beforeEach((to, from, next) => {
  const token = sessionStorage.getItem('token')
  if (to.meta.requireAuth && !token) {
    next('/login')
    this.$message.warning('请先登录')
  } else {
    next()
  }
})

export default router