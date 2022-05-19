package Server.Controller;

/**
 *
 * @author lucas
 */
public class Response {
    
    // Variável que armazenará a mensagem de erro gerada no controller
    private String error;
    // Variável que armazenará a mensagem de sucesso gerada no controller
    private String message;
    // Variável do 
    private String success;
    
    public Response(){
        this.error = null;
        this.message = null;
        this.success = null;
    }
    
    public void setError(String error){
        this.error = error;
    }
    
    public void setMessage(String message){
        this.message = message;
    }
    
    public void setSuccess(boolean success){
        if(success)
            this.success = "true";
        else
            this.success = "false";
    }
    
    public String getError(){
        return this.error;
    }
    
    public String getMessage(){
        return this.message;
    }
    
    public boolean getSuccess(){
        if(this.success.equalsIgnoreCase("true"))
            return true;
        else
            return false;
    }
}
