import http from "@/util/http.js";

const state = {
  tmp:0
}

const getters = {

}

const actions = {
  searchImage({commit},payload) {
    let formdata = new FormData();
    formdata.append("job", payload.sub_select.value);
    formdata.append("job_kr", payload.sub_select.label);
    payload.files.forEach((item) => formdata.append("upfile", item));
    http({
      method: "post",
      url: "/coregem/get-core-list",
      data: formdata,
      headers: { "Content-Type": "multipart/form-data" },
    })
      .then((response) => {
        commit('SEARCHIMAGE',response.data);
      })
      .catch(function (error) {
        console.log(error);
      });
  }
}

// mutations
const mutations = {
  SEARCHIMAGE(state,payload){
    state.tmp+=1;
    console.log(payload);
  }
}

//this.$emit("userCoreInfo", response.data);




export default {
  namespaced: true,
  state,
  getters,
  actions,
  mutations
}