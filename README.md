# The Power of Lacinia & Hystrix in Production

This repo has the code used for the talk The Power of Lacinia & Hystrix in Production initially prepared
for the Clojure/conj 2017.

## Table of Contents

* [Organization](#organization)
* [Presentation](#presentation)
* [Running The Demo Project](#running-the-demo-project)
* [Simulating Latency](#simulating-latency)
* [Load Testing](#load-testing)

## Organization

* [e-commerce-demo](/e-commerce-demo) - contains the demo project that was used for this presentation
* [statistics](/statistics) - contains the scripts for running the load tests as well as a dump of the test results used on the talk

## Presentation

* [Deck](https://speakerdeck.com/luchiniatwork/the-power-of-lacinia-and-hsytrix-in-production)
* Video

## Running The Demo Project

Refer to the README on [e-commerce-demo](/e-commerce-demo)

## Simulating Latency

The [e-commerce-demo](/e-commerce-demo) has a simple embedded latency simulator that can be configured
at `e-commerce-demo.application`.

The application gets initialized with two simulators and their settings are very straight-forward:

```clojure
   :delayer-prods-config {:mean 100
                          :std-dev 100}
```

## Load Testing

I've used Gil Tene's great [wrk2](https://github.com/giltene/wrk2) as a HTTP benchmarking tool.
It produces a constant throughput load and accuratly calculate latency details to the high 9s.

To build it for MacOS make sure to have XCode Command Line Tools and `brew` installed. Then you'll
need to have `openssl` and `expat` installed:

    $ brew install openssl
    $ brew install expat

Mac's `make` needs a bit help finding the libs:

    export LDFLAGS="-L/usr/local/opt/openssl/lib -L/usr/local/lib -L/usr/local/opt/expat/lib"
    export CFLAGS="-I/usr/local/opt/openssl/include/ -I/usr/local/include -I/usr/local/opt/expat/include"
    export CPPFLAGS="-I/usr/local/opt/openssl/include/ -I/usr/local/include -I/usr/local/opt/expat/include"
    export CPATH=/usr/local/opt/openssl/include:"${CPATH}"
    
    make

Then you can run the load script against your `localhost` with:

    $ cd statistics/
    $ wrk -t8 -c128 -d2m -R100 -s scripts/post.lua http://localhost:3000/graphql\?

The latency report will be printed after 2 minutes (`-d` parameter above).

Notice that `wrk2` runs its sampling initializer for 10 seconds and therefore shorter runs tend to
be more imprecise.

The constant rate parameter (`-R`) may also have different implications based on your system's
capabilities.

## License

Copyright © 2017 Tiago Luchini

Distributed under the MIT License.
