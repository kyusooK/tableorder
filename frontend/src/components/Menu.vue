<template>
    <div>
        <String label="메뉴명" v-model="value.menuName" :editMode="editMode"/>
        <Number label="메뉴 가격" v-model="value.price" :editMode="editMode"/>
        <String label="메뉴 설명" v-model="value.menuInfo" :editMode="editMode"/>
        <String label="사용 재료" v-model="value.ingredients" :editMode="editMode"/>
        <String label="리뷰ID" v-model="value.reviewId" :editMode="editMode"/>

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
    name: 'Menu',
    mixins:[BaseEntity],
    components:{
    },
    
    data: () => ({
        path: "menus"
    }),
    created(){
    },
    computed:{
    },
    methods: {
    },
}
</script>
