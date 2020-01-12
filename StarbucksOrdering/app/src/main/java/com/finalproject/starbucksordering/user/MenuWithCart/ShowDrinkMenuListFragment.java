package com.finalproject.starbucksordering.user.MenuWithCart;

import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.finalproject.starbucksordering.R;
import com.finalproject.starbucksordering.a.model.Cart;
import com.finalproject.starbucksordering.a.model.Drink;
import com.finalproject.starbucksordering.a.model.DrinkLab;
import com.finalproject.starbucksordering.a.model.Type;
import com.finalproject.starbucksordering.a.model.TypeLab;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShowDrinkMenuListFragment extends Fragment {
    private List<Type> types;
    private List<Drink> drinks;
    private RecyclerView mTypeRecyclerView;
    private RecyclerView mDrinkRecyclerView;
    private ImageButton mAddDrinkButton;

    //购物车
    private Map<String, Integer> selectedDrinks = new HashMap<>();
    private List<Cart> carts;

    private TypeAdapter mTypeAdapter;
    private DrinkAdapter mDrinkAdapter;

    private String selectedtype;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_user_drink_menu_list, container, false);

        mTypeRecyclerView = (RecyclerView) view.findViewById(R.id.user_drink_menu_list_middle_col_left);
        mTypeRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        mDrinkRecyclerView = (RecyclerView) view.findViewById(R.id.user_drink_menu_list_middle_col_right);
        mDrinkRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        updateTypeUI();
        updateDrinkUI();

