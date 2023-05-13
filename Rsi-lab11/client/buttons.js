const idInput = document.getElementById('id');
const nameInput = document.getElementById('name');
const ageInput = document.getElementById('age');
const emailInput = document.getElementById('email');

const idInput2 = document.getElementById('id2');

const getAllButton = document.getElementById('getAll');
const countButton = document.getElementById('count');

const createButton = document.getElementById('create');
const updateButton = document.getElementById('update');

const getByIdButton = document.getElementById('getById');
const deleteButton = document.getElementById('delete');


getAllButton.addEventListener('click', async () => {
    try {
        const entities = await getPersons();
        renderPersonList(entities);
    } catch (error) {
        alert(error.message);
    }
});

countButton.addEventListener('click', async () => {
    try {
        const count = await countPersons();

        alert(`Total number of entities: ${count}`);
    } catch (error) {
        alert(error.message);
    }
});

createButton.addEventListener('click', async () => {
    try {
        const entity = {
            id: idInput.value,
            name: nameInput.value,
            age: ageInput.value,
            email: emailInput.value
        };
        const createdEntity = await createPerson(entity);
        const entities = await getPersons();
        renderPersonList(entities);
    } catch (error) {
        alert(error.message);
    }
});

updateButton.addEventListener('click', async () => {
    try {
        const entity = {
            id: idInput.value,
            name: nameInput.value,
            age: ageInput.value,
            email: emailInput.value
        };
        const updatedEntity = await updatePerson(entity);
        const entities = await getPersons();
        renderPersonList(entities);
    } catch (error) {
        alert(error.message);
    }
});

getByIdButton.addEventListener('click', async () => {
    try {
        const id = idInput2.value;
        const entity = await getPersonById(id);
        renderPersonList([entity])
    } catch (error) {
        alert(error.message);
    }
});

deleteButton.addEventListener('click', async () => {
    try {
        const id = idInput2.value;
        const isDeleted = await deletePerson(id);
        const entities = await getPersons();
        renderPersonList(entities);
    } catch (error) {
        alert(error.message);
    }

});