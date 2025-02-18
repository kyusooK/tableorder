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
            </div>
            <div class="mb-5 text-lg font-bold"></div>
            <div class="table-responsive">
                <v-table>
                    <thead>
                        <tr>
                        <th>Id</th>
                        <th>주문요청정보</th>
                        <th>음식 가격</th>
                        <th>주문정보</th>
                        <th>결제정보</th>
                        <th>결제 상태</th>
                        <th>주문일자</th>
                        <th>주문정보</th>
                        <th>메뉴ID</th>
                        <th>메뉴</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr v-for="(val, idx) in value" :key="val" @click="changeSelectedRow(val)" :style="val === selectedRow ? 'background-color: #f0f3ff;':''">
                            <td class="font-semibold">{{ idx + 1 }}</td>
                            <td class="whitespace-nowrap" label="주문요청정보">{{ val.requestInfo }}</td>
                            <td class="whitespace-nowrap" label="음식 가격">{{ val.totalPrice }}</td>
                            <td class="whitespace-nowrap" label="주문정보">{{ val.orderStatus }}</td>
                            <td class="whitespace-nowrap" label="결제정보">{{ val.paymentId }}</td>
                            <td class="whitespace-nowrap" label="결제 상태">{{ val.paymentStatus }}</td>
                            <td class="whitespace-nowrap" label="주문일자">{{ val.orderDate }}</td>
                            <td class="whitespace-nowrap" label="주문정보">{{ val.orderInfo }}</td>
                            <td class="whitespace-nowrap" label="메뉴">
                                <MenuId :editMode="editMode" v-model="val.menuId"></MenuId>
                            </td>
                            <Icon style="margin-top: 15px;" icon="mi:delete" @click="deleteRow(val)" />
                        </tr>
                    </tbody>
                </v-table>
            </div>
            <MenuIdsDetailGrid style="margin-top: 20px;" label="메뉴ID" offline v-if="selectedRow" v-model="selectedRow.menuIds" :selectedRow="selectedRow"/>
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
                        <div style="color:white; font-size:17px; font-weight:700;">TableOrder 등록</div>
                        <v-spacer></v-spacer>
                        <v-icon
                            color="white"
                            small
                            @click="closeDialog()"
                        >mdi-close</v-icon>
                    </v-toolbar>
                    <v-card-text>
                        <TableOrder :offline="offline"
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
                        <div style="color:white; font-size:17px; font-weight:700;">TableOrder 수정</div>
                        <v-spacer></v-spacer>
                        <v-icon
                            color="white"
                            small
                            @click="closeDialog()"
                        >mdi-close</v-icon>
                    </v-toolbar>
                    <v-card-text>
                        <div>
                            <String label="주문요청정보" v-model="selectedRow.requestInfo" :editMode="true"/>
                            <Number label="음식 가격" v-model="selectedRow.totalPrice" :editMode="true"/>
                            <Number label="결제정보" v-model="selectedRow.paymentId" :editMode="true"/>
                            <String label="결제 상태" v-model="selectedRow.paymentStatus" :editMode="true"/>
                            <Date label="주문일자" v-model="selectedRow.orderDate" :editMode="true"/>
                            <String label="주문정보" v-model="selectedRow.orderInfo" :editMode="true"/>
                            <OrderStatus offline label="주문정보" v-model="selectedRow.orderStatus" :editMode="true"/>
                            <MenuIdsDetailGrid label="메뉴ID" offline v-model="selectedRow.menuIds" :editMode="true"/>
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
    name: 'tableOrderGrid',
    mixins:[BaseGrid],
    components:{
    },
    data: () => ({
        path: 'tableOrders',
    }),
    watch: {
    },
    methods:{
    }
}

</script>