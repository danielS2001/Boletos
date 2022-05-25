package com.example.boletos;

public class Boleto {
    //Atributos
    private int numeroDeBoleto;
    private String nombreDeCliente;
    private String destino;
    private int tipoDeViaje;
    private String fecha;
    private double precio;

    //Constructores
    public Boleto() { //Constructor vacío.
        this.numeroDeBoleto = 0;
        this.nombreDeCliente = "";
        this.destino = "";
        this.tipoDeViaje = 0;
        this.fecha = "";
        this.precio = 0;
    }

    public Boleto(int numeroDeBoleto, String nombreDeCliente, String destino,  //Constructor de parametros
                  int tipoDeViaje, String fecha, double precio) {
        this.numeroDeBoleto = numeroDeBoleto;
        this.nombreDeCliente = nombreDeCliente;
        this.destino = destino;
        this.tipoDeViaje = tipoDeViaje;
        this.fecha = fecha;
        this.precio = precio;
    }

    public Boleto (Boleto tickets) { //Constructor de copia
        this.numeroDeBoleto = tickets.numeroDeBoleto;
        this.nombreDeCliente = tickets.nombreDeCliente;
        this.destino = tickets.destino;
        this.tipoDeViaje = tickets.tipoDeViaje;
        this.fecha = tickets.fecha;
        this.precio = tickets.precio;
    }

    /**
     * @return the numeroDeBoleto
     */
    public int getNumeroDeBoleto() {
        return numeroDeBoleto;
    }

    /**
     * @param numeroDeBoleto the numeroDeBoleto to set
     */
    public void setNumeroDeBoleto(int numeroDeBoleto) {
        this.numeroDeBoleto = numeroDeBoleto;
    }

    /**
     * @return the nombreDeCliente
     */
    public String getNombreDeCliente() {
        return nombreDeCliente;
    }

    /**
     * @param nombreDeCliente the nombreDeCliente to set
     */
    public void setNombreDeCliente(String nombreDeCliente) {
        this.nombreDeCliente = nombreDeCliente;
    }

    /**
     * @return the destino
     */
    public String getDestino() {
        return destino;
    }

    /**
     * @param destino the destino to set
     */
    public void setDestino(String destino) {
        this.destino = destino;
    }

    /**
     * @return the tipoDeViaje
     */
    public int getTipoDeViaje() {
        return tipoDeViaje;
    }

    /**
     * @param tipoDeViaje the tipoDeViaje to set
     */
    public void setTipoDeViaje(int tipoDeViaje) {
        this.tipoDeViaje = tipoDeViaje;
    }

    /**
     * @return the fecha
     */
    public String getFecha() {
        return fecha;
    }

    /**
     * @param fecha the fecha to set
     */
    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    /**
     * @return the precio
     */
    public double getPrecio() {
        return precio;
    }

    /**
     * @param precio the precio to set
     */
    public void setPrecio(double precio) {
        this.precio = precio;
    }


    public double calcularSubTotal() {
        double subTotal = 0;
        if(this.tipoDeViaje==1) subTotal = this.precio;
        if(this.tipoDeViaje==2) subTotal = this.precio*1.8;
        return subTotal;
    }

    public double sacarDescuento(int edad) {
        double descuento = 0;
        if (edad >= 60) {
            descuento = (calcularSubTotal()) /2;
        }
        return descuento;
    }

    public double sacarImpuesto() {
        double subTotal = calcularSubTotal();
        double impuesto;
        impuesto = subTotal *.16;
        subTotal = subTotal + impuesto;
        return impuesto;
    }

    public double sacarTotal(double descuento) {
        double total;
        total = calcularSubTotal() - descuento;
        return total;
    }
}
