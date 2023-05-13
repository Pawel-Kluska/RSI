const filterSelect = document.getElementById('filter');
const filterValueInput = document.getElementById('filter-value');
const filterButton = document.getElementById('filter-button');
const clearFilterButton = document.getElementById('clear-filter');

// Event listeners
filterButton.addEventListener('click', async () => {
    const attribute = filterSelect.value;
    const value = filterValueInput.value;
    const entities = await getPersons();
    const filteredEntities = filterEntityList(entities, attribute, value);
    renderPersonList(filteredEntities);
});

clearFilterButton.addEventListener('click', async () => {
    filterSelect.value = '';
    filterValueInput.value = '';
    const entities = await getPersons();
    renderPersonList(entities);
});


// Filter entity list
const filterEntityList = (entities, attribute, value) => {
    if (!attribute || !value) {
        return entities;
    }
    return entities.filter((entity) => {
        return entity[attribute].toLowerCase().includes(value.toLowerCase());
    });
};
