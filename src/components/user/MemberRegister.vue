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
                v-model="username"
                type="text"
                required
                placeholder="이름 입력해주세요."
              ></b-form-input>
            </b-form-group>
            <b-form-group label="ID :" label-for="userid">
              <b-form-input
                @keyup="availableId"
                id="userid"
                v-model="user.userid"
                type="text"
                required
                placeholder="아이디 입력해주세요."
              ></b-form-input>
              <span class="badge badge-danger mt-1" v-if="!availableId"
                >아이디는 6자이상이여야 합니다.</span
              >
              <b-button
                type="button"
                variant="secondary"
                class="m-1"
                @click="checkId"
                >ID 중복 검사</b-button
              >
            </b-form-group>
            <div>
              <b-form-group label="비밀번호 :" label-for="userpwd">
                <b-form-input
                  @blur="checkDuplicate"
                  type="password"
                  id="userpwd"
                  required
                  v-model="userpwd"
                  placeholder="비밀번호 입력해주세요."
                ></b-form-input>
                <span class="badge badge-danger mt-1" v-if="!availablePw"
                  >비밀번호는 영문+숫자 8자 이상입니다.</span
                >
              </b-form-group>
              <b-form-group label="이메일 :" label-for="email">
                <b-form-input
                  type="mail"
                  id="email"
                  required
                  v-model="email"
                  placeholder="이메일 입력해주세요."
                ></b-form-input>
              </b-form-group>
              <b-form-group label="핸드폰번호 :" label-for="phone">
                <b-form-input
                  type="tel"
                  id="phone"
                  required
                  v-model="phone"
                  placeholder="핸드폰 번호 입력해주세요."
                ></b-form-input>
              </b-form-group>
              <div v-if="isId != null">
                <b-button type="submit" variant="primary" class="m-1"
                  >회원가입</b-button
                >
              </div>
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
        email: "",
        phone: "",
      },
      isId: null,
    };
  },
  methods: {
    checkId() {
      checkId(
        this.user.userid,
        ({ data }) => {
          let msg = "이 아이디는 이미 사용중입니다.";
          if (data === "success") {
            msg = "이 아이디는 사용가능합니다.";
            this.isId = 1;
          }
          alert(msg);
        },
        (error) => {
          console.log(error);
        }
      );
      console.log(this.user.userid);
    },

    async checkDuplicate() {
      this.availableId = false;
      //이메일 유효성을 검사한다.
      if (this.user.userid.length >= 6) {
        this.availableId = true;
      }
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
      console.log(this.user);
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
        }
      );
    },
    moveHome() {
      this.$router.push({ name: "home" });
    },
  },
};
</script>

<style></style>
