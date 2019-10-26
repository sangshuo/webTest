var router;

import Vue from 'vue';
import Router from 'vue-router';

import login from '../components/Login/login';

Vue.use(Router);

export const routes = [
    {
        path: '/',
        name: 'login',
        hidden: true,
        component: login
    }
];
router = new Router({
    routes: routes
});

export default router;
