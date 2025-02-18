<template>
    <div>
        <String label="주문정보" v-model="value.orderInfo" :editMode="editMode"/>
        <String label="주문요청서" v-model="value.requestInfo" :editMode="editMode"/>
        <FoodStatus offline label="음식상태" v-model="value.foodStatus" :editMode="editMode" @change="change"/>
        <TableOrderId offline label="tableOrderId" v-model="value.tableOrderId" :editMode="editMode" @change="change"/>

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
    name: 'Kitchen',
    mixins:[BaseEntity],
    components:{
    },
    
    data: () => ({
        path: "kitchens"
    }),
    created(){
    },
    computed:{
    },
    methods: {
    },
}
</script>
