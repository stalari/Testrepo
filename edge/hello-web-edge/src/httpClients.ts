import type { FooSelection } from "./config.js";

export interface FooClient {
  tellMeSomething(): Promise<string>;
}

export interface BazClient {
  baz(): Promise<string>;
}

export class HttpFooClient implements FooClient {
  constructor(private readonly baseUrl: string) {}

  async tellMeSomething(): Promise<string> {
    const res = await fetch(`${this.baseUrl}/foo/tell-me-something`, {
      method: "GET",
      headers: { Accept: "text/plain" }
    });
    if (!res.ok) {
      throw new Error(`Foo request failed with status ${res.status}`);
    }
    return await res.text();
  }
}

export class HttpBazClient implements BazClient {
  constructor(private readonly baseUrl: string) {}

  async baz(): Promise<string> {
    const res = await fetch(`${this.baseUrl}/baz`, { method: "GET", headers: { Accept: "text/plain" } });
    if (!res.ok) {
      throw new Error(`Baz request failed with status ${res.status}`);
    }
    return await res.text();
  }
}

export function createSelectedFooClient(opts: {
  selection: FooSelection;
  fooDefaultBaseUrl: string;
  fooAcmeBaseUrl: string;
}): FooClient {
  if (opts.selection === "FooBean") {
    return new HttpFooClient(opts.fooDefaultBaseUrl);
  }
  return new HttpFooClient(opts.fooAcmeBaseUrl);
}