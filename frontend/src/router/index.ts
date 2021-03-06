import Vue from "vue";
import VueRouter from "vue-router";
import Home from "../views/Home.vue";

Vue.use(VueRouter);

const routes = [
  {
    path: "/match_results",
    name: "Match Results",
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () =>
      import(/* webpackChunkName: "results" */ "../views/MatchResultsView.vue")
  },
  {
    path: "/league_table",
    name: "League Table",
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () =>
      import(
        /* webpackChunkName: "leaguetable" */ "../views/LeagueTableView.vue"
      )
  }
];

const router = new VueRouter({
  routes
});

export default router;
