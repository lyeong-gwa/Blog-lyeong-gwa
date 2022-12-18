<template>
  <div style="margin-bottom: 100px">
    <div v-show="toggle_set_calc == true">
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
        <v-col cols="9">
          <v-select
            :items="select_subcore_use"
            v-model="sub_core_use"
            item-value="value"
            item-text="label"
            solo
            return-object
          ></v-select>
        </v-col>
      </v-row>

      <hr />
      <h1>강화스킬 선택표</h1>
      <v-row dense>
        <v-col v-for="(skill, i) in getSkillName" :key="i" :cols="4">
          <skill-card
            :img_src="imgPath(i)"
            :info="removeExtension(getSkillName[i])"
            :idx="i"
            :selected="selected"
            @selectedChange="selectedChange"
          ></skill-card>
        </v-col>
      </v-row>

      <br />
      <hr />

      <h1 style="margin-bottom: 5px">
        선택 결과 (왼쪽에 있는 스킬부터 탐색합니다.)
        <v-btn class="primary" @click="nextCombiSearch">조합 찾아보기</v-btn>
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

    <div v-show="toggle_set_calc == false">
      <h1 style="margin-top: 5px">선택한 정보</h1>
      <h3 style="margin-bottom: 5px">사용할 코어 칸수:{{ core_use_count }}</h3>
      <h3 style="margin-bottom: 5px">
        사용할 코어 옵선:{{ sub_core_use.label }}
      </h3>
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
      <v-btn class="error" @click="toggle_set_calc = true"
        >선택한 정보 수정하기
      </v-btn>

      <br />
      <hr />
      <v-row>
        <v-col cols="6">
          <h1 style="position: relative; top: 30%">필터창 (중첩 범위지정)</h1>
        </v-col>
        <v-col cols="4">
          <div style="position: relative; right: 0px; top: 30%">
            <v-range-slider
              v-model="all_range"
              :max="core_use_count"
              min="0"
              hide-details
              class="align-center"
            >
              <template v-slot:prepend>
                <p style="margin-top: 15px">{{ all_range[0] }}</p>
              </template>
              <template v-slot:append>
                <p style="margin-top: 15px">{{ all_range[1] }}</p>
              </template>
            </v-range-slider>
          </div>
        </v-col>
        <v-col>
          <v-btn
            class="primary"
            style="position: relative; top: 50%"
            @click="calcCombi"
            >조합검색 시작</v-btn
          >
        </v-col>
      </v-row>

      <v-row v-show="toggle_set_calc == false">
        <v-col
          v-for="target_skill_idx in selected"
          :key="target_skill_idx"
          :cols="6"
        >
          <FilterCard
            :img_src="imgPath(target_skill_idx)"
            :info="removeExtension(getSkillName[target_skill_idx])"
            :idx="target_skill_idx"
            :limit="core_use_count"
            :all_range="all_range"
            @changeFilter="changeFilter"
          ></FilterCard>
        </v-col>
      </v-row>
    </div>
    <CombiTable :combi_list="this.result_combi_list"></CombiTable>
  </div>
</template>

<script>
import domain from "@/util/domain.js";
import { createNamespacedHelpers } from "vuex";
const cogemutilHelper = createNamespacedHelpers("cogemutil");

