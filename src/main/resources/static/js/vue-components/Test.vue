<template>
    <form>
        {{ test.question }}
        <br/>
        <input type="text" placeholder="answer" v-model="answer" />
        <input type="button" value="Save" @click="send" />
    </form>
</template>

<script>
    export default {
        data() {
            return {
                test: "",
                answer: ""
            }
        },
        created() {
            this.$http.get('/test').then( result =>
                result.json().then( data =>
                    this.test = data
                )
            )
        },
        methods: {
            send() {
                this.$http.post('/test', this.answer).then(console.log, console.log)
            }
        }
    }
</script>

<style>
</style>