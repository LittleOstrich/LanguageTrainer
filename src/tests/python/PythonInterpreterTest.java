package tests.python;

import org.python.core.PyInstance;
import org.python.util.PythonInterpreter;  


public class PythonInterpreterTest  
{  

   PythonInterpreter interpreter = null;  


   public PythonInterpreterTest()  
   {  
      PythonInterpreter.initialize(System.getProperties(),  
                                   System.getProperties(), new String[0]);  

      this.interpreter = new PythonInterpreter();  
   }  

   void execfile( final String fileName )  
   {  
      this.interpreter.execfile(fileName);  
   }  

   PyInstance createClass( final String className, final String opts )  
   {  
      return (PyInstance) this.interpreter.eval(className + "(" + opts + ")");  
   }  

   public static void main( String gargs[] )  
   {  
      PythonInterpreterTest ie = new PythonInterpreterTest();  

      ie.execfile(utils.Pathes.BASE + "\\src\\tests\\python\\py_testscript.py");  

      PyInstance hello = ie.createClass("Hello", "None");  

      hello.invoke("helloWorld");  
   }  
} 

