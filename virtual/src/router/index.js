var router;

import Vue from 'vue';
import Router from 'vue-router';
// import login from '../components/Login/login';
import index from '../components/index';

Vue.use(Router);

export const routes = [
    {
        path: '/',
        name: 'this is my index',
        hidden: true,
        component: index
    }
];
router = new Router({
    routes: routes
});

export default router;
