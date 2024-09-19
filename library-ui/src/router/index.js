import { createRouter, createWebHistory } from 'vue-router'
import Layout from "@/layout/Layout.vue";


const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path:'/',
      name: 'layout',
      component: Layout,
    },
    {
      path: '/register',
      name: 'register',
      component: () => import('@/views/register.vue'),
    },
    {
      path: '/login',
      name: 'login',
      component: () =>import('@/views/Login.vue'),
    },
  ]
})

export default router
