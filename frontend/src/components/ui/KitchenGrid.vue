<template>
    <v-container>
        <v-snackbar
            v-model="snackbar.status"
            :timeout="snackbar.timeout"
            :color="snackbar.color"
        >
            
            <v-btn style="margin-left: 80px;" text @click="snackbar.status = false">
                Close
            </v-btn>
        </v-snackbar>
        <div class="panel">
            <div class="gs-bundle-of-buttons" style="max-height:10vh;">
                <v-btn @click="addNewRow" @class="contrast-primary-text" small color="primary">
                    <v-icon small style="margin-left: -5px;">mdi-plus</v-icon>등록
                </v-btn>
                <v-btn style="margin-left: 5px;" @click="openEditDialog()" class="contrast-primary-text" small color="primary">
                    <v-icon small>mdi-pencil</v-icon>수정
                </v-btn>
                <v-btn style="margin-left: 5px;" @click="cookDialog = true" class="contrast-primary-text" small color="primary" :disabled="!hasRole('요리사')">
                    <v-icon small>mdi-minus-circle-outline</v-icon>조리
                </v-btn>
                <v-dialog v-model="cookDialog" width="500">
                    <Cook
                        @closeDialog="cookDialog = false"
                        @cook="cook"
                    ></Cook>
                </v-dialog>
                <v-btn style="margin-left: 5px;" @click="serveDialog = true" class="contrast-primary-text" small color="primary" :disabled="!hasRole('직원')">
                    <v-icon small>mdi-minus-circle-outline</v-icon>서빙
                </v-btn>
                <v-dialog v-model="serveDialog" width="500">
                    <Serve
                        @closeDialog="serveDialog = false"
                        @serve="serve"
                    ></Serve>
                </v-dialog>
            </div>
            <div class="mb-5 text-lg font-bold"></div>
            <div class="table-responsive">
                <v-table>
                    <thead>
                        <tr>
                        <th>Id</th>
                        <th>주문정보</th>
                        <th>주문요청서</th>
                        <th>음식상태</th>
                        <th>tableOrderId</th>
                        <th>테이블주문</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr v-for="(val, idx) in value" :key="val" @click="changeSelectedRow(val)" :style="val === selectedRow ? 'background-color: #f0f3ff;':''">
                            <td class="font-semibold">{{ idx + 1 }}</td>
                            <td class="whitespace-nowrap" label="주문정보">{{ val.orderInfo }}</td>
                            <td class="whitespace-nowrap" label="주문요청서">{{ val.requestInfo }}</td>
                            <td class="whitespace-nowrap" label="음식상태">{{ val.foodStatus }}</td>
                            <td class="whitespace-nowrap" label="테이블주문">
                                <TableOrderId :editMode="editMode" v-model="val.tableOrderId"></TableOrderId>
                            </td>
                            <Icon style="margin-top: 15px;" icon="mi:delete" @click="deleteRow(val)" />
                        </tr>
                    </tbody>
                </v-table>
            </div>
        </div>
        <v-col>
            <v-dialog
                v-model="openDialog"
                transition="dialog-bottom-transition"
                width="35%"
            >
                <v-card>
                    <v-toolbar
                        color="primary"
                        class="elevation-0"
                        height="50px"
                    >
                        <div style="color:white; font-size:17px; font-weight:700;">Kitchen 등록</div>
                        <v-spacer></v-spacer>
                        <v-icon
                            color="white"
                            small
                            @click="closeDialog()"
                        >mdi-close</v-icon>
                    </v-toolbar>
                    <v-card-text>
                        <Kitchen :offline="offline"
                            :isNew="!value.idx"
                            :editMode="true"
                            :inList="false"
                            v-model="newValue"
                            @add="append"
                        />
                    </v-card-text>
                </v-card>
            </v-dialog>
            <v-dialog
                v-model="editDialog"
                transition="dialog-bottom-transition"
                width="35%"
            >
                <v-card>
                    <v-toolbar
                        color="primary"
                        class="elevation-0"
                        height="50px"
                    >
                        <div style="color:white; font-size:17px; font-weight:700;">Kitchen 수정</div>
                        <v-spacer></v-spacer>
                        <v-icon
                            color="white"
                            small
                            @click="closeDialog()"
                        >mdi-close</v-icon>
                    </v-toolbar>
                    <v-card-text>
                        <div>
                            <String label="주문정보" v-model="selectedRow.orderInfo" :editMode="true"/>
                            <String label="주문요청서" v-model="selectedRow.requestInfo" :editMode="true"/>
                            <FoodStatus offline label="음식상태" v-model="selectedRow.foodStatus" :editMode="true"/>
                            <TableOrderId offline label="tableOrderId" v-model="selectedRow.tableOrderId" :editMode="true"/>
                            <v-divider class="border-opacity-100 my-divider"></v-divider>
                            <v-layout row justify-end>
                                <v-btn
                                    width="64px"
                                    color="primary"
                                    @click="save"
                                >
                                    수정
                                </v-btn>
                            </v-layout>
                        </div>
                    </v-card-text>
                </v-card>
            </v-dialog>
        </v-col>
    </v-container>
</template>

<script>
import { ref } from 'vue';
import { useTheme } from 'vuetify';
import BaseGrid from '../base-ui/BaseGrid.vue'


export default {
    name: 'kitchenGrid',
    mixins:[BaseGrid],
    components:{
    },
    data: () => ({
        path: 'kitchens',
        cookDialog: false,
        serveDialog: false,
    }),
    watch: {
    },
    methods:{
        cook(params){
            try{
                this.repository.invoke(this.getSelectedItem(), "cook", params)
                this.$EventBus.$emit('show-success','cook 성공적으로 처리되었습니다.')
                this.cookDialog = false
            }catch(e){
                console.log(e)
            }
            
        },
        serve(params){
            try{
                this.repository.invoke(this.getSelectedItem(), "serve", params)
                this.$EventBus.$emit('show-success','serve 성공적으로 처리되었습니다.')
                this.serveDialog = false
            }catch(e){
                console.log(e)
            }
            
        },
    }
}

</script>