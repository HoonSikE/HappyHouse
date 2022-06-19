<template>
  <div class="content">
    <div class="md-layout">
      <div class="md-layout-item md-medium-size-100 md-size-66">
        <div
          class="md-card md-theme-default"
          style="width: 80%; position: absolute; top: 10%; left: 10%"
        >
          <!-- 글상세조회 헤더부분 -->
          <md-card-header data-background-color="green">
            <h4 class="title" style="text-align: center">공지사항 상세 조회</h4>
            <p class="category" style="text-align: center">내용을 읽어보세요</p>
          </md-card-header>
          <md-card-content>
            <simple-table table-header-color="green"></simple-table>
          </md-card-content>
          <!-- 내용 보여지는 부분 -->
          <div>
            <md-table>
              <md-table-row
                slot="md-table-row"
                class="md-layout-item"
                style="font-weight: bold"
              >
                <md-table-cell style="width: 8%"></md-table-cell>
                <md-table-cell style="width: 20%">Title</md-table-cell>
                <md-table-cell style="width: 23%">Writer</md-table-cell>
                <md-table-cell style="width: 23%">Description</md-table-cell>
                <md-table-cell>Date</md-table-cell>
              </md-table-row>

              <md-table-row slot="md-table-row">
                <md-table-cell></md-table-cell>
                <md-table-cell>{{ user.title }}</md-table-cell>
                <md-table-cell>{{ user.writerName }}</md-table-cell>
                <md-table-cell>{{ user.description }}</md-table-cell>
                <md-table-cell>{{ user.dateTime | formatDate }}</md-table-cell>
              </md-table-row>
            </md-table>
          </div>
          <md-card-content>
            <div class="md-layout"></div>
          </md-card-content>
          <!-- 버튼 있는 곳 -->
          <div class="md-layout-item md-size-100 text-right">
            <sidebar-link
              :to="{ name: 'noticemodify', params: { userNo: user.noticeId } }"
              ><md-button class="md-raised md-success"
                >글 수정</md-button
              ></sidebar-link
            >
            <sidebar-link
              :to="{ name: 'noticedelete', params: { userNo: user.noticeId } }"
              ><md-button class="md-raised md-success"
                >글 삭제</md-button
              ></sidebar-link
            >
            <sidebar-link to="/dashboard/table"
              ><md-button class="md-raised md-success"
                >목록으로</md-button
              ></sidebar-link
            >
          </div>
          <!-- 여기가 댓글 부분 -->
          <div id="form-commentInfo">
            <div id="comment-count">댓글 <span id="count">0</span></div>
            <input id="comment-input" placeholder="댓글을 입력해 주세요." />
            <md-button @click="sendreply" id="submit">등록</md-button>
          </div>
          <div id="comments"></div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import axios from "axios";
import moment from "moment";

export default {
  name: "NoticeDetail",
  data: function () {
    return {
      user: {},
    };
  },
  components: {},
  created() {
    console.log(this.$route.params.userNo);
    axios
      .post("http://localhost:9999/notice/hits", {
        refreshToken: sessionStorage.getItem("refreshToken"),
        noticeId: this.$route.params.userNo,
      })
      .then(({ data }) => {
        console.log(data);
      });
    axios
      .get(`http://localhost:9999/notice/${this.$route.params.userNo}`)
      .then(({ data }) => {
        console.log(data);
        this.user = data;
      });
  },
  filters: {
    formatDate(regtime) {
      return moment(new Date(regtime)).format("YYYY.MM.DD HH:mm:ss");
    },
  },
  methods: {
    // 여기서 해야겟지
    // 다시 만드는것
    // 등록
    sendreply() {
      console.log(this.writerId);
      axios
        .post("http://localhost:9999/reply", {
          noticeId: this.$route.params.userNo,
          writerId: this.writerId,
          writerName: this.writerName,
          title: this.title,
          description: this.description,
        })
        .then(({ data }) => {
          console.log(data);
        });
    },
    showComment() {
      const userName = document.createElement("div");
    },
    // 삭제
  },
};
</script>

<style scoped>
#form-commentInfo {
  width: 100%;
}
#comments {
  margin-top: 10px;
}
#comment-count {
  margin-left: 10%;
  margin-bottom: 10px;
}

#comment-input {
  margin-left: 10%;
  margin-top: 1%;
  margin-right: 5%;
  width: 70%;
  height: 3.5em;
}
#submit {
  color: rgb(86, 173, 80);
  width: 5.5em;
  height: 3.2em;
  font-size: 15px;
  font-weight: bold;
  color: aliceblue;
}
</style>
