package com.example.e15gestitb;

import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MissedAttendanceViewModel extends ViewModel {
    MissedAttendanceListFragment missedAttendanceListFragment;
    List<MissedAttendanceModel> listOfModels = new ArrayList<MissedAttendanceModel>();

    public MissedAttendanceViewModel() {
        if (listOfModels.isEmpty()) {
            String[] modules = MainActivity.status;
            for (int i = 1; i <= 2; i++) {
                MissedAttendanceModel missedAttendanceModel = new MissedAttendanceModel("Book " + i, "", modules[new Random().nextInt(modules.length)], 0);

                listOfModels.add(missedAttendanceModel);

            }

        }
    }

    public List<MissedAttendanceModel> getListOfModels() {
        return listOfModels;
    }

    public void setListOfModels(List<MissedAttendanceModel> listOfModels) {
        this.listOfModels = listOfModels;
    }
}
