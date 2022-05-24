<template>
  <div>
    <div id="map" style="width: 100%; height: 500px; margin: auto"></div>
  </div>
</template>

<script>
import { mapState } from "vuex";
const houseStore = "houseStore";

export default {
  name: "KakaoMap",
  computed: {
    ...mapState(["house"]),
  },
  // created() {
  //   console.log(this.house.lat + "," + this.house.lng);
  // },
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
        center: new kakao.maps.LatLng(37.49959, 127.026372),
        level: 5,
      };

      //지도 객체를 등록합니다.
      //지도 객체는 반응형 관리 대상이 아니므로 initMap에서 선언합니다.
      var map = new kakao.maps.Map(container, options);

      var zoomControl = new kakao.maps.ZoomControl();
      map.addControl(zoomControl, kakao.maps.ControlPosition.RIGHT);

      kakao.maps.event.addListener(map, "center_changed", function () {
        console.log(
          "지도 중심 좌표 : " + map.getCenter().toString()
        );
        //console.log(this.house.lat + "," + this.house.lng);
      });


      var marker = new kakao.maps.Marker({
        position: new kakao.maps.LatLng(37.49959, 127.026372), 
        map: map, 
      });

      var infowindow = new kakao.maps.InfoWindow({
        content: '<div style="padding:5px;">확인용 클릭</div>', 
      });

      infowindow.open(map, marker);

      kakao.maps.event.addListener(marker, "click", function () {
        alert("클릭클릭");
      });


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
