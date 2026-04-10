# Technical Challenge "Estaciones de Servicio" 
Prueba técnica para aplicar al cargo de Desarrollador Especializado para la empresa ORGANIZACIÓN TERPEL S.A.
## Funcionalidad
Aplicación backend desarrollada en Java Spring Framework que consiste en un servicio API Rest que implementa el CRUD para la gestión de estaciones de servicio de ORGANIZACION TERPEL S.A. Incluye las operaciones básicas de listar todas las estaciones, buscar estación por ID, crear estación, modificar estación y borrar estación. El modelo implementado es REST MVC facilitar la separación por capas: Controlador que interactúa con la capa presentación, capa servicio que incluye la lógica de negocio y capa de acceso a datos. Utilizando para el envío y recepción de información el formato estandar JSON
## Herramientas y tecnologías utilizadas
* Backend:
    - Java 17 (v17.0.12)
    - MySql 8.0.45
    - Springboot 4.0.2
    - Maven 3.9.12
    - Lombok
    - JUnit y mockito para pruebas unitarias (Se realizó la prueba de la capa servicio: EstacionServiceTest.java)
* Librerías/Funcionalidades incluidas
    - Spring security (Basic Auth con usuarios inMemory USER, ADMIN y SecurityFilterChain para controlar el acceso a los endpoints )
    - Spring JPA (Capa de acceso a datos)
    - Lombok (Getters, setters y constructors)
    - Redis Cache (Manejo de cache para consultas frecuentes)
    - Actuator (Para validar cache activo, health e info de los endpoints)
    - Spring Validator (Validación global y manejo de excepciones de campos)
    - AuditingEntityListener (para el manejo automático de @CreatedDate y @LastModifiedDate)
    - BCryptPasswordEncoder (Para encriptación de passwords)
    - @RestControllerAdvice y GlobalExceptionHandler para el control de las excepciones y mensajes de error hacia el usuario
* Otros:
    - EclipseIDE 2025-12    
    - GIT/Github
    - Redis-cli (Instalado en WSL2 Windows Subsystem for linux) 
    - postman
    - MySql Workbench
    - Notepad++
## Contenido del proyecto
La aplicación cuenta con los siguientes elementos:
* /estaciones (Proyecto backend en MySql y Java)
* **/estaciones/src/main/resources/sql/script_creacion.sql** (Script DDL para creacion de tablas en la BD)
* **/estaciones/src/main/resources/sql/script_dml.sql** (Script DML para insertar la data inicial)
* **/estaciones/src/main/resources/postman_collection/estaciones.json** (Colección de postman para probar los endpoints)
* **/estaciones/secret.properties** (Archivo de configuración para los campos sensibles)

El archivo secrets.properties en entorno productivo no se almacenaria en el servidor ni en el repositorio de código, se pueden usar alternativas en la nube para almacenar esta info como Azure Key Vault o variables globales de entorno que puedan ser consultadas desde el código
## Instalación y configuración
1. Se debe instalar el servidor de bases de datos MySql como servicio de windows en localhost:3306
2. Crear la base de datos **terpel** y ejecutar el script de creación de tablas **sql/script_creacion.sql**
3. Ejecutar el script de inserción de datos iniciales **sql/script_dml.sql**
4. Instalar WSL2 preferiblemente Ubuntu: para esto desde powershell se ejecuta: > wsl --install, luego se reinicia el equipo y se configura usuario root y password del sistema linux. Una vez ingrese al promps ejecutar: > sudo apt update y luego > sudo apt install redis-server y finalmente > sudo service redis-server start para iniciar el servicio de Redis. Referirse a la documentación de Redis Cache: https://redis.io/docs/latest/operate/oss_and_stack/install/archive/install-redis/install-redis-on-windows/
5. Descargar el proyecto Java carpeta **estaciones** y compilar con maven: **> mvn clean install** genera el JAR /target/estaciones-0.0.1-SNAPSHOT.jar Ejecutar como aplicacion java en localhost:8080
6. Se deben realizar las pruebas desde postman importando la colección: **postman_collection/estaciones.json**

