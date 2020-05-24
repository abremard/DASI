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

/**
 *
 * @author DASI Team
 */
public class HistoriqueConsultationSerialisation extends Serialisation {

    @Override
    public void serialiser(HttpServletRequest request, HttpServletResponse response) throws IOException {

        List<Consultation> listeConsultations = (List<Consultation>)request.getAttribute("historiqueConsultationsClient");
        
        JsonObject container = new JsonObject();

        if (listeConsultations != null) {
            JsonArray jsonlisteConsultations = new JsonArray();
            
            for (Consultation consultation : listeConsultations) {
                JsonObject jsonConsultation = new JsonObject();

                String pattern = "MM/dd/yyyy";
                DateFormat df = new SimpleDateFormat(pattern);

                jsonConsultation.addProperty("id", consultation.getId());
                String dateAsString = df.format(consultation.getTemps());
                jsonConsultation.addProperty("date", dateAsString);
                jsonConsultation.addProperty("medium", consultation.getMedium().getDenomination());
                jsonConsultation.addProperty("commentaire", consultation.getCommentaire());
                jsonlisteConsultations.add(jsonConsultation);
            }
            container.add("consultations", jsonlisteConsultations); 
        }

        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = response.getWriter();
        Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
        gson.toJson(container, out);
        out.close();
    }

}
