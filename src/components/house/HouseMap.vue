<template>
  <div>
    <div style="text-align: left">
      <b-button class="eventBtn" @click="makeList">지도보기!</b-button>
    </div>
    <div id="map" style="width: 100%; height: 500px; margin: auto"></div>
    <!-- <button @click="displayMarker(markerPositions)">marker set 1</button> -->

    <!--값이 실시간으로 바뀌는지 보려면 이렇게 보는게 제일 정확해서 div 하나 팠음.. 최종적으로는 지울 것!!! -->

    <!--필요에 따라서 버튼을 만들어 적용하는 것도 나쁘지 않음... -->
  </div>
</template>
<!--displayMarker(markerPositions1)-->
<script>
import houseStore from "@/store/modules/houseStore";
import { mapState } from "vuex";
//import SmallCard from "@/components./layout/SmallCard";
export default {
  name: "HouseMap",
  components: {
    // SmallCard,
  },

  data() {
    return {
      posi: {},
      markers: [],
      infowindow: null,
      map: null,
      house: {},
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
    /////////////////////////지도 표시한다////////////////////////

    // var iwContent = '<div style="padding:5px;">Hello World!</div>',
    //   iwRemoveable = true;

    // var infowindow = new kakao.maps.InfoWindow({
    //   content: iwContent,
    //   removable: iwRemoveable,
    // });
    //this.setOverlay();

    // setOverlay() {
    //   console.log("호출됨");
    //   var bounds = this.map.getBounds();
    //   console.log(bounds);
    //   this.map.setBounds(bounds);

    //   var content =
    //     '<div class="overlaybox">' +
    //     '    <div class="boxtitle">금주 영화순위</div>' +
    //     '    <div class="first">' +
    //     '        <div class="triangle text">1</div>' +
    //     '        <div class="movietitle text">드래곤 길들이기2</div>' +
    //     "    </div>" +
    //     "    <ul>" +
    //     '        <li class="up">' +
    //     '            <span class="number">2</span>' +
    //     '            <span class="title">명량</span>' +
    //     '            <span class="arrow up"></span>' +
    //     '            <span class="count">2</span>' +
    //     "        </li>" +
    //     "        <li>" +
    //     '            <span class="number">3</span>' +
    //     '            <span class="title">해적(바다로 간 산적)</span>' +
    //     '            <span class="arrow up"></span>' +
    //     '            <span class="count">6</span>' +
    //     "        </li>" +
    //     "        <li>" +
    //     '            <span class="number">4</span>' +
    //     '            <span class="title">해무</span>' +
    //     '            <span class="arrow up"></span>' +
    //     '            <span class="count">3</span>' +
    //     "        </li>" +
    //     "        <li>" +
    //     '            <span class="number">5</span>' +
    //     '            <span class="title">안녕, 헤이즐</span>' +
    //     '            <span class="arrow down"></span>' +
    //     '            <span class="count">1</span>' +
    //     "        </li>" +
    //     "    </ul>" +
    //     "</div>";

    //   var position = new kakao.maps.LatLng(this.house?.lat, this.house?.lng);
    //   var customOverlay = new kakao.maps.CustomOverlay({
    //     position: position,
    //     content: content,
    //     xAnchor: 0.3,
    //     yAnchor: 0.91,
    //   });

    //   customOverlay.setMap(this.map);
    // },

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
  watch: function (houses) {
    this.houses = houses;
    console.log("호출");
    this.makeList();
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
