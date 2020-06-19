import javabridge, subprocess

classpath=str(subprocess.run(["clj", "-Spath"], capture_output=True).stdout)

jars = javabridge.JARS + [classpath]
javabridge.start_vm(run_headless=True, class_path=jars)

env = javabridge.get_env()
c = env.find_class("CljPythonTrampolineMain")
method_id = env.get_static_method_id(c, "bootstrap", "()V")
env.call_static_method(c, method_id)
