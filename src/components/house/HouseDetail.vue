<template>
  <b-container v-if="house" class="bv-example-row">
    <b-row class="mb-2 mt-1">
      <!-- <b-col
        ><b-img :src="require('@/assets/apt.png')" fluid-grow></b-img
      ></b-col> -->
    </b-row>
    <b-row>
      <b-col>
        <b-alert show variant="secondary"
          >아파트 이름 : {{ house.아파트 }}<br />
          법정동(도로명): {{ house.법정동 }}({{ house.도로명 }})<br />
          층수 : {{ house.층 }}층<br />
          전용면적 : {{ house.전용면적 }}m²<br />
        </b-alert>
      </b-col>
      <b-col>
        <b-alert show variant="secondary"
          >일련번호 : {{ house.일련번호 }}<br />
          건축년도 : {{ house.건축년도 }}<br />
          계약일 : {{ house.년 }}년 {{ house.계약월 }}월 {{ house.일 }}일<br />
          거래금액 : {{ house.거래금액 }}<br />
        </b-alert>
      </b-col>
    </b-row>
  </b-container>
</template>

<script>
import { mapState } from "vuex";

const houseStore = "houseStore";

export default {
  name: "HouseDetail",
  computed: {
    ...mapState(houseStore, ["house"]),
    // house() {
    //   return this.$store.state.house;
    // },
  },
  filters: {
    price(value) {
      if (!value) return value;
      return value.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
    },
  },
};
</script>

<style></style>
