var testApi = Vue.resource('/test')
var app = new Vue({
    el: '#app',
    data: {
        test: 'Привет, Vue!'
    },
    template: '<div><h1>Hello, world!</h1><div>{{ test.question }}</div></div>',
    created: function() {
        testApi.get().then( result =>
            result.json().then( data =>
                this.test = data
            )
        )
    }
})