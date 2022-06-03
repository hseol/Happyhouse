<template>
  <div>
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
          img-width="100%"
        >
          <b-carousel-slide
            class="pic"
            caption="행복한 우리집 찾기"
            img-src="https://image.newdaily.co.kr/site/data/img/2019/10/08/2019100800016_0.jpg"
          ></b-carousel-slide>
          <b-carousel-slide
            class="pic"
            caption=""
            img-src="https://cdn.pixabay.com/photo/2016/11/23/00/55/burn-1851563_960_720.jpg"
          ></b-carousel-slide>
          <b-carousel-slide
            class="pic"
            caption="꿈의 집으로 놀러오세요!"
            img-src="https://cdn.pixabay.com/photo/2016/06/24/10/47/house-1477041_960_720.jpg"
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
  </div>
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
.pic {
  width: 1140px;
  height: 480px;
}
</style>
