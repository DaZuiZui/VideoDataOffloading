<!--
   杨易达
   @email: y51288033@outlook.com
           y51288033@gmail.com
-->
<template>
    <div id="app">
      <Top></Top>
      <div>
        <a href="#" @click="openTemplate=0">个人资料</a>
        <a href="#" @click="openTemplate=1">介绍</a>
      </div>
      <div v-if="openTemplate == 0">
        <UserInfo :userinfo="user"></UserInfo>
      </div>
      <div v-if="openTemplate == 1">
        <!-- 引用 UserProfile 组件，并监听自定义事件 -->
        <UserProfile @childEvent="handleChildEvent"></UserProfile>
      </div>
    </div>
  </template>
  
  <script>
  import { add, getUserInfoById } from '@/api/user.js'
  import UserInfo from './component/UserInfo.vue'
  import UserProfile from './component/UserProfile.vue'
  import Top from '../../main-top.vue'
  export default {
    name: 'App',
  
    components: {
      UserInfo, UserProfile, Top
    },
  
    data() {
      return {
        openTemplate: 0,
        user: {
          username: "root",
          password: "admin"
        }
      }
    },
  
    methods: {
      handleChildEvent(dataFromChild) {
        // 接收到子组件传递的数据
        console.log(dataFromChild);
      },
  
      AddHandle() {
        this.AddUserInfoService();
      },
  
      AddUserInfoService() {
        add(this.user).then(res => {
          console.log(res);
        });
      },
  
      FindByIdHandle() {
        let params = {
          "id": 1
        }
  
        getUserInfoById(params).then(res => {
          console.log(res, "findById")
        })
      }
    }
  }
  </script>
  