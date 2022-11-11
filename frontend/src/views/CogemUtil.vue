<template>
  <v-container>
    <v-tabs v-model="tab">
      <v-tab>데이터 입력</v-tab>
      <v-tab>입력값 분석결과</v-tab>
      <v-tab>조합 찾기</v-tab>
    </v-tabs>
    <input-compo v-if="tab==0" @userCoreInfo="userCoreInfo"></input-compo>
    <result-compo v-if="tab==1" :request_image_list="request_image_list" :core_list="core_list"></result-compo>
  </v-container>
</template>

<script>
import InputCompo from "@/components/CogemUtilCompo/CogemUtilInput.vue";
import ResultCompo from "@/components/CogemUtilCompo/CogemUtilResult.vue";

export default {
  name: "CogemUtil-component",
  components: {
    InputCompo,
    ResultCompo
  },create() {
    this.tab=0;
  },
  data() {
    return {
      tab: null,
      core_list:[],
      request_image_list:[],
    };
  },
  methods:{
    userCoreInfo(data){
      this.core_list = data.core_list;
      this.request_image_list = data.request_image_list;
      this.tab = 1;
    }
  }
};
</script>