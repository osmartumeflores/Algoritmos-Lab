public class Tarea {
    private String titulo;
    private int prioridad;

    public Tarea(String descripcion, int prioridad) {
        this.titulo = descripcion;
        this.prioridad = prioridad;
    }

    public String getTitulo() {
        return titulo;
    }

    public int getPrioridad() {
        return prioridad;
    }

    @Override
    public String toString() {
        return "Tarea: " + titulo + " (Prioridad: " + prioridad + ")";
    }
}
