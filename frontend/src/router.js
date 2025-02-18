
import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router);


import MenuMenuManager from "./components/listers/MenuMenuCards"
import MenuMenuDetail from "./components/listers/MenuMenuDetail"

import GetMenuView from "./components/GetMenuView"
import GetMenuViewDetail from "./components/GetMenuViewDetail"
import TableTableOrderManager from "./components/listers/TableTableOrderCards"
import TableTableOrderDetail from "./components/listers/TableTableOrderDetail"

import OrderPageView from "./components/OrderPageView"
import OrderPageViewDetail from "./components/OrderPageViewDetail"
import SalesanalysisSalesManager from "./components/listers/SalesanalysisSalesCards"
import SalesanalysisSalesDetail from "./components/listers/SalesanalysisSalesDetail"

import SalesDataAnalysisView from "./components/SalesDataAnalysisView"
import SalesDataAnalysisViewDetail from "./components/SalesDataAnalysisViewDetail"
import KitchenKitchenManager from "./components/listers/KitchenKitchenCards"
import KitchenKitchenDetail from "./components/listers/KitchenKitchenDetail"


export default new Router({
    // mode: 'history',
    base: process.env.BASE_URL,
    routes: [
            {
                path: '/menus/menus',
                name: 'MenuMenuManager',
                component: MenuMenuManager
            },
            {
                path: '/menus/menus/:id',
                name: 'MenuMenuDetail',
                component: MenuMenuDetail
            },

            {
                path: '/menus/getMenus',
                name: 'GetMenuView',
                component: GetMenuView
            },
            {
                path: '/menus/getMenus/:id',
                name: 'GetMenuViewDetail',
                component: GetMenuViewDetail
            },
            {
                path: '/tables/tableOrders',
                name: 'TableTableOrderManager',
                component: TableTableOrderManager
            },
            {
                path: '/tables/tableOrders/:id',
                name: 'TableTableOrderDetail',
                component: TableTableOrderDetail
            },

            {
                path: '/tables/orderPages',
                name: 'OrderPageView',
                component: OrderPageView
            },
            {
                path: '/tables/orderPages/:id',
                name: 'OrderPageViewDetail',
                component: OrderPageViewDetail
            },
            {
                path: '/salesanalyses/sales',
                name: 'SalesanalysisSalesManager',
                component: SalesanalysisSalesManager
            },
            {
                path: '/salesanalyses/sales/:id',
                name: 'SalesanalysisSalesDetail',
                component: SalesanalysisSalesDetail
            },

            {
                path: '/salesanalyses/salesDataAnalyses',
                name: 'SalesDataAnalysisView',
                component: SalesDataAnalysisView
            },
            {
                path: '/salesanalyses/salesDataAnalyses/:id',
                name: 'SalesDataAnalysisViewDetail',
                component: SalesDataAnalysisViewDetail
            },
            {
                path: '/kitchens/kitchens',
                name: 'KitchenKitchenManager',
                component: KitchenKitchenManager
            },
            {
                path: '/kitchens/kitchens/:id',
                name: 'KitchenKitchenDetail',
                component: KitchenKitchenDetail
            },



    ]
})
