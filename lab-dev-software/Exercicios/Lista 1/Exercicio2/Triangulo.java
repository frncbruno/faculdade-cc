package com.mycompany.projetoconta;

/**
 *
 * @author laboratorio
 */
public class Triangulo extends FormaGeometrica {
    private float base;
    private float altura;

    public float getBase() {
        return base;
    }

    public void setBase(float base) {
        this.base = base;
    }

    public float getAltura() {
        return altura;
    }

    public void setAltura(float altura) {
        this.altura = altura;
    }
    
    @Override
    public float calcularArea(){
        area = (base * altura)/2;
        return area;
    }
}
