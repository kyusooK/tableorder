<template>

    <v-card outlined>
        <v-card-title>
            Cook
        </v-card-title>

        <v-card-text>
            <FoodStatus offline label="FoodStatus" v-model="value.foodStatus" :editMode="editMode" @change="change"/>
        </v-card-text>

        <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn
                    color="deep-purple lighten-2"
                    text
                    @click="cook"
            >
                Cook
            </v-btn>
            
            <v-btn
                    color="deep-purple lighten-2"
                    text
                    @click="close"
            >
                Close
            </v-btn>
        </v-card-actions>
    </v-card>

</template>

<script>

export default {
    name: 'CookCommand',
    components:{
    },
    props: {},
    data: () => ({
        editMode: true,
        value: {},
    }),
    created() {
        this.value.foodStatus = {};
    },
    watch: {
    },
    methods: {
        cook() {
            this.$emit('cook', this.value);
        },
        close() {
            this.$emit('closeDialog');
        },
        change() {
            this.$emit("update:modelValue", this.value);
        },
    }
}
</script>

