package com.cci.MarketLive.controller;

import com.cci.MarketLive.service.CategoriaService;
import com.cci.MarketLive.to.ProductoTO;
import com.cci.MarketLive.service.ProductoService;
import com.cci.MarketLive.to.CategoriaTO;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.regex.Pattern;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import org.primefaces.PrimeFaces;
import org.primefaces.model.file.UploadedFile;

@ManagedBean(name = "productoController")
@SessionScoped
public class ProductoController implements Serializable {

    private String RUTA_IMAGEN = "C:\\Users\\chris\\Documents\\Universidad\\Proyecto 2\\Marketlive\\web\\";

    private List<ProductoTO> productos;
    private ProductoTO selectedProducto;
    private List<ProductoTO> selectedProductos;
    private GeneralHelper generalHelper;
    private List<ProductoTO> busqueda;
    private List<ProductoTO> listaProductoCategoria;

    private ProductoService servicioProducto;
    private CategoriaService servicioCategoria;

    private String producto;
    private String categoria;

    private CategoriaTO categoriaTO;

    private int idCategoria;

    private UploadedFile imagenProducto;

    public ProductoController() {

        productos = new ArrayList<>();
        selectedProductos = new ArrayList<>();
        generalHelper = new GeneralHelper();
        busqueda = new ArrayList<ProductoTO>();
        listaProductoCategoria = new ArrayList<ProductoTO>();

        try {
            servicioProducto = new ProductoService();
            setProductos(servicioProducto.readAll());
            setBusqueda(servicioProducto.listarBusqueda(producto));

            setListaProductoCategoria(servicioProducto.readAllByCategoria(idCategoria));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void buscar(String producto) {
        try {
            setBusqueda(servicioProducto.listarBusqueda(producto));

            for (ProductoTO pro : getBusqueda()) {
                System.out.println("sa" + pro.getNombre());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void readProductoByCategoria(int idCategoria2) {

        try {

            idCategoria = idCategoria2;
            setListaProductoCategoria(servicioProducto.readAllByCategoria(idCategoria2));

            for (ProductoTO pro : getListaProductoCategoria()) {
                System.out.println("sa" + pro.getNombre());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void openNew() {
        this.selectedProducto = new ProductoTO();
    }

    public void updateProductoCantidad(ProductoTO producto, double cantidad) throws SQLException {

        servicioProducto.updateCantidad(producto, cantidad);
        setProductos(servicioProducto.readAll());
        generalHelper.redireccionar("/faces/productos_admin.xhtml");
    }

    public void saveProducto() {
        try {
            if (this.selectedProducto.getId() == 0) {

                if (imagenProducto != null) {
                    uploadImage();
                }

                servicioProducto.create(this.selectedProducto);

                setProductos(servicioProducto.readAll());

                selectedProducto.setId(0);

                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Producto agregado"));
            } else {

                // Obtiene la ruta de la imagen antigua
                String rutaImagenAntigua = this.selectedProducto.getRutaImagen();

                // Elimina la imagen antigua si existe
                if (imagenProducto != null && rutaImagenAntigua != null && !rutaImagenAntigua.isEmpty()) {
                    eliminarImagenAntigua(rutaImagenAntigua);
                    uploadImage();
                }

                // Realiza la actualización del producto
                servicioProducto.update(this.selectedProducto);

                generalHelper.redireccionar("/faces/productos_admin.xhtml");
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Producto actualizado"));
            }

            PrimeFaces.current().executeScript("PF('manageProductDialog').hide()");
            PrimeFaces.current().ajax().update("form:messages", "form:dt-products");
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("error producto"));
            e.printStackTrace();
        } finally {
            setSelectedProducto(null);
        }
    }

    public void deleteProducto() {
        try {
            String rutaImagenAntigua = this.selectedProducto.getRutaImagen();
            eliminarImagenAntigua(rutaImagenAntigua);

            boolean delete = servicioProducto.delete(this.selectedProducto.getId());

            if (delete) {
                this.productos.remove(this.selectedProducto);
                this.selectedProductos.remove(this.selectedProducto);
                this.selectedProducto = null;
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Producto Eliminado"));
                PrimeFaces.current().executeScript("PF('manageProductDialog').hide()");
                PrimeFaces.current().ajax().update("form:messages", "form:dt-products");
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Error al eliminar el producto"));
                PrimeFaces.current().executeScript("PF('manageProductDialog').hide()");
                PrimeFaces.current().ajax().update("form:messages", "form:dt-products");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void uploadImage() {

        try {
            String fileName = imagenProducto.getFileName();
            InputStream file = imagenProducto.getInputStream();

            if (fileName != null) {
                String destinationFile = RUTA_IMAGEN + "resources\\images\\productos\\";

                // write the inputStream to a FileOutputStream
                String[] partesArchivo = fileName.split(Pattern.quote("."));
                String extensionArchivo = partesArchivo[1];

                // Generar un nombre único usando UUID
                String nombreUnico = UUID.randomUUID().toString();

                //File tmp = new File(destinationFile + fileName);
                File tmp = new File(destinationFile + nombreUnico + "." + extensionArchivo);
                tmp.getParentFile().mkdirs();
                OutputStream out = new FileOutputStream(tmp);

                int read = 0;
                byte[] bytes = new byte[1024];

                while ((read = file.read(bytes)) != -1) {
                    out.write(bytes, 0, read);
                }

                file.close();
                out.flush();
                out.close();

                selectedProducto.setRutaImagen("/resources/images/productos/" + nombreUnico + "." + extensionArchivo);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

// Nuevo método para eliminar la imagen antigua
    private void eliminarImagenAntigua(String rutaImagenAntigua) {
        try {
            // Construye la ruta completa del archivo
            String rutaCompleta = RUTA_IMAGEN + rutaImagenAntigua;

            // Verifica si la ruta es válida antes de intentar eliminar la imagen
            File imagenAntigua = new File(rutaCompleta);

            if (imagenAntigua.exists()) {
                if (imagenAntigua.delete()) {
                    System.out.println("Imagen antigua eliminada con éxito");
                } else {
                    System.out.println("No se pudo eliminar la imagen antigua");
                }
            } else {
                System.out.println("La imagen antigua no existe en la ruta proporcionada");
            }
        } catch (Exception e) {
            System.out.println("Error al intentar eliminar la imagen antigua: " + e.getMessage());
        }
    }

    public List<ProductoTO> getProductos() {
        return productos;
    }

    public void setProductos(List<ProductoTO> productos) {
        this.productos = productos;
    }

    public ProductoTO getSelectedProducto() {
        return selectedProducto;
    }

    public void setSelectedProducto(ProductoTO selectedProducto) {
        this.selectedProducto = selectedProducto;
    }

    public List<ProductoTO> getSelectedProductos() {
        return selectedProductos;
    }

    public void setSelectedProductos(List<ProductoTO> selectedProductos) {
        this.selectedProductos = selectedProductos;
    }

    public List<ProductoTO> getBusqueda() {
        return busqueda;
    }

    public void setBusqueda(List<ProductoTO> busqueda) {
        this.busqueda = busqueda;
    }

    public List<ProductoTO> getListaProductoCategoria() {
        return listaProductoCategoria;
    }

    public void setListaProductoCategoria(List<ProductoTO> listaProductoCategoria) {
        this.listaProductoCategoria = listaProductoCategoria;
    }

    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public UploadedFile getImagenProducto() {
        return imagenProducto;
    }

    public void setImagenProducto(UploadedFile imagenProducto) {
        this.imagenProducto = imagenProducto;
    }

}
