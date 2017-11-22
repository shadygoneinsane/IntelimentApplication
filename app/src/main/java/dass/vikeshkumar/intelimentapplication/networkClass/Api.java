package dass.vikeshkumar.intelimentapplication.networkClass;

import java.util.List;

import dass.vikeshkumar.intelimentapplication.utility.TransportInfo;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by vikesh on 22-11-2017.
 */

public interface Api {
    String BASE_URL = "http://express-it.optusnet.com.au/";

    @GET("sample.json")
    Call<List<TransportInfo>> getTrandportInfo();
}
