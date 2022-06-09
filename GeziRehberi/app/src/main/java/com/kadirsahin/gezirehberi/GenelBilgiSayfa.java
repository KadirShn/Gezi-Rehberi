package com.kadirsahin.gezirehberi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;
import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
import java.util.List;


public class GenelBilgiSayfa extends AppCompatActivity {
    FirebaseDatabase firebaseDatabase;
    TextView bilgiyerad, bilgiyerucret, bilgiyersure, bilgiyerbilgilendirme,ortgenel;
    ImageView bilgiyerfoto;
    String yerno, Id;
    RecyclerView rvyorum;
    YorumAdapter yorumAdapter;
    List<YorumModel> yorumModelList;
    EditText yorumET;
    RatingBar ratingBar;
    Button oylabtn;
    Float toplampuan;

    Float toplampuanlar, sayi;
    Integer toplamkisi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_genel_bilgi_sayfa);

        bilgiyerad = findViewById(R.id.bilgiyerad);
        bilgiyerucret = findViewById(R.id.bilgiyerucret);
        ortgenel = findViewById(R.id.ortgenel);
        bilgiyersure = findViewById(R.id.bilgiyersure);
        bilgiyerbilgilendirme = findViewById(R.id.bilgiyerbilgilendirme);
        bilgiyerfoto = findViewById(R.id.bilgiyerfoto);
        rvyorum =findViewById(R.id.rvyorum);
        rvyorum.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rvyorum.setLayoutManager(linearLayoutManager);
        yorumModelList = new ArrayList<>();
        yorumAdapter = new YorumAdapter(this, yorumModelList);
        rvyorum.setAdapter(yorumAdapter);
        yorumET = findViewById(R.id.yorumET);
        ratingBar= findViewById(R.id.ratingbar);
        oylabtn = findViewById(R.id.oylabtn);
        toplamkisi=0;



        String isim = getIntent().getStringExtra("yeradi");
        String ucret = getIntent().getStringExtra("yerucret");
        String sure = getIntent().getStringExtra("yersure");
        String bilgi = getIntent().getStringExtra("yerbilgi");
        String img = getIntent().getStringExtra("yerfotograf");
        String yerno = getIntent().getStringExtra("yerno");
        String yerpuanstr = getIntent().getStringExtra("yerpuanstr");
        sayi = getIntent().getFloatExtra("sayi", 0.0f);
        toplampuanlar = getIntent().getFloatExtra("toplampuan", 0.0f);
        toplamkisi = getIntent().getIntExtra("toplamkisi", 0);


        bilgiyerad.setText(isim);
        bilgiyerucret.setText("Ücret: "+ucret);
        bilgiyersure.setText("Saat: "+sure);
        bilgiyerbilgilendirme.setText(bilgi);
        bilgiyerbilgilendirme.setMovementMethod(new ScrollingMovementMethod());
        ortgenel.setText(yerpuanstr);

        ImageButton yorumgonder = findViewById(R.id.yorumbuton);
        yorumgonder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(yorumET.getText().toString().equals("")){
                    Toast.makeText(GenelBilgiSayfa.this,"Yorum Boş Gönderilemez",Toast.LENGTH_LONG).show();
                }
                else
                {
                    yorumkaydetmefonk(yerno);
                }
                InputMethodManager imm = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(yorumET.getWindowToken(), 0);
            }
        });

        Glide.with(bilgiyerfoto.getContext())
                .load(img)
                //.placeholder(com.firebase.ui.database.R.drawable.common_google_signin_btn_icon_dark)
                .error(com.firebase.ui.database.R.drawable.common_google_signin_btn_icon_dark)
                .into(bilgiyerfoto);

        Id = getIntent().getExtras().getString("yerno");
        yorumOku();

        oylabtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = String.valueOf(ratingBar.getRating());
                float f = Float.parseFloat(s);

                toplampuanlar += f;
                toplamkisi += 1;
                sayi = toplampuanlar / toplamkisi;

                Toast.makeText(getApplicationContext(), "Ortalama: "+sayi.toString(), Toast.LENGTH_SHORT).show();

            }
        });

    }

    private void yorumkaydetmefonk(String yerno){
        FirebaseAuth auth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = auth.getCurrentUser();

        EditText etyorum = findViewById(R.id.yorumET);

        String yorumtxt = etyorum.getText().toString();

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference reference = database.getReference("Kullanicilar").child(currentUser.getUid());
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User user = snapshot.getValue(User.class);
                if (user != null && yorumtxt != null) {
                    YorumModel yorumModel = new YorumModel(user.firstName,yorumtxt);
                    FirebaseDatabase.getInstance().getReference("Yorumlar")
                            .child(yerno)
                            .push().setValue(yorumModel).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {

                                    Toast.makeText(GenelBilgiSayfa.this, "yorum yapildi", Toast.LENGTH_LONG).show();
                                    yorumET.setText("");
                                }
                            });
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(GenelBilgiSayfa.this, "hata",
                        Toast.LENGTH_LONG).show();
            }
        });
    }

    private void yorumOku(){
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Yorumlar").child(Id);

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                yorumModelList.clear();

                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                    YorumModel yorumModel = snapshot.getValue(YorumModel.class);
                    yorumModelList.add(yorumModel);
                }
                yorumAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }
    private void puanKaydet(String yerno) {
        DatabaseReference ref = firebaseDatabase.getReference("Kategoriler").child(yerno);

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                KategoriModeli kategoriModeli = new KategoriModeli(toplamkisi, toplampuan, sayi);

                FirebaseDatabase.getInstance().getReference("Puanlar").child(yerno)
                        .setValue(kategoriModeli).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                            }
                        });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(GenelBilgiSayfa.this, "HATA!", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
