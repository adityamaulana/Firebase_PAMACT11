package com.example.test_barang;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class TambahData extends AppCompatActivity {
    EditText edNama, edTelpon;
    Button submitBtn;
    private DatabaseReference database;
    String nm, tlp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_data);

        edNama = findViewById(R.id.editNama);
        edTelpon = findViewById(R.id.editTelpon);

        submitBtn = findViewById(R.id.btnOk);

        database = FirebaseDatabase.getInstance().getReference();

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!(edNama.getText().toString().isEmpty() && edTelpon.getText().toString().isEmpty())) {
                    nm = edNama.getText().toString();
                    tlp = edTelpon.getText().toString();

                    SubmitTeman(new Teman(edNama.getText().toString(), edTelpon.getText().toString()));
                } else {
                    Toast.makeText(getApplicationContext(), "Data Tidak Boleh Kosong", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void SubmitTeman(Teman tmn) {
        database.child("Teman").push().setValue(tmn).addOnSuccessListener(this, new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                edNama.setText("");
                edTelpon.setText("");

                Toast.makeText(getApplicationContext(), "Data Sukses Ditambahkan", Toast.LENGTH_LONG).show();
            }
        });
    }
}
