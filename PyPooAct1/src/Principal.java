import java.util.Scanner;

public class Principal {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Ingrese las coordenadas del primer rectángulo:");
        Coordenada c1 = new Coordenada(scanner.nextDouble(), scanner.nextDouble());
        Coordenada c2 = new Coordenada(scanner.nextDouble(), scanner.nextDouble());
        Rectangulo rect1 = new Rectangulo(c1, c2);
        
        System.out.println("Ingrese las coordenadas del segundo rectángulo:");
        Coordenada c3 = new Coordenada(scanner.nextDouble(), scanner.nextDouble());
        Coordenada c4 = new Coordenada(scanner.nextDouble(), scanner.nextDouble());
        Rectangulo rect2 = new Rectangulo(c3, c4);
        
        System.out.println(rect1);
        System.out.println(rect2);
        
        if (Verificador.seSobreponen(rect1, rect2)) {
            System.out.println("Los rectángulos se sobreponen.");
            Rectangulo interseccion = Rectangulo.calcularInterseccion(rect1, rect2);
            if (interseccion != null) {
                System.out.println("Área de la intersección: " + interseccion.calcularArea());
            }
        } else if (Verificador.estanJuntos(rect1, rect2)) {
            System.out.println("Los rectángulos están juntos.");
        } else {
            System.out.println("Los rectángulos son disjuntos.");
        }
        
        scanner.close();
    }
}
