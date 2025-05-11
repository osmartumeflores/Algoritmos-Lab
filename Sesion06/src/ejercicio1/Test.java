package ejercicio1;
import actividad1.ExceptionIsEmpty;
import actividad1.Stack;
public class Test {
    public static void main(String[] args){
        Stack<String> pila=new StackLink<>();
        try{
            pila.push("A");
            pila.push("B");
            pila.push("C");
            pila.push("D");
            System.out.println("Pila con lista enlazada: " + pila);
            pila.pop();
            System.out.println("Despues de pop. " + pila);
        }catch(ExceptionIsEmpty e){
            System.out.println(e.getMessage());
        }
    }
}
