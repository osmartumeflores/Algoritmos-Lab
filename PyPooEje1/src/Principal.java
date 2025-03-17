import java.util.Scanner;

public class Principal {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Ingrese la cantidad máxima de rectángulos a almacenar: ");
        int maxRect = scanner.nextInt();
        ContainerRect contenedor = new ContainerRect(maxRect);

        for (int i = 0; i < maxRect; i++) {
            System.out.println("Ingrese las coordenadas del rectángulo " + (i + 1) + ":");
            Coordenada c1 = new Coordenada(scanner.nextDouble(), scanner.nextDouble());
            Coordenada c2 = new Coordenada(scanner.nextDouble(), scanner.nextDouble());
            Rectangulo rect = new Rectangulo(c1, c2);
            contenedor.addRectangulo(rect);
        }

        System.out.println("\nLista de rectángulos almacenados:");
        System.out.println(contenedor);

        // Verificar si hay al menos 2 rectángulos para comparar
        if (maxRect > 1) {
            Rectangulo rect1 = contenedor.getRectangulo(0);
            Rectangulo rect2 = contenedor.getRectangulo(1);

            if (rect1 != null && rect2 != null) {
                if (Verificador.seSobreponen(rect1, rect2)) {
                    System.out.println("Los primeros dos rectángulos se sobreponen.");
                    Rectangulo interseccion = Rectangulo.calcularInterseccion(rect1, rect2);
                    if (interseccion != null) {
                        System.out.println("Área de intersección: " + interseccion.calcularArea());
                    }
                } else if (Verificador.estanJuntos(rect1, rect2)) {
                    System.out.println("Los primeros dos rectángulos están juntos.");
                } else {
                    System.out.println("Los primeros dos rectángulos son disjuntos.");
                }
            }
        }

        scanner.close();
    }
}

