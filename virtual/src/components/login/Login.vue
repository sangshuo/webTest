<template>
    <el-container>
        <div class="login">
            <div class="login_title">
                <div class="login_user_login login_title_color" @click="loginCheckedRegister('login')">账号登录</div>
                <div style="flex: 1"></div>
                <div class="login_user_register" @click="loginCheckedRegister('register')">账号注册</div>
            </div>
            <el-row class="login_el-row">
                <el-col :span="22">
                    <el-input type="text" placeholder="请您输入手机号/邮箱/用户名"></el-input>
                </el-col>
            </el-row>
            <el-row class="login_el-row">
                <el-col :span="22">
                    <el-input type="password" placeholder="请您输入密码"></el-input>
                </el-col>
            </el-row>
            <div class="login_forget_password">忘记密码</div>
            <div class="login_button">登录</div>
        </div>
    </el-container>
</template>

<script>
    export default {
        name: 'login',
        methods: {
            loginCheckedRegister(type) {
                if (type === 'login') {
                    if (!$('.login_user_login').hasClass('login_title_color')) {
                        $('.login_user_register').removeClass('login_title_color');
                        $('.login_user_login').addClass('login_title_color');
                    }
                } else if (type === 'register') {
                    if (!$('.login_user_register').hasClass('.login_title_color')) {
                        $('.login_user_register').addClass('login_title_color');
                        $('.login_user_login').removeClass('login_title_color');
                    }
                }
            }
        }
    };
</script>


<style scoped lang="less">
    .login {
        width: 500px;
        height: 550px;
        border: 1px solid #ccc;
        box-shadow: 0 1px 1px 0 rgba(0, 0, 0, .1);
        padding: 0 100px;
        box-sizing: border-box;

        .login_title {
            width: 270px;
            display: flex;
            margin: 50px 0 0 0;
            border-bottom: 2px solid #f5f5f5;

            .login_user_login {
                width: 100px;
                text-align: center;
            }
            .login_user_register {
                width: 100px;
                text-align: center;
            }

            .login_title_color {
                border-bottom: 2px solid #ca0c16;
            }
        }

        .login_el-row {
            margin: 30px 0;
        }

        .login_forget_password {
            margin-left: 200px;
        }

        .login_button {
            width: 230px;
            height: 40px;
            background: red;
            border-radius: 8px;
            text-align: center;
            line-height: 40px;
            font-size: 18px;
            color: #ffffff;
            margin-left: 15px;
            margin-top: 80px;
        }
    }
</style>
