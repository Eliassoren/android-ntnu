package no.me.eliasbrattli.ovinger.oving07;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.ArrayList;
import no.me.eliasbrattli.ovinger.oving07.fragments.ListFragment;
import no.me.eliasbrattli.ovinger.oving07.util.FileUtil;
import no.me.eliasbrattli.ovinger.oving07.database.DatabaseManager;

public class MainActivity extends AppCompatActivity {
    private DatabaseManager db;
    private final String TAG = "MainActivity";
    private ListFragment listFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try {
            //listFragment = (ListFragment) getFragmentManager().findFragmentById(R.id.listFragment);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

            db = new DatabaseManager(this);
            db.clean();
            ArrayList<String> entries = FileUtil.readFile("authorsbooks.txt");
            long id;
            for (int i = 0; i < entries.size(); i++) {
                id = db.insert(entries.get(i).split(",")[0].trim(), entries.get(i).split(",")[1].trim());
            }

            ArrayList<String> res1 = db.getAllAuthors();
            ArrayList<String> res2 = db.getAllBooks();
            //   ArrayList<String> res = db.getBooksByAuthor("Mildrid Ljosland");
            //   ArrayList<String> res = db.getAuthorsByBook("Programmering i C++");
            //   ArrayList<String> res = db.getAllBooksAndAuthors();
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.list_layout, res1);
            //listFragment.setListAdapter(adapter);
            //showResults(res);
        } catch (Exception e) {
            String tekst = e.getMessage();
            //TextView t = (TextView)findViewById(R.id.tw1);
            //t.setText(tekst);
            Log.e(TAG, tekst);
        }
    }
    public void showResults(ArrayList<String> list) {
        StringBuffer res = new StringBuffer("");
        for (String s : list)  {
            res.append(s+"\n");
        }
        TextView t = (TextView)findViewById(R.id.tw1);
        int colorid = findColor();
        t.setBackgroundColor(colorid);
        t.setText(res);
    }
   /* public void showResults(ArrayList<String> list) {
        StringBuffer res = new StringBuffer("");
        for (String s : list) {
            res.append(s + "\n");
        }
        TextView t = (TextView) findViewById(R.id.tw1);
        t.setText(res);
    }*/
    public void onClickAuthor(View v) {
        ArrayList<String> res = db.getAllAuthors();
        showResults(res);
    }
    public void onClickTitle(View v) {
        ArrayList<String> res = db.getAllBooks();
        showResults(res);
    }
    int findColor() {
        String key = getString(R.string.color_chosen);
        SharedPreferences sharedPref = PreferenceManager.
                getDefaultSharedPreferences(this);
        String chosen = sharedPref.getString(key, "Nothing");
        long color = Color.BLACK;
        if (!chosen.equals("Nothing")) {
            chosen = chosen.substring(2);
            color = Long.parseLong(chosen, 16);
        }
        return (int)color;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            startActivity(new Intent(this,
                    SettingsActivity.class));
            return true;
        }
        if (id==R.id.action_exit){
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
  /*  @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        MenuItem mi = menu.add(0, 0, 0, R.string.menu_red);
        mi.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
        mi = menu.add(0, 1, 0, R.string.menu_blue);
        mi.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
        mi = menu.add(0, 2, 0, R.string.menu_green);
        mi.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
        return true;
    }*/

    private void setColor(int id) {

    }



}
