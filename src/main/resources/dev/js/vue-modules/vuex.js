import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
    state: {
        loggedIn : false
    },
    mutations: {
        signin(state) {
            state.loggedIn=true
        },
        signout(state) {
            state.loggedIn=true
        }
    },
    actions: {
    },
    getters : {
        isLoggedIn: state => state.loggedIn
    }
})