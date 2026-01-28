import express from "express";
import path from "node:path";
import type { AppConfig } from "./config.js";
import type { BazClient, FooClient } from "./httpClients.js";

function legacyHelloHtml(fooMessage: string): string {
  const lines = [
    "<html>",
    "<head><title>Hello World Servlet</title></head>",
    "<body>",
    "    <h3>Hello World from a Servlet!</h3>",
    `    <p><b>Let's listen to FOO:</b> ${fooMessage}</p>`,
    "<body>",
    "</html>"
  ];
  return lines.join("\n") + "\n";
}

export function createApp(deps: { config: AppConfig; fooClient: FooClient; bazClient: BazClient }) {
  const app = express();

  const publicDir = path.resolve(process.cwd(), "public");
  app.use(express.static(publicDir));

  app.get("/hello", async (_req, res) => {
    const fooMessage = await deps.fooClient.tellMeSomething();
    const html = legacyHelloHtml(fooMessage);

    res.status(200);
    res.setHeader("Content-Type", "text/html; charset=utf-8");
    res.send(html);
  });

  return app;
}