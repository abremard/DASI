package fr.insalyon.dasi.ihm.web.action;

import fr.insalyon.dasi.metier.modele.Consultation;
import fr.insalyon.dasi.metier.service.Service;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Iyad
 */
public class ConsulterHistoriqueConsultationEmployeAction extends Action {
    @Override
    public void executer(HttpServletRequest request) {
        
        HttpSession session = request.getSession();
        Long id = (Long)session.getAttribute("id");
        Service service = new Service();
        List<Consultation> consultation = service.ConsulterHistoriqueConsultationEmploye(id);
    }
}
