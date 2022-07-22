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
            <a>Recipes</a>
          </div>

          <input type="checkbox" class="menu-btn" id="menu-btn">
          <label for="menu-btn" class="menu-icon">
            <span class="menu-icon__line"></span>
          </label>

          <ul class="nav-links">
            <li class="nav-link">
              <a href="/#/login">Sign up</a>
            </li>
            <li class="nav-link">
              <a href="/#/">Storage</a>
            </li>
          </ul>
        </div>



        <!-- input field -->

        <div>

          <v-container
              fluid
              dark
              style="background-color: transparent; height: 100%"
          >
            <v-row
                align="align">
              <v-col
                  v-for="recipe in recipes"
                  :key="recipe.id"
                  cols="4"
                  sm="3"
              >
                <v-item>
                  <v-card
                      class="pa-2"
                      min-width="350"
                      max-width="350"
                      min-height="400"
                      max-height="400"
                      tile
                      rounded = true
                      color="#385F73"
                      dark
                  >
                    <v-img
                        class="white--text align-end"
                        height="200px"
                        :src="recipe.image"
                    >
                    </v-img>

                    <v-card-title
                        style="word-break: break-word"
                    >{{ recipe.title }}</v-card-title>
                    <v-card-subtitle class="pb-0">
                      Ready in {{ recipe.readyInMinutes }} minutes
                    </v-card-subtitle>

                    <!--                      <v-card-text class="text&#45;&#45;primary">-->
                    <!--                        <div>Whitehaven Beach</div>-->

                    <!--                        <div>Whitsunday Island, Whitsunday Islands</div>-->
                    <!--                      </v-card-text>-->

                    <v-card-actions>
                      <!--                        <v-btn-->
                      <!--                            color="orange"-->
                      <!--                            text-->
                      <!--                        >-->
                      <!--                          Share-->
                      <!--                        </v-btn>-->

                      <v-btn

                          :href="recipe.sourceUrl"
                          text
                      >
                        <!--                            @click = ""-->
                        Cook
                      </v-btn>
                    </v-card-actions>
                  </v-card>
                </v-item>
              </v-col>
            </v-row>
          </v-container>

        </div>

      </div>
    </section>

  </div>
  </body>
</template>

<script>

import api from '../Api';

const Recipes = {
  name: 'Recipes',
  props: {
    activeUser: Object
  },

  // app initial state
  data: function () {
    return {
      items: [],
      recipes: [],
      loading: true,
      error: null,
      id: 0
    }
  },

  mounted() {
    this.populateRecipes()
  },

  methods: {
    populateFromFridge() {
      api.getRecipesForFridge()
          .then(response => {
            this.$log.debug("Data loaded: ", response.data)
            this.recipes = response.data
          })
          .catch(error => {
            this.$log.debug(error)
            this.error = "Failed to load recipes"
          })
          .finally(() => this.loading = false)
    },

    populateRandom() {
      api.getRandom()
          .then(response => {
            this.$log.debug("Data loaded: ", response.data)
            this.recipes = response.data
          })
          .catch(error => {
            this.$log.debug(error)
            this.error = "Failed to load recipes"
          })
          .finally(() => this.loading = false)
    },

    populateRecipes() {
      api.getAll()
          .then(response => {
            this.$log.debug("Data loaded: ", response.data)
            this.items = response.data
          })
          .catch(error => {
            this.$log.debug(error)
            this.error = "Failed to load items"
          })
          .finally(() => {
            if (this.items.length > 3) {
              this.populateFromFridge()
            } else {
              this.populateRandom()
            }
          })
    }
  }

}
export default Recipes
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
  background-color: #006064;
}

.main {
  position: relative;
  font-family: 'Montserrat', sans-serif;
  height: 100vh;
  display: grid;
  justify-content: center;
  align-items: center;
  font-size: 1.25vh;
  background-color: #006064;
}

/* navbar-background */

.nav-background{
  position: fixed;
  left: 0;
  top: 0;
  z-index: 3;
  background: #006064;
  width: 100vw;
  height: 15vh;
}

/* input field styling */

