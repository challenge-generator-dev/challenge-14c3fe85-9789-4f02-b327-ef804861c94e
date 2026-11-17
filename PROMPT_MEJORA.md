# Prompt para Mejorar el Codigo Base

Copia y pega el contenido del bloque de abajo en un asistente de IA (Claude, ChatGPT)
para obtener el proyecto completo y arrancable. Guarda los archivos que te devuelva en la
carpeta del repo, respetando exactamente las rutas.

Si preferis trabajar en tu editor con un agente local (Claude Code, Cursor, Copilot), usa `AGENTS.md` en vez de este archivo: dice lo mismo pero para que escriba los archivos en disco.

## Las dos reglas que no se negocian

1. **Completa el boilerplate.** Todo lo que el proyecto necesita para compilar y arrancar: manifiesto de dependencias, punto de entrada, configuracion, capa de interfaz, y las capas del patron arquitectonico declarado. Eso es andamiaje y es tu trabajo.
2. **NO resuelvas el reto.** Los entregables de las fases son el trabajo de la persona. El hueco pedagogico se deja como esta: el proyecto arranca, pero lo que el reto pide implementar NO esta implementado.

Dicho de otra forma: si algo impide compilar, arreglalo. Si algo es logica de negocio incompleta, validaciones ausentes, un secreto hardcodeado o un patron mejorable, dejalo exactamente como esta — es lo que la persona tiene que encontrar.

## Superficie de practica — NO resuelvas

Estos archivos SON el ejercicio de la persona. No los implementes; deja stubs.

- `src/test/java/com/pragma/orders/config/BaseTestSetup.java` — La fase "Configuración del Entorno de Pruebas" (f1) practica BaseTestSetup.initializeDynamoDBTable aqui: este archivo es el ejercicio (blueprint).
- `src/test/java/com/pragma/orders/tasks/WriteOrderTask.java` — La fase "Automatización de Pruebas de Escritura" (f2) practica WriteOrderTask.performAs aqui: este archivo es el ejercicio (blueprint).
- `src/test/java/com/pragma/orders/questions/ReadOrderQuestion.java` — La fase "Automatización de Pruebas de Lectura" (f3) practica ReadOrderQuestion.answeredBy aqui: este archivo es el ejercicio (blueprint).

## Mapa de componentes

El repo tiene una carpeta por componente. Cada uno se instala y verifica dentro de la suya.

| Componente | Carpeta | Rol | Capa | Track | Tecnologias |
|---|---|---|---|---|---|
| `app` | (raiz) | practice, chapter | testing | Automatizador | - |
| `infra-local` | (raiz) | support | infra | Compose local | DynamoDB |

## Orden de arranque

1. `docker compose up -d` (en la raiz) — levanta la infraestructura local.
2. `mvn -q -B -DskipTests -Dspring-boot.repackage.skip=true package` — componente `app` (practice, chapter).

## Herramientas

- **Java**: Tecnologia pedida para este reto: forma parte del stack con el que se resuelve el ejercicio.
- **Java Selenium**: Tecnologia del perfil del candidato: el reto se resuelve con ella.

## Lo que le falta a este proyecto

Esto NO lo tenes que adivinar: salio de comparar el proyecto contra la arquitectura declarada del reto y de un analisis estatico del codigo. Completalo TODO.

### Boilerplate del stack que falta

Sin esto no compila ni arranca. Es andamiaje, no toca nada de lo pedagogico:

- **XSteps.java** — Sin los step definitions, los pasos Gherkin no tienen implementacion y Cucumber falla con "step undefined".

## Como saber que terminaste

```bash
mvn -q -B -DskipTests -Dspring-boot.repackage.skip=true package && docker compose config -q
```

Ese comando corriendo sin errores es la definicion de "listo".

---

