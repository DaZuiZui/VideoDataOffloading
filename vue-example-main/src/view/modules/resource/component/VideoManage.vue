<template>
    <div id="app" class="filelist-page">
        <!-- 标题部分 -->
        <div class="header">
            <h1>文件列表</h1>
        </div>

        <!-- 文件列表 -->
        <ul class="filelist">
            <li v-for="obj in filelist" :key="obj.title" class="filelist-item">
                <span class="file-title">{{ obj.title }}</span>
                <span class="file-number">热度: {{ obj.filenumber }}</span>
            </li>
        </ul>
    </div>
</template>

<script>
// import { getPopularVideos } from "../../../../api/douyin.js";

export default {
    name: "App",

    data() {
        return {
            filelist: [],
        };
    },

    mounted() {
        this.fetchTextList();
    },

    methods: {
        fetchTextList() {
            // getPopularVideos().then((res) => {
            //     console.log(res.data.data, "obj");
            //     this.filelist = res.data.data;
            // });
        },
        forceRefresh() {
            this.fetchTextList();
            alert("强制刷新成功！");
        },
    },
};
</script>

<style scoped>
/* 页面布局和背景样式 */
.filelist-page {
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
.filelist {
    list-style-type: none;
    padding: 0;
    margin: 0;
}

.filelist-item {
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

.filelist-item:hover {
    transform: translateY(-3px);
    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
}

.file-title {
    font-size: 16px;
    font-weight: bold;
    color: #333;
}

.file-number {
    font-size: 14px;
    color: #666;
    font-weight: bold;
}
</style>