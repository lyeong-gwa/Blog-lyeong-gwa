<template>
  <v-container>
    <v-row dense>
      <v-col v-for="card in cards" :key="card.title" :cols="card.flex">
        <v-card>
          <router-link :to="card.link">
            <v-img
              :src="card.src"
              class="white--text align-end"
              gradient="to bottom, rgba(0,0,0,.1), rgba(0,0,0,.5)"
              height="300px"
            >
            </v-img>
          </router-link>
          <v-card-actions>
            <v-card-title v-text="card.title"></v-card-title>
            <v-spacer></v-spacer>
            <v-card-subtitle v-text="card.explain"></v-card-subtitle>
            <v-btn icon>
              <v-icon>mdi-bookmark</v-icon>
            </v-btn>
          </v-card-actions>
        </v-card>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
export default {
  data: () => ({
    cards: [
      {
        title: "코강유틸",
        explain: "코어강화 도우미 서비스",
        src: require("@/assets/image/coregemstone.png"),
        flex: 6,
        link: "CogemUtil",
      },
    ],
  }),
};
</script>
