<template>
    <div>
        <v-list two-line v-if="list.length > 0">
            <v-list-item-group 
                v-model="selected" 
                color="primary"
                @change="select"
            >
                <v-list-item v-for="(item, idx) in list" :key="idx">
                    <template v-slot:default="{ active }">
                        <v-list-item-content>
                            <v-list-item-title>
                                Menu #{{item._links.self.href.split('/').pop()}}
                            </v-list-item-title>
                            <v-list-item-subtitle>
                                MenuName: {{item.menuName}}
                            </v-list-item-subtitle>
                            <v-list-item-subtitle>
                                Price: {{item.price}}원
                            </v-list-item-subtitle>
                            <v-list-item-subtitle>
                                MenuInfo: {{item.menuInfo}}
                            </v-list-item-subtitle>
                        </v-list-item-content>

                        <v-list-item-action>
                            <v-checkbox :input-value="active" color="primary"></v-checkbox>
                        </v-list-item-action>
                    </template>
                </v-list-item>
            </v-list-item-group>
        </v-list>
        <v-alert
            v-else
            type="info"
            dense
        >
            등록된 메뉴가 없습니다.
        </v-alert>
    </div>
</template>

<script>
const axios = require('axios').default;

export default {
    name: 'MenuPicker',
    props: {
        value: [String, Object, Array, Number, Boolean],
    },
    data: () => ({
        list: [],
        selected: null,
    }),
    async created() {
        try {
            const temp = await axios.get(axios.fixUrl('/menus'));
            if(temp.data._embedded) {
                this.list = temp.data._embedded.menus;
            }
        } catch(e) {
            console.error(e);
        }
    },
    methods: {
        select(val) {
            if(val !== undefined) {
                const selectedMenu = this.list[val];
                const menuId = selectedMenu._links.self.href.split('/').pop();
                this.$emit('selected', { id: menuId });
            }
        },
    },
};
</script>