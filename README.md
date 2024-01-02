
# afirma-test

Proyecto que permite gestionar un sistema de reservas para un hotel

Desarrollar una aplicación de microservicios utilizando Spring Boot 3 que gestione un sistema de
reservas para un hotel. La aplicación debe incluir operaciones de CRUD (Crear, Leer, Actualizar,
Eliminar) para las reservas y los detalles de los cliente

## Consideraciones de arquitectura

Se opto por una arquitectura en capas ya que, a consideracion, el proyecto no requerira ser escaldao y es una arquitectura facil y rapida de implementar.

## Prerequisitos

para correr el projecto son necesarios los siguientes requisitos:

- [JDK 1.8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
- [Maven 3](https://maven.apache.org)


## Ejecucion de pruebas y cobertura

Para ejecutar las pruebas del projecto y ver la cobertura de los test, dirigete a la carpeta del projecto desde la consola y ejecuta el comando
```
mvn test jacoco:report
```
el cual te generara un archivo **index.html** en el directorio **target/site/jacoco/**
ejecute el archivo y se abrira en el navegador las estadisticas de covertura.

## Ejecucion del proyecto
para ejecutar el proyecto dirigase a la carpeta del mismo desde la terminal y ejecute el comando
```
mvn spring-boot:run
```
o genere el ejecutable **.jar** con el comando:
```
mvn clean install
```
esto generara un archivo **.jar** en el directorio **\target** dentro del proyecto, solo ejecutelo y se levantara el proyecto

## Documentacion generada

para poder acceder a la documentacion generada siga el siguiente enlace **http://localhost:8080/swagger-ui/index.html** de igual forma se añade una pequeña seccion con la descripcion y uso de las apis en este [**enlace**](DOCUMENTATION.md)