//        mAddDrinkButton = (ImageButton) view.findViewById(R.id.admin_to_add_drink_btn);
//        mAddDrinkButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(getActivity(), AddDrinkActivity.class);
//                startActivity(intent);
//            }
//        });


        mDrinkAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View v, String viewname, int position) {
                if(viewname.equals("add")){
                    String tempname = drinks.get(position).getName();
                    if(selectedDrinks.get(tempname) != null){
                        int tempvalue = selectedDrinks.get(tempname);
                        tempvalue++;
                        selectedDrinks.put(tempname, tempvalue);
                        TextView count = (TextView) v.findViewById(R.id.user_drink_menu_list_drink_item_drink_num);
                        count.setText(tempvalue+"");
                    }else{
                        selectedDrinks.put(tempname, 0);
                        ImageButton minusbtn = (ImageButton) v.findViewById(R.id.user_drink_menu_list_drink_item_drink_minus);
                        minusbtn.setVisibility(ImageButton.VISIBLE);
                        TextView count = (TextView) v.findViewById(R.id.user_drink_menu_list_drink_item_drink_num);
                        count.setText("0");
                        count.setVisibility(TextView.VISIBLE);
                    }
                }else if (viewname.equals("minus")){

                }else if (viewname.equals("item")){

                }
            }
        });


        return view;
    }

    // Type Holder
    private class TypeHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private Type mType;

        private TextView mTypeTextView;

        public TypeHolder(LayoutInflater inflater, ViewGroup parent){
            super(inflater.inflate(R.layout.fragment_user_drink_menu_list_item_type, parent, false));
            itemView.setOnClickListener(this);

            mTypeTextView = (TextView) itemView.findViewById(R.id.user_drink_menu_list_drink_item_type_name);
        }

        public void bind(Type type){
            mType = type;
            mTypeTextView.setText(mType.getType());

        }

        //
        @Override
        public void onClick(View view) {
            TextView type = view.findViewById(R.id.admin_drink_item_type_name);
            selectedtype = type.getText().toString();

            DrinkLab drinkLab = DrinkLab.get(getActivity());
            drinks.clear();
            drinks = drinkLab.getDrinksByType(selectedtype);
            mDrinkAdapter = new DrinkAdapter(drinks);
            mDrinkRecyclerView.setAdapter(mDrinkAdapter);
        }
    }

    // Type Adapter
    private class TypeAdapter extends RecyclerView.Adapter<TypeHolder>{
        private List<Type> mTypes;

        public TypeAdapter(List<Type> types){
            mTypes = types;
        }

        @NonNull
        @Override
        public TypeHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            return new TypeHolder(layoutInflater, parent);
        }

        @Override
        public void onBindViewHolder(@NonNull final TypeHolder holder, int position) {
            Type type = mTypes.get(position);
            holder.bind(type);

        }

        @Override
        public int getItemCount() {
            return mTypes.size();
        }
    }


    // Drink Holder
    private class DrinkHolder extends RecyclerView.ViewHolder{
        private Drink mDrink;
        private ImageView mImageView;
        private TextView mNameTextView;
        private TextView mPriceTextView;

        private ImageButton mMinusImageButton;
        private TextView mCountTextView;
        private ImageButton mAddImageButton;


        public DrinkHolder(LayoutInflater inflater, ViewGroup parent){
            super(inflater.inflate(R.layout.fragment_user_drink_menu_list_item_drink, parent, false));

            final OnItemClickListener mOnItemClickListener;

            mImageView = (ImageView) itemView.findViewById(R.id.user_drink_menu_list_drink_item_drink_img);
            mNameTextView = (TextView) itemView.findViewById(R.id.user_drink_menu_list_drink_item_drink_name);
            mPriceTextView = (TextView) itemView.findViewById(R.id.user_drink_menu_list_drink_item_drink_price);

            //增加减少购物车
            mAddImageButton = (ImageButton) itemView.findViewById(R.id.user_drink_menu_list_drink_item_drink_add);
            mCountTextView = (TextView) itemView.findViewById(R.id.user_drink_menu_list_drink_item_drink_num);
            mMinusImageButton = (ImageButton) itemView.findViewById(R.id.user_drink_menu_list_drink_item_drink_minus);

        }

        public void bind(Drink drink){
            mDrink = drink;
            mImageView.setImageURI(Uri.parse(mDrink.getImage()));

            mNameTextView.setText(mDrink.getName());
            mPriceTextView.setText(mDrink.getPrice().toString());

        }

        //
//        @Override
//        public void onClick(View view) {
//            ShowDrinkDialogFragment showDrinkDialogFragment = new ShowDrinkDialogFragment();
//            TextView name = view.findViewById(R.id.admin_drink_item_drink_name);
//            Bundle bundle = new Bundle();
//            bundle.putString("drink_name", name.getText().toString());
//            showDrinkDialogFragment.setArguments(bundle);
//            showDrinkDialogFragment.show(getActivity().getSupportFragmentManager(), null);
//        }



    }

    public interface OnItemClickListener  {
        void onItemClick(View v, String viewname, int position);
    }

    // Drink Adapter
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
        public void onBindViewHolder(@NonNull final DrinkHolder holder, final int position) {
            Drink drink = mDrinks.get(position);
            holder.bind(drink);

            if (mOnItemClickListener != null){
                holder.mAddImageButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mOnItemClickListener.onItemClick(view, "add", position);
                    }
                });

                holder.mMinusImageButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mOnItemClickListener.onItemClick(view, "minus", position);
                    }
                });

                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mOnItemClickListener.onItemClick(view, "item", position);
                    }
                });
            }
        }

        @Override
        public int getItemCount() {
            return mDrinks.size();
        }

        private OnItemClickListener mOnItemClickListener;//声明自定义的接口

        //定义方法并暴露给外面的调用者
        public void setOnItemClickListener(OnItemClickListener  listener) {
            this.mOnItemClickListener  = listener;
        }


    }




    private void updateTypeUI(){
        TypeLab typeLab = TypeLab.get(getActivity());
        types = typeLab.getTypes();

        mTypeAdapter = new TypeAdapter(types);
        mTypeRecyclerView.setAdapter(mTypeAdapter);

    }

    private void updateDrinkUI(){
        DrinkLab drinkLab = DrinkLab.get(getActivity());
        drinks = drinkLab.getDrinksByType("浓缩咖啡");

        mDrinkAdapter = new DrinkAdapter(drinks);
        mDrinkRecyclerView.setAdapter(mDrinkAdapter);

    }


}
