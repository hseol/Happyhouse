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
            <b-form-group label="이름 :" label-for="username">
              <b-form-input
                id="username"
                v-model="user.username"
                type="text"
                required
                placeholder="이름 입력해주세요."
              ></b-form-input>
            </b-form-group>
            <b-form-group label="ID :" label-for="userid">
              <b-form-input
                id="userid"
                v-model="user.userid"
                type="text"
                required
                placeholder="아이디 입력해주세요."
              ></b-form-input>
              <!-- <span class="badge badge-danger mt-1" v-if="!availableId"
                >아이디는 6자이상이여야 합니다.</span
              > -->
              <b-button
                type="button"
                variant="secondary"
                class="m-1"
                @click="checkId"
                >ID 중복 검사</b-button
              >
            </b-form-group>

            <b-form-group label="비밀번호 :" label-for="userpwd">
              <b-form-input
                type="password"
                id="userpwd"
                required
                v-model="user.userpwd"
                placeholder="비밀번호 입력해주세요."
              ></b-form-input>
              <span v-if="!isValid" class="badge badge-danger mt-1"
                >비밀번호는 영문,숫자,특수문자 혼용으로 8자 이상 20자 이하만
                가능합니다.</span
              >
            </b-form-group>
            <b-form-group label="비밀번호확인 :" label-for="userpwdConfirm">
              <b-form-input
                type="password"
                id="userpwdConfirm"
                required
                v-model="user.userpwdConfirm"
                placeholder="비밀번호 다시 입력해주세요."
              ></b-form-input>
              <span v-if="!pwdConfirm" class="badge badge-danger mt-1"
                >비밀번호가 일치하지 않습니다.</span
              ><span v-else class="badge badge-info mt-1"
                >두 비밀번호가 일치합니다.</span
              >
            </b-form-group>
            <b-form-group label="이메일 :" label-for="email">
              <b-form-input
                type="mail"
                id="email"
                required
                v-model="user.email"
                placeholder="이메일 입력해주세요."
              ></b-form-input>
            </b-form-group>
            <b-form-group label="핸드폰번호 :" label-for="phone">
              <b-form-input
                type="tel"
                id="phone"
                required
                v-model="user.phone"
                placeholder="핸드폰 번호 입력해주세요."
              ></b-form-input>
            </b-form-group>
            <div>
              <b-button type="submit" variant="primary" class="m-1"
                >회원가입</b-button
              >

              <b-button @click="moveHome" variant="danger" class="m-1"
                >가입 취소</b-button
              >
            </div>
          </b-form>
        </b-card>
        <br />
      </b-col>
      <b-col></b-col
    ></b-row>
  </b-container>
</template>

<script>
import { insertMember, checkId } from "@/api/member";
//import { mapState } from "vuex";

export default {
  name: "MemberRegister",
  data() {
    return {
      user: {
        username: "",
        userid: "",
        userpwd: "",
        userConfirm: "",
        email: "",
        phone: "",
      },
      isId: null,
    };
  },
  computed: {
    isValid() {
      let reg_pw = /^(?=.*[a-zA-Z])((?=.*\d)|(?=.*\W)).{8,20}$/;
      //비밀번호 영대소문자 최소 1개의 숫자와 특수문자 포함
      return reg_pw.test(this.user.userpwd);
    },

    pwdConfirm() {
      return this.user.userpwd === this.user.userpwdConfirm;
    },
  },
  methods: {
    checkId() {
      checkId(
        this.user.userid,
        ({ data }) => {
          let msg = "이 아이디는 이미 사용중입니다.";
          if (data === "success") {
            msg = "이 아이디는 사용가능합니다.";
          }
          alert(msg);
        },
        (error) => {
          console.log(error);
        },
      );
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
        !this.user.userid &&
        ((msg = "아이디 입력해주세요"),
        (err = false),
        this.$refs.userid.focus());
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

      if (!err) alert(msg);
      else this.registerMember();
    },
    registerMember() {
      insertMember(
        {
          userid: this.user.userid,
          userpwd: this.user.userpwd,
          username: this.user.username,
          email: this.user.email,
          phone: this.user.phone,
        },
        ({ data }) => {
          let msg = "회원가입실패";
          if (data === "success") {
            msg = "회원가입 성공!";
          }
          alert(msg);
          this.moveHome();
        },
        (error) => {
          console.log(error);
        },
      );
    },
    moveHome() {
      this.$router.push({ name: "home" });
    },
  },
};
</script>

<style></style>
