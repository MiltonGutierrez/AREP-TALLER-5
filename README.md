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

El siguiente diagrama de componentes describe la estructura básica de la aplicación, basada en el patrón **MVC (Modelo-Vista-Controlador)**:

![Componentes Arep](https://github.com/user-attachments/assets/fe00a142-d398-4d1b-a64e-62050092adec)

### Componentes Principales
1. **Browse**:
   - **Puerto 8080**: Punto de entrada para las solicitudes HTTP.

2. **NoteApplication (Lógica de Negocio - Modelo - Http - Server)**:
   - **Controller**: Procesa las peticiones del endpoint de /app, valida datos y coordina la interacción entre el servidor y los servicios.
   - **Services**: Implementan la lógica para operaciones CRUD de notas (crear, leer).
   - **Model**: Define la estructura de datos.
   - **Http**: Es un componente que contiene una implementación propia para representar las clases HttpRequest y HttpResponse.
   - **App**: indica al servidor la ubicacion de los archivos estaticos, y mediante funciones lambda se crean unos servicios get y post del controller, adicionalmente inicia el servicio spring.
   - **Server**: Contiene el servidor Http, MicroSpring y las anotaciones
  
### Flujo de la Aplicación:
1. El navegador envía solicitudes al **HttpServer** (puerto 8080, mediante el inicio del server en App) este procesa la peticion de archivos HTML, CSS, JS e imagenes..
2. El **Controller** recibe las solicitudes del endpoint /app, valida los parámetros y delega la lógica a los `Services` creando al final la respuesta.
3. El **MicroSpring** carga las clases con la anotación @RestController y los metodos, de manera que reciba las solicitudes al endpoint /spring *(en este caso get /hello, /note y post /note)*.
4. Los **Services** interactúan con el **Model** para acceder a la estructura de datos de modo que pueda responder a la petición..

### Diagrama de Clases y Explicación.
Se presentara el diagrama de clases que describe los métodos y las dependencias entre las clases existentes para cada componente del backend.

![Clases AREP](https://github.com/user-attachments/assets/dae759a4-7d8d-4cf8-9743-119c38993e36)


#### Clases Principales:
1. **Clase** `HttpServer`:
   - **Responsabilidad**: Núcleo del servidor web. Escucha en el puerto definido (`PORT`), gestiona conexiones entrantes y delega solicitudes.
   - **Atributos Clave**:
     - `PORT`: Puerto de escucha (ej: *8080*).
     - `WEB_ROOT`: Ruta de archivos estáticos (HTML, CSS, JS).
     - `noteController`: Controlador para peticiones con el endpoint /app/**.
   - **Métodos Destacados**:
     - `runServer()`: Inicia el servidor y acepta conexiones.
     - `stopServer()`: Detiene el servidor de manera elegante.
     - `handleRequests()`: Dirige solicitudes a métodos específicos (GET/POST) .
     - `handleGetRequests()`: Retorna archivos estáticos que se encuentran en el webroot del servidor (ej: *notes.html*).
     - `handleSpringRequests`: Dirige las solicitudes del enpoint /spring.
     - `handleAppGetRequests()`: Maneja las solicitudes *GET* realizadas al endpoint /app/** de manera que utiliza la función lamda implementada en el controlador para poder obtener el recurso.
     - `handleAppPostRequests()`:Maneja las solicitudes *POST* realizadas al endpoint /app/** de manera que utiliza la función lamda implementada en el controlador para poder realizar la petición.
       
2. **Clase** `MicroSpring`:
   - **Responsabilidad**: Núcleo del microframework basado en spring, utiliza la clase `ClassFileScanner` para obtener el listado de clases con la anotación @RestController, de manera que pueda obtener los metodos con la anotación @GetMapping y @PostMapping, los almacena en un Map<String, Method> (para cada verbo existente (get y post)) adicionalmente procesa las solicitudes del endpoint /spring y las anotaciones @RequestBody y @RequestParam
   
3. **Controladores**:
   - **Clase `NoteControllerImpl`**:
     - Acceder a la lógica de negocio.
     - Define los metodos get() y post() que permite al programador definir las rutas mediante el uso de funciones lambda.
     - **Dependencia**: `NoteServices` (inyección de servicios).
   - **Clase `GreetingController`**:
     - Utiliza la anotacion @RestController, y por el momento tiene un metodo con @GetMapping("/spring/hello") de manera que pide un parametro con @RequestParam, esto para poder devolver un saludo.
        **Clase `NoteControllerSpringImpl`**:
     - Utiliza la anotacion @RestController, y por el momento tiene un 2 metodo con @GetMapping("/spring/note")  y  @PostMapping("/spring/note").
4. **Servicios**:
   - **Interfaz `NoteServices`**:
     - Define operaciones como `addNote()` y `getNotes()`.
   - **Clase `NoteServicesImpl`**:
     - Implementa la interfaz y gestiona una lista de notas (`notes`).
     - **Atributo**: `notes` (almacenamiento temporal en memoria).
5. **Modelo**:
   - **Clase `Note`**:
     - Representa una nota con atributos: `title`, `group`, `content`, `date`.
     - **Nota**: `date` sugiere el uso de `LocalDate` para manejar fechas.
6. **Http**
   - **Clase `HttpRequest`**
      - Implementa la función *getQueryParams()* que permite obtener la lista de los querys en la petición.

#### Flujo de una Solicitud:
1. **Cliente → `HttpServer`**:  
   El navegador envía una solicitud (ej: `POST /app/notes` con parámetros).
2. **`HttpServer` → `NoteController`**:  
   El servidor detecta rutas bajo `/app` y delega al controlador.
   El servidor detecta rutas bajo `/spring` y delega al MicroSpring.
4. **`NoteController` → `NoteServices`**:  
   El controlador valida los datos y usa el servicio para agregar/retornar notas.
5. **Respuesta HTTP**:  
   - Éxito: `200 OK` con JSON de notas.  
   - Error: `400 Bad Request` con mensaje descriptivo (ej: parámetros inválidos).
  
## Virtualización y dockerización
 
### Funcionamiento contenedor docker local

![image](https://github.com/user-attachments/assets/c7246674-3390-4ee5-854e-c6861077b92a)


### Dockerizcaion y virtualizacion contenedor docker con EC2

![image](https://github.com/user-attachments/assets/d0d82af3-fb9c-4866-b041-66327b56c248)

![image](https://github.com/user-attachments/assets/49adc5d2-70d8-4ad6-9896-919cf4434ce0)

![image](https://github.com/user-attachments/assets/dcc80f7a-5712-4263-ba28-5f4beb65e563)

![image](https://github.com/user-attachments/assets/f78963e8-df2e-480c-a8d6-c03c19ae77a3)

### Funcionamiento.

![image](https://github.com/user-attachments/assets/18757fcb-829b-48bd-a75b-1026aad6813d)

https://github.com/user-attachments/assets/b55b5316-7438-40c9-9ada-abadfe624234


# Tecnologías Usadas en Pruebas
- **JUnit Jupiter 5:** Para pruebas unitarias y parametrizadas.
- **Mockito:** Para pruebas unitarias sobre los componentes Controller y Service
- **Maven:** Gestión de dependencias y ejecución de pruebas.

- **Resultado de las pruebas**
![image](https://github.com/user-attachments/assets/d2645602-e945-453b-8444-b100a1b6e2e1)


## Construido con.

- [Maven](https://maven.apache.org/) - Dependency Management

## Autores

- **Milton Andres Gutierrez Lopez** - *Initial work* - [MiltonGutierrez](https://github.com/MiltonGutierrez)

## Licencia

Este proyecto está licenciado bajo la Licencia GNU - mira el archivo [LICENSE.md](LICENSE.md) para más detalles.
