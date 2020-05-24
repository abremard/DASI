package fr.insalyon.dasi.ihm.web.serialisation;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import fr.insalyon.dasi.metier.modele.Client;
import fr.insalyon.dasi.metier.modele.Consultation;
import fr.insalyon.dasi.metier.modele.Medium;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ConsultationEmployeSerialisation extends Serialisation {

    @Override
    public void serialiser(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String pattern = "MM/dd/yyyy";
        DateFormat df = new SimpleDateFormat(pattern);

        Consultation consultation = (Consultation)request.getAttribute("consultationEmploye");
        
        JsonObject container = new JsonObject();

        if (consultation != null) {
            JsonObject jsonConsultation = new JsonObject();
            jsonConsultation.addProperty("id", consultation.getId());
            String dateAsString = df.format(consultation.getTemps());
            jsonConsultation.addProperty("date", dateAsString);

            container.add("consultation", jsonConsultation); 

            Medium medium = consultation.getMedium();

            JsonObject jsonMedium = new JsonObject();
            jsonMedium.addProperty("id", medium.getId());
            jsonMedium.addProperty("denomination", medium.getDenomination());
            jsonMedium.addProperty("genre", medium.getGenre());
            jsonMedium.addProperty("presentation", medium.getPresentation());
            jsonMedium.addProperty("type", medium.getType());

            container.add("medium", jsonMedium);

            Client client = consultation.getClient();

            JsonObject jsonClient = new JsonObject();
            jsonClient.addProperty("id", client.getId());
            jsonClient.addProperty("adresse", client.getAdresse());
            jsonClient.addProperty("animalTotem", client.getAnimalTotem());
            jsonClient.addProperty("couleurBonheur", client.getCouleurBonheur());
            jsonClient.addProperty("dateNaissance", client.getDateDeNaissance().toString());
            jsonClient.addProperty("mail", client.getMail());
            jsonClient.addProperty("nom", client.getNom());
            jsonClient.addProperty("prenom", client.getPrenom());
            jsonClient.addProperty("signeAstro", client.getSigneAstro());
            jsonClient.addProperty("signeZodiaque", client.getSigneZodiaque());
            jsonClient.addProperty("telephone", client.getTelephone());

            container.add("client", jsonClient);
        }

        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = response.getWriter();
        Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
        gson.toJson(container, out);
        out.close();
    }
}