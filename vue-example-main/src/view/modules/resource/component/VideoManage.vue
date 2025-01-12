<template>
    <div id="app" class="filelist-page">
        <!-- 标题部分 -->
        <div class="header">
            <h1>文件列表</h1>

            <!-- 隐藏的文件选择框 -->
            <input type="file" ref="fileInput" @change="handleFileChange" style="display: none" />

            <div class="actions">
                <!-- 自定义上传按钮 -->
                <button class="primary-btn" @click="triggerFileInput">上传文件</button>
            </div>
        </div>

        <!-- 文件列表 -->
        <ul class="filelist">
            <li v-for="obj in filelist" :key="obj.title" class="filelist-item">
                <span class="file-title">{{ obj.name }}</span>
                <span class="file-number">{{ obj.file }}</span>
                <!-- 按钮区域 -->
                <div class="button-container">
                    <!-- 复制路径按钮（绿色） -->
                    <button @click="copyPath(obj.file)" class="copy-btn">复制路径</button>
                    <!-- 下载文件按钮（蓝色） -->
                    <button @click="downloadFile(obj.name)" class="primary-btn">下载文件</button>
                    <!-- 删除文件按钮（红色） -->
                    <button @click="deleteFiles(obj.name)" class="delete-btn">删除</button>
                </div>
            </li>
        </ul>
    </div>
</template>

<script>
import { getFileListByUserId, upload, downFile, deleteFile } from "../../../../api/resource.js";

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
                this.fetchTextList(); //更新文件列表
                alert("文件上传成功！");
            }).catch((err) => {
                alert("文件上传失败！");
            });
        },

        // 复制文件路径到剪贴板
        copyPath(path) {
            if (navigator.clipboard) {
                navigator.clipboard.writeText(path).then(() => {
                    alert('路径已复制到剪贴板！');
                }).catch(err => {
                    alert('复制失败，请手动复制！');
                });
            } else {
                alert('该浏览器不支持复制到剪贴板功能！');
            }
        },

        // 文件下载
        downloadFile(filename) {
            const formData = new FormData();
            formData.append("filename", filename);

            downFile(formData).then(response => {
                const blob = new Blob([response.data]);
                const link = document.createElement('a');
                link.href = URL.createObjectURL(blob);
                link.download = filename; // 设置文件下载名称
                link.click(); // 触发下载
                alert('下载成功');
            }).catch(error => {
                alert("文件下载失败！");
                console.error(error);
            });
        },

        // 删除文件
        deleteFiles(filename) {
            if (confirm(`确定要删除文件 "${filename}" 吗？`)) {
                const formData = new FormData();
                formData.append("filename", filename);

                console.log('执行了1');

                deleteFile(formData).then(response => {
                    console.log('执行了');
                    
                    this.fetchTextList(); // 删除后更新文件列表
                    alert('文件删除成功');
                }).catch(error => {
                    alert("文件删除失败！");
                    console.error(error);
                });
            }
        }
    },
};
</script>

<style scoped>
/* 页面布局和背景样式 */
.filelist-page {
    font-family: "Arial", sans-serif;
    background: #f4f7fc;
    padding: 30px;
    max-width: 900px;
    margin: 0 auto;
    border-radius: 12px;
    box-shadow: 0 8px 16px rgba(0, 0, 0, 0.1);
}

/* 标题样式 */
.header {
    text-align: center;
    margin-bottom: 30px;
}

.header h1 {
    font-size: 28px;
    color: #333;
    margin-bottom: 10px;
}

/* 上传按钮样式（蓝色） */
.primary-btn {
    background-color: #007bff;
    color: white;
    padding: 5px 1px;
    border: none;
    border-radius: 8px;
    font-size: 16px;
    cursor: pointer;
    transition: background-color 0.3s;
    min-width: 80px;
}

.primary-btn:hover {
    background-color: #0056b3;
}

/* 文件列表样式 */
.filelist {
    list-style-type: none;
    padding: 0;
    margin: 0;
}

.filelist-item {
    display: flex;
    justify-content: space-between;
    align-items: center;
    padding: 15px;
    margin-bottom: 15px;
    background-color: #ffffff;
    border-radius: 8px;
    box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
    transition: transform 0.2s ease, box-shadow 0.2s ease;
}

.filelist-item:hover {
    transform: translateY(-4px);
    box-shadow: 0 4px 16px rgba(0, 0, 0, 0.2);
}

.file-title {
    font-size: 18px;
    color: #333;
    font-weight: bold;
}

.file-number {
    font-size: 14px;
    color: #777;
    font-weight: normal;
    margin-left: 10px;
}

/* 复制按钮样式（绿色） */
.copy-btn {
    background-color: #28a745;
    color: white;
    border: none;
    padding: 5px 1px;
    border-radius: 8px;
    font-size: 16px;
    cursor: pointer;
    transition: background-color 0.3s;
    min-width: 80px;
}

.copy-btn:hover {
    background-color: #218838;
}

/* 删除按钮样式（红色） */
.delete-btn {
    background-color: #dc3545;
    color: white;
    border: none;
    padding: 5px 1px;
    border-radius: 8px;
    font-size: 16px;
    cursor: pointer;
    transition: background-color 0.3s;
    min-width: 80px;
}

.delete-btn:hover {
    background-color: #c82333;
}

/* 按钮容器样式，使按钮靠右对齐 */
.button-container {
    display: flex;
    justify-content: flex-end;
    /* 按钮靠右对齐 */
    gap: 10px;
}

/* 按钮区域样式 */
.actions {
    display: flex;
    justify-content: center;
    flex-wrap: wrap;
    gap: 10px;
    margin-top: 20px;
}
</style>
