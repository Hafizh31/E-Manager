package usk.uas.emanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.sql.SQLException;

public class FormBaruActivity extends AppCompatActivity {

    private DataItem db;
    Button btn_submit;
    EditText namabarang, harga, satuan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tambahharga);
        db = new DataItem(this);
        try {
            db.open();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        btn_submit = findViewById(R.id.btn_submit);
        satuan = findViewById(R.id.satuanbarang);
        harga = findViewById(R.id.hargabarang);
        namabarang = findViewById(R.id.namabarang);


        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String et_nama = namabarang.getText().toString();
                String et_harga = harga.getText().toString();
                String et_satuan = satuan.getText().toString();

                if( TextUtils.isEmpty(namabarang.getText())){
                    Toast.makeText(FormBaruActivity.this, "Gagal!", Toast.LENGTH_SHORT).show();
                    namabarang.requestFocus();
                    namabarang.setError( "Nama Barang tidak boleh kosong!" );

                }else if( TextUtils.isEmpty(harga.getText())){
                    Toast.makeText(FormBaruActivity.this, "Gagal!", Toast.LENGTH_SHORT).show();
                    harga.requestFocus();
                    harga.setError( "Harga tidak boleh kosong!" );

                }else if( TextUtils.isEmpty(satuan.getText())) {
                    Toast.makeText(FormBaruActivity.this, "Gagal!", Toast.LENGTH_SHORT).show();
                    satuan.requestFocus();
                    satuan.setError("Satuan tidak boleh kosong!");

                }else {
                    db.addItem(new Item(et_nama, et_harga, et_satuan));
                    Toast.makeText(FormBaruActivity.this, "Data Item telah ditambahkan", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                }
            }
        });
    }
}
