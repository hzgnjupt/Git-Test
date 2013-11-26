package inieditor;

import javax.script.ScriptException;

import org.python.core.PyObject;
import org.python.util.PythonInterpreter;

public class Test {

    public static void main(String[] args) throws ScriptException {

        PythonInterpreter inter = new PythonInterpreter();
        inter.exec("import ConfigParser");
        PyObject obj = inter.eval("ConfigParser.ConfigParser()");
        System.out.println(obj.__dir__());

    }

}
