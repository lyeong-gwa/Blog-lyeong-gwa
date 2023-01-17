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
          <h1>보유한 코어정보(클릭시 수정가능)</h1>
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
              <tr v-for="core in getCoreList" :key="core.core_id" @click="selectCoreInfo(core.core_id)">
                <td>
                  {{ core.core_id }}
                </td>
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

                <td style="font-size: 0.7em">{{ removeExtension(skill) }}</td>
                <td>{{ getCoreLevel[i] }}</td>
                <td>
                  <v-checkbox v-model="skill_selected" :value="i"></v-checkbox>
                </td>
              </tr>
            </tbody>
          </template>
        </v-simple-table>
      </v-col>
    </v-row>
    <v-dialog v-model="change_dialog" max-width="290">
      <v-card>
        <v-card-title class="text-h5">
          No.{{ select_core }}코어 정보 수정
        </v-card-title>
        <v-card-text>
          <v-row>
            <v-col cols="3">
              <v-img
                :src="imgPath(select_core_info[0])"
                height="60"
                width="48"
              ></v-img>
            </v-col>
            <v-col cols="9">
              <v-text-field
                label="메인코어"
                v-model="select_core_info[0]"
              ></v-text-field>
            </v-col>
          </v-row>
          <v-row>
            <v-col cols="3">
              <v-img
                :src="imgPath(select_core_info[1])"
                height="60"
                width="48"
              ></v-img>
            </v-col>
            <v-col cols="9">
              <v-text-field
                label="서브코어:중간"
                v-model="select_core_info[1]"
              ></v-text-field>
            </v-col>
          </v-row>
          <v-row>
            <v-col cols="3">
              <v-img
                :src="imgPath(select_core_info[2])"
                height="60"
                width="48"
              ></v-img>
            </v-col>
            <v-col cols="9">
              <v-text-field
                label="서브코어:오른쪽"
                v-model="select_core_info[2]"
              ></v-text-field>
            </v-col>
          </v-row>
        </v-card-text>
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn color="green darken-1" text @click="tryChangeCoreInfo">
            변경하기
          </v-btn>
          <v-btn color="green darken-1" text @click="change_dialog = false">
            돌아가기
          </v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
  </div>
</template>

<script>
import domain from "@/util/domain.js";
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
      "getCoreLevel",
    ]),
  },
  data() {
    return {
      skill_selected: [],
      change_dialog: false,
      select_core: 0,
      select_core_info: [0, 0, 0],
    };
  },
  methods: {
    imgPath(num) {
      return (
        `${domain}coregem/skill/` + this.getJob + "/" + this.getSkillName[num]
      );
    },
    skill_info(arr) {
      return this.skill_selected.filter((x) => arr.includes(x)).length;
    },
    removeExtension(name) {
      return name.trim().replace(/(.png|.jpg|.jpeg|.gif)$/, "");
    },
    selectCoreInfo(select_id) {
      this.select_core = select_id;
      this.select_core_info = JSON.parse(
        JSON.stringify(this.getCoreList[select_id].skill_data)
      );
      this.change_dialog = true;
    },
    tryChangeCoreInfo() {
      let payload = { id: this.select_core, info: this.select_core_info };
      this.changeCoreInfo(payload);
    },
    ...cogemutilHelper.mapActions(["changeCoreInfo"]),
  },
};
</script>


