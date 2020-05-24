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
public class ValiderFinConsultationAction extends Action {

    @Override
    public void executer(HttpServletRequest request) {
        
        Long consultationId = Long.parseLong(request.getParameter("id"));
        String commentaire = request.getParameter("commentaire");

        Service service = new Service();
        Consultation consultation = null;
        try {
            consultation = service.ValiderFinConsultation(consultationId, commentaire);
        } catch (IOException ex) {
            Logger.getLogger(ValiderFinConsultationAction.class.getName()).log(Level.SEVERE, null, ex);
        }

        request.setAttribute("consultationEmploye", consultation);
    }
    
}
