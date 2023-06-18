<template>
  <CustomNavbar></CustomNavbar>
  <div id="equipments-table" class="container bg-light mt-5 mb-5 pb-3 rounded-3">
    <h4 v-if="!this.serverAvailable" class="error-message mt-5 pt-3">
      Nie udało się nawiązać połączenia z serwerem
    </h4>
    <table id="table" class="table">
      <thead>
      <tr class="header">
        <th>Nazwa</th>
        <th>Rozmiar</th>
        <th>Cena</th>
        <th>Opis</th>
        <th>Zdjęcie</th>
        <th>Kategoria</th>
        <th>Czy wypożyczona</th>
        <th>Usuń</th>
        <th>Aktualizuj</th>
      </tr>
      </thead>
      <tbody>
      <tr v-for="(eq, index) in paginatedEquipments()" :key="eq.id">
        <td>{{ eq.name }}</td>
        <td>{{ eq.size }}</td>
        <td>{{ eq.price }}</td>
        <td>{{ eq.description }}</td>
        <td>{{ eq.image }}</td>
        <td>{{ eq.category.name }}</td>
        <td>{{ eq.borrowed }}</td>
        <td>
          <button v-if="eq.id !== ''" @click="deleteEquipment(eq.id)" class="btn btn-danger">Usuń</button>
        </td>
        <td>
          <button class="btn btn-primary" v-if="eq.id !== ''"
                  @click="this.$router.push({ name: 'equipmentUpdate', params: { id: eq.id } });">
            Aktualizuj
          </button>
        </td>
      </tr>
      </tbody>
    </table>

    <div class="pagination">
      <button class="btn btn-primary" :disabled="currentPage === 1" @click="currentPage--">Prev</button>
      <span class="ms-3 me-3">Page {{ currentPage }} of {{ totalPages }}</span>
      <button class="btn btn-primary" :disabled="currentPage === totalPages" @click="currentPage++">Next</button>
    </div>
    <EquipmentForm :equipmentsSource="equipments" class="mt-5"/>
  </div>


</template>

<script>
import axios from "axios";
import EquipmentForm from "@/components/equipment/EquipmentForm.vue";
import CustomNavbar from "@/components/utils/CustomNavbar.vue";
import {apiLocation} from "@/components/utils/Properties";

export default {
  name: 'EquipmentTable',
  components: {CustomNavbar, EquipmentForm},

  data() {
    return {
      equipments: [],
      currentPage: 1,
      itemsPerPage: 5,
      serverAvailable: true,
    }
  },

  mounted() {
    this.getEquipments()
  },
  methods: {
    async getEquipments() {
      try {
        const response = await fetch(`${apiLocation}/get/equipments`)
        const data = await response.json()
        this.equipments = data
        this.equipments.sort((a, b) => (a.id > b.id) ? 1 : -1)
        this.serverAvailable = true;
      } catch (error) {
        console.error(error)
        this.serverAvailable = false;
      }
    },
    paginatedEquipments() {
      const startIndex = (this.currentPage - 1) * this.itemsPerPage;
      const endIndex = startIndex + this.itemsPerPage;
      let slicedEquipments = this.equipments.slice(startIndex, endIndex);
      if (slicedEquipments.length === 0 && this.currentPage > 1) {
        this.currentPage--;
      }
      while (slicedEquipments.length < this.itemsPerPage) {
        slicedEquipments.push({
          id: '',
          name: '',
          size: '',
          price: '',
          description: '',
          image: '',
          isBorrowed: '',
          category: {
            name: '',
          },});
      }
      return slicedEquipments;
    },
    async deleteEquipment(itemId) {
      axios.delete(`${apiLocation}/delete/equipment/${itemId}`)
          .then(response => {
            console.log(response.data);
          })
          .catch(error => {
            console.error(error);
          });
      this.equipments = this.equipments.filter(obj => {
        return obj.id !== itemId
      });
    }

  },
  computed: {
    totalPages() {
      return Math.ceil(this.equipments.length / this.itemsPerPage);
    }
  },
}

</script>

<style scoped>
tr {
  height: 100px;
}

.header {
  height: 50px;
}

.error-message {
  color: #d33c40;
}

</style>
