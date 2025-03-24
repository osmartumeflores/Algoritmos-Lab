public class MetodoGen {
    static<T extends Comparable<T>> boolean igualArrays(T[] x, T[] y){
        if (x.length != y.length) return false;

        for (int i=0;i<x.length;i++){
            if(!x[i].equals(y[i])) return false;
        }
        return true;
    }
    public static void main(String[] args){
        Integer num[]={1,2,3,5};
        Integer num2[]={1,2,3,4,5};
        Integer num3[]={1,2,7,4,5};
        Integer num4[]={1,2,7,4,5,6};

        if (igualArrays(num, num))
        System.out.println("nums es igual a nums");
    else
        System.out.println("num NO es igual a num");

    if (igualArrays(num, num2))
        System.out.println("num es igual a num2");
    else
        System.out.println("num NO es igual a num2");

    if (igualArrays(num, num3))
        System.out.println("num es igual a num3");
    else
        System.out.println("num NO es igual a num3");

    if (igualArrays(num, num4))
        System.out.println("num es igual a num4");
    else
        System.out.println("num NO es igual a num4");

    //Double dvals[]={1.1,2.2,3.3,4.4,5.5};
    //if(igualArrays(num, dvals))
      //System.err.println("nums es igual al dvals");                
    }
}
