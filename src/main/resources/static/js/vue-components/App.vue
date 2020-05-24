<template>
    <div>
        <h1>Online test</h1>
        <nav>
            <span v-if="!isLoggedIn">
                <router-link to="/auth">Войти</router-link> |
                <router-link to="/registration">Зарегистрироваться</router-link>
            </span>
            <span v-if="isLoggedIn">
                <router-link to="/test/start">Пройти тест</router-link> |
                <router-link to="/result">Мои ответы</router-link> |
                <router-link to="/newQuestion">Добавить новый вопрос</router-link> |
                <a @click="logout" href="/">Выйти</a>
            </span>
        </nav>
        <br/>
        <router-view></router-view>
    </div>
</template>

<script>
export default {
    computed : {
        isLoggedIn : function(){ return this.$store.getters.isLoggedIn}
    },
    methods: {
        logout: function () {
            location.reload();
        }
    },
}
</script>

<style>
</style>