import java.util.*;
import java.util.ArrayList;
import java.util.List;
public class Bolsa<T> implements Iterable<T> {
    private ArrayList<T> lista=new ArrayList<>();
    private int tope;

    public Bolsa(int tope){
        this.tope=tope;
    }
    public void add(T objeto){
        if(lista.size()<tope){
            lista.add(objeto);
        }else{
            throw new RuntimeException("No caben mas objetos en la bolsa");
        }
    }
    public Iterator<T> iterator(){
        return lista.iterator();
    }
}
