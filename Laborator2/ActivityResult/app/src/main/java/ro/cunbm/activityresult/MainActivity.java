package ro.cunbm.activityresult;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    /* Defining request code */
    private final int REQUEST1 = 1;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /* Defining a click listener for the button "GetResult" */
        OnClickListener listener = new OnClickListener() {
            @Override
            public void onClick(View v) {
                /* Creating an intent object to call the activity ActivityResult */
                Intent i = new Intent("ro.cunbm.activityresult.secondactivity");

                /* Starting the activity ActivityResultDemo */
                startActivityForResult(i, REQUEST1);
            }
        };

        /* Getting a reference to the "GetResult" button from the layout activity_main */
        Button btn = (Button) findViewById(R.id.btn_get_result);

        /* Setting click listener for the button GetResult */
        btn.setOnClickListener(listener);

    }

    /*
     * A callback method, which is executed when the activity that is called
     * from this activity is finished its execution
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        /*
         * requestCode : an integer code passed to the called activity set by caller activity
         * resultCode : an integer code returned from the called activity
         * data : an intent containing data set by the called activity
         */
        if(requestCode==REQUEST1 && resultCode==RESULT_OK){
            Toast.makeText(getBaseContext(),
                    data.getStringExtra("data"),
                    Toast.LENGTH_SHORT).show();
        }
    }
}

