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
        <!--        <el-radio-group v-model="isCollapse" style="margin-bottom: 10px">-->
        <!--          <el-radio-button :label="false">expand</el-radio-button>-->
        <!--          <el-radio-button :label="true">collapse</el-radio-button>-->
        <!--        </el-radio-group>-->
        <div class="flex-grow"/>
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
  <el-row>
    <div class="grid-content ep-bg-purple">

      <el-menu
          default-active="2"
          class="el-menu-vertical-demo"
          :collapse="isCollapse"
          @open="handleOpen"
          @close="handleClose"
      >
        <el-sub-menu index="1">
          <template #title>
            <el-icon>
              <location/>
            </el-icon>
            <span>Navigator One</span>
          </template>
          <el-menu-item-group>
            <template #title><span>Group One</span></template>
            <el-menu-item index="1-1">item one</el-menu-item>
            <el-menu-item index="1-2">item two</el-menu-item>
          </el-menu-item-group>
          <el-menu-item-group title="Group Two">
            <el-menu-item index="1-3">item three</el-menu-item>
          </el-menu-item-group>
          <el-sub-menu index="1-4">
            <template #title><span>item four</span></template>
            <el-menu-item index="1-4-1">item one</el-menu-item>
          </el-sub-menu>
        </el-sub-menu>
        <el-menu-item index="2">
          <el-icon>
            <icon-menu/>
          </el-icon>
          <template #title>Navigator Two</template>
        </el-menu-item>
        <el-menu-item index="3" disabled>
          <el-icon>
            <document/>
          </el-icon>
          <template #title>Navigator Three</template>
        </el-menu-item>
        <el-menu-item index="4">
          <el-icon>
            <setting/>
          </el-icon>
          <template #title>Navigator Four</template>
        </el-menu-item>
        <el-menu-item index="5" @click="isCollapse = !isCollapse">
          <el-icon>
            <ArrowRightBold/>
          </el-icon>
          <template #title>收缩</template>
        </el-menu-item>
      </el-menu>
    </div>
    <!--    </el-col>-->
    <!--    <el-col :span="20">-->
    <div class="grid-content ep-bg-purple "
         style=" margin-top:20px; margin-left:4%; margin-left: 5%; width: 80% ; border: 1px  solid   #ecf5ff; box-shadow: 8px 8px 10px 2px  #79bbff; ">
      是否显示表格:
      <el-switch v-model="parentBorder"/>

      <el-table :data="filterTableData" stripe :table-layout="tableLayout"  max-height="400" max-width="300" :border="parentBorder" :header-cell-style="{'text-align':'center'}">
        <el-table-column  label="id" prop="id" width="80" align="center" />
        <el-table-column label="用户名" prop="username" width="120" align="center">
          <template #default="scope">
            <el-popover effect="light" trigger="hover" placement="top">
              <template #default>
                <div>name: {{ scope.row.username }}</div>
                <div>address: {{ scope.row.password }}</div>
              </template>
              <template #reference>
                <el-tag>{{ scope.row.username }}</el-tag>
              </template>
            </el-popover>
          </template>
        </el-table-column>
        <el-table-column label="密码" prop="password" width="100" align="center"/> //prop中的内容与User.java中的字段必须相同
        <el-table-column label="性别" prop="sex" width="100" align="center"/>
        <el-table-column label="年龄" prop="age" width="100" align="center"/>
        <el-table-column label="手机" prop="phone" width="100" align="center"/>
        <el-table-column label="邮箱" prop="email" width="100" align="center"/>
        <el-table-column label="地址" prop="address" width="200" align="center"/>
        <el-table-column label="个性宣言" prop="statement" width="200" align="center"/>
        <el-table-column label="头像" prop="avatar" width="600" align="center"/>


        <el-table-column fixed="right" label="操作" width="180">
