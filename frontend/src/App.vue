<template>
  <v-app id="app">
    <Drawer></Drawer>
    <AppBar></AppBar>
    <v-main>
      <v-card class="mx-auto" max-width="1000" style="margin-top: 30px">
        <router-view />
      </v-card>
      
    </v-main>
  </v-app>
</template>

<script>
import AppBar from "./components/core/AppBar.vue";
import Drawer from "./components/core/Drawer";

export default {
  name: "App",

  components: {
    AppBar,
    Drawer,
  },
};
</script>
