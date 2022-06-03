<template>
  <b-container>
    <b-col md="1"></b-col>
    <b-card class="card" title="내 정보">
      <div>
        <form @submit.prevent>
          <b-row>
            <b-col md="1"></b-col>
            <b-col md="4">
              <b-form-group label="이름" label-for="username" class="formlabel">
                <div class="formstyle">
                  {{ user.username }}
                </div>
              </b-form-group>
            </b-col>
            <b-col md="6">
              <b-form-group label="핸드폰" label-for="phone" class="formlabel">
                <div class="formstyle">
                  {{ user.phone }}
                </div>
              </b-form-group>
            </b-col>
          </b-row>
          <b-row>
            <b-col md="1"></b-col>
            <b-col md="5">
              <b-form-group label="아이디" label-for="userid" class="formlabel">
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
          <b-row
            ><b-col md="1"></b-col>
            <!--<b-col md="10"><favorite-location /></b-col
            >--></b-row
          >
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
          <span class="text-center">
            <div>
              <b-button class="updateBtn" round v-b-modal.modal-1
                >정보수정</b-button
              >

              <b-modal id="modal-1" title="회원정보수정">
                <form ref="form" @submit.stop.prevent="handleSubmit">
                  <b-form-group
                    label="비밀번호를 입력하세요 "
                    label-for="pwd-input"
                    invalid-feedback="비밀번호 입력 필수!"
                    :state="pwdState"
                  >
                    <b-form-input
                      id="pwd-input"
                      v-model="checkpwd"
                      :state="pwdState"
                      type="password"
                      required
                    ></b-form-input>
                  </b-form-group>
                </form>
              </b-modal>
              &nbsp;&nbsp;
              <b-button variant="danger" @click="deleteMember"
                >회원탈퇴</b-button
              >
            </div>
          </span>
          <div class="clearfix"></div>
        </form>
      </div>
    </b-card>
    <b-col md="1"></b-col>
  </b-container>
</template>

<script>
import { mapMutations, mapState } from "vuex";
import { deleteMember } from "@/api/member";
//import ModalModal from "../layout/ModalModal.vue";
//import FavoriteLocation from "./FavoriteLocation.vue";
const memberStore = "memberStore";

export default {
  name: "MemberMyPage",
  props: {
    userid: String,
  },

  data() {
    return {
      user: {},
      checkpwd: "",
      pwdState: null,
    };
  },
  computed: {
    ...mapState(memberStore, ["userInfo"]),
  },
  created() {
    this.user = this.userInfo;
  },
  methods: {
    ...mapMutations(memberStore, ["SET_IS_LOGIN", "SET_USER_INFO"]),
    moveModify() {
      // this.$router.push({
      //   name: "memberModify",
      //   params: { userid: this.user.userid },
      // });
      this.$router.push({ name: "memberModify" });
    },
    deleteMember() {
      if (
        confirm("정말로 삭제하시겠습니까? 모든 게시글과 정보가 삭제됩니다. ")
      ) {
        deleteMember(this.user.userid, () => {
          this.SET_IS_LOGIN(false);
          this.SET_USER_INFO(null);
          sessionStorage.removeItem("access-token");
          alert("레알루다가 삭제됐당께~~");
          if (this.$route.path != "/") this.$router.push({ name: "home" });
        });
      }
    },

    checkFormValidity() {
      const valid = this.$refs.form.checkValidity();
      this.pwdState = valid;
      return valid;
    },
    resetModal() {
      this.checkpwd = "";
      this.pwdState = null;
    },
    handleOk(bvModalEvent) {
      // Prevent modal from closing
      bvModalEvent.preventDefault();
      // Trigger submit handler
      this.handleSubmit();
    },
    handleSubmit() {
      //서브밋 핸들러군
      // Exit when the form isn't valid
      if (!this.checkFormValidity()) {
        return;
      }
      // Push the name to submitted names
      //여기서 다 맞다면 행동해야할 것!
      if (this.checkpwd === this.userInfo.userpwd) {
        //수정하기로 갈것
        alert("확인되었습니다.");
        this.moveModify();
      } else {
        alert("땡!");
        // Hide the modal manually
        this.$nextTick(() => {
          this.$bvModal.hide("modal-prevent-closing");
        });
      }
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
#modal-1 {
  font-family: "GangwonEdu_OTFBoldA";
}
</style>
