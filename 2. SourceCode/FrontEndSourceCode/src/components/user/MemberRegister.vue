<template>
  <b-container class="bv-example-row mt-3">
    <b-row>
      <b-col>
        <b-alert variant="secondary" show><h3>회원가입</h3></b-alert>
      </b-col>
    </b-row>
    <b-row>
      <b-col></b-col>
      <b-col cols="8">
        <b-card class="text-center mt-3" style="max-width: 40rem" align="left">
          <b-form @submit.prevent="submitForm" class="text-left">
            <b-form-group label="이름:" label-for="username">
              <b-form-input
                id="name"
                v-model="member.name"
                required
                placeholder="이름 입력...."
              ></b-form-input>
            </b-form-group>
            <b-form-group label="전화번호:" label-for="tel">
              <b-form-input
                id="tel"
                v-model="member.tel"
                required
                placeholder="전화번호 입력...."
              ></b-form-input>
            </b-form-group>
            <b-form-group label="이메일:" label-for="email">
              <b-form-input
                id="email"
                v-model="member.email"
                required
                placeholder="이메일 입력...."
              ></b-form-input>
            </b-form-group>
            <b-form-group label="닉네임:" label-for="nickname">
              <b-form-input
                id="nickname"
                v-model="member.nickname"
                required
                placeholder="닉네임 입력...."
              ></b-form-input>
            </b-form-group>
            <b-form-group label="아이디:" label-for="userid">
              <b-form-input
                id="userid"
                v-model="member.userid"
                required
                placeholder="아이디 입력...."
              ></b-form-input>
            </b-form-group>
            <b-form-group label="비밀번호:" label-for="userpwd">
              <b-form-input
                type="password"
                id="password"
                v-model="member.password"
                required
                placeholder="비밀번호 입력...."
              >
              </b-form-input>
            </b-form-group>

            <b-row>
              <b-col class="d-flex justify-content-center">
                <b-button @click="memberSignup">회원가입</b-button>
              </b-col>
            </b-row>
          </b-form>
        </b-card>
      </b-col>
      <b-col></b-col>
    </b-row>
  </b-container>
</template>

<script>
import http from "@/api/http";

export default {
  name: "MemberRegister",

  data() {
    return {
      member: {
        userid: "",
        password: "",
        name: "",
        nickname: "",
        tel: "",
        email: "",
      },
    };
  },
  methods: {
    memberSignup() {
      http
        .post(`/member/join`, {
          id: this.member.userid,
          password: this.member.password,
          name: this.member.name,
          nickname: this.member.nickname,
          tel: this.member.tel,
          email: this.member.email,
        })
        .then(({ data }) => {
          let msg = "회원가입중 문제가 발생했습니다.";
          if (data != "null") {
            msg = "회원가입 성공!!!";
          }
          alert(msg);
          // 현재 route를 /home으로 변경.
          this.$router.push({ name: "home" });
        });
    },
  },
};
</script>

<style></style>
