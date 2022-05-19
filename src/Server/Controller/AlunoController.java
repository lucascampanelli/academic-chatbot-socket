package Server.Controller;

import Server.DAO.DaoAluno;
import Server.DAO.DaoBoleto;
import Server.DAO.DaoCurriculo;
import Server.DAO.DaoHistorico;
import Server.Model.AlunoModel;
import Server.Model.BoletoModel;
import Server.Model.CurriculoModel;
import Server.Model.HistoricoModel;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;

/**
 *
 * @author Erick Pereira, Felipe Campos, Guilherme Rodrigues, Lucas Campanelli, Paulo Silveira e Ronaldo Arley
 */
public class AlunoController {
    
    private DaoAluno DAOAluno;
    private DaoCurriculo DAOCurriculo;
    private DaoHistorico DAOHistorico;
    private DaoBoleto DAOBoleto;
    
    public Response realizarMatricula(int cursoID, String nome, String sobrenome,
                                      String rg, String cpf, String sexo,
                                      String dataNascimento, String celular, String telefone,
                                      String email, String endereco, String complemento,
                                      String bairro, String cidade, String estado,
                                      String cep, String historico, boolean bolsa, int campusID){
        
        this.DAOAluno = new DaoAluno();
        
        // Instanciando o objeto de retorno
        Response resposta = new Response();
        
        // Variável que armazenará o sexo validado
        String validaSexo = "";
        // Variável que armazenará o email institucional gerado
        String emailInsti;
        // Variável para validar o histórico escolar (se houver entrada String cria um boolean true)
        boolean validaHistorico = false;
        
        // Se o sexo inserido for igual a masculino, adiciona um M na variável de validação, senão adiciona um F
        if("masculino".equalsIgnoreCase(sexo.trim()))
            validaSexo = "M";
        else if("masculino".equalsIgnoreCase(sexo.trim()))
            validaSexo = "F";
        
        if(validaSexo.equals("")){
            resposta.setSuccess(false);
            resposta.setError("Ocorreu um problema ao realizar a matrícula. Tente novamente mais tarde."
                                + "\nSe o problema persistir, entre em contato com a Central de Atendimento ao Aluno.");
            
            return resposta;
        }
            
        // Pegando o segundo do tempo da criação da matrícula para evitar duplicidade de e-mails
        String currentTime = LocalTime.now().toString().split(":")[2].substring(0, 2);
        // Pegando os últimos dois dígitos do RG do aluno a ser inserido
        String rgFinal = rg.substring(rg.length() - 3, rg.length() - 1);
        
        String[] sobrenomeMail = sobrenome.split(" ");
        
        // Cria o e-mail institucional com o nome, sobrenome, segundo do tempo atual e os últimos dois dígitos do rg
        emailInsti = (nome + "." + sobrenomeMail[sobrenomeMail.length - 1] + currentTime + rgFinal + "@ulife.com").toLowerCase();
        
        // Validando a entrega do histórico escolar do ensino médio
        if(historico.length() > 0)
            validaHistorico = true;
        
        // Instânciando um objeto do modelo do aluno para mandar ao banco de dados
        AlunoModel aluno =  new AlunoModel(nome, sobrenome, rg, 
                                           cpf, validaSexo, dataNascimento, 
                                           celular, telefone, email, 
                                           emailInsti, endereco, complemento, bairro, 
                                           cidade, estado, cep, 1,
                                           "Matriculado", validaHistorico, false, bolsa, campusID, cursoID);
        
        // O RA do aluno será o ID cadastrado no banco de dados
        int ra = this.DAOAluno.inserirAluno(aluno);
        
        // Se o RA não for maior do que 0, ocorreu um erro
        if(!(ra > 0)){
            resposta.setSuccess(false);
            resposta.setError("Ocorreu um problema ao realizar a matrícula. Tente novamente mais tarde."
                                + "\nSe o problema persistir, entre em contato com a Central de Atendimento ao Aluno.");
            
            return resposta;
        }
        
        // Adicionando o ID do usuário cadastrado ao modelo (Será esse o RA do aluno)
        aluno.setID(ra);
        
        // Criando a lista que armazenará o currículo (disciplinas que fazem parte do curso) desse semestre
        ArrayList<CurriculoModel> curriculoSemestre;
        
        // Instanciando a classe DAO do currículo que possui o método para listar o currículo do semestre
        this.DAOCurriculo  = new DaoCurriculo();
        
        // Armazenando na lista o currículo do primeiro semestre do curso escolhido pelo aluno
        curriculoSemestre = this.DAOCurriculo.listarCurriculoSemestre(1, cursoID);
        
        // Se o currículo acadêmico do semestre não for maior do que 0, ocorreu um erro
        if(curriculoSemestre == null){
            resposta.setSuccess(false);
            resposta.setError("Ocorreu um problema ao realizar a matrícula. Tente novamente mais tarde."
                                + "\nSe o problema persistir, entre em contato com a Central de Atendimento ao Aluno.");
            
            return resposta;
        }
        
        // Instanciando a clase DAO do histórico que possui o método para inserir uma disciplina no histórico
        this.DAOHistorico = new DaoHistorico();
        
        // Cada disciplina que o curso tem nesse semestre será adicionada ao histórico (disciplinas cursadas) do aluno
        for(int i = 0; i < curriculoSemestre.size(); i++){
            HistoricoModel historicoModelo = new HistoricoModel(1, "Obrigatória", 0, curriculoSemestre.get(i).getCargaHoraria(), 8, 
                                                          "Cursando", curriculoSemestre.get(i).getDiaSemana(), curriculoSemestre.get(i).getUcID(), ra);
        
            // Se o ID do histórico criado, retornado pelo método, não for maior do que 0, ocorreu um erro
            if(!(this.DAOHistorico.inserirHistorico(historicoModelo) > 0)){
                resposta.setSuccess(false);
                resposta.setError("Ocorreu um problema ao realizar a matrícula. Tente novamente mais tarde."
                                    + "\nSe o problema persistir, entre em contato com a Central de Atendimento ao Aluno.");

                return resposta;
            }  
        }
        
        DAOBoleto = new DaoBoleto();
        
        if(!bolsa){
            String codBarras;
            String mesReferencia;
            int valor;
            int desconto = 0;
            String vencimento;
            String status = "Aguardando pagamento";
            
            // Gerando código de barras aleatório
            codBarras = rg.substring(0, 2) + 
                        LocalTime.now().toString().split(":")[0] + 
                        LocalTime.now().toString().split(":")[1] + 
                        rg.substring(rg.length() - 4) +
                        cpf.substring(cpf.length() - 2);
            
            mesReferencia = LocalDateTime.now().toString().split("-")[1] + "/" + LocalDateTime.now().toString().split("-")[0];
            
            /* 
            * Esse trecho deve ser automatizado futuramente.
            * O valor do boleto deve ser de acordo com o valor do curso indicado no banco de dados.
            * Na modelagem do banco de dados, ainda não foi inserido a coluna valor na tabela do curso.
            * Além disso, deve ser verificado se o aluno possui bolsa total ou parcial.
            * Inserindo esses valores hardcoded por enquanto.
            */
            valor = 163900;
            
            vencimento = "05/" + LocalDateTime.now().toString().split("-")[1] + "/" + LocalDateTime.now().toString().split("-")[0];
            
            BoletoModel boleto = new BoletoModel(codBarras, mesReferencia, valor, desconto, vencimento, status, ra);
            
            if(!(this.DAOBoleto.inserirBoleto(boleto) > 0)){
                System.out.println("Ocorreu um erro ao gerar o boleto de matrícula do aluno.");
                resposta.setMessage(nome + ", sua matrícula foi realizada com sucesso!\nSeu horário das disciplinas já está disponível para consulta.");
            }
            else{
                resposta.setMessage(nome + ", sua matrícula foi realizada com sucesso!\nO seu boleto de matrícula já está disponível para pagamento e pode ser consultado por este canal.\nAlém disso, seu horário das disciplinas já está disponível para consulta.");
            }
        }
        else{
            String codBarras;
            String mesReferencia;
            int valor;
            int desconto;
            String vencimento;
            String status;
            
            // Gerando código de barras aleatório
            codBarras = rg.substring(0, 2) + 
                        LocalTime.now().toString().split(":")[0] + 
                        LocalTime.now().toString().split(":")[1] + 
                        rg.substring(rg.length() - 4) +
                        cpf.substring(cpf.length() - 2);
            
            mesReferencia = LocalDateTime.now().toString().split("-")[1] + "/" + LocalDateTime.now().toString().split("-")[0];
            
            /* 
            * Esse trecho deve ser automatizado futuramente.
            * O valor do boleto deve ser de acordo com o valor do curso indicado no banco de dados.
            * Na modelagem do banco de dados, ainda não foi inserido a coluna valor na tabela do curso.
            * Além disso, deve ser verificado se o aluno possui bolsa total ou parcial.
            * Inserindo esses valores hardcoded por enquanto.
            */
            valor = 163900;
            desconto = valor;
            status = "Boleto pago";
            
            vencimento = "05/" + LocalDateTime.now().toString().split("-")[1] + "/" + LocalDateTime.now().toString().split("-")[0];
            
            BoletoModel boleto = new BoletoModel(codBarras, mesReferencia, valor, desconto, vencimento, status, ra);
            
            if(!(this.DAOBoleto.inserirBoleto(boleto) > 0)){
                System.out.println("Ocorreu um erro ao gerar o boleto de matrícula do aluno.");
                resposta.setMessage(nome + ", sua matrícula foi realizada com sucesso!\nSeu horário das disciplinas já está disponível para consulta.");
            }
            else{
                resposta.setMessage(nome + ", sua matrícula foi realizada com sucesso!\nO seu boleto de matrícula já está disponível para pagamento e pode ser consultado por este canal.\nAlém disso, seu horário das disciplinas já está disponível para consulta.");
            }
        }
        
        resposta.setSuccess(true);
        
        return resposta;
    }
    
}
