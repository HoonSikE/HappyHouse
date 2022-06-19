<template>
  <b-container class="bv-example-row mt-3">
    <b-row>
      <b-col>
        <b-alert variant="secondary" show><h3>정보 수정</h3></b-alert>
      </b-col>
    </b-row>
    <b-row>
      <b-col></b-col>
      <b-col cols="12">
        <b-jumbotron>
          <template #lead>회원 정보 수정</template>
          <hr class="my-4" />
          <b-container class="mt-4">
            <b-row>
              <b-col cols="2"></b-col>
              <b-col cols="2" align-self="end">닉네임</b-col
              ><b-col cols="4" align-self="start">
                <b-form-input
                  id="nickname"
                  v-model="member.nickname"
                  type="text"
                  required
                  placeholder="닉네임 입력..."
                ></b-form-input>
              </b-col>
              <b-col cols="2"></b-col>
            </b-row>
            <b-row>
              <b-col cols="2"></b-col>
              <b-col cols="2" align-self="end">이름</b-col
              ><b-col cols="4" align-self="start">
                <b-form-input
                  id="name"
                  v-model="member.name"
                  type="text"
                  required
                  placeholder="이름 입력..."
                ></b-form-input>
              </b-col>
              <b-col cols="2"></b-col>
            </b-row>
            <b-row>
              <b-col cols="2"></b-col>
              <b-col cols="2" align-self="end">아이디</b-col
              ><b-col cols="4" align-self="start">
                {{ member.id }}
              </b-col>
              <b-col cols="2"></b-col>
            </b-row>
            <b-row>
              <b-col cols="2"></b-col>
              <b-col cols="2" align-self="end">비밀번호</b-col
              ><b-col cols="4" align-self="start">
                <b-form @submit="onSubmit" @reset="onReset">
                  <b-form-input
                    id="password"
                    v-model="member.password"
                    type="password"
                    required
                    placeholder="비밀번호 입력..."
                  ></b-form-input>
                </b-form>
              </b-col>
              <b-col cols="2"></b-col>
            </b-row>
            <b-row>
              <b-col cols="2"></b-col>
              <b-col cols="2" align-self="end">전화번호</b-col
              ><b-col cols="4" align-self="start">
                <b-form @submit="onSubmit" @reset="onReset">
                  <b-form-input
                    id="password"
                    v-model="member.tel"
                    type="text"
                    required
                    placeholder="비밀번호 입력..."
                  ></b-form-input>
                </b-form>
              </b-col>
              <b-col cols="2"></b-col>
            </b-row>
            <b-row>
              <b-col cols="2"></b-col>
              <b-col cols="2" align-self="end">이메일</b-col
              ><b-col cols="4" align-self="start">
                <b-form @submit="onSubmit" @reset="onReset">
                  <b-form-input
                    id="email"
                    v-model="member.email"
                    type="text"
                    required
                    placeholder="이메일 입력..."
                  ></b-form-input>
                </b-form>
              </b-col>
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

          <b-button
            variant="primary"
            size="sm"
            class="mr-1"
            @click="updateMember"
            >정보수정</b-button
          >
          <b-button type="reset" variant="danger" size="sm" class="mr-1"
            >초기화</b-button
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
        // cdate: "",
        // role: "",
      },
    };
  },
  computed: {
    ...mapState(["isLoginId"]),
  },
  created() {
    http.get(`/member/item?id=${this.isLoginId}`).then(({ data }) => {
      this.member = data;
    });
  },
  methods: {
    ...mapMutations(["SET_IS_LOGIN", "SET_USER_INFO"]),

    onSubmit(event) {
      event.preventDefault();

      let err = true;
      let msg = "";
      !this.member.name &&
        ((msg = "작성자 입력해주세요"), (err = false), this.$refs.name.focus());
      err &&
        !this.member.email &&
        ((msg = "제목 입력해주세요"), (err = false), this.$refs.email.focus());
      err &&
        !this.member.tel &&
        ((msg = "전화번호 입력해주세요"),
        (err = false),
        this.$refs.tel.focus());
      err &&
        !this.member.nickname &&
        ((msg = "닉네임 입력해주세요"),
        (err = false),
        this.$refs.nickname.focus());

      if (!err) alert(msg);
      else this.type === "register" ? this.registMember() : this.updateMember();
    },
    onReset(event) {
      event.preventDefault();
      this.member.name = "";
      this.member.email = "";
      this.moveMypage();
    },

    updateMember() {
      http
        .put(`/member/update`, {
          id: this.member.id,
          password: this.member.password,
          name: this.member.name,
          nickname: this.member.nickname,
          tel: this.member.tel,
          email: this.member.email,
        })
        .then(({ data }) => {
          let msg = "수정중 문제가 발생했습니다.";
          if (data === "success") {
            msg = "수정 성공!!!";
            // sessionStorage.removeItem("loginId");
            this.SET_IS_LOGIN(true);
            this.SET_USER_INFO(this.member.id);
          }
          alert(msg);
          // 현재 route를 /myPage으로 변경.
          this.$router.push({ name: "myPage" });
        });
    },
  },
};
</script>

<style></style>
