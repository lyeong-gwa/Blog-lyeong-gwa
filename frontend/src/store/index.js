import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

import CoreStore from './core.js'
export default new Vuex.Store({
  state: {
  },
  getters: {
  },
  mutations: {
  },
  actions: {
  },
  modules: {
    core:CoreStore,
  }
})
