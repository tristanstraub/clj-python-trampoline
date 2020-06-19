(ns clj-python-trampoline.resources
  (:require [clojure.java.io :as io]))

(defn script
  []
  (print (slurp (io/resource "trampoline.py"))))

(defn requirements
  []
  (print (slurp (io/resource "requirements.txt"))))

(defn template
  []
  (print (slurp (io/resource "template.sh"))))

(defn -main
  [& args]
  (doseq [arg args]
    (cond (= arg "--script")
          (script)
          (= arg "--requirements")
          (requirements)
          (= arg "--template")
          (template))))
