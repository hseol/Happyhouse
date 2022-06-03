<template>
  <div id="app">
    <header-bar></header-bar>

    <router-view />
    <middle-bar></middle-bar>
    <footer-bar></footer-bar>
  </div>
</template>

<script>
import HeaderBar from "@/components/layout/HeaderNaviBar.vue";
import FooterBar from "@/components/layout/FooterNaviBar.vue";
import MiddleBar from "@/components/layout/MiddleNaviBar.vue";

export default {
  name: "App",
  components: {
    HeaderBar,
    FooterBar,
    MiddleBar,
  },
};
</script>

<style>
a {
  text-decoration: none;
}

a:hover {
  text-decoration: none;
  font-weight: bold;
  color: chartreuse;
}

a.router-link-exact-active {
  color: #68b3c8;
}

@font-face {
  font-family: "GangwonEdu_OTFBoldA";
  src: url("https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2201-2@1.0/GangwonEdu_OTFBoldA.woff")
    format("woff");
  font-weight: normal;
  font-style: normal;
}

#app {
  font-family: "GangwonEdu_OTFBoldA";
}
</style>
