package dass.vikeshkumar.intelimentapplication.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import dass.vikeshkumar.intelimentapplication.R;

/**
 * Created by vikesh on 23-11-2017.
 * Launcher activity
 */

public class LauncherActivity extends AppCompatActivity {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.launcher);
    }

    public void onClick(View view) {
        Intent launchIntent;
        if (view.getId() == R.id.startScenario1) {
            launchIntent = new Intent(LauncherActivity.this, ActivityScenario1.class);
        } else {
            launchIntent = new Intent(LauncherActivity.this, ActivityScenario2.class);
        }
        startActivity(launchIntent);
    }
}
