# **Project Experimentality**
Se realizo una API Rest para exponer algunos endpoints para diferentes propositos.

La estructura del proyecto se divide y contiene lo siguiente:

- Controller: Se almacena una clase la cual contiene los endpoints y algunas validaciones
- Exceptions: Se almacena una clase para el manejo de algunas excepciones
- Model: Se almacenan las clases de producto e imagen dto
- Persistance > entities: Se alamcenan la clase de producto e imagen, para realizar el mapeo a la base de datos
- Persistance > repository: Se almacenan los repositorios que son las conexiones a la base de datos
- Service: Se almacena una interfaz con algunos metodos
- Service > serviceimpl: Se almacena el service del producto el cual hereda de una interfaz

# **Endpoints**

- Obtener todos los productos: GET path/productos/lista
- Buscar por id: GET path/productos/{id}
- Crear producto: POST path/productos/crear
- Actualizar producto: PUT path/productos/actualizar

# **Uso de la API**

Para usar y probar la API se requiere:
- Un entorno de desarrollo que puede ejecutar el proyecto
- MySQL (ideal) o H2 DataBase
- Postman

Por defecto la aplicación viene con la configuracion de MySQL, esta configuracion se encuentra en: src > main > resources > application.properties deberemos cambiar tanto el usuario como la contraseña. Si se desea hacer uso de H2 debemos comentar las lineas (14 a 24) y descomentar las lineas (3 a 9).

Luego de realizar los cambios pertinentes, ejecutamos el main que se encuentra en src > main > java > ProjectExperimentalityApplication, cuando se ejecute, este creara la base de datos y las entidades, adicional quedaran los endpoints para ser usados.

Dentro de la carpeta raiz encontraremos un archivo llamado: Project Experimentality.postman_collection.json, lo debemos importar en Postman para ejecutar algunas pruebas. Ya importado el archivo, se generara una coleccion, dentro de esta podremos ejecutar los request, en el request de crear producto y el de actualizar producto debemos adjuntar dos imagenes, se adjuntan en el body > form-data > imagen, hay dos campos de imagen para subirlos.

Luego de añadir las imagenes podremos ejecutar los request, el orden ideal seria:

- Crear producto
- Crear producto sin imagenes
- Crear producto con porcentaje no permitido
- Buscar producto por id
- Buscar todos los productos
- Actualizar productos

Por ultimo se realizaron algunos test unitarios con Junit, para ejecutarlos debemos dirigirnos a src > test > java > com.projectExperimantality > ProjectExperimentalityApplicationTests. Podremos ejecutar todos los test o uno por uno, de igual manera deberian ejecutarse correctamente

