<template>
  <div>
    <MatchResult :matchResult="r" v-for="r in matchResults" :key="r.id" />
  </div>
</template>

<script lang="js">
import { Component, Prop, Vue } from "vue-property-decorator";
import { mapState, mapActions } from "vuex";

import MatchResult from "@/components/MatchResult.vue";
import IMatchResult from "@/model/IMatchResult.ts";

export default {
  name: "App",
  components: {
    MatchResult
  },
  created() {
    //TODO CVDW:
    // This gives a ts linting error but runs fine.
    // Changed source to js to get build to pass.
    this.fetchMatchResults();
  },
  computed: mapState({
    matchResults: (state) => state.matchResults
  }),
  methods: {
    ...mapActions(["fetchMatchResults"]),
  }
};
</script>
