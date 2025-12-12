package ro.cunbm.myfirstfragment;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button activityButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // get the reference of Button
        activityButton = (Button) findViewById(R.id.activity_button);
        // perform setOnClickListener event on Button
        activityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // display a message by using a Toast
                Toast.makeText(getApplicationContext(), "Activity's Button",
                Toast.LENGTH_LONG).show();
            }
        });
    }
}