English version below.

# KIROL4ALL - Sistema de Gestión de Actividades Deportivas

![Java](https://img.shields.io/badge/Java-8%2B-blue)
![Swing](https://img.shields.io/badge/UI-Swing-orange)
![ObjectDB](https://img.shields.io/badge/BD-ObjectDB-yellow)
![Maven](https://img.shields.io/badge/Maven-3.8%2B-red)
![Arquitectura](https://img.shields.io/badge/Arquitectura-3%20capas-green)

**KIROL4ALL** es una aplicación desarrollada en Java que permite gestionar las inscripciones y reservas de actividades deportivas en un polideportivo. El sistema sigue una arquitectura en tres capas (presentación, lógica de negocio y acceso a datos) utilizando **ObjectDB** como base de datos orientada a objetos y **Maven** para la gestión de dependencias.

Este proyecto ha sido realizado como trabajo académico partiendo de la base proporcionada en `Rides24.zip`.

<img width="398" height="304" alt="image" src="https://github.com/user-attachments/assets/7b4f007b-948a-4497-bfb4-395607e2a843" />


## Descripción del proyecto

Un polideportivo necesita una aplicación para gestionar:

- **Actividades deportivas**: cada actividad tiene un nombre y un grado de exigencia (1 a 5). El encargado puede añadir nuevas actividades.
- **Salas multiuso**: cada sala tiene un nombre y un aforo máximo.
- **Planificación semanal**: se crean sesiones indicando la actividad, la sala, la fecha y la hora.
- **Socios**: los usuarios deben registrarse para realizar reservas. Cada socio tiene un número máximo de reservas semanales.
- **Reservas**: los socios pueden reservar plaza en una sesión. Si no hay plazas, pasan a lista de espera (FIFO).
- **Cancelaciones**: al cancelar, la plaza se asigna al socio en espera más antiguo.
- **Facturación**: cada lunes se genera una factura por email para socios con más de 4 reservas en la semana anterior.
- **Pago de facturas**: los socios pueden pagar sus facturas mediante cuenta bancaria o tarjeta (SIMULADO).

## Tecnologías utilizadas

- **Lenguaje**: Java 8
- **IDE**: Eclipse (o cualquier IDE compatible con Maven)
- **Gestor de dependencias**: Maven
- **Base de datos**: ObjectDB (base de datos orientada a objetos)
- **Interfaz de usuario**: Swing
- **Comunicación entre capas**: RMI
- **Arquitectura**: 3 capas (presentación, lógica, datos)

## Estructura del proyecto
Rides24/
|-- src/main/java/
|   |-- dataAccess/
|   |   `-- ObjectdbManagerServer.java
|   |-- businessLogic/
|   |   `-- BusinessLogicServer.java
|   |-- gui/
|   |   `-- ApplicationLauncher.java
|   |-- domain/
|   `-- configuration/
|-- src/test/java/
`-- pom.xml

## Instalación y ejecución en Eclipse con Maven

### Requisitos previos

- Java JDK 8 o superior instalado
- Eclipse IDE con soporte para Maven
- Maven 3.8+ (puede usar el embebido de Eclipse)

### Pasos para arrancar la aplicación

La aplicación se ejecuta en **3 niveles** y debe arrancarse en este orden:

#### 1. Importar el proyecto en Eclipse
- Abre Eclipse.
- Ve a `File > Import > Existing Maven Projects`.
- Selecciona la carpeta raíz del proyecto (`Rides24`) y finaliza.

#### 2. Arrancar el servidor de base de datos (ObjectDB)
- En el panel `Package Explorer`, navega a: `Rides24/src/main/java/dataAccess/`
- Haz clic derecho sobre **`ObjectdbManagerServer.java`** y selecciona `Run As > Java Application`.
- La consola mostrará mensajes indicando que el servidor ObjectDB está en ejecución.

#### 3. Arrancar el servidor de lógica de negocio
- Ve a: `Rides24/src/main/java/businessLogic/`
- Ejecuta **`BusinessLogicServer.java`** como `Java Application`.
- Este servidor se conectará a ObjectDB y publicará los servicios RMI.

#### 4. Arrancar la interfaz de usuario
- Ve a: `Rides24/src/main/java/gui/`
- Ejecuta **`ApplicationLauncher.java`** como `Java Application`.
- Aparecerá la ventana principal de la aplicación.

> **Importante**: Respetar el orden de ejecución. Si cambias el orden, la aplicación no funcionará correctamente.

## Credenciales de acceso

<img width="791" height="476" alt="image" src="https://github.com/user-attachments/assets/aa87dfc3-a38c-47d9-ab72-9faf1577bb65" />


### Administrador (encargado del polideportivo)
- **Email**: `encargado@gmail.com`
- **Contraseña**: `encargado`

Con este usuario podrás gestionar actividades, salas, sesiones y ver todos los datos.
<img width="802" height="524" alt="image" src="https://github.com/user-attachments/assets/76ff6079-78db-445a-bac1-030da1e43a02" />

#### Uso básico
- Inicia sesión con encargado@gmail.com / encargado.
Desde el menú principal podrás:
- Añadir actividades.
- Planificar sesiones (asignar actividad, sala, fecha y hora).
- Enviar facturas.

### Socios
- **Email**: `socio1@gmail.com`
- **Contraseña**: `socio1`
<img width="798" height="515" alt="image" src="https://github.com/user-attachments/assets/7a240a30-df3f-47db-88a7-f76bfdcc3986" />

- También se pueden registrar nuevos socios desde la propia aplicación.
<img width="799" height="481" alt="image" src="https://github.com/user-attachments/assets/79b9cfbf-7344-4a16-ba45-caa438ae6117" />

#### Uso básico
- Inicia sesión o regístrate.
Una vez dentro, podrás:
- Consultar sesiones disponibles.
- Realizar reservas (si no superas el límite semanal y hay plazas).
- Ver tus reservas y facturas.
- Pagar facturas pendientes (simulado).

## Consulta pública
Sin necesidad de login, puedes acceder a la opción "Consultar sesiones" para ver las actividades de la semana en curso.
<img width="796" height="480" alt="image" src="https://github.com/user-attachments/assets/3626f096-a49d-4458-94c9-9c4af97fb288" />

---------ENGLISH VERSION---------
# KIROL4ALL - Sports Activities Management System

![Java](https://img.shields.io/badge/Java-8%2B-blue)
![Swing](https://img.shields.io/badge/UI-Swing-orange)
![ObjectDB](https://img.shields.io/badge/DB-ObjectDB-yellow)
![Maven](https://img.shields.io/badge/Maven-3.8%2B-red)
![Architecture](https://img.shields.io/badge/Architecture-3%20Layers-green)

**KIROL4ALL** is a Java-based application that manages registrations and bookings for sports activities in a sports center. The system follows a three-layer architecture (presentation, business logic, and data access) using **ObjectDB** as an object-oriented database and **Maven** for dependency management.

This project was developed as an academic assignment based on the provided template `Rides24.zip`.
<img width="398" height="304" alt="image" src="https://github.com/user-attachments/assets/7b4f007b-948a-4497-bfb4-395607e2a843" />

## Project Description

A sports center requires an application to manage:

- **Sports Activities**: each activity has a name and a difficulty level (1 to 5). The manager can add new activities.
- **Multi-purpose Rooms**: each room has a name and a maximum capacity.
- **Weekly Planning**: sessions are created specifying the activity, room, date, and time.
- **Members**: users must register to make reservations. Each member has a maximum number of weekly bookings.
- **Reservations**: members can book a session. If the session is full, they are added to a waiting list (FIFO).
- **Cancellations**: when a reservation is canceled, the spot is assigned to the member who has been waiting the longest.
- **Billing**: every Monday, an invoice is sent by email to members who made more than 4 reservations during the previous week.
- **Invoice Payment**: members can pay invoices using a stored bank account or credit card (SIMULATED).

## Technologies Used

- **Language**: Java 8  
- **IDE**: Eclipse (or any Maven-compatible IDE)  
- **Dependency Management**: Maven  
- **Database**: ObjectDB (object-oriented database)  
- **User Interface**: Swing  
- **Layer Communication**: RMI  
- **Architecture**: 3-layer architecture (presentation, business logic, data access)  

## Project Structure

Rides24/
├── src/main/java/
│   ├── dataAccess/          (Data access layer)
│   │   └── ObjectdbManagerServer.java   (ObjectDB server)
│   ├── businessLogic/       (Business logic layer)
│   │   └── BusinessLogicServer.java     (Business logic server)
│   ├── gui/                 (Presentation layer)
│   │   └── ApplicationLauncher.java     (GUI launcher)
│   ├── domain/              (Domain classes)
│   └── configuration/       (Configuration files)
└── pom.xml

## Installation and Execution (Eclipse + Maven)

### Prerequisites

- Java JDK 8 or higher installed  
- Eclipse IDE with Maven support  
- Maven 3.8+  

## Application Startup

The application runs in **3 tiers** and must be started in the following order:

### 1️⃣ Import the Project into Eclipse

- Open Eclipse  
- Go to `File > Import > Existing Maven Projects`  
- Select the root folder of the project (`Rides24`) and finish  

### 2️⃣ Start the Database Server (ObjectDB)

- Navigate to: `Rides24/src/main/java/dataAccess/`
- Right-click `ObjectdbManagerServer.java`
- Select `Run As > Java Application`

The console will indicate that the ObjectDB server is running.

### 3️⃣ Start the Business Logic Server

- Navigate to: `Rides24/src/main/java/businessLogic/`
- Run `BusinessLogicServer.java` as `Java Application`

This server connects to ObjectDB and publishes the RMI services.

### 4️⃣ Start the Graphical User Interface

- Navigate to: `Rides24/src/main/java/gui/`
- Run `ApplicationLauncher.java` as `Java Application`

The main application window will appear.

> ⚠️ Important: The execution order must be respected. Otherwise, the application will not function correctly.

## Login Credentials
<img width="791" height="476" alt="image" src="https://github.com/user-attachments/assets/aa87dfc3-a38c-47d9-ab72-9faf1577bb65" />

### Administrator (Sports Center Manager)

- **Email**: `encargado@gmail.com`  
- **Password**: `encargado`  

With this account, you can:
- Manage activities  
- Plan sessions  
- Send invoices
<img width="802" height="524" alt="image" src="https://github.com/user-attachments/assets/76ff6079-78db-445a-bac1-030da1e43a02" />

### Members

- **Email**: `socio1@gmail.com`  
- **Password**: `socio1`  
<img width="798" height="515" alt="image" src="https://github.com/user-attachments/assets/7a240a30-df3f-47db-88a7-f76bfdcc3986" />

New members can also register directly within the application.
<img width="799" height="481" alt="image" src="https://github.com/user-attachments/assets/79b9cfbf-7344-4a16-ba45-caa438ae6117" />

Once logged in, members can:

- View available sessions  
- Make reservations (if under weekly limit and spots are available)  
- View their reservations and invoices  
- Pay pending invoices (simulated payment)

## Public Access

Without logging in, users can access the **"View Sessions"** option to check the activities scheduled for the current week.
<img width="796" height="480" alt="image" src="https://github.com/user-attachments/assets/3626f096-a49d-4458-94c9-9c4af97fb288" />
