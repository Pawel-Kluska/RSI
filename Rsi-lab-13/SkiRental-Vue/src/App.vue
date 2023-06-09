<template>
  <div class="background"></div>
  <router-view/>
</template>
<script>

</script>

<style>
.background {
  background-size: cover;
  background: url(./assets/background.jpg) no-repeat center center fixed;
  height: 100vh;
  width: 100%;
  position: fixed;
  z-index: -1;
}
</style>
