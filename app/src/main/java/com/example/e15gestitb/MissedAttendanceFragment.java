package com.example.e15gestitb;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

public class MissedAttendanceFragment extends Fragment {
    static Spinner statusList;
    EditText bookTitle;
    EditText author;
    RatingBar stars;
    Button addButton;
    MissedAttendanceModel recover;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.missed_attendance_fragment, container, false);
        bookTitle = view.findViewById(R.id.editTextBookTitle);
        author = view.findViewById(R.id.editTextAuthor);
        stars = view.findViewById(R.id.ratingBar);
        statusList = view.findViewById(R.id.spinner);
        statusList.setSelection(0);
        int positionInList = -1;
        addButton = view.findViewById(R.id.addButton);
        if (MainActivity.isUpdating && getArguments() != null) {
            addButton.setText(R.string.updateButtonText);
            recover = MissedAttendanceListFragmentArgs.fromBundle(getArguments()).getMissedAttendanceModel();
            positionInList = MissedAttendanceListFragmentArgs.fromBundle(getArguments()).getPosition();
            assert recover != null;
            bookTitle.setText(recover.getTitle());
            author.setText(recover.getAuthor());
            statusList.setSelection(recover.getStatusInSpinner());
            stars.setRating(recover.getNumberOfStars());

        }
        final int finalPosition = positionInList;
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                if (!(bookTitle.getText().toString().equals("") || author.getText().toString().equals(""))) {

                    final MissedAttendanceModel result = new MissedAttendanceModel(bookTitle.getText().toString(), author.getText().toString(), statusList.getSelectedItem().toString(), stars.getRating());
                    AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                    builder.setMessage("Book  new info: " + result.toString())
                            .setTitle("Are you sure that you want to update this book?")
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {

                                    NavDirections directions = MissedAttendanceFragmentDirections.actionMissedAttendanceFragmentToMissedAttendanceListFragment3().setMissedAttendanceModel(result).setPosition(finalPosition);
                                    Navigation.findNavController(v).navigate(directions);
                                    bookTitle.setText("");
                                    author.setText("");
                                    stars.setNumStars(0);
                                }
                            })
                            .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {

                                }
                            });
                    final AlertDialog dialog = builder.create();
                    dialog.show();
                } else {
                    Toast.makeText(getActivity(), "Remember that title and author are mandatory", Toast.LENGTH_SHORT).show();
                }
            }
        });
        statusList.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                stars.setIsIndicator(!statusList.getItemAtPosition(position).equals("Read"));
                if (stars.isIndicator()) {
                    stars.setRating(0);
                    Toast.makeText(getActivity(), "You can't set the punctuation without finished the book!", Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        return view;

    }


}
