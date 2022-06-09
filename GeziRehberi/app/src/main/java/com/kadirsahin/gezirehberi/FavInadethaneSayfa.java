package com.kadirsahin.gezirehberi;

        import androidx.appcompat.app.AppCompatActivity;
        import androidx.recyclerview.widget.LinearLayoutManager;
        import androidx.recyclerview.widget.RecyclerView;
        import android.content.Intent;
        import android.os.Bundle;
        import android.view.View;
        import androidx.annotation.NonNull;
        import android.view.LayoutInflater;
        import android.view.ViewGroup;
        import com.bumptech.glide.Glide;
        import com.firebase.ui.database.FirebaseRecyclerAdapter;
        import com.firebase.ui.database.FirebaseRecyclerOptions;
        import com.google.firebase.database.DatabaseReference;
        import com.google.firebase.database.FirebaseDatabase;
        import com.google.firebase.database.Query;

public class FavInadethaneSayfa extends AppCompatActivity {

    DatabaseReference ref;
    FirebaseRecyclerOptions<KategoriModeli> options;
    FirebaseRecyclerAdapter<KategoriModeli, MyViewHolder> adapter;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fav_inadethane_sayfa);

        ref = FirebaseDatabase.getInstance().getReference().child("Kategoriler").child("Ibadethaneler");

        Query query = FirebaseDatabase.getInstance().getReference("Kategoriler").child("Ibadethaneler").orderByChild("yerpuan").startAt(4);

        recyclerView = findViewById(R.id.rvfav);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        options = new FirebaseRecyclerOptions.Builder<KategoriModeli>().setQuery(query, KategoriModeli.class).build();

        adapter = new FirebaseRecyclerAdapter<KategoriModeli, MyViewHolder>(options){

            @Override
            protected void onBindViewHolder(@NonNull MyViewHolder holder, int position, @NonNull KategoriModeli model) {
                String yeradi = model.getYeradi();
                String yerucret = model.getYerucret();
                String yersure = model.getYersure();
                String yerbilgi = model.getYerbilgi();
                String yerfotograf = model.getYerfotograf();
                String yerno = model.getYerno();
                String yerpuanstr = model.getYerpuanstr();
                Float toplampuanlar = model.getToplampuanlar();
                Integer toplamkisi = model.getToplamkisi();
                Float sayi = model.getSayi();


                holder.view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getApplicationContext(),GenelBilgiSayfa.class);

                        intent.putExtra("yeradi", yeradi);
                        intent.putExtra("yersure", yersure);
                        intent.putExtra("yerucret", yerucret);
                        intent.putExtra("yerbilgi", yerbilgi);
                        intent.putExtra("yerfotograf", yerfotograf);
                        intent.putExtra("yerno",yerno);
                        intent.putExtra("yerpuanstr", yerpuanstr);
                        intent.putExtra("toplamkisi", toplamkisi);
                        intent.putExtra("toplampuanlar", toplampuanlar);
                        intent.putExtra("sayi", sayi);

                        startActivity(intent);
                    }
                });

                holder.yeradi.setText(model.yeradi);

                Glide.with(holder.yerfotograf.getContext())
                        .load(model.getYerfotograf())
                        //.placeholder(com.firebase.ui.database.R.drawable.common_google_signin_btn_icon_dark)
                        .error(com.firebase.ui.database.R.drawable.common_google_signin_btn_icon_dark)
                        .into(holder.yerfotograf);
            }

            @NonNull
            @Override
            public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.eleman, parent, false);

                return new MyViewHolder(v);
            }
        };
        adapter.startListening();
        recyclerView.setAdapter(adapter);
    }
}