# product-shop-android

Esta aplicación es una prueba de concepto que cubre las siguientes pautas de desarrollo.

El desarrollo del proyecto se basa en un patrón MVP con Clean Arquitecture.

La división de capas se realiza de la siguiente forma:
  - UI: Donde se declaran las Activities, Views y Adapters.
  - Presentador: Se utiliza de punto de comunicación entre la capa UI y la capa de domimnio.
  - Interactor: Establecen la lógica de negocio de los casos de uso.
  - Repositorio: Sirve para la cominicación con los diferentes origenes de datos. En este caso son:
        * Firebases (A través de una librería propia de Firebase)
        * API Rest (A través de Retrofit)
        * Modelo SQL (A través del ORM DBFlow)

-> El intercambio de mensajes entre los repositorios y los presentados se realiza a través de un Bus de datos implemmentado con GreenRobot.
-> Para la carga de imagenes se utiliza Glid.
-> Se utiliza como inyector de dependecias Dagger2
-> ButterKnife para el binding de eventos y elementos de la capa de UI.

La aplicación se divide en tres partes:
  Login: con las siguientes operaciones:
    * Autenticar el usuario, para ello uso Firebase.
    * Crear nueva cuenta de usuario, también utilizo Firebase.
	  Una vez autenticado el usuario se guardará en las SharePreferences el email y uuid generado 	por Firebase, para que este disponible a lo largo de ciclo de vida de la aplicación.
  Productos Disponibles:
    * En esta pantalla se mostrará la lista de productos disponibles, que se recupera a través de RETROFIT atacando al API REST proporcionado por Food2Fork. A cada uno de estos productos se le asigna un precio mediante un Random y desde aquí el usuario podrá ir añadiendo productos a su cuenta.
    * Cuando se añade un producto, esta información se persiste en un modelo SQLITE utilizando el ORM DBFlow.
    * Desde está pantalla se podrá hacer la operación de logout que consistirá en:
        - Hacer logout en Firebase.
        - Eliminar la información del usuario almacenada previamente en las SharePreferences.
        - Cargar la pantalla inicial de Login
  Productos Añadidos: Desde esta pantalla el usuario podrá realizar tres operaciones:
    * Ver la lista de productos que ha añadido. En caso de que se haya añadido el mismo producto varias veces éste solo se mostrará una única vez con un contador de unidades.
    * Eliminar un producto, se realiza mediante un efecto Swipe hacia la izquierda.
    * Volver a la pantalla de productos disponibles (Fecha en el Toolbar).
    
    
    
