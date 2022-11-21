<template>
  <v-container>
    <UploadData @changepreviewimage="changepreviewimage"/>
    <CarouselPreview :previewimage="previewimage" />
  </v-container>
</template>

<script>
import UploadData from "@/components/CogemUtilCompo/Unit/UploadData.vue"
import CarouselPreview from "@/components/CogemUtilCompo/Unit/CarouselPreview.vue"
export default {
  components:{
    UploadData,
    CarouselPreview,
  },
  data: () => ({
    previewimage: [],
  }),
  methods: {
    changepreviewimage(tmp) {
      this.previewimage = [];
      for (let i = 0; i < tmp.length; i++) {
        this.previewimage.push(URL.createObjectURL(tmp[i]));
      }
    },
  },
};
</script>
