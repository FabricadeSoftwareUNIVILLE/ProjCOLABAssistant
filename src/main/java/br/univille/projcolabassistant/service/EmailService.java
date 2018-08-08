package br.univille.projcolabassistant.service;

import br.univille.projcolabassistant.util.EmailFields;


public interface EmailService {
    
    void SendEmail(EmailFields emailfields);

}