public class Verificador {
    public static boolean seSobreponen(Rectangulo a, Rectangulo b) {
        return !(a.getXMax() <= b.getXMin() ||  // A está completamente a la izquierda de B
                 b.getXMax() <= a.getXMin() ||  // B está completamente a la izquierda de A
                 a.getYMax() <= b.getYMin() ||  // A está completamente debajo de B
                 b.getYMax() <= a.getYMin());   // B está completamente debajo de A
    } 

    public static boolean estanJuntos(Rectangulo a, Rectangulo b) {
        return (a.getXMax() == b.getXMin() ||  // B está justo a la derecha de A
                a.getXMin() == b.getXMax() ||  // A está justo a la derecha de B
                a.getYMax() == b.getYMin() ||  // B está justo arriba de A
                a.getYMin() == b.getYMax());   // A está justo arriba de B
    }
}
