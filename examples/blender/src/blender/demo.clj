(ns blender.demo
  (:require [libpython-clj.require]
            [libpython-clj.python :refer [py..] :as py]))

(defn get-defaults
  []
  (let [bpy       (py/import-module "bpy")
        window    (first (seq (py.. bpy -context -window_manager -windows)))
        area      (first (filter #(= "VIEW_3D" (py.. % -type)) (py.. window -screen -areas)))
        region    (first (filter #(= "WINDOW" (py.. % -type)) (py.. area -regions)))
        workspace (first (filter #(= "Layout" (py.. % -name)) (py.. bpy -data -workspaces)))
        ctx       (py.. bpy -context (copy))]
    (py.. ctx (update (py/->py-dict {"window"    window
                                     "screen"    (py.. window -screen)
                                     "area"      area
                                     "region"    region
                                     "workspace" workspace})))
    ctx))

(defn info
  []
  (py.. (py/import-module "bpy") -app))

(defn cubes
  []
  (let [bpy      (py/import-module "bpy")
        on-timer (fn []
                   (dotimes [_ 10]
                     (let [location (vec (repeatedly 3 #(- (rand-int 20) 10)))]
                       (py.. bpy -ops -mesh
                             (primitive_cube_add (get-defaults)
                                                 :size 3
                                                 :location location)))))]
    (py.. bpy -app -timers (register on-timer))))

(comment
  (info)
  (cubes)

  )
