<template>
  <v-hover v-slot:default="{ hover }">
    <tr>
      <td>{{ matchResult.teamA }} vs {{ matchResult.teamB }}</td>
      <td>{{ matchResult.teamAGoals }} - {{ matchResult.teamBGoals }}</td>
      <td>
        <v-btn
          @click="remove"
          fab
          small
          outlined
          :color="hover ? '#C00000FF' : '#00000000'"
        >
          X
        </v-btn>
      </td>
    </tr>
  </v-hover>
</template>

<script lang="ts">
import { Component, Prop, Vue } from "vue-property-decorator";
import IMatchResult from "../model/IMatchResult";

//TODO CVDW: Cleanup mix of class style components and plain components in projects
@Component
export default class MatchResult extends Vue {
  @Prop() private matchResult!: IMatchResult;
  remove() {
    // this.$emit("remove", this.matchResult.id);
    alert("Not implemented");
    //TODO
  }
}
</script>
