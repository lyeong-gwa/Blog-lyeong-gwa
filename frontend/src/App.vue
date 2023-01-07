<template>
  <v-app id="app">
    <Drawer></Drawer>
    <AppBar></AppBar>
    <v-main>
      <v-card class="mx-auto" max-width="1300" style="margin-top: 30px">
        <router-view />
      </v-card>
    </v-main>
    <v-footer dark padless>
      <v-card class="flex" flat tile>
        <v-card-text class="py-2 white--text text-center">
          {{ new Date().getFullYear() }} —
          <strong>
            © www.coregemstone.com All Rights Reserved. www.coregemstone.com is
            not associated with NEXON Korea.</strong
          >
          <v-spacer></v-spacer>
          <v-btn v-for="item in icons" :key="item" class="mx-4" dark icon :href="item.link">
            <v-icon size="24px">
              {{ item.icon }}
            </v-icon>
          </v-btn>
        </v-card-text>
      </v-card>
    </v-footer>
  </v-app>
</template>

<script>
import AppBar from "./components/Core/AppBar";
import Drawer from "./components/Core/Drawer";

export default {
  name: "App",

  components: {
    AppBar,
    Drawer,
  },
  data: () => ({
    icons: [{icon:"mdi-github",link:"https://github.com/lyeong-gwa"}],
  }),
};
</script>
