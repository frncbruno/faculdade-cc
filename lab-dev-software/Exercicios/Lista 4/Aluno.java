
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projeto;

/**
 *
 * @author laboratorio
 */
public class Aluno {
    private String nome;
    private String dataNascimento;
    private String sexo;
    private int matricula;
    private String curso;
    private String cpf;
    private String rua;
    private String numero;
    private String bairro;
    private String cidade;
    private String cep;
    private String estado;
    private String telefone;

    public Aluno(String nome, String dataNascimento, String sexo,
                 int matricula, String curso, String cpf,
                 String rua, String numero, String bairro,
                 String cidade, String cep, String estado,
                 String telefone) {

        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.sexo = sexo;
        this.matricula = matricula;
        this.curso = curso;
        this.cpf = cpf;
        this.rua = rua;
        this.numero = numero;
        this.bairro = bairro;
        this.cidade = cidade;
        this.cep = cep;
        this.estado = estado;
        this.telefone = telefone;
    }

    public int getMatricula() {
        return matricula;
    }

    @Override
    public String toString() {
        return nome + ";" +
               dataNascimento + ";" +
               sexo + ";" +
               matricula + ";" +
               curso + ";" +
               cpf + ";" +
               rua + ";" +
               numero + ";" +
               bairro + ";" +
               cidade + ";" +
               cep + ";" +
               estado + ";" +
               telefone;
    }
}

