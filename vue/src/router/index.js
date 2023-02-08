import { createRouter, createWebHistory } from 'vue-router'
// import HomeView from '../views/HomeView.vue'
import {guardReactiveProps, handleError} from "vue";

const router = createRouter({
  history: createWebHistory("/"),
  routes: [
    // {
    //   path: '/',
    //   name: 'Home',
    //   component: () => import('../views/index.vue')
    // },
    {
      path: '/login',
      name: 'Login',
      component: () => import('../views/Login.vue')
    }
  ]
});

/*
路由守卫
*/
// router.beforeEach((guard)=>{
//   beforeEach.checkAuth(guard,router);
// });

/*
输出对象
 */
export default router
