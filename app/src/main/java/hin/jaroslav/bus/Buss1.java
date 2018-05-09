package hin.jaroslav.bus;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class Buss1 extends AppCompatActivity {

    //String time = "0001-01-01-01-01-01-0001"; //TEXT as ISO8601 strings ("YYYY-MM-DD HH:MM:SS.SSS")
    final String nameDB = "'app.db'";
    final String nameTable = "'buss'";

    SQLiteDatabase sqLiteDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buss1);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        dbCreateTable();

        Spinner spinner = (Spinner) findViewById(R.id.whence);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String[] buss_stops = getResources().getStringArray(R.array.buss_stops);
                dbSelect(buss_stops[position]);
                // showMessange(buss_stops[position]);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }

    private void dbCreateTable() {

        sqLiteDatabase = getBaseContext().openOrCreateDatabase(nameDB, MODE_PRIVATE, null);
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS " + nameTable + " (nameBuss TEXT, nameStops TEXT, time TEXT)");

        if (existsTable()) {
            dbFillUp();
        }

    }

    private boolean existsTable() {
        Boolean exists = true;

        Cursor query = sqLiteDatabase.rawQuery("SELECT * FROM " + nameTable + ";", null);

        if (query.moveToFirst()) {
            exists = false;
        }

        return exists;
    }

    private void dbFillUp() {

        sqLiteDatabase.execSQL("INSERT INTO " + nameTable + " VALUES ('№1', 'Автостанція', '10:21');");
        showMessange("Виконаний перший вхід в програму");
        showMessange("База розкладу рухів авобусів заповнена автоматично!");

    }

    private void dbSelect(String autoStopName) {

        Cursor query = sqLiteDatabase.rawQuery("SELECT * FROM " + nameTable + ";", null);

        if (query.moveToFirst()) {
            do {
                showMessange(query.getString(0));
                showMessange(query.getString(1));
                showMessange(query.getString(2));
            }
            while (query.moveToNext());
        }
        query.close();

    }

    public void rozkl_zag(View view) {
        Intent intent = new Intent(this, Buss2.class);
        startActivity(intent);
    }

    private void showMessange(String text) {
        Toast toast = Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT);
        toast.show();
    }

}
