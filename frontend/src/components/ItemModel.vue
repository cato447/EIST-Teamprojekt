<template>
  <body>
  <div class="main">
    <h1 class="email">{{ userEmail }}</h1>
    <section class="itemapp">
      <div v-if="loading">
        <h1 class="loading">Loading...</h1>
      </div>
      <div v-else>

        <!-- navbar -->

        <div class="navbar-header">
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
              <router-link to="/">Home</router-link>
            </li>
            <li class="nav-link">
              <router-link to="/profile">Profile</router-link>
            </li>
            <li class="nav-link">
              <router-link to="/recipes">Recipes</router-link>
            </li>
            <li class="nav-link">
              <router-link to="/shoppinglist">Shoppinglist</router-link>
            </li>
          </ul>
        </div>

        <!-- input field -->

        <div class="inputField-header">
          <input class="newItemName" id="inputTextField" autofocus autocomplete="off" placeholder=" " v-model="newItem"
                 @keyup.enter="addItem"/>
          <label for="inputTextField" class="formLabel">
            Add here ...
          </label>
        </div>

        <!-- response element -->

        <div class="item-section" v-show="items.length" v-cloak>
          <ul class="item-list">
            <li v-for="item in items"
                class="item"
                :key="item.id">
              <div class="view">
                <label class="item-name" @dblclick="editItem(item)">
                  <span class="item-name-fame">{{ item.name.toUpperCase() }} </span>
                  <span class="item-information-frame">{{ item.quantity }} {{ item.unit.toLowerCase() }}</span>
                </label>
                <button class="destroy" @click="removeItem(item)"></button>
              </div>
            </li>
          </ul>
        </div>
      </div>
    </section>

  </div>
  </body>
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

body{
  margin: 0;
  padding: 0;
  background-color:darkcyan;
}

.main {
  position: relative;
  font-family: 'Montserrat', sans-serif;
  height: 100vh;
  display: grid;
  justify-content: center;
  align-items: center;
  font-size: 1.25vh;
  background-color: darkcyan;
}

/* input field styling */

.inputField-header {
  z-index: 3;
  position: fixed;
  width: 20vh;
  left: 50%;
  transform: translateX(-50%);
}

.newItemName {
  z-index: 3;
  position: relative;
  top: -65.5vh; // 0,65 of height
  left: 0;
  width: 100%;
  height: 100%;
  border: 0.07352941176470588vh solid white; // 0.10 of font size // u cannot use var here
  border-radius: 0.4411764705882353vh; // times 2 of border
  font-family: inherit;
  font-size: inherit;
  color: black;
  outline: none;
  padding: 0.5vh;// size of font
  box-shadow: 10px 10px 30px rgba(0, 0, 0, 0.4); // 0.5 size of font and 1.5 size of font
  background: darkcyan;
}

.newItemName:hover {
  border-color: black;
}

.newItemName:focus {
  border-color: black;
}

.formLabel {
  z-index: 3;
  position: relative;
  top: -67.75vh;
  left: 0.75vh;
  padding: 0.1vh;
  font-size: 0.1vh;
  color: white;
  cursor: text;
  transition: top 200ms ease-in, left 200ms ease-in, font-size 200ms ease-in;
  background-color: darkcyan;
}

.newItemName:hover ~ .formLabel, .newItemName:not(:placeholder-shown).newItemName:not(:hover) ~ .formLabel {
  top: -69.30vh;
  left: 0.15vw;
  font-size: 0.1vh;
  color: black;
}

.newItemName:focus ~ .formLabel, .newItemName:not(:placeholder-shown).newItemName:not(:focus) ~ .formLabel {
  top: -69.30vh;
  left: 0.15vw;
  font-size: 0.1vh;
  color: black;
}

/* Workaround for below WQHD resolution */

@media screen and (max-height: 1400px) {
  .formLabel{
    opacity: 0;
  }
}

/* item section */

.item-section{
  z-index: 2;
  position: absolute;
  font-size: 20px;
  top: 80%;
  left: 50%;
  margin-left: 50px;
}

.item-list{
  z-index: 2;
  align-self: center;
  position: relative;
  display: grid;

  top: -60vh;
  left: -44.33vw;
  row-gap: 75px;
  column-gap: 340px;

  .item{
    z-index: 2;
    text-align: center;
    position: relative;
    margin-top: 0;
    font-family: Montserrat, sans-serif;
    list-style: none;
    padding: 100px;
    width: 300px;
    background: darkslategrey;
    box-shadow: 10px 10px 30px rgba(0, 0, 0, 0.6);
    display: flex;
  }

  .item-name{
    z-index: 2;
    position: relative;
    left: -20px;
  }

  .item-name-fame{
    z-index: 2;
    letter-spacing: 4px;
    font-size: 30px;
  }

  .item-name-fame:after{
    content:'';
    display:block;
    border-bottom:2px solid #000;
    top: 16px;
    height:0;
    width:250px;
    position:relative;
  }

  .item-information-frame{
    letter-spacing: 2px;
    position: relative;
    top: 32px;
  }

  .view{
    position: relative;
    top: -60px;
    left: -2.5vh;
    color: white;
  }

  .destroy{
    display: block;
    position: relative;
    height: 50px;
    width: 2px;
    top: 140px;
    left: 0;
    border-radius: 1px;
    border: solid black 1px;
    transform: rotate(45deg);
  }

  .destroy:after{
    content: '';
    height: 50px;
    width: 2px;
    border-radius: 1px;
    border: solid black 1px;
    position: fixed;
    top: -2px;
    transform: rotate(-90deg);
  }

  .destroy:hover{
    cursor: pointer;
  }
}

@media (max-width: 2299px) {
  .item-list{
    grid-template-columns: repeat(4, 2vh);
  }
}

@media (min-width: 2100px) {
  .item-list{
    grid-template-columns: repeat(5, 2vh);
  }
}

@media (min-width: 2560px) {
  .item-list{
    grid-template-columns: repeat(6, 2vh);
  }
}


@media (min-width: 3800px) {
  .item-list{
    grid-template-columns: repeat(9, 2vh);
    border-collapse: separate;
    border-spacing: 0 15px;
  }
}



</style>