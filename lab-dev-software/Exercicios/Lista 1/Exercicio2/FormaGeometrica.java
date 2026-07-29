public class FormaGeometrica {
    private float lado;
    protected float area;

    public float getLado() {
        return lado;
    }

    public void setLado(float lado) {
        this.lado = lado;
    }

    public float getArea() {
        return area;
    }

    public void setArea(float area) {
        this.area = area;
    }
    
    public float calcularArea(){
        area = lado * lado;
        return area;
    }
}
