# lein-retest

Run only the test namespaces which failed last time around.

## Usage

You'll need to add a hook to project.clj:

    :hooks [leiningen.hooks.retest]

Then each run of the tests will create a .lein-failures file in your
project root recording which namespaces contained failures. Then
running `lein retest` will re-run only those.

## License

Copyright (C) 2011 Phil Hagelberg

Distributed under the Eclipse Public License, the same as Clojure.
