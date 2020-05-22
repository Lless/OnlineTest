import Vue from 'vue'
import VueRouter from 'vue-router'
import Test from 'vue-components/Test.vue'
import Auth from 'vue-components/Auth.vue'
import Main from 'vue-components/MainPage.vue'
import store from 'vue-modules/vuex.js'

Vue.use(VueRouter)

let router = new VueRouter({
    mode: 'history',
    routes: [
        { path:'/', component: Main },
        { path:'/auth', component: Auth },
        { path:'/test/start', component: Test, meta: { requiresAuth: true }},
        { path:'*', component: Main }
    ]
})

router.beforeEach((to, from, next) => {
    if(to.matched.some(record => record.meta.requiresAuth))
        if (store.getters.isLoggedIn) next()
        else next('/auth')
    else next()
})

export default router