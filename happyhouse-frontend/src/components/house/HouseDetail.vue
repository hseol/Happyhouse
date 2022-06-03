<template>
  <b-container v-if="house" v-model="house" class="bv-example-row">
    <b-row class="mb-2 mt-1">
      <!-- <b-col
        ><b-img :src="require('@/assets/apt.png')" fluid-grow></b-img
      ></b-col> -->
    </b-row>
    <b-row>
      <b-col>
        <b-alert show variant="secondary"
          >아파트 이름 : {{ house.apartmentName }}<br />
          법정동: {{ house.dong }} <br />
          층수 : {{ house.floor }}층<br />
          전용면적(평): {{ house.area }}m² ({{
            parseInt(house.area / 3.3058)
          }}
          평)<br />
        </b-alert>
      </b-col>
      <b-col>
        <b-alert show variant="secondary"
          >일련번호 : {{ house.aptCode }}<br />
          건축년도 : {{ house.buildYear }} 년<br />
          계약일 : {{ house.dealYear }}년 {{ house.dealMonth }}월
          {{ house.dealDay }}일<br />
          거래금액 :{{
            (parseInt(house.recentPrice.replace(",", "")) * 10000) | price
          }}원<br />
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
    ...mapState(houseStore, ["house", "houses"]),
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
