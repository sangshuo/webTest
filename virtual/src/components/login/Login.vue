<template>
    <el-container>
        <div>
            账号：<el-input v-model="input" placeholder="请输入内容"></el-input>
            密码：<el-input v-model="input" type="password" placeholder="请输入内容"></el-input>
        </div>
    </el-container>
</template>

<script>
export default {
  name: 'Login',
  data() {
    return {
      input: ''
    };
  }
};
</script>

<style scoped>

</style>
