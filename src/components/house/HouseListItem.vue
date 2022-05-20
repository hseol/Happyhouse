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
        <h4 style="color: #ffcc66">
          <b-icon-geo-alt-fill></b-icon-geo-alt-fill> {{ house.아파트 }}
        </h4>
        <h6>- 거래금액 :{{ house.거래금액 }}만원</h6>
        <h6>- 법정동 :{{ house.법정동 }}</h6>
        <h6>- 도로명 : {{ house.도로명 }}</h6>
        <h6>- 면적 : {{ house.전용면적 }}m²</h6>
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
