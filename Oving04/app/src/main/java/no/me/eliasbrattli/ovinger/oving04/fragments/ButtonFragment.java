package no.me.eliasbrattli.ovinger.oving04.fragments;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

import no.me.eliasbrattli.ovinger.oving04.R;
import no.me.eliasbrattli.ovinger.oving04.items.Picture;

/**
 * Created by EliasBrattli on 11/11/2016.
 */
public class ButtonFragment extends Fragment implements View.OnClickListener{
    private final String TAG = "ButtonFragment";
    private Button nextButton;
    private Button prevButton;
    private int currentPos = 0;
    private ListFragment.FragmentInteractionListener interactionListener;
    private List<Picture> pictures;
    public ButtonFragment(){

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.button_fragment,container,false);
        pictures = new ArrayList<>();
        findAllViewsById(view);
        enableAllViews();
        setAllOnClickListeners();
        return view;
    }
    @Override
    public void onAttach(Context activity){
        super.onAttach(activity);
        try{
            interactionListener = (ListFragment.FragmentInteractionListener) activity;
        }catch (ClassCastException e){
            throw new ClassCastException(activity.toString()+" must implement FragmentInteractionListener");
        }
    }
    @Override
    public void onDetach(){
        super.onDetach();
    }
    @Override
    public void onClick(View view){
        Picture currentPicture;
        try{
            if(currentPos >= pictures.size()) currentPos = 0;
            else if(currentPos < 0) currentPos = pictures.size()-1;
            switch (view.getId()){
                default:break;

                case R.id.nextButton:
                    Log.i(TAG,"Next");
                    currentPicture = pictures.get(currentPos++);
                    interactionListener.onFragmentInteraction(currentPicture);
                    break;
                case R.id.prevButton:
                    Log.i(TAG,"Prev");
                    currentPicture = pictures.get(currentPos--);
                    interactionListener.onFragmentInteraction(currentPicture);
                    break;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void initList(ArrayList<Picture> list){
        try {
            if(!list.isEmpty()) {
                for (int i = 0; i < list.size(); i++) {
                    pictures.add( list.get(i));
                }
            }
        }catch (NullPointerException e){
            e.printStackTrace();
        }
    }

    private void findAllViewsById(View view){
        nextButton = (Button)view.findViewById(R.id.nextButton);
        prevButton = (Button)view.findViewById(R.id.prevButton);
    }
    private void enableAllViews(){
        nextButton.setEnabled(true);
        prevButton.setEnabled(true);
    }
    private void setAllOnClickListeners(){
        nextButton.setOnClickListener(this);
        prevButton.setOnClickListener(this);
    }
}
