<template>
  <div class="bv-example-row text-center">
    <header class="masthead">
      <div class="container position-relative">
        <div class="row justify-content-center">
          <div class="col-xl-6">
            <div class="text-center text-white">
              <!-- Page heading-->
              <h1 class="mb-5">HAPPY HOUSE</h1>
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
    <b-jumbotron bg-variant="dark" text-variant="white" border-variant="dark">
      <h3 class="underline-steelblue">
        <b-icon icon="house"></b-icon> 트렌디한 내 집 마련, Happy house
      </h3>
      <b-row>
        <b-col></b-col>
        <b-col cols="12">
          <b-jumbotron
            bg-variant="dark"
            text-variant="dark"
            border-variant="dark"
          >
            <template>
              <div>
                <b-carousel
                  id="carousel-1"
                  v-model="slide"
                  :interval="4000"
                  controls
                  indicators
                  background="#ababab"
                  img-width="512"
                  img-height="auto"
                  style="text-shadow: 1px 1px 2px #333"
                  @sliding-start="onSlideStart"
                  @sliding-end="onSlideEnd"
                >
                  <b-carousel-slide
                    img-src="https://ifh.cc/g/91Q6J3.jpg"
                  ></b-carousel-slide>

                  <b-carousel-slide img-src="https://ifh.cc/g/KW1hZT.jpg">
                  </b-carousel-slide>

                  <b-carousel-slide
                    img-src="https://ifh.cc/g/bkRCZ4.jpg"
                  ></b-carousel-slide>

                  <b-carousel-slide
                    img-src="https://ifh.cc/g/erAiKl.jpg"
                  ></b-carousel-slide>
                </b-carousel>
              </div>
            </template>

            <hr class="my-4" />
          </b-jumbotron>
        </b-col>
        <b-col></b-col>
      </b-row>
      <b-row>
        <b-col>
          <b-alert variant="secondary" show><h3>아파트 매매 정보</h3></b-alert>
        </b-col>
      </b-row>
      <div class="mt-3 mr-5 ml-5">
        <kakao-map />
      </div>

      <b-row>
        <b-col cols="4" v-for="(news, index) in News" :key="index">
          <b-card
            :title="news.title | convertString"
            :img-src="require('@/assets/news.jpg')"
            img-alt="Image"
            img-top
            tag="article"
            style="max-width: 20rem"
            class="mb-2"
          >
            <b-card-text>
              {{ news.description | convertString }}
            </b-card-text>

            <b-button :href="news.link" target="_blank" variant="primary"
              >뉴스보기</b-button
            >
          </b-card>
        </b-col>
      </b-row>
    </b-jumbotron>
  </div>
</template>

<script>
import KakaoMap from "@/components/map/KaKaoMap.vue";

export default {
  name: "HomeView",
  props: {
    msg: String,
  },
  components: {
    KakaoMap,
  },
};
</script>

<style scoped>
header.masthead {
  position: relative;
  background: url(@/assets/wallpaperbetter.jpg) no-repeat center center;
  background-size: cover;
  padding-top: 4rem;
  padding-bottom: 4rem;
  width: 100%;
}
.underline-steelblue {
  display: inline-block;
  background: linear-gradient(
    180deg,
    rgba(255, 255, 255, 0) 70%,
    rgba(72, 190, 233, 0.3) 30%
  );
}
</style>
