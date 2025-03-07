const apiClient = (() => {

    const url = "http://localhost:8080/api/";

    //Get

    const getProperties = async () => {
        let response = await $.ajax({
            url: url + 'property',
            method: 'GET',
          });
        return response;
    };

    const getPropertyById = async (id) => {
        let response = await $.ajax({
            url: url + 'property/' + id,
            method: 'GET',
          });
        return response;
    }

    //POST

    const createProperty = async (body) => {
        let response = await $.ajax({
            url: url + 'property',
            method: 'POST',
            data: body,
            contentType: 'application/json'
        });
        return response;
        
    };

    //PUT
    const updateProperty = async (body) => {
        let response = await $.ajax({
            url: url + 'property/' + id,
            method: 'PUT',
            data: body,
            contentType: 'application/json'
        });
        return response;
    }

    const deleteProperty = async (id) => {
        let response = await $.ajax({
            url: url + 'property/' + id,
            method: 'DELETE',
        });
        return response;
    }

    return {
        getProperties,
        getPropertyById,
        createProperty,
        updateProperty
    }
    
})();