<template>
  <div id="person-form" class="container">
    <h1>Dodaj nową kategorię</h1>
    <form @submit.prevent="handleSubmit">
      <label>Nazwa</label>
      <input
          v-model="category.name"
          type="text"
          :class="{ 'has-error': submitting && invalidName }"
          @focus="clearStatus"
          @keypress="clearStatus"
      />
      <p v-if="error && submitting" class="error-message">
        Proszę wypełnić wskazane pola formularza
      </p>
      <p v-if="success" class="success-message">
        Dane poprawnie zapisano
      </p>
      <button class="btn btn-primary mt-2">Dodaj kategorię</button>
    </form>
  </div>
</template>
<script>

import axios from "axios";
import {apiLocation} from "@/components/utils/Properties";

export default {
  name: 'CategoryForm',
  props: {
    categoriesSource: Array,
  },
  data() {
    return {
      submitting: false,
      error: false,
      success: false,
      category: {
        name: '',
      },
    }
  },
  methods: {

    async postData() {
      try {
        const response = await axios.post(`${apiLocation}/post/category`, {
          name: this.category.name,
        })
        console.log(response.data)
        const savedCategories = await fetch(`${apiLocation}/get/categories`)
        const categories = await savedCategories.json()
        this.categoriesSource.push(categories[categories.length - 1])
      } catch (error) {
        console.error(error)
      }
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
