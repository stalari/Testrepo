import request from "supertest";
import { createApp } from "../src/app.js";
import type { AppConfig } from "../src/config.js";
import type { BazClient, FooClient } from "../src/httpClients.js";

describe("GET /hello (legacy servlet parity)", () => {
  test("returns HTML with embedded Foo message and legacy line structure", async () => {
    const config: AppConfig = {
      port: 0,
      wiringSomeFoo: "AcmeFooBean",
      fooDefaultBaseUrl: "http://unused",
      fooAcmeBaseUrl: "http://unused",
      bazBaseUrl: "http://unused"
    };

    const fooClient: FooClient = { tellMeSomething: async () => "FOO_MSG" };
    const bazClient: BazClient = { baz: async () => "baaaaaaaazzzzz!" };

    const app = createApp({ config, fooClient, bazClient });

    const res = await request(app).get("/hello").expect(200);

    expect(res.text).toBe(
      [
        "<html>",
        "<head><title>Hello World Servlet</title></head>",
        "<body>",
        "    <h3>Hello World from a Servlet!</h3>",
        "    <p><b>Let's listen to FOO:</b> FOO_MSG</p>",
        "<body>",
        "</html>",
        ""
      ].join("\n")
    );
  });
});