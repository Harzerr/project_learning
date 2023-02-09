<template>
  <div>
    <header style="margin-top: fill">
      <el-menu
          :default-active="activeIndex"
          class="el-menu-demo"
          mode="horizontal"
          :ellipsis="false"
          @select="handleSelect"
      >
        <el-menu-item index="0">LOGO</el-menu-item>
        <div class="flex-grow" />
        <el-menu-item index="1">注册</el-menu-item>
        <el-sub-menu index="2">
          <template #title>关于</template>
          <el-menu-item index="2-1">item one</el-menu-item>
          <el-menu-item index="2-2">item two</el-menu-item>
          <el-menu-item index="2-3">item three</el-menu-item>
          <el-sub-menu index="2-4">
            <template #title>item four</template>
            <el-menu-item index="2-4-1">item one</el-menu-item>
            <el-menu-item index="2-4-2">item two</el-menu-item>
            <el-menu-item index="2-4-3">item three</el-menu-item>
          </el-sub-menu>
        </el-sub-menu>


      </el-menu>
    </header>
  </div>
  <div >
    <div style="max-width: 300px; margin: 100px auto; ">
      <h1 style="text-align: center; margin-bottom: 30px;font-size: large">Login</h1>
      <el-form
          :model="user"
          :rules="rules"
          :label-position="labelPostion"
          label-width="100px"
          ref="ruleFormRef">
        <el-form-item prop="username"  label="用户名">
          <el-input v-model="user.username" :prefix-icon="User"  />
        </el-form-item>
        <el-form-item prop="password" label="密码">
          <el-input v-model="user.password"  :prefix-icon="Lock" show-password />
        </el-form-item>
        <el-form-item>
          <el-button type="primary" style="width: 100%" @click="login">登录</el-button>
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>

<script lang="ts" setup>
import {ref, reactive, getCurrentInstance} from 'vue'
import {User, Lock} from '@element-plus/icons-vue'
import {ElMessage, ElMessageBox, ElNotification} from "element-plus";
import request from"../request";
import router from "../router";

const {proxy} = getCurrentInstance()

const labelPosition = ref('right')
const rules = reactive({
  username:[
    { required: true, message: '请输入用户名', trigger: 'blur' },
    { min: 3, max: 12, message: 'Length should be 3 to 12', trigger: 'blur' },
  ],
  password:[
    { required: true, message: '请输入密码', trigger: 'blur' },
    { min: 6, max: 16, message: 'Length should be 6 to 16', trigger: 'blur' },
  ]
})

const user = reactive({})
const login = ()=>{
  //console.log(proxy.$refs.ruleFormRef)
  proxy.$refs.ruleFormRef.validate((valid)=>{
    if(valid){
      //验证成功，向后台发送请求 http://localhost:9090
      //{"code":"200", "msg":"" , "data":null}
      request.post("/user/login", user).then(res =>{
         if(res){
           ElNotification({
             type: 'success',
             message:'登陆成功'
           })
           router.push('/')
         }else{
           ElMessage({
             type:'error',
             message:'登陆失败',
           })
         }
      })
    }
    else{
      ElMessage({
        type:'error',
        message:'用户名或密码错误'
      })
    }
  })
}

const activeIndex = ref('1')
const handleSelect = (key: string, keyPath: string[]) => {
  console.log(key, keyPath)
}
</script>

<style>
.flex-grow {
  flex-grow: 1;
}
</style>