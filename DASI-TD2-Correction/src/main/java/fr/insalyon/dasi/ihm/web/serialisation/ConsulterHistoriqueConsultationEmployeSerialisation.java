package fr.insalyon.dasi.ihm.web.serialisation;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import fr.insalyon.dasi.metier.modele.Consultation;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ConsulterHistoriqueConsultationEmployeSerialisation extends Serialisation {

    @Override
    public void serialiser(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String pattern = "MM/dd/yyyy";
        DateFormat df = new SimpleDateFormat(pattern);

        List<Consultation> listeConsultation = (List<Consultation>)request.getAttribute("consultations");
        
        JsonObject container = new JsonObject();

        if (listeConsultation != null) {
            JsonArray jsonlisteConsultation = new JsonArray();
            
            for (Consultation consultation : listeConsultation) {
                JsonObject jsonConsultation = new JsonObject();
                jsonConsultation.addProperty("id", consultation.getId());
                jsonConsultation.addProperty("medium", consultation.getMedium().getDenomination());
                jsonConsultation.addProperty("statut", consultation.getStatut().name());
                jsonConsultation.addProperty("nomClient", consultation.getClient().getNom());
                jsonConsultation.addProperty("prenomClient", consultation.getClient().getPrenom());
                jsonConsultation.addProperty("commentaire", consultation.getCommentaire());
                String dateAsString = df.format(consultation.getTemps());
                jsonConsultation.addProperty("date", dateAsString);
                jsonlisteConsultation.add(jsonConsultation);
            }
            container.add("consultations", jsonlisteConsultation); 
        }

        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = response.getWriter();
        Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
        gson.toJson(container, out);
        out.close();
    }
}