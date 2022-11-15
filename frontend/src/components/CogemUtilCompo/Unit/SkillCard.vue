<template>
  <v-card class="mx-auto" max-width="350" max-height="80" outlined>
    <v-list-item three-line>
      <v-list-item-avatar tile height="50" width="32">
        <v-img :src="img_src" height="50" width="32"></v-img>
      </v-list-item-avatar>
      <v-list-item-content right>
        <v-list-item-title style="font-size: 1.2em">
          {{ info }} 
          <v-checkbox style="margin-top:1px" label="선택" :true-value="check_val(true,idx)" :false-value="check_val(false,idx)" @change="check"></v-checkbox>
        </v-list-item-title>
      </v-list-item-content>
    </v-list-item>
  </v-card>
</template>

<script>
export default {
  props: {
    img_src: String,
    info: String,
    idx: Number,
    selected:Array,
  },
  
  methods:{
    check(target){
        console.log(target,"보냄");
        this.$emit("selectedChange", target);
    },
    check_val(check,val){
        return {check:check,val:val}
    }

  }
};
</script>

