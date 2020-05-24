<template>
    <div>
        <div v-for="result in results">
            {{result.question.question}}
            <div v-if="result.question.type=='ENTRY_QUESTION'">
                Правильный ответ: {{result.question.answer}}
                <br/>
                Мой ответ: {{result.myAnswer}}
            </div>

            <div v-if="result.question.type=='CHOICE_QUESTION'">
                <div v-for="option in result.question.options" :key="option">
                    <input type="radio" name="answer" disabled>
                    {{ option }}
                    </input>
                </div>
                Правильный ответ: {{+result.question.answer + 1}}
                <br/>
                Мой ответ: {{+result.myAnswer + 1}}
            </div>

            <br/>
            <br/>
        </div>
    </div>
</template>

<script>
export default {
    data() {
        return {
            results: ""
        }
    },
    created() {
        this.$http.get("/answers").then( result =>
                    result.json().then( data =>
                        this.results=data
                    ),console.log)
    },
}
</script>

<style>
</style>