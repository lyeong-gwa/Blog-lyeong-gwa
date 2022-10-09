// state
const state = {
    message: 'example'
}

// mutations
const mutations = {
     changeMessage (state, newMsg) {
      state.message = newMsg
    }
}

// actions
const actions = {
    callMutation ({ state, commit }, { newMsg }) {
      state
      commit('changeMessage', newMsg)
    }
}

// getters
const getters = {
    getMsg (state) {
      return `${state.message} => Length : ${state.message.length}`
    }
}

export default {
  namespaced: true,
  state,
  mutations,
  actions,
  getters
}

/*
store.js -> 모듈안에 모듈명: import명 선언

사용컴포넌트.vue
import { createNamespacedHelpers } from 'vuex'
const Helper명 = createNamespacedHelpers('모듈명')

computed: { // this.~~
    ...Helper명.mapState({
        message: state => state.message
    }),
    ...Helper명.mapGetters([
       'getMsg'
    ])
  },
  methods: { //this.~~()
    ...Helper명.mapMutations([
        'changeMessage' 
    ]),
    ...Helper명.mapActions([
        'callMutation'
    ])
  }
---
{{this.message}}<br>
{{this.getMsg}}<br>
<button v-on:click="this.changeMessage('good')">  문자열 추가</button>

*/