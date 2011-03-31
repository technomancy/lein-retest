(ns leiningen.retest
  (:require [leiningen.test]))

(defn retest
  "Run only the test namespaces which failed last time around.
Requires loading leiningen.hooks.retest to :hooks in project.clj."
  [project]
  ;; TODO: detect branch change; clear failure list
  (let [failed-namespaces (sort (read-string (slurp ".lein-failures")))]
    (apply leiningen.test/test project (map name failed-namespaces))))
