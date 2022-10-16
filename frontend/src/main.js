import {createApp} from 'vue'
import App from './App.vue'

import vuetify from './plugins/vuetify' //vuetify사용
import Vuex from 'vuex'

import { loadFonts } from './plugins/webfontloader' //구글 폰트, 아이콘 사용
import {router} from './router/router';//라우터
import 'es6-promise/auto' //ES6방식의 promise 적용
import store from './store/store' //store.js 사용

//npm install bootstrap @properjs/core
import "bootstrap/dist/css/bootstrap.min.css"
import "bootstrap"
loadFonts()

createApp(App)
  .use(router)  
  .use(vuetify)
  .use(Vuex)
  .use(store)
  .mount('#app');


