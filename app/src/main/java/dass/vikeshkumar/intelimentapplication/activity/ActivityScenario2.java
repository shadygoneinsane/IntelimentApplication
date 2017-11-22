package dass.vikeshkumar.intelimentapplication.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import dass.vikeshkumar.intelimentapplication.R;
import dass.vikeshkumar.intelimentapplication.networkClass.Api;
import dass.vikeshkumar.intelimentapplication.utility.TransportInfo;
import dass.vikeshkumar.intelimentapplication.utility.Utils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ActivityScenario2 extends AppCompatActivity implements AdapterView.OnItemSelectedListener, View.OnClickListener {
    private ArrayAdapter<String> adapter;
    private Spinner dropdown;
    private TextView carTransport, trainTransport;
    private List<TransportInfo> transportInfo;
    private Button navigateBtn;
    private String name;
    private Double latitude, longitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scenario2);
        dropdown = findViewById(R.id.spinner);
        carTransport = findViewById(R.id.CarTransport);
        trainTransport = findViewById(R.id.TrainTransport);
        navigateBtn = findViewById(R.id.navigateBtn);

        if (Utils.isNetworkAvailable(ActivityScenario2.this))
            getTransportList();
        else
            showToast("No Network");
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    private void getTransportList() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Api api = retrofit.create(Api.class);

        Call<List<TransportInfo>> call = api.getTransportInfo();

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
                navigateBtn.setOnClickListener(ActivityScenario2.this);
            }

            @Override
            public void onFailure(Call<List<TransportInfo>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long l) {
        //modeOfTransport.setText(adapterView.getItemAtPosition(pos));
        if (transportInfo != null) {
            carTransport.setText(transportInfo.get(pos).getFromcentral().getCar());
            trainTransport.setText(transportInfo.get(pos).getFromcentral().getTrain());
            name = transportInfo.get(pos).getName();
            latitude = Double.valueOf(transportInfo.get(pos).getLocation().getLatitude());
            longitude = Double.valueOf(transportInfo.get(pos).getLocation().getLongitude());
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.navigateBtn) {
            if (transportInfo != null) {
                alert();
            }
        }
    }

    private void alert() {
        AlertDialog.Builder builder = new AlertDialog.Builder(ActivityScenario2.this);
        builder.setMessage("Choose")
                .setTitle("Would you like to view the location inside map app");
        builder.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                String geoUri = "http://maps.google.com/maps?q=loc:" + latitude + "," + longitude + " (" + name + ")";
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(geoUri));
                startActivity(intent);
            }
        });
        builder.setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                Intent intent = new Intent(ActivityScenario2.this, MapsActivity.class);
                intent.putExtra("lat",latitude);
                intent.putExtra("long",longitude);
                intent.putExtra("title",name);

                startActivity(intent);
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();

    }
}