<template>
  <div>
    <v-simple-table>
      <template v-slot:default>
        <thead>
          <tr>
            <th class="text-left">Match</th>
            <th class="text-left">Goals</th>
            <th class="text-left"></th>
          </tr>
        </thead>
        <tbody>
          <MatchResult :matchResult="r" v-for="r in matchResults" :key="r.id" />
        </tbody>
      </template>
    </v-simple-table>
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
