(ns clj-python-trampoline.cli
  (:require [clojure.java.io :as io]))

(defn script
  []
  (print (slurp (io/resource "trampoline.py"))))

(defn requirements
  []
  (print (slurp (io/resource "requirements.txt"))))

(defn -main
  [& args]
  (doseq [arg args]
    (cond (= arg "--script")
          (script)
          (= arg "--requirements")
          (requirements))))
