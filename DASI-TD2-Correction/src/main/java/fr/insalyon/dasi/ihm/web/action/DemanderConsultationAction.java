package fr.insalyon.dasi.ihm.web.action;

import fr.insalyon.dasi.metier.modele.Consultation;
import fr.insalyon.dasi.metier.service.Service;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author DASI Team
 */
public class DemanderConsultationAction extends Action {

    @Override
    public void executer(HttpServletRequest request) {
        
        HttpSession session = request.getSession();

        Long id = (Long)session.getAttribute("id");

        Long mediumId = Long.parseLong(request.getParameter("medium"));

        Service service = new Service();        

        Consultation consultation = null;
        try {
            consultation = service.DemanderConsultation(id, mediumId);
        } catch (IOException ex) {
            Logger.getLogger(DemanderConsultationAction.class.getName()).log(Level.SEVERE, null, ex);
        }

        request.setAttribute("consultation", consultation);
        
    }
    
}
