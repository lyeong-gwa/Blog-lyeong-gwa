<template>
  <v-container>
    <v-tabs v-model="tab">
      <v-tab>데이터 입력</v-tab>
      <v-tab>입력값 분석결과</v-tab>
      <v-tab>조합 찾기</v-tab>
    </v-tabs>
    <input-compo v-if="tab==0" @userCoreInfo="userCoreInfo"></input-compo>
    <result-compo v-else-if="tab==1" :request_image_list="request_image_list" 
    :core_list="core_list" :job="job" :job_kr="job_kr" :skill_name="skill_name" :core_level="core_level"></result-compo>
    <search-compo v-else-if="tab==2" :job="job" :job_kr="job_kr" :skill_name="skill_name"></search-compo>
  </v-container>
</template>

<script>
import InputCompo from "@/components/CogemUtilCompo/CogemUtilInput.vue";
import ResultCompo from "@/components/CogemUtilCompo/CogemUtilResult.vue";
import SearchCompo from "@/components/CogemUtilCompo/CogemUtilSearch.vue";
export default {
  name: "CogemUtil-component",
  components: {
    InputCompo,
    ResultCompo,
    SearchCompo
  },create() {
    this.tab=0;
  },
  data() {
    return {
      tab: null,
      core_list:[],
      request_image_list:[],
      job:"",
      job_kr:"",
      skill_name:[],
      core_level:[],
    };
  },
  methods:{
    userCoreInfo(data){
      this.core_list = data.core_list;
      this.request_image_list = data.request_image_list;
      this.job = data.job;
      this.job_kr = data.job_kr;
      this.skill_name = data.skill_name;
      this.tab = 1;
      this.core_level = new Array(data.skill_name.length);
      this.core_level.fill(0);
      
      const acc_exp =[ 0,1,3,4,6,8,
                       10,13,15,19,22,
                       26,30,34,39,44,
                       49,55,61,67,74,
                       80,88,95,103,111,9999];

      for(let i = 0 ; i < this.core_list.length ; i++){
        this.core_level[this.core_list[i].skill_data[0]] += acc_exp[this.core_list[i].level];
      }
      for(let i = 0 ; i < this.core_level.length ; i++){
        for(let j = 0; j < acc_exp.length-1;j++){
          if(acc_exp[j]<= this.core_level[i] && this.core_level[i]<acc_exp[j+1]){
            this.core_level[i] = j;
            break;
          }
        }

      }
    }
  }
};
</script>