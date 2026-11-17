# Automatización de Pruebas de Integración con DynamoDB

El sistema de gestión de pedidos utiliza una tabla DynamoDB para persistir la información de los pedidos. Es necesario automatizar pruebas de integración que validen la correcta lectura y escritura de datos en esta tabla. El objetivo es cubrir el flujo completo de persistencia de pedidos, desde la creación hasta la verificación de los datos almacenados.

## Informacion General

| Campo | Valor |
|-------|-------|
| **Tema** | Automatizacion de pruebas de integracion contra DynamoDB |
| **Nivel** | advanced-l2 |
| **Tipo** | practical |
| **Tiempo estimado** | 8 horas |

## Fases del Reto

### Fase 0: Configuración del Proyecto

**Objetivo:** Tener el proyecto base del reto funcionando en tu máquina: clonar el repositorio, instalar las herramientas y verificar que compila y arranca antes de empezar a practicar.

**Tiempo estimado:** 15-30 minutos

**Instrucciones:**

- Instala las herramientas para ejecutar el proyecto: JDK 17+ y Maven 3.9+.
- Clona el repositorio del reto (el enlace viene en la entrega del reto y en la sección "Repositorio del Proyecto" del PDF) y ábrelo en tu IDE.
- Instala las dependencias y compila ejecutando `mvn -q -B -DskipTests -Dspring-boot.repackage.skip=true package && docker compose config -q` en la raíz del repositorio.
- Confirma que el comando termina sin errores: el proyecto arranca y sus pruebas corren.
- Ubica los archivos de práctica: son los que contienen `TODO(reto)` (búscalos en el repositorio). Lee el README.md y el AGENTS.md antes de empezar.

**Entregable:** El proyecto compila/arranca sin errores con `mvn -q -B -DskipTests -Dspring-boot.repackage.skip=true package && docker compose config -q`.

<details>
<summary>Pistas de conocimiento</summary>

- Los archivos con `TODO(reto)` son el ejercicio: el resto del repositorio ya está resuelto y no hace falta tocarlo.
- Si el comando de verificación falla, revisa la versión de las herramientas instaladas antes de modificar código.
- AGENTS.md describe el reto, el comando de verificación y las reglas del repositorio.

</details>

### Fase 1: Configuración del Entorno de Pruebas

**Objetivo:** Configurar el entorno de pruebas para interactuar con DynamoDB.

**Tiempo estimado:** 2 horas

**Instrucciones:**

- Configura el proyecto para utilizar Serenity BDD con el patrón Screenplay.
- Establece las conexiones necesarias con DynamoDB.
- Crea un escenario que verifique la persistencia de un pedido en DynamoDB.

**Entregable:** Entorno de pruebas configurado y conectado a DynamoDB.

<details>
<summary>Pistas de conocimiento</summary>

- Revisa la documentación de Serenity BDD y el patrón Screenplay.
- Consulta la documentación de DynamoDB para establecer conexiones y realizar operaciones.

</details>

### Fase 2: Automatización de Pruebas de Escritura

**Objetivo:** Automatizar pruebas que validen la escritura de pedidos en DynamoDB.

**Tiempo estimado:** 3 horas

**Instrucciones:**

- Crea pruebas automatizadas que escriban pedidos en DynamoDB.
- Verifica que los pedidos persistidos cumplan con los criterios de aceptación.
- Utiliza esperas explícitas en lugar de Thread.sleep.

**Entregable:** Pruebas automatizadas que escriben y verifican pedidos en DynamoDB.

<details>
<summary>Pistas de conocimiento</summary>

- Revisa la documentación de Serenity BDD para crear pruebas automatizadas.
- Consulta la documentación de DynamoDB para realizar operaciones de escritura.

</details>

### Fase 3: Automatización de Pruebas de Lectura

**Objetivo:** Automatizar pruebas que validen la lectura de pedidos desde DynamoDB.

**Tiempo estimado:** 3 horas

**Instrucciones:**

- Crea pruebas automatizadas que lean pedidos desde DynamoDB.
- Verifica que los pedidos leídos cumplan con los criterios de aceptación.
- Utiliza esperas explícitas en lugar de Thread.sleep.

**Entregable:** Pruebas automatizadas que lean y verifiquen pedidos desde DynamoDB.

<details>
<summary>Pistas de conocimiento</summary>

- Revisa la documentación de Serenity BDD para crear pruebas automatizadas.
- Consulta la documentación de DynamoDB para realizar operaciones de lectura.

</details>

## Dimensiones Evaluadas

- **queEs**: ¿Qué es Serenity BDD y cómo se utiliza en este reto?
- **paraQueSirve**: ¿Para qué sirve el patrón Screenplay en este contexto?
- **comoSeUsa**: ¿Cómo se utilizan las esperas explícitas en lugar de Thread.sleep?
- **erroresComunes**: ¿Cuáles son los errores comunes al automatizar pruebas de integración con DynamoDB?
- **queDecisionesImplica**: ¿Qué decisiones implica la automatización de pruebas de integración con DynamoDB?

## Criterios de Evaluacion

- Configuración del entorno de pruebas con Serenity BDD y el patrón Screenplay.
- Automatización de pruebas de escritura en DynamoDB con esperas explícitas.
- Automatización de pruebas de lectura en DynamoDB con esperas explícitas.
- Verificación de la persistencia de pedidos en DynamoDB.

## Como trabajar con un asistente de IA

Hay dos caminos, elegi uno:

- **AGENTS.md** (recomendado) — instrucciones nativas del repo. Abri esta carpeta con tu agente local (Claude Code, Cursor, Codex, Copilot, Gemini) y las carga solo. Sabe que archivos faltan y con que comando se verifica, y completa el scaffold escribiendo en disco.
- **PROMPT_MEJORA.md** — para copiar y pegar en un chat (claude.ai, ChatGPT) si no tenes un agente en el IDE. Declara lo mismo que AGENTS.md; los archivos que te devuelva el chat los guardas en esta carpeta respetando las rutas.

Ninguno de los dos resuelve las fases del reto: eso es tu trabajo.

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

## Verificacion

El proyecto esta listo para trabajar cuando este comando corre sin errores:

```bash
mvn -q -B -DskipTests -Dspring-boot.repackage.skip=true package && docker compose config -q
```

---

*Reto generado automaticamente por Challenge Generator - Pragma*
