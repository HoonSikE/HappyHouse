<template>
  <div class="bv-example-row text-center">
    <header class="masthead">
      <div class="container position-relative">
        <div class="row justify-content-center">
          <div class="col-xl-6">
            <div class="text-center text-white">
              <!-- Page heading-->
              <h1 class="mb-5">Board Service</h1>
              <!-- Signup form-->
              <!-- * * * * * * * * * * * * * * *-->
              <!-- * * SB Forms Contact Form * *-->
              <!-- * * * * * * * * * * * * * * *-->
              <!-- This form is pre-integrated with SB Forms.-->
              <!-- To make this form functional, sign up at-->
              <!-- https://startbootstrap.com/solution/contact-forms-->
              <!-- to get an API token!-->
            </div>
          </div>
        </div>
      </div>
    </header>
    <router-view></router-view>
  </div>
</template>
<script>
export default {
  name: "BoardView",
};
</script>
<style scoped>
.underline-hotpink {
  display: inline-block;
  /* background: no-repeat url("../../media/examples/lizard.png"); */
  background: linear-gradient(
    180deg,
    rgba(179, 26, 26, 0) 70%,
    rgba(11, 79, 63, 0.3) 30%
  );
}
header.masthead {
  position: relative;
  background-color: #343a40;
  background: url(@/assets/wallpaperbetter.jpg) no-repeat center center;
  background-size: cover;
  padding-top: 8rem;
  padding-bottom: 8rem;
  width: 100%;
}
header.masthead:before {
  content: "";
  position: absolute;
  background-color: #1c375e;
  height: 100%;
  width: 100%;
  top: 0;
  left: 0;
  opacity: 0.5;
}
header.masthead h1,
header.masthead .h1 {
  font-size: 2rem;
}
</style>
