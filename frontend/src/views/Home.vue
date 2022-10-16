<template>
  <v-container fluid>
    <v-row dense>
      <v-col v-for="card in cards" :key="card.title" :cols="card.flex">
        <v-card>
          <a :href="card.link">
            <v-img
              :src="card.src"
              class="white--text align-end"
              gradient="to bottom, rgba(0,0,0,.1), rgba(0,0,0,.5)"
              height="300px"
            >
            </v-img>
          </a>
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
  name: "Home-component",
  data: () => ({
    cards: require("@/data/core/Home.json"),
  }),
};
</script>
