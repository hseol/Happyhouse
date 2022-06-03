<template>
  <div id="footer">
    <b-container id="container" class="mt-2 p-2">
      <div class="row">
        <h4>Happy House</h4>
      </div>
      <div class="row p-3">
        <div class="col-sm-3">
          <h5>[ Service ]</h5>
          <ul>
            <ol>
              매매 검색
            </ol>
            <ol>
              관심 지역 등록
            </ol>
          </ul>
        </div>
        <div class="col-sm-3">
          <h5>[ House ]</h5>
          <ul>
            <ol>
              공지사항
            </ol>
            <ol>
              Q & A
            </ol>
          </ul>
        </div>
        <div class="col-sm-3">
          <h5>[ Policies ]</h5>
          <ul>
            <ol>
              Terms of Use
            </ol>
          </ul>
        </div>
        <div class="col-sm-3">
          <h5>[ About House ]</h5>
          <ul>
            <ol>
              Careers
            </ol>
            <ol>
              Copyright
            </ol>
          </ul>
        </div>
      </div>
    </b-container>
    <div id="copyright">
      <p>
        Copyright © 2022 Happy house. All rights reserved. Designed by 성성민 &
        허설
      </p>
    </div>
  </div>
</template>
<style scoped>
@import url("https://fonts.googleapis.com/css2?family=Montserrat&display=swap");
p {
  font-size: 13px;
  font-family: "Montserrat", sans-serif;
  color: wheat;
}
#container {
  height: 160px;
}
#footer {
  border-top: 1px solid rgba(243, 169, 104, 0.136);
  /* border-bottom: 5px solid rgb(107, 100, 103); */
}
h4 {
  color: #514d49;
}
h5 {
  /* color: #030b14; */
  color: #66615b;
}
#copyright {
  position: relative;
  text-align: right;
  padding-right: 40px;
}
ol,
ul {
  color: #4b3d2c;
  margin: 0;
  padding: 0;
}
</style>
<script>
export default {};
</script>
