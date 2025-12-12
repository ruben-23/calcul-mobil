package ro.cunbm.activityresult;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class SecondActivity extends Activity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        /* Click event listener for the button OK */
        OnClickListener listener = v -> {

            /* Getting a reference to the edittext txt_data of the layout activity_result_layout */
            EditText editText = findViewById(R.id.txt_data);

            /* Creating an intent object to pass data to the caller activity */
            Intent data = new Intent();

            /* Setting data to the intent object */
            data.putExtra("data",editText.getText().toString());

            /* Setting result , which can be retreived from caller activity
             * in the callback method onActivityResult */
            setResult(RESULT_OK, data);

            /* Exits this activity */
            finish();
        };

        /* Getting a reference to the button available in the layout activity_result_layout */
        Button btnOk = findViewById(R.id.btn_ok);

        /* Setting click event listener for the button */
        btnOk.setOnClickListener(listener);
    }
}