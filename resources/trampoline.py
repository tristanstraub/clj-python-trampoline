import os, javabridge, subprocess

classpath=subprocess.check_output("clj " + os.environ["CLJ_ARGS"] + " -Spath ", shell=True).decode('utf-8')
print("classpath:", classpath)

jars = javabridge.JARS + [classpath]
javabridge.start_vm(run_headless=True, class_path=jars)

env = javabridge.get_env()
stringclass = env.find_class("java/lang/String")

cljargs = env.make_object_array(2, stringclass)
env.set_object_array_element(cljargs, 0, env.new_string_utf("-e"))
env.set_object_array_element(cljargs, 1, env.new_string_utf("(require 'clj-python-trampoline.python-nrepl) (clj-python-trampoline.python-nrepl/-main)"))

c = env.find_class("clojure/main")
method_id = env.get_static_method_id(c, "main", "([Ljava/lang/String;)V")
env.call_static_method(c, method_id, cljargs)
