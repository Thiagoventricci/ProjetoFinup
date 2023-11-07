
package DAO;

import DTO.PessoaFisicaDTO;
import DTO.PessoaJuridicaDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JOptionPane;



public class PessoaFisicaDAO {

    Connection conn;
    PreparedStatement pstm, pstmD; 
    ResultSet rs;
    ResultSet rsD;
    ArrayList<PessoaFisicaDTO> lista = new ArrayList<>();
        
    
      public int[] GeraNum(){
        // Gerar número aleatório de conta e agência
        Random random = new Random();
        // Gera um número de 6 dígitos entre 1000 e 9999
        var conta = 100000 + random.nextInt(900000); 
         // Gera um número de 4 dígitos entre 100 e 999
        var agencia = 1000 + random.nextInt(9000);
        
        int[] numeros = {conta, agencia}; // Armazena os números em um array e retorna
        return numeros;
   }
       
        
       
       
       
      

        // Método para cadastrar uma pessoa fisica
        public void cadastrarFisicaConta(PessoaFisicaDTO cadastrardto){
        conn = new ConexaoDAO().Conexao();
        String sql = 
                "insert into pessoa_fisica (Nome, Sobrenome, Endereco, "
                + "Numero, Telefone, Data_Nascimento, Cpf, Senha, Numero_conta, Agencia) "
                + "values (?,?,?,?,?,?,?,?,?,?)";
        String sqlConta = "Insert into conta (Titular, Certi_P, Numero_conta, Agencia, Saldo)"
                + "values (?,?,?,?, 0.0)";
        
         GeraNum();
        
        int[] numeros = GeraNum(); // Chama o método GeraNum() para obter os números de conta e agência
        
        int contaGerada = numeros[0]; // Obtém o número de conta gerado
        int agenciaGerada = numeros[1]; // Obtém o número de agência gerado
       
        try {
            
            pstm = conn.prepareStatement(sql);
            pstm.setString(1, cadastrardto.getNome());
            pstm.setString(2, cadastrardto.getSobrenome());
            pstm.setString(3, cadastrardto.getEndereco());
            pstm.setString(4, cadastrardto.getNumero());
            pstm.setString(5, cadastrardto.getTelefone());
            pstm.setString(6, cadastrardto.getDatanascimento());
            pstm.setString(7, cadastrardto.getCpf());
            pstm.setString(8, cadastrardto.getSenha());           
            pstm.setInt(9, contaGerada);
            pstm.setInt(10, agenciaGerada);
            
            
            pstm.execute();
            pstm.close();
            
            PreparedStatement pstmConta = conn.prepareStatement(sqlConta);
            pstmConta.setString(1, cadastrardto.getNome() + " " + cadastrardto.getSobrenome());
            pstmConta.setString(2, cadastrardto.getCpf_titular());
            pstmConta.setInt(3, contaGerada);
            pstmConta.setInt(4, agenciaGerada);
            
            pstmConta.execute();
            pstmConta.close();
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "CadastrarFisicaDAO: " + e);
        }
        
        
    }
    

    
        // Método para autenticar um usuario com base no CPF e SENHA
    public ResultSet autenticacaoUsuario(PessoaFisicaDTO cadastradto){
        conn = new ConexaoDAO().Conexao();
        
        try{
            String sql = "select * from pessoa_fisica where Cpf = ? and Senha = ?";
            // O PreparedStatement prepara a conexão.
            pstm = conn.prepareStatement(sql);
            pstm.setString(1, cadastradto.getCpf());
            pstm.setString(2,cadastradto.getSenha());
            ResultSet rs = pstm.executeQuery();
            return rs;
            
        }catch (SQLException e){
            JOptionPane.showMessageDialog(null, "ClienteFisicaDAO: " + e);
            return null;
        }
}
        // Método para buscar informações de uma pessoa fisica por cpf
    public PessoaFisicaDTO BuscarPorCpf(){
        conn = new ConexaoDAO().Conexao();
        PessoaFisicaDTO cadastrarFisica =  new PessoaFisicaDTO();
        String sql = "Select * from pessoa_fisica where Cpf = " + cadastrarFisica.getCpf();
        
        try {
            pstm =  conn.prepareStatement(sql);
            rs = pstm.executeQuery();
            
            while(rs.next()){
                cadastrarFisica.setNome(rs.getString("Nome"));
                cadastrarFisica.setSobrenome(rs.getString("Sobrenome"));
                cadastrarFisica.setCpf(rs.getString("Cpf"));
                cadastrarFisica.setConta(rs.getString("Numero_conta"));
                cadastrarFisica.setAgencia(rs.getString("Agencia"));
               
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "BuscarPorCpf " + e);
        }
        return cadastrarFisica;
        
        
    }
       // Método para verificar se um CPF existe na tabela
        public boolean verficarCpf(PessoaFisicaDTO cadastradto){
        conn = new ConexaoDAO().Conexao();
        String sql = "Select Cpf_titular from conta WHERE Cpf_titular = ?";
        try {
            pstm = conn.prepareStatement(sql);
            pstm.setString(1, cadastradto.getCpf_titular());
            return pstm.executeQuery().next();
            
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "verificarCpf " + e);
        }
        return false;
    }

    // Método para verificar o saldo da tabela pra fazer um novo depósito
    public String verificarSaldoParaDeposito(PessoaFisicaDTO cadastradto){
    conn = new ConexaoDAO().Conexao();
    String sql = "Select Saldo FROM conta WHERE Cpf_titular = ?";
     
        try {
            pstm = conn.prepareStatement(sql);
            pstm.setString(1, cadastradto.getCpf_titular());
            String saldo = null; // variavel para armazenar o saldo
           rs = pstm.executeQuery();
           
           if (rs.next()) {
            saldo = rs.getString("saldo");
        }

        rs.close();
        pstm.close();

           
        return saldo;    
        
        } catch (Exception e) {
            
           JOptionPane.showMessageDialog(null, "verificarCpf " + e);
        }
        return null;
    
    }
    
    
    
    public void realizarPixCpf(){
    
    PessoaFisicaDTO objpdto = new PessoaFisicaDTO();
    String sqlAtualizacao = "UPDATE conta SET Saldo = CASE WHEN Certi_P = ? THEN Saldo - ? WHEN Certi_P = ? THEN Saldo + ? ELSE Saldo END WHERE Certi_P IN (?,?);";
    
    String consultaSaldo = "Select Saldo From conta Where Certi_P = ?";
    buscarCpfParaComprovantesPix();
    
    conn = new ConexaoDAO().Conexao();
    
        try {
            pstm = conn.prepareStatement(consultaSaldo);
            pstm.setString(1, objpdto.getCpf());
            pstm.execute();
            
            ResultSet resultadoConsulta = pstm.executeQuery();
            float saldo = 0.0f;
            if(resultadoConsulta.next()){
                saldo = resultadoConsulta.getFloat("Saldo");
            }else{
                JOptionPane.showMessageDialog(null, "Erro ao consultar o saldo");
            }
            resultadoConsulta.close();
            
            float valorPix = objpdto.getValorPix();
            
            // Verifica se o saldo é menor ou igual ao investimento
            if(saldo >= valorPix){
                
                
                pstm = conn.prepareStatement(sqlAtualizacao);
                pstm.setString(1, objpdto.getCpf());
                pstm.setFloat(2, valorPix);
                
                pstm.setString(3, objpdto.getCpf_titular());
                pstm.setFloat(4, valorPix);
                
                pstm.setString(5, objpdto.getCpf());
                pstm.setString(6, objpdto.getCpf_titular());
                pstm.execute();
                pstm.close();
                
                conn.close();
                ImprimeBDPIX(objpdto);
                
                  JOptionPane.showMessageDialog(null, "Pix realizado com suscesso " +
                "\nDe: " + objpdto.getTitularR()+ "\nValor: " + objpdto.getValorPix() + "\nPara: " + 
                        objpdto.getTitularD());
        
                
            }else{
                JOptionPane.showMessageDialog(null, "Saldo Insuficiente ");
            }
            
        } catch (Exception e) {
            
                JOptionPane.showMessageDialog(null, "realizarPixDAO " + e);
        }
    
        
    }
    
    public void realizarPixCnpj(){
    
    PessoaFisicaDTO objpdto = new PessoaFisicaDTO();
    PessoaJuridicaDTO objjdto = new PessoaJuridicaDTO();
    String sqlAtualizacao = "UPDATE conta SET Saldo = CASE WHEN Certi_P = ? THEN Saldo - ? WHEN Certi_P = ? THEN Saldo + ? ELSE Saldo END WHERE Certi_P IN (?,?);";
    
    String consultaSaldo = "Select Saldo From conta Where Certi_P = ?";
    buscarCpfParaComprovantesPix();
    
    conn = new ConexaoDAO().Conexao();
    
        try {
            pstm = conn.prepareStatement(consultaSaldo);
            pstm.setString(1, objpdto.getCpf());
            pstm.execute();
            
            ResultSet resultadoConsulta = pstm.executeQuery();
            float saldo = 0.0f;
            if(resultadoConsulta.next()){
                saldo = resultadoConsulta.getFloat("Saldo");
            }else{
                JOptionPane.showMessageDialog(null, "Erro ao consultar o saldo");
            }
            resultadoConsulta.close();
            
            float valorPix = objpdto.getValorPix();
            
            // Verifica se o saldo é menor ou igual ao investimento
            if(saldo >= valorPix){
                
                
                pstm = conn.prepareStatement(sqlAtualizacao);
                pstm.setString(1, objpdto.getCpf());
                pstm.setFloat(2, valorPix);
                
                pstm.setString(3, objjdto.getCnpj_titular());
                pstm.setFloat(4, valorPix);
                
                pstm.setString(5, objpdto.getCpf());
                pstm.setString(6, objjdto.getCnpj_titular());
                pstm.execute();
                pstm.close();
                
                conn.close();
                ImprimeBDPIX(objpdto);
                
                JOptionPane.showMessageDialog(null, "Pix realizado com suscesso " +
                "\nDe: " + objpdto.getTitularR()+ "\nValor: " + objpdto.getValorPix() + "\nPara: " + 
                        objpdto.getTitularD());
        
                
            }else{
                JOptionPane.showMessageDialog(null, "Saldo Insuficiente ");
            }
            
        } catch (Exception e) {
            
                JOptionPane.showMessageDialog(null, "realizarPixDAO " + e);
        }
    
        
    }
    
    public void ImprimeBDPIX(PessoaFisicaDTO objPessoaFisicaDTO){
        String sqlArmazena = "INSERT INTO `transferencias_pix` (Titular_R, Tipo_T, CertP_R, valor, Titular_D,  "
                + "CertP_D, Data_T) VALUES (?, ?, ?, ?, ?, ?, ? );";
        conn = new ConexaoDAO().Conexao();
        buscarCpfParaComprovantesPix();
        RegistraDataPix();
        try{
            PreparedStatement pstmArmazenaDados = conn.prepareStatement(sqlArmazena);
            pstmArmazenaDados.setString(1, objPessoaFisicaDTO.getTitularR());
            pstmArmazenaDados.setString(2, "PIX");
            pstmArmazenaDados.setString(3, objPessoaFisicaDTO.getCpf());                
            pstmArmazenaDados.setFloat(4, objPessoaFisicaDTO.getValorPix());
            pstmArmazenaDados.setString(5, objPessoaFisicaDTO.getTitularD());
            pstmArmazenaDados.setString(6, objPessoaFisicaDTO.getCpf_titular());
            pstmArmazenaDados.setString(7, objPessoaFisicaDTO.getData_System());

            pstmArmazenaDados.execute();
            pstmArmazenaDados.close();
            
           
        }catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "imprimeBDPIXDAO : " + erro.getMessage());
        }
    }
    
     public ArrayList<PessoaFisicaDTO> HistoricoPIX(String cpf){ 
        conn = new ConexaoDAO().Conexao();
        String sql = "SELECT * FROM transferencias_pix WHERE CertP_R =" + cpf + " AND Tipo_T = 'PIX' ";
        try{        
            pstm = conn.prepareStatement(sql);
            rs = pstm.executeQuery();
            
            while(rs.next()){
                PessoaFisicaDTO pessoaDTO = new PessoaFisicaDTO();
                pessoaDTO.setTitularHistoricoR(rs.getString("Titular_R"));
                pessoaDTO.setValorPixTabela(rs.getFloat("valor"));
                pessoaDTO.setTitularHistoricoD(rs.getString("Titular_D"));
                pessoaDTO.setData_transferencia_pix(rs.getString("Data_T"));  
                pessoaDTO.setTipo_transferenciaPix(rs.getString("Tipo_T"));
                lista.add(pessoaDTO);
            }
        }catch(SQLException erro){            
            JOptionPane.showMessageDialog(null, "Erro pesquisar Historico_Pix" + erro);
        }
        return lista;
    }
    
    public void realizarTedDoc(PessoaFisicaDTO cadastrardto) {
        
        String sqlAtualizacao = "UPDATE conta SET Saldo = CASE WHEN Certi_P = ? THEN Saldo - ? WHEN Numero_conta = ? and Agencia = ? THEN Saldo + ? ELSE Saldo END WHERE Certi_P IN (?) OR (Numero_conta = ? AND Agencia = ?)";
        String consultarSaldo = "Select Saldo FROM conta WHERE Certi_P = ?";
        PessoaFisicaDTO objpdto = new PessoaFisicaDTO();
       
        conn = new ConexaoDAO().Conexao();
        
        try {
            pstm = conn.prepareStatement(consultarSaldo);
            pstm.setString(1, objpdto.getCpf());
            pstm.execute();
            
            ResultSet resultadoConsulta = pstm.executeQuery();
            float saldo = 0f;
            if(resultadoConsulta.next()){
                saldo = resultadoConsulta.getFloat("Saldo");
                
            }else{
                JOptionPane.showMessageDialog(null, "Erro ao consultar o saldo");
                }
            resultadoConsulta.close();
            pstm.close();
            float valorTed_Doc = objpdto.getValorTed_Doc();
            
            if(saldo >= valorTed_Doc){
                
                pstm = conn.prepareStatement(sqlAtualizacao);
                pstm.setString(1, objpdto.getCpf());
                pstm.setFloat(2, valorTed_Doc);
                
                pstm.setInt(3, objpdto.getContaTabelaD());
                pstm.setInt(4, objpdto.getAgenciaTabelaD());
                pstm.setFloat(5, valorTed_Doc);
                
                pstm.setString(6, objpdto.getCpf());
                pstm.setInt(7, objpdto.getContaTabelaD());
                pstm.setInt(8, objpdto.getAgenciaTabelaD());
                
                pstm.execute();
                pstm.close();
                
                ImprimeBDTED(objpdto);
                
               
                
                JOptionPane.showMessageDialog(null, "Ted/Doc realizado com sucesso " + "\nConta_Re: " 
                        + objpdto.getConta() + "\nAgência_Re: " + objpdto.getAgencia() + "\nValor: " 
                        + objpdto.getValorTed_Doc() + "Conta_Des: " 
                        + objpdto.getContaTabelaD() + "\nAgência_Des: " 
                        + objpdto.getAgenciaTabelaD());
            }else{
                JOptionPane.showMessageDialog(null, "Saldo Insuficiente");
            }
            
           
            conn.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "realizarTed_DocDAO " + e);
        }
    }
    
    public void ImprimeBDTED(PessoaFisicaDTO objPessoaFisicaDTO){
        String sqlArmazena = "INSERT INTO `transferencias_ted` (Certi_R, Tipo_T, Conta_R, Agencia_R, "
                + "Valor, Conta_D, Agencia_D, Data_T) VALUES (?, ?, ?, ?, ?, ?, ?, ? );";
        conn = new ConexaoDAO().Conexao();
        
        RegistraDataTed();
        
        try{
            PreparedStatement pstmArmazenaDados = conn.prepareStatement(sqlArmazena);
            pstmArmazenaDados.setString(1, objPessoaFisicaDTO.getCpf());
            pstmArmazenaDados.setString(2, "TED_DOC");
            pstmArmazenaDados.setString(3, objPessoaFisicaDTO.getConta());  
            pstmArmazenaDados.setString(4, objPessoaFisicaDTO.getAgencia());
            pstmArmazenaDados.setFloat(5, objPessoaFisicaDTO.getValorTed_Doc());
            pstmArmazenaDados.setInt(6, objPessoaFisicaDTO.getContaTabelaD());
            pstmArmazenaDados.setInt(7, objPessoaFisicaDTO.getAgenciaTabelaD());
            pstmArmazenaDados.setString(8, objPessoaFisicaDTO.getData_System());
            
            
          
            pstmArmazenaDados.execute();
            pstmArmazenaDados.close();
            
            System.out.println(objPessoaFisicaDTO.getData_System());
        }catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro ao armazenar dados: " + erro.getMessage());
        }
    }
    public ArrayList<PessoaFisicaDTO> HistoricoTED(String cpf){ 
        conn = new ConexaoDAO().Conexao();
        String sql = "SELECT * FROM transferencias_ted WHERE Certi_R = " + cpf + " AND Tipo_T = 'TED_DOC' ";
         
        try{        
            pstm = conn.prepareStatement(sql);
            
            rs = pstm.executeQuery();

            while(rs.next()){
                PessoaFisicaDTO pessoaDTO = new PessoaFisicaDTO();
                pessoaDTO.setConta(rs.getString("Conta_R"));
                pessoaDTO.setAgencia(rs.getString("Agencia_R"));
                pessoaDTO.setValorTed(rs.getFloat("valor"));
                pessoaDTO.setContaTabelaD(rs.getInt("Conta_D"));
                pessoaDTO.setAgenciaTabelaD(rs.getInt("Agencia_D"));
                pessoaDTO.setData_transferencia_ted(rs.getString("Data_T"));
                pessoaDTO.setTipo_transferenciaTed(rs.getString("Tipo_T"));
                lista.add(pessoaDTO);
            }
        }catch(SQLException erro){            
            JOptionPane.showMessageDialog(null, "Erro pesquisar Historico_Ted" + erro);
        }
        return lista;
    }
    
    public void RegistraDataPix (){
        
        PessoaFisicaDTO pessoaDTO = new PessoaFisicaDTO();
        
        DateTimeFormatter dataFormatada = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");  
        LocalDateTime now = LocalDateTime.now();
        
        //Formata a data pra ser recebida no banco de dados
        String dataFormatadaString = now.format(dataFormatada);
        
        pessoaDTO.setData_System(dataFormatadaString);
        pessoaDTO.setData_transferencia_pix(dataFormatadaString);
                
    }
    
    public void RegistraDataTed (){
        
        PessoaFisicaDTO pessoaDTO = new PessoaFisicaDTO();
        
        DateTimeFormatter dataFormatada = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");  
        LocalDateTime now = LocalDateTime.now();
        
        //Formata a data pra ser recebida no banco de dados
        String dataFormatadaString = now.format(dataFormatada);
        
        pessoaDTO.setData_System(dataFormatadaString);
        pessoaDTO.setData_transferencia_ted(dataFormatadaString);
                
    }
    
    
     public PessoaFisicaDTO Saldo() {
        conn = new ConexaoDAO().Conexao();
        PessoaFisicaDTO objUsuarioDTO = new PessoaFisicaDTO();
        String sql = "select SALDO from conta where Certi_P = " + objUsuarioDTO.getCpf();
        try {
            pstm = conn.prepareStatement(sql);
            rs = pstm.executeQuery();
            while (rs.next()) {
                objUsuarioDTO.setSaldo(rs.getString("Saldo"));

            }

        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "SaldoDAO " + erro);
        }
        return objUsuarioDTO;
    }
    
     
    
      public PessoaFisicaDTO buscarCpfParaComprovantesPix(){
          conn = new ConexaoDAO().Conexao();
          PessoaFisicaDTO objpdto = new PessoaFisicaDTO();
          String sqlR = "Select Titular from conta WHERE Certi_P = " + objpdto.getCpf();
          String sqlD = "Select Titular from conta WHERE Certi_P =  " + objpdto.getCpf_titular();
         
          try {
              pstm = conn.prepareStatement(sqlR);
              rs = pstm.executeQuery();
              while (rs.next()) {
                  objpdto.setTitularR(rs.getString("Titular"));
              }
              
              pstm = conn.prepareStatement(sqlD);
            
              rs = pstm.executeQuery();
              while (rs.next()) {                  
                  objpdto.setTitularD(rs.getString("Titular"));
                
              }
             
          
          } catch (Exception e) {
              JOptionPane.showMessageDialog(null, "buscarCpfParaComprovantePixDAO " + e);
        }
        return null;
      }
      
        public ArrayList<PessoaFisicaDTO> HistoricoRecebimentosPix(){ 
        conn = new ConexaoDAO().Conexao();
        PessoaFisicaDTO objpdto = new PessoaFisicaDTO();
        String sql = "SELECT * FROM transferencias_pix Where CertP_D = " + objpdto.getCpf();
        try{        
            pstm = conn.prepareStatement(sql);
            rs = pstm.executeQuery();
            
            while(rs.next()){
                PessoaFisicaDTO pessoaDTO = new PessoaFisicaDTO();
                pessoaDTO.setTitularHistoricoR(rs.getString("Titular_R"));
                pessoaDTO.setValorPixTabela(rs.getFloat("valor"));
                pessoaDTO.setData_transferencia_pix(rs.getString("Data_T"));  
                pessoaDTO.setTipo_transferenciaPix(rs.getString("Tipo_T"));
                lista.add(pessoaDTO);
            }
        }catch(SQLException erro){            
            JOptionPane.showMessageDialog(null, "HistoricoRecebimentosDAO " + erro);
        }
        return lista;
    }
        
         public ArrayList<PessoaFisicaDTO> HistoricoRecebimentosTed(){ 
        conn = new ConexaoDAO().Conexao();
        PessoaFisicaDTO objpdto = new PessoaFisicaDTO();
        String sql = "SELECT * FROM transferencias_ted Where Certi_D = " + objpdto.getCpf_titular();
        try{        
            pstm = conn.prepareStatement(sql);
            rs = pstm.executeQuery();
            
            while(rs.next()){
                PessoaFisicaDTO pessoaDTO = new PessoaFisicaDTO();
                pessoaDTO.setConta(rs.getString("Conta_R"));
                pessoaDTO.setAgencia(rs.getString("Agencia_R"));
                pessoaDTO.setValorTed(rs.getFloat("Valor"));
                pessoaDTO.setData_transferencia_pix(rs.getString("Data_T"));  
                pessoaDTO.setTipo_transferenciaPix(rs.getString("Tipo_T"));
                lista.add(pessoaDTO);
            }
        }catch(SQLException erro){            
            JOptionPane.showMessageDialog(null, "HistoricoRecebimentosDAO " + erro);
        }
        return lista;
    }
        
   
}
      
     
      
  
     
       
