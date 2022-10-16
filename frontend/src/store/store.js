import {createStore} from 'vuex'
import CoreStore from './core.js'
const store = createStore({
    modules: {
        core: CoreStore
    }
 })
 
 export default store
