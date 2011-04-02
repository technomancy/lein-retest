(ns leiningen.hooks.retest
  (:use [robert.hooke :only [add-hook]]
        [leiningen.test :only [form-for-testing-namespaces
                               *exit-after-tests*]]))

(add-hook #'form-for-testing-namespaces
          (fn [form-for-testing & args]
            (let [orig-exit-after-tests *exit-after-tests*]
              (binding [*exit-after-tests* false]
                `(let [failures# (atom #{})]
                   (try (require '~'robert.hooke)
                        ((resolve 'robert.hooke/add-hook)
                         (resolve 'clojure.test/report)
                         (fn report-with-failures [report# m# & args#]
                           (when (#{:error :fail} (:type m#))
                             (swap! failures# conj
                                    (-> clojure.test/*testing-vars*
                                        first meta :ns ns-name)))
                           (apply report# m# args#)))
                        (catch Exception _#
                          (println "retest requires robert/hooke dep.")))
                   ~(apply form-for-testing args)
                   (spit ".lein-failures" (pr-str @failures#))
                   (if ~orig-exit-after-tests
                     (System/exit 0)))))))
