package spoonaccular;

import io.github.cdimascio.dotenv.Dotenv;
import okhttp3.Request;

public class APIAuthentication {

    private static final Dotenv dotenv = Dotenv.configure().ignoreIfMissing().ignoreIfMalformed().load();

    private APIAuthentication(){
    }

    public static Request.Builder addAuthHeaders(Request.Builder builder){
        return builder
                .get()
                .addHeader("X-RapidAPI-Key", dotenv.get("X-RapidAPI-Key"))
                .addHeader("X-RapidAPI-Host", dotenv.get("X-RapidAPI-Host"));
    }

}
