<!--
   杨易达
   @email: y51288033@outlook.com
           y51288033@gmail.com
-->
<template>
  <div id="app">
    <!-- 顶部组件 -->
    <Top></Top>

    <!-- 导航区域 -->
    <div class="nav">
      <a 
        href="#" 
        :class="{ active: openTemplate === 0 }" 
        @click.prevent="openTemplate = 0"
      >
        视频热度
      </a>
      <a 
        href="#" 
        :class="{ active: openTemplate === 1 }" 
        @click.prevent="openTemplate = 1"
      >
        抖音流水化耕田
      </a>
    </div>

    <!-- 内容区域 -->
    <div class="content">
      <transition name="fade">
        <HotList v-if="openTemplate === 0"></HotList>
      </transition>
      <transition name="fade">
        <Farming v-if="openTemplate === 1"></Farming>
      </transition>
    </div>
  </div>
</template>

<script>
import { add, getUserInfoById } from '@/api/user.js';
import UserInfo from './component/UserInfo.vue';
import UserProfile from './component/UserProfile.vue';
import Top from '../../main-top.vue';

export default {
  name: 'App',

  components: {
    HotList, 
    Farming, 
    Top
  },

  data() {
    return {
      openTemplate: 0,
      user: {
        username: "root",
        password: "admin"
      }
    };
  },

  methods: {}
};
</script>

<style scoped>
/* 顶部组件样式（如果需要） */
#app {
  font-family: 'Arial', sans-serif;
  color: #333;
}

/* 导航栏样式 */
.nav {
  display: flex;
  justify-content: center;
  background: #f8f9fa;
  padding: 10px 0;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
  margin-bottom: 20px;
}

.nav a {
  margin: 0 15px;
  text-decoration: none;
  font-size: 16px;
  color: #007bff;
  transition: color 0.3s ease, border-bottom 0.3s ease;
  padding-bottom: 2px;
}

.nav a:hover {
  color: #0056b3;
  border-bottom: 2px solid #0056b3;
}

.nav a.active {
  color: #0056b3;
  font-weight: bold;
  border-bottom: 2px solid #0056b3;
}

/* 内容区样式 */
.content {
  padding: 20px;
}

/* 组件切换动画 */
.fade-enter-active, .fade-leave-active {
  transition: opacity 0.5s ease;
}

.fade-enter-from, .fade-leave-to {
  opacity: 0;
}
</style>
