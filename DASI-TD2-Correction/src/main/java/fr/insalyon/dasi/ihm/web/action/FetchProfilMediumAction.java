package fr.insalyon.dasi.ihm.web.action;

import fr.insalyon.dasi.metier.modele.Medium;
import fr.insalyon.dasi.metier.service.Service;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author DASI Team
 */
public class FetchProfilMediumAction extends Action {

    @Override
    public void executer(HttpServletRequest request) {
        
        Long id = Long.parseLong(request.getParameter("id"));

        Service service = new Service();
        Medium medium = service.afficherDetailsMedium(id);

        request.setAttribute("medium", medium);
        
    }
    
}
