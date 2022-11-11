const state = {
  drawer: true,
  pathName: "Home"
}

const getters = {
  getDrawer(state){
    return state.drawer;
  },
  getPathName(state){
    return state.pathName;
  }
}

const actions = {
  changeDrawer(context){
    context.commit('changeDrawer',{a:"1"});
  }
}

// mutations
const mutations = {
   changeDrawer(state){
    state.drawer = !state.drawer
   },

}






export default {
namespaced: true,
state,
getters,
actions,
mutations
}