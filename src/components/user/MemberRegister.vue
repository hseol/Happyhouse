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
              <!--  <b-button
                type="button"
                variant="secondary"
                class="m-1"
                @click="checkid"
                >ID 중복 검사</b-button
              > -->
            </b-form-group>

            <b-form-group label="비밀번호 :" label-for="userpwd">
              <b-form-input
                type="password"
                id="userpwd"
                required
                v-model="user.userpwd"
                placeholder="비밀번호 입력해주세요."
              ></b-form-input>
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
            <b-button type="submit" variant="primary" class="m-1"
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
import { insertMember } from "@/api/member";
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
    };
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
        },
      );
    },
    moveHome() {
      this.$router.push({ name: "home" });
    },
    // signup() {
    //   http
    //     .axios({
    //       url: "/user",
    //       method: "post",
    //       data: this.user,
    //     })
    //     .then((res) => {
    //       if (res.data === "success") {
    //         alert("회원 가입 성공");
    //         this.moveHome();
    //       }
    //     })
    //     .catch((err) => {
    //       alert("회원 가입 실패");
    //       console.log(err);
    //     });
    // },
  },
};
</script>

<style></style>
