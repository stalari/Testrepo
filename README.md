# JavaEE_EJB_Wiring_Demo (Modernized)

This repository modernizes the legacy Java EE EAR (WAR + multiple EJB modules + application.xml wiring overrides + JNDI remote client) into an AWS-native friendly microservices-style layout while preserving **observable business behavior** defined by the legacy codebase and knowledge base rules.

## What is preserved (behavioral parity)

- `Foo.tellMeSomething()` includes `Bar.sayHello()` output (RULE-001)
- `AcmeFooBean.tellMeSomething()` prefixes and preserves base behavior (RULE-002)
- `CoffeeFacade.drinkLocalCoffee()` uses the injected **local** Coffee path; `drinkRemoteCoffee()` uses the injected **remote** Coffee path (RULE-003)
- Deployment-time ambiguity resolution is modeled via **explicit wiring configuration**:
  - `some/Foo` selects Foo implementation (RULE-006)
  - `org.sample.FooBean/bar` selects Bar implementation used by Foo Default (RULE-005)
- Browser workflow `GET /hello` returns HTML containing the Foo message (HTTP_Hello_Request)
- Webapp lifecycle listener behavior is reproduced by the Node edge startup hook (Webapp_Lifecycle_Listener)

## Repository structure

- `services/*` — Java 17 + Spring Boot services
  - `bar-default`, `bar-acme`
  - `foo-default`, `foo-acme`
  - `baz`
  - `coffee`
  - `coffee-facade`
  - `coffee-client` (standalone Java program)
- `edge/hello-web-edge` — Node.js + TypeScript edge service (browser entrypoint)

## Build (Java)

Prereqs:
- JDK 17+
- Maven 3.9+

From repo root:

```bash
mvn -q -DskipTests=false test
```

To build jars:

```bash
mvn -q package
```

## Run locally (one possible setup)

Start services (separate terminals). Each service has a default port:

- bar-default: `8081`
- bar-acme: `8082`
- foo-default: `8083`
- foo-acme: `8084`
- baz: `8085`
- coffee-facade: `8086`
- coffee: `8087`
- hello-web-edge: `3000`

### 1) Start Java services

```bash
mvn -q -pl services/bar-default spring-boot:run
mvn -q -pl services/bar-acme spring-boot:run
mvn -q -pl services/foo-default spring-boot:run
mvn -q -pl services/foo-acme spring-boot:run
mvn -q -pl services/baz spring-boot:run
mvn -q -pl services/coffee spring-boot:run
mvn -q -pl services/coffee-facade spring-boot:run
```

`foo-default` requires wiring config (ambiguity resolution) via environment variables:

```bash
export WIRING_ORG_SAMPLE_FOOBEAN_BAR=AcmeBarBean
```

### 2) Start the Node edge

Prereqs:
- Node.js 18+ (Node 20+ recommended)
- npm 9+

```bash
cd edge/hello-web-edge
npm ci
export WIRING_SOME_FOO=AcmeFooBean
export FOO_DEFAULT_BASE_URL=http://localhost:8083
export FOO_ACME_BASE_URL=http://localhost:8084
export BAZ_BASE_URL=http://localhost:8085
npm run dev
```

Open:
- http://localhost:3000/ (redirect page)
- http://localhost:3000/hello (HTML output)

### 3) Run the Coffee client

```bash
mvn -q -pl services/coffee-client exec:java -Dexec.mainClass=org.sample.remotelocal.CoffeeMain
```

It calls `COFFEE_FACADE_BASE_URL` (default `http://localhost:8086`) and prints results, printing stack traces to stderr on errors (legacy parity).

## Test execution

### Java tests
```bash
mvn test
```

### Node tests
```bash
cd edge/hello-web-edge
npm test
```

---

## Wiring configuration keys (parity with legacy application.xml intent)

- `some/Foo` (modeled as env var `WIRING_SOME_FOO`)
  - Allowed values: `FooBean` or `AcmeFooBean`

- `org.sample.FooBean/bar` (modeled as env var `WIRING_ORG_SAMPLE_FOOBEAN_BAR`)
  - Allowed values: `BarBean` or `AcmeBarBean`

If these keys are missing or invalid, services fail fast at startup (mirrors legacy deployment-time ambiguity failure behavior).