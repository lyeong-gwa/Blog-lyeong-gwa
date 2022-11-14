<template>
  <v-container>
    <v-row>
      <v-col class="d-flex" sm="2">
        <v-select
          :items="main_category"
          v-model="main_select"
          label="직업 대분류"
          item-value="value"
          item-text="label"
          return-object
          solo
          @change="change_main"
        ></v-select>
      </v-col>
      <v-col class="d-flex" sm="2">
        <v-select
          :items="sub_category_list[main_select.value]"
          v-model="sub_select"
          label="직업 선택"
          item-value="value"
          item-text="label"
          return-object
          solo
        ></v-select>
      </v-col>

      <v-col class="d-flex" sm="6">
        <v-file-input
          v-model="files"
          accept="image/png, image/jpeg"
          color="deep-purple accent-4"
          label="코어창 이미지를 선택하시오(권장:15장)"
          multiple
          placeholder="Select your files"
          prepend-icon="mdi-paperclip"
          outlined
          @change="changepreviewimage"
          :show-size="1000"
          ref="file"
          dense
        >
          <template v-slot:selection="{ index, text }">
            <v-chip v-if="index < 2" color="primary" dark label small>
              {{ text }}
            </v-chip>

            <span v-else-if="index === 2" class="text-overline grey--text text--darken-3 mx-2">
              +{{ files.length - 2 }} File(s)
            </span>
          </template>
        </v-file-input>
      </v-col>
      <v-col class="d-flex" sm="2">
        <v-btn outlined rounded color="primary" height="55px" @click="searchImage">코어정보 확인하기</v-btn>
      </v-col>
    </v-row>

    <v-carousel id="preview">
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
  </v-container>
</template>

<script>
import axios from "axios";
export default {
  name: "CogemUtil-Input",
  data: () => ({
    main_category: require("@/data/CogemUtil/main_category.json"),
    sub_category_list: require("@/data/CogemUtil/sub_category.json"),
    main_select: { label: "모험가", value: "adventurer" },
    sub_select: { label: "히어로", value: "Hero" },
    files: [],
    previewimage: [require("@/assets/image/coregemstone.png")],
  }),
  methods: {
    changepreviewimage() {
      this.previewimage = [];
      let tmp = document.querySelector("input[type=file]").files;
      for (let i = 0; i < tmp.length; i++) {
        this.previewimage.push(URL.createObjectURL(tmp[i]));
      }
    },
    searchImage() {
      let formdata = new FormData();
      formdata.append("job", this.sub_select.value);
      formdata.append("job_kr", this.sub_select.label);
      this.files.forEach((item) => formdata.append("upfile", item));
      axios({
        method: "post",
        url: "http://127.0.0.1/coregem/get-core-list",
        data: formdata,
        headers: { "Content-Type": "multipart/form-data" },
      })
        .then((response) => {
          this.$emit("userCoreInfo", response.data);
        })
        .catch(function (error) {
          console.log(error);
        });
    },
    change_main(){
      this.sub_select = this.sub_category_list[this.main_select.value][0];
    }
  },
};
</script>
