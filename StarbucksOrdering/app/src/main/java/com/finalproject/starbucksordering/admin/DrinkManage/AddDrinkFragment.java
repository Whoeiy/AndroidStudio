package com.finalproject.starbucksordering.admin.DrinkManage;

import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.finalproject.starbucksordering.R;
import com.finalproject.starbucksordering.a.model.Drink;
import com.finalproject.starbucksordering.a.model.DrinkLab;
import com.finalproject.starbucksordering.a.model.Type;
import com.finalproject.starbucksordering.a.model.TypeLab;
import com.finalproject.starbucksordering.admin.AdminFuncActivity;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static android.app.Activity.RESULT_CANCELED;

public class AddDrinkFragment extends Fragment {

    //
    protected final int RESULT_TEMP = 0x002;
    public Drink mDrink = new Drink();
    private DrinkLab mDrinkLab;
    private TypeLab mTypeLab;
    // 2.
    private ImageView mDrinkImg;    //头像
    private EditText mDrinkName;    //饮品名称
    private EditText mDrinkPrice;   //饮品价格
    private Spinner mTypeSpinner;   //饮品类型
    private Button mChhoseType;
    private TextView mShowType;
    private EditText mDrinkDetail;
    private Button mAdd;
    private ImageButton mExitImageButton;

