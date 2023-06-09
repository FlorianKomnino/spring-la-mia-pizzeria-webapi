<template>
    <form @submit.prevent="createPizza">
        <label for="name">Nome</label><br>
        <input type="text" name="name" v-model="pizza.name">
        <br>
        <label for="description">Descrizione</label><br>
        <input type="text" name="description" v-model="pizza.description">
        <br>
        <label for="imgUrl">Url immagine della pizza</label>
        <br>
        <input type="text" name="imgUrl" v-model="pizza.imgUrl">
        <br>
        <label for="price">Prezzo</label><br>
        <input type="number" name="price" v-model="pizzaPrice" step=".01">
        <br>
        <input type="submit" value="CREA">
    </form>
</template>

<script>
import axios from "axios";

export default {
    name: "PizzaCreateComponent",

    data() {
        return {
            baseUrl: "http://localhost:8080/api/v1",
            pizzaPrice: null,
            pizza: {
                name: "",
                description: "",
                imgUrl: "",
                priceInCents: null,
            }
        }
    },

    methods: {
        createPizza() {
            this.pizza.priceInCents = this.pizzaPrice * 100;
            axios.post(this.baseUrl + '/ourPizzas/create', this.pizza)
                .then((response) => {
                    console.log(this.pizza);
                    this.$router.push('/');
                })
                .catch(function (error) {
                    console.log(error);
                })
                .finally(function () {
                    // always executed
                });
        }
    },
}
</script>

