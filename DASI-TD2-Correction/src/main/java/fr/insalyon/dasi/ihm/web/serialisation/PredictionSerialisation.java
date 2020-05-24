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
public class PredictionSerialisation extends Serialisation {

    @Override
    public void serialiser(HttpServletRequest request, HttpServletResponse response) throws IOException {

        List<String> listePrediction= (List<String>)request.getAttribute("predictions");
        
        JsonObject container = new JsonObject();

        JsonObject jsonPrediction = new JsonObject();
        jsonPrediction.addProperty("amour", listePrediction.get(0));
        jsonPrediction.addProperty("sante", listePrediction.get(1));
        jsonPrediction.addProperty("travail", listePrediction.get(2));

        container.add("predictions", jsonPrediction); 

        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = response.getWriter();
        Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
        gson.toJson(container, out);
        out.close();
    }

}
