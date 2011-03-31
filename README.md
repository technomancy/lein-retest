# lein-retest

Run only the test namespaces which failed last time around.

## Single-project Usage

You'll need to add a :dependency, a :dev-dependency, and a :hook to
project.clj:

    :dependencies [[robert/hooke "1.1.0"]]
    :dev-dependencies [[lein-retest "1.0.0"]]
    :hooks [leiningen.hooks.retest]

Then each run of the tests will create a .lein-failures file in your
project root recording which namespaces contained failures. Then
running `lein retest` will re-run only those.

## User-wide Usage

    $ lein plugin install lein-retest 1.0.0

Add this to ~/.lein/init.clj

    (require 'leiningen.hooks.retest)

Then you should be set to use it in all your projects, though each
project will still need to have Robert Hooke as a :dependency for it
to work.

## License

Copyright (C) 2011 Phil Hagelberg

Distributed under the Eclipse Public License, the same as Clojure.
