(ns blender.app
  (:require [libpython-clj.python :refer [py..] :as py]))

(defn info
  []
  (py.. (py/import-module "bpy") -app))

(comment
  (info)

  )
