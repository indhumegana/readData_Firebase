package com.example.readdata_firebase;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;


import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView list;
    Button btn;
    private List<String> namelist=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        list=findViewById(R.id.list);
        Button btn=findViewById(R.id.button);

        btn.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {

                FirebaseFirestore.getInstance().collection("my data").addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {

                        namelist.clear();
                        for(DocumentSnapshot s:value){

                            namelist.add(s.getString("name") + ":" + s.getString("birth_year"));

                        }

                        ArrayAdapter adapter=new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_selectable_list_item,namelist);
                        adapter.notifyDataSetChanged();
                        list.setAdapter(adapter);

                    }
                });

            }
       });

    }
}