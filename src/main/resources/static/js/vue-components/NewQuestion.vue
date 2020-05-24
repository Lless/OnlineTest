<template>
    <div>
        <label for="choiceCheckbox">Выбор вариантов ответа:</label>
        <input type="checkbox" value="Выбор вариантов ответа" v-model="choiceQuestion" id="choiceCheckbox">
        <br/>
        <form v-if="!choiceQuestion" @submit.prevent="addEntryQuestion">
            <h3>Добавить вопрос без вариантов ответа</h3>
            <label for="EntryQuestion">Вопрос:</label>
            <input required v-model="question" placeholder="Вопрос" id="EntryQuestion"/>
            <br/>
            <label for="answer">Ответ:</label>
            <input required v-model="answer" placeholder="Ответ" Id="answer"/>
            <br/>
            <br/>
            <button type="submit">Добавить</button>
        </form>
        <form v-else @submit.prevent="addChoiceQuestion">
            <h3>Добавить вопрос с выбором вариантов ответа</h3>
            <input required v-model="question" placeholder="Вопрос" id="choiceQuestion"/>
            <br/>
            <div v-for="(option, index) in options" :key="index">
                <input required type="radio" name="answer" v-bind:value="index" v-model="answer"/>
                <input required type="text" :placeholder="'Вариант ' + (index + 1)" v-model="options[index]">
            </div>
            <br/>
            <button type="submit">Добавить</button>
        </form>
    </div>
</template>

<script>
    export default {
        data() {
            return {
                question: "",
                answer: "",
                options: ["","","",""],
                choiceQuestion: false
            }
        },
        methods: {
            addEntryQuestion(){
                this.$http.post('/addEntryQuestion', {question: this.question, answer: this.answer})
                    .then(res => {
                        if (res.ok) {
                            this.question="";
                            this.answer="";
                        }
                    } , console.log)
            },
            addChoiceQuestion(){
                this.$http.post('/addChoiceQuestion',
                            {question: this.question, answer: this.answer, options: this.options})
                    .then(res => {
                        if (res.ok) {
                            this.question="";
                            this.answer="";
                            this.options=["","","",""];
                        }
                    } , console.log)
            },
            register: function () {
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