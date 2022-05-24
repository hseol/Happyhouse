<template>
  <b-container class="bv-example-row mt-3 text-center">
    <h3 class="underline-steelblue">
      <b-icon icon="house"></b-icon> HappyHouse
    </h3>
    <b-row>
      <b-carousel
        id="carousel-fade"
        style="text-shadow: 0px 0px 2px #000"
        fade
        indicators
        img-width="1140"
        img-height="480"
      >
        <b-carousel-slide
          caption="first Slide"
          img-src="https://cdn.pixabay.com/photo/2016/11/21/15/09/apartments-1845884_1280_.jpg"
        ></b-carousel-slide>
        <b-carousel-slide
          caption="Second Slide"
          img-src="https://picsum.photos/1140/480/?image=12"
        ></b-carousel-slide>
        <b-carousel-slide
          caption="Third Slide"
          img-src="https://picsum.photos/1140/480/?image=22"
        ></b-carousel-slide>
      </b-carousel>
    </b-row>
    <b-row>
      <b-col class="my-3"
        ><b-alert show><h3>공지사항</h3></b-alert><board-home></board-home
      ></b-col>
      <b-col class="my-3"
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
