import type { BazClient, FooClient } from "./httpClients.js";

export async function runStartupTasks(deps: { bazClient: BazClient; fooClient: FooClient }): Promise<void> {
  const bazOut = await deps.bazClient.baz();
  const fooOut = await deps.fooClient.tellMeSomething();

  process.stderr.write("---- context initialized\n");
  process.stderr.write(`  baz is: ${bazOut}\n`);
  process.stderr.write(`  foo says: ${fooOut}\n`);
}

export function registerShutdownHook(): void {
  const handler = () => {
    process.stderr.write("---- context destroyed\n");
    process.exit(0);
  };

  process.on("SIGTERM", handler);
  process.on("SIGINT", handler);
}