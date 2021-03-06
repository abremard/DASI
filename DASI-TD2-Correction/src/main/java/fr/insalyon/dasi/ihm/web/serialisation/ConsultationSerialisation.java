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

public class ConsultationSerialisation extends Serialisation {

    @Override
    public void serialiser(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String pattern = "MM/dd/yyyy";
        DateFormat df = new SimpleDateFormat(pattern);

        Consultation consultation = (Consultation)request.getAttribute("consultation");
        
        JsonObject container = new JsonObject();

        if (consultation != null) {
            JsonObject jsonConsultation = new JsonObject();
            jsonConsultation.addProperty("id", consultation.getId());
            jsonConsultation.addProperty("medium", consultation.getMedium().getDenomination());
            String dateAsString = df.format(consultation.getTemps());
            jsonConsultation.addProperty("date", dateAsString);
            container.add("consultation", jsonConsultation); 
        }

        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = response.getWriter();
        Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
        gson.toJson(container, out);
        out.close();
    }
}