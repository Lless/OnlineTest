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
            this.getQuestion()
        },
        methods: {
            send() {
                this.$http.post('/test', this.answer).then(
                this.getQuestion, console.log)
            },
            getQuestion() {
                this.$http.get('/test').then( result =>
                    result.json().then( data =>
                        this.test = data
                    )
                )
            }
        }
    }
</script>

<style>
</style>