import Vue from 'vue'
import App from './App.vue'
import vuetify from './plugins/vuetify'
import router from './router'
import store from './store'
import WebFont from 'webfontloader';

Vue.config.productionTip = false

WebFont.load({
  google: { families: ["Roboto:100,300,400,500,700,900&display=swap"] }
});

new Vue({
  vuetify,
  router,
  store,
  render: h => h(App)
}).$mount('#app')
