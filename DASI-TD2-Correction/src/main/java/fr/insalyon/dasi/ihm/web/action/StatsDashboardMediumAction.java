package fr.insalyon.dasi.ihm.web.action;

import fr.insalyon.dasi.metier.modele.Medium;
import fr.insalyon.dasi.metier.service.Service;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Iyad
 */
public class StatsDashboardMediumAction extends Action {
    @Override
    public void executer(HttpServletRequest request) {
        Service service = new Service();
        List<Medium> statsMedium = service.statsDashboardMedium();
        request.setAttribute("statsMedium", statsMedium);
        
    }
}
