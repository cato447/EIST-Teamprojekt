import axios from 'axios'

const SERVER_URL = 'http://localhost:9000'

const instance = axios.create({
    baseURL : SERVER_URL,
    timeout: 0
})

export default {
    createNew: (name, quantity, unit) => instance.post("/api/v1/items", {name: name, quantity : quantity, unit : unit}),

    getAll: () => instance.get('/api/v1/items', {  
        transformResponse: [function (data) {  
          return data? JSON.parse(data)._embedded.items : data;  
        }]  
      }),

    getRecipesForFridge: () => instance.get('/api/v1/recipe/forFridge', {
        transformResponse: [function (data) {
            return data? JSON.parse(data) : data;
        }]
    }),

    getRandom: () => instance.get('/api/v1/recipe/random', {
        transformResponse: [function (data) {
            return data? JSON.parse(data) : data;
        }]
    }),

    removeForId: (id) => instance.delete('/api/v1/items/'+ id)
}