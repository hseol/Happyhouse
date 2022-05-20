<template>
  <b-container class="bv-example-row mt-3">
    <b-row>
      <b-col>
        <b-alert variant="secondary" show><h3>회원가입</h3></b-alert>
      </b-col>
    </b-row>
    <b-row>
      <b-col></b-col>
      <b-col cols="8">
        <b-card class="text-center mt-3" style="max-width: 50rem" align="left">
          <b-form @submit="onSubmit" class="text-left">
            <b-alert show variant="danger" v-if="isLoginError"
              >아이디 또는 비밀번호를 확인하세요.</b-alert
            >
            <b-form-group label="이름 :" label-for="username">
              <b-form-input
                id="username"
                v-model="username"
                required
                placeholder="이름을 입력하세요."
              ></b-form-input>
            </b-form-group>
            <b-form-group label="ID :" label-for="userid">
              <b-form-input
                id="userid"
                v-model="userid"
                required
                placeholder="아이디를 입력하세요."
              ></b-form-input>
              <b-button
                type="button"
                variant="secondary"
                class="m-1"
                @click="checkid"
                >ID 중복 검사</b-button
              >
            </b-form-group>

            <b-form-group label="비밀번호 :" label-for="userpw">
              <b-form-input
                type="password"
                id="userpw"
                v-model="userpw"
                required
                placeholder="비밀번호를 입력하세요."
              ></b-form-input>
            </b-form-group>
            <b-form-group label="이메일 :" label-for="useremail">
              <b-form-input
                type="mail"
                id="useremail"
                v-model="useremail"
                required
                placeholder="이메일을 입력하세요."
              ></b-form-input>
            </b-form-group>
            <b-button
              @click="signup"
              type="submit"
              variant="primary"
              class="m-1"
              >회원가입</b-button
            >
            <b-button @click="moveHome" variant="danger" class="m-1"
              >가입 취소</b-button
            >
          </b-form>
        </b-card>
        <br />
      </b-col>
      <b-col></b-col
    ></b-row>
  </b-container>
</template>

<script>
import http from "@/api/http.js";

export default {
  name: "MemberRegister",
  data() {
    return {
      isLoginError: false,
      user: {
        username: "",
        userid: "",
        userpw: "",
        useremail: "",
      },
    };
  },
  methods: {
    moveHome() {
      this.$router.push({ name: "home" });
    },
    signup() {
      http
        .axios({
          url: "/user",
          method: "post",
          data: this.user,
        })
        .then((res) => {
          if (res.data === "success") {
            alert("회원 가입 성공");
            this.moveHome();
          }
        })
        .catch((err) => {
          alert("회원 가입 실패");
          console.log(err);
        });
    },
  },
};
</script>

<style></style>
