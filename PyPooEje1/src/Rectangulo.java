public class Rectangulo {
    private Coordenada esquina1, esquina2;
    
    public Rectangulo(Coordenada c1, Coordenada c2) {
        this.esquina1 = c1;
        this.esquina2 = c2;
    }
    
    public Coordenada getEsquina1() { return esquina1; }
    public Coordenada getEsquina2() { return esquina2; }
    
    // Métodos para obtener los límites correctos del rectángulo
    public double getXMin() { return Math.min(esquina1.getX(), esquina2.getX()); }
    public double getXMax() { return Math.max(esquina1.getX(), esquina2.getX()); }
    public double getYMin() { return Math.min(esquina1.getY(), esquina2.getY()); }
    public double getYMax() { return Math.max(esquina1.getY(), esquina2.getY()); }

    // Método para calcular el área del rectángulo
    public double calcularArea() {
        double base = getXMax() - getXMin();
        double altura = getYMax() - getYMin();
        return base * altura;
    }

    // Método para calcular la distancia euclidiana entre las esquinas
    public double calcularDistancia() {
        return Coordenada.distancia(esquina1, esquina2);
    }

    // Método para calcular la intersección entre dos rectángulos
    public static Rectangulo calcularInterseccion(Rectangulo a, Rectangulo b) {
        double x1 = Math.max(a.getXMin(), b.getXMin());
        double y1 = Math.max(a.getYMin(), b.getYMin());
        double x2 = Math.min(a.getXMax(), b.getXMax());
        double y2 = Math.min(a.getYMax(), b.getYMax());

        if (x1 < x2 && y1 < y2) {
            return new Rectangulo(new Coordenada(x1, y1), new Coordenada(x2, y2));
        }
        return null;
    }

    @Override
    public String toString() {
        return "Rectángulo: [" + esquina1 + ", " + esquina2 + "]";
    }
}
