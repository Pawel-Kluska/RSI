<template>
  <CustomNavbar></CustomNavbar>
  <div class="container bg-light pt-3 mt-5 mb-5 pb-5 rounded-3">
    <div class="container-fluid mt-3">
      <h4 v-if="!this.serverAvailable" class="error-message">
        Nie udało się nawiązać połączenia z serwerem
      </h4>
      <h1>Autorzy / Informacje</h1>

      <h3> Autorzy: {{ authors.author1 }}, {{ authors.author2 }} </h3>
      <h3> Data i czas: {{ authors.datetime }} </h3>
      <h3> Wersja Javy: {{ authors.javaVersion }} </h3>
      <h3> Nazwa użytkownika: {{ authors.userName }} </h3>
      <h3> Nazwa systemu operacyjnego: {{ authors.osName }} </h3>
      <h3> Adres IP: {{ authors.localAddress }} </h3>

      <button class="btn btn-primary "
              @click="this.$router.push({ name: 'main'});">Powrót
      </button>
    </div>
  </div>
</template>


<script>

import {apiLocation} from "@/components/utils/Properties";
import CustomNavbar from "@/components/utils/CustomNavbar.vue";

export default {
  name: 'Authors',
  components: {CustomNavbar},

  data() {
    return {
      authors: {
        author1: "",
        author2: "",
        datetime: "",
        javaVersion: "",
        userName: "",
        osName: "",
        localAddress: ""
      },
      serverAvailable: true,
    }
  },

  mounted() {
    this.getAuthors()
  },
  methods: {
    async getAuthors() {
      try {
        const response = await fetch(`${apiLocation}/get/authors`)
        let authors = await response.json()
        this.authors = authors
        this.serverAvailable = true
      } catch (error) {
        console.error(error)
        this.serverAvailable = false
      }
    },
  },
}
</script>

<style scoped>

h1 {
  font-size: 24px;
  margin-bottom: 10px;
}

h3 {
  font-size: 18px;
  margin-top: 30px;
}

.error-message {
  color: #d33c40;
}

</style>
