Vue.component('test', {
    data: function() {
        return {
            test: null,
            answer: null
        }
    },
    template: '<form>' +
        '{{ test.question }} <br/>' +
        '<input type="text" placeholder="answer" v-model="answer" />' +
        '<input type="button" value="Save" @click="send" />' +
    '</form>',
    created: function() {
        this.$http.get('/test').then( result =>
            result.json().then( data =>
                this.test = data
            )
        )
    },
    methods: {
        send: function() {
            this.$http.post('/test', this.answer).then(console.log, console.log)
        }
    }
})