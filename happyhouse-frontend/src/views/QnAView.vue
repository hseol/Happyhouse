<template>
  <b-container class="bv-example-row mt-3 text-center">
    <div>
      <h3 class="underline-steelpurple">
        <b-icon icon="emoji-laughing"></b-icon>QnA
      </h3>
      <router-view></router-view>
    </div>
  </b-container>
</template>

<script>
export default {};
</script>

<style>
.underline-steelpurple {
  display: inline-block;
  background: linear-gradient(
    180deg,
    rgba(255, 255, 255, 0) 70%,
    rgba(158, 65, 240, 0.3) 30%
  );
}
</style>
