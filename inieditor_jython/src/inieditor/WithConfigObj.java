package inieditor;

import org.python.core.PyString;
import org.python.util.PythonInterpreter;

/**
 * 通过jython调用Python第三方的ConfigObj模块读写ini文件（比较完美，只是注释前后的空格会被去掉，不影响正常使用）
 * 
 * @author hezhigang
 * 
 */
public class WithConfigObj {

    public static void main(String[] args) {

        PythonInterpreter inter = new PythonInterpreter();

        // 增加python的classpath
        inter.exec("import sys");
        inter.exec("sys.path.append('.\\pythonlib')");
        inter.exec("import configobj");

        // 加载文件
        inter.exec("config = configobj.ConfigObj('zxphr.cfg')");

        // 读配置项
        String version = ((PyString) inter.eval("config['InstallCfg']['Version']")).asString();
        System.out.println(version);

        // 写配置项
        inter.exec("config['InstallCfg']['Version'] = 'V6.01.10.B1.OUTPUT'");

        // 保存文件
        inter.exec("config.filename = 'output.cfg'");
        inter.exec("config.write()");

    }

}
