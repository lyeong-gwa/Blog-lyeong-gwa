<template>
  <v-data-table
    :headers="headers"
    :items="core_list"
    sort-by="calories"
    class="elevation-1"
    :footer-props="{
      'items-per-page-text': '페이지 당 보여줄 조합',
      'items-per-page-options': [5, 10, 15, 20],
      pageText: '총 {2}개 항목 중 {0}-{1}',
    }"
  >
    <template v-slot:top>
      <v-toolbar flat>
        <v-toolbar-title><h1>찾은 조합</h1></v-toolbar-title>
        <v-divider class="mx-4" inset vertical></v-divider>
        <v-spacer></v-spacer>

        <v-dialog v-model="dialog_item" max-width="1000px">
          <v-card>
            <v-card-text>
              <v-container>
                <div style="padding-bottom: 20px">
                  선택해야 할 코어 :
                  <a v-for="(val, idx) in selectItem.data" :key="idx">
                    {{ val }},
                  </a>
                </div>
                <v-row>
                  <v-col v-for="(val, idx) in selectItem.limit" :key="idx" cols="3">
                    <v-card class="mx-auto" outlined>
                      <v-list-item three-line>
                        <v-list-item-avatar tile height="50" width="32">
                          <v-img
                            :src="fix_imgPath(idx)"
                            height="50"
                            width="32"
                          ></v-img>
                        </v-list-item-avatar>
                        <v-list-item-content right>
                          <v-list-item-title style="font-size: 1.2em">
                            중첩 수 : {{val}}<br>
                            예상레벨: {{selectLevel[idx]}}
                          </v-list-item-title>
                        </v-list-item-content>
                      </v-list-item>
                    </v-card>
                  </v-col>
                </v-row>
              </v-container>
            </v-card-text>

            <v-card-actions>
              <v-spacer></v-spacer>
              <v-btn color="blue darken-1" text @click="close"> Cancel </v-btn>
            </v-card-actions>
          </v-card>
        </v-dialog>
      </v-toolbar>
    </template>

    <template v-slot:[`item.data[0]`]="{ item }">
      No.{{ item.data[0] }}
      <table>
        <tr>
          <th>
            <v-img
              :src="imgPath({ num: item.data[0], idx: 0 })"
              height="36"
              width="29"
            />
          </th>

          <th>
            <v-img
              :src="imgPath({ num: item.data[0], idx: 1 })"
              height="36"
              width="29"
            />
          </th>

          <th>
            <v-img
              :src="imgPath({ num: item.data[0], idx: 2 })"
              height="36"
              width="29"
            />
          </th>
        </tr>
      </table>
    </template>
    <template v-slot:[`item.data[1]`]="{ item }">
      No.{{ item.data[1] }}
      <table>
        <tr>
          <th>
            <v-img
              :src="imgPath({ num: item.data[1], idx: 0 })"
              height="36"
              width="29"
            />
          </th>

          <th>
            <v-img
              :src="imgPath({ num: item.data[1], idx: 1 })"
              height="36"
              width="29"
            />
          </th>

          <th>
            <v-img
              :src="imgPath({ num: item.data[1], idx: 2 })"
              height="36"
              width="29"
            />
          </th>
        </tr>
      </table>
    </template>
    <template v-slot:[`item.data[2]`]="{ item }">
      No.{{ item.data[2] }}
      <table>
        <tr>
          <th>
            <v-img
              :src="imgPath({ num: item.data[2], idx: 0 })"
              height="36"
              width="29"
            />
          </th>

          <th>
            <v-img
              :src="imgPath({ num: item.data[2], idx: 1 })"
              height="36"
              width="29"
            />
          </th>

          <th>
            <v-img
              :src="imgPath({ num: item.data[2], idx: 2 })"
              height="36"
              width="29"
            />
          </th>
        </tr>
      </table>
    </template>
    <template v-slot:[`item.data[3]`]="{ item }">
      No.{{ item.data[3] }}
      <table>
        <tr>
          <th>
            <v-img
              :src="imgPath({ num: item.data[3], idx: 0 })"
              height="36"
              width="29"
            />
          </th>

          <th>
            <v-img
              :src="imgPath({ num: item.data[3], idx: 1 })"
              height="36"
              width="29"
            />
          </th>

          <th>
            <v-img
              :src="imgPath({ num: item.data[3], idx: 2 })"
              height="36"
              width="29"
            />
          </th>
        </tr>
      </table>
    </template>
    <template v-slot:[`item.data[4]`]="{ item }">
      No.{{ item.data[4] }}
      <table>
        <tr>
          <th>
            <v-img
              :src="imgPath({ num: item.data[4], idx: 0 })"
              height="36"
              width="29"
            />
          </th>

          <th>
            <v-img
              :src="imgPath({ num: item.data[4], idx: 1 })"
              height="36"
              width="29"
            />
          </th>

          <th>
            <v-img
              :src="imgPath({ num: item.data[4], idx: 2 })"
              height="36"
              width="29"
            />
          </th>
        </tr>
      </table>
    </template>
    <template v-slot:[`item.data[5]`]="{ item }">
      No.{{ item.data[5] }}
      <table>
        <tr>
          <th>
            <v-img
              :src="imgPath({ num: item.data[5], idx: 0 })"
              height="36"
              width="29"
            />
          </th>

          <th>
            <v-img
              :src="imgPath({ num: item.data[5], idx: 1 })"
              height="36"
              width="29"
            />
          </th>

          <th>
            <v-img
              :src="imgPath({ num: item.data[5], idx: 2 })"
              height="36"
              width="29"
            />
          </th>
        </tr>
      </table>
    </template>
    <template v-slot:[`item.data[6]`]="{ item }">
      No.{{ item.data[6] }}
      <table>
        <tr>
          <th>
            <v-img
              :src="imgPath({ num: item.data[6], idx: 0 })"
              height="36"
              width="29"
            />
          </th>

          <th>
            <v-img
              :src="imgPath({ num: item.data[6], idx: 1 })"
              height="36"
              width="29"
            />
          </th>

          <th>
            <v-img
              :src="imgPath({ num: item.data[6], idx: 2 })"
              height="36"
              width="29"
            />
          </th>
        </tr>
      </table>
    </template>

    <!-- 정보확인 -->
    <template v-slot:[`item.actions`]="{ item }">
      <v-icon small class="mr-2" @click="LookItem(item)"> mdi-pencil </v-icon>
    </template>

    <!-- 데이터가 없을 경우 -->
    <template v-slot:no-data> 데이터가 존재하지 않습니다. </template>
  </v-data-table>
