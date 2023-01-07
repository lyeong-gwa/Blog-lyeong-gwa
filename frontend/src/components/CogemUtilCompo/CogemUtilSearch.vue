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
            ↓ 선택한 스킬만 일괄적용 ↓
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
            :disabled="wait_toggle"
            >조합검색 시작</v-btn
          >
        </v-col>
      </v-row>

      <v-row v-show="toggle_set_calc == false">
        <v-col
          v-for="(value, target_skill_idx) in getSkillName"
          :key="target_skill_idx"
          :cols="6"
        >
          <FilterCard
            :img_src="imgPath(target_skill_idx)"
            :info="removeExtension(getSkillName[target_skill_idx])"
            :idx="target_skill_idx"
            :limit="core_use_count"
            :all_range="all_range"
            :this_selected="selected.includes(target_skill_idx)"
            @changeFilter="changeFilter"
          ></FilterCard>
        </v-col>
        <v-col cols="12">
          <CombiTable :combi_list="this.result_combi_list"></CombiTable>
        </v-col>
      </v-row>
    </div>
  </div>
</template>

<script>
import http from "@/util/http.js";
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
      let target_core = this.getCoreList[i].skill_data;
      let target_list = this.map_core[this.getCoreList[i].skill_data[0]];

      let duplicate = false;
      for (let j = 0; j < target_list.length; j++) {
        let unit_core = this.getCoreList[target_list[j]].skill_data; //미리들어간 코어
        let cnt = 0;
        for (let k = 0; k < unit_core.length; k++) {
          if (target_core.includes(unit_core[k])) {
            cnt++;
          }
        }
        if (cnt == 3) {
          duplicate = true;
        }
      }

      if (!duplicate) {
        this.map_core[this.getCoreList[i].skill_data[0]].push(i);
      }
    }
    this.min_max_limit = {};
    for (let i = 0; i < this.getSkillName.length; i++) {
      this.min_max_limit[i] = [0, 2];
    }
    //console.log(this.getCoreList);
    //console.log(this.map_core);
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
      wait_toggle: false,
      selected: [], // 선택한 핵심코어 담은 리스트
      min_max_limit: [],
      core_use_count: 2, // 사용할 코어 개수값
      select_core_use: [2, 3, 4, 5, 6, 7, 8], // 사용할 코어개수 박스옵션
      select_subcore_use: [
        // 단기적으로 볼 것인가 장기적으로 볼 것인가
        { label: "잡코어는 사용하지 않고 선택한 코어만 강화", value: false },
        {
          label:
            "필요한 스킬이 스킬1,스킬2,스킬3 이지만 필요에 따라서 스킬4도 강화",
          value: true,
        },
      ],
      sub_core_use: {
        label: "잡코어는 사용하지 않고 선택한 코어만 강화",
        value: false,
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
      } else {
        let target_remove = this.selected.indexOf(data.val);
        this.selected.splice(target_remove, 1);
      }
    },
    //몇중첩인지 출력
    skill_info(arr) {
      return this.selected.filter((x) => arr.includes(x)).length;
    },
    removeExtension(name) {
      return name.trim().replace(/(.png|.jpg|.jpeg|.gif)$/, "");
    },

    async calcCombi() {
      this.wait_toggle = true;
      let sub_list = []; //target_core는 2중첩 코어 가지고있는 라벨들만, sub_list는 선택한 스킬 이외의 코어들에서 2중첩인 경우

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
      //console.log(this.selected);
      //console.log(sub_list);

      //우선 2중첩 위주로 선택, 선택한 스킬에서만 한정
      let filter_select_2core = this.selected.filter(
        (x) => this.target_core[x].length != 0
      );
      //선택 스킬에서 2중첩 코어들로만 구성하면 부족한 경우 -> 1개 있는 것도 사용한다.
      //단 앞에서부터 접근(우선순위)하고 코어가 있는 것만 집어 넣는다.
      if (filter_select_2core.length < this.core_use_count) {
        for (let i = 0; i < this.selected.length; i++) {
          if (
            this.map_core[this.selected[i]].length != 0 &&
            !filter_select_2core.includes(this.selected[i])
          ) {
            filter_select_2core.push(this.selected[i]);
          }
          if (filter_select_2core.length >= this.core_use_count) {
            break;
          }
        }
      }

      //만약 그럼에도 코어 수를 채우지 못한다면?
      //핵심코어가 아님에도 2중첩인 코어들 가져오기
      let final_2core_list = [];
      if (filter_select_2core.length < this.core_use_count) {
        for (let i in this.target_core) {
          if (
            this.target_core[i].length != 0 &&
            !filter_select_2core.includes(parseInt(i))
          ) {
            //2코어이면서 선택하지 않은 스킬일때
            final_2core_list.push(parseInt(i));
          }
        }
        final_2core_list = this.makeCombi(
          final_2core_list,
          this.core_use_count - filter_select_2core.length
        );
      }
      let real_core_use_count = Math.min(
        filter_select_2core.length,
        this.core_use_count
      );
      let make_combi_list = this.makeCombi(
        filter_select_2core,
        real_core_use_count
      );

      //final_2core의 길이가 0이 아니라면 make_combi_list는 1개의 array로만 구성
      //console.log(make_combi_list, this.sub_core_use.value);
      if (final_2core_list.length != 0 && this.sub_core_use.value) {
        let tmp_make_combi_list = [];
        for (let i in make_combi_list) {
          for (let j in final_2core_list) {
            tmp_make_combi_list.push([
              ...make_combi_list[i],
              ...final_2core_list[j],
            ]);
          }
        }
        make_combi_list = tmp_make_combi_list;
        real_core_use_count += final_2core_list[0].length;
      }

      let able_min = 0;
      let able_max = 0;
      for (let i = 0; i < this.getSkillName.length; i++) {
        able_min += this.min_max_limit[i][0];
        able_max += this.min_max_limit[i][1];
      }
      if (
        !(
          able_min <= real_core_use_count * 3 &&
          real_core_use_count * 3 <= able_max
        )
      ) {
        alert(
          "가능하지 않은 필터 중첩범위입니다.\n최소중첩들 합 <= 조합에 쓰는 코어 수 * 3 <= 최대중첩들 합\n사용하고자 하는 코어 수: " +
            real_core_use_count +
            "\n사용하고자 하는 코어 수는 소지한 코어가 부족하면 기입한 것에 비해 줄어들 수 있습니다."
        );
        return;
      }
      const wait = (timeToDelay) => new Promise((resolve) => setTimeout(resolve, timeToDelay));
      //tree만들기 : [1,2,3] -> { 1:{2:3:{}}} 형태로
      //[1,2,3] [1,2,4] 두개가 있을 때 { 1 : { 2: { 3 : {}, 4 : {} } } } DFS처리하기 용이함
      this.result_combi_list = [];

      console.log(`조합 수:${make_combi_list.length}`);
      for (let i = 0; i < make_combi_list.length; i++) {
        let tree = this.makeCoreList(make_combi_list[i]);
        await wait(10);
        console.log(`${i+1}번째 조합 연산시작`);
        this.searchTree(tree, make_combi_list[i]);
        if (this.result_combi_list.length > 100) {
          break;
        }
      }

      let formdata = new FormData();
      formdata.append("job", this.getJob);
      formdata.append("core_num", this.core_use_count);
      formdata.append("select_skill", JSON.stringify(this.selected));
      formdata.append("combi_list_len", this.result_combi_list.length);
      this.wait_toggle = false;
      http({
        method: "post",
        url: "/coregem/data_get",
        data: formdata,
      })
        .then(() => {})
        .catch(function (error) {
          alert(`error:문제가 발생하였습니다. 잠시 후 다시 이용해주세요.
        ${error}`);
        });
      alert("계산이 완료되었습니다.");
    },
    async searchTree(tree, combi_list) {
      const wait = (timeToDelay) => new Promise((resolve) => setTimeout(resolve, timeToDelay));
      let limit = {};
      for (let i = 0; i < this.getSkillName.length; i++) {
        limit[i] = 0;
      }
      for (let i = 0; i < combi_list.length; i++) {
        limit[combi_list[i]] += 1;
      }

      let arr = [];
      let state_list = [];
      state_list.push(JSON.parse(JSON.stringify(tree[0])));
      while (state_list.length != 0) {
        if(arr.length==2){
          await wait(0);
        }
        if (
          state_list[state_list.length - 1].length == 0 ||
          arr.length == tree.length
        ) {
          //이전 가지로 돌아가야 할 경우 (leaf 혹은 가지 전체 확인완료)
          if (arr.length == tree.length) {
            //leaf일 때
            this.result_combi_list.push({
              data: JSON.parse(JSON.stringify(arr)),
              limit: JSON.parse(JSON.stringify(limit)),
            });
            if (this.result_combi_list.length > 100) {
              break;
            }
          } else {
            state_list.pop();
          }
          let remove_limit_cnt = arr.pop();
          if (remove_limit_cnt != undefined) {
            let target_core = this.getCoreList[remove_limit_cnt];
            for (let i = 1; i < target_core.skill_data.length; i++) {
              let target_skill =
                this.getCoreList[remove_limit_cnt].skill_data[i]; //key로 지정된 코어의 데이터 메인->중간->오른쪽 순으로
              limit[target_skill] -= 1;
            }
          }
        } else {
          //다음가지로 뻗어나가야 할 때
          let insert_core_idx = state_list[state_list.length - 1].pop();
          arr.push(insert_core_idx);
          let target_core = this.getCoreList[insert_core_idx];
          //추가한 코어에 대한 중첩계산
          for (let i = 1; i < target_core.skill_data.length; i++) {
            let target_skill = this.getCoreList[insert_core_idx].skill_data[i]; //key로 지정된 코어의 데이터 메인->중간->오른쪽 순으로
            limit[target_skill] += 1;
          }

          let check_able = true;
          //중첩제한 유효성 검사
          for (let i = 0; i < this.getSkillName.length; i++) {
            if (
              this.min_max_limit[i][0] >
                limit[i] + (tree.length - arr.length) ||
              limit[i] > this.min_max_limit[i][1]
            ) {
              check_able = false;
              break;
            }
          }
          if (check_able) {
            if (arr.length != tree.length) {
              state_list.push(
                JSON.parse(JSON.stringify(tree[state_list.length]))
              );
            }
          } else {
            arr.pop();
            for (let i = 1; i < target_core.skill_data.length; i++) {
              let target_skill =
                this.getCoreList[insert_core_idx].skill_data[i];
              limit[target_skill] -= 1;
            }
          }
        }
      }
    },
    changeFilter(data) {
      this.min_max_limit[data.target] = data.range;
      this.min_max_limit = JSON.parse(JSON.stringify(this.min_max_limit));
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
      // [1,2,3]스킬을 선택 -> [1번스킬의 코어들][2번스킬의 코어들][3번스킬의 코어들]들로 구성하는 조합
      let tmp_list = [];
      for (let i = 0; i < arr.length; i++) {
        let check_core = this.target_core[arr[i]];
        if (check_core.length == 0) {
          check_core = this.map_core[arr[i]];
        }
        tmp_list.push(check_core);
      }
      return tmp_list;
    },
  },
};
</script>