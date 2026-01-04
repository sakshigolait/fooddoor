package com.example.fooddoor;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class D_Boy_HistoryFragment extends Fragment {

    RecyclerView recyclerToday, recyclerYesterday;
    ArrayList<HistoryModel> todayList, yesterdayList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_d__boy__history, container, false);

        recyclerToday = view.findViewById(R.id.recyclerToday);
        recyclerYesterday = view.findViewById(R.id.recyclerYesterday);

        recyclerToday.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerYesterday.setLayoutManager(new LinearLayoutManager(getContext()));

        todayList = new ArrayList<>();
        yesterdayList = new ArrayList<>();

        // Today Orders (sample)
        todayList.add(new HistoryModel("ACR147856", "Online", "$32.00", "Delivered"));
        todayList.add(new HistoryModel("AWQ145698", "Cash on delivery", "$35.00", "Delivered"));
        todayList.add(new HistoryModel("TRE123654", "Online", "$40.00", "Pending"));

        // Yesterday Orders (sample)
        yesterdayList.add(new HistoryModel("ACR147856", "Online", "$32.00", "Rejected"));
        yesterdayList.add(new HistoryModel("AWQ145698", "Cash on delivery", "$35.00", "Delivered"));

        recyclerToday.setAdapter(new HistoryAdapter(getContext(), todayList));
        recyclerYesterday.setAdapter(new HistoryAdapter(getContext(), yesterdayList));

        return view;
    }
}