# Test
Android Test for Aranda Software, consuming API of Movies.

La arquitectura escogida para el desarrollo de esta aplicación es Model View Controller, esta arquitectura o patrón de diseño permite separar la lógica de negocio (Controller) de la presentación (View), de esta forma mejora la mantenibilidad del código. Las capas utilizadas en este proyecto fueron:

1. View: (Vista)
ListActivity: Clase contenedora de los fragments
DetailsFragment: Contiene el detalle de cada ítem
ListMovieFragment: Contiene el recyclerview

Dentro de esta, está el sub paquete Adapter, el cual contiene las clases adapter (Offline y Online) encargadas de inflar el recyclerview con la información respectiva.
Además contiene una interfaz q maneja los onClick de cada tipo de item (Offline y Online)


2. Api (Red)
Contiene las clases: Request, Response, RetrofitClient, ApiManager y la interfaz MovieApi, estas en conjunto conforman la funiconalidad de networking usando la librería Retrofit

3. Util
Es un paquete que contiene 2 clases:
Constants que contiene las constantes globales de la aplicación
Utils que contiene el método estático q consulta el estado de la conexión a internet.

4. Model
 Contiene la clase que permite modelar los atributos de cada movie antes de ser enviada al fragment DetailsFragment

5. Database (Persistencia)
Esta capa está conformada por los subpaquetes:
Async, el cuál posee la clase AppExecutors que utiliza otro thread para cceder a la BD local.
Entity, incluye las 3 entidades de la BD local: Movie, TopRatedMovie y UpComingMovie.
A su vez contiene la clase abstracta MovieDatabase, esta es la BD.
Y finalmente la interfaz DaoAccess que contiene los queries para acceder a la BD local.


6. Controller (Lógica)
Este paquete contiene sólo dos clases:

ListMovieFragController : se encarga de comunicarse con el modelo y la persistencia para modificar la vista del listado y envía la data necesaria para inflar el Detalle.
DetailsFragmentController : se encarga de comunicarse con el modelo y la persistencia para modificar la vista del Detalle.

7. Application
Contiene la clase q extiende a Application y permite retornar el singleton de la base de datos Room. 