    // 1. 实现fragment声明周期方法
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDrink = new Drink();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_admin_add_drink, container, false);
        //通过调用LayoutInflater.inflate()方法传入布局资源ID生成的。
        //第二个参数是视图的父视图，通常需要父视图来正确配置组建。
        //第三个参数告诉布局生成器是否将生成的视图添加给父视图（这里传入了false参数，因为要用代码添加视图）。

        // 2. 在fragment中实例化组件

        // ImageView - Image
        mDrinkImg = (ImageView) v.findViewById(R.id.admin_add_drink_img);
        mDrinkImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BottomDialogFragment btmdialog = new BottomDialogFragment();
                btmdialog.setTargetFragment(AddDrinkFragment.this, RESULT_TEMP);
                btmdialog.show(getActivity().getSupportFragmentManager(), "AddImageBottomDialogFragment");
            }
        });

        // EditText - Name
        mDrinkName = (EditText) v.findViewById(R.id.admin_add_drink_name); //调用View.findViewById(Int)方法
        mDrinkName.addTextChangedListener(new TextWatcher() {  //监听方法
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                //This space intentionally left blank
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {//CharSequence 代表用户输入
                String teststr = s.toString();
                if (teststr.length() == 0) {
                    Toast.makeText(getActivity(), "饮品名称不能为空，请重新输入！", Toast.LENGTH_SHORT)
                            .show();
                } else if (teststr.length() >= 20) {
                    Toast.makeText(getActivity(), "饮品名称不能超过20个字符，请重新输入！", Toast.LENGTH_SHORT)
                            .show();
                } else {
                    mDrink.setName(s.toString());
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                //This one too
            }
        });

        // EditText - Price
        mDrinkPrice = (EditText) v.findViewById(R.id.admin_add_drink_price);
        mDrinkPrice.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String teststr = charSequence.toString();
                if (teststr.length() == 0) {
                    Toast.makeText(getActivity(), "饮品价格不能为空，请重新输入！", Toast.LENGTH_SHORT)
                            .show();
                } else if (!strIsNumber(charSequence)) {
                    Toast.makeText(getActivity(), "饮品价格不能包含非数字字符，请重新输入！", Toast.LENGTH_SHORT)
                            .show();
                } else {
                    mDrink.setPrice(Double.parseDouble(charSequence.toString()));
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        // Spinner - Type
        mTypeLab.get(getActivity());
        mTypeSpinner = (Spinner) v.findViewById(R.id.admin_add_drink_type_spinner);

        List<Type> types = mTypeLab.get(getContext()).getTypes();

        String arrstr = "";
        for(Type type : types){
            arrstr = arrstr + type.getType() + ",";
        }

        String[] arr = arrstr.split(",");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity().getBaseContext(), R.layout.support_simple_spinner_dropdown_item, arr);

        mTypeSpinner.setAdapter(adapter);

        mTypeSpinner.setOnItemSelectedListener(listener);


        //Detail
        mDrinkDetail = (EditText) v.findViewById(R.id.admin_add_drink_detail);
        mDrinkDetail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String detail = charSequence.toString();
                mDrink.setDetail(detail);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        //Button
//        mDateButton = (Button) v.findViewById(R.id.crime_data);
//        mDateButton.setText(mCrime.getDate().toString());
//        mDateButton.setEnabled(false); //禁用Button按钮

        //Checkbox
//        mSolvedCheckBox = (CheckBox) v.findViewById(R.id.crime_solved);
//        mSolvedCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                mCrime.setSolved(isChecked);
//            }
//        });*/

        //Add TextView Button
        mAdd = (Button) v.findViewById(R.id.admin_add_drink_btn);

        mAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(getActivity(), "信息填写不全，不能为空！", Toast.LENGTH_SHORT).show();
                if(mDrink.getImage() == null || mDrink.getName() == null || mDrink.getPrice() == null
                        || mDrink.getType() == null || mDrink.getDetail() == null){
                    Toast.makeText(getActivity(), "信息填写不全，不能为空！", Toast.LENGTH_SHORT).show();
                }
                else{
                    mDrinkLab = DrinkLab.get(getActivity());
                    List<Drink> selectdrinks = mDrinkLab.getDrinksByName(mDrink.getName());
                    if(selectdrinks.size() != 0){
                        Toast.makeText(getActivity(), "该菜品已存在，请重新填写！", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        mDrinkLab.addDrink(mDrink);
//                    List<String> str = new ArrayList<>();
//                    str.add(mDrink.getId().toString());
//                    str.add(mDrink.getName());
//                    str.add(mDrink.getType());
//                    str.add(mDrink.getPrice().toString());
//                    str.add(mDrink.getDetail());
//                    mDrinkLab.addDrink(str);
                        Toast.makeText(getActivity(), "加入成功！", Toast.LENGTH_SHORT).show();
                        getActivity().getSupportFragmentManager()
                                .beginTransaction()
                                .replace(R.id.fragment_container, new AddDrinkFragment(), null)
                                .addToBackStack(null)
                                .commit();
                    }
                }
            }
        });


        // ImageButton - Exit
        mExitImageButton = (ImageButton) v.findViewById(R.id.admin_add_drink_exit_btn);
        mExitImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), AdminFuncActivity.class);
                startActivity(intent);
            }
        });

        return v;
    }


    public boolean strIsNumber(CharSequence s){

        Pattern pattern = Pattern.compile("[0-9]*.?[0-9]*");
        Matcher matcher = pattern.matcher(s);
        boolean res = matcher.matches();
        return res;
    }

    // 获得DrinkDialogFragment传回的值
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_CANCELED){
            return;
        }
        Bitmap bm=null;
        ContentResolver resolver = getActivity().getContentResolver();
        if(requestCode == RESULT_TEMP){
            String imguristr = data.getStringExtra("photo");
            Uri uri = Uri.parse(imguristr);
//            Log.i("test","uri"+uri);
//            try {
//                bm= MediaStore.Images.Media.getBitmap(resolver,uri);
//                Log.i("test",bm+"");
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            try {
//                bm= BitmapFactory.decodeStream(getActivity().getContentResolver().openInputStream(uri));
//            } catch (FileNotFoundException e) {
//                e.printStackTrace();
//            }
//            mDrinkImg.setImageBitmap(bm);
            mDrinkImg.setImageURI(uri);
            mDrink.setImage(imguristr);

        }
    }

    AdapterView.OnItemSelectedListener listener = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
           String type = adapterView.getItemAtPosition(i).toString();
           mDrink.setType(type);
        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {

        }
    };

}
