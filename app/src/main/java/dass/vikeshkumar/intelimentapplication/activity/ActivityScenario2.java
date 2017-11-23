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
import dass.vikeshkumar.intelimentapplication.utility.TransportInfo;
import dass.vikeshkumar.intelimentapplication.utility.Utils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public class ActivityScenario2 extends AppCompatActivity implements AdapterView.OnItemSelectedListener, View.OnClickListener {
    private ArrayAdapter<String> adapter;
    private Spinner dropdown;
    private TextView carTransportTime, trainTransportTime, trainTransportLabel, carTransportLabel;
    private List<TransportInfo> transportInfo;
    private Button navigateBtn;
    private String name;
    private Double latitude, longitude;
    private String BaseMapURI = "http://maps.google.com/maps?q=loc:";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scenario2);
        dropdown = findViewById(R.id.spinner);
        carTransportTime = findViewById(R.id.CarTransport);
        trainTransportTime = findViewById(R.id.TrainTransport);
        navigateBtn = findViewById(R.id.navigateBtn);
        trainTransportLabel = findViewById(R.id.trainTransportLabel);
        carTransportLabel = findViewById(R.id.carTransportLabel);
        if (Utils.isNetworkAvailable(ActivityScenario2.this)) {
            getTransportList();
        } else {
            showToast(getString(R.string.no_network));
        }
    }

    /* For showing error message*/
    private void showToast(String message) {
        String[] str = {message};
        adapter = new ArrayAdapter<>(ActivityScenario2.this, android.R.layout.simple_spinner_dropdown_item, str);
        dropdown.setAdapter(adapter);
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

                String[] locations = new String[transportInfo.size()];

                for (int i = 0; i < transportInfo.size(); i++) {
                    locations[i] = transportInfo.get(i).getName();
                }
                setAdapter(locations);
            }

            @Override
            public void onFailure(Call<List<TransportInfo>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                showToast(getString(R.string.no_network));
            }
        });
    }

    private void setAdapter(final String[] locations) {
        adapter = new ArrayAdapter<>(ActivityScenario2.this, android.R.layout.simple_spinner_dropdown_item, locations);
        dropdown.setAdapter(adapter);
        dropdown.setOnItemSelectedListener(ActivityScenario2.this);
        navigateBtn.setOnClickListener(ActivityScenario2.this);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long l) {
        if (transportInfo != null) {
            if (transportInfo.get(pos).getFromcentral().getCar() != null) {
                carTransportTime.setVisibility(View.VISIBLE);
                carTransportLabel.setVisibility(View.VISIBLE);
                carTransportTime.setText(transportInfo.get(pos).getFromcentral().getCar());
            } else {
                carTransportTime.setVisibility(View.GONE);
                carTransportLabel.setVisibility(View.GONE);
            }

            if (transportInfo.get(pos).getFromcentral().getTrain() != null) {
                trainTransportTime.setVisibility(View.VISIBLE);
                trainTransportLabel.setVisibility(View.VISIBLE);
                trainTransportTime.setText(transportInfo.get(pos).getFromcentral().getTrain());
            } else {
                trainTransportTime.setVisibility(View.GONE);
                trainTransportLabel.setVisibility(View.GONE);
            }

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
            } else {
                showToast(getString(R.string.no_network));
            }
        }
    }

    private void alert() {
        AlertDialog.Builder builder = new AlertDialog.Builder(ActivityScenario2.this);
        builder.setTitle(getString(R.string.map_alert_msg));
        builder.setPositiveButton(R.string.open_map_app, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                String BaseMapURI = "http://maps.google.com/maps?q=loc:";
                String geoUri = BaseMapURI + latitude + "," + longitude + " (" + name + ")";
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(geoUri));
                startActivity(intent);
            }
        });
        builder.setNegativeButton(R.string.open_here, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                Intent intent = new Intent(ActivityScenario2.this, MapsActivity.class);
                intent.putExtra("lat", latitude);
                intent.putExtra("long", longitude);
                intent.putExtra("title", name);
                startActivity(intent);
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public interface Api {
        String BASE_URL = "http://express-it.optusnet.com.au/";

        @GET("sample.json")
        Call<List<TransportInfo>> getTransportInfo();
    }
}
