import Vue from 'vue'
import VueRouter from 'vue-router'
import Test from 'vue-components/Test.vue'
import Auth from 'vue-components/Auth.vue'

Vue.use(VueRouter)

export default new VueRouter({
    mode: 'history',
    routes: [
        { path:'/', component: Test },
        { path:'/auth', component: Auth },
        { path:'*', component: Test }
    ]
})