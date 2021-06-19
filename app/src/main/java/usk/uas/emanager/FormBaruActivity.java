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
    EditText et_npm, et_nama, et_jurusan;

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
        et_jurusan = findViewById(R.id.satuanbarang);
        et_nama = findViewById(R.id.hargabarang);
        et_npm = findViewById(R.id.namabarang);


        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String npm = et_npm.getText().toString();
                String nama = et_nama.getText().toString();
                String jurusan = et_jurusan.getText().toString();

                if( TextUtils.isEmpty(et_npm.getText())){
                    Toast.makeText(FormBaruActivity.this, "Gagal!", Toast.LENGTH_SHORT).show();
                    et_npm.requestFocus();
                    et_npm.setError( "Nama Barang tidak boleh kosong!" );

                }else if( TextUtils.isEmpty(et_nama.getText())){
                    Toast.makeText(FormBaruActivity.this, "Gagal!", Toast.LENGTH_SHORT).show();
                    et_nama.requestFocus();
                    et_nama.setError( "Harga tidak boleh kosong!" );

                }else if( TextUtils.isEmpty(et_jurusan.getText())) {
                    Toast.makeText(FormBaruActivity.this, "Gagal!", Toast.LENGTH_SHORT).show();
                    et_jurusan.requestFocus();
                    et_jurusan.setError("Satuan tidak boleh kosong!");

                }else {
                    db.addItem(new Item(npm, nama, jurusan));
                    Toast.makeText(FormBaruActivity.this, "Data Item telah ditambahkan", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                }
            }
        });
    }
}
