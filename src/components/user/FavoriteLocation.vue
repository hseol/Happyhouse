<template>
  <b-row class="text-center">
    <b-form-group label="관심지역" label-for="favlo" class="formlabel">
      <span>
        <b-form-select
          style="width: 120px"
          v-model="sidoCode"
          :options="sidos"
          @change="gugunList"
        ></b-form-select>

        <b-form-select
          style="width: 120px"
          v-model="gugunCode"
          :options="guguns"
        ></b-form-select>
        &nbsp;&nbsp;
        <b-button>관심지역설정!</b-button>
      </span>
    </b-form-group>

    <!--
    <b-col cols="5">
      <b-form-select
        v-model="sidoCode"
        :options="sidos"
        @change="gugunList"
      ></b-form-select>
    </b-col>
    <b-col cols="5">
      <b-form-select v-model="gugunCode" :options="guguns"></b-form-select>
    </b-col> -->

    <!-- @change="dongList"  -->
    <!-- <b-col sm="3">
      <b-form-select
        v-model="dongCode"
        :options="dongs"
        @change="searchApt"
      ></b-form-select>
    </b-col> -->
  </b-row>
</template>

<script>
import { mapState, mapActions, mapMutations } from "vuex";

const houseStore = "houseStore";

export default {
  name: "HouseSearchBar",
  data() {
    return {
      sidoCode: null,
      gugunCode: null,
      // dongCode: null,
    };
  },
  computed: {
    ...mapState(houseStore, ["sidos", "guguns"]),
    // sidos() {
    //   return this.$store.state.sidos;
    // },
  },
  created() {
    // this.$store.dispatch("getSido");
    // this.sidoList();
    this.CLEAR_SIDO_LIST();
    this.getSido();
  },
  methods: {
    ...mapActions(houseStore, [
      "getSido",
      "getGugun",
      //"getDong,
    ]),
    ...mapMutations(houseStore, [
      "CLEAR_SIDO_LIST",
      "CLEAR_GUGUN_LIST",
      //"CLEAR_DONG_LIST",
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
    // dongList() {
    //   console.log(this.gugunCode);
    //   this.CLEAR_DONG_LIST();
    //   this.dongCode = null;
    //   if (this.gugunCode) this.getDong(this.gugunCode);
    // },
    // searchApt() {
    //   if (this.gugunCode) this.getHouseList(this.gugunCode);
    // },
  },
};
</script>

<style></style>
