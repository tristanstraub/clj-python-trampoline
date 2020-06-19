import javabridge

jars = javabridge.JARS + ["./target/clj_python_trampoline.jar"]
javabridge.start_vm(run_headless=True, class_path=jars)

env = javabridge.get_env()
c = env.find_class("Main")
method_id = env.get_static_method_id(c, "bootstrap", "()V")
env.call_static_method(c, method_id)
