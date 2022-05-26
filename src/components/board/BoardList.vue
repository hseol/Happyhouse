<template>
  <div>
    <b-container class="bv-example-row mt-3">
      <b-row class="mb-1">
        <b-col class="text-right">
          <b-button variant="outline-primary" @click="moveWrite()"
            >글쓰기</b-button
          >
        </b-col>
      </b-row>

      <board-paginated-list :pageSize="2" :list-array="articles" />
      <!-- <b-col v-else class="text-center">도서 목록이 없습니다.</b-col> -->
    </b-container>
  </div>
</template>

<script>
import { listArticle } from "@/api/board.js";
import BoardPaginatedList from "./BoardPaginatedList";
import { mapState } from "vuex";
import memberStore from "@/store/modules/memberStore";
//import BoardListItem from "@/components/board/item/BoardListItem";

export default {
  name: "boardList",
  components: {
    BoardPaginatedList,
  },
  data() {
    return {
      articles: [],
      user: {},
    };
  },
  created() {
    this.user = this.$store.state.memberStore.userInfo;
    console.log(this.user);
    let param = {
      pg: 1,
      spp: 20,
      key: null,
      word: null,
    };
    listArticle(
      param,
      (response) => {
        this.articles = response.data;
      },
      (error) => {
        console.log(error);
      }
    );
  },
  methods: {
    moveWrite() {
      this.$router.push({ name: "boardRegister" });
    },
  },
  computed: {
    ...mapState(memberStore, ["userInfo"]),
  },
};
</script>

<style scope>
.tdClass {
  width: 50px;
  text-align: center;
}
.tdSubject {
  width: 300px;
  text-align: left;
}
</style>
