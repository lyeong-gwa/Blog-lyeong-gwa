const state = {
  drawer: true
}

// mutations
const mutations = {
   changeDrawer(state){
    state.drawer = !state.drawer
   }
}

// actions
const actions = {
  callMutation ({ commit }) {
    commit('changeDrawer')
  }
}



export default {
namespaced: true,
state,
mutations,
actions,
}