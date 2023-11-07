
package DTO;


public class PessoaJuridicaDTO {
    
    private String nomeEmpresa; // Tabela pessoa_juridica
    private String nomeFantasia; //  Tabela pessoa_juridica
    private String endereco; // Tablea pessoa_juridica
    private String numero; // Tabela pessoa_juridica
    private String telefone; // Tabela pessoa_juridica
    private static String cnpj; // Tabela pessoa_juridica
    private String senha; // Variável para guardar senha cadastrar para logar depois
    private static int num_conta; 
    private static int num_agencia;
    private static String nomeFantasia_titular; // Tabela conta
    private static String cnpj_titular; // Tabela conta
    
    

    public String getNomeEmpresa() {
        return nomeEmpresa;
    }

    public void setNomeEmpresa(String nomeEmpresa) {
        this.nomeEmpresa = nomeEmpresa;
    }

    public String getNomeFantasia() {
        return nomeFantasia;
    }

    public void setNomeFantasia(String nomeFantasia) {
        this.nomeFantasia = nomeFantasia;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public int getNum_conta() {
        return num_conta;
    }

    public void setNum_conta(int num_conta) {
      this.num_conta = num_conta;
    }

    public int getNum_agencia() {
        return num_agencia;
    }

    public void setNum_agencia(int num_agencia) {
        this.num_agencia = num_agencia;
    }

    public String getNomeFantasia_titular() {
        return nomeFantasia_titular;
    }

    public void setNomeFantasia_titular(String nomeFantasia_titular) {
        this.nomeFantasia_titular = nomeFantasia_titular;
    }

    public  String getCnpj_titular() {
        return cnpj_titular;
    }

    public void setCnpj_titular(String cnpj_titular) {
        this.cnpj_titular = cnpj_titular;
    }

    
    
    
   
    
}
