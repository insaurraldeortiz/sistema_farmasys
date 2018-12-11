package modelos;

public class Rubros {
    int id_rubro;
    String nombre_rubro;

    public Rubros() {
    }

    public Rubros(int id_rubro, String nombre_rubro) {
        this.id_rubro = id_rubro;
        this.nombre_rubro = nombre_rubro;
    }

    public int getId_rubro() {
        return id_rubro;
    }

    public void setId_rubro(int id_rubro) {
        this.id_rubro = id_rubro;
    }

    public String getNombre_rubro() {
        return nombre_rubro;
    }

    public void setNombre_rubro(String nombre_rubro) {
        this.nombre_rubro = nombre_rubro;
    }
    
    
}
