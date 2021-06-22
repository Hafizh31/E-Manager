package usk.uas.emanager;

import androidx.annotation.StyleRes;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
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
import android.widget.ScrollView;
import android.widget.SearchView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.SQLException;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private DataItem db;
    private SearchView searchView;
    private ScrollView toolbar;
    Button button3;
    Button button2;


    TableRow tb;
    TableRow tr;
    TableLayout tl;
    TableLayout.LayoutParams trParams;
    TextView tv_no, tv_npm, tv_nama, tv_jurusan;
    Button btntmbh;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new DataItem(this);
        tl = findViewById(R.id.tl);
        button2 = findViewById(R.id.button2);
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
                tr = new TableRow(this);
                trParams = new TableLayout.LayoutParams
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
                Button btntmbh = new Button(this);


                tv_namabarang.setText(itm.get_namabarang());
                tv_namabarang.setHeight(128);
                tv_namabarang.setTextSize(25);
                tv_namabarang.setPadding(70, 0, 0, 20);
                tv_namabarang.setTextColor(Color.BLACK);
                tv_namabarang.setBackgroundColor(getResources().getColor(R.color.maroon));

                tv_harga.setText(itm.get_harga());
                tv_harga.setHeight(128);
                tv_harga.setTextSize(25);
                tv_harga.setPadding(40, 0, 0, 20);
                tv_harga.setTextColor(Color.BLACK);
                tv_harga.setBackgroundColor(getResources().getColor(R.color.maroon));

                tv_satuan.setText(itm.get_satuan());
                tv_satuan.setHeight(128);
                tv_satuan.setTextSize(25);
                tv_satuan.setPadding(0, 0, 0, 20);
                tv_satuan.setTextColor(Color.BLACK);
                tv_satuan.setBackgroundColor(getResources().getColor(R.color.maroon));

                btntmbh.setText("+");
                btntmbh.setTextSize(18);
                btntmbh.setTextColor(getResources().getColor(R.color.white));
                btntmbh.setPadding(0,0,0,20);
                btntmbh.setBackgroundColor(getResources().getColor(R.color.maroon));
                btntmbh.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (btntmbh.getText().equals("+")) {
                            btntmbh.setText("-");
                        } else {
                            btntmbh.setText("+");
                        }
                    }
                });





                tr.addView(tv_namabarang);
                tr.addView(tv_harga);
                tr.addView(tv_satuan);
                tr.addView(btntmbh);
                tl.addView(tr);
            }
        }

        Button button3;
        button3=findViewById(R.id.button3);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, FormBaruActivity.class);
                startActivity(i);
            }
        });

        searchView = findViewById(R.id.Search);



        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                List<Item> listSearched = db.searched(s);
                int nomor = 1;
                tl.removeAllViews();
                if(listSearched.isEmpty()){
                    TextView empty = new TextView(MainActivity.this);
                    empty.setText("Belum ada Data Brang");
                    empty.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                    empty.setPadding(0,30,0,0);
                    Log.d("EMP", "Kosong!");
                    tl.addView(empty);

                }else {
                    for (Item itm : listSearched) {
//                        TableRow tr = new TableRow(MainActivity.this);

                        tr = new TableRow(MainActivity.this);
                        trParams = new TableLayout.LayoutParams
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
                        TextView tv_namabarang = new TextView(MainActivity.this);
                        TextView tv_harga = new TextView(MainActivity.this);
                        TextView tv_satuan = new TextView(MainActivity.this);
                        Button btntmbh = new Button(MainActivity.this);


                        tv_namabarang.setText(itm.get_namabarang());
                        tv_namabarang.setHeight(128);
                        tv_namabarang.setTextSize(25);
                        tv_namabarang.setPadding(70, 0, 0, 20);
                        tv_namabarang.setTextColor(Color.BLACK);
                        tv_namabarang.setBackgroundColor(getResources().getColor(R.color.maroon));

                        tv_harga.setText(itm.get_harga());
                        tv_harga.setHeight(128);
                        tv_harga.setTextSize(25);
                        tv_harga.setPadding(40, 0, 0, 20);
                        tv_harga.setTextColor(Color.BLACK);
                        tv_harga.setBackgroundColor(getResources().getColor(R.color.maroon));

                        tv_satuan.setText(itm.get_satuan());
                        tv_satuan.setHeight(128);
                        tv_satuan.setTextSize(25);
                        tv_satuan.setPadding(0, 0, 0, 20);
                        tv_satuan.setTextColor(Color.BLACK);
                        tv_satuan.setBackgroundColor(getResources().getColor(R.color.maroon));

                        btntmbh.setText("+");
                        btntmbh.setTextSize(18);
                        btntmbh.setTextColor(getResources().getColor(R.color.white));
                        btntmbh.setPadding(0,0,0,20);
                        btntmbh.setBackgroundColor(getResources().getColor(R.color.maroon));
                        btntmbh.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if (btntmbh.getText().equals("+")) {
                                    btntmbh.setText("-");
                                } else {
                                    btntmbh.setText("+");
                                }
                            }
                        });






                        tr.addView(tv_namabarang);
                        tr.addView(tv_harga);
                        tr.addView(tv_satuan);
                        tr.addView(btntmbh);
                        tl.addView(tr);
                    }
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return true;
            }
        });
        searchView.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                List<Item> listItem = db.getAllItem();
                int nomor = 1;
                tl.removeAllViews();
                if(listItem.isEmpty()){
                    TextView empty = new TextView(MainActivity.this);
                    empty.setText("Belum ada Data Brang");
                    empty.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                    empty.setPadding(0,30,0,0);
                    Log.d("EMP", "Kosong!");
                    tl.addView(empty);

                }else {
                    for (Item itm : listItem) {
                        tr = new TableRow(MainActivity.this);
                        trParams = new TableLayout.LayoutParams
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
                        TextView tv_namabarang = new TextView(MainActivity.this);
                        TextView tv_harga = new TextView(MainActivity.this);
                        TextView tv_satuan = new TextView(MainActivity.this);
                        Button btntmbh = new Button(MainActivity.this);


                        tv_namabarang.setText(itm.get_namabarang());
                        tv_namabarang.setHeight(128);
                        tv_namabarang.setTextSize(25);
                        tv_namabarang.setPadding(70, 0, 0, 20);
                        tv_namabarang.setTextColor(Color.BLACK);
                        tv_namabarang.setBackgroundColor(getResources().getColor(R.color.maroon));

                        tv_harga.setText(itm.get_harga());
                        tv_harga.setHeight(128);
                        tv_harga.setTextSize(25);
                        tv_harga.setPadding(40, 0, 0, 20);
                        tv_harga.setTextColor(Color.BLACK);
                        tv_harga.setBackgroundColor(getResources().getColor(R.color.maroon));

                        tv_satuan.setText(itm.get_satuan());
                        tv_satuan.setHeight(128);
                        tv_satuan.setTextSize(25);
                        tv_satuan.setPadding(0, 0, 0, 20);
                        tv_satuan.setTextColor(Color.BLACK);
                        tv_satuan.setBackgroundColor(getResources().getColor(R.color.maroon));

                        btntmbh.setText("+");
                        btntmbh.setTextSize(18);
                        btntmbh.setTextColor(getResources().getColor(R.color.white));
                        btntmbh.setPadding(0, 0, 0, 20);
                        btntmbh.setBackgroundColor(getResources().getColor(R.color.maroon));
                        btntmbh.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if (btntmbh.getText().equals("+")) {
                                    btntmbh.setText("-");
                                } else {
                                    btntmbh.setText("+");
                                }
                            }
                        });



                        tr.addView(tv_namabarang);
                        tr.addView(tv_harga);
                        tr.addView(tv_satuan);
                        tr.addView(btntmbh);
                        tl.addView(tr);
                    }
                }
                return true;
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Kalkulator.class));
            }
        });
    }



}