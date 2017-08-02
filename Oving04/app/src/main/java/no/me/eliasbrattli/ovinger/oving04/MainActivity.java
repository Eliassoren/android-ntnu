package no.me.eliasbrattli.ovinger.oving04;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;

import no.me.eliasbrattli.ovinger.oving04.fragments.*;

import no.me.eliasbrattli.ovinger.oving04.fragments.ListFragment;
import no.me.eliasbrattli.ovinger.oving04.items.Picture;

public class MainActivity extends AppCompatActivity implements ListFragment.FragmentInteractionListener{


    private android.app.FragmentManager fragmentManager;
    private android.app.FragmentTransaction fragmentTransaction;
    private ButtonFragment buttonFragment;
    private ListFragment listFragment;
    private PictureFragment pictureFragment;
    // private Picture picture;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pictureFragment = (PictureFragment)getFragmentManager().findFragmentById(R.id.pictureFragment);
        listFragment =(ListFragment) getFragmentManager().findFragmentById(R.id.listFragment);
        buttonFragment = (ButtonFragment) getFragmentManager().findFragmentById(R.id.buttonFragment);
        buttonFragment.initList(listFragment.getPictures());
    }

    @Override
    public void onFragmentInteraction(Picture picture){
        pictureFragment.setPicture(picture);
    }


}
