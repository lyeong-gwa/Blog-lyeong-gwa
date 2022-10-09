import {createStore} from 'vuex'
//import ExampleStore from './example/example.js'
import CoreStore from './core.js'
const store = createStore({
    modules: {
        //example: ExampleStore
        core: CoreStore
    }
 })
 
 export default store
