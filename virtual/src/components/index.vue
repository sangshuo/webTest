<template>
    <el-container class="my_index">
        <login-model class="login_model"></login-model>
    </el-container>
</template>

<script>
    import loginModel from './Login/login';

    export default {
        name: 'index',
        components: {
            loginModel
        }
    };
</script>

<style scoped lang="less">
    .my_index {
        width: 100%;
        height: 947px;
        background: url("../../public/bg.jpg") no-repeat;
        background-size: 100% 100%;
        .login_model {
            margin: 80px 0 0 105px;
        }
    }
</style>
