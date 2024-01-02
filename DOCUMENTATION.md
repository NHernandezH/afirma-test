## Documentacion

El proyecto contiene 16 APIs, una para agregar usuarios necesarios para la autenticacion y las demas para la gestion de reservas, clientes y habitaciones.


## Autenticacion

Api ara poder generar el usuario y contraseña con el que se autenticaran las demas apis


#### Request

    Methodo: POST   
    Path:/users   
    
    Body:

    {
        "name": "admin",
        "password": "temporal"
    }

#### Response

    HTTP/1.1 200 OK


## Clientes

Permite la administracion de usuarios mediante la implementacion de operaciones crud

#### Request

    Descripcion: Agregar un nuevo cliente
    Methodo: POST   
    Path:/clients   
    
    Body:

    {
        "name":"noe",
        "lastName":"hernandez",
        "email":"noe_hernandez@gmail.com",
        "phone":"5580860989"
    }

#### Response

    HTTP/1.1 201 Created
    Body:
    {
        "name": "noe",
        "lastName": "hernandez",
        "email": "noe_hernandez@gmail.com",
        "phone": "57657657",
        "id": 10,
        "registerDate": "2023-12-29"
    }

    HTTP/1.1 400 Bad Request
    Body:
    {
        "code": "400 BAD_REQUEST",
        "errors": [
            "Ingrese un email valido"
        ]
    }

## Habitaciones

Permite la administracion de habitaciones mediante la implementacion de operaciones crud

#### Request

    Descripcion: Agregar una nueva habitacion
    Methodo: POST   
    Path:/rooms   
    
    Body:

    {
        "number": "25-A",
        "type": "individual",
        "description": "esta es una descripcion",
        "capacity": 1,
        "pricePerNight": 1
    }

#### Response

    HTTP/1.1 201 Created
    Body:
    {
        "number": "25-A",
        "type": "individual",
        "description": "esta es una descripcion",
        "capacity": 1,
        "pricePerNight": 1
    }

    HTTP/1.1 400 Bad Request
    Body:
    {
        "code": "400 BAD_REQUEST",
        "errors": [
            "La capacidad de personas es obligatorio"
        ]
    }

    HTTP/1.1 500 Internal Server Error
    Body:
    {
    "code": "INTERNAL_SERVER_ERROR",
        "errors": [
            "El numero de habitacion '25-A' ya se encuentra registrado"
        ]
    }

## Reservas

Permite la administracion de reservasiones mediante la implementacion de operaciones crud

#### Request

    Descripcion: Generar una nueva reservacion
    Methodo: POST   
    Path:/bookings   
    
    Body:

    {
        "roomId":2,
        "clientId":1,
        "startDate":"07/03/2020",
        "endDate":"09/03/2020",
        "totalNights":3
    }

#### Response

    HTTP/1.1 201 Created
    Body:
    {
        "roomId": 9,
        "clientId": 3,
        "startDate": "2020-03-07",
        "endDate": "2020-03-09",
        "totalNights": null,
        "id": 13,
        "totalAmount": 3.00,
        "status": "PENDING"
    }

    HTTP/1.1 400 Bad Request
    Body:
    {
        "code": "400 BAD_REQUEST",
        "errors": [
            "El id del cliente es obligatorio"
        ]
    }

    HTTP/1.1 404 Not Found
    Body:
    {
        "code": "NOT_FOUND",
        "errors": [
            "La habitacion con id 1 no existe en el systema"
        ]
    }

    HTTP/1.1 500 Internal Server Error
    Body:
    {
        "code": "INTERNAL_SERVER_ERROR",
        "errors": [
            "La habitacion con id 9 no tiene disponibilidad en las fechas del 07/03/2020 al 09/03/2020 "
        ]
    }



