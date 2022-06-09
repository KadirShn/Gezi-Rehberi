package com.kadirsahin.gezirehberi;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyViewHolder extends RecyclerView.ViewHolder {
    TextView yeradi;
    ImageView yerfotograf;
    View view;

    public MyViewHolder(@NonNull View itemView) {
        super(itemView);

        yeradi = itemView.findViewById(R.id.bilgiyerad);
        yerfotograf = itemView.findViewById(R.id.bilgiyerfoto);

        view = itemView;
    }
}
