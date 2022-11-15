<template>
  <v-carousel >
      <v-carousel-item
        v-for="(item, i) in previewimage"
        :key="i"
        reverse-transition="fade-transition"
        transition="fade-transition"
      >
        <div >
          <v-img :src="item" contain max-height="450px" ></v-img>
        </div>
      </v-carousel-item>
    </v-carousel>
</template>

<script>
export default {
    props:{
        previewimage:Array,
    }
}
</script>