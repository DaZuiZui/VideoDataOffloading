<template>
    <div class="main-left">
      <ul class="left-nav-list">
        <li 
          v-for="(item, index) in leftNavItems" 
          :key="index" 
          :class="{ active: activeLeftNav === index }"
          @click="handleNavClick(index)"
        >
          <a href="#" @click.prevent>{{ item.label }}</a>
        </li>
      </ul>
    </div>
  </template>
  
  <script>
  export default {
    name: "MainLeft",
    props: {
      activeLeftNav: {
        type: Number,
        default: 0
      }
    },
    data() {
      return {
        leftNavItems: [
          { label: "抖音(✅)" },
          { label: "Tiktok(⚠️)" },
          { label: "哔哩哔哩(⚠️)" },
          { label: "小红书(⚠️)" },
          { label: "Youtube(⚠️)" },
          { label: "视频资源管理(✅)" },
          { label: "全平台自动(only Sponsors)" },
          { label: "技术支持(no free)" },
        ]
      };
    },
    methods: {
      handleNavClick(index) {
        this.$emit("updateActiveNav", index); // 通知父组件切换选中项
      }
    }
  };
  </script>
  
  <style scoped>
  /* 左导航样式 */
  .main-left {
    width: 200px;
    background-color: #f8f9fa;
    border-right: 1px solid #ddd;
    padding: 20px 0;
    height: calc(100vh - 100px); /* 全屏高度减去顶部组件和导航栏的高度 */
    box-shadow: 2px 0 5px rgba(0, 0, 0, 0.1);
  }
  
  .left-nav-list {
    list-style: none;
    padding: 0;
    margin: 0;
  }
  
  .left-nav-list li {
    margin-bottom: 10px;
  }
  
  .left-nav-list li a {
    text-decoration: none;
    font-size: 16px;
    color: #333;
    padding: 10px 20px;
    display: block;
    border-radius: 4px;
    transition: all 0.3s ease;
  }
  
  .left-nav-list li a:hover {
    background-color: #e9ecef;
    color: #007bff;
  }
  
  .left-nav-list li.active a {
    background-color: #007bff;
    color: white;
    font-weight: bold;
  }
  </style>
  