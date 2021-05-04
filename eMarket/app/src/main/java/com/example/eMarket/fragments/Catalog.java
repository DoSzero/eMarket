package com.example.eMarket.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.eMarket.AllCategory;
import com.example.eMarket.R;
import com.example.eMarket.adapter.CategoryAdapter;
import com.example.eMarket.model.Category;

import java.util.ArrayList;
import java.util.List;

public class Catalog extends Fragment {

    RecyclerView categoryRecyclerView;
    CategoryAdapter categoryAdapter;
    List<Category> categoryList;
    TextView allCategory;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_catalog, null);

         categoryRecyclerView = (RecyclerView) v.findViewById(R.id.categoryRecycler);
         allCategory = (TextView) v.findViewById(R.id.allCategoryImage);


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

        return v;
    }

    private void setCategoryRecycler(List<Category> categoryDataList) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        categoryRecyclerView.setLayoutManager(layoutManager);
        categoryAdapter = new CategoryAdapter(getContext(),categoryDataList);
        categoryRecyclerView.setAdapter(categoryAdapter);
    }

}
