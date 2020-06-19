(ns clj-python-trampoline.interpreter)

(require '[libpython-clj.python.interpreter])

(defn initialize!
  [& args]
  (apply libpython-clj.python.interpreter/initialize! args))

(in-ns 'libpython-clj.python.interpreter)

;; Directly out of libpython-clj. Obviously, changes like this belong in libpython-clj,
;; but this idea is still being developed.
(defn initialize!
  [& {:keys [program-name
             library-path]
      :as options}]
  (when-not (main-interpreter)
    (log-info (str "Executing python initialize with options:" options))
    (let [{:keys [python-home libnames java-library-path-addendum
                  executable] :as startup-info}
          (detect-startup-info options)
          library-names (concat
                         (when library-path
                           [library-path])
                         libnames
                         (libpy-base/library-names))]
      (reset! python-home-wide-ptr* nil)
      (reset! python-path-wide-ptr* nil)
      (log/infof "Trying python library names %s" (vec library-names))
      (when python-home
        (append-java-library-path! java-library-path-addendum)
        (reset! python-home-wide-ptr* (jna/string->wide-ptr python-home))
        (reset! python-path-wide-ptr* (jna/string->wide-ptr
                                       (format "%s/bin/python3"
                                               python-home))))
      (loop [[library-name & library-names] library-names]
        (if (and library-name
                 (not (try-load-python-library! library-name
                                                @python-home-wide-ptr*
                                                @python-path-wide-ptr*)))
          (recur library-names)))
      (setup-direct-mapping!))

    (construct-main-interpreter! nil nil)))

(in-ns 'clj-python-trampoline.interpreter)
