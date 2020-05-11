package fr.insalyon.dasi.ihm.web.action;

import fr.insalyon.dasi.metier.modele.Medium;
import fr.insalyon.dasi.metier.service.Service;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author DASI Team
 */
public class ListerMediumAction extends Action {

    @Override
    public void executer(HttpServletRequest request) {
        
        String type = request.getParameter("type");

        Service service = new Service();
        List<String> listeMediums = service.listerMediumParType(type);

        request.setAttribute("listeMediums", listeMediums);
    }
    
}
