
public class Comprador {
    private String nombre;
    private String email;
    private int cantidadBoletosDeseados;
    private double presupuestoMaximo;

    public Comprador(String nombre, String email, int cantidadBoletosDeseados, double presupuestoMaximo) {
        this.nombre = nombre;
        this.email = email;
        this.cantidadBoletosDeseados = cantidadBoletosDeseados;
        this.presupuestoMaximo = presupuestoMaximo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getCantidadBoletosDeseados() {
        return cantidadBoletosDeseados;
    }

    public void setCantidadBoletosDeseados(int cantidadBoletosDeseados) {
        this.cantidadBoletosDeseados = cantidadBoletosDeseados;
    }

    public double getPresupuestoMaximo() {
        return presupuestoMaximo;
    }

    public void setPresupuestoMaximo(double presupuestoMaximo) {
        this.presupuestoMaximo = presupuestoMaximo;
    }

    public void restarPresupuesto(double cantidad) {
        presupuestoMaximo -= cantidad;
    }

    public void restarBoletos(int cantidad) {
        cantidadBoletosDeseados -= cantidad;
    }
}
