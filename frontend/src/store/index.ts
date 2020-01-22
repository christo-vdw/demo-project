import Vue from "vue";
import Vuex from "vuex";
import Vapi from "vuex-rest-api";

Vue.use(Vuex);

const api = new Vapi({
  baseURL: "http://localhost:8080/api/v1",
  state: {
    matchResults: [],
    teamScores: []
  }
})
  .delete({
    action: "delete",
    property: "matchResults",
    path: ({ id }: any) => `/match_results/"${id}`
  })
  .get({
    action: "fetchMatchResults",
    property: "matchResults",
    path: "/match_results"
  })
  .get({
    action: "fetchTeamScores",
    property: "teamScores",
    path: "/league_table"
  })
  .getStore();

export default new Vuex.Store(api as any);
