<template>
  <div class="main-left">
    <ul class="left-nav-list">
      <li
        v-for="(item, index) in leftNavItems"
        :key="index"
        :class="{ active: activeNav === index }"
        @click="handleNavClick(index, item.route)"
      >
        <a href="#" @click.prevent>{{ item.label }}</a>
      </li>
    </ul>
  </div>
</template>

<script>
export default {
  name: "MainLeft",
  data() {
    return {
      leftNavItems: [
        { label: "抖音(✅)", route: "/douyin" },
        { label: "Tiktok(⚠️)", route: "/tiktok" },
        { label: "哔哩哔哩(⚠️)", route: "/bilibili" },
        { label: "小红书(⚠️)", route: "/xiaohongshu" },
        { label: "Youtube(⚠️)", route: "/youtube" },
        { label: "视频资源管理(✅)", route: "/resource" },
        { label: "全平台自动(only Sponsors)", route: "/sponsors" },
        { label: "技术支持(no free)", route: "/support" },
      ],
      activeNav: 0, // 当前选中的导航索引
    };
  },
  watch: {
    // 监听路由变化以更新 activeNav
    $route: {
      immediate: true, // 页面加载时立即触发
      handler(to) {
        const queryNav = parseInt(to.query.activeNav, 10);
        this.activeNav = isNaN(queryNav) ? 0 : queryNav; // 如果参数无效，默认选中第一个
      },
    },
  },
  methods: {
    handleNavClick(index, route) {
      // 跳转路由并附带 activeNav 参数
      this.$router.push({
        path: route,
        query: { activeNav: index },
      });
    },
  },
};
</script>

<style scoped>
.main-left {
  width: 200px;
  background-color: #f8f9fa;
  border-right: 1px solid #ddd;
  padding: 20px 0;
  height: calc(100vh - 100px);
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
