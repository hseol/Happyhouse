<template>
  <b-card class="card" title="정보 수정">
    <div>
      <b-form @submit="onSubmit">
        <b-row>
          <b-col md="1"></b-col>
          <b-col md="4">
            <b-form-group label="이름" label-for="username" class="formlabel">
              <b-form-input
                id="username"
                class="formstyle"
                type="text"
                placeholder="이름 입력해주세요"
                v-model="user.username"
              >
              </b-form-input>
            </b-form-group>
          </b-col>
          <b-col class="text-center">
            <b-form-group label="관심지역" label-for="favlo" class="formlabel">
              <span>
                <b-form-select
                  style="width: 120px"
                  v-model="sidoCode"
                  :options="sidos"
                  @change="gugunList"
                ></b-form-select>
                <b-form-select
                  style="width: 120px"
                  v-model="gugunCode"
                  :options="guguns"
                ></b-form-select>
                &nbsp;&nbsp;
                <!-- <b-button @click="addFavlo">관심지역설정!</b-button> -->
              </span>
            </b-form-group>
          </b-col>
          <b-col md="1"></b-col>
        </b-row>

        <b-row>
          <b-col md="1"></b-col>
          <b-col md="5">
            <b-form-group label="아이디" label-for="userid" class="formlabel">
              <b-form-input
                id="userid"
                class="formstyle"
                type="text"
                disabled
                placeholder="아이디입력"
                v-model="user.userid"
              >
              </b-form-input>
            </b-form-group>
          </b-col>

          <b-col md="5">
            <b-form-group
              label="비밀번호"
              label-for="password"
              class="formlabel"
            >
              <b-form-input
                id="userpwd"
                class="formstyle"
                type="password"
                placeholder="비밀번호 입력해주세요"
                v-model="user.userpwd"
              >
              </b-form-input>
            </b-form-group>
          </b-col>
          <b-col md="1"></b-col>
        </b-row>
        <b-row>
          <b-col md="1"></b-col>
          <b-col md="10">
            <b-form-group label="Email" label-for="email" class="formlabel">
              <b-form-input
                id="email"
                class="formstyle"
                type="email"
                placeholder="이메일 입력해주세요"
                v-model="user.email"
              >
              </b-form-input>
            </b-form-group>
          </b-col>
        </b-row>
        <b-row>
          <b-col md="1"></b-col>
          <b-col md="10">
            <b-form-group label="핸드폰" label-for="phone" class="formlabel">
              <b-form-input
                id="phone"
                class="formstyle"
                type="text"
                placeholder="핸드폰 번호 입력해주세요"
                v-model="user.phone"
              >
              </b-form-input>
            </b-form-group>
          </b-col>
        </b-row>
        <b-row>
          <b-col md="1"></b-col>
          <b-col md="10">
            <b-form-group
              label="가입일자"
              label-for="joindate"
              class="formlabel"
            >
              <b-form-input
                id="joindate"
                class="formstyle"
                type="text"
                disabled
                placeholder="가입날짜에러"
                v-model="user.joindate"
              >
              </b-form-input>
            </b-form-group>
          </b-col>
        </b-row>

        <b-row>
          <b-col md="1"></b-col>
          <b-col md="10">
            <b-form-group
              label="자기소개"
              label-for="description"
              class="formlabel"
            >
              <textarea
                id="aboutme"
                class="textarea"
                rows="5"
                placeholder="자기소개가 없습니다."
                v-model="user.aboutme"
              >
                  <!-- class="form-control border-input" -->
                  </textarea
              >
            </b-form-group>
          </b-col>
          <b-col md="1"></b-col>
        </b-row>

        <div class="text-center">
          <b-button class="updateBtn" type="submit" round> 수정하기 </b-button>
          &nbsp;&nbsp;
          <b-button variant="danger" @click="moveMypage">수정취소</b-button>
        </div>

        <div class="clearfix"></div>
      </b-form>
    </div>
  </b-card>
</template>

