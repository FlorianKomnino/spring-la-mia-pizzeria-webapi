
<template>
  <h1>
    Hello, page loaded!
  </h1>
</template>

<script>
import axios from 'axios';

export default {
  name: "HomePage",

  data() {
    return {
      baseUrl: "http://localhost:8080/",
    }
  },

  methods: {
    axiosget() {
      axios.get(this.baseUrl + '/hello', {
        params: {

        }
      })
        .then(function (response) {
          console.log(response);
        })
        .catch(function (error) {
          console.log(error);
        })
        .finally(function () {
          // always executed
        });
    }
  },

  mounted() {

    console.log("book create mounted");


  }



}
</script>