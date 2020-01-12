package com.finalproject.starbucksordering.admin.DrinkManage;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.finalproject.starbucksordering.R;
import com.finalproject.starbucksordering.a.model.Drink;
import com.finalproject.starbucksordering.a.model.DrinkLab;
import com.finalproject.starbucksordering.a.model.Type;
import com.finalproject.starbucksordering.a.model.TypeLab;
import com.finalproject.starbucksordering.admin.AdminFuncActivity;

import java.net.URI;
import java.util.List;
import java.util.UUID;

public class ShowDrinksListFragment extends Fragment  {
    private List<Type> types;
    private List<Drink> drinks;
    private RecyclerView mTypeRecyclerView;
    private RecyclerView mDrinkRecyclerView;
    private ImageButton mAddDrinkButton;

    private TypeAdapter mTypeAdapter;
    private DrinkAdapter mDrinkAdapter;

    private String selectedtype;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_admin_show_drink_list, container, false);

        mTypeRecyclerView = (RecyclerView) view.findViewById(R.id.admin_drink_list_middle_col_left);
        mTypeRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        mDrinkRecyclerView = (RecyclerView) view.findViewById(R.id.admin_drink_list_middle_col_right);
        mDrinkRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        updateTypeUI();
        updateDrinkUI();

        mAddDrinkButton = (ImageButton) view.findViewById(R.id.admin_to_add_drink_btn);
        mAddDrinkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), AddDrinkActivity.class);
                startActivity(intent);
            }
        });

       mDrinkAdapter.setOnItemClickListener(new OnItemClickListener() {
           @Override
           public void onItemLongClick(View view, final int position) {
               final PopupMenu popupMenu = new PopupMenu(getActivity(),view);
               popupMenu.getMenuInflater().inflate(R.menu.popup_menu_item, popupMenu.getMenu());

               popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                   @Override
                   public boolean onMenuItemClick(MenuItem menuItem) {
                       if(menuItem.getItemId() == R.id.removeItem){
                           DrinkLab drinkLab = DrinkLab.get(getActivity());
                           String name = drinks.get(position).getName();
                           drinkLab.deleteDrink(name);
                           Toast.makeText(getActivity(), "删除成功", Toast.LENGTH_SHORT).show();
                           drinks.remove(position);
                           mDrinkAdapter.notifyItemRemoved(position);
                       }
                       else if(menuItem.getItemId() == R.id.updateItem){

                       }
                       return false;
                   }
               });
               popupMenu.show();
           }
       });


        return view;
    }

    // Type Holder
    private class TypeHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private Type mType;

        private TextView mTypeTextView;

        public TypeHolder(LayoutInflater inflater, ViewGroup parent){
            super(inflater.inflate(R.layout.fragment_admin_show_drink_list_item_type, parent, false));
            itemView.setOnClickListener(this);

            mTypeTextView = (TextView) itemView.findViewById(R.id.admin_drink_item_type_name);
        }

        public void bind(Type type){
            mType = type;
            mTypeTextView.setText(mType.getType());
        }

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
    private class DrinkHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private Drink mDrink;
        private ImageView mImageView;
        private TextView mNameTextView;
        private TextView mPriceTextView;

        public DrinkHolder(LayoutInflater inflater, ViewGroup parent){
            super(inflater.inflate(R.layout.fragment_admin_show_drink_list_item_drink, parent, false));
            itemView.setOnClickListener(this);

            mImageView = (ImageView) itemView.findViewById(R.id.admin_drink_item_drink_img);
            mNameTextView = (TextView) itemView.findViewById(R.id.admin_drink_item_drink_name);
            mPriceTextView = (TextView) itemView.findViewById(R.id.admin_drink_item_drink_price);

        }

        public void bind(Drink drink){
            mDrink = drink;
            mImageView.setImageURI(Uri.parse(mDrink.getImage()));

            mNameTextView.setText(mDrink.getName());
            mPriceTextView.setText(mDrink.getPrice().toString());
        }

        @Override
        public void onClick(View view) {
            ShowDrinkDialogFragment showDrinkDialogFragment = new ShowDrinkDialogFragment();
            TextView name = view.findViewById(R.id.admin_drink_item_drink_name);
            Bundle bundle = new Bundle();
            bundle.putString("drink_name", name.getText().toString());
            showDrinkDialogFragment.setArguments(bundle);
            showDrinkDialogFragment.show(getActivity().getSupportFragmentManager(), null);
        }
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

            if(mOnItemClickListener != null){
                holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View view) {
                        mOnItemClickListener.onItemLongClick(holder.itemView, position);
                        return false;
                    }
                });
            }
        }

        @Override
        public int getItemCount() {
            return mDrinks.size();
        }


        private OnItemClickListener mOnItemClickListener;


        public void setOnItemClickListener(OnItemClickListener onItemClickListener){
            this.mOnItemClickListener = onItemClickListener;
        }
    }

    public interface OnItemClickListener{
        void onItemLongClick(View view, int position);
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