//.field-header-box{
//  z-index: 3;
//  position: center;
//  width: 200vw;
//  height: 5vh;
//  left: 0;
//  top: 10vh;
//  display: grid;
//  align-content: center;
//  justify-content: center;
//}

.inputField-header {
  position: relative;
  z-index: 5;
  width: 20vh;
  height: 2vh;
}

.newItemName {
  z-index: 3;
  position: relative;
  left: 0;
  width: 100%;
  height: 100%;
  border: 0.07352941176470588vh solid white; // 0.10 of font size // u cannot use var here
  border-radius: 0.4411764705882353vh; // times 2 of border
  font-family: inherit;
  font-size: inherit;
  color: white;
  outline: none;
  padding: 0.5vh;// size of font
  box-shadow: 10px 10px 30px rgba(0, 0, 0, 0.4); // 0.5 size of font and 1.5 size of font
  background: #006064;
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

//@media screen and (max-height: 1400px) {
//  .formLabel{
//    opacity: 0;
//  }
//}

/* item section */

.item-section{
  z-index: 2;
  position: absolute;
  top: 80%;
  left: 50%;
  width: 0;
  height: 0;
  font-size: 20px;
  margin-left: 30px;
  border: 1px solid black;
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
    background: darkslategrey;
    display: flex;
    padding: 100px;
    width: 300px;
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
    height:0;
    position:relative;

    top: 16px;
    width:250px;
  }

  .item-information-frame{
    position: relative;

    letter-spacing: 2px;
    top: 32px;
  }

  .view{
    position: relative;
    color: white;

    top: -60px;
    left: -52px;
  }

  .destroy{
    display: block;
    position: relative;
    left: 0;
    border-radius: 1px;
    border: solid black 1px;
    transform: rotate(45deg);

    height: 50px;
    width: 2px;
    top: 140px;
  }

  .destroy:after{
    content: '';
    border-radius: 1px;
    border: solid black 1px;
    position: fixed;
    transform: rotate(-90deg);

    height: 50px;
    width: 2px;
    top: -2px;
  }

  .destroy:hover{
    cursor: pointer;
  }
}

@media (max-width: 2100px) {
  .item-list{
    grid-template-columns: repeat(7, 2vh);
  }

  .item{
    right: 4.1vw;
  }
}

@media (min-width: 2100px) {
  .item-list{
    grid-template-columns: repeat(8, 2vh);
  }
}

@media (min-width: 2560px) {
  .item-list{
    grid-template-columns: repeat(8, 2vh);
  }
}


@media (min-width: 3800px) {
  .item-list{
    grid-template-columns: repeat(9, 2vh);
    border-collapse: separate;
    border-spacing: 0 15px;
  }
}

/* responsive */

@media (max-height: 1440px) and (min-width: 720px) {

  .item-section{

    font-size: 40/3 px;
    margin-left: 80px;
  }

  .item-list{
    z-index: 2;
    align-self: center;
    position: relative;
    display: grid;
    top: -60vh;
    left: -44.33vw;

    row-gap: 50px;
    column-gap: 230px;

    .item{
      width: 200px;
      height: 200px;
    }

    .item-name{
      left: -30px;
    }

    .item-name-fame{
      position: relative;
      letter-spacing: 4px;
      font-size: 12px;
      top: -20px;
    }

    .item-name-fame:after{
      content:'';
      display:block;
      border-bottom:2px solid #000;
      height:0;
      position:relative;

      top: 8px;
      width:170px;
    }

    .item-information-frame{

      font-size: 8px;

      letter-spacing: 1px;
      top: -5px;
    }

    .view{
      position: relative;
      color: white;

      top: -50px;
      left: -52px;
    }

    .destroy{
      display: block;
      position: relative;
      left: -10px;
      border-radius: 1px;
      border: solid black 1px;
      transform: rotate(45deg);

      height: 35px;
      width: 2px;
      top: 50px;
    }

    .destroy:after{
      content: '';
      border-radius: 1px;
      border: solid black 1px;
      position: fixed;
      transform: rotate(-90deg);

      height: 35px;
      width: 2px;
      top: -2px;
    }

    .destroy:hover{
      cursor: pointer;
    }
  }
}

</style>