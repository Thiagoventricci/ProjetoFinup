package DTO;

import java.time.LocalDate;


public class PessoaFisicaDTO {

    private String nome; // Tabela pessoa_fisica
    private String sobrenome; // Tabela pessoa_fisica
    private String endereco; // Tabela pessoa_fisica
    private String numero;  // Tabela pessoa_fisica
    private String telefone; // Tabela pessoa_fisica
    private String dataNascimento; // Tabela pessoa_fisica
    private static String cpf; // Tabela pessoa_fisica
    private static String cpf_titular; // Tabela conta
    private static String senha; // Variável para guardar a senha cadastrada para logar depois
    private static String conta; // Usada para receber numero da conta do Remetente e exibir no historico
    private static String agencia; // Usada para guardar da agencia do Remetente e exibir no historico
    private static String saldo; // Usada para guardar o saldo do rementente para fazer a transferencia, tanto pix, como ted também
    private static float valorPix; // Usada para guardar o valor que o Remetente quer enviar para o destinatário em pix
    private static float valorTed_Doc; // Usada para guardar o valor que o Remetente quer enviar para o destinatátario em ted
    private static String data_abertura; // Usada para guardar a data e salvar no Banco de Dados a data da abertura de conta
    private static String data_System;
    private static String tipo_transferenciaPix; //  Usada para guardar o dado do banco de dados e exibir no historico
    private static String tipo_transferenciaTed; //  Usada para guardar o dados do banco de dados e exibir no historico
    
    // Para as tabelas transferencias
    private float valorPixTabela; // Usada para receber o saldo do pix feito para mostrar no historico
    private String data_transferencia_pix; // Usada para imprimir na tabela transferência pix a data da transferência
    private float valorTed; // Usada para receber o valor que já foi feito pelo remetente e exibir no historico
    private static int contaTabelaD; // Usada para receber os dados da conta do destinatário e exibir no historico
    private static int agenciaTabelaD; // Usada para receber os dados da agência do destinatário e exibir no historico
    private String data_transferencia_ted; // // Usada para imprimir na tabela transferência ted a data da transferência
    private static String TitularR; // Usada para exibir no JOptionPane nome e sobrenome ou a razão social juridico do remetente após a transferência
    private static String TitularD; // Usada para exibir no JOptionPane nome e sobrenome ou a razão social juridico do destinatario após a transferência
    private String titularHistoricoR; // Usada para receber do banco de dados o nome e sobrenome do remetente e exibir no historico
    private String titularHistoricoD; // Usada para receber do banco de dados o nome sobrenome do destinatário e exibir no historico 
    private static String titular_TedR;
    private static String titular_TedD;
    private static String cpf_ted;
    
    
    
    public String getNome(){
        return nome;
    }
    
    public void setNome(String nome){
        this.nome = nome;
    }
    
    public String getSobrenome(){
        return sobrenome;
    }
    
    public void setSobrenome(String sobrenome){
        this.sobrenome = sobrenome;
    }
    
    public String getEndereco(){
        return endereco;
    }
    
    public void setEndereco(String endereco){
        this.endereco = endereco;
    }
    
    public String getNumero(){
        return numero;
    }
    
    public void setNumero(String numero){
        this.numero = numero;
    }
    
    public String getTelefone(){
        return telefone;
    }
    
    public void setTelefone(String telefone){
        this.telefone = telefone;
    }
    
    public String getDatanascimento(){
        return dataNascimento;
    }
    
    public void setDatanascimento(String dataNascimento){
        this.dataNascimento = dataNascimento;
    }
    
    public String getCpf(){
        return cpf;
    }
    
    public void setCpf(String cpf){
        this.cpf = cpf;
    }
    
    public String getSenha(){
        return senha;
    }
    
    public void setSenha(String senha){
        this.senha = senha;
    }
    
    public String getConta() {
        return conta;
    }

    public void setConta(String conta) {
        this.conta = conta;
    }

    public String getAgencia() {
       
        return agencia;
        
    }

