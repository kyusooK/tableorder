<template>
    <div>
        <String label="주문요청정보" v-model="value.requestInfo" :editMode="editMode"/>
        <Number label="음식 가격" v-model="value.totalPrice" :editMode="editMode"/>
        <Number label="결제정보" v-model="value.paymentId" :editMode="editMode"/>
        <String label="결제 상태" v-model="value.paymentStatus" :editMode="editMode"/>
        <Date label="주문일자" v-model="value.orderDate" :editMode="editMode"/>
        <String label="주문정보" v-model="value.orderInfo" :editMode="editMode"/>
        <OrderStatus offline label="주문정보" v-model="value.orderStatus" :editMode="editMode" @change="change"/>
        <MenuIdsDetailGrid label="메뉴ID" offline v-model="value.menuIds" :editMode="editMode" @change="change"/>

        <v-divider class="border-opacity-100 my-divider"></v-divider>
        <v-layout row justify-end>
            <v-btn
                width="64px"
                color="primary"
                @click="save"
            >
                저장
            </v-btn>
        </v-layout>
    </div>
</template>


<script>

import BaseEntity from './base-ui/BaseEntity.vue'

export default {
    name: 'TableOrder',
    mixins:[BaseEntity],
    components:{
    },
    
    data: () => ({
        path: "tableOrders"
    }),
    created(){
    },
    computed:{
    },
    methods: {
    },
}
</script>
