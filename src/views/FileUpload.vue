<template>
  <div>
    <b-form-group
      id="input-group-3"
      label="프로필사진을 넣어보세요!"
      label-for="input-3"
    >
      <b-row align-v="center">
        <b-col>
          <b-avatar :src="previewImageData" size="6rem"></b-avatar>
          <b-form-file
            v-model="file"
            :state="Boolean(file)"
            placeholder="Choose a file..."
            drop-placeholder="Drop file here..."
            accept=".jpg, .png, .gif"
            @change="onSubmit"
          ></b-form-file>
        </b-col>
      </b-row>

      <div class="mt-3">Selected file: {{ file ? file.name : "" }}</div>
    </b-form-group>
  </div>
</template>

<script>
import axios from "axios";
export default {
  name: "FileUpload",
  data() {
    return {
      file: null,
      uploadImgUrl: [],
    };
  },
  methods: {
    onSubmit(file) {
      if (!file) {
        return;
      }
      const formData = new FormData();
      this.uploadImgUrl = [];
      const reader = new FileReader();
      reader.onload = (e) => {
        this.uploadImgUrl.push({ url: e.target.result });
      };
      reader.readAsDataURL(file);
      axios({
        url: "http://localhost:9999/vue",
        method: "POST",
        headers: { "Content-Type": "multipart/form-data" },
        data: formData,
      })
        .then((res) => {
          console.log(res.data.message);
        })
        .catch((err) => {
          alert(err);
        });
    },

    onReset(e) {
      e.preventDefault();
      // Reset
      this.form.file = "";
      this.form.desc = "";
      this.show = false;
      this.$nextTick(() => {
        this.show = true;
      });
    },
    previewImage() {
      var input = event.target;

      if (input.files && input.files[0]) {
        var reader = new FileReader();
        reader.onload = (e) => {
          this.previewImageData = e.target.result;
          console.log(typeof this.previewImageData);
          this.userInfo.userpic = this.previewImageData;
          this.SET_USER_PROFILE(this.previewImageData);
        };
        reader.readAsDataURL(input.files[0]);
      } else {
        this.previewImageData = null;
      }
    },
  },
};
</script>
