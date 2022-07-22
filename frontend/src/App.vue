

<script>
import ItemModel from "@/components/ItemModel";
import LoginPage from "@/components/LoginPage";
import Custom404Page from "@/components/Custom404Page";
import RecipePage from "@/components/RecipeModel";

const routes = {
  '/': ItemModel,
  '/login': LoginPage,
  '/recipes' : RecipePage
}

export default {
  data() {
    return {
      currentPath: window.location.hash
    }
  },
  computed: {
    currentView() {
      return routes[this.currentPath.slice(1) || '/'] || Custom404Page
    }
  },
  mounted() {
    window.addEventListener('hashchange', () => {
      this.currentPath = window.location.hash
    })
  }
}



</script>

<template>



  <v-app
      :is="currentView"
  >
    <v-main>

    </v-main>
  </v-app>
</template>



<style>
[v-cloak] {
  display: none;
}
</style>