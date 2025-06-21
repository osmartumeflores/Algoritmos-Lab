//EJERCICIO 4
package btree;

public class RegistroEstudiante implements Comparable<RegistroEstudiante> {
    private int codigo;
    private String nombre;
    
    public RegistroEstudiante(int codigo, String nombre) {
        this.codigo = codigo;
        this.nombre = nombre;
    }
    
    public int getCodigo() { 
        return codigo; 
    }
    
    public String getNombre() { 
        return nombre; 
    }
    
    @Override
    public int compareTo(RegistroEstudiante otro) {
        return Integer.compare(this.codigo, otro.codigo);
    }
    
    @Override
    public String toString() {
        return codigo + " - " + nombre;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        RegistroEstudiante that = (RegistroEstudiante) obj;
        return codigo == that.codigo;
    }
    
    @Override
    public int hashCode() {
        return Integer.hashCode(codigo);
    }
}