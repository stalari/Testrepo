import { loadConfig } from "../src/config.js";

describe("config wiring validation", () => {
  test("throws when WIRING_SOME_FOO is missing (ambiguity unresolved)", () => {
    expect(() => loadConfig({} as NodeJS.ProcessEnv)).toThrow(/some\/Foo/);
  });

  test("throws when WIRING_SOME_FOO is invalid", () => {
    expect(() => loadConfig({ WIRING_SOME_FOO: "NotABean" } as NodeJS.ProcessEnv)).toThrow(/some\/Foo/);
  });

  test("loads when WIRING_SOME_FOO is FooBean", () => {
    const cfg = loadConfig({ WIRING_SOME_FOO: "FooBean" } as NodeJS.ProcessEnv);
    expect(cfg.wiringSomeFoo).toBe("FooBean");
  });
});