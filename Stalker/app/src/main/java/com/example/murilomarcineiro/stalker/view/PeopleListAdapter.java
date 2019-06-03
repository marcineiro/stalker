package com.example.murilomarcineiro.stalker.view;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.murilomarcineiro.stalker.R;
import com.example.murilomarcineiro.stalker.data.DAOPerson;
import com.example.murilomarcineiro.stalker.model.Person;

import java.util.ArrayList;

public class PeopleListAdapter extends RecyclerView.Adapter<PeopleListViewHolder> {

    private ArrayList<Person> peopleList;
    private PersonListener listener;

    public PeopleListAdapter(PersonListener personListener){
        peopleList = DAOPerson.getINSTANCE().getPeople();
        this.listener = personListener;
    }

    public interface PersonListener{
        void onClickPersonListener(Person p);
    }

    @NonNull
    @Override
    public PeopleListViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View view = layoutInflater.inflate(R.layout.vh_people_list,viewGroup, false);
        PeopleListViewHolder peopleListViewHolder = new PeopleListViewHolder(view, this.listener);
        return peopleListViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull PeopleListViewHolder peopleListViewHolder, int i) {
        peopleListViewHolder.bind(peopleList.get(i));
    }

    @Override
    public int getItemCount() {
        return peopleList.size();
    }

}
