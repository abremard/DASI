package fr.insalyon.dasi.ihm.web.serialisation;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import fr.insalyon.dasi.metier.modele.Medium;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class StatsDashboardMediumActionSerialisation extends Serialisation {

    @Override
    public void serialiser(HttpServletRequest request, HttpServletResponse response) throws IOException {

        List<Medium> listeMedium = (List<Medium>)request.getAttribute("statsMedium");
        
        JsonObject container = new JsonObject();

        if (listeMedium != null) {
            JsonArray jsonlisteMedium = new JsonArray();
            
            for (Medium medium : listeMedium) {
                JsonObject jsonMedium = new JsonObject();
                jsonMedium.addProperty("id", medium.getId());
                jsonMedium.addProperty("denomination", medium.getDenomination());
                jsonMedium.addProperty("genre", medium.getGenre());
                jsonMedium.addProperty("type", medium.getType());
                jsonMedium.addProperty("nbconsultation", medium.getNbConsultation());
                jsonlisteMedium.add(jsonMedium);
            }
            container.add("mediums", jsonlisteMedium); 
        }

        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = response.getWriter();
        Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
        gson.toJson(container, out);
        out.close();
    }
}