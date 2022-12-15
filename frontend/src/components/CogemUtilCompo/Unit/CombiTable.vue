<template>
  <v-data-table
    :headers="headers"
    :items="core_list"
    sort-by="calories"
    class="elevation-1"
  >
    <template v-slot:top>
      <v-toolbar flat>
        <v-toolbar-title><h1>찾은 조합</h1></v-toolbar-title>
        <v-divider class="mx-4" inset vertical></v-divider>
        <v-spacer></v-spacer>

        <v-dialog v-model="dialog" max-width="1000px">
          <v-card>
            <v-card-text>
              <v-container>
                <v-row>
                  <v-col cols="12" sm="6" md="4">
                    <v-text-field
                      v-model="editedItem.name"
                      label="Dessert name"
                    ></v-text-field>
                  </v-col>
                  <v-col cols="12" sm="6" md="4">
                    <v-text-field
                      v-model="editedItem.calories"
                      label="Calories"
                    ></v-text-field>
                  </v-col>
                  <v-col cols="12" sm="6" md="4">
                    <v-text-field
                      v-model="editedItem.fat"
                      label="Fat (g)"
                    ></v-text-field>
                  </v-col>
                  <v-col cols="12" sm="6" md="4">
                    <v-text-field
                      v-model="editedItem.carbs"
                      label="Carbs (g)"
                    ></v-text-field>
                  </v-col>
                  <v-col cols="12" sm="6" md="4">
                    <v-text-field
                      v-model="editedItem.protein"
                      label="Protein (g)"
                    ></v-text-field>
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
      <v-icon small class="mr-2" @click="editItem(item)"> mdi-pencil </v-icon>
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
    dialog: false,
    headers: [],
    core_list: [],
    editedItem: {
      name: "",
      calories: 0,
      fat: 0,
      carbs: 0,
      protein: 0,
    },
  }),
  computed: {
    ...cogemutilHelper.mapGetters(["getJob", "getSkillName", "getCoreList"]),
  },
  watch: {
    dialog(val) {
      val || this.close();
    },
    combi_list: function (val) {
      val;
      this.core_list = [
        {
          data: [1, 2, 3, 4, 5, 6 ],
        },
        {
          data: [2, 3, 4, 5, 6, 7],
        },
        {
          data: [3, 4, 5, 6, 7, 1],
        },
        {
          data: [4, 5, 6, 7, 1, 2],
        },
        {
          data: [5, 6, 7, 1, 2, 3],
        },
        {
          data: [6, 7, 1, 2, 3, 4],
        },
        {
          data: [7, 1, 2, 3, 4, 5],
        },
      ];
      this.headers = [
        { text: "정보확인", value: "actions", sortable: false },
        { text: "1번", value: "data[0]" },
        { text: "2번", value: "data[1]" },
        { text: "3번", value: "data[2]" },
        { text: "4번", value: "data[3]" },
        { text: "5번", value: "data[4]" },
        { text: "6번", value: "data[5]" },
        { text: "7번", value: "data[6]" },
      ].slice(0,this.cre_list.length+1);
    },
  },

  created() {
    this.initialize();
  },

  methods: {
    initialize() {
      this.core_list = [
        {
          data: [1, 2, 3, 4, 5, 1],
        },
        {
          data: [2, 3, 4, 5, 6, 1],
        },
        {
          data: [3, 4, 5, 6, 7, 1],
        },
      ];

      this.headers = [
        { text: "정보확인", value: "actions", sortable: false },
        { text: "1번", value: "data[0]" },
        { text: "2번", value: "data[1]" },
        { text: "3번", value: "data[2]" },
        { text: "4번", value: "data[3]" },
        { text: "5번", value: "data[4]" },
        { text: "6번", value: "data[5]" },
        { text: "7번", value: "data[6]" },
      ].slice(0,this.core_list.length+1);
    },

    editItem(item) {
      this.editedItem = Object.assign({}, item);
      this.dialog = true;
    },

    close() {
      this.dialog = false;
    },

    imgPath(data) {
      console.log(data.num, this.getCoreList[data.num]);
      let target = this.getCoreList[data.num].skill_data[data.idx];
      return (
        `${domain}coregem/skill/` +
        this.getJob +
        "/" +
        this.getSkillName[target]
      );
    },
  },
};
</script>