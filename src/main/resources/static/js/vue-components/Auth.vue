<template>
    <div>
        <form @submit.prevent="auth">
            <h3>Вход</h3>
            <input required v-model="username" placeholder="Имя"/>
            <br/>
            <input required v-model="password" type="password" placeholder="Пароль"/>
            <br/>
            <button type="submit">Войти</button>
        </form>
        <br/>
        <br/>
        <router-link to="/registration">Зарегистрироваться</router-link>
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
            auth: function () {
                let username = this.username
                let password = this.password
                this.$http.post('/login', { username, password })
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