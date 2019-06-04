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
import android.widget.Toast;

import com.example.murilomarcineiro.stalker.R;
import com.example.murilomarcineiro.stalker.ShowPersonActivity;
import com.example.murilomarcineiro.stalker.data.DAOPerson;
import com.example.murilomarcineiro.stalker.data.DBHelper;
import com.example.murilomarcineiro.stalker.model.Person;

public class RemovePersonDialogFragment extends DialogFragment {
    private static final String PERSON_KEY = "com.example.murilomarcineiro.stalker.PERSON";

    public static RemovePersonDialogFragment newInstance(Person person){
        Bundle args = new Bundle();
        args.putParcelable(PERSON_KEY, person);
        RemovePersonDialogFragment removePersonDialogFragment = new RemovePersonDialogFragment();
        removePersonDialogFragment.setArguments(args);
        return removePersonDialogFragment;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(R.string.remove_person)
                .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Person person = getArguments().getParcelable(PERSON_KEY);
                        DBHelper dbHelper = new DBHelper(getActivity());
                        DAOPerson.remove(dbHelper,person.getId());
                        Intent output = new Intent();
                        output.putExtra(ShowPersonActivity.MODE_KEY, ShowPersonActivity.MODE_REMOVE);
                        output.putExtra(ShowPersonActivity.PERSON_KEY, (Parcelable) person);
                        getActivity().setResult(getActivity().RESULT_OK, output);
                        getActivity().finish();
                        Toast.makeText(getActivity(), getActivity().getString(R.string.removed_person)+person.getFirstName(), Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getActivity(), getActivity().getString(R.string.not_removed_person), Toast.LENGTH_SHORT).show();
                    }
                });
        return builder.create();
    }
}
