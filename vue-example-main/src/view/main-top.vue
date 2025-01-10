<!--
   杨易达
   @email: y51288033@outlook.com
           y51288033@gmail.com
-->
<template>
    <div id="app">
        Top
    </div>
  </template>
  
<script>
    export default {
 

        name: 'App',

        data() {
            return {
        
            }
        },  

        mounted() {
            console.log("用户的简历")
            this.$emit('childEvent', "你好我给我的父组件值");
        },

        methods: {

 
        }
    }
</script>
    
  