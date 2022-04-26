# Challenge
  
### Framework
Los Framework que usa es Spring Security con Json Web Tokens, por otro lado Swagger, para logs SLog4j

### App User Login

username: juan.challenge

password: challenge


### Consideraciones
#### Compilacion
La applicacion se compila con Gradle, esta usando Java 8

#### Ejecucion
Cuando se corra la aplicacion tiene en swagger 2 definiciones es **authentication** una para ejecutar el login y la otra es **location** para obtener las Provincias. Para **location** Definition tener en cuenta de copiar el password de la respuesta del login (es el token) pegarlo dentro del value dentro de Authorize

Tambien quiero agregar que por atras esta yendo a [Api de Datos del gobierno de GeoReferencia](https://apis.datos.gob.ar/georef/api/provincias ).

##### Base
No decidi hacerlo con una base de Datos, Porque tenia que usar flyway como framework para levantar todo el schema y crear los datos y eso me iba a llevar mas tiempo del debido, por lo que me decidi por crear un Repository MemoryAppUserDetailsDaoService que traquilamente podria ser remplazado por una base de datos cuando se cree.
 
