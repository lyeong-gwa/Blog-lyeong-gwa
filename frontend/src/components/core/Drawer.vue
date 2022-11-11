
<template>
  <v-navigation-drawer v-model="drawer" app>
    <v-list-item 
      link to="Home" 
      color="rgba(0, 0, 0, 0)"
      
      >
      <v-img contain src="@/assets/logo.png" min-height="65"></v-img>
    </v-list-item>
    <v-divider></v-divider>
    <v-list nav>
      <v-list-item
        v-for="item in items"
        :key="item.title"
        link :to="item.link"
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
  data(){
    return {
      items: require("@/data/core/Drawer.json"),
      drawer: null,
    };
  },
  created(){
    this.drawer = this.getDrawer;
  },
  computed: {
    ...coreHelper.mapGetters(['getDrawer']),
  },
  watch:{
    getDrawer:function(val){
      this.drawer = val;
    }
  }
};
</script>
