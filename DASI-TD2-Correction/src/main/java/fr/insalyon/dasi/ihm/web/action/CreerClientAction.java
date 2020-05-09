package fr.insalyon.dasi.ihm.web.action;

import fr.insalyon.dasi.metier.modele.Client;
import fr.insalyon.dasi.metier.service.Service;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author DASI Team
 */
public class CreerClientAction extends Action {

    @Override
    public void executer(HttpServletRequest request) {
        
        String nom = request.getParameter("nom");
        String prenom = request.getParameter("prenom");
        int jourNaissance = Integer.parseInt(request.getParameter("jourNaissance"));
        int moisNaissance = Integer.parseInt(request.getParameter("moisNaissance"));
        int anneeNaissance = Integer.parseInt(request.getParameter("anneeNaissance"));

        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(anneeNaissance, moisNaissance, jourNaissance);

        String adressePostale = request.getParameter("adressePostale");
        String adresseMail = request.getParameter("adresseMail");
        String telephone = request.getParameter("telephone");
        String mdp = request.getParameter("mdp");
        String mdpConf = request.getParameter("mdpConf");

        Service service = new Service();
        
        Client client = null;
        try {
            client = service.inscrireClient(nom, prenom, calendar.getTime(), adresseMail, adressePostale, telephone, mdp);
        } catch (IOException ex) {
            Logger.getLogger(CreerClientAction.class.getName()).log(Level.SEVERE, null, ex);
        }

        request.setAttribute("client", client);
        
        // Gestion de la Session: ici, enregistrer l'ID du Client authentifié
        HttpSession session = request.getSession();
        if (client != null) {
            session.setAttribute("idClient", client.getId());
        }
        else {
            session.removeAttribute("idClient");
        }
    }
    
}
