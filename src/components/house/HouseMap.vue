<template>
  <div>
    <div id="map" style="width: 100%; height: 500px; margin: auto"></div>
  </div>
</template>

<script>
import { mapState } from "vuex";
export default {
  name: "KakaoMap",
  computed: {
    ...mapState(["house"]),
  },
  mounted() {
    if (window.kakao && window.kakao.maps) {
      this.initMap();
    } else {
      const script = document.createElement("script");
      /* global kakao */
      script.onload = () => kakao.maps.load(this.initMap);
      script.src =
        "//dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=6a435b67598819acc51c97c1d6599d3b";
      document.head.appendChild(script);
    }
  },

  methods: {
    initMap() {
      const container = document.getElementById("map");
      const options = {
        center: new kakao.maps.LatLng(33.450701, 126.570667),
        level: 5,
      };

      //지도 객체를 등록합니다.
      //지도 객체는 반응형 관리 대상이 아니므로 initMap에서 선언합니다.
      this.map = new kakao.maps.Map(container, options);
    },

    showPosition() {
      if (this.house) {
        console.log(this.house.lat + "," + this.house.lng);
        var placePosition = new kakao.maps.LatLng(
          this.house.lat,
          this.house.lng,
        );
        var bounds = new kakao.maps.LatLngBounds();
        bounds.extend(placePosition);
        this.map.setBounds(bounds);
      }
    },
  },
};
</script>

<style scoped>
#map {
  width: 320px;
  height: 320px;
}
</style>
