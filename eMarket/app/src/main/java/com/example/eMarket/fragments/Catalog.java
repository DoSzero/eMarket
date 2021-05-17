package com.example.eMarket.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.eMarket.AllCategory;
import com.example.eMarket.R;
import com.example.eMarket.adapter.CategoryAdapter;
import com.example.eMarket.adapter.RecentlyViewedAdapter;
import com.example.eMarket.model.Category;
import com.example.eMarket.model.RecentlyViewed;

import java.util.ArrayList;
import java.util.List;

public class Catalog extends Fragment {

    RecyclerView categoryRecyclerView, recentlyViewedRecycler;
    CategoryAdapter categoryAdapter;
    List<Category> categoryList;
    TextView allCategory;

    RecentlyViewedAdapter recentlyViewedAdapter;
    List<RecentlyViewed> recentlyViewedList;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_catalog, null);

         categoryRecyclerView = (RecyclerView) v.findViewById(R.id.categoryRecycler);
         allCategory = (TextView) v.findViewById(R.id.allCategoryImage);
         recentlyViewedRecycler = (RecyclerView) v.findViewById(R.id.recently_item);


        allCategory.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), AllCategory.class);
                startActivity(intent);
            }
        });

        // adding data to model
        categoryList = new ArrayList<>();
        categoryList.add(new Category(1, R.drawable.computer));
        categoryList.add(new Category(2, R.drawable.laptop));
        categoryList.add(new Category(3, R.drawable.phone));
        categoryList.add(new Category(4, R.drawable.electronics));
        categoryList.add(new Category(5, R.drawable.home_and_kitchen));
        categoryList.add(new Category(6, R.drawable.male_dress));
        categoryList.add(new Category(7, R.drawable.female_dress));
        categoryList.add(new Category(8, R.drawable.sport));
        categoryList.add(new Category(9, R.drawable.toys));
        categoryList.add(new Category(10, R.drawable.milk_bottle));


        setCategoryRecycler(categoryList);

        // adding data to model
        recentlyViewedList = new ArrayList<>();
        recentlyViewedList.add(new RecentlyViewed("Ноутбук Acer Nitro 5 AN517-51-51S3 Shale Black (NH.Q5CEU.011)", "Laptops", "Description Acer Nitro 5", "700", R.drawable.test_image1));
        recentlyViewedList.add(new RecentlyViewed("XIAOMI Redmi Note 8 Pro 6/64GB Mineral Grey" , "Smartphones","Description XIAOMI Redmi Note 8 Pro" , "350", R.drawable.test_image2));
        setRecentlyViewedRecycler(recentlyViewedList);



        return v;
    }

    private void setCategoryRecycler(List<Category> categoryDataList) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        categoryRecyclerView.setLayoutManager(layoutManager);
        categoryAdapter = new CategoryAdapter(getContext(),categoryDataList);
        categoryRecyclerView.setAdapter(categoryAdapter);
    }

    private void setRecentlyViewedRecycler(List<RecentlyViewed> recentlyViewedDataList) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recentlyViewedRecycler.setLayoutManager(layoutManager);
        recentlyViewedAdapter = new RecentlyViewedAdapter(getContext(),recentlyViewedDataList);
        recentlyViewedRecycler.setAdapter(recentlyViewedAdapter);
    }

}
