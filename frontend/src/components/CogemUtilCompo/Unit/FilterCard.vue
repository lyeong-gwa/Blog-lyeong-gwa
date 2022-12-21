<template>
  <v-card
    :class="{ 'mx-auto': true, 'blue lighten-4': this_selected }"
    max-height="80"
    outlined
  >
 
    <v-list-item three-line>
      <v-list-item-avatar tile height="50" width="32">
        <v-img :src="img_src" height="50" width="32"></v-img>
      </v-list-item-avatar>

      <v-list-item-content right>
        <v-list-item-title style="font-size: 1.2em">
          <v-row>
            <v-col cols="6" style="margin-top: 15px">
              {{ info }}
            </v-col>
            <v-col cols="6">
              <v-range-slider
                v-model="range"
                :max="limit"
                min="0"
                hide-details
                class="align-center"
              >
                <template v-slot:prepend>
                  <p style="margin-top: 15px">{{ range[0] }}</p>
                </template>
                <template v-slot:append>
                  <p style="margin-top: 15px">{{ range[1] }}</p>
                </template>
              </v-range-slider>
            </v-col>
          </v-row>
        </v-list-item-title>
      </v-list-item-content>
    </v-list-item>
  </v-card>
</template>

<script>
export default {
  props: {
    img_src: String,
    info: String,
    idx: Number,
    limit: Number,
    all_range: Array,
    this_selected: Boolean,
  },
  computed: {
  },
  data() {
    return {
      range: [0, 7],
    };
  },
  watch: {
    limit: function (new_data) {
      this.$set(this.range, 1, new_data);
    },
    range: function (new_range) {
      this.$emit("changeFilter", { target: this.idx, range: new_range });
    },
    all_range: function (new_data) {
      if(this.this_selected){
        this.range = new_data;
      }
      
    },
  },
};
</script>

