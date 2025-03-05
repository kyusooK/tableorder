<template>
    <v-card outlined>
        <v-card-title>
            Serve
        </v-card-title>

        <v-card-text>
            <FoodStatus offline label="FoodStatus" v-model="value.foodStatus" :editMode="editMode" @change="change"/>
        </v-card-text>

        <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn
                    color="primary"
                    text
                    @click="serve"
            >
                Serve
            </v-btn>
            
            <v-btn
                    color="primary"
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
        name: 'ServeCommand',
        components:{},
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
            serve() {
                this.$emit('serve', this.value);
            },
            close() {
                this.$emit('closeDialog');
            },
            change() {
                this.$emit('input', this.value);
            },
        }
    }
</script>

