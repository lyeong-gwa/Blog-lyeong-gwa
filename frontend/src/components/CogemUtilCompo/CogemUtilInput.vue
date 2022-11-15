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
    // searchImage() {
    //   let formdata = new FormData();
    //   formdata.append("job", this.sub_select.value);
    //   formdata.append("job_kr", this.sub_select.label);
    //   this.files.forEach((item) => formdata.append("upfile", item));
    //   axios({
    //     method: "post",
    //     url: "http://127.0.0.1/coregem/get-core-list",
    //     data: formdata,
    //     headers: { "Content-Type": "multipart/form-data" },
    //   })
    //     .then((response) => {
    //       this.$emit("userCoreInfo", response.data);
    //     })
    //     .catch(function (error) {
    //       console.log(error);
    //     });
    // },
  },
};
</script>
