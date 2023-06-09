
<template>
  <h1>
    Ciao! Benvenuto/a al PizzaPlace!
  </h1>
  <br>
  <h3>
    Ecco la lista delle nostre pizze
  </h3>
  <br>
  <h3>
    Puoi filtrare le pizze per nome digitando qui di seguito
  </h3>
  <input type="text" v-model="searchString" @keyup="axiosPostSearch">
  <br>
  <br>
  <ul>
    <PizzaComponent v-for="pizza in pizzas" :pizza="pizza" />
  </ul>
  <br>
  <h4>
    Se vuoi creare una nuova pizza clicca l'apposito link
  </h4>
  <router-link to="/pizzaCreate">
    Crea una pizza
  </router-link>
</template>

<script>
import axios from 'axios';
import PizzaComponent from "../components/PizzaComponent.vue";

export default {
  name: "HomePage",

  components: {
    PizzaComponent
  },

  data() {
    return {
      baseUrl: "http://localhost:8080/api/v1/ourPizzas",
      pizzas: [],
      searchString: "",
    }
  },

  methods: {
    axiosget() {
      axios.get(this.baseUrl, {
        params: {

        }
      })
        .then((response) => {
          console.log(response.data);
          this.pizzas = response.data;
        })
        .catch(function (error) {
          console.log(error);
        })
        .finally(function () {
          // always executed
        });
    },

    axiosPostSearch() {
      axios.post(this.baseUrl, this.searchString)
        .then((response) => {
          console.log(this.searchString);
          console.log(response.data);
          this.pizzas = response.data;
        })
        .catch(function (error) {
          console.log(error);
        })
        .finally(function () {
          // always executed
        });
    },
  },

  created() {

    console.log("book create mounted");
    this.axiosget();

  }



}
</script>