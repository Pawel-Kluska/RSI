<template>
  <CustomNavbar></CustomNavbar>
  <div id="person-form" class="container bg-light pt-3 mt-5 mb-5 pb-1 rounded-3">
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
      <button class="btn btn-primary mt-4">Aktualizuj</button>
    </form>
  </div>
</template>
<script>
import axios from "axios";
import CustomNavbar from "@/components/utils/CustomNavbar.vue";
import {apiLocation} from "@/components/utils/Properties";

export default {
  name: 'EquipmentUpdate',
  components: {CustomNavbar},
  data() {
    return {
      submitting: false,
      error: false,
      success: false,
      equipment: {
        id: 0,
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
    this.getEquipment()
  },
  methods: {

    async getCategories() {
      try {
        const response = await fetch(`${apiLocation}/get/categories`)
        this.categories = await response.json()
      } catch (error) {
        console.error(error)
      }
    },

    async getEquipment() {
      axios.get(`${apiLocation}/get/equipment/${this.equipmentId()}`)
          .then(response => {
            this.equipment = response.data
            this.equipment.category = response.data.category.name
          })
          .catch(error => {
            console.error(error);
          })
    },

    async postData() {
      try {
        const category = this.categories.filter(obj => {
          return obj.name === this.equipment.category
        });
        console.log(category[0])
        const response = await axios.patch(`${apiLocation}/patch/equipment`, {
          id: this.equipment.id,
          name: this.equipment.name,
          size: this.equipment.size,
          price: this.equipment.price,
          description: this.equipment.description,
          image: this.equipment.image,
          isBorrowed: false,
          category: category[0],
        })
        this.$router.push({name: 'equipments'});
        console.log(response.data)
      } catch (error) {
        console.error(error)
      }
    },

    equipmentId() {
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
  margin-bottom: 2rem;
}

label {
  display: block;
  margin-bottom: 0.5rem;
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
