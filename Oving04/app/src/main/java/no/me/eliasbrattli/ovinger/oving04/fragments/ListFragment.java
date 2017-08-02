package no.me.eliasbrattli.ovinger.oving04.fragments;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import no.me.eliasbrattli.ovinger.oving04.R;
import no.me.eliasbrattli.ovinger.oving04.items.Picture;
/**
 * Created by EliasBrattli on 31/10/2016.
 */
public class ListFragment extends Fragment implements AdapterView.OnItemClickListener{
    private ArrayList<Picture> titleList;
    private ArrayAdapter<Picture> listAdapter;
    private ListView listView;
    private FragmentInteractionListener interactionListener;
    public ListFragment(){

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.list_fragment,container,false);
        initListView(view);
        fillList();
        return view;
    }
    @Override
    public void onAttach(Context activity){
        super.onAttach(activity);
        try{
            interactionListener = (FragmentInteractionListener) activity;
        }catch (ClassCastException e){
            throw new ClassCastException(activity.toString()+" must implement FragmentInteractionListener");
        }
    }
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id){
        try{
            Picture item = listAdapter.getItem(position);
            interactionListener.onFragmentInteraction(item);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @Override
    public void onDetach(){
        super.onDetach();
        interactionListener = null;
    }
    private void initListView(View view){
        try {
            listView = (ListView) view.findViewById(R.id.listView);
            listView.setEnabled(true);
            titleList = new ArrayList<>();
            listAdapter = new ArrayAdapter<Picture>(getActivity(), android.R.layout.simple_list_item_1, titleList);
            listView.setAdapter(listAdapter);
            listView.setChoiceMode(AbsListView.CHOICE_MODE_SINGLE);
            listView.setOnItemClickListener(this);
        }catch (NullPointerException e){
            e.printStackTrace();
        }
    }
    private void fillList(){
        listAdapter.add(new Picture(R.drawable.eric1,"Eric 1","Eric med bart"));
        listAdapter.add(new Picture(R.drawable.eric2,"Eric 2","Eric med telefon"));
        listAdapter.add(new Picture(R.drawable.eric3,"Eric 3","Eric scorer i beer pong"));
    }
    public interface FragmentInteractionListener {
        void onFragmentInteraction(Picture picture);
    }
    public ArrayList<Picture> getPictures(){
       return titleList;
    }
}
