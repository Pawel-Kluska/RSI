// Get all entities
// API endpoint
const apiUrl = 'http://localhost:8080/persons';

const getPersons = async () => {

    const response = await fetch(apiUrl);
    const data = await response.json();
    return data;

};

// Get entity by id
const getPersonById = async (id) => {

    const response = await fetch(`${apiUrl}/${id}`);
    if (response.status === 404) {
        throw new Error('Person not found with id: ' + id);
    }
    const data = await response.json();
    return data;

};

// Create entity
const createPerson = async (person) => {

    const response = await fetch(apiUrl, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(person)
    });
    if (response.status === 400) {
        throw new Error('Person already exists');
    }
    const data = await response.json();
    return data;

};

// Update entity
const updatePerson = async (person) => {
    const response = await fetch(`${apiUrl}`, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(person)
    });
    if (response.status === 404) {
        throw new Error('Person not found with id: ' + person.id);
    }

    const data = await response.json();
    return data;
};

// Delete entity
const deletePerson = async (id) => {
    const response = await fetch
    (`${apiUrl}/${id}`, {
        method: 'DELETE'
    });
    if (response.status === 404) {
        throw new Error('Person not found with id: ' + id);
    }
    const data = await response.json();
    return data;

};

// Count entities
const countPersons = async () => {
    try {
        const response = await fetch(`${apiUrl}/count`);
        const data = await response.json();
        return data;
    } catch (error) {
        console.error(error);
    }
};
