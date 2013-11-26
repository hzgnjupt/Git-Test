package inieditor;

import org.python.core.PyObject;
import org.python.core.PyString;
import org.python.util.PythonInterpreter;

/**
 * 通过jython调用Python的ConfigParser读写ini文件（缺陷：写文件的时候配置项顺序会乱）
 * 
 * @author hezhigang
 * 
 */
public class Test {

    public static void main(String[] args) {

        PythonInterpreter inter = new PythonInterpreter();

        // 加载文件
        inter.exec("import ConfigParser");
        PyObject configParser = inter.eval("ConfigParser.ConfigParser()");
        configParser.invoke("read", new PyString("zxphr.cfg"));

        // 读配置项
        String version = ((PyString) configParser.invoke("get", new PyObject[] { new PyString("InstallCfg"), new PyString("Version") })).asString();
        System.out.println(version);

        // 写配置项
        configParser.invoke("set", new PyObject[] { new PyString("InstallCfg"), new PyString("Version"), new PyString("V6.01.10.B1.OUTPUT") });

        // 保存文件
        PyObject outputFile = inter.eval("open('output.cfg', 'w')");
        configParser.invoke("write", outputFile);
        outputFile.invoke("close");

    }

}
