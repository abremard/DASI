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

/**
 *
 * @author DASI Team
 */
public class ListerTypeMediumSerialisation extends Serialisation {

    @Override
    public void serialiser(HttpServletRequest request, HttpServletResponse response) throws IOException {

        List<String> listeType = (List<String>)request.getAttribute("listeTypeMedium");
        
        JsonObject container = new JsonObject();

        if (listeType != null) {
            JsonArray jsonlisteType = new JsonArray();
            
            for (String type : listeType) {
                JsonObject jsonType = new JsonObject();
                jsonType.addProperty("type", type);
                jsonlisteType.add(jsonType);
            }
            container.add("typeMediums", jsonlisteType); 
        }

        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = response.getWriter();
        Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
        gson.toJson(container, out);
        out.close();
    }

}
