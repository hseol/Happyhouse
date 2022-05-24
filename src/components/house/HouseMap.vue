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
    // makeList() {
    //   this.houses.forEach(function (item) {
    //     console.log("호출됨");
    //     let tmp = new kakao.maps.LatLng(item.lat, item.lng);
    //     positions.push({ latlng: tmp });
    //   });
    // },
    initMap() {
      const container = document.getElementById("map");
      const options = {
        center: new kakao.maps.LatLng(37.49959, 127.026372),
        level: 5,
      };

      //지도 객체를 등록합니다.
      //지도 객체는 반응형 관리 대상이 아니므로 initMap에서 선언합니다.
      var map = new kakao.maps.Map(container, options);

      var positions = [
        {
          title: "제발",
          latlng: new kakao.maps.LatLng(this.house.lat, this.house.lng),
        },
      ];

      // var positions = [
      //   {
      //     title: "카카오",
      //     latlng: new kakao.maps.LatLng(33.450705, 126.570677),
      //   },
      //   {
      //     title: "생태연못",
      //     latlng: new kakao.maps.LatLng(33.450936, 126.569477),
      //   },
      //   {
      //     title: "텃밭",
      //     latlng: new kakao.maps.LatLng(33.450879, 126.56994),
      //   },
      //   {
      //     title: "근린공원",
      //     latlng: new kakao.maps.LatLng(33.451393, 126.570738),
      //   },
      // ];

      // 마커 이미지의 이미지 주소입니다
      var imageSrc =
        "https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/markerStar.png";

      for (var i = 0; i < positions.length; i++) {
        // 마커 이미지의 이미지 크기 입니다
        var imageSize = new kakao.maps.Size(24, 35);

        // 마커 이미지를 생성합니다
        var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize);

        // 마커를 생성합니다
        var marker = new kakao.maps.Marker({
          map: map, // 마커를 표시할 지도
          position: positions[i].latlng, // 마커를 표시할 위치
          // 마커의 타이틀, 마커에 마우스를 올리면 타이틀이 표시됩니다
          image: markerImage, // 마커 이미지
        });
      }
      marker.setMap(map);
      // var geocoder = new kakao.maps.services.Geocoder();
      // geocoder.addressSearch(function (result, status) {
      //   console.log("들어옴?");
      //   if (status === kakao.maps.services.Status.OK) {
      //     console.log(result);
      //     var coords = new kakao.maps.LatLng(result[0].y, result[0].x);
      //     var marker = new kakao.maps.Marker({
      //       map: map,
      //       position: coords,
      //     });
      //     var infowindow = new kakao.maps.InfoWindow({
      //       content:
      //         '<div style="width:150px;text-align:center;padding:6px 0;">뿅</div>',
      //     });
      //     infowindow.open(map, marker);
      //     map.setCenter(coords);
      //   }
      // });

      // var zoomControl = new kakao.maps.ZoomControl();
      // map.addControl(zoomControl, kakao.maps.ControlPosition.RIGHT);

      // kakao.maps.event.addListener(map, "center_changed", function () {
      //   console.log("지도 중심 좌표 : " + map.getCenter().toString());
      //   //console.log(this.house.lat + "," + this.house.lng);
      // });

      // var lat = this.house.lat;
      // var lng = this.house.lng;
      // var marker = new kakao.maps.Marker({
      //   position: new kakao.maps.LatLng(lat, lng),
      //   map: map,
      // });
      // console.log(this.house.apartmentName);
      // console.log(this.house.lat);
      // console.log(this.house.lng);
      // var infowindow = new kakao.maps.InfoWindow({
      //   content: '<div style="padding:5px;">확인용 클릭</div>',
      // });

      // infowindow.open(map, marker);

      // kakao.maps.event.addListener(marker, "click", function () {
      //   alert("클릭클릭");
      // });
    },
    // addMarker(datas) {
    //   datas.forEach((pos) => {
    //     var marker = new kakao.maps.Marker({
    //       clickable: true,
    //       position: new kakao.maps.LatLng(
    //         parseFloat(pos.lat),
    //         parseFloat(pos.lng)
    //       ),
    //     });
    //     marker.setMap(this.map);
    //     this.markers.push(marker);
    //   });
    // },
    // showPosition() {
    //   if (this.house) {
    //     console.log(this.house.lat + "," + this.house.lng);
    //     var placePosition = new kakao.maps.LatLng(
    //       this.house.lat,
    //       this.house.lng
    //     );
    //     var bounds = new kakao.maps.LatLngBounds();
    //     bounds.extend(placePosition);
    //     this.map.setBounds(bounds);
    //   }
    // },
  },
};
</script>

<style scoped>
#map {
  width: 320px;
  height: 320px;
}
</style>
