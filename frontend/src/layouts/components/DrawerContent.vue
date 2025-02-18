<script setup>
import {
  VerticalNavLink,
  VerticalNavSectionTitle,
} from '@layouts'
</script>

<template>
    <ul>
        <VerticalNavSectionTitle :item="{ heading: '메뉴' }" />
        <VerticalNavLink
            :item="{
                title: '메뉴',
                to: '/menus',
            }"
        />
        <VerticalNavSectionTitle :item="{ heading: '테이블' }" />
        <VerticalNavLink
            :item="{
                title: '테이블주문',
                to: '/tableOrders',
            }"
        />
        <VerticalNavSectionTitle :item="{ heading: '매출 분석' }" />
        <VerticalNavLink
            :item="{
                title: '매출',
                to: '/sales',
            }"
        />
        <VerticalNavSectionTitle :item="{ heading: '주방' }" />
        <VerticalNavLink
            :item="{
                title: '주방',
                to: '/kitchens',
            }"
        />
    </ul>
</template>
