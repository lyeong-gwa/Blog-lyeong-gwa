<template>
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

          <span
            v-else-if="index === 2"
            class="text-overline grey--text text--darken-3 mx-2"
          >
            +{{ files.length - 2 }} File(s)
          </span>
        </template>
      </v-file-input>
    </v-col>
    <v-col class="d-flex" sm="2">
      <v-btn
        outlined
        rounded
        color="primary"
        height="55px"
        @click="searchImage(requestForm)"
        :disabled="getUploadWait"
        >코어정보 확인하기</v-btn
      >
    </v-col>

  </v-row>
</template>

<script>
import { createNamespacedHelpers } from "vuex";
const cogemutilHelper = createNamespacedHelpers("cogemutil");
export default {
  data: () => ({
    main_category: require("@/data/CogemUtil/main_category.json"),
    sub_category_list: require("@/data/CogemUtil/sub_category.json"),
    main_select: { label: "모험가", value: "adventurer" },
    sub_select: { label: "히어로", value: "Hero" },
    files: [],
  }),
  methods: {
    //대분류에 따른 세부분류 변환
    change_main() {
      this.sub_select = this.sub_category_list[this.main_select.value][0];
    },
    //미리보기 이미지 전환
    changepreviewimage() {
      this.$emit("changepreviewimage", this.files);
    },
    //서버요청, 이미지, 직업 데이터에 따른 요청
    ...cogemutilHelper.mapActions(["searchImage"]),
  },
  computed: {
    requestForm() {
      return {sub_select:this.sub_select,files:this.files};
    },
    ...cogemutilHelper.mapGetters(["getUploadWait"]),
  },
};
</script>

<style>
</style>