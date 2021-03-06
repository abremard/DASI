package fr.insalyon.dasi.ihm.web.serialisation;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import fr.insalyon.dasi.metier.modele.Client;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author DASI Team
 */
public class ProfilClientSerialisation extends Serialisation {

    @Override
    public void serialiser(HttpServletRequest request, HttpServletResponse response) throws IOException {

        Client client = (Client)request.getAttribute("client");
        
        JsonObject container = new JsonObject();

        Boolean connexion = (client != null);
        container.addProperty("connexion", connexion);

        if (client != null) {
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
