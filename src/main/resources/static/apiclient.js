const apiClient = (() => {
    const url = "http://localhost:8080/api/";

    // GET
    const getProperties = async () => {
        const response = await fetch(url + 'property');
        return response.json();
    };

    const getPropertyById = async (id) => {
        const response = await fetch(url + `property/${id}`);
        return response.json();
    };

    // POST
    const createProperty = async (body) => {
        const response = await fetch(url + 'property', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: body
        });
        return response.json();
    };

    // PUT
    const updateProperty = async (id, body) => {
        const response = await fetch(url + `property/${id}`, {
            method: 'PUT',
            headers: { 'Content-Type': 'application/json' },
            body: body
        });
        return response.json();
    };

    // DELETE
    const deleteProperty = async (id) => {
        const response = await fetch(url + `property/${id}`, { method: 'DELETE' });
        return response.json();
    };

    return {
        getProperties,
        getPropertyById,
        createProperty,
        updateProperty,
        deleteProperty
    };
})();
