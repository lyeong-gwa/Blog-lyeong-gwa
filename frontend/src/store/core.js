const state = {
  drawer: true,
  pathName: "Home"
}

// mutations
const mutations = {
   changeDrawer(state){
    state.drawer = !state.drawer
   },
   changePathName(state,newPath){
    state.pathName = newPath
   }
}





export default {
namespaced: true,
state,
mutations
}