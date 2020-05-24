<template>
    <div>
        <h2>Моя статистика:</h2>
        <div>Отвечено на {{stats.percentCorrectAnswers | toPercent}} заданных вопросов</div>
        <div>{{stats.percentUsersBetterThanUser | toPercent}} остальных пользователей, начавших тест, ответили на большее число вопросов</div>
        <div>{{stats.percentUsersWorseThanUser | toPercent}} остальных пользователей, начавших тест, ответили на меньшее число вопросов</div>
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
            this.$http.get('/myStatistics').then( result => {
                result.json().then( data =>
                    this.stats = data
                )
            }, console.log)
        },
        filters: {
            toPercent: function (value) {
                return Math.floor(+value * 100) + "%"
            }
        }
    }
</script>

<style>
</style>