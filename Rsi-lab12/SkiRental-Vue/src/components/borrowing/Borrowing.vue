<template>
  <CustomNavbar></CustomNavbar>
  <div class="container bg-light pt-3 mt-5 mb-5 pb-5 rounded-3">
    <div class="container-fluid mt-3">
      <h3>Filtruj</h3>
      <select v-model="currentCategory" @change="updateView()">
        <option @focus="clearStatus">Wszystkie</option>
        <option
            v-for="c in categories" :key="c.id"
            @focus="clearStatus"
        >
          {{ c.name }}
        </option>
      </select>
      <h2>Liczba aktualnie dostępnych sprzętów w kategorii:
        {{ this.getAmountOfAvaliableEquipment() }} / {{ this.equipments.length}}</h2>
      <div class="row mt-3">
        <div v-for="item in this.equipments" :key="item.id" class="col-12 col-sm-6 col-md-4 col-lg-3 mb-5">
          <div class="card h-100">
            <div>
              <p style="color: #32a95d" v-if="!item.borrowed">Dostępny</p>
              <p style="color: #d33c40" v-if="item.borrowed">Niedostępny</p>
            </div>
            <div class="h-75 d-flex align-items-center justify-content-center">
              <img :src="item.image" alt="Item Image" class="card-img-top">
            </div>
            <div class="card-body text-center">
              {{ item.name }}
              <br>
              Rozmiar: {{ item.size }}
              <br>
              Cena: {{ item.price }} zł
            </div>
            <button v-if="!item.borrowed" class="btn btn-primary" @click="borrowEquipment(item.id)">Wypożycz</button>
            <button v-if="item.borrowed" class="btn btn-info" @click="borrowEquipment(item.id)">Oddaj</button>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>


<script>

import CustomNavbar from "@/components/utils/CustomNavbar.vue";
import EquipmentForm from "@/components/equipment/EquipmentForm.vue";
import {apiLocation} from "@/components/utils/Properties";

export default {
  name: 'Borrowing',
  components: {CustomNavbar, EquipmentForm},

  data() {
    return {
      equipments: [],
      categories: [],
      amountOfBorrowedEquipment: '',
      currentCategory: 'Wszystkie',
    }
  },

  mounted() {
    this.getCategories()
    this.getEquipments()
    this.getAmountOfBorrowedEquipment()
  },
  methods: {
    async getEquipments() {
      try {
        const response = await fetch(`${apiLocation}/get/equipments`)
        let equipments = await response.json()
        this.equipments = equipments
        this.equipments.sort((a, b) => (a.id > b.id) ? 1 : -1)
      } catch (error) {
        console.error(error)
      }
    },
    async getCategories() {
      try {
        const response = await fetch(`${apiLocation}/get/categories`)
        this.categories = await response.json()
      } catch (error) {
        console.error(error)
      }
    },

    async getEquipmentsByCategory() {
      try {
        const currentCategory = this.categories.find(c => c.name === this.currentCategory)
        const response = await fetch(`${apiLocation}/get/equipments/category/${currentCategory.id}`)
        this.equipments = await response.json()
        this.equipments.sort((a, b) => (a.id > b.id) ? 1 : -1)

      } catch (error) {
        console.error(error)
      }
    },

    async updateView() {
      if (this.currentCategory === 'Wszystkie') {
        await this.getEquipments()
        await this.getAmountOfBorrowedEquipment()
      } else {
        await this.getEquipmentsByCategory()
        await this.getAmountOfBorrowedEquipmentByCategory()
      }
    },
    async borrowEquipment(id) {
      try {
        await fetch(`${apiLocation}/change-borrow/equipment/${id}`)
        await this.updateView()
      } catch (error) {
        console.error(error)
      }
    },

    async getAmountOfBorrowedEquipment() {
      try {
        const response = await fetch(`${apiLocation}/get/equipments/countBorrowed`)
        const data = await response.json()
        this.amountOfBorrowedEquipment = data
      } catch (error) {
        console.error(error)
      }
    },

    async getAmountOfBorrowedEquipmentByCategory() {
      try {
        const currentCategory = this.categories.find(c => c.name === this.currentCategory)
        const response = await fetch(`${apiLocation}/get/equipments/countBorrowed/category/${currentCategory.id}`)
        const data = await response.json()
        this.amountOfBorrowedEquipment = data
        console.log(this.amountOfBorrowedEquipment)
      } catch (error) {
        console.error(error)
      }
    },

    getAmountOfAvaliableEquipment() {
      return this.equipments.length - this.amountOfBorrowedEquipment
    },

    clearStatus() {
      this.success = false
      this.error = false
    },
  },
}
</script>


<style scoped>

</style>
