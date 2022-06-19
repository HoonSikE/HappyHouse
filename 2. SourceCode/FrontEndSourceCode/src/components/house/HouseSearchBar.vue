<template>
  <b-row class="mt-4 mb-4 text-center">
    <!-- <b-col class="sm-3">
      <b-form-input
        v-model.trim="dongCode"
        placeholder="동코드 입력...(예 : 11110)"
        @keypress.enter="sendKeyword"
      ></b-form-input>
    </b-col>
    <b-col class="sm-3" align="left">
      <b-button variant="outline-primary" @click="sendKeyword">검색</b-button>
    </b-col> -->
    <b-col class="sm-3">
      <b-form-select
        v-model="sido"
        :options="sidos"
        @change="gugunList"
      ></b-form-select>
    </b-col>
    <b-col class="sm-3">
      <b-form-select
        v-model="gugun"
        :options="guguns"
        @change="searchApt"
      ></b-form-select>
    </b-col>
  </b-row>
</template>

<script>
import { mapState, mapActions, mapMutations } from "vuex";

export default {
  name: "HouseSearchBar",
  data() {
    return {
      sido: null,
      gugun: null,
    };
  },
  computed: {
    ...mapState(["sidos", "guguns", "houses"]),
    // sidos() {
    //   return this.$store.state.sidos;
    // },
  },
  created() {
    // this.$store.dispatch("getSido");
    // this.sidoList();
    this.CLEAR_SIDO_LIST();
    this.getSido();
  },
  methods: {
    ...mapActions(["getSido", "getGugun", "getHouseList"]),
    ...mapMutations(["CLEAR_SIDO_LIST", "CLEAR_GUGUN_LIST"]),
    ...mapState(["sidoName", "gugunName"]),
    // sidoList() {
    //   this.getSido();
    // },
    gugunList() {
      console.log(this.sido.sidoName);
      this.$store.state.sidoName = this.sido.sidoName;
      console.log(this.$store.state.sidoName);
      // console.log(this.sidoName);
      this.CLEAR_GUGUN_LIST();
      this.gugunCode = null;
      if (this.sido.sidoCode) this.getGugun(this.sido.sidoCode);
    },
    searchApt() {
      console.log(this.gugun);
      this.$store.state.gugunName = this.gugun.gugunName;
      console.log(this.$store.state.gugunName);
      if (this.gugun.gugunCode) this.getHouseList(this.gugun.gugunCode);
    },
  },
};
</script>

<style></style>
