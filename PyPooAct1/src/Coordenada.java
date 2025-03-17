public class Coordenada {
    private double x, y;
    public Coordenada() {
        this.x = 0;
        this.y = 0;
    }
    public Coordenada(double x, double y) {
        this.x = x;
        this.y = y;
    }
    public Coordenada(Coordenada c) {
        this.x = c.x;
        this.y = c.y;
    }
    public void setX(double x) { this.x = x; }
    public void setY(double y) { this.y = y; }
    public double getX() { return x; }
    public double getY() { return y; }
    public double distancia(Coordenada c) {
        return Math.sqrt(Math.pow(this.x - c.x, 2) + Math.pow(this.y - c.y, 2));
    }
    public static double distancia(Coordenada c1, Coordenada c2) {
        return c1.distancia(c2);
    }
    public String toString() {
        return "(" + x + ", " + y + ")";
    }
}
