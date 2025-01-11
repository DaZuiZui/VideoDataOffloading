<template>
    <div id="app" class="farming-page">
      <!-- 标题和操作按钮 -->
      <div class="header">
        <h1>抖音农田耕种</h1>
        <span style="color: red;"> <b>通知：</b> 单账号数据分析已经并入作品管理 </span>
        <div class="actions">
          <button class="primary-btn"   @click="openModal">开启新的抖音账号进行耕种</button>
          <button class="danger-btn"    @click="clearAccounts">清除所有耕种账户</button>
          <button class="primary-btn"   @click="publishVideo">批量发布视频</button>
          <button class="secondary-btn" @click="testAccounts">账号状态测试</button>
        </div>
      </div>
  
      <!-- 账号数量 -->
      <div class="account-count">
        <p>目前我所管理的账号数量: {{ accounts.length }}</p>
      </div>
  
      <!-- 账号列表 -->
      <div class="account-list">
        <div v-for="(account, index) in accounts" :key="index" class="account-card">
          <img :src="account.avatarUrl" alt="头像" class="account-avatar" />
          <div class="account-info">
            <p>任务编号:  {{ account.WebDriveID }}</p>
            <p>标记:      {{ account.tmpname }}</p>
            <p>DouyinID: {{ account.id }}</p>
            <p>用户名: {{ account.username }}</p>
            <p>个性签名: {{ account.signature }}</p>
            <p>订阅量: {{ account.followingCount }}</p>
            <p>粉丝数: {{ account.fansCount }}</p>
            <p>获赞数: {{ account.likeCount }}</p>
          </div>
          <div> 
            <div class="account-actions">
                <button class="primary-btn" @click="publishVideoForAccount(account.id)">发布视频</button>
                <button class="primary-btn" >作品管理</button>
                <button class="primary-btn" >状态监测</button>
                <button class="danger-btn" @click="deleteAccount(index)">删除</button>
                <button class="secondary-btn" @click="reloginAccount(account.id)">重新登入</button>
            </div>
        </div>
 
        </div>
      </div>
  
      <!-- 弹出框 -->
      <div class="modal" v-if="isModalOpen">
        <div class="modal-content">
          <h2>开启新的抖音账号</h2>
          <div class="modal-body">
            <label for="account-name" style="color: red;">这里的流程应该是弹出一个二维码，然后让用户去扫描登入，扫描好后点我输入好了按钮，然后进行登入判断还有风控判断。但是现在时间紧急直接让用户在服务端扫码登入。正常商业角度来说服务端不会给用户扫描的，会封装一层到客户端扫描</label>
            <label for="account-name" style="color: red;">我没有想好一个很好的处理方案，我觉得如果在这里让用户在输入好他的抖音号，那么我开发层面是不是可以节省更多的业务开发？ </label>
            <label for="account-name">请输入账号名字:<span style="color: red;">（不重要，让你自己标识用的）</span> </label>
            <input
              id="account-name"
              type="text"
              v-model="newAccountName"
              placeholder="输入名字"
            />
            <img
              src="https://via.placeholder.com/200"
              alt="示例图片"
              class="example-image"
            />
          </div>
          <div class="modal-footer">
            <button class="primary-btn" @click="confirmAccount">我输入好了</button>
            <button class="secondary-btn" @click="closeModal">取消</button>
          </div>
        </div>
      </div>
    </div>
  </template>
  
  <script>
  import { start, getFarmerInfo, loginForScan , getFarmerList } from "../../../../api/douyin.js";
  export default {
    name: "App",
    data() {
      return {
        isModalOpen: false, // 控制弹出框是否显示
        newAccountName: "", // 存储用户输入的新账号名字
        accounts: [], // 存储账号信息
        WebDriveID: 0, //导航id
      };
    },
  
    mounted() {
      this.getUserDouYinData();
      this.getFarmerListFun();
    },
  
    
    methods: {
        getFarmerListFun(){
            console.log("??")
            getFarmerList().then(res=>{
                this.accounts = res.data.data;
            });
        },  
      /**
       * 获取抖音用户数据
       */
      getUserDouYinData() {
        // 这里可以加载已有账号的数据
      },
  
      async openModal() {
        this.isModalOpen = true;

        //获取二维码准备工作
        // 启动浏览器
        await start({
            name: "tmp",
            type: 1,
          }).then((res) => {
            this.WebDriveID = res.data.data;
          });
  
          // 登入抖音
          await loginForScan({
            id: this.WebDriveID,
          }).then(res=>{

          });
      },
      closeModal() {
        this.isModalOpen = false;
        this.newAccountName = ""; // 清空输入框
      },
  
      /**
       * 确认提交用户
       * todo: 这里需要改，启动浏览器、登入抖音为A组，
       *       获取用户的信息   为B组
       *       点击我输入好了按钮应该只触发B组
       */
      async confirmAccount() {
        if (this.newAccountName.trim()) {
          let info = {
            tmpname: null,
            WebDriveID: null,
            username: null,
            id: null,
            signature: null,
            followingCount: null,
            fansCount: null,
            likeCount: null,
            avatarUrl: null,
          };

          info.WebDriveID = this.WebDriveID;
          info.tmpname = this.newAccountName;
  
    
  
          // 获取用户的信息
          await getFarmerInfo({ 
            id: info.WebDriveID,
            PCID: this.WebDriveID,
            name: this.newAccountName

        }).then((res) => {
            const data = res.data.data;
            info.username = data.username;
            info.id = data.id;
            info.signature = data.signature;
            info.followingCount = data.followingCount;
            info.fansCount = data.fansCount;
            info.likeCount = data.likeCount;
            info.WebDriveID = data.WebDriveID;
            info.tmpname = data.tmpname;
            info.avatarUrl = data.avatarUrl;
            info.WebDriveID = this.WebDriveID;
            info.tmpname = this.newAccountName;
          });
  
          this.accounts.push(info);
          this.closeModal();
        } else {
          alert("请输入账号名字！");
        }
      },
      clearAccounts() {
        this.accounts = [];
        alert("已清除所有耕种账户！");
      },
      publishVideo() {
        alert("所有账号开始发布视频！");
      },
      publishVideoForAccount(accountId) {
        alert(`为账号 ${accountId} 发布视频！`);
      },
      deleteAccount(index) {
        this.accounts.splice(index, 1);
        alert("账号已删除！");
      },
      reloginAccount(accountId) {
        alert(`重新登入账号 ${accountId}！`);
      },
      testAccounts() {
        alert("账号状态测试！");
      },
    },
  };
  </script>
  
  <style scoped>
  /* 页面布局和背景 */
  .farming-page {
    font-family: "Arial", sans-serif;
    background: #f9f9f9;
    padding: 20px;
    max-width: 800px;
    margin: 0 auto;
    border-radius: 10px;
    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
  }
  
  .header {
    text-align: center;
    margin-bottom: 20px;
  }
  
  .header h1 {
    font-size: 24px;
    color: #333;
  }
  
  .actions {
    display: flex;
    justify-content: center;
    flex-wrap: wrap;
    gap: 10px;
    margin-top: 10px;
  }
  
  .account-count {
    font-size: 16px;
    margin-bottom: 20px;
    color: #555;
  }
  
  /* 账号卡片样式 */
  .account-list {
    display: flex;
    flex-direction: column;
    gap: 15px;
  }
  
  .account-card {
    background: #fff;
    padding: 15px;
    border-radius: 8px;
    box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
    display: flex;
    align-items: center;
    gap: 15px;
  }
  
  .account-avatar {
    width: 80px;
    height: 80px;
    border-radius: 50%;
    object-fit: cover;
    border: 2px solid #ccc;
    box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
  }
  
  .account-info {
    flex: 1;
  }
  
  .account-info p {
    margin: 5px 0;
    font-size: 14px;
    color: #555;
  }
  
  .account-actions {
    margin-top: 10px;
    display: flex;
    gap: 10px;
  }
  
  .status-online {
    color: green;
    font-weight: bold;
  }
  
  .status-offline {
    color: red;
    font-weight: bold;
  }
  
  /* 按钮样式 */
  button {
    padding: 8px 12px;
    font-size: 14px;
    border: none;
    border-radius: 5px;
    cursor: pointer;
    transition: all 0.3s ease;
  }
  
  .primary-btn {
    background: #007bff;
    color: white;
  }
  
  .primary-btn:hover {
    background: #0056b3;
  }
  
  .danger-btn {
    background: #dc3545;
    color: white;
  }
  
  .danger-btn:hover {
    background: #c82333;
  }
  
  .secondary-btn {
    background: #6c757d;
    color: white;
  }
  
  .secondary-btn:hover {
    background: #5a6268;
  }
  
  /* 弹出框样式 */
  .modal {
    position: fixed;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    background: rgba(0, 0, 0, 0.5);
    display: flex;
    justify-content: center;
    align-items: center;
    z-index: 1000;
  }
  
  .modal-content {
    background: #fff;
    padding: 20px;
    border-radius: 10px;
    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
    width: 400px;
    text-align: center;
  }
  
  .modal-content h2 {
    margin-bottom: 20px;
    color: #333;
  }
  
  .modal-body {
    margin-bottom: 20px;
  }
  
  .modal-body label {
    display: block;
    font-size: 14px;
    color: #555;
    margin-bottom: 10px;
  }
  
  .modal-body input {
    width: 100%;
    padding: 8px;
    font-size: 14px;
    border: 1px solid #ccc;
    border-radius: 5px;
    box-sizing: border-box;
    margin-bottom: 10px;
  }
  
  .example-image {
    width: 200px;
    height: auto;
    margin: 10px 0;
  }
  
  .modal-footer {
    display: flex;
    justify-content: space-between;
    gap: 10px;
  }
  </style>
  