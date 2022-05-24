<template>
  <div>
    <div id="map" style="width: 100%; height: 500px; margin: auto"></div>
    <div>{{ this.$store.state.houses }}</div>
    <!--값이 실시간으로 바뀌는지 보려면 이렇게 보는게 제일 정확해서 div 하나 팠음.. 최종적으로는 지울 것!!! -->

    <button @click="initMap">지도보기</button>
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
      markerPositions1: [
        [33.452278, 126.567803],
        [33.452671, 126.574792],
        [33.451744, 126.572441],
      ],
      posi: [],
      markers: [],
      infowindow: null,
    };
  },
  // props: {
  //   houses: Object,
  // },

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
        "//dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=6a435b67598819acc51c97c1d6599d3b&libraries=services";
      document.head.appendChild(script);
    }
  },

  methods: {
    makeList() {
      var posi = [];
      let lat = 0;
      let lng = 0;

      this.houses.forEach((house) => {
        lat = Number(house.lat);
        lng = Number(house.lng);
        this.posi.push([lat, lng]); //배열형식이 안맞는것 같다...
      });
      console.log(posi);
    },
    set() {
      // this.$store.commit("SET_HOUSE_LIST", this.$store.state.houses);
      this.houses = this.$store.state.houses;
      //console.log(this.houses);
      //console.log(typeof this.houses[0].lat);
      this.makeList();
    },

    //////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////

    initMap() {
      const container = document.getElementById("map");
      const options = {
        center: new kakao.maps.LatLng(37.49959, 127.026372),
        level: 5,
      };
      ///////////////////////////////////////////////////////////////
      ///////////////////////////////////////////////////////////////
      // var posi = [];
      let lat = 0;
      let lng = 0;
      this.houses = this.$store.state.houses;
      /////////////////////////////////////////////////////////////////
      ///////////////////////////////////////////////////////////
      //
      // this.houses.forEach((house) => {
      //   lat = Number(house.lat);
      //   lng = Number(house.lng);
      //   console.log(lat);
      //   console.log(lng);
      //   this.posi.push([lat, lng]);
      // });
      // console.log(this.posi);
      ///////////////////////////////////////////////////////////////
      lat = Number(this.houses[0].lat);
      lng = Number(this.houses[0].lng);
      //posi = [lat, lng];
      console.log(lat);
      console.log(lng);

      var map = new kakao.maps.Map(container, options); // 지도를 생성합니다
      var markerPosition = new kakao.maps.LatLng(lat, lng);
      var marker = new kakao.maps.Marker({
        position: markerPosition,
      });
      marker.setMap(map);
      //////////////////////////////////////////////////////
      ///////////////////////////////////////////////////////////////////

      //지도 객체를 등록합니다.
      //지도 객체는 반응형 관리 대상이 아니므로 initMap에서 선언합니다.
      // var map = new kakao.maps.Map(container, options);
      // if (this.markers.length > 0) {
      //   this.markers.forEach((marker) => marker.setMap(null));
      //   const positions = posi.map(
      //     (position) => new kakao.maps.LatLng(...position)
      //   );
      //   if (positions.length > 0) {
      //     console.log("호출");
      //     this.markers = positions.map(
      //       (position) =>
      //         new kakao.maps.Marker({
      //           map: this.map,
      //           position,
      //         })
      //     );

      //     const bounds = positions.reduce(
      //       (bounds, latlng) => bounds.extend(latlng),
      //       new kakao.maps.LatLngBounds()
      //     );

      //     this.map.setBounds(bounds);
      //   }
      //   // 마커가 표시될 위치입니다
      //   var markerPosition = new kakao.maps.LatLng(lat, lng);

      //   // 마커를 생성합니다
      //   var marker = new kakao.maps.Marker({
      //     position: markerPosition,
      //   });

      //   // 마커가 지도 위에 표시되도록 설정합니다
      //   marker.setMap(map);
      // }
      //////////////////////////////////////////////////
      ////////////////////////////////////////////////////
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