</template>

<script>
import domain from "@/util/domain.js";
import { createNamespacedHelpers } from "vuex";
const cogemutilHelper = createNamespacedHelpers("cogemutil");
export default {
  props: {
    combi_list: Array,
  },
  data: () => ({
    dialog_item: false,
    headers: [],
    core_list: [],
    selectItem: {},
    selectLevel:{},
  }),
  computed: {
    ...cogemutilHelper.mapGetters(["getJob", "getSkillName", "getCoreList","getCoreLevel"]),
  },
  watch: {
    dialog_item(val) {
      val || this.close();
    },
    combi_list: function (val) {
      this.core_list = [];
      let header_len = 8;
      if (val.length != 0) {
        header_len = val[0].data.length + 1;
      }
      this.headers = [
        { text: "정보확인", value: "actions", sortable: false },
        { text: "1번", value: "data[0]" },
        { text: "2번", value: "data[1]" },
        { text: "3번", value: "data[2]" },
        { text: "4번", value: "data[3]" },
        { text: "5번", value: "data[4]" },
        { text: "6번", value: "data[5]" },
        { text: "7번", value: "data[6]" },
      ].slice(0, header_len);
      this.core_list = val;
    },
  },

  created() {
    this.initialize();
  },

  methods: {
    initialize() {
      this.headers = [
        { text: "정보확인", value: "actions", sortable: false },
        { text: "1번", value: "data[0]" },
        { text: "2번", value: "data[1]" },
        { text: "3번", value: "data[2]" },
        { text: "4번", value: "data[3]" },
        { text: "5번", value: "data[4]" },
        { text: "6번", value: "data[5]" },
        { text: "7번", value: "data[6]" },
      ].slice(0, 8);
    },
    makeLimit(arr) {
      let tmp = {};
      for (let i = 0; i < this.getSkillName.length; i++) {
        tmp[i] = 0;
      }
      for (let i = 0; i < arr.length; i++) {
        tmp[this.getCoreList[arr[i]].skill_data[0]] += 1;
        tmp[this.getCoreList[arr[i]].skill_data[1]] += 1;
        tmp[this.getCoreList[arr[i]].skill_data[2]] += 1;
      }
      return tmp;
    },
    LookItem(item) {
      this.selectItem = Object.assign({}, item);
      this.selectLevel = {};
      for(let i in this.getSkillName){
        this.selectLevel[i] = 0;
      }
      for(let i in item.data){
        let target = this.getCoreList[item.data[i]];
        for(let j in target.skill_data){
          this.selectLevel[target.skill_data[j]] += this.getCoreLevel[target.skill_data[0]];
        }
      }
      this.dialog_item = true;
    },

    close() {
      this.dialog_item = false;
    },

    imgPath(data) {
      let target = this.getCoreList[data.num].skill_data[data.idx];
      return (
        `${domain}coregem/skill/` +
        this.getJob +
        "/" +
        this.getSkillName[target]
      );
    },
    fix_imgPath(data) {
      return (
        `${domain}coregem/skill/` + this.getJob + "/" + this.getSkillName[data]
      );
    },
  },
};
</script>
