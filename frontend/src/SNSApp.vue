<template>
    <v-app id="inspire">
        <div>
            <v-app-bar color="primary" app clipped-left flat>
                <v-toolbar-title>
                    <span class="second-word font uppercase"
                        style="font-weight:700;"
                    >
                        <v-app-bar-nav-icon
                            @click="openSideBar()"
                            style="z-index:1;
                            height:56px;
                            width:30px;
                            margin-right:10px;
                            font-weight:300;
                            font-size:55px;"
                        >
                            <div style="line-height:100%;">≡</div>
                        </v-app-bar-nav-icon>
                    </span>
                </v-toolbar-title>
                <span v-if="urlPath!=null" 
                    class="mdi mdi-home" 
                    key="" 
                    to="/" 
                    @click="goHome()"
                    style="margin-left:10px; font-size:20px; cursor:pointer;"
		        ></span> 
                <v-spacer></v-spacer>

            </v-app-bar>

            <v-navigation-drawer app clipped flat v-model="sideBar">
                <v-list>
                    <v-list-item
                        class="px-2"
                        key="menus"
                        to="/menus/menus"
                        @click="changeUrl()"
                        color="primary"
                        style="font-weight:700;"
                    >
                        메뉴
                    </v-list-item>


                    <v-list-item
                        class="px-2"
                        key="tableOrders"
                        to="/tables/tableOrders"
                        @click="changeUrl()"
                        color="primary"
                        style="font-weight:700;"
                    >
                        테이블주문
                    </v-list-item>


                    <v-list-item
                        class="px-2"
                        key="orderPages"
                        to="/tables/orderPages"
                        @click="changeUrl()"
                        color="primary"
                        style="font-weight:700;"
                    >
                        주문 이력
                    </v-list-item>
                    <v-list-item
                        class="px-2"
                        key="sales"
                        to="/salesanalyses/sales"
                        @click="changeUrl()"
                        color="primary"
                        style="font-weight:700;"
                    >
                        매출
                    </v-list-item>


                    <v-list-item
                        class="px-2"
                        key="salesDataAnalyses"
                        to="/salesanalyses/salesDataAnalyses"
                        @click="changeUrl()"
                        color="primary"
                        style="font-weight:700;"
                    >
                        판매 데이터 분석
                    </v-list-item>
                    <v-list-item
                        class="px-2"
                        key="kitchens"
                        to="/kitchens/kitchens"
                        @click="changeUrl()"
                        color="primary"
                        style="font-weight:700;"
                    >
                        주방
                    </v-list-item>


                </v-list>
            </v-navigation-drawer>
        </div>

        <v-main>
            <v-container style="padding:0px;" v-if="urlPath" fluid>
                <router-view></router-view>
            </v-container>
            <v-container style="padding:0px;" v-else fluid>
                <div style="width:100%; position: relative;">
                    <v-img style="width:100%; height:200px;"
                        src="image/main.png"
                    ></v-img>
                    <div class="App-main-text-overlap"></div>
                    <div class="App-sub-text-overlap"></div>
                </div>
                <v-row class="pa-0 ma-0">
                    <v-col cols="12" lg="3" md="4" sm="6" class="pa-0 pa-0" v-for="(aggregate, index) in aggregate" :key="index">
                        <div class="pa-4">
                            <v-card
                                :key="aggregate.key"
                                :to="aggregate.route"
                                @click="changeUrl()"
                                class="mx-auto main-card pa-4"
                                style="text-align: center; border-radius: 10px;"
                                outlined
                            >
                                <div class="d-flex justify-center" style="width:120px; height:120px; border-radius: 10px; margin: 0 auto; background-color:white;">
                                    <v-img style="width:100%; height:100%; object-fit:contain; border-radius: 10px;" :src="aggregate.ImageUrl"></v-img>
                                </div>
                                <div style="text-align: center;">
                                    <h2 class="main-card-title">{{ aggregate.title }}</h2>
                                </div>
                            </v-card>
                        </div>
                    </v-col>
                </v-row>
            </v-container>
        </v-main>
    </v-app>
</template>

<script>

export default {
    name: "App",
    data: () => ({
        useComponent: "",
        drawer: true,
        components: [],
        sideBar: true,
        urlPath: null,
        flipped: [],
        ImageUrl: '',
        aggregate: [
            { 
                title: '메뉴', 
                description: 'Menu을 관리하는 화면입니다.', 
                key: 'menus', 
                route: '/menus/menus',
                ImageUrl: '/image/open.svg',
            },
            { 
                title: '테이블주문', 
                description: 'TableOrder을 관리하는 화면입니다.', 
                key: 'tableOrders', 
                route: '/tables/tableOrders',
                ImageUrl: '/image/list.svg',
            },
            { 
                title: '매출', 
                description: 'sales을 관리하는 화면입니다.', 
                key: 'sales', 
                route: '/salesanalyses/sales',
                ImageUrl: '/image/market.svg',
            },
            { 
                title: '주방', 
                description: 'kitchen을 관리하는 화면입니다.', 
                key: 'kitchens', 
                route: '/kitchens/kitchens',
                ImageUrl: '/image/kitchen.svg',
            },
            { 
                title: '주문 이력', 
                description: 'OrderPage을 관리하는 화면입니다.', 
                key: 'orderPages', 
                route: '/tables/orderPages',
                ImageUrl: '/image/shopping-list.svg',
            },
            { 
                title: '판매 데이터 분석', 
                description: '판매 데이터 분석을 관리하는 화면입니다.', 
                key: 'salesDataAnalyses', 
                route: '/salesanalyses/salesDataAnalyses',
                ImageUrl: '/image/data.svg',
            },
        ],
    }),
    
    async created() {
      var path = document.location.href.split("#/")
      this.urlPath = path[1];

    },
    watch: {
        cards(newCards) {
            this.flipped = new Array(newCards.length).fill(false);
        },
    },

    mounted() {
        var me = this;
        me.components = this.$ManagerLists;
    },

    methods: {
        openSideBar(){
            this.sideBar = !this.sideBar
        },
        changeUrl() {
            var path = document.location.href.split("#/")
            this.urlPath = path[1];
            this.flipped.fill(false);
        },
        goHome() {
            this.urlPath = null;
        },
        flipCard(index) {
            this.$set(this.flipped, index, true);
        },
        unflipCard(index) {
            this.$set(this.flipped, index, false);
        },
    }
};
</script>
<style>
</style>
