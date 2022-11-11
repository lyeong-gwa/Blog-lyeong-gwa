
<template>
  <v-app-bar app>
    <v-app-bar-nav-icon @click="changeDrawer"></v-app-bar-nav-icon>
    <v-toolbar-title>{{getPathName}}</v-toolbar-title>
  </v-app-bar>
</template>

<script>
import { createNamespacedHelpers } from "vuex";
const coreHelper = createNamespacedHelpers("core");

export default {
  name: "AppBar",
  computed: {
    ...coreHelper.mapGetters(['getPathName']),
  },
  methods: {
    ...coreHelper.mapActions(["changeDrawer"]),
  },
};
</script>
