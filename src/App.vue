<template>
  <div id="app">
    <header-bar></header-bar>
    <ether-test />
    <!--<map-view /> -->
    <router-view />
    <footer-bar></footer-bar>
  </div>
</template>

<script>
import HeaderBar from "@/components/layout/HeaderNaviBar.vue";
import FooterBar from "@/components/layout/FooterNaviBar.vue";
import EtherTest from "./views/EtherTest.vue";
//import MapView from "./views/MapView.vue";

export default {
  name: "App",
  components: {
    HeaderBar,
    FooterBar,
    EtherTest,
    // MapView,
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
</style>
