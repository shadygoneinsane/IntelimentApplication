package dass.vikeshkumar.intelimentapplication.main;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import dass.vikeshkumar.intelimentapplication.R;
import dass.vikeshkumar.intelimentapplication.networkClass.Api;
import dass.vikeshkumar.intelimentapplication.utility.TransportInfo;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ActivityScenario2 extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private ArrayAdapter<String> adapter;
    private Spinner dropdown;
    private TextView carTransport, trainTransport;
    private List<TransportInfo> transportInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scenario2);
        dropdown = findViewById(R.id.spinner);
        carTransport = findViewById(R.id.CarTransport);
        trainTransport = findViewById(R.id.TrainTransport);
        getHeroes();
    }

    private void getHeroes() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Api api = retrofit.create(Api.class);

        Call<List<TransportInfo>> call = api.getTrandportInfo();

        call.enqueue(new Callback<List<TransportInfo>>() {
            @Override
            public void onResponse(Call<List<TransportInfo>> call, Response<List<TransportInfo>> response) {
                transportInfo = response.body();

                //Creating an String array for the ListView
                String[] locations = new String[transportInfo.size()];

                //looping through all the locations and inserting the names inside the string array
                for (int i = 0; i < transportInfo.size(); i++) {
                    locations[i] = transportInfo.get(i).getName();
                }

                //displaying the string array into list view
                adapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, locations);
                dropdown.setAdapter(adapter);
                dropdown.setOnItemSelectedListener(ActivityScenario2.this);
            }

            @Override
            public void onFailure(Call<List<TransportInfo>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long l) {
        //modeOfTransport.setText(adapterView.getItemAtPosition(pos).toString());
        if (transportInfo != null) {
            carTransport.setText(transportInfo.get(pos).getFromcentral().getCar());
            trainTransport.setText(transportInfo.get(pos).getFromcentral().getTrain());
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
