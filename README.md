> **⚠️ WARNING:** <span style="color: yellow">Para que las peticiones, de acceso publico, sean permitidas por la configuración deben incluir <code>/public/&lt;entity&gt;</code> en el controlador de la entidad.</span>

> **ℹ️ INFO:** <span style="color: white">Todas las respuestas de los controladores tienen el formato <code>{code: httpCode; message: String; body: Object}</code>.</span> Incluyendo los errores.

## Tabla de Contenidos

- [Descripción del Proyecto](#descripción-del-proyecto)
- [Estructura del Proyecto](#estructura-del-proyecto)
- [Requisitos Previos](#requisitos-previos)
- [Configuración del Entorno de Desarrollo](#configuración-del-entorno-de-desarrollo)
- [Guía de Contribución](#guía-de-contribución)
- [Estándares de Codificación](#estándares-de-codificación)
- [Pruebas](#pruebas)
- [Despliegue](#despliegue)
- [Licencia](#licencia)

## Descripción del Proyecto

Este proyecto es una aplicación de comercio electrónico desarrollada en Java. Proporciona funcionalidades esenciales para la gestión de productos, carritos de compra y procesamiento de pedidos.

## Estructura del Proyecto

La estructura del proyecto es la siguiente:

```
ecommerce-java/
├── src/
│   ├── main/
│   │   ├── java/
│   │   └── resources/
│   └── test/
│       ├── java/
│       └── resources/
├── pom.xml
└── README.md
```

- **src/main/java/**: Contiene el código fuente principal de la aplicación.
- **src/main/resources/**: Incluye archivos de configuración y recursos estáticos.
- **src/test/**: Contiene las pruebas unitarias y de integración.
- **pom.xml**: Archivo de configuración de Maven para la gestión de dependencias y compilación.

## Requisitos Previos

Antes de comenzar, asegúrate de tener instalados los siguientes componentes:

- [Java Development Kit (JDK) 11 o superior](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html)
- [Apache Maven 3.6.0 o superior](https://maven.apache.org/download.cgi)
- [Git](https://git-scm.com/downloads)

## Configuración del Entorno de Desarrollo

1. **Clonar el repositorio:**

   ```bash
   git clone https://github.com/MartinoLucas/ecommerce-java.git
   cd ecommerce-java
   git checkout develop
   ```

2. **Compilar el proyecto:**

   ```bash
   mvn clean install
   ```

3. **Ejecutar la aplicación:**

   ```bash
   mvn spring-boot:run
   ```

   La aplicación estará disponible en `http://localhost:8080`.

## Guía de Contribución

Para contribuir al proyecto, sigue estos pasos:

1. **Crear una rama nueva desde `develop`:**

   ```bash
   git checkout -b feature/nombre-de-tu-rama develop
   ```

2. **Realizar los cambios necesarios y confirmar los commits siguiendo los [Conventional Commits](https://www.conventionalcommits.org/en/v1.0.0/#summary):**

   ```bash
   git add .
   git commit -m "feat: agregada nueva funcionalidad para X"
   ```

3. **Actualizar la rama `develop`:**

   ```bash
   git checkout develop
   git pull origin develop
   ```

4. **Rebasar tu rama con `develop`:**

   ```bash
   git checkout feature/nombre-de-tu-rama
   git rebase develop
   ```

5. **Resolver conflictos si los hay y luego empujar los cambios:**

   ```bash
   git push origin feature/nombre-de-tu-rama
   ```

6. **Crear una Pull Request hacia `develop`:**

    - Ve al repositorio en GitHub.
    - Navega a la pestaña "Pull requests".
    - Haz clic en "New pull request".
    - Selecciona tu rama y compárala con `develop`.
    - Proporciona una descripción detallada de los cambios y envía la Pull Request.

## Estándares de Codificación

- Sigue las convenciones de codificación de Java.
- Asegúrate de que el código esté bien documentado.
- Utiliza nombres de variables y métodos descriptivos.
- Mantén las funciones y métodos concisos y enfocados en una sola responsabilidad.
- **Todo el código y la documentación dentro del proyecto deben estar escritos en inglés.**

## Pruebas

Antes de enviar una Pull Request, asegúrate de que:

- Todas las pruebas existentes pasen sin errores:

  ```bash
  mvn test
  ```

- Se hayan agregado pruebas para las nuevas funcionalidades o cambios realizados.

## Despliegue

Las instrucciones para el despliegue de la aplicación se proporcionarán en una fase posterior del desarrollo.

## Licencia

Este proyecto está bajo la Licencia MIT. Consulta el archivo `LICENSE` para más detalles.

