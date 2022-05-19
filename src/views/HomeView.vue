<template>
  <b-container class="bv-example-row mt-3 text-center">
    <h3 class="underline-steelblue">
      <b-icon icon="house"></b-icon> HappyHouse
    </h3>
    <b-row>
      <b-col></b-col>
      <b-col cols="10">
        <b-jumbotron
          bg-variant="muted"
          text-variant="dark"
          border-variant="dark"
        >
          <template #header>HappyHouse</template>

          <template #lead>
            이제는 최종프로젝트다 <br />
            화이팅하고 달려가자!
          </template>

          <hr class="my-4" />
        </b-jumbotron>
      </b-col>
      <b-col></b-col>
    </b-row>
    <b-row>
      <b-col
        ><b-alert show><h3>공지사항</h3></b-alert><board-home></board-home
      ></b-col>
      <b-col
        ><b-alert show><h3>Q&amp;A</h3></b-alert><qna-list></qna-list
      ></b-col>
    </b-row>
  </b-container>
</template>

<script>
import BoardHome from "@/components/board/BoardList.vue";
import QnaList from "@/components/qna/QnaList.vue";

export default {
  name: "HomeView",
  props: {
    msg: String,
  },
  components: {
    BoardHome,
    QnaList,
  },
};
</script>

<style scoped>
.underline-steelblue {
  display: inline-block;
  background: linear-gradient(
    180deg,
    rgba(255, 255, 255, 0) 70%,
    rgba(72, 190, 233, 0.3) 30%
  );
}
</style>
