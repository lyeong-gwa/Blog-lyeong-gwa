
<template>
  <v-app-bar app>
    <v-app-bar-nav-icon @click="changeDrawer"></v-app-bar-nav-icon>
  </v-app-bar>
</template>

<script>
import { createNamespacedHelpers } from "vuex";
const coreHelper = createNamespacedHelpers("core");

export default {
  name: "AppBar",
  methods: {
    ...coreHelper.mapActions(["changeDrawer"]),
  },
};
</script>
