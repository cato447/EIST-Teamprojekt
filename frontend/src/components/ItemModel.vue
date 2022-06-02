<template>
  <div>
    <h1 class="title">Items</h1>
    <h1 class="email">{{userEmail}}</h1>
    <section class="itemapp">
      <div v-if="loading">
        <h1 class="loading">Loading...</h1>
      </div>
      <div v-else>
        <header class="header">
          <input class="newItemName"
                autofocus autocomplete="off"
                 :placeholder="this.inputPlaceholder"
                 v-model="newItem"
                 @keyup.enter="addItem"/>
        </header>
        <section class="main" v-show="items.length" v-cloak>
          <ul class="item-list">
            <li v-for="item in items"
                class="item"
                :key="item.id">
                <div class="view">
                  <label @dblclick="editItem(item)">{{ item.name }} {{ item.quantity }}{{ item.unit }}</label>
                  <button class="destroy" @click="removeItem(item)">❌</button>
                </div>
            </li>
          </ul>
        </section>
      </div>
    </section>
  </div>
</template>

<script>
  import api from '../Api'; 
  const Items = {
    name: 'Items',
    props: {
      activeUser: Object
    },
    
    // app initial state
    data: function() {
      return {
        items: [],
        newItem: '',
        editedItem: null,
        loading: true,
        error: null,
        id: 0
      }
    },

    mounted() {
      api.getAll()
          .then(response => {
            this.$log.debug("Data loaded: ", response.data)
            this.items = response.data
      })
        .catch(error => {
          this.$log.debug(error)
          this.error = "Failed to load items"
      })
        .finally(() => this.loading = false)
    },

    computed: {
      userEmail: function () {
        return this.activeUser ? this.activeUser.email : ''
      },
      inputPlaceholder: function () {
        return this.activeUser ? this.activeUser.given_name + ', what do you want to add?' : 'What needs to be added'
      }
    },

    methods: {
      addItem: function () {
        var value = this.newItem && this.newItem.trim()
        if (!value) {
          return
        }
        
        var components = value.split(' ')

        api.createNew(components[0],
                      parseInt(components[1].replace ( /[^\d.]/g, '' )),
                      components[1].replace(/[0-9]/g, '') === 'ml' ? 'MILLILETERS' : "GRAMMS"
          ).then( (response) => {  
          this.$log.debug("New item created:", response);  
          this.items.push({  
              id: response.data.id,  
              name: components[0],
              quantity: parseInt(components[1].replace ( /[^\d.]/g, '' )),
              unit: components[1].replace(/[0-9]/g, '') === 'MILLILETERS' ? 'ml' : 'g'
          })  
          }).catch((error) => {  
            this.$log.debug(error);  
          this.error = "Failed to add item"  
        });

        this.newItem = ''
      },

      removeItem: function (item) { // notice NOT using "=>" syntax
        api.removeForId(item.id).then(() => {
          this.$log.debug("Item removed:", item);
          this.items.splice(this.items.indexOf(item), 1)
        }).catch((error) => {
          this.$log.debug(error);
          this.error = "Failed to remove item"
        })
      }
    },

    directives: {
      'item-focus': function (el, binding) {
        if (binding.value) {
          el.focus()
        }
      }
    }

 }
export default Items
</script>