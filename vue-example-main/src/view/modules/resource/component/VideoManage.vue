<template>
    <div id="app" class="filelist-page">
        <!-- 标题部分 -->
        <div class="header">
            <h1>文件列表</h1>

            <!-- 隐藏的文件选择框 -->
            <input type="file" ref="fileInput" @change="handleFileChange" style="display: none" />

            <!-- 自定义上传按钮 -->
            <button @click="triggerFileInput">上传文件</button>
        </div>

        <!-- 文件列表 -->
        <ul class="filelist">
            <li v-for="obj in filelist" :key="obj.title" class="filelist-item">
                <span class="file-title">文件名：{{ obj.name }}</span>
                <span class="file-number">文件路径: {{ obj.file }}</span>
                <button @click="copyPath(obj.file)">复制路径</button>
            </li>
        </ul>
    </div>
</template>


<script>
import { getFileListByUserId, upload } from "../../../../api/resource.js";

export default {
    name: "App",

    data() {
        return {
            filelist: [],
            selectedFile: null,  // 存储选中的文件
        };
    },

    mounted() {
        this.fetchTextList();
    },

    methods: {
        // 获取文件列表
        fetchTextList() {
            getFileListByUserId().then((res) => {
                console.log(res.data.data, "obj");
                this.filelist = res.data.data;  // 将返回的文件列表保存到 filelist 中
            });
        },

        // 点击上传按钮时触发文件选择框的点击事件
        triggerFileInput() {
            this.$refs.fileInput.click();  // 触发 file input 的点击事件
        },

        // 处理文件选择
        handleFileChange(event) {
            const file = event.target.files[0];  // 获取用户选择的文件
            if (file) {
                this.selectedFile = file;  // 保存文件到 data 中
                this.uploadFile();  // 上传文件
            }
        },

        // 上传文件
        uploadFile() {
            if (!this.selectedFile) {
                alert("请选择文件！");
                return;
            }

            const formData = new FormData();
            formData.append('file', this.selectedFile);  // 文件
            formData.append('remark', this.selectedFile.name.slice(0, this.selectedFile.name.lastIndexOf('.')));  // 备注

            // 上传
            upload(formData).then((res) => {
                console.log(res.data, "文件上传成功");
                this.fetchTextList(); //更新
                alert("文件上传成功！");
            }).catch((err) => {
                console.error("文件上传失败：", err);
                alert("文件上传失败！");
            });
        },

        // 复制文件路径到剪贴板
        copyPath(path) {
            if (navigator.clipboard) {
                navigator.clipboard.writeText(path).then(() => {
                    alert('路径已复制到剪贴板！');
                }).catch(err => {
                    console.error('复制失败：', err);
                    alert('复制失败，请手动复制！');
                });
            } else {
                alert('该浏览器不支持复制到剪贴板功能！');
            }
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