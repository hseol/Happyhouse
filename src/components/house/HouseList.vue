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
import { mapState } from "vuex";

const houseStore = "houseStore";

export default {
  name: "HouseList",
  components: {
    HouseListItem,
  },
  data() {
    return {};
  },
  computed: {
    ...mapState(houseStore, ["houses"]),
    // houses() {
    //   return this.$store.state.houses;
    // },
  },
};
</script>

<style></style>