    public void setAgencia(String agencia) {
        this.agencia = agencia;
    }

    public String getSaldo() {
        return saldo;
    }

    public void setSaldo(String saldo) {
        this.saldo = saldo;
    }

    public String getData_abertura() {
        return data_abertura;
    }

    public void setData_abertura(String data_abertura) {
        this.data_abertura = data_abertura;
    }

    public String getCpf_titular() {
        return cpf_titular;
    }
    
    public void setCpf_titular(String cpf_titular) {
        this.cpf_titular = cpf_titular;
    }
    
    public float getValorPix() {
        return valorPix;
    }

    public void setValorPix(float valorPix) {
        this.valorPix = valorPix;
    }
    
    public float getValorTed_Doc() {
        return valorTed_Doc;
    }

    public void setValorTed_Doc(float valorTed_Doc) {
        this.valorTed_Doc = valorTed_Doc;
    }

    public String getData_System() {
        return data_System;
    }

    public void setData_System(String data_System) {
        this.data_System = data_System;
    }

    public float getValorPixTabela() {
        return valorPixTabela;
    }

    public void setValorPixTabela(float valorPixTabela) {
        this.valorPixTabela = valorPixTabela;
    }

    public String getData_transferencia_pix() {
        return data_transferencia_pix;
    }

    public void setData_transferencia_pix(String data_transferencia_pix) {
        this.data_transferencia_pix = data_transferencia_pix;
    }

    public float getValorTed() {
        return valorTed;
    }

    public void setValorTed(float valorTed) {
        this.valorTed = valorTed;
    }

    public String getData_transferencia_ted() {
        return data_transferencia_ted;
    }

    public void setData_transferencia_ted(String data_transferencia_ted) {
        this.data_transferencia_ted = data_transferencia_ted;
    }

    public int getContaTabelaD() {
        return contaTabelaD;
    }

    public void setContaTabelaD(int contaTabelaD) {
        this.contaTabelaD = contaTabelaD;
    }
    
     public int getAgenciaTabelaD(){
         return agenciaTabelaD;
    }

    public void setAgenciaTabelaD(int agenciaTabelaD) {
        this.agenciaTabelaD = agenciaTabelaD;
    }
 
    public String getTipo_transferenciaPix() {
        return tipo_transferenciaPix;
    }

    public void setTipo_transferenciaPix(String tipo_transferenciaPix) {
        this.tipo_transferenciaPix = tipo_transferenciaPix;
    }

    public String getTipo_transferenciaTed() {
        return tipo_transferenciaTed;
    }

    public void setTipo_transferenciaTed(String tipo_transferenciaTed) {
        this.tipo_transferenciaTed = tipo_transferenciaTed;
    }

    public String getTitularR() {
        return TitularR;
    }

    public void setTitularR(String titularR) {
        this.TitularR = titularR;
    }

    public String getTitularHistoricoR() {
        return titularHistoricoR;
    }

    public void setTitularHistoricoR(String titularHistoricoR) {
        this.titularHistoricoR = titularHistoricoR;
    }

    public String getTitularHistoricoD() {
        return titularHistoricoD;
    }

    public void setTitularHistoricoD(String titularHistoricoD) {
        this.titularHistoricoD = titularHistoricoD;
    }

    public String getTitular_TedR() {
        return titular_TedR;
    }

    public void setTitular_TedR(String titular_TedR) {
        this.titular_TedR = titular_TedR;
    }

    public String getTitular_TedD() {
        return titular_TedD;
    }

    public void setTitular_TedD(String titular_TedD) {
        this.titular_TedD = titular_TedD;
    }

    public String getTitularD() {
        return TitularD;
    }

    public void setTitularD(String titularD) {
        this.TitularD = titularD;
    }

    public String getCpf_ted() {
        return cpf_ted;
    }

    public void setCpf_ted(String cpf_ted) {
        this.cpf_ted = cpf_ted;
    }

   

   
   
}

   
