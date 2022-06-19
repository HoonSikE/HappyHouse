<template>
  <b-container class="bv-example-row mt-3">
    <b-row>
      <b-col>
        <b-alert show><h3 style="color: black">글목록</h3></b-alert>
      </b-col>
    </b-row>
    <b-row class="mb-1">
      <div class="col-auto">
        <b-form-select v-model="selected" :options="options"></b-form-select>
        <input
          type="text"
          class=""
          v-model="search"
          placeholder="검색어를 입력해주세요"
          @keypress.enter.prevent="searchArticles"
        />
        <button class="btn btn-primary" @click="searchArticles">검색</button>
      </div>
      <b-col class="text-right">
        <b-button variant="outline-primary" @click="moveWrite()"
          >글쓰기</b-button
        >
      </b-col>
    </b-row>
    <b-row>
      <b-col v-if="articles.length">
        <b-table
          striped
          hover
          id="article-table"
          :items="articles"
          :fields="fields"
          :per-page="perPage"
          :current-page="currentPage"
          @row-clicked="viewArticle"
        >
          <!-- <b-table-simple hover responsive> -->
          <!-- <b-thead head-variant="dark">
            <b-tr>
              <b-th>글번호</b-th>
              <b-th>제목</b-th>
              <b-th>작성자</b-th>
              <b-th>작성일</b-th>
              <b-th>조회수</b-th>
            </b-tr>
          </b-thead> -->
          <!-- 하위 component인 ListRow에 데이터 전달(props) -->
          <!-- <tbody>
            <board-list-item
              v-for="article in articles"
              :key="article.articleno"
              v-bind="article"
            />
          </tbody> -->
          <!-- </b-table-simple> -->
        </b-table>
      </b-col>
      <b-col v-else class="text-center">도서 목록이 없습니다.</b-col>
    </b-row>
    <b-row>
      <b-col class="d-flex justify-content-center">
        <b-pagination
          v-model="currentPage"
          :total-rows="rows"
          :per-page="perPage"
          aria-controls="article-table"
        ></b-pagination> </b-col
    ></b-row>
  </b-container>
</template>

<script>
import http from "@/api/http";
// import BoardListItem from "@/components/board/item/BoardListItem";

export default {
  name: "BoardList",
  components: {
    // BoardListItem,
  },
  data() {
    return {
      articles: [],
      fields: [
        { key: "articleno", label: "글번호", tdClass: "" },
        { key: "subject", label: "제목", tdClass: "tdSubject" },
        { key: "userid", label: "작성자", tdClass: "" },
        { key: "regtime", label: "작성일", tdClass: "" },
        { key: "hit", label: "조회수", tdClass: "" },
      ],
      search: "",
      selected: "",
      options: [
        { value: "", text: "선택" },
        { value: "subject", text: "제목" },
        { value: "userid", text: "작성자" },
      ],
      pg: "1",
      currentPage: 1,
      perPage: 10,
    };
  },
  created() {
    http.get(`/board?pg=${this.pg}`).then(({ data }) => {
      this.articles = data;
      console.log(this.articles);
    });
  },
  methods: {
    moveWrite() {
      this.$router.push({ name: "boardRegister" });
    },
    searchArticles() {
      if (this.selected === "") {
        alert("옵션을 선택해 주세요");
      } //작성자
      else {
        http
          .get(
            `/board/search?pg=${this.pg}&search=${this.search}&type=${this.selected}`,
          )
          .then(({ data }) => {
            this.articles = data;
            console.log(this.articles);
          });
      }
    },
    viewArticle(article) {
      this.$router.push({
        name: "boardDetail",
        params: { articleno: article.articleno },
      });
    },
  },
  computed: {
    rows() {
      return this.articles.length;
    },
  },
};
</script>

<style scope>
* {
  color: white;
}
.tdClass {
  width: 50px;
  text-align: center;
}
.tdSubject {
  width: 300px;
  text-align: left;
}
</style>
