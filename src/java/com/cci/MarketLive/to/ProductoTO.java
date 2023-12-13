package com.cci.MarketLive.to;

public class ProductoTO {

    private int id;
    private String tipo;
    private String codigo;
    private String nombre;
    private String descripcion;
    private double precio;
    private double stock;
    private String tipoCobro;
    private int usuarioId;
    private int categoriaId;
    private String categoriaNombre;

    private int tiendaId;
    private String TiendaNombre;

    private String rutaImagen;


    public ProductoTO() {
    }

    public ProductoTO(int id, String tipo, String codigo, String nombre, String descripcion, double precio, double stock, String tipoCobro, int usuarioId, int categoriaId, String categoriaNombre, int tiendaId, String TiendaNombre, String rutaImagen) {

        this.id = id;
        this.tipo = tipo;
        this.codigo = codigo;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.stock = stock;
        this.tipoCobro = tipoCobro;
        this.usuarioId = usuarioId;
        this.categoriaId = categoriaId;
        this.categoriaNombre = categoriaNombre;
        this.tiendaId = tiendaId;
        this.TiendaNombre = TiendaNombre;
        this.rutaImagen = rutaImagen;

    }

    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public double getStock() {
        return stock;
    }

    public void setStock(double stock) {
        this.stock = stock;
    }

    public String getTipoCobro() {
        return tipoCobro;
    }

    public void setTipoCobro(String tipoCobro) {
        this.tipoCobro = tipoCobro;
    }

    public int getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(int usuarioId) {
        this.usuarioId = usuarioId;
    }

    public int getCategoriaId() {
        return categoriaId;
    }

    public void setCategoriaId(int categoriaId) {
        this.categoriaId = categoriaId;
    }

    public String getCategoriaNombre() {
        return categoriaNombre;
    }

    public void setCategoriaNombre(String categoriaNombre) {
        this.categoriaNombre = categoriaNombre;
    }


    public int getTiendaId() {
        return tiendaId;
    }

    public void setTiendaId(int tiendaId) {
        this.tiendaId = tiendaId;
    }

    public String getTiendaNombre() {
        return TiendaNombre;
    }

    public void setTiendaNombre(String TiendaNombre) {
        this.TiendaNombre = TiendaNombre;
    }
    
    public String getRutaImagen() {
        return rutaImagen;
    }

    public void setRutaImagen(String rutaImagen) {
        this.rutaImagen = rutaImagen;

    }

}
