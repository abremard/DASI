package fr.insalyon.dasi.ihm.web.action;

import fr.insalyon.dasi.metier.service.Service;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author DASI Team
 */
public class SmsEmployeAction extends Action {

    @Override
    public void executer(HttpServletRequest request) {
        
        Long consultationId = Long.parseLong(request.getParameter("consultationId"));

        Service service = new Service();
        String smsEmploye = service.EnvoyerMessageConsultation(consultationId);

        request.setAttribute("smsEmployeConsultation", smsEmploye);
    }
    
}
