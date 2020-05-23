<template>
    <form>
        {{ test.question }}
        <br/>
        <div v-if="test.type=='ENTRY_QUESTION'">
            <input type="text" placeholder="answer" v-model="answer" />
            <input type="button" value="Save" @click="send" />
        </div>
        <div v-if="test.type=='CHOICE_QUESTION'">
            <div v-for="(option, index) in test.options" :key="option">
                <input type="radio" name="answer" v-model="answer" v-bind:value="index">
                    {{ option }}
                </input>
            </div>
            <input type="button" value="Save" @click="send" />
        </div>
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
                this.$http.post('/test', this.answer).then( res => {
                    this.answer="";
                    this.getQuestion()
                }
                , console.log)
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