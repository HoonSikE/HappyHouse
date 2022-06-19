<template>
  <div>
    <b-navbar toggleable="lg" type="dark" variant="dark">
      <b-navbar-toggle target="nav-collapse"></b-navbar-toggle>

      <b-collapse id="nav-collapse" is-nav>
        <b-navbar-nav class="justify-content-space-evenly mr-auto">
          <span>
            <b-nav-item href="#"
              ><router-link :to="{ name: 'home' }" class="nav-link"
                ><b-icon icon="house" font-scale="1.5"></b-icon> 홈</router-link
              ></b-nav-item
            >
          </span>
          <b-nav-item href="#"
            ><router-link :to="{ name: 'board' }" class="nav-link"
              ><b-icon icon="journal" font-scale="1.5"></b-icon>
              게시판</router-link
            ></b-nav-item
          >
          <b-nav-item href="#"
            ><router-link :to="{ name: 'instagram' }" class="nav-link"
              ><b-icon icon="instagram" font-scale="1.5"></b-icon>
              인별그램</router-link
            ></b-nav-item
          >
          <b-nav-item href="#"
            ><router-link :to="{ name: 'house' }" class="nav-link"
              ><b-icon icon="house-fill" font-scale="1.5"></b-icon>
              아파트정보</router-link
            ></b-nav-item
          >
          <b-nav-item href="#"
            ><router-link :to="{ name: 'qna' }" class="nav-link"
              ><b-icon icon="question-square" font-scale="1.5"></b-icon>
              QnA</router-link
            ></b-nav-item
          >
        </b-navbar-nav>

        <b-navbar-nav class="ml-auto">
          <b-nav-item-dropdown right>
            <template #button-content>
              <b-icon icon="people" font-scale="2"></b-icon>
            </template>
            <!-- 로그인 상태 -->
            <b-dropdown-item href="#" v-if="isLogin" @click.prevent="logout()"
              ><b-icon icon="key"></b-icon> 로그아웃</b-dropdown-item
            >
            <b-dropdown-item href="#" v-if="isLogin"
              ><router-link :to="{ name: 'myPage' }" class="link"
                ><b-icon icon="key"></b-icon> 마이페이지</router-link
              ></b-dropdown-item
            >
            <!-- 로그아웃 상태 -->
            <b-dropdown-item href="#" v-if="!isLogin"
              ><router-link :to="{ name: 'signIn' }" class="link"
                ><b-icon icon="key"></b-icon> 로그인</router-link
              ></b-dropdown-item
            >
            <b-dropdown-item href="#" v-if="!isLogin"
              ><router-link :to="{ name: 'signUp' }" class="link"
                ><b-icon icon="person-circle"></b-icon> 회원가입</router-link
              ></b-dropdown-item
            >
          </b-nav-item-dropdown>
        </b-navbar-nav>
      </b-collapse>
    </b-navbar>
  </div>
</template>

<script>
import { mapState, mapMutations } from "vuex";
// import http from "@/api/http";

export default {
  name: "HeaderNaviBar",

  computed: {
    ...mapState(["isLogin", "memberInfo"]),
  },

  methods: {
    ...mapMutations(["SET_IS_LOGIN", "SET_USER_INFO"]),
    logout() {
      this.SET_IS_LOGIN(false);
      this.SET_USER_INFO(null);
      sessionStorage.removeItem("loginId");
      alert("로그아웃!!!");
    },
  },
};
</script>

<style>
.main {
  text-align: center;
}
</style>
