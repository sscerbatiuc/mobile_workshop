package stockcontrol.dev.workshop;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private final String TAG_MAIN_ACTIVITY = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        setCurrentUserName();
    }

    private void setCurrentUserName(){
        Intent loginPageIntent = getIntent();
        String userName = loginPageIntent.getStringExtra(LoginActivity.USERNAME_INTENT_KEY);
        TextView userNameTxtView = (TextView) findViewById(R.id.twUsername);
        Log.d(TAG_MAIN_ACTIVITY, "Loaded username [" + userName + "]");
        userNameTxtView.setText(userName);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_replenishment) {
            Toast.makeText(getApplicationContext(), "Replenishment selected", Toast.LENGTH_LONG).show();
        } else if (id == R.id.nav_stocktake) {
            Toast.makeText(getApplicationContext(), "Stocktake selected", Toast.LENGTH_LONG).show();
        } else if (id == R.id.nav_receiving) {
            Toast.makeText(getApplicationContext(), "Receiving received", Toast.LENGTH_LONG).show();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void increaseQuantity(View view){
        EditText articleQtyEditText = (EditText) findViewById(R.id.etArticleQty);
        articleQtyEditText.setText(String.valueOf(getArticleQuantity(articleQtyEditText) + 1));
    }

    public void decreaseQuantity(View view){
        EditText articleQtyEditText = (EditText) findViewById(R.id.etArticleQty);
        int currentArticleQty = getArticleQuantity(articleQtyEditText);
        if(currentArticleQty > 0){
            articleQtyEditText.setText(String.valueOf(currentArticleQty - 1));
        }
    }

    private int getArticleQuantity(EditText articleQtyView){
        int articleQty;
        String currentlySetQty = articleQtyView.getText().toString();
        if(!currentlySetQty.isEmpty()){
            articleQty = Integer.parseInt(articleQtyView.getText().toString());
        } else {
            articleQty = 0;
        }
        return articleQty;
    }

    public void receiveArticle(View view){
        EditText articleQtyEditText = (EditText) findViewById(R.id.etArticleQty);
        if(getArticleQuantity(articleQtyEditText) != 0){
            Toast.makeText(getApplicationContext(), "Article received", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(getApplicationContext(), "Plase enter the quantity", Toast.LENGTH_LONG).show();
        }
    }
}