<script>
import { modifyMember } from "@/api/member";
import { mapState, mapMutations, mapActions } from "vuex";
import FavoriteLocation from "./FavoriteLocation.vue";
const memberStore = "memberStore";
const houseStore = "houseStore";
export default {
  name: "ModifyMyPage",
  data() {
    return {
      user: {
        username: "",
        userid: "",
        userpwd: "",
        email: "",
        phone: "",
        aboutme: "",
      },
      sidoCode: null,
      gugunCode: null,
      dongCode: null,
    };
  },
  conponents: {
    FavoriteLocation,
  },
  computed: {
    ...mapState(memberStore, ["userInfo"]),
    ...mapState(houseStore, ["sidos", "guguns", "dongs"]),
    function() {
      console.log(this.gugunName);
    },
  },
  created() {
    this.user = this.userInfo;
    this.CLEAR_SIDO_LIST();
    this.getSido();
  },
  methods: {
    ...mapMutations(memberStore, ["SET_USER_INFO"]),
    ...mapActions(houseStore, [
      "getSido",
      "getGugun",
      "getDong",
      //"getDong,
    ]),
    ...mapMutations(houseStore, [
      "CLEAR_SIDO_LIST",
      "CLEAR_GUGUN_LIST",
      "CLEAR_DONG_LIST",
      "SET_HOUSE_LIST",
      "SET_DONG_LIST",
    ]),
    gugunList() {
      console.log(this.sidoCode);
      this.CLEAR_GUGUN_LIST();
      this.gugunCode = null;
      if (this.sidoCode) this.getGugun(this.sidoCode);
    },
    dongList() {
      console.log(this.gugunCode);
      this.CLEAR_DONG_LIST();
      this.dongCode = null;
      if (this.gugunCode) this.getDong(this.gugunCode);
    },
    onSubmit(event) {
      event.preventDefault();

      let err = true;
      let msg = "";
      !this.user.username &&
        ((msg = "이름 입력해주세요"),
        (err = false),
        this.$refs.username.focus());
      err &&
        !this.user.userpwd &&
        ((msg = "비밀번호 입력해주세요"),
        (err = false),
        this.$refs.userpwd.focus());
      err &&
        !this.user.email &&
        ((msg = "이메일 입력해주세요"),
        (err = false),
        this.$refs.email.focus());
      err &&
        !this.user.phone &&
        ((msg = "핸드폰 번호 입력해주세요"),
        (err = false),
        this.$refs.phone.focus());
      err &&
        !this.user.aboutme &&
        ((msg = "자기소개가 없습니다."),
        (err = false),
        this.$refs.aboutme.focus());

      if (!err) alert(msg);
      else this.modifyMember();
    },
    modifyMember() {
      console.log(this.user);
      modifyMember(
        {
          username: this.user.username,
          userid: this.user.userid,
          userpwd: this.user.userpwd,
          email: this.user.email,
          phone: this.user.phone,
          aboutme: this.user.aboutme,
          joindate: this.user.joindate,
        },
        ({ data }) => {
          let msg = "수정 처리시 문제가 발생했습니다.";
          if (data === "success") {
            this.SET_USER_INFO(this.user);
            msg = "수정이 완료되었습니다.";
          }
          alert(msg);
          // 현재 route를 /list로 변경.

          this.moveMypage();
        },
        (error) => {
          console.log(error);
        }
      );
    },
    moveMypage() {
      this.$router.push({ name: "mypage" });
    },
  },
};
</script>

<style>
.card {
  border-radius: 10px;
  box-shadow: 0 2px 2px rgba(204, 197, 185, 0.5);
  background-color: #ffffff;
  /* color: $card-black-color; */
  margin-bottom: 20px;
  position: relative;
  z-index: 1;
  border: none;
}
.formstyle {
  background-color: #fffcf5;
  border: medium;
  border-radius: 4px;
  color: #66615b;
  font-size: 14px;
  transition: background-color 0.3s ease 0s;
  padding: 7px 18px;
  height: 40px;
}
.textarea {
  width: 100%;
  background-color: #fffcf5;
  border: medium;
  border-radius: 4px;
  color: #66615b;
  font-size: 14px;
  transition: background-color 0.3s ease 0s;
  padding: 7px 18px;
  outline-color: #cbe4fc;
}
.formlabel {
  color: #9a9a9a;
  text-align: left;
}
.updateBtn {
  background-color: #68b3c8;
  border-color: #68b3c8;
  color: #fff;
  opacity: 1;
}
input[type="text"]:disabled {
  background: #fffcf5;
  font-weight: bolder;
}
</style>
