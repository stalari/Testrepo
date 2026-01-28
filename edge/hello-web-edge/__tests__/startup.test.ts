import { runStartupTasks } from "../src/startup.js";
import type { BazClient, FooClient } from "../src/httpClients.js";

describe("startup listener parity", () => {
  test("prints legacy listener messages to stderr", async () => {
    const writes: string[] = [];
    const originalWrite = process.stderr.write.bind(process.stderr);

    (process.stderr.write as unknown as (s: string) => boolean) = (s: string) => {
      writes.push(s);
      return true;
    };

    const bazClient: BazClient = { baz: async () => "baaaaaaaazzzzz!" };
    const fooClient: FooClient = { tellMeSomething: async () => "FOO" };

    try {
      await runStartupTasks({ bazClient, fooClient });
    } finally {
      (process.stderr.write as unknown as typeof originalWrite) = originalWrite;
    }

    expect(writes.join("")).toBe("---- context initialized\n  baz is: baaaaaaaazzzzz!\n  foo says: FOO\n");
  });
});