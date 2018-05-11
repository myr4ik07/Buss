package hin.jaroslav.bus;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

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

        Spinner spinner = findViewById(R.id.whence);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String[] buss_stops = getResources().getStringArray(R.array.buss_stops);
                dbSelect(buss_stops[position]);
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

        query.close();

        return exists;
    }

    private void dbFillUp() {

        sqLiteDatabase.execSQL("INSERT INTO " + nameTable + " VALUES ('№1', '10.20', '0.15хв');");
        sqLiteDatabase.execSQL("INSERT INTO " + nameTable + " VALUES ('№2', '11.20', '1.15хв');");
        sqLiteDatabase.execSQL("INSERT INTO " + nameTable + " VALUES ('№3', '12.20', '2.15хв');");

        sqLiteDatabase.execSQL("INSERT INTO " + nameTable + " VALUES ('№1', '10.20', '0.15хв');");
        sqLiteDatabase.execSQL("INSERT INTO " + nameTable + " VALUES ('№2', '11.20', '1.15хв');");
        sqLiteDatabase.execSQL("INSERT INTO " + nameTable + " VALUES ('№3', '12.20', '2.15хв');");

        sqLiteDatabase.execSQL("INSERT INTO " + nameTable + " VALUES ('№1', '10.20', '0.15хв');");
        sqLiteDatabase.execSQL("INSERT INTO " + nameTable + " VALUES ('№2', '11.20', '1.15хв');");
        sqLiteDatabase.execSQL("INSERT INTO " + nameTable + " VALUES ('№3', '12.20', '2.15хв');");

        sqLiteDatabase.execSQL("INSERT INTO " + nameTable + " VALUES ('№1', '10.20', '0.15хв');");
        sqLiteDatabase.execSQL("INSERT INTO " + nameTable + " VALUES ('№2', '11.20', '1.15хв');");
        sqLiteDatabase.execSQL("INSERT INTO " + nameTable + " VALUES ('№3', '12.20', '2.15хв');");

        sqLiteDatabase.execSQL("INSERT INTO " + nameTable + " VALUES ('№1', '10.20', '0.15хв');");
        sqLiteDatabase.execSQL("INSERT INTO " + nameTable + " VALUES ('№2', '11.20', '1.15хв');");
        sqLiteDatabase.execSQL("INSERT INTO " + nameTable + " VALUES ('№3', '12.20', '2.15хв');");

        sqLiteDatabase.execSQL("INSERT INTO " + nameTable + " VALUES ('№1', '10.20', '0.15хв');");
        sqLiteDatabase.execSQL("INSERT INTO " + nameTable + " VALUES ('№2', '11.20', '1.15хв');");
        sqLiteDatabase.execSQL("INSERT INTO " + nameTable + " VALUES ('№3', '12.20', '2.15хв');");

        sqLiteDatabase.execSQL("INSERT INTO " + nameTable + " VALUES ('№1', '10.20', '0.15хв');");
        sqLiteDatabase.execSQL("INSERT INTO " + nameTable + " VALUES ('№2', '11.20', '1.15хв');");
        sqLiteDatabase.execSQL("INSERT INTO " + nameTable + " VALUES ('№3', '12.20', '2.15хв');");


        showMessange("Виконаний перший вхід в програму");
        showMessange("База розкладу рухів авобусів заповнена автоматично!");

    }

    private void dbSelect(String autoStopName) {

        GridView gridView = findViewById(R.id.result);

        ArrayList <String> listBuss = new ArrayList<>();
        listBuss.add("№ маршруту");
        listBuss.add("Час відправлення");
        listBuss.add("До відправлення");


        Cursor query = sqLiteDatabase.rawQuery("SELECT * FROM " + nameTable + ";", null);

        if (query.moveToFirst()) {
            do {
                listBuss.add(query.getString(0));
                listBuss.add(query.getString(1));
                listBuss.add(query.getString(2));
            }
            while (query.moveToNext());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.list_item, listBuss);
        gridView.setAdapter(adapter);

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
