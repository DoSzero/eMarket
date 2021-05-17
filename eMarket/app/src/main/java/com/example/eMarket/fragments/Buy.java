package com.example.eMarket.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.eMarket.AllCategory;
import com.example.eMarket.R;
import com.example.eMarket.adapter.RecentlyViewedAdapter;
import com.example.eMarket.model.RecentlyViewed;

import java.util.ArrayList;
import java.util.List;

public class Buy extends Fragment {

    RecyclerView recentlyViewedRecycler;

    RecentlyViewedAdapter recentlyViewedAdapter;
    List<RecentlyViewed> recentlyViewedList;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_buy, null);

        recentlyViewedRecycler = (RecyclerView) v.findViewById(R.id.recently_item);

        // adding data to model
        recentlyViewedList = new ArrayList<>();

        setRecentlyViewedRecycler(recentlyViewedList);

        return v;
    }

    private void setRecentlyViewedRecycler(List<RecentlyViewed> recentlyViewedDataList) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recentlyViewedRecycler.setLayoutManager(layoutManager);
        recentlyViewedAdapter = new RecentlyViewedAdapter(getContext(),recentlyViewedDataList);
        recentlyViewedRecycler.setAdapter(recentlyViewedAdapter);
    }
}
