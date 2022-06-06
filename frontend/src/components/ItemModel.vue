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
  font-size: 1.25rem;
  background-color: darkcyan;
}

/* cursor styling */

.cursor {
  width: 1rem;
  height: 1rem;
  border: 0.15rem solid black;
  border-radius: 100%;
  position: absolute;
}

/* navbar styling */

.navbar-header {
  position: fixed;
  display: flex;
  justify-content: space-between;
  align-items: center;
  top: 2.5rem;
  left: 0;
  width: 100vw;
  padding: 0 5vw;
  color: black;
  z-index: 1;

  a {
    text-decoration: none;
    color: inherit;
    text-transform: uppercase;
    font-size: 2rem;
  }

  .nav-links {
    display: flex;
    list-style: none;

    a {
      margin: 0.2rem;
      padding: 1rem 0.5rem;
      transition: all 300ms;
    }

    a:hover {
      font-size: 3.5rem;
      transition: all 300ms;
    }
  }
}

.menu-icon {
  position: relative;
  padding: 26px 10px;
  cursor: pointer;
  z-index: 1;
  display: none;

  &__line {
    display: block;
    position: relative;
    background: black;
    height: 2.5px;
    width: 3rem;
    border-radius: 4px;

    &::before, &::after {
      content: '';
      position: absolute;
      height: 100%;
      width: 100%;
      border-radius: 4px;
      background: black;
      transition: background .8s ease;
    }

    &::before {
      transform: translateY(-10px);
    }

    &::after {
      transform: translateY(10px);
    }
  }
}

.menu-btn {
  display: none;
}


.logo:hover {
  cursor: default;
}

@media screen {

  .navbar-header {

    .menu-icon {
      display: block;
      font-weight: bold;

      &__line {
        animation: closedButton 1s backwards;
        animation-direction: reverse;

        &::before {
          animation: closedButtonBefore 1s backwards;
          animation-direction: reverse;
        }

        &::after {
          animation: closedButtonAfter 1s backwards;
          animation-direction: reverse;
        }
      }
    }

    .nav-links {
      position: absolute;
      top: -2.5rem;
      left: 0;
      opacity: 0;
      flex-direction: column;
      justify-content: space-evenly;
      align-items: center;
      padding: 10rem 0;
      width: 100vw;
      height: 100vh;
      font-size: 2rem;
      font-weight: bolder;
      letter-spacing: 0.25rem;
      color: white;
      background: #272727;

      transition: opacity 0.8s 0.5s,
      clip-path 1s 0.5s;
      clip-path: circle(200px at top right);

      .nav-links {
        opacity: 0;
        transform: translateX(100%);
        width: 100%;
        text-align: center;

        a {
          display: block;
          padding: 2rem 0;
        }
      }
    }

    .menu-btn:checked ~ .nav-links {
      opacity: 1;
      clip-path: circle(100% at center);

      .nav-link {
        opacity: 1;
        transform: translateX(0);
      }
    }

    .menu-btn:checked ~ .menu-icon {

      .menu-icon__line {
        background: white;
        animation: openButton 1s forwards;

        &::before {
          background: white;
          animation: openButtonBefore 1s forwards;
        }

        &::after {
          background: white;
          animation: openButtonAfter 1s forwards;
        }
      }
    }
  }
}

@keyframes openButtonBefore {
  0% {
    transform: translateY(-10px) rotate(0deg);
  }
  50% {
    transform: translateY(0px) rotate(0deg);
  }
  100% {
    transform: translateY(0px) rotate(90deg);
  }
}

@keyframes openButton {
  50% {
    transform: rotate(0deg);
  }
  100% {
    transform: rotate(45deg);
  }
}

@keyframes openButtonAfter {
  0% {
    transform: translateY(10px) rotate(0deg);
  }
  50% {
    transform: translateY(0px) rotate(0deg);
  }
  100% {
    transform: translateY(0px) rotate(90deg);
  }
}

@keyframes closedButtonBefore {
  0% {
    transform: translateY(-10px) rotate(0deg);
  }
  50% {
    transform: translateY(0px) rotate(0deg);
  }
  100% {
    transform: translateY(0px) rotate(90deg);
  }
}

@keyframes closedButton {
  50% {
    transform: rotate(0deg);
  }
  100% {
    transform: rotate(45deg);
  }
}

@keyframes closedButtonAfter {
  0% {
    transform: translateY(10px) rotate(0deg);
  }
  50% {
    transform: translateY(0px) rotate(0deg);
  }
  100% {
    transform: translateY(0px) rotate(90deg);
  }
}

/* input field styling */

.inputField-header {
  position: relative;
  width: 20rem;
}

.newItemName {
  position: absolute;
  top: -75rem;
  left: 0;
  width: 100%;
  height: 100%;
  border: 0.15rem solid white;
  border-radius: 0.2rem;
  font-family: inherit;
  font-size: inherit;
  color: black;
  outline: none;
  padding: 1.5rem;
  background: none;
  box-shadow: 10px 10px 30px rgba(0, 0, 0, 0.4);
}


.newItemName:hover {
  border-color: black;
}

.newItemName:focus {
  border-color: black;
}

.formLabel {
  position: absolute;
  left: 1rem;
  top: -74.5rem;
  padding: 0.5rem;
  color: white;
  cursor: text;
  transition: top 200ms ease-in, left 200ms ease-in, font-size 200ms ease-in;
  background-color: darkcyan;
}

.newItemName:hover ~ .formLabel, .newItemName:not(:placeholder-shown).newItemName:not(:hover) ~ .formLabel {
  top: -76.25rem;
  left: 0.25rem;
  font-size: 0.00001rem;
  color: black;
  // set context here
}

.newItemName:focus ~ .formLabel, .newItemName:not(:placeholder-shown).newItemName:not(:focus) ~ .formLabel {
  top: -76.25rem;
  left: 0.25rem;
  font-size: 0.00001rem;
  color: black;
  // set context here
}

/* item section */

</style>