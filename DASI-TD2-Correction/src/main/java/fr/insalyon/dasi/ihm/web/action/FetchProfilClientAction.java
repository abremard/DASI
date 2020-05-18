package fr.insalyon.dasi.ihm.web.action;

import fr.insalyon.dasi.metier.modele.Client;
import fr.insalyon.dasi.metier.service.Service;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author DASI Team
 */
public class FetchProfilClientAction extends Action {

    @Override
    public void executer(HttpServletRequest request) {
        
        HttpSession session = request.getSession();

        Long id = (Long)session.getAttribute("id");

        Service service = new Service();
        Client client = service.rechercherClientParId(id);

        request.setAttribute("client", client);
        
    }
    
}
