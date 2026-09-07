/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package cadastroespecies;

/**
 *
 * @author bruno
 */
public class CadastroEspecies {
    private String nomeCientifico;
    private String apelido;
    private float peso;
    private float altura;
    private String grupo;
    private String sexo;

    public CadastroEspecies(String nomeCientifico, String apelido, float peso, float altura, String grupo, String sexo) {
        this.nomeCientifico = nomeCientifico;
        this.apelido = apelido;
        this.peso = peso;
        this.altura = altura;
        this.grupo = grupo;
        this.sexo = sexo;
    }

    public String getNomeCientifico() {
        return nomeCientifico;
    }

    public void setNomeCientifico(String nomeCientifico) {
        this.nomeCientifico = nomeCientifico;
    }

    public String getApelido() {
        return apelido;
    }

    public void setApelido(String apelido) {
        this.apelido = apelido;
    }

    public float getPeso() {
        return peso;
    }

    public void setPeso(float peso) {
        this.peso = peso;
    }

    public float getAltura() {
        return altura;
    }

    public void setAltura(float altura) {
        this.altura = altura;
    }

    public String getGrupo() {
        return grupo;
    }

    public void setGrupo(String grupo) {
        this.grupo = grupo;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    @Override
    public String toString() {
        return nomeCientifico + ";" +
               apelido + ";" +
               peso + ";" +
               altura + ";" +
               grupo + ";" +
               sexo;
    }
}
