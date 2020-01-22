<template>
  <tr>
    <td>{{ teamScore.team }}</td>
    <td>{{ teamScore.score.points }}</td>
    <td>{{ teamScore.score.goalDifference }}</td>
  </tr>
</template>

<script lang="ts">
import { Component, Prop, Vue } from "vue-property-decorator";
import ITeamScore from "../model/ITeamScore";

@Component
export default class TeamScore extends Vue {
  @Prop() private teamScore!: ITeamScore;
}
</script>
