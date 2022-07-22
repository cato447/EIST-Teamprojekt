<template>
  <div class="main">

      <div v-if="loading">
        <h1 class="loading">Loading...</h1>
      </div>
      <div v-else>

        <!-- navbar -->

<!--        <header class="navbar-header">-->
<!--          <div class="logo">-->
<!--            <a>Home</a>-->
<!--          </div>-->

<!--          <input type="checkbox" class="menu-btn" id="menu-btn">-->
<!--          <label for="menu-btn" class="menu-icon">-->
<!--            <span class="menu-icon__line"></span>-->
<!--          </label>-->

<!--          <ul class="nav-links">-->
<!--            <li class="nav-link">-->
<!--              <a href="#">Profile</a>-->
<!--            </li>-->
<!--            <li class="nav-link">-->
<!--              <a href="#">Storage</a>-->
<!--            </li>-->
<!--            <li class="nav-link">-->
<!--              <a href="#">Recipes</a>-->
<!--            </li>-->
<!--            <li class="nav-link">-->
<!--              <a href="#">About</a>-->
<!--            </li>-->
<!--          </ul>-->
<!--        </header>-->
    </div>
  </div>

</template>


<script>
export default {
  name: "HomePage"
}
</script>

<style lang="scss">

@import url('https://fonts.googleapis.com/css2?family=Montserrat:wght@100;200&display=swap');
@import 'src/styling/navbar';

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

</style>