(ns clj-python-trampoline.bootstrap
  (:require [nrepl.cmdline]
            [clj-python-trampoline.interpreter] ;; patch libpython-clj.python.interpreter/initialize! to work with already loaded python library
            ))

(defn -main
  []
  (future
    (require 'libpython-clj.require)
    (nrepl.cmdline/-main)))
