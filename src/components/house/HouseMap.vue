<template>
  <div>
    <div id="map" style="width: 100%; height: 500px; margin: auto"></div>
    <!-- <button @click="displayMarker(markerPositions)">marker set 1</button> -->
    <div v-if="this.$store.state.houses.length > 0">
      {{ this.$store.state.houses[0].apartmentName }}
    </div>
    <!--값이 실시간으로 바뀌는지 보려면 이렇게 보는게 제일 정확해서 div 하나 팠음.. 최종적으로는 지울 것!!! -->

    <button @click="makeList">이벤트발생</button>
    <!--필요에 따라서 버튼을 만들어 적용하는 것도 나쁘지 않음... -->
  </div>
</template>
<!--displayMarker(markerPositions1)-->
<script>
import houseStore from "@/store/modules/houseStore";
import { mapState } from "vuex";

export default {
  name: "HouseMap",
  data() {
    return {
      posi: {},
      markers: [],
      infowindow: null,
      map: null,
    };
  },

  computed: {
    ...mapState(houseStore, ["house", "houses"]),
  },
  mounted() {
    if (window.kakao && window.kakao.maps) {
      this.initMap();
    } else {
      const script = document.createElement("script");
      /* global kakao */
      script.onload = () => kakao.maps.load(this.initMap);
      script.src =
        "//dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=929551aadfc7f7f042a99f58cbd97041&libraries=services";
      document.head.appendChild(script);
    }
  },

  methods: {
    makeList() {
      var posi = [];
      this.houses = this.$store.state.houses;
      this.houses.forEach((house) => {
        posi.push({
          title: house.apartmentName,
          latlng: new kakao.maps.LatLng(house.lat, house.lng),
        });
      });

      this.posi = posi;
      console.log(this.posi);
      //console.log(new kakao.maps.LatLng(126.640030500086, 37.4808144576815));

      var imageSrc =
        "https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/markerStar.png";
      var bounds = new kakao.maps.LatLngBounds();
      for (var i = 0; i < posi.length; i++) {
        // 마커 이미지의 이미지 크기 입니다
        var imageSize = new kakao.maps.Size(24, 35);

        // 마커 이미지를 생성합니다
        var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize);

        //   // 마커를 생성합니다
        var marker = new kakao.maps.Marker({
          map: this.map, // 마커를 표시할 지도
          position: posi[i].latlng, // 마커를 표시할 위치
          title: posi[i].title, // 마커의 타이틀, 마커에 마우스를 올리면 타이틀이 표시됩니다
          image: markerImage, // 마커 이미지
        });

        marker.setMap(this.map);
        bounds.extend(posi[i].latlng);
      }

      this.map.setBounds(bounds);
    },

    //////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////

    initMap() {
      var mapContainer = document.getElementById("map"), // 지도를 표시할 div
        mapOption = {
          center: new kakao.maps.LatLng(33.450701, 126.570667), // 지도의 중심좌표
          level: 3, // 지도의 확대 레벨
        };
      var map = new kakao.maps.Map(mapContainer, mapOption);
      this.map = map;
    },
  },
};
</script>

<style scoped>
#map {
  width: 400px;
  height: 400px;
}

.button-group {
  margin: 10px 0px;
}

button {
  margin: 0 3px;
}
</style>
