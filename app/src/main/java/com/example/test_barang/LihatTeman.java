package com.example.test_barang;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class LihatTeman extends AppCompatActivity {
    private DatabaseReference databaseReference;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<Teman> dataTeman;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lihat_teman);

        recyclerView = findViewById(R.id.rv_utama);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        databaseReference = FirebaseDatabase.getInstance().getReference();

        databaseReference.child("Teman").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull  DataSnapshot snapshot) {
                dataTeman = new ArrayList<>();
                for (DataSnapshot noteDataSnapshot : snapshot.getChildren())
                {
                    Teman teman = noteDataSnapshot.getValue(Teman.class);
                    teman.setKode(noteDataSnapshot.getKey());
                    dataTeman.add(teman);
                }
                adapter = new AdapterTeman(dataTeman, LihatTeman.this);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull  DatabaseError error) {
                System.out.println(error.getDetails() + ""+error.getMessage());
            }
        });
    }
    public static Intent getActIntent(Activity activity)
    {
        return new Intent(activity, LihatTeman.class);
    }
}