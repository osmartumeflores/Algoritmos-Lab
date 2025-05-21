package actividad;
import actividad.LinkedBST;
import actividad.ExceptionIsEmpty;
import actividad.ItemDuplicated;
import actividad.ItemNoFound;
public class Test {
    public static void main(String[] args) {
        LinkedBST<Integer> bst = new LinkedBST<>();
        try {
            bst.insert(8);
            bst.insert(3);
            bst.insert(1);
            bst.insert(6);
            bst.insert(10);
            bst.insert(14);
            bst.insert(4);

            System.out.println("InOrden: " + bst.toString());
            System.out.println("PreOrden: " + bst.preOrder());
            System.out.println("PostOrden: " + bst.postOrder());
            System.out.println("Mínimo: " + bst.findMin());
            System.out.println("Máximo: " + bst.findMax());

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    
}

