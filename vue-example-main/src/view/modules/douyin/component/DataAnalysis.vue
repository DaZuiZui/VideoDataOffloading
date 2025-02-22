<template>
    <div id="app" class="hotlist-page">
      <!-- 标题部分 -->
      <div class="header">
        <h1>视频矩阵数据统计</h1>
        <p>累计粉丝数：{{ totalFans }} | 互动率：{{ interactionRate }}%</p>
      </div>
  
      <!-- 数据统计折线图 -->
      <div id="line-chart" style="width: 100%; height: 400px;"></div>
      
      <!-- 互动构成占比（环形图） -->
      <div id="pie-chart" style="width: 100%; height: 400px;"></div>
      
      <!-- 每个视频的表现（柱状图） -->
      <div id="bar-chart" style="width: 100%; height: 400px;"></div>
  
      <!-- 切换时间维度 -->
      <div class="time-filter">
        <button @click="updateTimeRange('day')">日</button>
        <button @click="updateTimeRange('week')">周</button>
        <button @click="updateTimeRange('month')">月</button>
      </div>
    </div>
  </template>
  
  <script>
  import * as echarts from 'echarts';
  
  export default {
    name: 'App',
    data() {
      return {
        totalFans: 15000, // 累计粉丝数
        interactionRate: 8.5, // 互动率计算值
        timeRange: 'day',
        chartData: {
          day: { dates: ['02-01', '02-02', '02-03', '02-04', '02-05'], fans: [1200, 1300, 1250, 1400, 1500], views: [10000, 12000, 11000, 12500, 13000], likes: [1500, 1600, 1550, 1700, 1800], comments: [200, 250, 230, 270, 300], shares: [100, 120, 110, 130, 150] }
        }
      };
    },
    mounted() {
      this.initCharts();
    },
    methods: {
      initCharts() {
        this.initLineChart();
        this.initPieChart();
        this.initBarChart();
      },
      initLineChart() {
        const myChart = echarts.init(document.getElementById('line-chart'));
        const data = this.chartData[this.timeRange];
        const option = {
          title: { text: '粉丝增长 & 互动数据' },
          tooltip: { trigger: 'axis' },
          legend: { data: ['粉丝数', '播放量', '点赞数', '评论数', '分享数'] },
          xAxis: { type: 'category', data: data.dates },
          yAxis: { type: 'value' },
          series: [
            { name: '粉丝数', type: 'line', data: data.fans },
            { name: '播放量', type: 'line', data: data.views },
            { name: '点赞数', type: 'line', data: data.likes },
            { name: '评论数', type: 'line', data: data.comments },
            { name: '分享数', type: 'line', data: data.shares }
          ]
        };
        myChart.setOption(option);
      },
      initPieChart() {
        const myChart = echarts.init(document.getElementById('pie-chart'));
        const data = this.chartData[this.timeRange];
        const option = {
          title: { text: '互动构成占比' },
          tooltip: { trigger: 'item' },
          series: [{
            type: 'pie',
            radius: '50%',
            data: [
              { value: data.likes.reduce((a, b) => a + b, 0), name: '点赞' },
              { value: data.comments.reduce((a, b) => a + b, 0), name: '评论' },
              { value: data.shares.reduce((a, b) => a + b, 0), name: '分享' }
            ]
          }]
        };
        myChart.setOption(option);
      },
      initBarChart() {
        const myChart = echarts.init(document.getElementById('bar-chart'));
        const data = this.chartData[this.timeRange];
        const option = {
          title: { text: '视频表现对比' },
          tooltip: { trigger: 'axis' },
          legend: { data: ['播放量', '点赞数', '评论数', '分享数'] },
          xAxis: { type: 'category', data: data.dates },
          yAxis: { type: 'value' },
          series: [
            { name: '播放量', type: 'bar', data: data.views },
            { name: '点赞数', type: 'bar', data: data.likes },
            { name: '评论数', type: 'bar', data: data.comments },
            { name: '分享数', type: 'bar', data: data.shares }
          ]
        };
        myChart.setOption(option);
      },
      updateTimeRange(range) {
        this.timeRange = range;
        this.initCharts();
      }
    }
  };
  </script>
  
  <style scoped>
  .hotlist-page {
    font-family: "Arial", sans-serif;
    padding: 20px;
    max-width: 800px;
    margin: 0 auto;
  }
  .header {
    text-align: center;
    margin-bottom: 20px;
  }
  .header h1 {
    font-size: 24px;
    color: #333;
  }
  .time-filter {
    display: flex;
    justify-content: center;
    margin-top: 10px;
  }
  .time-filter button {
    margin: 0 5px;
    padding: 5px 10px;
    border: none;
    background-color: #007bff;
    color: white;
    cursor: pointer;
    border-radius: 5px;
  }
  .time-filter button:hover {
    background-color: #0056b3;
  }
  </style>
  