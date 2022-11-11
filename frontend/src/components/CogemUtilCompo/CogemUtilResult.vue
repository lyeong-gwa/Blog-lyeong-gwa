<template>
  <div>
    <v-carousel id="resultimage">
      <v-carousel-item
        v-for="(item, i) in request_image_list"
        :key="i"
        reverse-transition="fade-transition"
        transition="fade-transition"
      >
      <div >
          <v-img :src="item" contain max-height="450px"></v-img>
        </div>
    </v-carousel-item>
    </v-carousel>
    <v-row>
      <v-col>
        <v-simple-table dense fixed-header height="300px">
      <template v-slot:default>
        <thead>
          <tr>
            <th class="text-left">id</th>
            <th class="text-left">1</th>
            <th class="text-left">2</th>
            <th class="text-left">3</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="core in core_list" :key="core.core_id">
            <td>{{ core.core_id }}</td>
            <td>{{ core.skill_data[0] }}</td>
            <td>{{ core.skill_data[1] }}</td>
            <td>{{ core.skill_data[2] }}</td>
          </tr>
        </tbody>
      </template>
    </v-simple-table>
      </v-col>
      <v-col>
        
      </v-col>
    </v-row>
  </div>
</template>

<script>
export default {
  name: "CogemUtil-Result",
  props: {
    request_image_list: Array,
    core_list: Array,
  },
};
</script>

<style></style>
