<template>
  <b-container v-if="houses && houses.length != 0" class="bv-example-row mt-3">
    <b-card no-body>
      <b-card-header header-tag="nav">
        <b-nav card-header tabs>
          <b-nav-item active>검색 결과</b-nav-item>
          <b-nav-item active>관심 지역</b-nav-item>
        </b-nav>
      </b-card-header>
      <b-card-body
        class="mb-2"
        style="
          position: relative;
          min-height: 500px;
          max-height: 650px;
          overflow-y: scroll;
        "
      >
        <house-list-item
          v-for="(house, index) in houses"
          :key="index"
          :house="house"
        />
      </b-card-body>
    </b-card>
  </b-container>

  <b-container v-else class="bv-example-row mt-3">
    <b-row>
      <b-col><b-alert show>주택 목록이 없습니다.</b-alert></b-col>
    </b-row>
  </b-container>
</template>

<script>
import HouseListItem from "@/components/house/HouseListItem.vue";
import { mapState, mapMutations } from "vuex";

const houseStore = "houseStore";

export default {
  name: "HouseList",
  components: {
    HouseListItem,
  },

  data() {
    return {
      house: {},
    };
  },
  created() {
    console.log(this.$store.state.houses);
  },
  //보안상 위험하다는 경고가 떴다 직접적으로 접근하지 말라는데 아무리 시도해도 state가 출력이 안된다... 하루를 다썼다..
  computed: {
    ...mapState(houseStore, ["houses"]),
    ...mapMutations(houseStore, [" SET_HOUSE_LIST"]),
    hous() {
      console.log(this.houses);
      this.$store.state.houses = this.houses;
      return this.$store.state.houses;
    },
  },
  //낮에 시도해려고 했던거 computed 와 watch
  // watch가 유심히 보고 있다가 데이터에 변화가 생기면 (val) 특정행동을 해준다. 지금 같은 경우는 목록을 실시간으로 띄워주려고
  // 그 다음에 컴퓨티드한테 줘서 셋함수? 처럼 쓰는거 같음.. 이걸 처음에 했었는데 return 을 다른 걸 줘서 계속 값이 안나왔음..
  watch: {
    hous(val) {
      this.houses = val;
    },
  },
  methods: {
    SET_HOUSE_LIST() {
      this.$store.commit("SET_HOUSE_LIST", this.houses);
    },
  },
};
</script>

<style></style>
