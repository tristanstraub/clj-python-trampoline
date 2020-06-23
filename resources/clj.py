import sys, os, javabridge

def main(*args):
    jars = javabridge.JARS
    classpath=os.getenv("CLASSPATH", None)
    if classpath:
        jars = jars + [classpath]

    javabridge.start_vm(run_headless=True, class_path=jars)

    env = javabridge.get_env()
    stringclass = env.find_class("java/lang/String")

    print('args:', args)

    cljargs = env.make_object_array(len(args), stringclass)
    for i, arg in enumerate(args):
        env.set_object_array_element(cljargs, i, env.new_string_utf(arg))

    c = env.find_class("clojure/main")
    method_id = env.get_static_method_id(c, "main", "([Ljava/lang/String;)V")
    env.call_static_method(c, method_id, cljargs)

if __name__ == "__main__":

    args = sys.argv[:]

    if '--' in args:
        main(*args[args.index("--") + 1:])
    else:
        main()
