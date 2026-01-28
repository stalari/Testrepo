# hello-web-edge

Node.js + TypeScript edge service that provides the browser entrypoint `GET /hello` and reproduces the legacy webapp lifecycle listener behavior by invoking Baz and Foo on startup and writing to stderr.

## Wiring (required)

- `WIRING_SOME_FOO` must be set to `FooBean` or `AcmeFooBean`.

## Run

```bash
npm ci
export WIRING_SOME_FOO=AcmeFooBean
npm run build
npm start
```

---

{
  "behavioralEquivalence": {
    "value": true,
    "reasons": [
      "Legacy FooBean.tellMeSomething returns exactly \"Foo tells you (this is what Bar said): \" + bar.sayHello(); the modern foo-default service preserves the exact string format and concatenation in FooService.tellMeSomething(), verified by FooServiceTest.",
      "Legacy EAR application.xml overrides ejb-ref-name 'org.sample.FooBean/bar' to ejb-link 'AcmeBarBean' and ejb-ref-name 'some/Foo' to ejb-link 'AcmeFooBean'; the modern system enforces the same deployment-time ambiguity resolution via mandatory env vars WIRING_ORG_SAMPLE_FOOBEAN_BAR and WIRING_SOME_FOO with fail-fast startup validation mirroring legacy deployment failure when ambiguous.",
      "Legacy CoffeeFacadeBean outputs \"drinking Wacker's Kaffee: \" and \"drinking Starbucks: \" and appends the injected dependency runtime class simple name via getClass().getSimpleName(); the modern coffee-facade service reproduces the same output structure and uses distinct injected client instances so the runtime simple-name behavior remains observable and equivalent."
    ]
  }
}

GITHUB DELIVERY INSTRUCTIONS

```bash
git status

# Initialize if needed
git init

# Ensure default branch is main (if not already)
git checkout -B main

# Add all files
git add .

# Commit
git commit -m "Modernize JavaEE EJB wiring demo into Spring Boot services + Node edge with parity tests"

# Push
git push -u origin main
```

Committed content:
- A Maven multi-module Java 17 codebase implementing Bar/Foo/Baz/Coffee/CoffeeFacade behaviors with explicit wiring override configuration and comprehensive unit tests.
- A Node.js + TypeScript edge service providing `GET /hello`, static redirect page, and startup/shutdown lifecycle logging equivalent to the legacy servlet context listener, with unit tests.