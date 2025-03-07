### Escuela Colombiana de Ingeniería

### Arquitectura Empresarial - AREP

#  TALLER PATRONES ARQUITECTURALES

Este proyecto es un sistema CRUD simple para la gestión de propiedades inmobiliarias mediante una aplicación web. El frontend está desarrollado con HTML y JavaScript, proporcionando formularios para que los usuarios puedan crear, ver, actualizar y eliminar anuncios de propiedades. Se comunica con un backend en Spring Boot a través de APIs RESTful. El backend gestiona las propiedades e interactúa con una base de datos MySQL utilizando JPA/Hibernate para la persistencia de datos. Cada propiedad cuenta con un ID generado automáticamente, dirección, precio, tamaño y descripción. El sistema se despliega en AWS con el backend y la base de datos en servidores separados. Funcionalidades adicionales incluyen paginación, búsqueda y retroalimentación al usuario sobre las operaciones.


## Empezando

Estas instrucciones te permitirán obtener una copia del proyecto y ejecutarlo en tu máquina local para propósitos de desarrollo y pruebas.

### Prerequisitos

- Java 17 preferiblemente.
- Maven 3.x
- Acceso a una terminal.

### Instalando

Pasos para configurar el entorno de desarrollo:

1. Clona el repositorio del proyecto:

   ```bash
   git clone https://github.com/MiltonGutierrez/AREP-TALLER-5.git
   cd AREP-TALLER-5
   ```

### Creacion de imagenes de docker

Para poder crear un contenedor primero creamos el Dockerfile y para poder crear la imagen se ejecuta el siguiente comando 
   ```bash
   docker build --tag arep5 .
   ```
![image](https://github.com/user-attachments/assets/4110a73e-d698-49c9-a374-7afff262448b)


Una vez creada la imagen se crea el contenedor con el siguiente comando (Se requiere crear un .env con los valores de url, nombre de db, usuario y contraseña para que funcione correctamente):

   ```bash
   docker run --env-file .env -d -p 8087:8080 --name arep5c arep5i
   ```

![image](https://github.com/user-attachments/assets/3bfaef9b-4e28-4ab8-ab96-2cc413fd9dc9)


Accede al aplicativo local mediante el contenedor docker desde tu navegador en [http://localhost:8087](http://localhost:8087).

## Arquitectura


### Diagrama de despliegue

El siguiente diagrama de despliegue describe la estructura básica de la aplicación.

![Deployment](https://github.com/user-attachments/assets/b6b85b9a-a19e-4865-b59b-927f113c37f3)

### Descripción del Diagrama de Despliegue

#### 1. **WebClient**
   - **Puerto 8087**: Punto de entrada para las solicitudes HTTP desde el navegador.

#### 2. **EC2 INSTANCE (Aplicación en Contenedor Docker)**
   - **FrontEnd**: Se encarga de la interfaz de usuario y la comunicación con el backend.
   - **BackEnd**: Procesa las peticiones del frontend, maneja la lógica de negocio y se comunica con la base de datos.

#### 3. **EC2 INSTANCE (Base de Datos MySQL en Contenedor Docker)**
   - **DockerContainer con MySQLDB**: Contenedor que aloja la base de datos MySQL para almacenamiento y consulta de datos.
   - **Puerto 3307**: Comunicación entre el backend y la base de datos MySQL.

---

### **Flujo de la Aplicación**
1. El **navegador** del usuario (WebClient) envía solicitudes HTTP al backend a través del puerto **8087**.
2. El **FrontEnd** procesa la solicitud y, si es necesario, la envía al **BackEnd** dentro de la instancia EC2.
3. El **BackEnd** maneja la lógica de negocio y, si requiere acceso a datos, consulta la base de datos **MySQL** a través del puerto **3307**.
4. La base de datos MySQL devuelve la información solicitada al **BackEnd**, que la procesa y envía una respuesta al **FrontEnd**.
5. El **FrontEnd** actualiza la interfaz del usuario con la respuesta obtenida.

---


### Diagrama de componentes.
El siguiente diagrama de despliegue describe la estructura básica de la aplicación. Utilizando el patron MVC.

![Component](https://github.com/user-attachments/assets/6816960c-15e7-4796-b67a-998d52b7f844)


### 1. **WebClient**
   - **Browser**: Punto de entrada de la aplicación, donde los usuarios interactúan con la interfaz.
   - **Spring**: Enlace entre el navegador y la aplicación, que maneja las solicitudes HTTP.

### 2. **App**
   - Contiene los componentes del **FrontEnd** y **BackEnd**.

#### **FrontEnd**
   - **Html**: Archivos HTML que estructuran la interfaz de usuario.
   - **Js**: Archivos JavaScript que proporcionan interactividad y comunicación con el backend.

#### #**BackEnd**
   - **Model**: Define la estructura de datos utilizada en la aplicación.
   - **Service**: Implementa la lógica de negocio y orquesta la interacción con los repositorios.
   - **Controller**: Gestiona las solicitudes HTTP y delega el procesamiento a los servicios.
   - **Repository**: Componente que interactúa con la base de datos.
   - **JPARepository**: Implementación de acceso a datos que utiliza JPA para comunicarse con la base de datos.

### 3. **Base de Datos**
   - **MySqlDb**: Sistema de almacenamiento de datos utilizado por la aplicación.

---

## **Flujo de la Aplicación**
1. El **navegador** envía solicitudes a través de **Spring** al backend.
2. El **Controller** recibe la solicitud y la pasa a los **Services**.
3. Los **Services** interactúan con el **Repository** para acceder a los datos.
4. El **Repository** utiliza **JPARepository** para realizar consultas en la base de datos **MySqlDb**.
5. Los datos recuperados se devuelven al **Controller**, que genera una respuesta para el **FrontEnd**.
6. El **FrontEnd** muestra los datos en la interfaz utilizando **Html** y **Js**.

---


### Funcionamiento.



https://github.com/user-attachments/assets/36d71a5a-f8b8-492a-9741-48147fdb710c




# Tecnologías Usadas en Pruebas
- **JUnit Jupiter 5:** Para pruebas unitarias y parametrizadas.
- **Mockito:** Para pruebas unitarias sobre los componentes Controller y Service
- **Maven:** Gestión de dependencias y ejecución de pruebas.

- **Resultado de las pruebas**

Para correr las pruebas automatizadas utilize el comando **IMPORTANTE: Debe configurar las variables de entorno necesarias para realizar la conexión a la DB, de lo contrario habrán pruebas que fallen**.


```bash
   mvn clean test
```

![image](https://github.com/user-attachments/assets/10d04c55-0ac5-447e-ad60-61aa2384a98f)



## Construido con.

- [Maven](https://maven.apache.org/) - Dependency Management

## Autores

- **Milton Andres Gutierrez Lopez** - *Initial work* - [MiltonGutierrez](https://github.com/MiltonGutierrez)

## Licencia

Este proyecto está licenciado bajo la Licencia GNU - mira el archivo [LICENSE.md](LICENSE.md) para más detalles.
