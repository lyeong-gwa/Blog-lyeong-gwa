<template>
  <div>
    <h1>정보기입</h1>
    <v-row>
      <v-col cols="3">
        <v-select
          :items="select_core_use"
          v-model="core_use_count"
          hint="사용할 코어 개수"
          label="사용할 코어 개수"
          persistent-hint
          item-value="value"
          solo
        ></v-select>
      </v-col>
      <v-col cols="4">
        <v-select
          :items="select_subcore_use"
          v-model="sub_core_use"
          item-value="value"
          item-text="label"
          solo
        ></v-select>
      </v-col>
    </v-row>
    <hr />
    <h1>강화스킬 선택표</h1>
    <v-row dense>
      <v-col v-for="(skill, i) in getSkillName" :key="i" :cols="4">
        <skill-card
          :img_src="imgPath(i)"
          :info="getSkillName[i]"
          :idx="i"
          :selected="selected"
          @selectedChange="selectedChange"
        ></skill-card>
      </v-col>
    </v-row>
    <br />
    <hr />
    <h1 style="margin-bottom: 5px">
      선택 결과 (왼쪽에 있는 스킬부터 탐색합니다.) <v-btn class="primary" @click="startCalc">조합 검색시작</v-btn>
    </h1>
    <table>
      <tr>
        <th v-for="(skill, i) in selected" :key="i">
          <v-row>
            <v-col style="margin-right: 5px">
              <v-img :src="imgPath(skill)" height="72" width="58" />
            </v-col>
          </v-row>
        </th>
        
      </tr>
    </table>
    <br />
    <hr />
  </div>
</template>

<script>
import { createNamespacedHelpers } from "vuex";
const cogemutilHelper = createNamespacedHelpers("cogemutil");

import SkillCard from "@/components/CogemUtilCompo/Unit/SkillCard.vue";
export default {
  name: "CogemUtil-Search",
  components: {
    SkillCard,
  },
  created(){
    for(let i = 0 ; i < this.getSkillName.length;i++){
      this.map_core[i]=[];
    }
    for(let i = 0 ; i <this.getCoreList.length;i++){
      this.map_core[this.getCoreList[i].skill_data[0]].push(i);
    }
    console.log(this.map_core);
  },
  computed:{
    ...cogemutilHelper.mapGetters([
      "getJob",
      "getJobKr",
      "getSkillName",
      "getCoreList",
    ]),
    core_duplicate(){
      // let tmp3 = {}
      // let tmp2 = {}
      for(let i = 0 ; i < this.map_core.length; i++){
        console.log(this.map_core[i]);
      }

      return this.map_core;
    }
  },
  data() {
    return {
      selected: [],
      core_use_count: 2,
      select_core_use: [2, 3, 4, 5, 6, 7],
      select_subcore_use: [
        { label: "선택안한 스킬도 강화에 활용", value: "true" },
        { label: "선택한 스킬만 강화에 활용", value: "false" },
      ],
      sub_core_use: { label: "선택안한 스킬도 강화에 활용", value: "true" },

      map_core:{}

    };
  },
  methods: {
    imgPath(num) {
      return (
        "http://127.0.0.1/coregem/skill/" +
        this.getJob +
        "/" +
        this.getSkillName[num]
      );
    },
    selectedChange(data) {
      if (data.check) {
        this.selected.push(data.val);
      } else {
        this.selected = this.selected.filter((item) => item != data.val);
      }
    },
    skill_info(arr) {
      return this.skill_selected.filter((x) => arr.includes(x)).length;
    },

    startCalc(){
      console.log(this.core_duplicate3);
    }
  },
};
</script>

