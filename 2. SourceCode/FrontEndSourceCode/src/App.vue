<template>
  <div id="app">
    <navi-bar />
    <router-view />
    <footer-bar />
  </div>
</template>

<script>
import NaviBar from "@/components/layout/HeaderNaviBar.vue";
import FooterBar from "@/components/layout/FooterNaviBar.vue";

export default {
  name: "App",
  data() {
    return {
      isDarkModeEnabled: true,
    };
  },
  components: {
    NaviBar,
    FooterBar,
  },
  methods: {},
};
</script>

<style>
/* a {
  text-decoration: none;
}

a:hover {
  text-decoration: none;
  font-weight: bold;
  color: rgb(146, 146, 146);
}
a.router-link-exact-active {
  color: #757575;
} */

#app {
  min-height: 100%;
  position: relative;
  background-color: #303030;
  color: #757575;
}
</style>
