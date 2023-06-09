<template>
  <CustomNavbar></CustomNavbar>
  <div id="category-form" class="container bg-light pt-3 mt-5 mb-5 pb-5 rounded-3">
    <form @submit.prevent="handleSubmit">
      <label>Nazwa</label>
      <input
          v-model="category.name"
          type="text"
          :class="{ 'has-error': submitting && invalidName }"
          @focus="clearStatus"
          @keypress="clearStatus"
          :placeholder="category.name"
      />
      <p v-if="error && submitting" class="error-message">
        Proszę wypełnić wskazane pola formularza
      </p>
      <p v-if="success" class="success-message">
        Dane poprawnie zapisano
      </p>
      <button class="btn btn-primary">Dodaj kategorię</button>
    </form>
  </div>
</template>
<script>

import axios from "axios";
import CustomNavbar from "@/components/utils/CustomNavbar.vue";
import {apiLocation} from "@/components/utils/Properties";

export default {
  name: 'CategoryUpdate',
  components: {CustomNavbar},
  data() {
    return {
      submitting: false,
      error: false,
      success: false,
      category: {
        id: 0,
        name: '',
      },
    }
  },
  mounted() {
    this.getCategory()
  },
  methods: {

    async postData() {
      try {
        const response = await axios.patch(`${apiLocation}/patch/category`, {
              id: this.category.id,
              name: this.category.name,
            }
        )
        console.log(response.data)
        this.$router.push({ name: 'categories'});
      } catch (error) {
        console.error(error)
      }
    },

    async getCategory() {
      axios.get(`${apiLocation}/get/category/${this.categoryId()}`)
          .then(response => {
            // Obsługa odpowiedzi serwera
            console.log(response.data);
            this.category.id = response.data.id,
            this.category.name = response.data.name;
          })
          .catch(error => {
            // Obsługa błędów
            console.error(error);
          })
    },

    categoryId() {
      return this.$route.params.id;
    },

    handleSubmit() {
      this.submitting = true
      this.clearStatus()

      if (this.invalidName) {
        this.error = true
        return
      }
      this.postData()
      this.category = {
        name: '',
      }
      this.error = false
      this.success = true
      this.submitting = false
    },
    clearStatus() {
      this.success = false
      this.error = false
    },

  },
  computed: {
    invalidName() {
      return this.category.name === ''
    },
  },
}
</script>
<style scoped>
form {
  margin-bottom: 2rem;
}

[class*='-message'] {
  font-weight: 500;
}

.error-message {
  color: #d33c40;
}

.success-message {
  color: #32a95d;
}
</style>
