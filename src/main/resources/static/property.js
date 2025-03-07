const property = (() => {

    let api = apiClient;

    const getProperties = async () => {
        try {
            let properties = await api.getProperties();
            console.log(properties);
            let propertyList = document.getElementById("propertyList");
            propertyList.innerHTML = ""; 

            properties.forEach(property => {
                const propertyItem = document.createElement("div");
                propertyItem.classList.add("property-item");
                propertyItem.innerHTML = `
                    <p><strong>Address:</strong> ${property.address}</p>
                    <p><strong>Price:</strong> $${property.price.toFixed(2)}</p>
                    <p><strong>Size:</strong> ${property.size} m²</p>
                    <p><strong>Description:</strong> ${property.description}</p>
                    <button class="update-btn" onclick="property.updateProperty(${property.id})">Update</button>
                    <button class="delete-btn" onclick="property.deleteProperty(${property.id})">Delete</button>
                `;
                propertyList.appendChild(propertyItem);
            });
        } catch (error) {
            alert(error);
        }
    };

    

    const getPropertyById = async (id) => {
        try {
            let response = await api.getPropertyById(id);
            console.log(response);
            return response;
        } catch (error) {
            alert(error);
        }
    };

    const createProperty = async () => {
        try {
            let address = document.getElementById('address').value;
            let price = document.getElementById('price').value;
            let size = document.getElementById('size').value;
            let description = document.getElementById('description').value;
            if(!address || !price || !size || !description) {
                throw new Error('All fields are required');
            }
            console.log(address, price, size, description);
            let body  = JSON.stringify({
                address: address,
                price: price,
                size: size,
                description: description
            });
            let response = await api.createProperty(body);
            console.log(response);
            return response;
        } catch (error) {
            alert(error);
        }
    }

    const updateProperty = async (id, address, price, size, description) => {
        try {
            let body  = JSON.stringify({
                address: address,
                price: price,
                size: size,
                description: description
            });
            let response = await api.updateProperty(id, body);
            console.log(response);
            return response;
        } catch (error) {
            alert(error);
        }
    }

    const deleteProperty = async (id) => {
        try {
            let response = await api.deleteProperty(id);
            console.log(response);
            return response;
        } catch (error) {
            alert(error);
        }
    }

    return {
        getProperties,
        getPropertyById,
        createProperty,
        updateProperty,
        deleteProperty

    };

})();