import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '../views/HomeView.vue'
import CogemUtil from '../views/CogemUtilView.vue'
import About from '../views/AboutView.vue'

Vue.use(VueRouter)

//라우터에 보여줄 것들은 view 그 외에는 컴포넌트
const routes = [
  { 
    path: '/', 
    redirect: '/Home'
  },{
    path: '/Home',
    component: Home,
  },{
    path: '/CogemUtil',
    component: CogemUtil,
  },{
    path: '/About',
    component: About,
  }
];

const router = new VueRouter({
  routes
})

export default router
