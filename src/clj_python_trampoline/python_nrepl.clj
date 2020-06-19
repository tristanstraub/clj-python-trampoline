(ns clj-python-trampoline.python-nrepl
  (:require [clojure.string :as str]
            [nrepl.cmdline]
            [clj-python-trampoline.interpreter] ;; patch libpython-clj.python.interpreter/initialize! to work with already loaded python library
            )
  (:gen-class))

(defn -main
  [& _]
  (future
    (try
      ;; Find python executable and library, and initialize. clj-python-trampoline.interpreter
      ;; has overriden libpython-clj.python.interpreter/initialize! to allow initialization from
      ;; already running python process
      (require 'libpython-clj.require)
      (println "NREPL_CMDLINE_ARGS" (str/split (or (System/getenv "NREPL_CMDLINE_ARGS")
                                                   "")
                                               #" "))
      (apply nrepl.cmdline/-main (str/split (or (System/getenv "NREPL_CMDLINE_ARGS")
                                                "")
                                            #" "))
      (catch Throwable e
        (println e)
        (throw e)))))
