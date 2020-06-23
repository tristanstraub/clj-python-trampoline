(ns clj-python-trampoline.resources
  (:require [clojure.java.io :as io]))

(defn resource-clj
  []
  (print (slurp (io/resource "clj.py"))))

(defn resource-requirements
  []
  (print (slurp (io/resource "requirements.txt"))))

(defn -main
  [& args]
  (doseq [arg args]
    (cond (= arg "--clj")
          (resource-clj)
          (= arg "--requirements")
          (resource-requirements))))
