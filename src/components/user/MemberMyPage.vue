<template>
  <b-row>
    <b-col>
      <b-card class="card" title="내 정보">
        <div>
          <form @submit.prevent>
            <b-row>
              <b-col md="1"></b-col>
              <b-col md="4">
                <b-form-group
                  label="이름"
                  label-for="username"
                  class="formlabel"
                >
                  <div class="formstyle">
                    {{ user.username }}
                  </div>
                </b-form-group>
              </b-col>
              <b-col md="7"></b-col>
            </b-row>
            <b-row>
              <b-col md="1"></b-col>
              <b-col md="5">
                <b-form-group
                  label="아이디"
                  label-for="userid"
                  class="formlabel"
                >
                  <div class="formstyle">
                    {{ user.userid }}
                  </div>
                </b-form-group>
              </b-col>

              <b-col md="5">
                <b-form-group
                  label="비밀번호"
                  label-for="password"
                  class="formlabel"
                >
                  <div class="formstyle">쉿~! 비밀이야</div>
                </b-form-group>
              </b-col>
              <b-col md="1"></b-col>
            </b-row>
            <b-row>
              <b-col md="1"></b-col>
              <b-col md="10">
                <b-form-group label="Email" label-for="email" class="formlabel">
                  <div class="formstyle">
                    {{ user.email }}
                  </div>
                </b-form-group>
              </b-col>
            </b-row>
            <b-row>
              <b-col md="1"></b-col>
              <b-col md="10">
                <b-form-group
                  label="핸드폰"
                  label-for="phone"
                  class="formlabel"
                >
                  <div class="formstyle">
                    {{ user.phone }}
                  </div>
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
                  <div class="formstyle">
                    {{ user.joindate }}
                  </div>
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
                  <div class="textarea">
                    {{ user.aboutme }}
                  </div>
                </b-form-group>
              </b-col>
              <b-col md="1"></b-col>
            </b-row>
            <div class="text-center">
              <b-button class="updateBtn" type="info" round @click="moveModify">
                정보수정
              </b-button>
              &nbsp;&nbsp;
              <b-button variant="danger" href="#">회원탈퇴</b-button>
            </div>
            <div class="clearfix"></div>
          </form>
        </div>
      </b-card>
    </b-col>
    <b-col><todo-view></todo-view></b-col>
  </b-row>
</template>

<script>
import { mapState } from "vuex";
import TodoView from "@/views/TodoView.vue";
const memberStore = "memberStore";

export default {
  name: "MemberMyPage",
  components: {
    TodoView,
  },
  data() {
    return {
      user: {},
    };
  },
  computed: {
    ...mapState(memberStore, ["userInfo"]),
  },
  created() {
    this.user = this.userInfo;
    console.log(this.user);
  },
  methods: {
    moveModify() {
      this.$router.push({ name: "infoModify" });
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
