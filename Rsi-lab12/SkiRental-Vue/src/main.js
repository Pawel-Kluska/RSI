import {createApp} from 'vue'
import {createRouter, createWebHistory} from "vue-router";
import App from './App.vue'

import 'bootstrap/dist/css/bootstrap.css'
import 'bootstrap-vue/dist/bootstrap-vue.css'
import 'bootstrap/dist/js/bootstrap.js';

import './assets/main.css'
import EquipmentTable from "@/components/equipment/EquipmentTable.vue";
import CategoryTable from "@/components/category/CategoryTable.vue";
import CategoryUpdate from "@/components/category/CategoryUpdate.vue";
import EquipmentUpdate from "@/components/equipment/EquipmentUpdate.vue";
import Main from "@/components/Main.vue";
import Borrowing from "@/components/borrowing/Borrowing.vue";

const router = createRouter({
    history: createWebHistory(),
    routes: [
        {
            path: '/',
            component: Main,
        },
        {
            path: '/equipments',
            name: 'equipments',
            component: EquipmentTable,
        },
        {
            path: '/categories',
            name: 'categories',
            component: CategoryTable,
        },
        {
            path: '/category-update/:id',
            name: 'categoryUpdate',
            component: CategoryUpdate,
        },
        {
            path: '/equipment-update/:id',
            name: 'equipmentUpdate',
            component: EquipmentUpdate,
        },
        {
            path: '/borrowings',
            name: 'borrowings',
            component: Borrowing,
        }
    ]
})

createApp(App).use(router).mount('#app')
