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
          <b-col md="7"></b-col>
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
          <b-button variant="danger" @click="$router.push({ name: 'mypage' })"
            >수정취소</b-button
          >
        </div>
        <div class="clearfix"></div>
      </b-form>
    </div>
  </b-card>
</template>

<script>
import { modifyMember, renewInfo } from "@/api/member";
//import { mapState } from "vuex";
//const memberStore = "memberStore";

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
    };
  },
  computed: {
    // ...mapState(memberStore, ["userInfo"]),
  },
  created() {
    console.log(this.$route.params.userid);

    renewInfo(
      this.$route.params.userid,
      ({ data }) => {
        this.user = data;
        console.log(this.user);
      },
      (error) => {
        console.log(error);
      },
    );
  },
  methods: {
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
        },
        ({ data }) => {
          let msg = "수정 처리시 문제가 발생했습니다.";
          if (data === "success") {
            msg = "수정이 완료되었습니다.";
          }
          alert(msg);
          // 현재 route를 /list로 변경.

          this.$router.push({ name: "home" });
        },
        (error) => {
          console.log(error);
        },
      );
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
