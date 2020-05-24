<template>
    <div>
        <h2>Статистика:</h2>
        <div>Всего пользователей зарегистрировано: {{stats.registeredUsersCount}}</div>
        <div>Начали тестирование: {{stats.testedUsersCount}}</div>
        <div>Закончили тестирование: {{stats.testFinishedUsersCount}}</div>
        <div>Ответили на все вопросы правильно: {{stats.allCorrectUsersCount}}</div>
    </div>
</template>

<script>
    export default {
        data() {
            return {
                stats: "",
            }
        },
        created() {
            this.$http.get('/statistics').then( result => {
                result.json().then( data =>
                    this.stats = data
                )
            }, console.log)
        }
    }
</script>

<style>
</style>