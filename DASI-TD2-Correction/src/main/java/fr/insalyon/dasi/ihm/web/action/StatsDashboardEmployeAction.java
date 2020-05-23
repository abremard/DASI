package fr.insalyon.dasi.ihm.web.action;

import fr.insalyon.dasi.metier.modele.Employe;
import fr.insalyon.dasi.metier.service.Service;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author DASI Team
 */
public class StatsDashboardEmployeAction extends Action {

    @Override
    public void executer(HttpServletRequest request) {
        Service service = new Service();
        List<Employe> statsEmploye = service.statsDashboardEmploye();
        request.setAttribute("statsEmploye", statsEmploye);
        
    }
    
}