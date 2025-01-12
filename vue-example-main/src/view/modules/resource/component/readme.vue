<template>
    <div id="app" class="donation-page">
      <!-- 使用说明标题 -->
      <div class="header">
        <h1>使用说明</h1>
        <p>如果你觉得我的产品对你有帮助，请为我捐款，我将为您更新。</p>
      </div>
  
      <!-- 捐款选项 -->
      <div class="donation-section">
        <!-- 微信收款 -->
        <div class="donation-option">
          <h2>微信收款</h2>
          <img src="../../../../../static/image/pay/wechat.jpg" alt="微信收款" class="donation-image">
        </div>
  
        <!-- 微信港元收款 -->
        <div class="donation-option">
          <h2>微信港元收款</h2>
          <img src="../../../../../static/image/pay/wechatHK.jpg" alt="微信港元收款" class="donation-image">
        </div>
  
        <!-- 支付宝港元收款 -->
        <div class="donation-option">
          <h2>支付宝收款</h2>
          <img src="../../../../../static/image/pay/alipay.jpg" alt="支付宝港元收款" class="donation-image">
        </div>
      </div>
    </div>
  </template>
  
  <script>
  export default {
    name: "App",
    data() {
      return {};
    },
    mounted() {},
    methods: {}
  };
  </script>
  
  <style scoped>
  /* 页面整体样式 */
  .donation-page {
    font-family: "Arial", sans-serif;
    background: #f9f9f9;
    padding: 20px;
    max-width: 800px;
    margin: 0 auto;
    border-radius: 10px;
    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
    text-align: center;
  }
  
  /* 标题和说明样式 */
  .header h1 {
    font-size: 24px;
    color: #333;
    margin-bottom: 10px;
  }
  
  .header p {
    font-size: 16px;
    color: #555;
    margin-bottom: 20px;
  }
  
  /* 捐款部分样式 */
  .donation-section {
    display: flex;
    flex-wrap: wrap;
    justify-content: center;
    gap: 20px;
  }
  
  /* 单个捐款选项样式 */
  .donation-option {
    background: #fff;
    border: 1px solid #ddd;
    border-radius: 10px;
    padding: 15px;
    width: 250px;
    box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
    text-align: center;
    transition: transform 0.3s ease, box-shadow 0.3s ease;
  }
  
  .donation-option:hover {
    transform: translateY(-5px);
    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
  }
  
  /* 捐款标题样式 */
  .donation-option h2 {
    font-size: 18px;
    color: #333;
    margin-bottom: 15px;
  }
  
  /* 图片样式 */
  .donation-image {
    max-width: 100%;
    height: auto;
    border-radius: 5px;
    box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
  }
  </style>
  