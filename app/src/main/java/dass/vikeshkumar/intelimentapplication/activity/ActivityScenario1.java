package dass.vikeshkumar.intelimentapplication.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import dass.vikeshkumar.intelimentapplication.R;
import dass.vikeshkumar.intelimentapplication.fragments.FragmentTopRecyclerItems;
import dass.vikeshkumar.intelimentapplication.fragments.ViewPagerFragment;

/**
 * Created by vikesh on 22-11-2017.
 * Main Activity for Scenario 1
 */
public class ActivityScenario1 extends AppCompatActivity implements FragmentTopRecyclerItems.OnFragmentInteractionListener, View.OnClickListener {
    private TextView item_name_from_list_top;
    private LinearLayout parent_item5;
    private String fragmentName;
    private int referenceForColor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scenario1);

        item_name_from_list_top = findViewById(R.id.item_name_from_list_top);
        parent_item5 = findViewById(R.id.item5);
        Button redBtn = findViewById(R.id.redButton);
        Button bluBtn = findViewById(R.id.blueButton);
        Button greenBtn = findViewById(R.id.greenButton);
        redBtn.setOnClickListener(this);
        bluBtn.setOnClickListener(this);
        greenBtn.setOnClickListener(this);
        redBtn.performClick();

        Fragment fragment = Fragment.instantiate(this, FragmentTopRecyclerItems.class.getName());
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_item1, fragment);

        fragment = ViewPagerFragment.newInstance(0, "");
        fragmentTransaction.replace(R.id.fragment_item2, fragment);
        fragmentTransaction.commit();

        if (savedInstanceState != null) {
            referenceForColor = savedInstanceState.getInt("referenceForColor");
            fragmentName = savedInstanceState.getString("fragmentName");
            parent_item5.setBackgroundColor(referenceForColor);
            item_name_from_list_top.setText(fragmentName);
        }

    }

    @Override
    public void onFragmentInteraction(final String name) {
        //running this action on the activity UI thread.
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                item_name_from_list_top.setText(name);
            }
        });
        fragmentName = name;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.redButton:
                referenceForColor = (getResources().getColor(R.color.red));
                break;

            case R.id.blueButton:
                referenceForColor = (getResources().getColor(R.color.blue));
                break;

            case R.id.greenButton:
                referenceForColor = (getResources().getColor(R.color.green));
                break;
        }
        parent_item5.setBackgroundColor(referenceForColor);
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putInt("referenceForColor", referenceForColor);
        savedInstanceState.putString("fragmentName", fragmentName);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}