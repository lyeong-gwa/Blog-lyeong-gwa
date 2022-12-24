<template>
  <v-container>
    <div v-if="getUploadWait" class="loading-container">
      <div class="loading">
        <PulseLoader />
      </div>
    </div>

    <v-tabs v-model="tab">
      <v-tab>데이터 입력</v-tab>
      <v-tab>입력값 분석결과</v-tab>
      <v-tab>조합 찾기</v-tab>
      <a href="https://lyeong-gwa.tistory.com/231" target='_blank' style="position:absolute; right:0;">사용법 설명링크</a>
    </v-tabs>
    <InputCompo v-show="tab == 0" />
    <result-compo v-show="tab==1"></result-compo>
    <search-compo v-if="tab==2"></search-compo>
  </v-container>
</template>

<script>
import PulseLoader from "vue-spinner/src/PulseLoader.vue";
import { createNamespacedHelpers } from "vuex";
const cogemutilHelper = createNamespacedHelpers("cogemutil");

import InputCompo from "@/components/CogemUtilCompo/CogemUtilInput.vue";
import ResultCompo from "@/components/CogemUtilCompo/CogemUtilResult.vue";
import SearchCompo from "@/components/CogemUtilCompo/CogemUtilSearch.vue";
export default {
  components: {
    InputCompo,
    ResultCompo,
    SearchCompo,
    PulseLoader,
  },
  create() {
    this.tab = 0;
  },
  computed: {
    ...cogemutilHelper.mapGetters(["getUploadWait"]),
  },
  data() {
    return {
      tab: null,
    };
  },
  watch: {
    getUploadWait(wait) {
      if (!wait) {
        this.tab = 1;
      }
    },
  },
};
</script>
