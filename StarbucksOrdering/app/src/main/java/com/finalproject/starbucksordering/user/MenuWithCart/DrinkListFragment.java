package com.finalproject.starbucksordering.user.MenuWithCart;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.finalproject.starbucksordering.R;
import com.finalproject.starbucksordering.a.model.Drink;
import com.finalproject.starbucksordering.a.model.DrinkLab;

import java.util.List;

public class DrinkListFragment extends Fragment {
    private static final String DIALOG = "wtf";

    // top

    // middle
    private RecyclerView mTypeRecyclerView;
    private RecyclerView mDrinkRecyclerview;
    private DrinkAdapter mAdaper;



    private Button mButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_drink_list, container, false);

        mTypeRecyclerView = (RecyclerView) view.findViewById(R.id.drink_list_middle_col_left);
        mTypeRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        mDrinkRecyclerview = (RecyclerView) view.findViewById(R.id.drink_list_middle_col_right);
        mDrinkRecyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));

        updateUI();

        return view;
    }

    private class TypeHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private Drink mDrink;

        private TextView mTypeTextView;
        private TextView mNumTextView;

        public TypeHolder(LayoutInflater inflater, ViewGroup parent){
            super(inflater.inflate(R.layout.fragment_drink_list_item_type, parent, false));
            itemView.setOnClickListener(this);
            mTypeTextView = (TextView) itemView.findViewById(R.id.drink_item_type_name);
            mNumTextView = (TextView) itemView.findViewById(R.id.drink_item_type_num);
        }

//        public void bind(Drink drink){
//            mDrink = drink;
//            mTypeTextView.setText(mDrink.getName());
//            mDateTextView.setText(mDrink.getDate().toString());
//        }
//
        @Override
        public void onClick(View view){
//            Toast.makeText(getActivity(),
//                    mCrime.getTitle() + " clicked!", Toast.LENGTH_SHORT)
//                    .show();
        }

    }

    private class DrinkHolder extends RecyclerView.ViewHolder{
        private Drink mDrink;
        private ImageView mImageView;
        private TextView mNameTextView;
        private TextView mPriceTextView;

        public DrinkHolder(LayoutInflater inflater, ViewGroup parent){
            super(inflater.inflate(R.layout.fragment_drink_list_item_drink, parent, false));

            mImageView = itemView.findViewById(R.id.drink_item_drink_img);
            mNameTextView = itemView.findViewById(R.id.drink_item_type_name);
            mPriceTextView = itemView.findViewById(R.id.drink_item_drink_price);

        }

//        public void bind(Drink drink){
//            mDrink = drink;
//            mImageView.setBackground();
//        }
    }

    private class DrinkAdapter extends RecyclerView.Adapter<DrinkHolder>{
        private List<Drink> mDrinks;

        public DrinkAdapter(List<Drink> drinks){
            mDrinks = drinks;
        }

        @NonNull
        @Override
        public DrinkHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());

            return new DrinkHolder(layoutInflater, parent);
        }

        @Override
        public void onBindViewHolder(@NonNull DrinkHolder holder, int position) {

        }

        @Override
        public int getItemCount() {
            return 0;
        }
    }


//    @Override
//    public void onPause(){
//        super.onPause();
//
//        DrinkLab.get(getActivity())
//                .updateDrink(mDrink);
//    }


    private void updateUI(){
        DrinkLab drinkLab = DrinkLab.get(getActivity());
        List<Drink> drinks = drinkLab.getDrinks();

        mAdaper = new DrinkAdapter(drinks);
        mDrinkRecyclerview.setAdapter(mAdaper);
    }
}



