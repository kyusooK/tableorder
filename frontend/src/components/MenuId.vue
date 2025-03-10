<template>
    <v-card outlined>
        <v-list dense v-if="value && value.length > 0">
            <v-list-item v-for="(menu, index) in referenceValues" :key="index">
                <v-list-item-content>
                    <v-list-item-title>
                        {{ menu.menuName }} - {{ menu.price }}원
                    </v-list-item-title>
                    <v-list-item-subtitle>
                        {{ menu.menuInfo }}
                    </v-list-item-subtitle>
                </v-list-item-content>
                <v-list-item-action v-if="editMode">
                    <v-btn icon @click="removeMenu(index)">
                        <v-icon>mdi-delete</v-icon>
                    </v-btn>
                </v-list-item-action>
            </v-list-item>
        </v-list>

        <v-card-actions v-if="editMode">
            <v-btn color="primary" text @click="openDialog">
                메뉴 추가
            </v-btn>
        </v-card-actions>

        <v-dialog v-model="pickerDialog" width="500">
            <v-card>
                <v-card-title>메뉴 선택</v-card-title>
                <v-card-text>
                    <MenuPicker v-model="tempValue" @selected="pick"/>
                </v-card-text>
                <v-card-actions>
                    <v-spacer></v-spacer>
                    <v-btn color="primary" text @click="pickerDialog = false">Close</v-btn>
                </v-card-actions>
            </v-card>
        </v-dialog>
    </v-card>
</template>

<script>
const axios = require('axios').default;
import MenuPicker from './listers/MenuPicker';

export default {
    name: 'MenuId',
    components: {
        MenuPicker
    },
    props: {
        value: [Object, String, Number, Boolean, Array],
        editMode: Boolean,
        isNew: Boolean,
        offline: Boolean,
        label: String,
    },
    data: () => ({
        tempValue: null,
        pickerDialog: false,
        referenceValues: [],
    }),
    async created() {
        if (!Array.isArray(this.value)) {
            this.$emit('input', []);
        }
        await this.updateReferenceValues();
    },
    methods: {
        openDialog() {
            if (this.editMode) {
                this.pickerDialog = true;
            }
        },
        async updateReferenceValues() {
            if (this.value && this.value.length > 0) {
                this.referenceValues = await Promise.all(
                    this.value.map(async (menuId) => {
                        try {
                            const response = await axios.get(axios.fixUrl('/menus/' + menuId.id));
                            return response.data;
                        } catch (error) {
                            console.error('Error fetching menu:', error);
                            return null;
                        }
                    })
                );
                // Remove any null values from failed requests
                this.referenceValues = this.referenceValues.filter(v => v !== null);
            }
        },
        async pick(val) {
            if (!Array.isArray(this.value)) {
                this.value = [];
            }
            
            const menuIdObj = { id: val.id };
            
            if (!this.value.some(item => item.id === val.id)) {
                this.value = [...this.value, menuIdObj];
                await this.updateReferenceValues();
                this.$emit('input', this.value);
                this.$emit('change', this.value);
            }
            this.pickerDialog = false;
        },
        removeMenu(index) {
            this.value.splice(index, 1);
            this.referenceValues.splice(index, 1);
            this.$emit('input', this.value);
            this.$emit('change', this.value);
        },
        change() {
            this.$emit('change', this.value);
        }
    }
}
</script>