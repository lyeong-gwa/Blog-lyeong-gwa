<template>
  <div>
    <v-carousel id="resultimage">
      <v-carousel-item
        v-for="(item, i) in request_image_list"
        :key="i"
        :src="item"
        reverse-transition="fade-transition"
        transition="fade-transition"
      ></v-carousel-item>
    </v-carousel>
  </div>
</template>

<script>
export default {
    name: "CogemUtil-Result",
    props:{
        request_image_list:Array,
    },
    data(){
        return{

        }
    },
};
</script>

<style>
</style>