package fr.insalyon.dasi.ihm.web.action;

import fr.insalyon.dasi.metier.modele.Medium;
import fr.insalyon.dasi.metier.service.Service;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author DASI Team
 */
public class MailClientAction extends Action {

    @Override
    public void executer(HttpServletRequest request) {
        
        Long id = Long.parseLong(request.getParameter("id"));

        Service service = new Service();
        String mailString = null;
        try {
            mailString = service.EnvoyerMailConfirmationInscription(id);
        } catch (IOException ex) {
            Logger.getLogger(MailClientAction.class.getName()).log(Level.SEVERE, null, ex);
        }

        request.setAttribute("mailConfirmationInscription", mailString);
    }
    
}
