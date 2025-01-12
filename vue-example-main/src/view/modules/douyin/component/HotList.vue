<template>
    <div id="app" class="hotlist-page">
      <!-- 标题部分 -->
      <div class="header">
        <h1>抖音话题热度排行榜</h1>
        <p>
          这里将每1小时刷新一次，或者点击
          <a href="#" @click.prevent="forceRefresh" class="refresh-link">我来进行强制刷新</a>
        </p>
      </div>
  
      <!-- 热度列表 -->
      <ul class="hotlist">
        <li v-for="obj in hotlist" :key="obj.title" class="hotlist-item">
          <span class="hot-title">{{ obj.title }}</span>
          <span class="hot-number">热度: {{ obj.hotnumber }}</span>
        </li>
      </ul>
    </div>
  </template>
  
  <script>
  import { getPopularVideos } from "../../../../api/douyin.js";
  
  export default {
    name: "App",
  
    data() {
      return {
        hotlist: [],
        turnOn: 1
      };
    },
  
    mounted() {
      this.fetchHotlist();
    },
  
    methods: {
      fetchHotlist() {
        getPopularVideos(this.turnOn).then((res) => {
          console.log(res.data.data, "obj");
          this.hotlist = res.data.data;
        });
      },
      forceRefresh() {
        this.fetchHotlist();
        alert("强制刷新成功！");
      },
    },
  };
  </script>
  
  <style scoped>
  /* 页面布局和背景样式 */
  .hotlist-page {
    font-family: "Arial", sans-serif;
    background: #f9f9f9;
    padding: 20px;
    max-width: 800px;
    margin: 0 auto;
    border-radius: 10px;
    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
  }
  
  /* 标题样式 */
  .header {
    text-align: center;
    margin-bottom: 20px;
  }
  
  .header h1 {
    font-size: 24px;
    color: #333;
  }
  
  .header p {
    font-size: 14px;
    color: #555;
  }
  
  .header .refresh-link {
    color: #007bff;
    text-decoration: none;
    font-weight: bold;
    cursor: pointer;
  }
  
  .header .refresh-link:hover {
    text-decoration: underline;
    color: #0056b3;
  }
  
  /* 热度列表样式 */
  .hotlist {
    list-style-type: none;
    padding: 0;
    margin: 0;
  }
  
  .hotlist-item {
    display: flex;
    justify-content: space-between;
    align-items: center;
    padding: 10px 15px;
    margin-bottom: 10px;
    background: #ffffff;
    border-radius: 8px;
    box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
    transition: transform 0.2s ease, box-shadow 0.2s ease;
  }
  
  .hotlist-item:hover {
    transform: translateY(-3px);
    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
  }
  
  .hot-title {
    font-size: 16px;
    font-weight: bold;
    color: #333;
  }
  
  .hot-number {
    font-size: 14px;
    color: #666;
    font-weight: bold;
  }
  </style>
  