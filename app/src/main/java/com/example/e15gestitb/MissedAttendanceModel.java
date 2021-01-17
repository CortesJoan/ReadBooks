package com.example.e15gestitb;

import android.os.Parcel;
import android.os.Parcelable;

public class MissedAttendanceModel implements Parcelable {
    public static final Creator<MissedAttendanceModel> CREATOR = new Creator<MissedAttendanceModel>() {
        @Override
        public MissedAttendanceModel createFromParcel(Parcel in) {
            return new MissedAttendanceModel(in);
        }

        @Override
        public MissedAttendanceModel[] newArray(int size) {
            return new MissedAttendanceModel[size];
        }
    };
    String title;
    String author;
    String status;
    float numberOfStars;

    public MissedAttendanceModel(String title, String author, String status, float numberOfStars) {
        this.title = title;
        this.author = author;
        this.status = status;
        this.numberOfStars = numberOfStars;
    }

    protected MissedAttendanceModel(Parcel in) {
        title = in.readString();
        author = in.readString();
        status = in.readString();
        numberOfStars = in.readFloat();
    }

    public static Creator<MissedAttendanceModel> getCREATOR() {
        return CREATOR;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;


    }

    public int getStatusInSpinner() {
        switch (status) {
            case "Want to read":
                return 0;
            case "Reading":
                return 1;
            case "Read":
                return 2;
            default:
                return -1;
        }
    }

    public float getNumberOfStars() {
        return numberOfStars;
    }

    public void setNumberOfStars(float numberOfStars) {
        this.numberOfStars = numberOfStars;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(author);
        dest.writeString(status);
        dest.writeFloat(numberOfStars);
    }

    @Override
    public String toString() {
        return "MissedAttendanceModel{" +
                "bookTitle='" + title + '\'' +
                ", author='" + author + '\'' +
                ", status='" + status + '\'' +
                ", numbersOfStars=" + numberOfStars +
                '}';
    }
}
