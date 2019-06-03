package com.example.murilomarcineiro.stalker.view;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.murilomarcineiro.stalker.R;
import com.example.murilomarcineiro.stalker.model.Person;

public class PeopleListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private TextView tv_firstName;
    private TextView tv_phone;
    private TextView tv_age;
    private Person person;
    private PeopleListAdapter.PersonListener personListener;

    public PeopleListViewHolder(@NonNull View itemView, PeopleListAdapter.PersonListener personListener) {
        super(itemView);
        tv_firstName = (TextView) itemView.findViewById(R.id.tv_firstName);
        tv_phone = (TextView) itemView.findViewById(R.id.tv_phone);
        tv_age = (TextView) itemView.findViewById(R.id.tv_age);
        this.personListener = personListener;
        itemView.setOnClickListener(this);
    }

    public void bind(Person person){
        tv_firstName.setText(person.getFirstName());
        tv_age.setText(Integer.toString(person.getAge()));
        tv_phone.setText(person.getPhone());
        this.person = person;
    }

    @Override
    public void onClick(View v) {
        personListener.onClickPersonListener(this.person);
    }
}