<!--          <template #header>-->
<!--            <el-input fixed="right" v-model="search" size="small" placeholder="Type to search" />-->
<!--          </template>-->
          <template #default="scope">

            <el-button size="small" @click="handleEdit(scope.$index, scope.row)">
              <el-icon size="size" color="#409EFF" style="margin-right: 4px">
                <Edit/>
              </el-icon>
              编辑
            </el-button>
            <el-popconfirm title="确定删除吗？" @confirm="handleDelete(scope.row.id)" confirm-button-text="确定" cancel-button-text="取消">
              <template #reference>
                <el-button size="small" >
                  <el-icon size="small" color="Red" style="margin-right: 4px">
                    <Delete/>
                  </el-icon>
                  删除
                </el-button>
              </template>
            </el-popconfirm>
          </template>
        </el-table-column>
      </el-table>
      <div style="margin:5px" text @click="handleAdd">
        <el-button class="mt-4" @click="handleAdd" :icon="Plus" color=" #337ecc">Add Item</el-button>
      </div>
    </div>
    <!--    </el-col>-->
  </el-row>


  <el-dialog v-model="dialogFormVisible" title=" 新增用户" width="40%">
    <el-form :model="state.form" label-width="200px" style="width: 80%" :rules="state.rules" ref="ruleFormRef">
      <el-form-item label="用户名" prop="username">
        <el-input v-model="state.form.username" autocomplete="off" placeholder="请输入用户名"/>
      </el-form-item>
      <el-form-item label="密码" prop="password" label-width="200px">
        <el-input v-model="state.form.password" autocomplete="off" placeholder="请输入密码"/>
      </el-form-item>
      <el-form-item label="性别" prop="sex" label-width="200px">
        <el-radio-group v-model="state.form.sex" class="ml-4">
          <el-radio label="男" >男</el-radio>
          <el-radio label="女" >女</el-radio>
        </el-radio-group>
      </el-form-item>
      <el-form-item label="年龄" prop="age" label-width="200px">
        <el-input v-model="state.form.age" autocomplete="off" placeholder="请输入年龄"/>
      </el-form-item>
      <el-form-item label="手机" prop="phone" label-width="200px">
        <el-input v-model="state.form.phone" autocomplete="off" placeholder="请输入手机"/>
      </el-form-item>
      <el-form-item label="邮箱" prop="email" label-width="200px">
        <el-input v-model="state.form.email" autocomplete="off" placeholder="请输入邮箱"/>
      </el-form-item>
      <el-form-item label="地址" prop="address" label-width="200px">
        <el-input type="textarea" v-model="state.form.address" autocomplete="off" placeholder="请输入地址"/>
      </el-form-item>
      <el-form-item label="个性宣言" prop="address" label-width="200px">
        <el-input type="textarea" v-model="state.form.statement" autocomplete="off" placeholder="请输入个性宣言"/>
      </el-form-item>
    </el-form>
    <template #footer>
      <span class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取消</el-button>
        <el-button type="primary" @click="save"> 确认</el-button>
      </span>
    </template>
  </el-dialog>
</template>

<script lang="ts" setup>
import {computed, getCurrentInstance, reactive, ref} from 'vue';
import request from "../request";
import {Plus, Delete, Edit, ArrowRightBold} from '@element-plus/icons-vue'
import {
  Document,
  Menu as IconMenu,
  Location,
  Setting,
} from '@element-plus/icons-vue'
import {ElMessage, ElNotification} from "element-plus";

const {proxy} = getCurrentInstance()

// 属性
const dialogFormVisible = ref(false)
const formLabelWidth = '140px'
const parentBorder = ref(false)
const search = ref('')
const isCollapse = ref(true)
const tableLayout = ref('fixed')
const checkEmail=(rule, value, callback)=>{
  const regEmail = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(\.[a-zA-Z0-9_-])+/;
  if(!regEmail.test(value)){
    return callback(new Error("邮箱格式错误"))
  }
  callback()
}
// 自定义接口
interface User {
  id: BigInteger
  name: string
  password: string
}

