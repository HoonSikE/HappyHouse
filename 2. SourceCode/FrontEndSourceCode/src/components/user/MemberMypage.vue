<template>
  <b-container class="bv-example-row mt-3">
    <b-row>
      <b-col>
        <b-alert variant="secondary" show><h3>마이페이지</h3></b-alert>
      </b-col>
    </b-row>
    <b-row>
      <b-col></b-col>
      <b-col cols="12">
        <b-jumbotron>
          <template #header>My Page</template>
          <hr class="my-4" />
          <b-container class="mt-8">
            <b-row>
              <b-col cols="2"></b-col>
              <b-col cols="2" align-self="end">이름</b-col
              ><b-col cols="4" align-self="start">{{ member.name }}</b-col>
              <b-col cols="2"></b-col>
            </b-row>
            <b-row>
              <b-col cols="2"></b-col>
              <b-col cols="2" align-self="end">전화번호</b-col
              ><b-col cols="4" align-self="start">{{ member.tel }}</b-col>
              <b-col cols="2"></b-col>
            </b-row>
            <b-row>
              <b-col cols="2"></b-col>
              <b-col cols="2" align-self="end">이메일</b-col
              ><b-col cols="4" align-self="start">{{ member.email }}</b-col>
              <b-col cols="2"></b-col>
            </b-row>
            <b-row>
              <b-col cols="2"></b-col>
              <b-col cols="2" align-self="end">닉네임</b-col
              ><b-col cols="4" align-self="start">{{ member.nickname }}</b-col>
              <b-col cols="2"></b-col>
            </b-row>
            <hr class="my-4" />
            <b-row>
              <b-col cols="2"></b-col>
              <b-col cols="2" align-self="end">아이디</b-col
              ><b-col cols="4" align-self="start">{{ member.id }}</b-col>
              <b-col cols="2"></b-col>
            </b-row>
            <b-row>
              <b-col cols="2"></b-col>
              <b-col cols="2" align-self="end">비밀번호</b-col
              ><b-col cols="4" align-self="start">{{ member.password }}</b-col>
              <b-col cols="2"></b-col>
            </b-row>
            <b-row>
              <b-col cols="2"></b-col>
              <b-col cols="2" align-self="end">가입일</b-col
              ><b-col cols="4" align-self="start">{{ member.udate }}</b-col>
              <b-col cols="2"></b-col>
            </b-row>
          </b-container>
          <hr class="my-4" />

          <b-button variant="primary" size="sm" @click="moveUpdate" class="mr-1"
            >정보수정</b-button
          >
          <b-button variant="danger" size="sm" @click="removeMember()"
            >회원탈퇴</b-button
          >
        </b-jumbotron>
      </b-col>
      <b-col></b-col>
    </b-row>
  </b-container>
</template>

<script>
import { mapState, mapMutations } from "vuex";
import http from "@/api/http";

export default {
  name: "MemberLogin",

  computed: {
    ...mapState(["isLoginId"]),
  },
  data() {
    return {
      member: {
        id: "",
        password: "",
        name: "",
        nickname: "",
        tel: "",
        email: "",
        udate: "",
      },
    };
  },
  created() {
    http.get(`/member/item?id=${this.isLoginId}`).then(({ data }) => {
      this.member = data;
    });
  },
  methods: {
    ...mapMutations(["SET_IS_LOGIN", "SET_USER_INFO"]),
    removeMember() {
      http.delete(`/member/delete?id=${this.isLoginId}`).then(({ data }) => {
        let msg = "회원탈퇴중 문제가 발생했습니다.";
        if (data === "success") {
          msg = "회원탈퇴 성공!!!";
          // sessionStorage.removeItem("loginId");
          this.SET_IS_LOGIN(false);
          this.SET_USER_INFO(null);
        }
        alert(msg);
        // 현재 route를 /home으로 변경.
        this.$router.push({ name: "home" });
      });
    },
    moveUpdate() {
      this.$router.push({ name: "MemberUpdate" });
    },
  },
};
</script>

<style></style>
