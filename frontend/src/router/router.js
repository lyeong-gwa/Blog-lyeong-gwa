import { createWebHistory, createRouter } from 'vue-router'
import Home from '../views/Home.vue'
import Content from '../views/Content.vue'
import Development from '../views/Development.vue'
import About from '../views/About.vue'

//라우터에 보여줄 것들은 view 그 외에는 컴포넌트
const routes = [
  { 
    path: '/', 
    redirect: '/Home'
  },{
    path: '/',
    name: 'Home-route',
    component: Home,
  },{
    path: '/Content',
    name: 'Content-route',
    component: Content,
  },{
    path: '/Development',
    name: 'Development-route',
    component: Development,
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