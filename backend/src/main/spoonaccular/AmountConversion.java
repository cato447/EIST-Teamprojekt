package spoonaccular;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.cdimascio.dotenv.Dotenv;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import spoonaccular.models.amount_conversion.ConvertedAmount;

import java.io.IOException;

public class AmountConversion {

    private static final OkHttpClient client =  new OkHttpClient();
    private static final Dotenv dotenv = Dotenv.configure().ignoreIfMissing().ignoreIfMalformed().load();

    private AmountConversion(){
    }

    public static double convertAmount(String ingrdientName, Double sourceAmount, String sourceUnit, String targetUnit) throws IOException {
        Request request = APIAuthentication.addAuthHeaders(
                new Request.Builder()
                        .url("https://" + dotenv.get("X-RapidAPI-Host") +
                        "recipes/convert?ingredientName=" + ingrdientName +
                        "&targetUnit=" + targetUnit +
                        "&sourceUnit=" + sourceUnit +
                        "&sourceAmount=" + sourceAmount)
        ).build();
        Response response = client.newCall(request).execute();
        String responseString = response.body().string();
        return new ObjectMapper().readValue(responseString, ConvertedAmount.class).getTargetAmount();
    }

}
