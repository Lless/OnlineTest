<template>
    <div>
        <h2>Тест</h2>
        <div v-if="finished">Прохождение теста завершено</div>
        <form v-else>
            {{ test.question }}
            <br/>
            <div v-if="test.type=='ENTRY_QUESTION'">
                <input type="text" placeholder="Ответ" v-model="answer" />
                <input type="button" value="Отправить" @click="send" />
            </div>
            <div v-if="test.type=='CHOICE_QUESTION'">
                <div v-for="(option, index) in test.options" :key="option">
                    <input type="radio" name="answer" v-model="answer" v-bind:value="index">
                        {{ option }}
                    </input>
                </div>
                <input type="button" value="Отправить" @click="send" />
            </div>
        </form>
    </div>
</template>

<script>
    export default {
        data() {
            return {
                test: "",
                answer: "",
                finished: false
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
                this.$http.get('/test').then( result => {
                    console.log(result)
                    if (!result.body) this.finished = true;
                        else result.json().then( data =>
                            this.test = data
                        )
                    }
                )
            }
        }
    }
</script>

<style>
</style>