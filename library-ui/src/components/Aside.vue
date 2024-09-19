<template>
  <div>
    <el-menu
        style="width: 200px; min-height: calc(100vh - 50px);"
        :default-active="path"
        router
        background-color="#30333c"
        text-color="#fff"
    >
      <el-menu-item index="/dashboard" v-if="user.role === '1'">
        <svg class="icon" aria-hidden="true">
          <use xlink:href="#icondashboard "></use>
        </svg>
        <span>数据统计</span>
      </el-menu-item>
      <el-menu-item index="/book" v-if="user.role === '1'">
        <svg class="icon" aria-hidden="true">
          <use xlink:href="#iconbook "></use>
        </svg>
        <span>书籍管理</span>
      </el-menu-item>
      <el-menu-item index="/book" v-if="user.role === '2'">
        <svg class="icon" aria-hidden="true">
          <use xlink:href="#iconbook "></use>
        </svg>
        <span>图书查询</span>
      </el-menu-item>
      <el-menu-item index="/user" v-if="user.role === '1'">
        <svg class="icon" aria-hidden="true">
          <use xlink:href="#iconreader "></use>
        </svg>
        <span>用户管理</span>
      </el-menu-item>
      <el-menu-item index="/lendrecord" v-if="user.role === '1'">
        <svg class="icon" aria-hidden="true">
          <use xlink:href="#iconlend-record"></use>
        </svg>
        <span>借阅管理</span>
      </el-menu-item>
      <el-menu-item index="/bookwithuser" v-if="user.role === '2'">
        <el-icon>
          <grid/>
        </el-icon>
        <span>借阅查询</span>
      </el-menu-item>
      <el-sub-menu index="2" text-color="#fff">
        <template #title>
          <svg class="icon" aria-hidden="true">
            <use xlink:href="#icon-mingpian "></use>
          </svg>
          <span>个人信息</span>
        </template>
        <el-menu-item index="/person" style="font-color: white">
          <svg class="icon" aria-hidden="true">
            <use xlink:href="#icon-a-bianji1 "></use>
          </svg>
          <span>修改个人信息</span>
        </el-menu-item>
        <el-menu-item index="/password">
          <svg class="icon" aria-hidden="true">
            <use xlink:href="#icon-mima "></use>
          </svg>
          <span>修改密码</span>
        </el-menu-item>
      </el-sub-menu>
    </el-menu>
  </div>
</template>

<script setup>
import { ref } from 'vue';
import { useRoute } from 'vue-router';
import { useUserStore } from '@/stores/userStore.js';

const user = ref({});
const route = useRoute();
const path = ref(route.path);
const userStore = useUserStore();
user.value = userStore.userInfo;

</script>

<style scoped>
.icon {
  width: 30px;
  height: 30px;
  padding-top: 5px;
  padding-right: 10px;
}
</style>
