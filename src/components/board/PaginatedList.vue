<template>
  <div>
    <b-row>
      <b-col v-if="listArray.length">
        <b-table-simple hover responsive>
          <b-thead head-variant="light">
            <b-tr>
              <b-th style="width: 10%">번호</b-th>
              <b-th style="width: 35%">제목</b-th>
              <b-th style="width: 20%">작성자</b-th>
              <b-th style="width: 15%">조회수</b-th>
              <b-th style="width: 20%">작성일</b-th>
            </b-tr>
          </b-thead>
          <tbody>
            <!-- 하위 component인 ListRow에 데이터 전달(props) -->

            <b-tr
              v-for="article in paginatedData"
              :key="article.articleno"
              v-bind="article"
            >
              <b-td>{{ article.articleno }}</b-td>
              <b-th class="text-left">
                <router-link
                  :to="{
                    name: 'boardDetail',
                    params: { articleno: article.articleno },
                  }"
                  >{{ article.subject }}</router-link
                >
              </b-th>
              <b-td>{{ article.userid }}</b-td>
              <b-td>{{ article.hit }}</b-td>
              <b-td>{{ article.regtime | dateFormat }}</b-td>
            </b-tr>
          </tbody>
        </b-table-simple>
      </b-col>
      <!-- <b-col v-else class="text-center">도서 목록이 없습니다.</b-col> -->
    </b-row>
    <div class="btn-cover">
      <b-button
        variant="outline-primary"
        :disabled="pageNum === 0"
        @click="prevPage"
        class="page-btn"
      >
        이전
      </b-button>
      <span class="page-count">{{ pageNum + 1 }} / {{ pageCount }} 페이지</span>
      <b-button
        variant="outline-primary"
        :disabled="pageNum >= pageCount - 1"
        @click="nextPage"
        class="page-btn"
      >
        다음
      </b-button>
    </div>
  </div>
</template>

<script>
import moment from "moment";
//import { listArticle } from "@/api/board.js";
//import BoardListItem from "@/components/board/item/BoardListItem";

export default {
  name: "paginated-list",
  components: {
    // BoardListItem,
  },
  data() {
    return {
      pageNum: 0,
    };
  },
  props: {
    listArray: {
      type: Array,
      required: true,
    },
    pageSize: {
      type: Number,
      required: false,
      default: 10,
    },
  },
  methods: {
    nextPage() {
      this.pageNum += 1;
    },
    prevPage() {
      this.pageNum -= 1;
    },
  },
  computed: {
    pageCount() {
      let listLeng = this.listArray.length,
        listSize = this.pageSize,
        page = Math.floor(listLeng / listSize);
      if (listLeng % listSize > 0) page += 1;

      /*
      아니면 page = Math.floor((listLeng - 1) / listSize) + 1;
      이런식으로 if 문 없이 고칠 수도 있다!
      */
      return page;
    },
    paginatedData() {
      const start = this.pageNum * this.pageSize,
        end = start + this.pageSize;
      return this.listArray.slice(start, end);
    },
  },

  filters: {
    dateFormat(regtime) {
      return moment(new Date(regtime)).format("YY.MM.DD");
    },
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
