<template>
  <b-container class="bv-example-row mt-3">
    <b-row>
      <b-col>
        <b-alert variant="secondary" show><h3>로그인</h3></b-alert>
      </b-col>
    </b-row>
    <b-row>
      <b-col></b-col>
      <b-col cols="8">
        <b-card class="text-center mt-3" style="max-width: 40rem" align="left">
          <b-form class="text-left">
            <b-alert show variant="danger" v-if="isLoginError"
              >아이디 또는 비밀번호를 확인하세요.</b-alert
            >
            <b-form-group label="아이디:" label-for="userid">
              <b-form-input
                id="userid"
                v-model="member.userid"
                required
                placeholder="아이디 입력...."
                @keyup.enter="confirm"
              ></b-form-input>
            </b-form-group>
            <b-form-group label="비밀번호:" label-for="userpwd">
              <b-form-input
                type="password"
                id="userpwd"
                v-model="member.password"
                required
                placeholder="비밀번호 입력...."
                @keyup.enter="confirm"
              ></b-form-input>
            </b-form-group>
            <b-button
              type="button"
              variant="primary"
              class="m-1"
              @click="confirm"
              >로그인</b-button
            >
            <b-button
              type="button"
              variant="success"
              class="m-1"
              @click="movePage"
              >회원가입</b-button
            >
          </b-form>
        </b-card>
      </b-col>
      <b-col></b-col>
    </b-row>
  </b-container>
</template>

<script>
import { mapState, mapActions } from "vuex";
import http from "@/api/http";

export default {
  name: "MemberLogin",
  data() {
    return {
      isLoginError: false,
      member: {
        userid: "",
        password: "",
      },
    };
  },
  computed: {
    ...mapState(["isLogin", "isLoginId"]),
  },
  methods: {
    ...mapActions(["login", "getMemberInfo"]),
    confirm() {
      http
        .post(
          `/member/login?id=${this.member.userid}&pw=${this.member.password}`,
        )
        .then(({ data }) => {
          let msg = "로그인중 문제가 발생했습니다.";
          // alert(data.id);
          if (data.id != undefined) {
            msg = "로그인 성공!!!";
            // sessionStorage.setItem("loginId", data);
            this.login(true);
            this.getMemberInfo(this.member.userid);
          }
          alert(msg);
          // 현재 route를 /home으로 변경.
          this.$router.push({ name: "home" });
        });
    },
    movePage() {
      this.$router.push({ name: "signUp" });
    },
  },
};
</script>

<style></style>
