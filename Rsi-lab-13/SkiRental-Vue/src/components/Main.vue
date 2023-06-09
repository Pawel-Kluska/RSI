<template>
  <div>
    <CustomNavbar/>

    <div class="container mt-5">
      <h1 class="display-4">Wypożyczalnia "Górska przygoda Ski rental"</h1>
      <p class="lead">Znajdź idealny sprzęt dla twojej przygody na stoku.</p>
      <a class="btn btn-primary btn-lg" href="/borrowings" role="button">Sprawdź ofertę</a>

      <div class="row mt-5">
        <div class="col-md-6">
          <h2>Wypożyczalnia Sprzętu Narciarskiego</h2>
          <p>Górska Przygoda Ski Rental to profesjonalna wypożyczalnia sprzętu narciarskiego, która zapewni Ci
            niezapomniane doświadczenie na stoku. Oferujemy najnowsze modele nart, wiązań, kasków, gogli i innych
            akcesoriów, aby zapewnić Ci pełne bezpieczeństwo i komfort podczas Twojej przygody na górskich stokach.
            Wybierz Górska Przygoda Ski Rental i ciesz się idealnym sprzętem narciarskim, który sprawi, że Twoje
            narciarskie wakacje będą niezapomniane."</p>
        </div>
        <div class="col-md-6">
          <img
              src="https://www.travelandleisure.com/thmb/rkS-yRT9Ss55H-DINCPYEWDXQ-k=/1500x0/filters:no_upscale():max_bytes(150000):strip_icc()/best-ski-gear-tested-and-reviewed-2-tout-69f254f29c41437dad0ec03aab3e7b47.jpg"
              alt="Sprzęt narciarski" class="img-fluid">
        </div>
      </div>
    </div>
  </div>
</template>


<script>
import EquipmentForm from "@/components/equipment/EquipmentForm.vue";
import CategoryTable from "@/components/category/CategoryTable.vue";
import CustomNavbar from "@/components/utils/CustomNavbar.vue";

export default {
  name: "Main",
  components: {CustomNavbar, CategoryTable, EquipmentForm}
}
</script>

<style scoped>
img {
  border-radius: 30px;
}

</style>
