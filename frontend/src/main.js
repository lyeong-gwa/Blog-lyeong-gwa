import {createApp} from 'vue'
import App from './App.vue'
import vuetify from './plugins/vuetify'
import Vuex from 'vuex'
import { loadFonts } from './plugins/webfontloader'
import {router} from './router/router';
import 'es6-promise/auto'
import store from './store/store' 
loadFonts()

createApp(App)
  .use(router)  
  .use(vuetify)
  .use(Vuex)
  .use(store)
  .mount('#app');


