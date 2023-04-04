package com.openclassrooms.entrevoisins.ui.neighbour_list;


import static com.openclassrooms.entrevoisins.service.DummyNeighbourGenerator.getFavorisNeighbour;

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
import com.openclassrooms.entrevoisins.model.Neighbour;

import java.lang.annotation.Annotation;

import butterknife.BindView;
import butterknife.ButterKnife;

public final class DetailActivity extends AppCompatActivity implements BindView {

    @BindView(R.id.imageViewPhoto)
    ImageView mphoto;

    @BindView(R.id.imageButtonBack)
    ImageButton mbuttonBack;

    @BindView(R.id.floatingActionButtonFavoris)
    FloatingActionButton mfloatingActionButtonFavoris;

    @BindView(R.id.textViewNeighbourName1)
    TextView mNeighbourName1;

    @BindView(R.id.textViewNeighbourName2)
    TextView mNeighbourName2 ;

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


    Intent intent = getIntent();

    Neighbour neighbour = intent.getParcelableExtra("NEIGHBOUR");

        if (intent != null) {

            if (neighbour != null) {
                /**
                 * get neighbour informations from intent
                 */
                String neighbourAddress = neighbour.getAddress();
                String neighbourName = neighbour.getName();
                String neighbourPhone = neighbour.getPhoneNumber();
                String neighbourAboutMe = neighbour.getAboutMe();
                String neighbourAvatarUrl = neighbour.getAvatarUrl();

                /**
                 * set the informations to show to the user
                 */

                mNeighbourName2.setText(neighbourName);
                mNeighbourName1.setText(neighbourName);
                mNeighbourAddress.setText(neighbourAddress);
                mNeighbourPhoneNumber.setText(neighbourPhone);
                Glide.with(mphoto.getContext())
                        .load(neighbourAvatarUrl)
                        .fitCenter()
                        .into(mphoto);
                mtextViewAboutMe.setText(neighbourAboutMe);
                String textSocialProfil = "www.facebook/" + neighbourName;
                mneighbourSocialProfil.setText(textSocialProfil);

            }
        }

        mbuttonBack.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();
            }
        });

        mfloatingActionButtonFavoris.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                if (getFavorisNeighbour().contains(neighbour)) {
                    getFavorisNeighbour().remove(neighbour);
                    ;
                } else {
                    getFavorisNeighbour().add(neighbour);
                    ;
                }
            }
        });

    }

    /**
     * @return
     */
    @Override
    public int value() {
        return 0;
    }

    /**
     * @return
     */
    @Override
    public Class<? extends Annotation> annotationType() {
        return null;
    }
}