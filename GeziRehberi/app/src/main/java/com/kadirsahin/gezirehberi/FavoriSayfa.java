package com.kadirsahin.gezirehberi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class FavoriSayfa extends AppCompatActivity {

    TextView hosgeldibolge;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favori_sayfa);

        // Initialize Firebase Auth
        FirebaseAuth auth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = auth.getCurrentUser();
        if (currentUser == null) {
            Intent intent = new Intent(this, GirisEkran.class);
            startActivity(intent);
            finish();
            return;
        }
        hosgeldibolge = findViewById(R.id.baslikmetin);

        ImageButton parkButon = findViewById(R.id.parkbtnf);
        parkButon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(FavoriSayfa.this,FavParklarSayfa.class));

            }
        });
        ImageButton kutupButon = findViewById(R.id.kutuphanebtnf);
        kutupButon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(FavoriSayfa.this,FavKutuphaneSayfa.class));

            }
        });
        ImageButton ibadetButon = findViewById(R.id.ibadethanebtnf);
        ibadetButon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(FavoriSayfa.this,FavInadethaneSayfa.class));

            }
        });
        ImageButton marketButon = findViewById(R.id.marketbtn);
        marketButon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(FavoriSayfa.this,FavMarketSayfa.class));

            }
        });
        ImageButton otelButon = findViewById(R.id.otelbtnf);
        otelButon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(FavoriSayfa.this,FavOtelSayfa.class));

            }
        });
        ImageButton otoparkButon = findViewById(R.id.otoparkbtnf);
        otoparkButon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(FavoriSayfa.this,FavOtoparkSayfa.class));

            }
        });
        ImageButton tarihiyerbuton = findViewById(R.id.tarihiyerbtnf);
        tarihiyerbuton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(FavoriSayfa.this,FavTarihiyerlerSayfa.class));

            }
        });

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference reference = database.getReference("Kullanicilar").child(currentUser.getUid());
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User user = snapshot.getValue(User.class);
                if (user != null) {
                    hosgeldibolge.setText("HOŞGELDİN\n" + user.firstName);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }

}