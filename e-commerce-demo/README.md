# e-commerce-demo

FIXME: GraphQL server for e-commerce-demo

## Table of Contents

* [Getting Started](#getting-started)
* [More Details](#more-details)
* [Bugs](#bugs)
* [Help!](#help)

## Getting Started

You can start the server via `lein` with:

    $ lein run

Or directly with Java from the `jar`:

    $ lein uberjar
    $ cd target/
    $ java -jar e-commerce-demo.jar

You should see the server initializing and ready:

```
[main] INFO org.eclipse.jetty.server.Server - jetty-9.4.0.v20161208
[main] INFO org.eclipse.jetty.server.handler.ContextHandler - Started o.e.j.s.ServletContextHandler@6e892437{/,null,AVAILABLE}
[main] INFO org.eclipse.jetty.server.AbstractConnector - Started ServerConnector@5a208e17{HTTP/1.1,[http/1.1, h2c]}{0.0.0.0:3000}
[main] INFO org.eclipse.jetty.server.Server - Started @17352ms
```

Open another terminal and fire a simple query against the server:

    $ curl localhost:8888/graphql -X POST -H "content-type: application/graphql" -d '{ hello }'

You should see:

    {"data":{"hello":"... world!!!!"}}

Point your browser to [http://localhost:3000](http://localhost:3000) and you will see graphiQL's interface.

## More Details

### Component Architecture

This implementation follows the component/reloaded approach. The system is defined at
`e-commerce-demo.application`

### Reloading and Working on the REPL

When you initialize a REPL, you'll be in the `user` namespace by default. Over there you
can fire the whole system with `(go)`. `(stop)` and `(start)` are also available as well as
`(reset)` and `(reset-all)`. These should give you directly access to reload the system during
development.

### App Settings

The default port is 3000 but this can be changed in `e-commerce-demo.config`.

### Modelling

A sample schema is defined in `resources/schemas/hello.umlaut`. It is defined using
[Umlaut](http://github.com/workco/umlaut).

### Resolvers

The sample only has one resolver which is defined in `e-commerce-demo.resolvers.hello`. Resolvers
are aggregated via `e-commerce-demo.resolvers.core`.

### Extra Routes

Besides GraphQL's `/graphql` this sample also shows how to add extra routes on `e-commerce-demo.components.routes`.

## Bugs

FIXME

## Help

FIXME

## License

Copyright © 2017 FIXME

Distributed under the FIXME.
