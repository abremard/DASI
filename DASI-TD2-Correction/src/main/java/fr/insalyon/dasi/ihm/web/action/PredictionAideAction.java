package fr.insalyon.dasi.ihm.web.action;

import fr.insalyon.dasi.metier.modele.Medium;
import fr.insalyon.dasi.metier.service.Service;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author DASI Team
 */
public class PredictionAideAction extends Action {

    @Override
    public void executer(HttpServletRequest request) {
        
        String couleur = request.getParameter("couleur");
        String animal = request.getParameter("animal");

        int amour = Integer.parseInt(request.getParameter("amour"));
        int sante = Integer.parseInt(request.getParameter("sante"));
        int travail = Integer.parseInt(request.getParameter("travail"));

        Service service = new Service();
        List<String> predictions = null;
        try {
            predictions = service.PredictionsAide(couleur, animal, amour, sante, travail);
        } catch (IOException ex) {
            Logger.getLogger(PredictionAideAction.class.getName()).log(Level.SEVERE, null, ex);
        }

        request.setAttribute("predictions", predictions);
    }
    
}
