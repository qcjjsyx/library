<script setup>

import Header from "@/components/Header.vue";
import Aside from "@/components/Aside.vue";
</script>

<template>
  <Header />
  <div class="container">
    <Aside />

    <RouterView />
  </div>
</template>

<style scoped>
.container{
  display: flex;
}
</style>