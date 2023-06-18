<template>
  <div id="person-form" class="container">
    <h1>Dodaj nowy sprzęt</h1>
    <form @submit.prevent="handleSubmit">
      <label>Nazwa</label>
      <input
          v-model="equipment.name"
          type="text"
          :class="{ 'has-error': submitting && invalidName}"
          @focus="clearStatus"
          @keypress="clearStatus"
      />
      <p v-if="submitting && invalidName" class="error-message">
        Nazwa nie może być pusta
      </p>

      <label>Rozmiar</label>
      <input
          v-model="equipment.size"
          type="text"
          :class="{ 'has-error': submitting && invalidSize}"
          @focus="clearStatus"
          @keypress="clearStatus"
      />
      <p v-if="submitting && invalidSize" class="error-message">
        Rozmiar nie może być pusty
      </p>

      <label>Cena</label>
      <input
          v-model="equipment.price"
          type="number" step="0.01"
          :class="{ 'has-error': submitting && invalidPrice}"
          @focus="clearStatus"
          @keypress="clearStatus"
      />
      <p v-if="submitting && invalidPrice" class="error-message">
        Cena musi być liczbą większą od 0.
      </p>

      <label>Opis</label>
      <input
          v-model="equipment.description"
          type="text"
          @focus="clearStatus"
          @keypress="clearStatus"
      />

      <label>Zdjęcie</label>
      <input
          v-model="equipment.image"
          type="text"
          :class="{ 'has-error': submitting && invalidImage}"
          @focus="clearStatus"
          @keypress="clearStatus"
      />
      <p v-if="submitting && invalidImage" class="error-message">
        Proszę podać link do zdjęcia.
      </p>

      <label>Kategoria</label>
      <select v-model="equipment.category"
              :class="{ 'has-error': submitting && invalidCategory}">
        <option
            v-for="c in categories" :key="c.id"
            @focus="clearStatus"
        >
          {{ c.name }}
        </option>
      </select>
      <p v-if="submitting && invalidCategory" class="error-message">
        Proszę wybrać kategorię.
      </p>

      <p v-if="error && submitting" class="error-message">
        Proszę wypełnić wskazane pola formularza.
      </p>
      <p v-if="success" class="success-message">
        Dane poprawnie zapisano.
      </p>
      <button class="btn btn-primary mt-4">Dodaj sprzęt</button>
    </form>
  </div>
</template>
<script>
import axios from "axios";
import {apiLocation} from "@/components/utils/Properties";

export default {
  name: 'EquipmentForm',
  props: {
    equipmentsSource: Array,
  },
  data() {
    return {
      submitting: false,
      error: false,
      success: false,
      equipment: {
        name: '',
        size: '',
        price: '',
        description: '',
        image: '',
        isBorrowed: '',
        category: '',
      },
      categories: [],
    }
  },
  mounted() {
    this.getCategories()
  },
  methods: {

    async getCategories() {
      try {
        const response = await fetch(`${apiLocation}/get/categories`)
        const data = await response.json()
        this.categories = data
      } catch (error) {
        console.error(error)
      }
    },

    async postData() {
      try {
        const category = this.categories.filter(obj => {
          return obj.name === this.equipment.category
        });
        const response = await axios.post(`${apiLocation}/post/equipment`, {
          name: this.equipment.name,
          size: this.equipment.size,
          price: this.equipment.price,
          description: this.equipment.description,
          image: this.equipment.image,
          borrowed: false,
          category: category[0]
        })
        console.log(response.data)
        const savedEquipment = await fetch(`${apiLocation}/get/equipments`)
        const equipments = await savedEquipment.json()
        this.equipmentsSource.push(equipments[equipments.length - 1])
      } catch (error) {
        console.error(error)
      }
    },

    handleSubmit() {
      this.submitting = true
      this.clearStatus()

      if (this.invalidName || this.invalidSize || this.invalidPrice || this.invalidImage || this.invalidCategory) {
        this.error = true
        return
      }
      this.postData()

      this.equipment = {
        name: '',
        size: '',
        price: '',
        description: '',
        image: '',
        isBorrowed: '',
        category: '',
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
      return this.equipment.name === ''
    },

    invalidSize() {
      return this.equipment.size === ''
    },

    invalidPrice() {
      return this.equipment.price < 0 || this.equipment.price === ''
    },

    invalidImage() {
      return this.equipment.image === ''
    },

    invalidCategory() {
      return this.equipment.category === null || this.equipment.category === ''
    },

  },
}
</script>
<style scoped>
form {
  margin: 0 auto;
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


label {
  display: block;
  margin-bottom: 0.5rem;
}
</style>
