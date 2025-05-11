package ejercicio4;

public class Test {
    public static void main(String[] args) {
        String[] entradas = {
            "()()()[()]()",
            "((()))[]",
            "([])[](",
            "([{)]}",
            "[",
            "[][][]{{{}}}"
        };

        for (String entrada : entradas) {
            System.out.println(entrada + " -> " + Application.symbolBalancing(entrada));
        }
    }
}
