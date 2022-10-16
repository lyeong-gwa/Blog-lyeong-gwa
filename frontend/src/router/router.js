import { createWebHistory, createRouter } from 'vue-router'
import Home from '../views/Home.vue'
import CogemUtil from '../views/CogemUtil.vue'
import About from '../views/About.vue'

//라우터에 보여줄 것들은 view 그 외에는 컴포넌트
const routes = [
  { 
    path: '/', 
    redirect: '/Home'
  },{
    path: '/Home',
    name: 'Home-route',
    component: Home,
  },{
    path: '/CogemUtil',
    name: 'CogemUtil-route',
    component: CogemUtil,
  },{
    path: '/About',
    name: 'About-route',
    component: About,
  }
];

export const router = createRouter({
  history: createWebHistory(),
  routes,
});