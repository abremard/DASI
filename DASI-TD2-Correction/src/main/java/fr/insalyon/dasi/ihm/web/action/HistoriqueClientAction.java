package fr.insalyon.dasi.ihm.web.action;

import fr.insalyon.dasi.metier.modele.Consultation;
import fr.insalyon.dasi.metier.service.Service;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author DASI Team
 */
public class HistoriqueClientAction extends Action {

    @Override
    public void executer(HttpServletRequest request) {
        
        Long id = Long.parseLong(request.getParameter("id"));

        Service service = new Service();
        List<Consultation> consultations = service.ConsulterHistoriqueConsultationClient(id);

        request.setAttribute("historiqueConsultationsClient", consultations);
    }
    
}
