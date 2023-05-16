// Get all entities
// API endpoint
const apiUrl = 'http://192.168.43.30:8080';

const getPersons = async () => {

    const response = await fetch(`${apiUrl}/persons`);
    const data = await response.json();
    return data;

};

// Get entity by id
const getPersonById = async (id) => {

    const response = await fetch(`${apiUrl}/persons/${id}`);
    if (response.status === 404) {
        throw new Error('Person not found with id: ' + id);
    }
    const data = await response.json();
    return data;

};

// Create entity
const createPerson = async (person) => {

    const response = await fetch(`${apiUrl}/persons`, {
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
    const response = await fetch(`${apiUrl}/persons`, {
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
    (`${apiUrl}/persons/${id}`, {
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
        const response = await fetch(`${apiUrl}/persons/count`);
        const data = await response.json();
        return data;
    } catch (error) {
        console.error(error);
    }
};

// Get authors
const getAuthors = async () => {
    const response = await fetch(`${apiUrl}/authors`);
    const data = await response.json();
    return data;
};
