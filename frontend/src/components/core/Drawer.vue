
<template>
  <v-navigation-drawer v-model="this.drawer">
    <v-list-item 
      link to="Home" 
      color="rgba(0, 0, 0, 0)"
      v-on:click="this.changePathName('Home')"
      >
      <v-img contain src="@/assets/logo.png"></v-img>
    </v-list-item>
    <v-divider></v-divider>

    <v-list nav>
      <v-list-item
        v-for="item in items"
        :key="item.title"
        link :to="item.link"
        v-on:click="this.changePathName(item.title)"
      >
        <v-list-item-title>
          <v-icon>{{ item.icon }}</v-icon>
          {{ item.title }}
        </v-list-item-title>
      </v-list-item>
    </v-list>
  </v-navigation-drawer>
</template>

<script>
import { createNamespacedHelpers } from "vuex";
const coreHelper = createNamespacedHelpers("core");

export default {
  name: "DrawerComponent",
  data: function () {
    return {
      items: require("@/data/core/Drawer.json"),
    };
  },
  computed: {
    ...coreHelper.mapState({
      drawer: (state) => state.drawer,
    }),
  },
  methods: {
    ...coreHelper.mapMutations([
      "changeDrawer",
      "changePathName"
  ]),
  },
};
</script>
