<template>
    <div>
        <h1>Online test</h1>
        <nav>
            <router-link to="/">Home</router-link> |
            <span v-if="!isLoggedIn">
                <router-link to="/auth">Login</router-link> |
                <router-link to="/auth">Register</router-link>
            </span>
            <span v-if="isLoggedIn">
                <router-link to="/test">Test</router-link>
                | <a @click="logout">Logout</a>
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
            this.$store.commit('signout')
            this.$router.push('/auth')
        }
    },
}
</script>

<style>
</style>