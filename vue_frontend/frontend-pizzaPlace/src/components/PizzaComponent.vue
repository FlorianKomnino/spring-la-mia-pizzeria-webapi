<template>
    <li v-if="!deleted">
        <span @click="open = !open">
            {{ pizza.name }}
        </span>
        <button @click="deletePizza(pizza.id)">X</button>
        <div v-if="open">
            {{ pizza.description }}
        </div>
    </li>
</template>

<script>
import axios from 'axios';

export default {
    data() {

        return {
            baseUrl: "http://localhost:8080/api/v1/ourPizzas",
            open: false,
            deleted: false
        }
    },
    methods: {

        deletePizza(id) {

            axios.delete(this.baseUrl + "/delete/" + id)
                .then(response => this.deleted = true)
                .catch(err => console.log(err));
        }
    },
    props: {
        pizza: {}
    }
}
</script>