import { loadConfig } from "./config.js";
import { createSelectedFooClient, HttpBazClient } from "./httpClients.js";
import { createApp } from "./app.js";
import { registerShutdownHook, runStartupTasks } from "./startup.js";

async function main() {
  const config = loadConfig(process.env);

  const fooClient = createSelectedFooClient({
    selection: config.wiringSomeFoo,
    fooDefaultBaseUrl: config.fooDefaultBaseUrl,
    fooAcmeBaseUrl: config.fooAcmeBaseUrl
  });

  const bazClient = new HttpBazClient(config.bazBaseUrl);

  registerShutdownHook();
  await runStartupTasks({ bazClient, fooClient });

  const app = createApp({ config, fooClient, bazClient });
  app.listen(config.port, () => {
    process.stdout.write(`hello-web-edge listening on port ${config.port}\n`);
  });
}

main().catch((e) => {
  process.stderr.write(String(e?.stack ?? e) + "\n");
  process.exit(1);
});