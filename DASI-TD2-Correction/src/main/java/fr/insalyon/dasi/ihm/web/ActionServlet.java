package fr.insalyon.dasi.ihm.web;

import fr.insalyon.dasi.dao.JpaUtil;
import fr.insalyon.dasi.ihm.web.action.Action;
import fr.insalyon.dasi.ihm.web.action.AuthentifierClientAction;
import fr.insalyon.dasi.ihm.web.action.AuthentifierEmployeAction;
import fr.insalyon.dasi.ihm.web.action.ConsulterHistoriqueConsultationEmployeAction;
import fr.insalyon.dasi.ihm.web.action.ListerMediumAction;
import fr.insalyon.dasi.ihm.web.action.ListerTypeMediumAction;
import fr.insalyon.dasi.ihm.web.action.FetchProfilClientAction;
import fr.insalyon.dasi.ihm.web.action.FetchProfilMediumAction;
import fr.insalyon.dasi.ihm.web.action.CreerClientAction;
import fr.insalyon.dasi.ihm.web.action.StatsDashboardEmployeAction;
import fr.insalyon.dasi.ihm.web.action.StatsDashboardMediumAction;
import fr.insalyon.dasi.ihm.web.action.DemanderConsultationAction;
import fr.insalyon.dasi.ihm.web.action.FetchConsultationAction;
import fr.insalyon.dasi.ihm.web.action.HistoriqueClientAction;
import fr.insalyon.dasi.ihm.web.action.PredictionAideAction;
import fr.insalyon.dasi.ihm.web.action.SignalerDebutConsultationAction;
import fr.insalyon.dasi.ihm.web.action.SmsClientAction;
import fr.insalyon.dasi.ihm.web.action.SmsEmployeAction;
import fr.insalyon.dasi.ihm.web.action.ValiderFinConsultationAction;
import fr.insalyon.dasi.ihm.web.serialisation.ConsultationEmployeSerialisation;

import fr.insalyon.dasi.ihm.web.serialisation.ProfilClientSerialisation;
import fr.insalyon.dasi.ihm.web.serialisation.ProfilEmployeSerialisation;
import fr.insalyon.dasi.ihm.web.serialisation.ProfilMediumSerialisation;
import fr.insalyon.dasi.ihm.web.serialisation.ListerMediumSerialisation;
import fr.insalyon.dasi.ihm.web.serialisation.ListerTypeMediumSerialisation;
import fr.insalyon.dasi.ihm.web.serialisation.Serialisation;
import fr.insalyon.dasi.ihm.web.serialisation.StatsDashboardEmployeActionSerialisation;
import fr.insalyon.dasi.ihm.web.serialisation.StatsDashboardMediumActionSerialisation;
import fr.insalyon.dasi.ihm.web.serialisation.ConsulterHistoriqueConsultationEmployeSerialisation;
import fr.insalyon.dasi.ihm.web.serialisation.ConsultationSerialisation;
import fr.insalyon.dasi.ihm.web.serialisation.HistoriqueConsultationSerialisation;
import fr.insalyon.dasi.ihm.web.serialisation.PredictionSerialisation;
import fr.insalyon.dasi.ihm.web.serialisation.SmsClientSerialisation;
import fr.insalyon.dasi.ihm.web.serialisation.SmsEmployeSerialisation;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author DASI Team
 */
@WebServlet(name = "ActionServlet", urlPatterns = {"/ActionServlet"})
public class ActionServlet extends HttpServlet {

    @Override
    public void init() throws ServletException {
        super.init();
        JpaUtil.init();
    }

    @Override
    public void destroy() {
        JpaUtil.destroy();
        super.destroy();
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(true);
        request.setCharacterEncoding("UTF-8");

        String todo = request.getParameter("todo");

        Action action = null;
        Serialisation serialisation = null;

        if (todo != null) {
            switch (todo) {
                // Cas utilisateurs externes
                case "lister-medium":
                    action = new ListerMediumAction();
                    serialisation = new ListerMediumSerialisation();
                    break;
                case "lister-type-medium":
                    action = new ListerTypeMediumAction();
                    serialisation = new ListerTypeMediumSerialisation();
                    break;
                case "details-medium":
                    action = new FetchProfilMediumAction();
                    serialisation = new ProfilMediumSerialisation();
                    break;
                case "connecter":
                    action = new AuthentifierClientAction();
                    serialisation = new ProfilClientSerialisation();
                    break; 
                case "connecter-employe":
                    action = new AuthentifierEmployeAction();
                    serialisation = new ProfilEmployeSerialisation();
                    break;
                case "creation-compte":
                    action = new CreerClientAction();
                    serialisation = new ProfilClientSerialisation();
                    break;
                case "mail-client": // TO ADD ON JAVASCRIPT
                    action = new DemanderConsultationAction();
                    serialisation = new ConsultationSerialisation();
                    break;                
                default:
                    // Cas utilisateurs internes
                    if (session.getAttribute("user") != null) {
                        String sessionUser = (String)session.getAttribute("user");
                        switch (sessionUser) {
                            case "client":
                                switch (todo) {
                                    case "init-client":
                                        action = new FetchProfilClientAction();
                                        serialisation = new ProfilClientSerialisation();
                                        break;
                                    case "demander-consultation":
                                        action = new DemanderConsultationAction();
                                        serialisation = new ConsultationSerialisation();
                                        break;
                                    case "sms-client":
                                        action = new SmsClientAction();
                                        serialisation = new SmsClientSerialisation();
                                        break;
                                    case "sms-employe":
                                        action = new SmsEmployeAction();
                                        serialisation = new SmsEmployeSerialisation();
                                        break;
                                }
                            case "employe":
                                switch (todo) {
                                    // Consultation
                                    case "fetch-consultation":
                                        action = new FetchConsultationAction();
                                        serialisation = new ConsultationEmployeSerialisation();
                                        break;
                                    case "signaler-debut-consultation":
                                        action = new SignalerDebutConsultationAction();
                                        serialisation = new ConsultationSerialisation();
                                        break;
                                    case "historique-client":
                                        action = new HistoriqueClientAction();
                                        serialisation = new HistoriqueConsultationSerialisation();
                                        break;
                                    case "prediction-aide":
                                        action = new PredictionAideAction();
                                        serialisation = new PredictionSerialisation();
                                        break;
                                    case "valider-fin-consultation":
                                        action = new ValiderFinConsultationAction();
                                        serialisation = new ConsultationSerialisation();
                                        break;
                                    // Dashboard
                                    case "dashboard-employe":
                                        action = new StatsDashboardEmployeAction();
                                        serialisation = new StatsDashboardEmployeActionSerialisation();
                                        break;    
                                    case "dashboard-medium":
                                        action = new StatsDashboardMediumAction();
                                        serialisation = new StatsDashboardMediumActionSerialisation();
                                        break;
                                    case "historique-employe":
                                        action = new ConsulterHistoriqueConsultationEmployeAction();
                                        serialisation = new ConsulterHistoriqueConsultationEmployeSerialisation();
                                        break; 
                                }
                        }
                    }
                    // Cas utilisateurs sans session => les rediriger
                    else {
                        
                    }
            }
        }
        
        if (action != null) {
            action.executer(request);
            serialisation.serialiser(request, response);
        }
        else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Erreur dans les paramètres de la requête");
        }

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
