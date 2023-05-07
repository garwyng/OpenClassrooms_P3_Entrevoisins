package com.openclassrooms.entrevoisins.ui.neighbour_list;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailActivity extends AppCompatActivity {

    NeighbourApiService service;

    @BindView(R.id.imageViewPhoto)
    ImageView mphoto;

    @BindView(R.id.imageButtonBack)
    ImageButton mbuttonBack;

    @BindView(R.id.floatingActionButtonFavoris)
    FloatingActionButton mfloatingActionButtonFavoris;

    @BindView(R.id.textViewNeighbourName1)
    TextView mNeighbourName1;

    @BindView(R.id.textViewNeighbourName2)
    TextView mNeighbourName2;

    @BindView(R.id.textViewAddress)
    TextView mNeighbourAddress;

    @BindView(R.id.textViewPhoneNumber)
    TextView mNeighbourPhoneNumber;

    @BindView(R.id.textViewSocialProfil)
    TextView mneighbourSocialProfil;

    @BindView(R.id.textViewAboutMeTitre)
    TextView mtextViewAboutMeTitre;

    @BindView(R.id.textViewNeighbourAboutMe)
    TextView mtextViewAboutMe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);
        service = DI.getNeighbourApiService();

        Intent intent = getIntent();

        Neighbour neighbour = intent.getParcelableExtra("neighbour");


        /**
         * set the informations to show to the user
         */
        mNeighbourName2.setText(neighbour.getName());
        mNeighbourName1.setText(neighbour.getName());
        mNeighbourAddress.setText(neighbour.getAddress());
        mNeighbourPhoneNumber.setText(neighbour.getPhoneNumber());
        Glide.with(mphoto.getContext())
                .load(neighbour.getAvatarUrl())
                .fitCenter()
                .into(mphoto);
        mtextViewAboutMe.setText(neighbour.getAboutMe());
        String textSocialProfil = "www.facebook/" + neighbour.getName();
        mneighbourSocialProfil.setText(textSocialProfil);


        mbuttonBack.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();
            }
        });
        if(service.getFavoritesNeighbours().contains(neighbour)){
            mfloatingActionButtonFavoris.setImageResource(R.drawable.ic_star_yellow_24dp);
            }
        mfloatingActionButtonFavoris.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                service.manageFavorite(neighbour);
                mfloatingActionButtonFavoris.setImageResource(R.drawable.ic_star_yellow_24dp);

            }
        });

    }

}