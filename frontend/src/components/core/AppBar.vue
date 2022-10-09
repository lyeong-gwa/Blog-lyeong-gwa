
<template>
  <v-app-bar app>
    <v-app-bar-nav-icon @click="this.changeDrawer"></v-app-bar-nav-icon>
    <v-toolbar-title>Application</v-toolbar-title>
  </v-app-bar>
</template>

<script>
import { createNamespacedHelpers } from "vuex";
const coreHelper = createNamespacedHelpers("core");

export default {
  name: "AppBar",
  methods: {
    ...coreHelper.mapMutations(["changeDrawer"]),
  },
};
</script>
