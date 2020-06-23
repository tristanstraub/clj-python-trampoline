(ns clj-python-trampoline.resources
  (:require [clojure.java.io :as io]))

(defn nrepl
  []
  (print (slurp (io/resource "nrepl.py"))))

(defn requirements
  []
  (print (slurp (io/resource "requirements.txt"))))

(defn -main
  [& args]
  (doseq [arg args]
    (cond (= arg "--nrepl")
          (nrepl)
          (= arg "--requirements")
          (requirements))))
