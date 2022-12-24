import http from "@/util/http.js";

const state = {
  core_list:[],
  request_image_list:[],
  job:"",
  job_kr:"",
  skill_name:[],
  core_level:[],

  upload_wait:false,
}

const getters = {
  getUploadWait(){
    return state.upload_wait;
  },
  getCoreList(){
    return state.core_list;
  },
  getRequestImageList(){
    return state.request_image_list;
  },
  getJob(){
    return state.job;
  },
  getJobKr(){
    return state.job_kr;
  },
  getSkillName(){
    return state.skill_name;
  },
  getCoreLevel(){
    return state.core_level;
  }
}

const actions = {
  //CogemUtilInput 비동기처리
  searchImage({state,commit},payload) {
    state.upload_wait=true;
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
        commit('USERCOREINFO',response.data);
      })
      .catch(function (error) {
        alert(`error:문제가 발생하였습니다. 잠시 후 다시 이용해주세요.
        ${error}`);
        state.upload_wait=false;
      });
  }
}

// mutations
const mutations = {
  //CogemUtilInput 동기처리
  SEARCHIMAGE(state,payload){
    console.log("mutation",payload);
    state.upload_wait=false;
  },


  USERCOREINFO(state,payload) {
    state.core_list = payload.core_list;
    state.request_image_list = payload.request_image_list;
    state.job = payload.job;
    state.job_kr = payload.job_kr;
    state.skill_name = payload.skill_name;
    state.core_level = new Array(payload.skill_name.length);
    state.core_level.fill(0);

    const acc_exp = [
      0, 1, 3, 4, 6, 8, 10, 13, 15, 19, 22, 26, 30, 34, 39, 44, 49, 55, 61,
      67, 74, 80, 88, 95, 103, 111, 9999,
    ];

    for (let i = 0; i < state.core_list.length; i++) {
      state.core_level[state.core_list[i].skill_data[0]] +=
        acc_exp[state.core_list[i].level];
    }
    for (let i = 0; i < state.core_level.length; i++) {
      for (let j = 0; j < acc_exp.length - 1; j++) {
        if (
          acc_exp[j] <= state.core_level[i] &&
          state.core_level[i] < acc_exp[j + 1]
        ) {
          state.core_level[i] = j;
          break;
        }
      }
    }
    
    state.upload_wait=false;
  },
}

export default {
  namespaced: true,
  state,
  getters,
  actions,
  mutations
}