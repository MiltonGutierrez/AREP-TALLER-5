const property = (() => {

    let api = apiClient;

    const getProperties = async () => {
        try {
            const propertyList = document.getElementById("propertyList");
            propertyList.innerHTML = ""; 
            const properties = api.getProperties();

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

    const buttonFilterAll = () => {
        searchForm.innerHTML = `<button type="submit">Buscar Todas</button>`;
    };

    const buttonFilterByPriceRange = () => {
        searchForm.innerHTML = `
        <label for="minPrice">Precio mínimo:</label>
        <input type="number" id="minPrice" name="minPrice" step="any" placeholder="Precio mínimo" required>
        <label for="maxPrice">Precio máximo:</label>
        <input type="number" id="maxPrice" name="maxPrice" step="any" placeholder="Precio máximo" required>
        <button type="submit">Buscar por rango de precio</button>
      `;
    }

    const buttonFilterBySizeRange = () => {
        searchForm.innerHTML = `
        <label for="minSize">Tamaño mínimo (m²):</label>
        <input type="number" id="minSize" name="minSize" step="any" placeholder="Tamaño mínimo" required>
        <label for="maxSize">Tamaño máximo (m²):</label>
        <input type="number" id="maxSize" name="maxSize" step="any" placeholder="Tamaño máximo" required>
        <button type="submit">Buscar por rango de tamaño</button>
      `;
    };

    const buttonFilterById = () => {
        searchForm.innerHTML = `
        <label for="searchId">ID:</label>
        <input type="number" id="searchId" name="searchId" step="1" placeholder="Ingrese el ID" required>
        <button type="submit">Buscar por ID</button>
      `;
    }

    const getPropertyById = async (id) => {
        try {
            let response = await api.getPropertyById(id);
            return response.json();
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
            return response.json();
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
            return response.json();
        } catch (error) {
            alert(error);
        }
    }

    const deleteProperty = async (id) => {
        try {
            let response = await api.deleteProperty(id);
            return response.json();
        } catch (error) {
            alert(error);
        }
    }

    return {
        getProperties,
        getPropertyById,
        createProperty,
        updateProperty,
        deleteProperty,
        buttonFilterAll,
        buttonFilterByPriceRange,
        buttonFilterBySizeRange,
        buttonFilterById

    };

})();