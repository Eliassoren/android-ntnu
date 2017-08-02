package no.me.eliasbrattli.ovinger.oving04.fragments;

import android.app.Fragment;
import android.content.Context;
import android.media.Image;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import no.me.eliasbrattli.ovinger.oving04.R;
import no.me.eliasbrattli.ovinger.oving04.items.Picture;

/**
 * Created by EliasBrattli on 31/10/2016.
 */
public class PictureFragment extends Fragment {
    private ImageView imageView;
    private TextView textView;
    public PictureFragment(){
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.picture_fragment,container,false);
       // findAllViewsById(view);
        return view;
    }
    @Override
    public void onAttach(Context activity){
        super.onAttach(activity);
    }
    @Override
    public void onDetach(){
        super.onDetach();
    }
    public void setPicture(Picture picture){
        try {
            findAllViewsById(getView());
            imageView.setImageResource(picture.getResourceId());
            textView.setText(picture.getDescription());
        }catch (NullPointerException e){
            e.printStackTrace();
        }
    }
    private void findAllViewsById(View view){
        try {
            imageView = (ImageView) view.findViewById(R.id.imageView);
            textView = (TextView) view.findViewById(R.id.imageText);
        }catch (NullPointerException e){
            e.printStackTrace();
        }
    }
}
