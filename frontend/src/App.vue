<template>
  <v-app>
    <Drawer></Drawer>
    <AppBar></AppBar>
    <v-main>
      <br>
      <v-card
        class="mx-auto"
        max-width="1300"
      >
        <router-view />
      </v-card>
    </v-main>
    
  </v-app>
</template>

<script>
import AppBar from './components/core/AppBar.vue'
import Drawer from "./components/core/Drawer";

export default {
  name: 'App',

  components: {
    AppBar,
    Drawer,
  },
}
</script>
