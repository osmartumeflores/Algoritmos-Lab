import java.util.Scanner;

public class Calculadora {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Operaciones op = new Operaciones();
        
        while (true) {
            System.out.println("Menú de Operaciones:");
            System.out.println("1. Suma\n2. Resta\n3. Producto\n4. División\n5. Potencia\n6. Raíz Cuadrada\n7. Raíz Cúbica\n8. Salir");
            int opcion = scanner.nextInt();
            if (opcion == 8) break;

            Number a, b = 0;
            System.out.print("Ingrese el primer número: ");
            a = leerNumero(scanner);
            
            if (opcion >= 1 && opcion <= 5) {
                System.out.print("Ingrese el segundo número: ");
                b = leerNumero(scanner);
            }

            try {
                switch (opcion) {
                    case 1: System.out.println("Resultado: " + op.suma(a, b)); break;
                    case 2: System.out.println("Resultado: " + op.resta(a, b)); break;
                    case 3: System.out.println("Resultado: " + op.producto(a, b)); break;
                    case 4: System.out.println("Resultado: " + op.division(a, b)); break;
                    case 5: System.out.println("Resultado: " + op.potencia(a, b)); break;
                    case 6: System.out.println("Resultado: " + op.raizCuadrada(a)); break;
                    case 7: System.out.println("Resultado: " + op.raizCubica(a)); break;
                    default: System.out.println("Opción inválida");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
        scanner.close();
    }

    private static Number leerNumero(Scanner scanner) {
        String input = scanner.next();
        if (input.contains(".")) {
            return Double.parseDouble(input);
        } else {
            return Integer.parseInt(input);
        }
    }
}
