<template>
  <CustomNavbar></CustomNavbar>
  <div id="categories-table" class="container bg-light pt-3 mt-5 mb-5 pb-3 rounded-3">
    <h4 v-if="!this.serverAvailable" class="error-message">
      Nie udało się nawiązać połączenia z serwerem
    </h4>

    <table id="table">
      <thead>
      <tr class="header">
        <th>Nazwa</th>
        <th>Usuń</th>
        <th>Aktualizuj</th>
      </tr>
      </thead>
      <tbody>
      <tr v-for="(category, index) in paginatedCategories()" :key="category.id">
        <td>{{ category.name }}</td>
        <td>
          <button class="btn btn-danger" v-if="category.id !== ''" @click="deleteCategory(category.id)">Usuń</button>
        </td>
        <td>
          <button class="btn btn-primary " v-if="category.id !== ''"
                  @click="this.$router.push({ name: 'categoryUpdate', params: { id: category.id } });">Aktualizuj
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
    <CategoryForm :categoriesSource="categories" class="mt-5"/>
  </div>
</template>

<script>
import axios from "axios";
import CategoryForm from "@/components/category/CategoryForm.vue";
import CustomNavbar from "@/components/utils/CustomNavbar.vue";
import {apiLocation} from "@/components/utils/Properties";

export default {
  name: 'CategoryTable',
  components: {CustomNavbar, CategoryForm},

  data() {
    return {
      categories: [],
      currentPage: 1,
      itemsPerPage: 3,
      serverAvailable: true,
    }
  },

  mounted() {
    this.getCategories()
  },
  methods: {
    paginatedCategories() {
      const startIndex = (this.currentPage - 1) * this.itemsPerPage;
      const endIndex = startIndex + this.itemsPerPage;
      let slicedEquipments = this.categories.slice(startIndex, endIndex);
      if (slicedEquipments.length === 0 && this.currentPage > 1) {
        this.currentPage--;
      }
      while (slicedEquipments.length < this.itemsPerPage) {
        slicedEquipments.push({
          id: '',
          name: '',
        });
      }
      return slicedEquipments;
    },
    async getCategories() {
      try {
        const response = await fetch(`${apiLocation}/get/categories`)
        const data = await response.json()
        this.categories = data
        this.categories.sort((a, b) => (a.id > b.id) ? 1 : -1)
        this.serverAvailable = true;
      } catch (error) {
        console.error(error)
        this.serverAvailable = false;
      }
    },
    async deleteCategory(itemId) {
      axios.delete(`${apiLocation}/delete/category/${itemId}`)
          .then(response => {
            console.log(response.data);
            this.categories = this.categories.filter(obj => {
              return obj.id !== itemId
            });
          })
          .catch(error => {
            alert("Nie można usunąć kategorii, ponieważ jest przypisana do sprzętu!")
            console.error(error);
          });
    }
  },
  computed: {
    totalPages() {
      return Math.ceil(this.categories.length / this.itemsPerPage);
    }
  },

}
</script>

<style scoped>

tr {
  height: 60px;
}

.header {
  height: 50px;
}

.error-message {
  color: #d33c40;
}
</style>
