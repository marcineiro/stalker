package com.example.murilomarcineiro.stalker.view;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.murilomarcineiro.stalker.R;
import com.example.murilomarcineiro.stalker.ShowPersonActivity;
import com.example.murilomarcineiro.stalker.data.DAOPerson;
import com.example.murilomarcineiro.stalker.data.DBHelper;
import com.example.murilomarcineiro.stalker.model.Person;

public class EditPersonDialogFragment extends DialogFragment {
    private static final String PERSON_KEY = "com.example.murilomarcineiro.stalker.PERSON";

    private EditText et_fn;
    private EditText et_ln;
    private EditText et_age;
    private EditText et_job;
    private EditText et_birthday;
    private EditText et_phone;
    private EditText et_description;

    public static EditPersonDialogFragment newInstance(Person person){
        Bundle args = new Bundle();
        args.putParcelable(PERSON_KEY, person);
        EditPersonDialogFragment editPersonDialogFragment = new EditPersonDialogFragment();
        editPersonDialogFragment.setArguments(args);
        return editPersonDialogFragment;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
        View view = layoutInflater.inflate(R.layout.dialog_fragment_edit_person, null);
        this.et_fn = (EditText) view.findViewById(R.id.et_fn);
        this.et_ln= (EditText) view.findViewById(R.id.et_ln);
        this.et_age= (EditText) view.findViewById(R.id.et_age);
        this.et_job= (EditText) view.findViewById(R.id.et_job);
        this.et_birthday= (EditText) view.findViewById(R.id.et_birthday);
        this.et_phone= (EditText) view.findViewById(R.id.et_phone);
        this.et_description= (EditText) view.findViewById(R.id.et_description);

        final Person person = getArguments().getParcelable(PERSON_KEY);
        this.et_fn.setText(person.getFirstName());
        this.et_ln.setText(person.getLastName());
        this.et_age.setText(Integer.toString(person.getAge()));
        this.et_job.setText(person.getJob());
        this.et_birthday.setText(person.getBirthday());
        this.et_phone.setText(person.getPhone());
        this.et_description.setText(person.getDescription());

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(view)
                .setPositiveButton(R.string.saveKey, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String fn = et_fn.getText().toString();
                        String ln = et_ln.getText().toString();
                        int age = Integer.parseInt(et_age.getText().toString());
                        String job = et_job.getText().toString();
                        String birthday = et_birthday.getText().toString();
                        String phone = et_phone.getText().toString();
                        String description = et_description.getText().toString();
                        if(!fn.isEmpty() && !ln.isEmpty()) {
                            person.setFirstName(fn);
                            person.setLastName(ln);
                            person.setAge(age);
                            person.setJob(job);
                            person.setBirthday(birthday);
                            person.setPhone(phone);
                            person.setDescription(description);
                            DBHelper dbHelper = new DBHelper(getActivity());
                            DAOPerson.update(dbHelper, person);
                            Intent output = new Intent();
                            output.putExtra(ShowPersonActivity.MODE_KEY, ShowPersonActivity.MODE_EDIT);
                            output.putExtra(ShowPersonActivity.PERSON_KEY,(Parcelable) person);
                            getActivity().setResult(getActivity().RESULT_OK, output);
                            getActivity().finish();
                            Toast.makeText(getActivity(), getActivity().getString(R.string.update_contact)+person.getId(), Toast.LENGTH_SHORT).show();
                        }
                        else {
                            Toast.makeText(getActivity(), getActivity().getString(R.string.not_update_contact), Toast.LENGTH_SHORT).show();
                        }
                    }
                })
                .setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getActivity(), getActivity().getString(R.string.not_update_contact), Toast.LENGTH_SHORT).show();
                    }
                });
        return builder.create();
    }
}
