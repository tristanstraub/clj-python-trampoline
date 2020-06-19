import os, javabridge, subprocess

print("cmd:" + "clj -Spath " + os.environ["CLJ_ARGS"])
classpath=str(subprocess.check_output("clj -Spath " + os.environ["CLJ_ARGS"], shell=True))

jars = javabridge.JARS + [classpath]
javabridge.start_vm(run_headless=True, class_path=jars)

env = javabridge.get_env()
c = env.find_class("clj_python_trampoline/python_nrepl")
method_id = env.get_static_method_id(c, "main", "([Ljava/lang/String;)V")
env.call_static_method(c, method_id, None)
