import { createRouter, createWebHashHistory } from 'vue-router';

const router = createRouter({
  history: createWebHashHistory(),
  routes: [
    {
      path: '/',
      component: () => import('../components/pages/Index.vue'),
    },
    {
      path: '/menus',
      component: () => import('../components/ui/MenuGrid.vue'),
    },
    {
      path: '/tableOrders',
      component: () => import('../components/ui/TableOrderGrid.vue'),
    },
    {
      path: '/orderPages',
      component: () => import('../components/OrderPageView.vue'),
    },
    {
      path: '/sales',
      component: () => import('../components/ui/SalesGrid.vue'),
    },
    {
      path: '/salesDataAnalyses',
      component: () => import('../components/SalesDataAnalysisView.vue'),
    },
    {
      path: '/kitchens',
      component: () => import('../components/ui/KitchenGrid.vue'),
    },
  ],
})

export default router;
