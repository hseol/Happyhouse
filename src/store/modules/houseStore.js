import {
  sidoList,
  gugunList,
  dongList,
  //houseList,
  aptList,
} from "@/api/house.js";

const houseStore = {
  namespaced: true,
  state: {
    sidos: [{ value: null, text: "시도 선택하세요" }],
    guguns: [{ value: null, text: "구군 선택하세요" }],
    dongs: [{ value: null, text: "읍면동 선택하세요" }],
    houses: [],
    house: null,
    positions: [],
  },

  getters: {},

  mutations: {
    SET_SIDO_LIST: (state, sidos) => {
      sidos.forEach((sido) => {
        state.sidos.push({ value: sido.sidoCode, text: sido.sidoName });
      });
    },
    SET_GUGUN_LIST: (state, guguns) => {
      guguns.forEach((gugun) => {
        state.guguns.push({ value: gugun.gugunCode, text: gugun.gugunName });
      });
    },
    SET_DONG_LIST: (state, dongs) => {
      dongs.forEach((dong) => {
        state.dongs.push({ value: dong.dongCode, text: dong.dong });
      });
    },
    CLEAR_SIDO_LIST: (state) => {
      state.sidos = [{ value: null, text: "시도 선택하세요" }];
    },
    CLEAR_GUGUN_LIST: (state) => {
      state.guguns = [{ value: null, text: "구군 선택하세요" }];
    },
    CLEAR_DONG_LIST: (state) => {
      state.dongs = [{ value: null, text: "읍면동 선택하세요" }];
    },
    SET_HOUSE_LIST: (state, houses) => {
      state.houses = houses;
    },
    SET_DETAIL_HOUSE: (state, house) => {
      state.house = house;
      console.log(state.house);
    },
  },

  actions: {
    getSido: ({ commit }) => {
      sidoList(
        ({ data }) => {
          // console.log(data);
          commit("SET_SIDO_LIST", data);
        },
        (error) => {
          console.log(error);
        }
      );
    },
    getGugun: ({ commit }, sidoCode) => {
      const params = {
        sido: sidoCode,
      };
      gugunList(
        params,
        ({ data }) => {
          // console.log(commit, response);
          commit("SET_GUGUN_LIST", data);
        },
        (error) => {
          console.log(error);
        }
      );
    },
    getDong: ({ commit }, gugunCode) => {
      const params = {
        gugun: gugunCode,
      };
      dongList(
        params,
        ({ data }) => {
          // console.log(commit, response);
          commit("SET_DONG_LIST", data);
        },
        (error) => {
          console.log(error);
        }
      );
    },

    getAptList: ({ commit }, dongCode) => {
      const params = {
        dong: dongCode,
      };
      aptList(
        params,
        (data) => {
          //console.log(data.data);

          commit("SET_HOUSE_LIST", data.data);
        },
        (error) => {
          console.log(error);
        }
      );
    },
    detailHouse: ({ commit }, house) => {
      // 나중에 house.일련번호를 이용하여 API 호출
      commit("SET_DETAIL_HOUSE", house);
    },
    // getHouseList: ({ commit }, dongCode) => {
    //   // vue cli enviroment variables 검색
    //   //.env.local file 생성.
    //   // 반드시 VUE_APP으로 시작해야 한다.
    //   const SERVICE_KEY = process.env.VUE_APP_APT_DEAL_API_KEY;
    //   //   const SERVICE_KEY =
    //   //     "9Xo0vlglWcOBGUDxH8PPbuKnlBwbWU6aO7%2Bk3FV4baF9GXok1yxIEF%2BIwr2%2B%2F%2F4oVLT8bekKU%2Bk9ztkJO0wsBw%3D%3D";
    //   //jgN4xkUPZcv67dkpS9SksO6zpuS%2FHPP5t7Fu3Zco86WrxkGtB1wJD9CwJIiwB2bRtgZdTNa6Wd5DUeRr8kUSng%3D%3D
    //   console.log(dongCode);
    //   const params = {
    //     LAWD_CD: dongCode,
    //     DEAL_YMD: "202110",
    //     serviceKey: decodeURIComponent(SERVICE_KEY),
    //   };
    //   houseList(
    //     params,
    //     (response) => {
    //       console.log(response.data.response.body.items.item);
    //       commit("SET_HOUSE_LIST", response.data.response.body.items.item);
    //     },
    //     (error) => {
    //       console.log(error);
    //     }
    //   );
    // },

    // getAptList: ({ commit }, dongCode) => {
    //   const SERVICE_KEY = process.env.VUE_APP_APT_DEAL_API_KEY;
    //   //console.log(typeof dongCodesub);
    //   const code = dongCode.substring(0, 5);
    //   console.log(code);
    //   const params = {
    //     numOfRows: 30,
    //     LAWD_CD: code,
    //     DEAL_YMD: "202010",
    //     serviceKey: decodeURIComponent(SERVICE_KEY),
    //     dongCode: dongCode,
    //   };
    //   houseList(
    //     params,
    //     (response) => {
    //       console.log(response);
    //       // console.log(
    //       //   response.data.response.body.items.item[0].법정동읍면동코드
    //       // );
    //       const arr = [];
    //       const dong = params.dongCode.substring(5);
    //       const res = response.data.response.body.items.item;
    //       for (var index of res) {
    //         console.log(index.법정동읍면동코드);
    //         console.log(dong);
    //         if (index.법정동읍면동코드 == dong) {
    //           console.log("1");
    //           arr.push(index);
    //         }
    //       }
    //       commit("SET_HOUSE_LIST", arr);
    //     },
    //     (error) => {
    //       console.log(error);
    //     }
    //   );
    // },
    // detailHouse: ({ commit }, house) => {
    //   // 나중에 house.일련번호를 이용하여 API 호출
    //   commit("SET_DETAIL_HOUSE", house);
    // },
  },
};

export default houseStore;