import SkillCard from "@/components/CogemUtilCompo/Unit/SkillCard.vue";
import FilterCard from "@/components/CogemUtilCompo/Unit/FilterCard.vue";
import CombiTable from "@/components/CogemUtilCompo/Unit/CombiTable.vue";
export default {
  name: "CogemUtil-Search",
  components: {
    SkillCard,
    FilterCard,
    CombiTable,
  },
  created() {
    for (let i = 0; i < this.getSkillName.length; i++) {
      this.map_core[i] = [];
    }
    for (let i = 0; i < this.getCoreList.length; i++) {
      this.map_core[this.getCoreList[i].skill_data[0]].push(i);
    }
    console.log(this.getCoreList);
    console.log(this.map_core);
    
  },
  computed: {
    ...cogemutilHelper.mapGetters([
      "getJob",
      "getJobKr",
      "getSkillName",
      "getCoreList",
    ]),
  },
  data() {
    return {
      selected: [], // 선택한 핵심코어 담은 리스트
      min_max_limit: [],
      core_use_count: 2, // 사용할 코어 개수값
      select_core_use: [2, 3, 4, 5, 6, 7], // 사용할 코어개수 박스옵션
      select_subcore_use: [
        // 단기적으로 볼 것인가 장기적으로 볼 것인가
        { label: "잡코어는 사용하지 않고 선택한 코어만 강화", value: "false" },
        {
          label:
            "필요한 스킬이 스킬1,스킬2,스킬3 이지만 필요에 따라서 스킬4도 강화",
          value: "true",
        },
      ],
      sub_core_use: {
        label: "잡코어는 사용하지 않고 선택한 코어만 강화",
        value: "false",
      },

      map_core: {}, //유저 소지코어 hashmap형태로 보관
      target_core: {}, //계산에 사용할 코어
      toggle_set_calc: true,
      all_range: [0, 7],
      result_combi_list: [],
    };
  },
  methods: {
    //서버에게 이미지 요청 링크
    imgPath(num) {
      return (
        `${domain}coregem/skill/` + this.getJob + "/" + this.getSkillName[num]
      );
    },
    //조합찾아보기 버튼으로 넘어갈 때 호출
    nextCombiSearch() {
      this.toggle_set_calc = false;
      this.target_core = {};
      for (let i = 0; i < this.getSkillName.length; i++) {
        this.target_core[i] = [];
      }

      console.log("가지고 있는 코어정보", this.map_core);
      for (let i in this.map_core) {
        for (let j = 0; j < this.map_core[i].length; j++) {
          let target_skill = this.getCoreList[this.map_core[i][j]].skill_data;

          if (this.skill_info(target_skill) >= 2) {
            this.target_core[i].push(this.map_core[i][j]);
          }
        }
      }
      console.log("2중첩 이상 코어만 활용", this.target_core);
    },
    //SkillCard emit 선택, 해제함수
    selectedChange(data) {
      if (data.check) {
        this.selected.push(data.val);

        this.min_max_limit.push([0, this.core_use_count]);
      } else {
        let target_remove = this.selected.indexOf(data.val);
        this.selected.splice(target_remove, 1);
        this.min_max_limit.splice(target_remove, 1);
      }
    },
    //몇중첩인지 출력
    skill_info(arr) {
      return this.selected.filter((x) => arr.includes(x)).length;
    },
    removeExtension(name) {
      return name.trim().replace(/(.png|.jpg|.jpeg|.gif)$/, "");
    },

    calcCombi() {
      let sub_list = [];

      if (this.selected.length < this.core_use_count) {
        for (let i in this.target_core) {
          if (
            !this.selected.includes(parseInt(i)) &&
            this.target_core[i].length > 0
          ) {
            sub_list.push(parseInt(i));
          }
        }
      }
      // console.log(this.selected);
      // console.log(sub_list);
      let filter_select_2core = this.selected.filter(
        (x) => this.target_core[x].length != 0
      );

      let make_combi_list = this.makeCombi(
        filter_select_2core,
        Math.min(filter_select_2core.length, this.core_use_count)
      );
      console.log("실질적으로 사용할 2중첩 이상 선택코어", filter_select_2core);
      console.log("코어 선택 경우의 수", make_combi_list.length);
      let tree = {};

      for (let i = 0; i < make_combi_list.length; i++) {
        let last_list = this.makeCoreList(make_combi_list[i]);
        for (let j = 0; j < last_list.length; j++) {
          let node = tree;
          for (let k = 0; k < last_list[j].length; k++) {
            if (node[last_list[j][k]] == undefined) {
              node[last_list[j][k]] = {};
            }
            node = node[last_list[j][k]];
          }
        }
      }
      //console.log(tree);
      this.result_combi_list = [];
      this.searchTree(tree, JSON.parse(JSON.stringify(this.min_max_limit)), []); //깊은 복사 필요
      //console.log(this.result_combi_list.length);
    },
    searchTree(tree, limit, arr) {
      if(this.result_combi_list.length>10000000){
        return;
      }
      if (Object.keys(tree).length == 0) {
        this.result_combi_list.push({data:arr});
      } else {
        for (let key in tree) {
          for (let i = 0; i < this.getCoreList[key].skill_data.length; i++) {
            let target_skill = this.getCoreList[key].skill_data[i]; //key로 지정된 코어의 데이터 메인->중간->오른쪽 순으로
            let target_min_max_idx = this.selected.indexOf(target_skill); //min_max의 몇번째 배열이 해당 스킬의 제한인가
            let target = limit[target_min_max_idx];
            if (target != undefined) {
              target[1] -= 1;
            }
          }

          //Vue는 배열값 변경에 제약이 있으므로 re-rendering
          limit = JSON.parse(JSON.stringify(limit));

          let check = true;
          for (let i = 0; i < this.getCoreList[key].skill_data.length; i++) {
            let target_skill = this.getCoreList[key].skill_data[i]; //key로 지정된 코어의 데이터 메인->중간->오른쪽 순으로
            let target_min_max_idx = this.selected.indexOf(target_skill); //min_max의 몇번째 배열이 해당 스킬의 제한인가
            let target = limit[target_min_max_idx];
            if (target != undefined && target[0] > target[1]) {
              check = false;
              break;
            }
          }
          if (check) {
            this.searchTree(tree[key], JSON.parse(JSON.stringify(limit)), [
              ...arr,
              key,
            ]);
          }
          for (let i = 0; i < this.getCoreList[key].skill_data.length; i++) {
            let target_skill = this.getCoreList[key].skill_data[i]; //key로 지정된 코어의 데이터 메인->중간->오른쪽 순으로
            let target_min_max_idx = this.selected.indexOf(target_skill); //min_max의 몇번째 배열이 해당 스킬의 제한인가
            let target = limit[target_min_max_idx];
            if (target != undefined) {
              target[1] += 1;
            }
          }
        }
      }
    },
    changeFilter(data) {
      let tmp_change_min_max = this.selected.indexOf(data.target);
      this.$set(this.min_max_limit, tmp_change_min_max, data.range);
    },
    makeCombi(arr, selectNumber) {
      const results = [];
      if (selectNumber === 1) return arr.map((el) => [el]);

      arr.forEach((fixed, index, arr) => {
        const new_arr = arr.slice(index + 1);
        const combination = this.makeCombi(new_arr, selectNumber - 1);

        const attached = combination.map((el) => [fixed, ...el]);
        results.push(...attached);
      });
      return results;
    },
    makeCoreList(arr) {
      let tmp_list = [];
      for (let i = 0; i < arr.length; i++) {
        if (tmp_list.length == 0) {
          //아무것도 없는 상태 아래 구현 모두 날라감
          tmp_list = this.target_core[arr[i]].map((el) => [el]);
        } else if (this.target_core[arr[i]].length != 0) {
          let tmp = [];
          for (let j = 0; j < tmp_list.length; j++) {
            for (let k = 0; k < this.target_core[arr[i]].length; k++) {
              tmp.push([...tmp_list[j], this.target_core[arr[i]][k]]);
            }
          }
          tmp_list = tmp;
        }
      }
      return tmp_list;
    },
  },
};
</script>

