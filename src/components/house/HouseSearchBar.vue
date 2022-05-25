<template>
  <div>
    <div>
      <b-row class="mt-4 mb-4 text-center">
        <b-col></b-col>
        <b-col
          ><img :src="isOn ? on : off" width="50px" @click="voiceInput" /><input
            v-model="searchWords"
        /></b-col>
        <b-col></b-col>
      </b-row>
      <b-row class="">
        <b-col
          >(마이크를 누르고 5초안에 검색 지역을 말하세요. ex. "서울특별시
          종로구")</b-col
        >
      </b-row>
    </div>
    <b-row class="mt-4 mb-4 text-center">
      <!-- <b-col class="sm-3">
      <b-form-input
        v-model.trim="dongCode"
        placeholder="동코드 입력...(예 : 11110)"
        @keypress.enter="sendKeyword"
      ></b-form-input>
    </b-col>
    <b-col class="sm-3" align="left">
      <b-button variant="outline-primary" @click="sendKeyword">검색</b-button>
    </b-col> -->

      <b-col class="sm-3">
        <b-form-select
          v-model="sidoCode"
          :options="sidos"
          @change="gugunList"
        ></b-form-select>
      </b-col>
      <b-col class="sm-3">
        <b-form-select
          v-model="gugunCode"
          :options="guguns"
          @change="dongList"
        ></b-form-select>
      </b-col>
      <b-col class="sm-3">
        <b-form-select
          v-model="dongCode"
          :options="dongs"
          @change="searchApt"
        ></b-form-select>
      </b-col>
    </b-row>
  </div>
</template>

<script>
import { mapState, mapActions, mapMutations } from "vuex";

/*
  namespaced: true를 사용했기 때문에 선언해줍니다.
  index.js 에서 modules 객체의 '키' 이름입니다.

  modules: {
    키: 값
    memberStore: memberStore,
    houseStore: houseStore
  }
*/
const houseStore = "houseStore";

export default {
  name: "HouseSearchBar",
  data() {
    return {
      sidoCode: null,
      gugunCode: null,
      dongCode: null,
      isOn: false,
      on: "img/mic2.png",
      off: "img/mic1.png",
      searchWords: "",
      recognition: {},
    };
  },
  computed: {
    ...mapState(houseStore, ["sidos", "guguns", "dongs", "houses"]),
    // sidos() {
    //   return this.$store.state.sidos;
    // },
  },
  created() {
    // this.$store.dispatch("getSido");
    // this.sidoList();
    this.CLEAR_SIDO_LIST();
    this.getSido();
    this.voiceSearchReady();
  },
  methods: {
    ...mapActions(houseStore, [
      "getSido",
      "getGugun",
      "getDong",
      "getHouseList",
      "getAptList",
    ]),
    ...mapMutations(houseStore, [
      "CLEAR_SIDO_LIST",
      "CLEAR_GUGUN_LIST",
      "CLEAR_DONG_LIST",
      "SET_HOUSE_LIST",
    ]),

    // sidoList() {
    //   this.getSido();
    // },
    gugunList() {
      console.log(this.sidoCode);
      this.CLEAR_GUGUN_LIST();
      this.gugunCode = null;
      if (this.sidoCode) this.getGugun(this.sidoCode);
    },
    dongList() {
      console.log(this.gugunCode);
      this.CLEAR_DONG_LIST();
      this.dongCode = null;
      if (this.gugunCode) this.getDong(this.gugunCode);
    },
    // searchApt() {
    //   if (this.gugunCode) this.getHouseList(this.gugunCode);
    // },
    searchApt() {
      console.log(this.dongCode);
      if (this.dongCode) {
        this.getAptList(this.dongCode);
      }
    },
    voiceSearchReady() {
      window.SpeechRecognition =
        window.SpeechRecognition || window.webkitSpeechRecognition;
      this.recognition = new window.SpeechRecognition();
      this.recognition.interimResults = true;
      console.log(this.sidos);
      //  console.log(this.recognition);
      this.recognition.addEventListener("result", (e) => {
        //console.log(e.results[0][0].transcript);
        this.searchWords = e.results[0][0].transcript;
        const words = this.searchWords.split(" ");
        let sidoWord = words[0].trim();
        let sido = this.$store.state.sidos.find((item) => {
          return item.text == sidoWord;
        });
        this.sidoCode = sido.value;

        this.gugunList();
        let gugunWord = words[1].trim();
        console.log(gugunWord);
        let gugun = this.$store.state.guguns.find((item) => {
          return item.text == gugunWord;
        });
        this.gugunCode = gugun.value;

        this.dongList();
        setTimeout(() => {
          let dongWord = words[2].trim();
          console.log(dongWord);
          let dong = this.$store.state.dongs.find((item) => {
            return item.text == dongWord;
          });
          this.dongCode = dong.value;
          this.searchApt();
        }, 500);
      });
    },
    voiceInput() {
      this.isOn = true;
      setTimeout(() => {
        this.isOn = false;
        this.recognition.stop();
      }, 5000);
      this.recognition.start();
    },
  },
};
</script>

<style></style>
