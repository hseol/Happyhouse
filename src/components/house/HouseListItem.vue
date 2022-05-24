<template>
  <b-row
    class="m-2"
    @click="selectHouse"
    @mouseover="colorChange(true)"
    @mouseout="colorChange(false)"
    :class="{ 'mouse-over-bgcolor': isColor }"
  >
    <b-media>
      <b-media-body>
        <h5 style="color: #ffcc66">
          <b-icon-geo-alt-fill></b-icon-geo-alt-fill> {{ house.apartmentName }}
        </h5>
        <h6>- 거래금액 :{{ house.recentPrice }}만원</h6>
        <h6>- 건축년도 :{{ house.buildYear }}년</h6>
        <h6>- 면적(평) : {{ house.area }}m²  ({{ (parseInt(house.area/3.3058)) }} 평)</h6>
      </b-media-body>
    </b-media>
    <!-- <b-col cols="2" class="text-center align-self-center">
      <b-img
        thumbnail
        src="https://picsum.photos/250/250/?image=58"
        alt="Image 1"
      ></b-img>
    </b-col> -->
  </b-row>
</template>

<script>
import { mapActions } from "vuex";

const houseStore = "houseStore";

export default {
  name: "HouseListItem",
  data() {
    return {
      isColor: false,
      hou: {},
    };
  },
  props: {
    house: Object,
  },
  methods: {
    ...mapActions(houseStore, ["detailHouse"]),
    selectHouse() {
      // console.log("listRow : ", this.house);
      // this.$store.dispatch("getHouse", this.house);
      this.detailHouse(this.house);
    },
    colorChange(flag) {
      this.isColor = flag;
    },
  },
  created() {
    this.hou = this.house;
    //console.log(this.hou);
  },
};
</script>

<style scoped>
.apt {
  width: 50px;
}
.mouse-over-bgcolor {
  background-color: lightblue;
}
</style>
