<template>
  <v-container>
    <v-row>
      <v-col class="d-flex" sm="2">
        <v-select
          :items="main_category"
          v-model="main_select"
          label="직업 대분류"
          item-value="value"
          item-title="label"
          return-object
          solo
        ></v-select>
      </v-col>
      <v-col class="d-flex" sm="2">
        <v-select
          :items="sub_category_list[main_select.value]"
          v-model="sub_select"
          label="직업 선택"
          item-value="value"
          item-title="label"
          return-object
          solo
        ></v-select>
      </v-col>

      <v-col class="d-flex" sm="6">
        <v-file-input
          v-model="files"
          color="deep-purple accent-4"
          counter
          label="코어창 이미지를 선택하시오(권장:15장)"
          multiple
          placeholder="Select your files"
          prepend-icon="mdi-paperclip"
          outlined
          @change="previewFiles"
          :show-size="1000"
        >
          <template v-slot:selection="{ fileNames }">
            <div v-for="(fileName, index) in fileNames" :key="fileName">
              <v-chip
                v-if="index < 2"
                color="deep-purple-accent-4"
                label
                size="small"
                class="mr-2"
              >
                {{ fileName }}
              </v-chip>

              <span
                v-else-if="index === 2"
                class="text-overline text-grey-darken-3 mx-2"
              >
                +{{ files.length - 2 }} File(s)
              </span>
            </div>
          </template>
        </v-file-input>
      </v-col>
    </v-row>

    <v-carousel id="preview">
      <v-carousel-item
      v-for="(item,i) in previewimage"
      :key="i"
      :src="item"
      reverse-transition="fade-transition"
      transition="fade-transition"
    ></v-carousel-item>
    </v-carousel>
  </v-container>
</template>

<script>
export default {
  name: "CogemUtil-component",
  data: () => ({
    main_category: require("@/data/CogemUtil/main_category.json"),
    sub_category_list: require("@/data/CogemUtil/sub_category.json"),
    main_select: { label: "모험가", value: "adventurer" },
    sub_select: { label: "히어로", value: "Hero" },
    files: [],
    previewimage:['https://cdn.vuetifyjs.com/images/carousel/squirrel.jpg','https://cdn.vuetifyjs.com/images/carousel/sky.jpg'],
  }),
  methods: {
    previewFiles() {
      var files = document.querySelector("input[type=file]").files;
      var tmp_preview=[];
      function readAndPreview(file) {
        // `file.name` 형태의 확장자 규칙에 주의하세요
        if (/\.(jpe?g|png|gif)$/i.test(file.name)) {
          var reader = new FileReader();

          reader.addEventListener(
            "load",
            function () {
              var image = new Image();
              image.height = 100;
              image.title = file.name;
              image.src = this.result;
              tmp_preview.push(image.src);
            },
            false
          );

          reader.readAsDataURL(file);
        }
      }
      
      if (files) {
        [].forEach.call(files, readAndPreview);
      }
      this.previewimage=tmp_preview;
      console.log(tmp_preview[0]);
      console.log(this.previewimage[0])
    },
  },
};
</script>
