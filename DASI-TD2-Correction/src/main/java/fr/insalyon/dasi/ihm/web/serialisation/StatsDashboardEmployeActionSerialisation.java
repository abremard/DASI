package fr.insalyon.dasi.ihm.web.serialisation;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import fr.insalyon.dasi.metier.modele.Employe;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class StatsDashboardEmployeActionSerialisation extends Serialisation {

    @Override
    public void serialiser(HttpServletRequest request, HttpServletResponse response) throws IOException {

        List<Employe> listeEmploye = (List<Employe>)request.getAttribute("statsEmploye");
        
        JsonObject container = new JsonObject();

        if (listeEmploye != null) {
            JsonArray jsonlisteEmploye = new JsonArray();
            
            for (Employe employe : listeEmploye) {
                JsonObject jsonEmploye = new JsonObject();
                jsonEmploye.addProperty("id", employe.getId());
                jsonEmploye.addProperty("nom", employe.getNom());
                jsonEmploye.addProperty("prenom", employe.getPrenom());
                jsonEmploye.addProperty("nbconsultation", employe.getNbConsultation());
                jsonlisteEmploye.add(jsonEmploye);
            }
            container.add("employes", jsonlisteEmploye); 
        }

        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = response.getWriter();
        Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
        gson.toJson(container, out);
        out.close();
    }
}