const state = reactive({
  tableData: [],
  form: {},
  rules: {
    username: [
      {
        required: true,
        message: '请输入用户名',
        trigger: 'blur'
      },
      {
        min: 3,
        max: 12,
        message: '长度范围保持在3~12个字符',
        trigger: 'blur'
      }
    ],
    password: [
      {
        required: true,
        message: '请输入密码',
        trigger: 'blur'//触发
      },
      {
        min: 6,
        max: 16,
        message: '长度范围保持在6~16个字符',
        trigger: 'blur'
      },
    ],
    address:[
      {
        required: true,
        message: '请输入地址',
        trigger: 'blur'//触发
      },
      {
        max: 255,
        message: '最多输入255个字符',
        trigger: 'blur'
      },
    ],
    email:[
      {
        validator: checkEmail,
        trigger:'blur',
      }
    ]
  }

})


// 函数


//每次刷新都要调用的

const handleAdd = () => {
  dialogFormVisible.value = true
  state.form = {}//数据初始化
}
const handleOpen = (key: string, keyPath: string[]) => {
  console.log(key, keyPath)
}
const handleClose = (key: string, keyPath: string[]) => {
  console.log(key, keyPath)
}
const handleClick = () => {
  console.log('click')
}
const handleEdit = (index: number, row: User) => {
  dialogFormVisible.value = true
  state.form = JSON.parse(JSON.stringify(row))
}
const handleDelete = (id) => {
  request.delete("/user/" + id).then(res => {
    if (res.code === '200') {
      ElMessage.success("删除成功")
    } else {
      ElMessage.error("res.msg")
    }
    // 刷新表格
    load()
  })
}
const load = () => {
  request.get("/user").then(res => {
    if(res.code === '200'){
      state.tableData = res.data
    }else{
      ElMessage.error(res.msg)
    }

  })
}
load()
const save = () => {
// 简单的数据校验
  proxy.$refs.ruleFormRef.validate((valid) => {
    if (valid) {
      if (state.form.id) {//id 存在说明是 编辑操作， id由数据库自动递增给出
        request.put("/user", state.form).then(res => {
          if(res.code === '200'){
            ElMessage.success("保存成功")
            dialogFormVisible.value = false
            load()
          }
          else{
            ElMessage.error(res.msg)
          }
        })
      } else {//新增操作
        console.log(state.form)
        request.post("/user", state.form).then(res => {
          if(res.code === '200' && res.data === true){
            ElMessage.success("保存成功")
            //关闭弹窗
            dialogFormVisible.value = false
            // 刷新表格
            load()
          }
          else{
            ElMessage.error(res.msg)
          }
        })
      }
    }
  })
}
const filterTableData = computed(() =>
    state.tableData.filter(
        (data) =>
            !search.value ||
            data.name.toLowerCase().includes(search.value.toLowerCase())
    ))

</script>
<style scoped lang="scss">
.el-box-shadow {
  box-shadow: 0px -1px 0px 0px #e5e5e5, /*上边阴影 */

  -0.2px 0px 0px 0px #e5e5e5, /*左边阴影  */

  0.2px 0px 0px 0px #e5e5e5, /*右边阴影 */

  0px 1px 0px 0px #e5e5e5; /*下边阴影 */
}

.el-row {
  margin-bottom: 20px;
}

.el-row:last-child {
  margin-bottom: 0;
}

.el-col {
  border-radius: 4px;
}

.grid-content {
  border-radius: 4px;
  min-height: 36px;
}

.el-button--text {
  margin-right: 15px;
}

.el-select {
  width: 300px;
}

.el-input {
  width: 300px;
}

.dialog-footer button:first-child {
  margin-right: 10px;
}

.flex-grow {
  flex-grow: 1;
}

.el-menu-vertical-demo:not(.el-menu--collapse) {
  width: 200px;
  min-height: 400px;
}
</style>