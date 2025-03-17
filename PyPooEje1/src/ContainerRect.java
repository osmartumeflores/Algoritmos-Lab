public class ContainerRect {
    private Rectangulo[] rectangulos;
    private double[] distancias;
    private double[] areas;
    private int n;
    private static int numRec = 0;

    public ContainerRect(int n) {
        this.n = n;
        this.rectangulos = new Rectangulo[n];
        this.distancias = new double[n];
        this.areas = new double[n];
    }

    public void addRectangulo(Rectangulo r) {
        if (numRec < n) {
            rectangulos[numRec] = r;
            distancias[numRec] = r.calcularDistancia();
            areas[numRec] = r.calcularArea();
            numRec++;
        } else {
            System.out.println("No es posible agregar más rectángulos.");
        }
    }

    // Método para obtener un rectángulo por su índice
    public Rectangulo getRectangulo(int index) {
        if (index >= 0 && index < numRec) {
            return rectangulos[index];
        }
        return null;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Rectangulo\tCoordenadas\tDistancia\tArea\n");
        for (int i = 0; i < numRec; i++) {
            sb.append((i + 1) + "\t" + rectangulos[i] + "\t" + distancias[i] + "\t" + areas[i] + "\n");
        }
        return sb.toString();
    }
}
