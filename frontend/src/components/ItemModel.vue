<template>
  <div class="main">
    <h1 class="email">{{ userEmail }}</h1>
    <section class="itemapp">
      <div v-if="loading">
        <h1 class="loading">Loading...</h1>
      </div>
      <div v-else>

        <!-- navbar -->

        <header class="navbar-header">
          <div class="logo">
            <a>Storage</a>
          </div>

          <span class="divider"></span>

          <input type="checkbox" class="menu-btn" id="menu-btn">
          <label for="menu-btn" class="menu-icon">
            <span class="menu-icon__line"></span>
          </label>

          <ul class="nav-links">
            <li class="nav-link">
              <a href="#">Home</a>
            </li>
            <li class="nav-link">
              <a href="#">Profile</a>
            </li>
            <li class="nav-link">
              <a href="#">Recipes</a>
            </li>
            <li class="nav-link">
              <a href="#">Placeholder</a>
            </li>
          </ul>
        </header>

        <!-- input field -->

        <header class="inputField-header">
          <input class="newItemName" id="inputTextField" autofocus autocomplete="off" placeholder=" " v-model="newItem"
                 @keyup.enter="addItem"/>
          <label for="inputTextField" class="formLabel">
            Add here ...
          </label>
        </header>

        <!-- response element -->

        <section class="item-section" v-show="items.length" v-cloak>
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
  data: function () {
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
          parseInt(components[1].replace(/[^\d.]/g, '')),
          components[1].replace(/[0-9]/g, '') === 'ml' ? 'MILLILETERS' : "GRAMMS"
      ).then((response) => {
        this.$log.debug("New item created:", response);
        this.items.push({
          id: response.data.id,
          name: components[0],
          quantity: parseInt(components[1].replace(/[^\d.]/g, '')),
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

<style lang="scss">

@import url('https://fonts.googleapis.com/css2?family=Montserrat:wght@100;200&display=swap');
@import 'src/styling/navbar';

:root{
  --globalFontSize: 20px; // 1/108
  --globalFontSizeHalf: var(--globalFontSize) * 0.5;
  --globalFontSizeOneAndHalf: var(--globalFontSize) * 1.5;

  --globalFontSizeTenth: var(--globalFontSize) * 0.1;
  --globalFontSizeFifth: var(--globalFontSize) * 0.2;
  --globalFontSizeFourFiths: var(--globalFontSize) * 0.8;
}

*,
*::before,
*::after {
  box-sizing: border-box;
  margin: 0;
  padding: 0;
}

.main {
  font-family: 'Montserrat', sans-serif;
  height: 100vh;
  display: grid;
  justify-content: center;
  align-items: center;
  font-size: 1vh;
  background-color: darkcyan;
}

/* input field styling */

.inputField-header {
  position: relative;
  width: 20vh;
}

.newItemName {
  position: absolute;
  top: -67.5vh; // 0,65 of height
  left: 0;
  width: 100%;
  height: 100%;
  border: 2px solid white; // 0.10 of font size // u cannot use var here
  border-radius: var(--globalFontSizeFifth); // times 2 of border
  font-family: inherit;
  font-size: inherit;
  color: black;
  outline: none;
  padding: 1.3vh;// size of font
  background: none;
  box-shadow: 10px 10px 30px rgba(0, 0, 0, 0.4); // 0.5 size of font and 1.5 size of font
}

.newItemName:hover {
  border-color: black;
}

.newItemName:focus {
  border-color: black;
}

.formLabel {
  position: absolute;
  left: 8px; // dont change this
  top: -67vh; // 0.647peroid2
  padding: 8px;
  color: white;
  cursor: text;
  transition: top 200ms ease-in, left 200ms ease-in, font-size 200ms ease-in;
  background-color: darkcyan;
}

.newItemName:hover ~ .formLabel, .newItemName:not(:placeholder-shown).newItemName:not(:hover) ~ .formLabel {
  top: -68.65vh;
  left: 0.25vw;
  font-size: 0.00001rem;
  color: black;
}

.newItemName:focus ~ .formLabel, .newItemName:not(:placeholder-shown).newItemName:not(:focus) ~ .formLabel {
  top: -68.65vh;
  left: 0.25vw;
  font-size: 0.00001rem;
  color: black;
}

/* item section */

</style>