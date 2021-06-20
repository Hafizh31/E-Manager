package usk.uas.emanager;

import androidx.annotation.StyleRes;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.SQLException;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private DataItem db;
    Button button3;



    TableRow tb;
    TextView tv_no, tv_npm, tv_nama, tv_jurusan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new DataItem(this);
        TableLayout tl = findViewById(R.id.tl);
        try {
            db.open();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        List<Item> listItem = db.getAllItem();
        int nomor = 1;

        if(listItem.isEmpty()){
            TextView empty = new TextView(this);
            empty.setText("Belum ada Data Brang");
            empty.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            empty.setPadding(0,30,0,0);
            Log.d("EMP", "Kosong!");
            tl.addView(empty);

        }else {
            for (Item itm : listItem) {
                TableRow tr = new TableRow(this);
                TableLayout.LayoutParams trParams = new TableLayout.LayoutParams
                        (TableLayout.LayoutParams.FILL_PARENT, TableLayout.LayoutParams.WRAP_CONTENT);
                trParams.setMargins(0, 4, 0, 0);
                //            tr.setBackgroundColor(Color.rgb(255,240,255));
                tr.setLayoutParams(trParams);
                tr.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent data = new Intent(MainActivity.this, DetailActivity.class);
                        data.putExtra("nama barang", itm.get_namabarang());
                        data.putExtra("harga", itm.get_harga());
                        data.putExtra("satuan", itm.get_satuan());
                        data.putExtra("id", Integer.toString(itm.get_id()));
                        startActivity(data);
                    }
                });
                TextView tv_namabarang = new TextView(this);
                TextView tv_harga = new TextView(this);
                TextView tv_satuan = new TextView(this);


                tv_namabarang.setText(itm.get_namabarang());
                tv_namabarang.setTextSize(25);
                tv_namabarang.setPadding(100, 0, 0, 20);
                tv_namabarang.setTextColor(Color.BLACK);
                tv_namabarang.setBackgroundColor(getResources().getColor(R.color.maroon));

                tv_harga.setText(itm.get_harga());
                tv_harga.setTextSize(25);
                tv_harga.setPadding(40, 0, 0, 20);
                tv_harga.setTextColor(Color.BLACK);
                tv_harga.setBackgroundColor(getResources().getColor(R.color.maroon));

                tv_satuan.setText(itm.get_satuan());
                tv_satuan.setTextSize(25);
                tv_satuan.setPadding(0, 0, 0, 20);
                tv_satuan.setTextColor(Color.BLACK);
                tv_satuan.setBackgroundColor(getResources().getColor(R.color.maroon));




                tr.addView(tv_namabarang);
                tr.addView(tv_harga);
                tr.addView(tv_satuan);
                tl.addView(tr);
            }
        }

        db.close();

    }



}