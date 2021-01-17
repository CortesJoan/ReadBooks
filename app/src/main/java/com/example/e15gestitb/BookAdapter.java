package com.example.e15gestitb;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import static com.example.e15gestitb.MainActivity.isReturningFromOtherFragment;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.MissedAttendanceViewHolder> {
    static List<Book> books;

    public BookAdapter(List<Book> books) {
        BookAdapter.books = books;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public MissedAttendanceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.missed_attendance_list_item, parent, false);
        return new MissedAttendanceViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MissedAttendanceViewHolder holder, int position) {
        holder.bindData(books.get(position));
    }

    @Override
    public int getItemCount() {
        return books.size();
    }

    static class MissedAttendanceViewHolder extends RecyclerView.ViewHolder {
        TextView bookTitle;

        TextView author;
        TextView bookStatus;

        TextView starsNumberText;
        RatingBar stars;

        public MissedAttendanceViewHolder(@NonNull final View itemView) {
            super(itemView);
            bookTitle = itemView.findViewById(R.id.bookName);
            bookStatus = itemView.findViewById(R.id.bookStatus);
            author = itemView.findViewById(R.id.author);
            // starsNumberText = itemView.findViewById(R.id.starsNumber);
            stars = itemView.findViewById(R.id.ratingBar);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    MainActivity.isUpdating = true;
                    isReturningFromOtherFragment = true;
                    NavDirections directions = BooksListDirections.bookListToFragment().setBook(books.get(getAdapterPosition())).setPosition(getAdapterPosition());

                    System.out.println("AdapterPosition " + getAdapterPosition());
                    Navigation.findNavController(v).navigate(directions);
                }
            });
        }

        public void bindData(Book book) {
            bookTitle.setText(book.getTitle());
            author.setText(book.getAuthor());
            bookStatus.setText(book.getStatus());

            stars.setRating(book.getNumberOfStars());
        }
    }
}
