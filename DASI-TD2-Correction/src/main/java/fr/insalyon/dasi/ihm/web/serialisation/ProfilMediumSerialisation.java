package fr.insalyon.dasi.ihm.web.serialisation;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import fr.insalyon.dasi.metier.modele.Medium;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author DASI Team
 */
public class ProfilMediumSerialisation extends Serialisation {

    @Override
    public void serialiser(HttpServletRequest request, HttpServletResponse response) throws IOException {

        Medium medium = (Medium)request.getAttribute("medium");
        
        JsonObject container = new JsonObject();

        Boolean connexion = (medium != null);

        if (medium != null) {
            JsonObject jsonMedium = new JsonObject();
            jsonMedium.addProperty("denomination", medium.getDenomination());
            jsonMedium.addProperty("genre", medium.getGenre());
            jsonMedium.addProperty("presentation", medium.getPresentation());
            jsonMedium.addProperty("type", medium.getType());

            container.add("medium", jsonMedium);
        }

        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = response.getWriter();
        Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
        gson.toJson(container, out);
        out.close();
    }

}
