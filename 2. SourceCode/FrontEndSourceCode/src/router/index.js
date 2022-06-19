import Vue from "vue";
import VueRouter from "vue-router";
import HomeView from "../views/HomeView.vue";
import store from "@/store/index.js";

Vue.use(VueRouter);
const onlyAuthUser = async (to, from, next) => {
  const checkUserInfo = store.getters["checkUserInfo"];
  if (checkUserInfo === null) {
    alert("로그인 후 이용가능합니다...");
    router.push({ name: "signIn" });
  } else {
    next();
  }
};

const routes = [
  {
    path: "/",
    name: "home",
    component: HomeView,
  },
  {
    path: "/user",
    name: "user",
    component: () => import("@/views/MemberView.vue"),
    children: [
      {
        path: "singin",
        name: "signIn",
        component: () => import("@/components/user/MemberLogin.vue"),
      },
      {
        path: "mypage",
        name: "myPage",
        beforeEnter: onlyAuthUser,
        component: () => import("@/components/user/MemberMypage.vue"),
      },
      {
        path: "singup",
        name: "signUp",
        component: () => import("@/components/user/MemberRegister.vue"),
      },
      {
        path: "update",
        name: "MemberUpdate",
        beforeEnter: onlyAuthUser,
        component: () => import("@/components/user/MemberUpdate.vue"),
      },
      // {
      //   path: "searchpwd",
      //   name: "MemberSearchPwd",
      //   component: MemberSearchPwd,
      // },
      // {
      //   path: "searchid",
      //   name: "MemberSearchId",
      //   component: MemberSearchId,
      // },
    ],
  },
  {
    path: "/board",
    name: "board",
    component: () => import("@/views/BoardView.vue"),
    redirect: "/board/list",
    children: [
      {
        path: "list",
        name: "boardList",
        beforeEnter: onlyAuthUser,
        component: () => import("@/components/board/BoardList.vue"),
      },
      {
        path: "write",
        name: "boardRegister",
        beforeEnter: onlyAuthUser,
        component: () => import("@/components/board/BoardRegister.vue"),
      },
      {
        path: "detail/:articleno",
        name: "boardDetail",
        beforeEnter: onlyAuthUser,
        component: () => import("@/components/board/BoardDetail.vue"),
      },
      {
        path: "modify/:articleno",
        name: "boardModify",
        beforeEnter: onlyAuthUser,
        component: () => import("@/components/board/BoardModify.vue"),
      },
      {
        path: "delete/:articleno",
        name: "boardDelete",
        beforeEnter: onlyAuthUser,
        component: () => import("@/components/board/BoardDelete.vue"),
      },
    ],
  },
  {
    path: "/house",
    name: "house",
    component: () => import("@/views/HouseView.vue"),
  },
  {
    path: "/qna",
    name: "qna",
    component: () => import("@/views/QnaView.vue"),
    redirect: "/qna/list",
    // children: [
    //   {
    //     path: "list",
    //     name: "QnaList",
    //     beforeEnter: onlyAuthUser,
    //     component: () => import("@/components/qna/qnaList.vue"),
    //   },
    //   {
    //     path: "write",
    //     name: "QnaWrite",
    //     beforeEnter: onlyAuthUser,
    //     component: () => import("@/components/qna/QnaWrite.vue"),
    //   },
    //   {
    //     path: "detail/:qnano",
    //     name: "QnaView",
    //     beforeEnter: onlyAuthUser,
    //     component: () => import("@/components/qna/QnaView.vue"),
    //   },
    //   {
    //     path: "update/:qnano",
    //     name: "QnaUpdate",
    //     beforeEnter: onlyAuthUser,
    //     component: () => import("@/components/qna/QnaUpdate.vue"),
    //   },
    //   // {
    //   //   path: "delete/:articleno",
    //   //   name: "qnaDelete",
    //   //   beforeEnter: onlyAuthUser,
    //   //   component: () => import("@/components/qna/qnaDelete.vue"),
    //   // },
    //   {
    //     path: "update/:memono",
    //     name: "MemoUpdate",
    //     component: () => import("@/components/qna/MemoUpdate.vue"),
    //   },
    //   {
    //     path: "delete/:memono",
    //     name: "MemoDelete",
    //     component: () => import("@/components/qna/MemoDelete.vue"),
    //   },
    //   {
    //     path: "memowrite/:qnano",
    //     name: "MemoWrite",
    //     component: () => import("@/components/qna/MemoWrite.vue"),
    //   },
    // ],
  },
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
});

export default router;
