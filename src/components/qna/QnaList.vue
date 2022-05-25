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

      <qna-paginated-list :pageSize="2" :list-array="articles" />
      <!-- <b-col v-else class="text-center">도서 목록이 없습니다.</b-col> -->
    </b-container>
  </div>
</template>

<script>
import { listArticle } from "@/api/qna.js";
import QnaPaginatedList from ".//QnaPaginatedList";
//import BoardListItem from "@/components/board/item/BoardListItem";

export default {
  name: "qnaList",
  components: {
    QnaPaginatedList,
  },
  data() {
    return {
      articles: [],
    };
  },
  created() {
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
      },
    );
  },
  methods: {
    moveWrite() {
      this.$router.push({ name: "qnaRegister" });
    },
  },
  computed: {},
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
