<template>
  <div>
    {{ matchResult }}
    <a @click="remove" href="#">delete</a>
  </div>
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
    //TODO
  }
}
</script>
