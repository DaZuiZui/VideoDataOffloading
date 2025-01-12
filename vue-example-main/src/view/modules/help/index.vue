<template>
  <div id="app">
    <!-- 顶部组件 -->
    <Top></Top>


    <!-- 主布局 -->
    <div class="main-layout">
          <!-- 左侧导航 -->
          <MainLeft
          :activeLeftNav="activeLeftNav"
          @updateActiveNav="activeLeftNav = $event"
          ></MainLeft>
  
          <!-- 内容区域 -->
          <div class="content">
                  <!-- 导航区域 -->
      <div class="nav">
          <a 
          href="#" 
          :class="{ active: openTemplate === 4 }" 
          @click.prevent="openTemplate = 4"
          >
          使用说明
          </a>
          <a 
          href="#" 
          :class="{ active: openTemplate === 0 }" 
          @click.prevent="openTemplate = 0"
          >
          关于我们
          </a>
          <a 
          href="#" 
          :class="{ active: openTemplate === 1 }" 
          @click.prevent="openTemplate = 1"
          >
          Github
          </a>
          <a 
          href="#" 
          :class="{ active: openTemplate === 3 }" 
          @click.prevent="openTemplate = 3"
          >
          捐款 ❤️
          </a>
      </div>



        <transition name="fade">
           qqq
        </transition>
       
      </div>
    </div>
  </div>
</template>

<script>


import MainLeft from '../../main-left.vue';
import Top from '../../main-top.vue';
 

export default {
  name: 'App',
  components: {
    Top,
    MainLeft
  },
  data() {
    return {
      openTemplate: 4,    // 控制顶部导航内容
      activeLeftNav: 0    // 控制左侧导航内容
    };
  }
};
</script>

<style scoped>
/* 顶部组件样式 */
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

/* 主布局样式 */
.main-layout {
  display: flex;
  height: calc(100vh - 100px); /* 减去顶部组件和导航的高度 */
}

/* 内容区域样式 */
.content {
  flex-grow: 1;
  padding: 20px;
  overflow-y: auto; /* 内容溢出时允许滚动 */
}
</style>
