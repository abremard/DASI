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
public class FetchConsultationAction extends Action {

    @Override
    public void executer(HttpServletRequest request) {
        
        HttpSession session = request.getSession();

        Long id = (Long)session.getAttribute("id");

        Service service = new Service();        

        Consultation consultation = service.fetchConsultationEmploye(id);

        request.setAttribute("consultationEmploye", consultation);
        
    }
    
}
