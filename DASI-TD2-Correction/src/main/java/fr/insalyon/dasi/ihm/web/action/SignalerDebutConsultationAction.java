package fr.insalyon.dasi.ihm.web.action;

import fr.insalyon.dasi.metier.modele.Consultation;
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
public class SignalerDebutConsultationAction extends Action {

    @Override
    public void executer(HttpServletRequest request) {
        
        Long consultationId = Long.parseLong(request.getParameter("id"));

        Service service = new Service();
        Consultation consultation = null;
        try {
            consultation = service.SignalerDebutConsultation(consultationId);
        } catch (IOException ex) {
            Logger.getLogger(SignalerDebutConsultationAction.class.getName()).log(Level.SEVERE, null, ex);
        }

        request.setAttribute("consultation", consultation);
    }
    
}
