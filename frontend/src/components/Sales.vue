<template>
    <div>
        <String label="Menu" v-model="value.menu" :editMode="editMode"/>
        <Number label="TotalOrderCount" v-model="value.totalOrderCount" :editMode="editMode"/>
        <Number label="TotalPrice" v-model="value.totalPrice" :editMode="editMode"/>
        <Date label="OrderDate" v-model="value.orderDate" :editMode="editMode"/>

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
    name: 'Sales',
    mixins:[BaseEntity],
    components:{
    },
    
    data: () => ({
        path: "sales"
    }),
    created(){
    },
    computed:{
    },
    methods: {
    },
}
</script>
