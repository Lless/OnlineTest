<template>
    <div>
        <h1>Online test</h1>
        <nav>
            <router-link to="/">Home</router-link> |
            <span v-if="!isLoggedIn">
                <router-link to="/auth">Login</router-link> |
                <router-link to="/registration">Register</router-link>
            </span>
            <span v-if="isLoggedIn">
                <router-link to="/test/start">Test</router-link> |
                <router-link to="/result">My answers</router-link> |
                <router-link to="/newQuestion">Add new question</router-link> |
                <a @click="logout" href="/">Logout</a>
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
            //this.$store.commit('signout')
            //this.$router.push('/auth')
        }
    },
}
</script>

<style>
</style>