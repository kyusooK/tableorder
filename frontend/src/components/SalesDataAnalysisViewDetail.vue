<template>

    <v-card outlined>
        <v-card-title>
            SalesDataAnalysis # {{$route.params.id }}
        </v-card-title>

        <v-card-text>
            <div>
                <String label="MenuName" v-model="item.menuName" :editMode="editMode" @change="change" />
            </div>
            <div>
                <Number label="TotalOrderCount" v-model="item.totalOrderCount" :editMode="editMode" @change="change" />
            </div>
            <div>
                <Number label="TotalPrice" v-model="item.totalPrice" :editMode="editMode" @change="change" />
            </div>
            <div>
                <Date label="OrderDate" v-model="item.orderDate" :editMode="editMode" @change="change" />
            </div>
        </v-card-text>
    </v-card>

</template>

<script>
  const axios = require('axios').default;

  export default {
    name: 'SalesDataAnalysisViewDetail',
    props: {
      value: Object,
    },
    data: () => ({
        item : [],
    }),
    async created() {
      var params = this.$route.params;
      var temp = await axios.get(axios.fixUrl('/salesDataAnalyses/' + params.id))

      this.item = temp.data;

    },
    methods: {
    }
  }
</script>

