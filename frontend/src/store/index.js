import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

import CoreStore from './core.js'
import CogemUtilStore from './cogemutil.js'
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
    cogemutil:CogemUtilStore,
  }
})
