package br.univille.projcolabassistant.util;

class EmailFields{
    
    // ATTRIBUTES
    private String from;
    private String to;
    private String subject;
    private String message;
    
    // SETTERS
    public void setFrom(String from){
        this.from = from;
    }
    public void seTo(String to){
        this.to = to;
    }
    public void setSubject(String subject){
        this.subject = subject;
    }
    public void setMessage(String message){
        this.message = message;
    }

    // GETTERS
    public String getFrom(){
        return this.from;
    }
    public String getTo(){
        return this.to;
    }
    public String getSubject(){
        return this.subject;
    }
    public String getMessage(){
        return this.message;
    }
}