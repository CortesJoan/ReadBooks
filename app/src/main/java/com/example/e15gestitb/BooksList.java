package com.example.e15gestitb;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import static com.example.e15gestitb.MainActivity.isReturningFromOtherFragment;

public class BooksList extends Fragment {
    RecyclerView recyclerView;
    BooksViewModel booksViewModel;
    FloatingActionButton addButton;
    BookAdapter adapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        booksViewModel = new ViewModelProvider(this).get(BooksViewModel.class);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.missed_attendance_list_fragment, container, false);
        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        if (getArguments() != null) {
            if (isReturningFromOtherFragment) {
                Book recover;
                recover = BooksListArgs.fromBundle(getArguments()).getBook();
                if (recover != null) {
                    if (MainActivity.isUpdating) {
                        int position = BooksListArgs.fromBundle(getArguments()).getPosition();
                        if (position != -1)
                            booksViewModel.listOfModels.set(position, recover);
                        MainActivity.isUpdating = false;
                    } else {
                        booksViewModel.listOfModels.add(recover);
                        Toast.makeText(getContext(), recover.toString(), Toast.LENGTH_SHORT).show();
                    }
                }
                isReturningFromOtherFragment = false;
            }
        }
        adapter = new BookAdapter(booksViewModel.listOfModels);
        recyclerView.setAdapter(adapter);
        addButton = view.findViewById(R.id.addButton);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isReturningFromOtherFragment = true;
                MainActivity.isUpdating = false;
                NavDirections directions = BooksListDirections.bookListToFragment();
                Navigation.findNavController(v).navigate(directions);
            }
        });
        return view;
    }
}
