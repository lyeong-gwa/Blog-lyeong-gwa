<template>
  <div>
    <h1>선택직업 :{{ getJobKr }}</h1>
    <v-carousel id="resultimage">
      <v-carousel-item
        v-for="(item, i) in getRequestImageList"
        :key="i"
        reverse-transition="fade-transition"
        transition="fade-transition"
      >
        <div>
          <v-img :src="item" contain max-height="450px"></v-img>
        </div>
      </v-carousel-item>
    </v-carousel>
    <v-row>
      <v-col cols="6">
        <div class="text-center">
          <h1>보유한 코어정보</h1>
        </div>
        <v-simple-table fixed-header height="1100px">
          <template v-slot:default>
            <thead>
              <tr>
                <th class="text-left">No.</th>
                <th class="text-center">메인</th>
                <th class="text-center">서브1</th>
                <th class="text-center">보조2</th>
                <th class="text-center">정보</th>
              </tr>
            </thead>
            <tbody>
              <tr v-for="core in getCoreList" :key="core.core_id">
                <td>{{ core.core_id }}</td>
                <td>
                  <v-img
                    :src="imgPath(core.skill_data[0])"
                    height="90"
                    width="72"
                  ></v-img>
                </td>
                <td>
                  <v-img
                    :src="imgPath(core.skill_data[1])"
                    height="90"
                    width="72"
                  ></v-img>
                </td>
                <td>
                  <v-img
                    :src="imgPath(core.skill_data[2])"
                    height="90"
                    width="72"
                  ></v-img>
                </td>

                <td v-if="skill_info(core.skill_data) == 1">
                  Lv.{{ core.level }}<br />1코어
                </td>
                <td
                  class="blue lighten-4"
                  v-else-if="skill_info(core.skill_data) == 2"
                >
                  Lv.{{ core.level }}<br />
                  2코어
                </td>
                <td class="red" v-else-if="skill_info(core.skill_data) == 3">
                  <span class="font-weight white--text"
                    >Lv.{{ core.level }}<br />3코어</span
                  >
                </td>
                <td v-else>Lv.{{ core.level }}</td>
              </tr>
            </tbody>
          </template>
        </v-simple-table>
      </v-col>
      <v-col cols="6">
        <div class="text-center">
          <h1>직업스킬 목록</h1>
        </div>
        <v-simple-table fixed-header height="1100px">
          <template v-slot:default>
            <thead>
              <tr>
                <th class="text-left" style="width: 5px">No.</th>
                <th style="width: 5px"></th>
                <th class="text-left">스킬명</th>
                <th class="text-left">Lv.(추정)</th>
                <th class="text-left">필요</th>
              </tr>
            </thead>
            <tbody>
              <tr v-for="(skill, i) in getSkillName" :key="i">
                <td>{{ i }}</td>

                <td>
                  <v-img :src="imgPath(i)" height="60" width="48"></v-img>
                </td>

                <td style="font-size: 0.7em">{{ skill }}</td>
                <td>{{ getCoreLeve[i] }}</td>
                <td>
                  <v-checkbox v-model="skill_selected" :value="i"></v-checkbox>
                </td>
              </tr>
            </tbody>
          </template>
        </v-simple-table>
      </v-col>
    </v-row>
  </div>
</template>

<script>
import { createNamespacedHelpers } from "vuex";
const cogemutilHelper = createNamespacedHelpers("cogemutil");
export default {
  name: "CogemUtil-Result",
  computed: {
    ...cogemutilHelper.mapGetters([
      "getCoreList",
      "getRequestImageList",
      "getJob",
      "getJobKr",
      "getSkillName",
      "getCoreLeve",
    ]),
  },
  data() {
    return {
      skill_selected: [],
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
    skill_info(arr) {
      return this.skill_selected.filter((x) => arr.includes(x)).length;
    },
  },
};
</script>


