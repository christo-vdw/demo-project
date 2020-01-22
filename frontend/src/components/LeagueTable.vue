<template>
  <div>
    <v-simple-table>
      <template v-slot:default>
        <thead>
          <tr>
            <th class="text-left">Team</th>
            <th class="text-left">Points</th>
            <th class="text-left">Goal Difference</th>
          </tr>
        </thead>
        <tbody>
          <TeamScore :teamScore="s" v-for="s in teamScores" :key="s.id" />
        </tbody>
      </template>
    </v-simple-table>
  </div>
</template>

<script lang="js">
import { Component, Prop, Vue } from "vue-property-decorator";
import { mapState, mapActions } from "vuex";

import TeamScore from "@/components/TeamScore.vue";
import ITeamScore from "@/model/ITeamScore.ts";

export default {
  name: "App",
  components: {
    TeamScore
  },
  created() {
    this.fetchTeamScores();
  },
  computed: mapState({
    teamScores: (state) => state.teamScores
  }),
  methods: {
    ...mapActions(["fetchTeamScores"]),
  }
};
</script>
