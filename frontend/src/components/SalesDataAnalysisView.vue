<template>

    <v-data-table
        :headers="headers"
        :items="salesDataAnalysis"
        :items-per-page="5"
        class="elevation-1"
    ></v-data-table>

</template>

<script>
    const axios = require('axios').default;

    export default {
        name: 'SalesDataAnalysisView',
        props: {
            value: Object,
            editMode: Boolean,
            isNew: Boolean
        },
        data: () => ({
            headers: [
                { text: "id", value: "id" },
                { text: "menuName", value: "menuName" },
                { text: "totalOrderCount", value: "totalOrderCount" },
                { text: "totalPrice", value: "totalPrice" },
                { text: "orderDate", value: "orderDate" },
            ],
            salesDataAnalysis : [],
        }),
          async created() {
            var temp = await axios.get(axios.fixUrl('/salesDataAnalyses'))

            temp.data._embedded.salesDataAnalyses.map(obj => obj.id=obj._links.self.href.split("/")[obj._links.self.href.split("/").length - 1])

            this.salesDataAnalysis = temp.data._embedded.salesDataAnalyses;
        },
        methods: {
        }
    }
</script>

