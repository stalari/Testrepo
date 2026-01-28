export type FooSelection = "FooBean" | "AcmeFooBean";

export interface AppConfig {
  port: number;
  wiringSomeFoo: FooSelection;
  fooDefaultBaseUrl: string;
  fooAcmeBaseUrl: string;
  bazBaseUrl: string;
}

export function loadConfig(env: NodeJS.ProcessEnv): AppConfig {
  const port = parseInt(env.PORT ?? "3000", 10);

  const wiringSomeFoo = env.WIRING_SOME_FOO;
  if (wiringSomeFoo !== "FooBean" && wiringSomeFoo !== "AcmeFooBean") {
    throw new Error(
      "Ambiguous or invalid wiring for key 'some/Foo'. Expected 'FooBean' or 'AcmeFooBean' " +
        `but got: '${wiringSomeFoo ?? ""}'. Set env var WIRING_SOME_FOO to resolve.`
    );
  }

  const fooDefaultBaseUrl = env.FOO_DEFAULT_BASE_URL ?? "http://localhost:8083";
  const fooAcmeBaseUrl = env.FOO_ACME_BASE_URL ?? "http://localhost:8084";
  const bazBaseUrl = env.BAZ_BASE_URL ?? "http://localhost:8085";

  return {
    port,
    wiringSomeFoo,
    fooDefaultBaseUrl,
    fooAcmeBaseUrl,
    bazBaseUrl
  };
}