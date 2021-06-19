package usk.uas.emanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.SQLException;

public class DetailActivity extends AppCompatActivity {

    private DataItem db;
    Button btn_submit, btn_hapus;
    EditText namabarang, harga, satuan;
    Bitmap bitmapPic;
    byte[] bytePic;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_harga);

        Intent bundle = getIntent();

        db = new DataItem(this);
        try {
            db.open();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        init();

        String id = bundle.getStringExtra("id");
        String namabarang = bundle.getStringExtra("namabarang");
        String harga   = bundle.getStringExtra("harga");
        String satuan = bundle.getStringExtra("satuan");




        namabarang.setText(namabarang);
        harga.setText(harga);
        satuan.setText(satuan);

        submit(id);
        hapus(id);

    }

    private void init(){
        namabarang = findViewById(R.id.edit_namabarang);
        harga = findViewById(R.id.edit_harga);
        satuan = findViewById(R.id.edit_satuan);
        btn_hapus = findViewById(R.id.btn_hapus);
        btn_submit = findViewById(R.id.btn_edit_submit);
    }

    private void hapus(String id){
        btn_hapus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.deleteOne(id);
                startActivity(new Intent(DetailActivity.this, MainActivity.class));
                Toast.makeText(DetailActivity.this, "Data ID : "+id + " Terhapus", Toast.LENGTH_LONG).show();

            }
        });

    }
    private void submit(String id){
        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String new_namabarang = namabarang.getText().toString();
                String new_harga = harga.getText().toString();
                String new_satuan = satuan.getText().toString();

                if( TextUtils.isEmpty(namabarang.getText())){
                    Toast.makeText(DetailActivity.this, "Gagal!", Toast.LENGTH_SHORT).show();
                    namabarang.requestFocus();
                    namabarang.setError( "Nama barang tidak boleh kosong!" );

                }else if( TextUtils.isEmpty(harga.getText())){
                    Toast.makeText(DetailActivity.this, "Gagal!", Toast.LENGTH_SHORT).show();
                    harga.requestFocus();
                    harga.setError( "Harga tidak boleh kosong!" );

                }else if( TextUtils.isEmpty(satuan.getText())) {
                    Toast.makeText(DetailActivity.this, "Gagal!", Toast.LENGTH_SHORT).show();
                    satuan.requestFocus();
                    satuan.setError("Jurusan tidak boleh kosong!");

                }else {
                    startActivity(new Intent(DetailActivity.this, MainActivity.class));
                    Toast.makeText(DetailActivity.this, "Data ID : " + id + " Berhasil diperbarui", Toast.LENGTH_LONG).show();
                    db.updateOne(id, new_namabarang, new_harga, new_satuan );
                    bytePic = db.bitmapToByte(bitmapPic);

                }
            }
        });
    }



}