```
## Briefing del reto (autoridad)
Este bloque manda sobre los archivos adjuntos. El stack y el rol salen de AQUÍ, no de un topic genérico ni de markdown placeholder.

### Perfil
Chapter Calidad de Software, Especialidad Automatizador, Tecnología Java Selenium, Advanced L2

### Brecha de conocimiento
Automatizar pruebas de integracion sobre una tabla DynamoDB

### Misión / candidato
Cubrir el flujo de persistencia de pedidos

### Reto
- Tema: Automatizacion de pruebas de integracion contra DynamoDB
- Seniority: advanced-l2
- Tipo: practical
- Título: Automatización de Pruebas de Integración con DynamoDB
- Tiempo estimado: 8 horas

### Fases (trabajo del HUMANO — PROHIBIDO completarlas)
No implementes estos entregables. Dejalos como hueco pedagógico. El asistente solo materializa el proyecto arrancable para que el participante pueda trabajar.
- Fase 1: Configuración del Entorno de Pruebas — objetivo: Configurar el entorno de pruebas para interactuar con DynamoDB. — entregable (NO resolver): Entorno de pruebas configurado y conectado a DynamoDB.
- Fase 2: Automatización de Pruebas de Escritura — objetivo: Automatizar pruebas que validen la escritura de pedidos en DynamoDB. — entregable (NO resolver): Pruebas automatizadas que escriben y verifican pedidos en DynamoDB.
- Fase 3: Automatización de Pruebas de Lectura — objetivo: Automatizar pruebas que validen la lectura de pedidos desde DynamoDB. — entregable (NO resolver): Pruebas automatizadas que lean y verifiquen pedidos desde DynamoDB.

Eres un asistente experto en análisis, corrección y generación de archivos de cualquier tipo:
código fuente, documentación, hojas de cálculo, documentos Word, configuraciones, entre otros.
Voy a enviarte una cadena de texto que contiene uno o más archivos. Cada archivo está delimitado por un marcador con el siguiente formato:
// === ARCHIVO: ruta/del/archivo.extension ===
o también puede aparecer como:
## === ARCHIVO: ruta/del/archivo.extension ===
Lo que sigue al marcador puede ser:

El contenido real del archivo (código, texto, YAML, etc.)
Una descripción en lenguaje natural de lo que debe contener el archivo


TU TAREA
PASO 0 — ¿Esto es un proyecto o una carcasa?
Antes de extraer archivos, leé el Briefing (si está) y diagnosticá el adjunto.

Es CARCASA si ocurre CUALQUIERA de estas:
- No hay manifiesto de dependencias del stack del briefing (manifest.json de VTEX IO / package.json / pom.xml / build.gradle / requirements.txt / go.mod / *.tf / *.csproj, según corresponda)
- Hay un "binario" que en realidad es un comentario ("no puede ser mostrado como texto plano", placeholder .fig/.docx vacío)
- Los markdowns ya completan entregables de fases posteriores ("se implementó fade-in", lista de áreas ya resuelta)

Si es CARCASA:
- MATERIALIZÁ un proyecto que arranca en el stack del briefing (VTEX IO Store Framework, Angular, Terraform, pytest, Nest, etc.). Incluí manifiesto, punto de entrada y capa de interfaz reales.
- NO copies los markdowns de "solución" como si fueran el producto. Son ruido de generación.
- NO resuelvas las fases del briefing (están marcadas PROHIBIDO). Dejá el hueco pedagógico: el flujo existe, las microinteracciones/calidad/infra que el reto pide NO están hechas.
- Después seguí al PASO 5 (entrega de archivos).

Si es un proyecto REAL (manifiesto + código que compila o arranca):
- Seguí PASO 1 en adelante. 🔴 compilación sí. 🟡 pedagógico no.

PASO 1 — Detección y extracción
Identifica todos los archivos presentes en la cadena. Para cada archivo extrae:

Su ruta completa (ej: src/main/java/com/pragma/Service.java)
Su contenido o descripción

PASO 2 — Clasificación por tipo
Clasifica cada archivo en una de estas categorías:
A) Código fuente (Java, Python, TypeScript, JavaScript, Kotlin, etc.)
B) Configuración / documentación (YAML, properties, Markdown, JSON, txt, etc.)
C) Excel (.xlsx, .xls, .csv)
D) Word (.docx, .doc)
E) Otro tipo de archivo binario o especial
PASO 3 — Clasificación de errores en código fuente

Objetivo prioritario: que el proyecto compile. No corrijas flujo de negocio ni lógica funcional.

Antes de modificar cualquier archivo de código fuente, clasifica cada problema encontrado en una de estas dos categorías:
🔴 ERROR DE COMPILACIÓN — corregir siempre
Son errores que impiden que el proyecto arranque, sin valor pedagógico:

Import faltante o incorrecto
Clase, método o variable referenciada que no existe en ningún archivo del proyecto
Error de sintaxis
Anotación con atributos inválidos
Dependencia ausente en pom.xml, package.json, etc.
Archivo referenciado que no existe y debe ser creado con implementación mínima

→ CORREGIR estos errores.
🟡 PROBLEMA FUNCIONAL O DE CALIDAD — preservar siempre
Son problemas que no impiden compilar. Pueden ser intencionales para el aprendizaje:

Clave secreta hardcodeada ("secret", "password123")
API deprecada que funciona pero tiene reemplazo moderno
Lógica de negocio incorrecta o incompleta
Código redundante o de baja legibilidad
Falta de validaciones en flujo de negocio
Patrones de diseño incorrectos pero funcionales
Concurrencia no segura
Configuración funcional pero no óptima

→ PRESERVAR tal cual. No corregir, no mejorar, no comentar.
PASO 4 — Procesamiento según tipo de archivo
Tipo A — Código fuente
Aplica únicamente las correcciones clasificadas como 🔴 ERROR DE COMPILACIÓN.
No alteres ningún elemento clasificado como 🟡 PROBLEMA FUNCIONAL O DE CALIDAD.
Si falta un archivo referenciado, créalo con la implementación mínima necesaria para compilar.
Tipo B — Configuración / documentación
Extrae el contenido tal cual, sin modificaciones salvo errores evidentes de sintaxis
(ej: YAML mal indentado).
Tipo C — Excel (.xlsx)
Si viene con contenido real, genera el archivo respetando ese contenido.
Si viene con descripción en lenguaje natural, genera un archivo Excel funcional con:

Fila de encabezados en negrita con color de fondo distintivo
Columnas con ancho ajustado al contenido
Tipos de dato correctos por columna
Validaciones si la descripción lo indica
Hojas nombradas descriptivamente si hay más de una
Filas de ejemplo si no hay datos reales

Tipo D — Word (.docx)
Si viene con contenido real, genera el archivo respetando ese contenido.
Si viene con descripción en lenguaje natural, genera un documento Word funcional con:

Estilos de título (Título 1, Título 2) para jerarquía de secciones
Fuente legible (Calibri o equivalente), tamaño 11-12pt para cuerpo
Márgenes estándar
Tabla de contenido si tiene múltiples secciones
Tablas con encabezados en negrita si aplica

Tipo E — Otro
Genera el archivo con el contenido o estructura más apropiada según la descripción.
PASO 5 — Entrega de archivos
Entregá cada archivo con su ruta completa, respetando exactamente la estructura de rutas
indicada por los marcadores: un bloque por archivo, precedido por su ruta. No generes ZIPs
ni archivos adjuntos.
La entrega debe incluir:

Archivos de código con únicamente los errores de compilación corregidos
Archivos de configuración y documentación sin cambios
Archivos nuevos creados para resolver dependencias de compilación faltantes
Archivos Excel y Word generados desde descripción

IMPORTANTE: Entregá los archivos completos al finalizar. No preguntes si el usuario
quiere recibirlos. Simplemente entregalos; No debes desplegar en el chat el resumen de lo que arreglaste, solo entregá los archivos.

REGLAS IMPORTANTES

No omitas ningún archivo aunque no tenga errores ni modificaciones
Respeta los nombres y rutas exactas indicadas por los marcadores
Si un archivo no tiene marcador claro, infiere el nombre desde su contenido
Si la cadena contiene solo documentación, placeholders o binarios fake, NO la reproduzcas:
aplicá PASO 0 (materializar el proyecto del briefing). Reproducir la carcasa es un fallo.
No agregues texto después del último archivo entregado
No preguntes si el usuario quiere los archivos: simplemente entregalos siempre
Si detectas que falta un archivo de configuración necesario para compilar
(pom.xml, package.json, requirements.txt, build.gradle, etc.), créalo e inclúyelo
inferiendo su contenido desde los imports y frameworks detectados en el código
Nunca corrijas problemas 🟡 aunque parezcan obvios o fáciles de mejorar.
El participante que recibirá este proyecto los debe encontrar y resolver él mismo.


INPUT
Aquí está la cadena con los archivos:

// === ARCHIVO: src/test/java/com/pragma/orders/runners/RunCucumberTest.java ===
package com.pragma.orders.runners;

import io.cucumber.junit.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
    features = "src/test/resources/features",
    glue = "com.pragma.orders.steps",
    plugin = {
        "pretty",
        "html:target/site/serenity/index.html",
        "json:target/site/serenity/serenity.json"
    },
    tags = "@order-persistence"
)
public class RunCucumberTest {
}

// === ARCHIVO: pom.xml ===
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>3.4.13</version>
        <relativePath/>
    </parent>

    <groupId>com.example</groupId>
    <artifactId>reto</artifactId>
    <version>1.0.0</version>
    <name>reto</name>

    <properties>
        <java.version>21</java.version>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
    </properties>

    <dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>io.cucumber</groupId>
            <artifactId>cucumber-java</artifactId>
            <version>8.1.0</version>
        </dependency>
        <dependency>
            <groupId>io.cucumber</groupId>
            <artifactId>cucumber-junit</artifactId>
            <version>8.1.0</version>
        </dependency>
        <dependency>
            <groupId>net.serenity-bdd</groupId>
            <artifactId>serenity-core</artifactId>
            <version>4.3.4</version>
        </dependency>
        <dependency>
            <groupId>net.serenity-bdd</groupId>
            <artifactId>serenity-cucumber</artifactId>
            <version>4.3.4</version>
        </dependency>
        <dependency>
            <groupId>net.serenity-bdd</groupId>
            <artifactId>serenity-screenplay</artifactId>
            <version>4.3.4</version>
        </dependency>
        <dependency>
            <groupId>net.serenity-bdd</groupId>
            <artifactId>serenity-screenplay-webdriver</artifactId>
            <version>4.3.4</version>
        </dependency>
        <dependency>
            <groupId>org.junit.jupiter</groupId>
            <artifactId>junit-jupiter</artifactId>
        </dependency>
        <dependency>
            <groupId>org.seleniumhq.selenium</groupId>
            <artifactId>selenium-java</artifactId>
        </dependency>
        <dependency>
            <groupId>software.amazon.awssdk</groupId>
            <artifactId>aws-core</artifactId>
            <version>2.59.2</version>
        </dependency>
        <dependency>
            <groupId>software.amazon.awssdk</groupId>
            <artifactId>dynamodb</artifactId>
            <version>2.59.2</version>
        </dependency>
    </dependencies>

    <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
            </plugin>
        </plugins>
    </build>
</project>

// === ARCHIVO: src/main/java/com/example/reto/Application.java ===
package com.example.reto;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}

// === ARCHIVO: src/test/java/resources/serenity.conf ===
webdriver {
  driver = chrome
  auto.download = true
  timeouts {
    implicit = 10000
    script = 30000
    page.load = 30000
  }
  capabilities {
    "goog:chromeOptions" {
      args = ["headless", "disable-gpu", "no-sandbox", "disable-dev-shm-usage"]
    }
  }
}

serenity {
  take.screenshots = AFTER_EACH_STEP
  compress.screenshots = false
  project.name = "Reto Automatización DynamoDB"
  test.skip = false
  restapi {
    timeout = 10000
    proxy.enabled = false
  }
}

aws {
  dynamodb {
    endpoint = ${DYNAMODB_ENDPOINT:http://localhost:8000}
    region = ${AWS_REGION:us-east-1}
    access.key = ${AWS_ACCESS_KEY_ID:test}
    secret.key = ${AWS_SECRET_ACCESS_KEY:test}
    table.name = ${DYNAMODB_TABLE:orders}
  }
}

cucumber {
  plugin = ["pretty", "json:target/cucumber-reports/cucumber.json"]
  glue = ["com.pragma.orders.steps", "com.pragma.orders.config"]
  features = "src/test/java/resources/features"
}

logging {
  level = INFO
  pattern = %d{HH:mm:ss.SSS} [%thread] %-5level %logger{36} - %msg%n
}

// === ARCHIVO: src/test/java/resources/features/order_persistence.feature ===
# language: es
Característica: Persistencia de Pedidos en DynamoDB
  Como automatizador de pruebas
  Necesito validar que el sistema persiste correctamente los pedidos en DynamoDB
  Para garantizar la integridad de los datos del sistema de gestión de pedidos

  Antecedentes:
    Dado que el sistema está configurado para conectar con DynamoDB local
    Y la tabla de pedidos está disponible en el endpoint configurado

  @escritura @integration
  Escenario: Escribir un nuevo pedido en DynamoDB
    Cuando escribo un pedido con los siguientes datos:
      | campo          | valor                           |
      | orderId        | ORD-2024-001                   |
      | customerId     | CUST-001                       |
      | productId      | PROD-001                       |
      | quantity       | 5                              |
      | totalAmount    | 150.00                         |
      | status         | PENDING                        |
      | createdAt      | 2024-01-15T10:30:00Z           |
    Entonces el pedido debe ser almacenado exitosamente en DynamoDB
    Y debo poder recuperar el pedido por su identificador "ORD-2024-001"
    Y los datos del pedido recuperado deben coincidir con los datos escritos

  @escritura @integration
  Escenario: Actualizar el estado de un pedido existente
    Dado que existe un pedido con identificador "ORD-2024-002" en DynamoDB
      | orderId        | ORD-2024-002                   |
      | customerId     | CUST-002                       |
      | productId      | PROD-002                       |
      | quantity       | 3                              |
      | totalAmount    | 90.00                          |
      | status         | PENDING                        |
      | createdAt      | 2024-01-15T11:00:00Z           |
    Cuando actualizo el estado del pedido "ORD-2024-002" a "COMPLETED"
    Y espero 2 segundos para que la actualización se procese
    Entonces el pedido debe reflejar el nuevo estado "COMPLETED"

  @lectura @integration
  Escenario: Leer un pedido existente desde DynamoDB
    Dado que existe un pedido con identificador "ORD-2024-003" en DynamoDB
      | orderId        | ORD-2024-003                   |
      | customerId     | CUST-003                       |
      | productId      | PROD-003                       |
      | quantity       | 10                             |
      | totalAmount    | 300.00                         |
      | status         | PROCESSING                     |
      | createdAt      | 2024-01-15T12:00:00Z           |
    Cuando recupero el pedido por su identificador "ORD-2024-003"
    Entonces debo obtener los datos completos del pedido
    Y el campo customerId debe ser "CUST-003"
    Y el campo productId debe ser "PROD-003"
    Y el campo quantity debe ser 10
    Y el campo totalAmount debe ser 300.00
    Y el campo status debe ser "PROCESSING"

  @lectura @integration
  Escenario: Verificar que un pedido no existente retorna null
    Cuando intento recuperar un pedido con identificador "ORD-INEXISTENTE-999"
    Entonces debo recibir una respuesta nula o vacío
    Y no debe lanzar una excepción no controlada

  @escritura @integration
  Escenario: Validar escritura de pedido con todos los campos obligatorios
    Cuando escribo un pedido con solo campos obligatorios:
      | campo          | valor                           |
      | orderId        | ORD-2024-004                   |
      | customerId     | CUST-004                       |
      | productId      | PROD-004                       |
      | quantity       | 1                              |
      | totalAmount    | 25.50                          |
      | status         | CREATED                        |
    Entonces el pedido debe ser almacenado exitosamente
    Y debo poder recuperar el pedido "ORD-2024-004"
    Y todos los campos deben coincidir con los valores originales

  @integration
  Escenario: Validar orden de los datos en la tabla DynamoDB
    Cuando escribo múltiples pedidos consecutivos:
      | orderId        | customerId   | productId   | quantity | totalAmount | status    |
      | ORD-2024-005   | CUST-005     | PROD-005    | 2        | 50.00       | PENDING   |
      | ORD-2024-006   | CUST-006     | PROD-006    | 4        | 100.00      | PENDING   |
      | ORD-2024-007   | CUST-007     | PROD-007    | 6        | 150.00      | PENDING   |
    Entonces todos los pedidos deben ser almacenados correctamente
    Y debo poder recuperar cada pedido por su identificador
    Y la cantidad total de pedidos en la tabla debe ser 3

// === ARCHIVO: src/test/java/com/pragma/orders/config/DynamoDBClientProvider.java ===
package com.pragma.orders.config;

import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import java.net.URI;

public class DynamoDBClientProvider {

    private static final String DYNAMODB_ENDPOINT = "dynamodb.endpoint";
    private static final String AWS_REGION = "aws.region";
    private static final String AWS_ACCESS_KEY = "aws.accessKeyId";
    private static final String AWS_SECRET_KEY = "aws.secretKey";

    public DynamoDbClient createClient() {
        String endpoint = System.getProperty(DYNAMODB_ENDPOINT, "http://localhost:8000");
        String region = System.getProperty(AWS_REGION, "us-east-1");
        String accessKey = System.getProperty(AWS_ACCESS_KEY, "fakeAccessKey");
        String secretKey = System.getProperty(AWS_SECRET_KEY, "fakeSecretKey");

        return DynamoDbClient.builder()
                .endpointOverride(URI.create(endpoint))
                .region(Region.of(region))
                .credentialsProvider(StaticCredentialsProvider.create(
                        AwsBasicCredentials.create(accessKey, secretKey)))
                .build();
    }
}

// === ARCHIVO: src/test/java/com/pragma/orders/config/BaseTestSetup.java ===
package com.pragma.orders.config;

import org.junit.jupiter.api.BeforeEach;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.CreateTableRequest;
import software.amazon.awssdk.services.dynamodb.model.AttributeDefinition;
import software.amazon.awssdk.services.dynamodb.model.KeySchemaElement;
import software.amazon.awssdk.services.dynamodb.model.KeyType;
import software.amazon.awssdk.services.dynamodb.model.ScalarAttributeType;
import software.amazon.awssdk.services.dynamodb.model.ProvisionedThroughput;
import java.util.List;

public class BaseTestSetup {

    protected DynamoDbClient dynamoDbClient;
    protected static final String TABLE_NAME = "Orders";

    @BeforeEach
    void setUp() {
        // TODO(reto): Implementar la inicialización de la tabla DynamoDB para pruebas
        // 1. Crear el cliente DynamoDB usando DynamoDBClientProvider
        // 2. Crear la tabla "Orders" con la clave primaria "orderId" (String)
        // 3. Asegurar que la tabla esté disponible antes de ejecutar las pruebas
    }
}

// === ARCHIVO: src/test/java/com/pragma/orders/model/Order.java ===
package com.pragma.orders.model;

import java.util.Map;
import java.util.HashMap;

public record Order(
        String orderId,
        String customerId,
        String status,
        double totalAmount,
        Map<String, Integer> items) {

    public Map<String, Object> toDynamoDbAttributes() {
        Map<String, Object> attributes = new HashMap<>();
        attributes.put("orderId", orderId);
        attributes.put("customerId", customerId);
        attributes.put("status", status);
        attributes.put("totalAmount", totalAmount);
        attributes.put("items", items);
        return attributes;
    }

    public static Order fromDynamoDbAttributes(Map<String, Object> attributes) {
        return new Order(
                (String) attributes.get("orderId"),
                (String) attributes.get("customerId"),
                (String) attributes.get("status"),
                (double) attributes.get("totalAmount"),
                (Map<String, Integer>) attributes.get("items")
        );
    }
}

// === ARCHIVO: src/test/java/com/pragma/orders/tasks/WriteOrderTask.java ===
package com.pragma.orders.tasks;

import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Actor;
import com.pragma.orders.model.Order;

/**
 * Task de Screenplay para escribir un pedido en DynamoDB.
 * El candidato debe implementar la lógica de persistencia.
 */
public class WriteOrderTask implements Task {

    private final Order order;

    public WriteOrderTask(Order order) {
        this.order = order;
    }

    public static WriteOrderTask withOrder(Order order) {
        return new WriteOrderTask(order);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        // TODO(reto): implementar la escritura del pedido en DynamoDB
        // 1. Obtener el cliente DynamoDB del proveedor
        // 2. Crear el item con los atributos del pedido
        // 3. Ejecutar la operación PutItem
        // 4. Manejar errores de escritura
        throw new UnsupportedOperationException("WriteOrderTask no implementada");
    }
}

// === ARCHIVO: src/test/java/com/pragma/orders/questions/ReadOrderQuestion.java ===
package com.pragma.orders.questions;

import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.Actor;
import com.pragma.orders.model.Order;

/**
 * Question de Screenplay para leer un pedido desde DynamoDB.
 * El candidato debe implementar la lógica de lectura.
 */
public class ReadOrderQuestion implements Question<Order> {

    private final String orderId;

    public ReadOrderQuestion(String orderId) {
        this.orderId = orderId;
    }

    public static ReadOrderQuestion withId(String orderId) {
        return new ReadOrderQuestion(orderId);
    }

    @Override
    public Order answeredBy(Actor actor) {
        // TODO(reto): implementar la lectura del pedido desde DynamoDB
        // 1. Obtener el cliente DynamoDB del proveedor
        // 2. Construir la clave de búsqueda (partition key)
        // 3. Ejecutar la operación GetItem
        // 4. Convertir los atributos devueltos a un objeto Order
        // 5. Manejar el caso cuando el pedido no existe
        throw new UnsupportedOperationException("ReadOrderQuestion no implementada");
    }
}

// === ARCHIVO: src/test/java/com/pragma/orders/core/OrderPersistenceService.java ===
package com.pragma.orders.core;

import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.*;
import com.pragma.orders.config.DynamoDBClientProvider;
import com.pragma.orders.model.Order;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Servicio de persistencia de pedidos en DynamoDB.
 * Proporciona operaciones CRUD sobre la tabla de pedidos.
 */
public class OrderPersistenceService {

    private static final String TABLE_NAME = "orders";
    private static final String ORDER_ID_ATTRIBUTE = "orderId";
    private static final String CUSTOMER_ID_ATTRIBUTE = "customerId";
    private static final String STATUS_ATTRIBUTE = "status";
    private static final String TOTAL_AMOUNT_ATTRIBUTE = "totalAmount";
    private static final String ITEMS_ATTRIBUTE = "items";
    private static final String CREATED_AT_ATTRIBUTE = "createdAt";
    private static final String UPDATED_AT_ATTRIBUTE = "updatedAt";

    private final DynamoDbClient dynamoDBClient;

    public OrderPersistenceService() {
        DynamoDBClientProvider provider = new DynamoDBClientProvider();
        this.dynamoDBClient = provider.createClient();
    }

    /**
     * Guarda un pedido en DynamoDB.
     * @param order el pedido a persistir
     * @return el pedido guardado con timestamps actualizados
     */
    public Order saveOrder(Order order) {
        Instant now = Instant.now();
        
        Map<String, Object> attributes = order.toDynamoDbAttributes();
        Map<String, AttributeValue> item = convertToAttributeValue(attributes);
        
        item.put(ORDER_ID_ATTRIBUTE, AttributeValue.builder().s(order.orderId()).build());
        item.put(CUSTOMER_ID_ATTRIBUTE, AttributeValue.builder().s(order.customerId()).build());
        item.put(STATUS_ATTRIBUTE, AttributeValue.builder().s(order.status()).build());
        item.put(TOTAL_AMOUNT_ATTRIBUTE, AttributeValue.builder().n(String.valueOf(order.totalAmount())).build());
        item.put(ITEMS_ATTRIBUTE, AttributeValue.builder().s(order.items().toString()).build());
        item.put(CREATED_AT_ATTRIBUTE, AttributeValue.builder().s(now.toString()).build());
        item.put(UPDATED_AT_ATTRIBUTE, AttributeValue.builder().s(now.toString()).build());

        PutItemRequest putRequest = PutItemRequest.builder()
                .tableName(TABLE_NAME)
                .item(item)
                .build();

        try {
            dynamoDBClient.putItem(putRequest);
            return Order.fromDynamoDbAttributes(convertToObjectMap(item));
        } catch (DynamoDbException e) {
            throw new RuntimeException("Error al guardar el pedido en DynamoDB: " + e.getMessage(), e);
        }
    }

    /**
     * Recupera un pedido por su ID.
     * @param orderId identificador del pedido
     * @return Optional con el pedido si existe, vacío si no
     */
    public Optional<Order> findById(String orderId) {
        Map<String, AttributeValue> key = new HashMap<>();
        key.put(ORDER_ID_ATTRIBUTE, AttributeValue.builder().s(orderId).build());

        GetItemRequest getRequest = GetItemRequest.builder()
                .tableName(TABLE_NAME)
                .key(key)
                .build();

        try {
            GetItemResponse response = dynamoDBClient.getItem(getRequest);
            
            if (!response.hasItem() || response.item().isEmpty()) {
                return Optional.empty();
            }
            
            Map<String, AttributeValue> item = response.item();
            Map<String, Object> attributes = convertToObjectMap(item);
            Order order = Order.fromDynamoDbAttributes(attributes);
            return Optional.of(order);
        } catch (DynamoDbException e) {
            throw new RuntimeException("Error al leer el pedido de DynamoDB: " + e.getMessage(), e);
        }
    }

    /**
     * Actualiza el estado de un pedido.
     * @param orderId identificador del pedido
     * @param newStatus nuevo estado
     * @return el pedido actualizado
     */
    public Order updateOrderStatus(String orderId, String newStatus) {
        Instant now = Instant.now();
        
        Map<String, AttributeValue> key = new HashMap<>();
        key.put(ORDER_ID_ATTRIBUTE, AttributeValue.builder().s(orderId).build());

        Map<String, AttributeValueUpdate> attributeUpdates = new HashMap<>();
        attributeUpdates.put(STATUS_ATTRIBUTE, AttributeValueUpdate.builder()
                .value(AttributeValue.builder().s(newStatus).build())
                .action(AttributeAction.PUT)
                .build());
        attributeUpdates.put(UPDATED_AT_ATTRIBUTE, AttributeValueUpdate.builder()
                .value(AttributeValue.builder().s(now.toString()).build())
                .action(AttributeAction.PUT)
                .build());

        UpdateItemRequest updateRequest = UpdateItemRequest.builder()
                .tableName(TABLE_NAME)
                .key(key)
                .attributeUpdates(attributeUpdates)
                .build();

        try {
            dynamoDBClient.updateItem(updateRequest);
            return findById(orderId)
                    .orElseThrow(() -> new RuntimeException("Pedido no encontrado después de actualizar"));
        } catch (DynamoDbException e) {
            throw new RuntimeException("Error al actualizar el pedido en DynamoDB: " + e.getMessage(), e);
        }
    }

    /**
     * Elimina un pedido por su ID.
     * @param orderId identificador del pedido
     */
    public void deleteOrder(String orderId) {
        Map<String, AttributeValue> key = new HashMap<>();
        key.put(ORDER_ID_ATTRIBUTE, AttributeValue.builder().s(orderId).build());

        DeleteItemRequest deleteRequest = DeleteItemRequest.builder()
                .tableName(TABLE_NAME)
                .key(key)
                .build();

        try {
            dynamoDBClient.deleteItem(deleteRequest);
        } catch (DynamoDbException e) {
            throw new RuntimeException("Error al eliminar el pedido de DynamoDB: " + e.getMessage(), e);
        }
    }

    /**
     * Convierte un mapa de objetos a un mapa de AttributeValue de DynamoDB.
     * @param attributes mapa de atributos como objetos
     * @return mapa de AttributeValue
     */
    private Map<String, AttributeValue> convertToAttributeValue(Map<String, Object> attributes) {
        Map<String, AttributeValue> result = new HashMap<>();
        for (Map.Entry<String, Object> entry : attributes.entrySet()) {
            Object value = entry.getValue();
            if (value instanceof String) {
                result.put(entry.getKey(), AttributeValue.builder().s((String) value).build());
            } else if (value instanceof Number) {
                result.put(entry.getKey(), AttributeValue.builder().n(value.toString()).build());
            } else if (value instanceof Boolean) {
                result.put(entry.getKey(), AttributeValue.builder().bool((Boolean) value).build());
            }
        }
        return result;
    }

    /**
     * Convierte un mapa de AttributeValue a un mapa de objetos simples.
     * @param item mapa de AttributeValue
     * @return mapa de objetos
     */
    private Map<String, Object> convertToObjectMap(Map<String, AttributeValue> item) {
        Map<String, Object> result = new HashMap<>();
        for (Map.Entry<String, AttributeValue> entry : item.entrySet()) {
            AttributeValue av = entry.getValue();
            if (av.s() != null) {
                result.put(entry.getKey(), av.s());
            } else if (av.n() != null) {
                result.put(entry.getKey(), av.n());
            } else if (av.bool() != null) {
                result.put(entry.getKey(), av.bool());
            } else if (av.hasL()) {
                result.put(entry.getKey(), av.l());
            } else if (av.hasM()) {
                result.put(entry.getKey(), convertToObjectMap(av.m()));
            }
        }
        return result;
    }
}

// === ARCHIVO: docker-compose.yml ===
# Infraestructura local del reto. SOLO desarrollo: credenciales de ejemplo (local-dev-only), puertos en loopback.
# Levantar: docker compose up -d --wait
name: infra-local
services:
  dynamodb-local:
    image: "amazon/dynamodb-local:2.5.2"
    command: ["-jar","DynamoDBLocal.jar","-sharedDb","-inMemory"]
    ports:
      - "127.0.0.1:8000:8000"
    healthcheck:
      test: ["CMD-SHELL","bash -c ':> /dev/tcp/127.0.0.1/8000' || exit 1"]
      interval: 10s
      timeout: 5s
      retries: 10
      start_period: 5s


// === ARCHIVO: infra-local/README.md ===
# Infraestructura local

Servicios de soporte para el reto, definidos en `docker-compose.yml` (raiz del repo).

```bash
docker compose up -d --wait
```

| Servicio | Imagen | Puertos (host) |
|---|---|---|
| DynamoDB Local | `amazon/dynamodb-local:2.5.2` | 8000 |

Credenciales: solo de desarrollo local (`local-dev-only`, `local`). No son secretos ni deben reutilizarse.
Los scripts de `infra-local/` (tablas, topics) se ejecutan con el compose en estado healthy.

Tablas de DynamoDB: `orders` (pk `id`).


// === ARCHIVO: infra-local/dynamodb/create-tables.sh ===
#!/usr/bin/env bash
# Crea las tablas en DynamoDB Local (idempotente). Credenciales dummy: DynamoDB Local no valida firmas.
set -euo pipefail

export AWS_ACCESS_KEY_ID=local
export AWS_SECRET_ACCESS_KEY=local
export AWS_REGION=us-east-1
ENDPOINT="${DYNAMODB_ENDPOINT:-http://localhost:8000}"

create_table() {
  local name="$1" pk="$2" sk="${3:-}"
  if aws dynamodb describe-table --table-name "$name" --endpoint-url "$ENDPOINT" >/dev/null 2>&1; then
    echo "Tabla $name ya existe"
    return
  fi
  local attrs="AttributeName=$pk,AttributeType=S"
  local keys="AttributeName=$pk,KeyType=HASH"
  if [ -n "$sk" ]; then
    attrs="$attrs AttributeName=$sk,AttributeType=S"
    keys="$keys AttributeName=$sk,KeyType=RANGE"
  fi
  # shellcheck disable=SC2086
  aws dynamodb create-table --table-name "$name" --attribute-definitions $attrs --key-schema $keys \
    --billing-mode PAY_PER_REQUEST --endpoint-url "$ENDPOINT" >/dev/null
  echo "Tabla $name creada"
}

create_table "orders" "id"

```
