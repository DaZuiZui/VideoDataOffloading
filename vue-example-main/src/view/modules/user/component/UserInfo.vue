<!--
   杨易达
   @email: y51288033@outlook.com
           y51288033@gmail.com
-->
<template>
    <div id="app">
        userinfo
    </div>
  </template>
  
<script>
    export default {
        props: ["userinfo"],

        name: 'App',

        data() {
            return {
        
            }
        },  

        mounted() {
            console.log("UserInfo component mounted!");
            console.log("userinfo prop:", this.userinfo);
        },

        methods: {

  

 
        }
    }
</script>
    
  