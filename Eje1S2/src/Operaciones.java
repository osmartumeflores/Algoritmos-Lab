public class Operaciones implements Operable {
    @Override
    public <E extends Number> E suma(E a, E b) {
        return (E) (Number) (a.doubleValue() + b.doubleValue());
    }

    @Override
    public <E extends Number> E resta(E a, E b) {
        return (E) (Number) (a.doubleValue() - b.doubleValue());
    }

    @Override
    public <E extends Number> E producto(E a, E b) {
        return (E) (Number) (a.doubleValue() * b.doubleValue());
    }

    @Override
    public <E extends Number> E division(E a, E b) {
        if (b.doubleValue() == 0) throw new ArithmeticException("No se puede dividir por cero");
        return (E) (Number) (a.doubleValue() / b.doubleValue());
    }

    @Override
    public <E extends Number> E potencia(E base, E exponente) {
        return (E) (Number) Math.pow(base.doubleValue(), exponente.doubleValue());
    }

    @Override
    public <E extends Number> E raizCuadrada(E a) {
        if (a.doubleValue() < 0) throw new ArithmeticException("No se puede calcular la raíz cuadrada de un número negativo");
        return (E) (Number) Math.sqrt(a.doubleValue());
    }

    @Override
    public <E extends Number> E raizCubica(E a) {
        return (E) (Number) Math.cbrt(a.doubleValue());
    }
}
