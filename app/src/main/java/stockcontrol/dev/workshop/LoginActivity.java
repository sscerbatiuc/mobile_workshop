package stockcontrol.dev.workshop;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {

    private final String TAG_LOGIN_ACTIVITY = "LoginActivity";

    /**
     * @type: String
     */
    public static String USERNAME_INTENT_KEY = "user_name";

    private String defaultUserName = "default";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    public void authenticateUser(View view) {
        Intent mainPageIntent = new Intent(this, MainActivity.class);
        EditText etUserName = (EditText) findViewById(R.id.etName);
        String providedUserName = etUserName.getText().toString();
        mainPageIntent.putExtra(USERNAME_INTENT_KEY,
                providedUserName.isEmpty() ? defaultUserName : providedUserName );
        startActivity(mainPageIntent);
    }

}
