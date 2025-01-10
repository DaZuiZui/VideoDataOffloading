<!--
   杨易达
   @email: y51288033@outlook.com
           y51288033@gmail.com
-->
<template>
    <div id="app">
        <div class="container">
            <h1 class="message">视频分流，矩阵，智能分析。请遵守当地法律禁止商用</h1>
        </div>
    </div>
</template>

<script>
export default {
    name: 'App',
    data() {
        return {}
    },
    mounted() {},
    methods: {}
}
</script>

<style scoped>
/* 全屏背景设置 */
.container {
    display: flex;
    justify-content: center;
    align-items: center;
    height: 10vh;
    background: linear-gradient(135deg, #6a11cb, #2575fc);
    color: white;
    font-family: 'Arial', sans-serif;
}

/* 文字样式 */
.message {
    font-size: 2rem;
    font-weight: bold;
    text-align: center;
    text-shadow: 0 2px 4px rgba(0, 0, 0, 0.4);
    animation: fadeIn 2s ease-in-out;
}

/* 淡入动画 */
@keyframes fadeIn {
    from {
        opacity: 0;
    }
    to {
        opacity: 1;
    }
}
</style>