## Ejecución Aplicación
### Ruta ejecución:
http://localhost:8080/api/estaciones

### Ejecucuón de las pruebas unitarias:
En eclipse: Clic derecho en clase main() -> Run As... -> Maven Test
<img width="1675" height="1047" alt="image" src="https://github.com/user-attachments/assets/f3b8f550-d646-4748-901d-6401d4f5e3c9" />

Resultado en Consola:
<img width="1457" height="497" alt="image" src="https://github.com/user-attachments/assets/e884b6b2-d65c-4138-8dbb-595582331e29" />

### Ejecución desde Eclipse
En eclipse Clic derecho en clase main() -> Run As... -> Java Application
<img width="1837" height="1046" alt="image" src="https://github.com/user-attachments/assets/6cc2a521-ac3a-4b5d-adf9-33a569c06c3b" />

Resultado en Consola:
<img width="1875" height="964" alt="image" src="https://github.com/user-attachments/assets/3c78b549-ab04-4c69-ac36-f1c12c246268" />

### Acceso desde navegador web a funcionalidad pide autenticación
<img width="1902" height="986" alt="image" src="https://github.com/user-attachments/assets/d0e7924d-866f-43a4-a4b2-3ca57ea02ac4" />

### Una vez autenticado ejecuta el webservice
<img width="1904" height="992" alt="image" src="https://github.com/user-attachments/assets/ce970f7a-7b1a-44e9-bf8f-c39fea9bd84e" />

### Pruebas de los endpoints desde POSTMAN:
Configurar "Basic Auth" en la pestaña autorización
<img width="1878" height="892" alt="image" src="https://github.com/user-attachments/assets/e0ab808d-8b19-490e-bf71-099a4e08bf7d" />

Prueba listar todas las estaciones: getEstaciones GET retorna 200 OK
<img width="1882" height="990" alt="image" src="https://github.com/user-attachments/assets/8bf4640f-48a9-49c2-b5b4-83585a60a377" />

Prueba crear una estación addEstacion POST retorna 201 Created
<img width="1873" height="995" alt="image" src="https://github.com/user-attachments/assets/182b9396-afa1-438f-94b2-c108bfd10b46" />

Prueba actualizar estacion updateEstacion PUT retorna 200 OK
<img width="1890" height="988" alt="image" src="https://github.com/user-attachments/assets/a8f6761c-4da2-47e2-a294-4238acc4d258" />

Prueba obtener una estación getEstacionById GET retorna 200 OK
<img width="1878" height="990" alt="image" src="https://github.com/user-attachments/assets/56f67feb-a052-4163-be39-7b6294700c10" />

Prueba borrar una estación deleteEstacion DELETE retorna 200OK
<img width="1896" height="996" alt="image" src="https://github.com/user-attachments/assets/8e549851-79e1-4d0b-bb31-39958b8c6e6b" />

Prueba borrar una estación que no existe DELETE retorna 404 Not Found
<img width="1893" height="954" alt="image" src="https://github.com/user-attachments/assets/214d1ed5-0ff6-493e-a02e-ebc637f9c1dd" />

Prueba mensajes controlados Ejemplo: un URL que no existe: http://localhost:8080/api/estaciones/d Retorna 500 Internal Server Error
<img width="1904" height="989" alt="image" src="https://github.com/user-attachments/assets/e1d4506f-0354-4bc2-a1be-38a5d8bfc137" />

Prueba insertar una estación con código que ya existe en BD: 500 Internal Server Error
<img width="1884" height="957" alt="image" src="https://github.com/user-attachments/assets/0b9a9efa-5b6b-4290-b0dc-46b27e25674f" />

Prueba validación de datos: DATO DE ENTRADA INVALIDO, retorna 400 Bad Request
<img width="1898" height="986" alt="image" src="https://github.com/user-attachments/assets/44237232-7ec3-4593-8c74-997f62ae54fc" />

Prueba actualizar una estación que no existe: http://localhost:8080/api/estaciones/99  Retorna 404 Not Found
<img width="1891" height="1002" alt="image" src="https://github.com/user-attachments/assets/e92b8397-e179-41fd-b9fc-0a5db6392ae4" />





