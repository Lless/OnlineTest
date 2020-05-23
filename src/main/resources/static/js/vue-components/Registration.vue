<template>
    <div>
        <form @submit.prevent="register">
            <h3>Register</h3>
            <input required v-model="username" placeholder="Name"/>
            <br/>
            <input required v-model="password" type="password" placeholder="Password"/>
            <br/>
            <button type="submit">Register</button>
        </form>
        <br/>
        <br/>
        <router-link to="/auth">Login</router-link>
    </div>
</template>

<script>
    import Vue from "vue"
    export default {
        data() {
            return {
                username : "",
                password : ""
            }
        },
        methods: {
            register: function () {
                let username = this.username
                let password = this.password
                this.$http.post('/register', { username, password })
                    .then( res => {
                        if (! res) return;
                        this.$store.commit("signin")
                        Vue.http.interceptors.push((request, next) => {
                            request.headers.set('Authorization', 'Basic ' + window.btoa(username + ':' + password))
                            next()
                        })
                        this.$router.push('/')
                    })
                    .catch(err =>  this.$http.interceptors.push((request, next) => {
                            request.headers.set('Authorization', 'Basic ' + window.btoa(username + ':' + password))
                            next()
                        }))
            }
        }
    }
</script>

<style>
</style>