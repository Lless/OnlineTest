import Vue from 'vue'
import App from 'vue-components/App.vue'
import 'vue-modules/vue-resource'
import store from 'vue-modules/vuex'
import router from 'vue-modules/vue-router'
import '@babel/polyfill'

new Vue({
    el: '#app',
    store,
    router,
    render: a => a(App),
})