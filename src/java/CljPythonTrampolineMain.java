import clojure.lang.RT;
import clojure.java.api.Clojure;
import clojure.lang.IFn;

public class CljPythonTrampolineMain {
  public static void bootstrap() {
    try {
      RT.var("clojure.core", "require").invoke(RT.var("clojure.core","read-string").invoke("clj-python-trampoline.bootstrap"));
      RT.var("clj-python-trampoline.bootstrap", "-main").invoke();
    } catch (Throwable e) {
      System.err.println(e);
    }
  }
}
