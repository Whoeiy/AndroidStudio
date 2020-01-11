package com.finalproject.starbucksordering.admin.UsersManage;

import android.os.Bundle;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.finalproject.starbucksordering.R;
import com.finalproject.starbucksordering.a.model.Drink;
import com.finalproject.starbucksordering.a.model.User;
import com.finalproject.starbucksordering.a.model.UserLab;

import java.util.List;

public class ShowUsersListFragment extends Fragment {
    private RecyclerView mUserRecyclerView;
    private UserAdapter mAdapter;


    // 配置视图
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_admin_show_user_list, container, false);

        mUserRecyclerView = (RecyclerView) view.findViewById(R.id.admin_show_user_recycler_view);
        mUserRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));     //支持以竖直列表的形式展示列表项

        updateUI();

        return view;
    }

    // 定义ViewHolder内部类，实例化使用项布局
    private class UserHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private User mUser;

        private TextView mUsernameTextView;
        private TextView mNameTextView;
        private ImageButton mDeleteImageButton;

        public UserHolder(LayoutInflater inflater, ViewGroup parent){
            super(inflater.inflate(R.layout.fragment_admin_show_user_list_item, parent, false));
            itemView.setOnClickListener(this);

            mUsernameTextView = (TextView) itemView.findViewById(R.id.admin_show_user_list_item_Username);
            mNameTextView = (TextView) itemView.findViewById(R.id.admin_show_user_list_item_Name);
            mDeleteImageButton = (ImageButton) itemView.findViewById(R.id.admin_show_user_list_item_DeleteBtn);
            mDeleteImageButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    UserLab userLab = UserLab.get(getActivity());
                    String username = ((TextView) itemView.findViewById(R.id.admin_show_user_list_item_Username)).getText().toString();
                    userLab.deleteUser(username);
                }
            });
        }

        //方法：bind()：有新的user要在UserHolder中显示，都要调用它一次。
        public void bind(User user){
            mUser = user;
            mUsernameTextView.setText(mUser.getUsername());
            mNameTextView.setText(mUser.getName());
        }

        @Override
        public void onClick(View view){
            Toast.makeText(getActivity(),"clicked",Toast.LENGTH_SHORT).show();
        }

    }

    // 创建Adapter控制器
    private class UserAdapter extends RecyclerView.Adapter<UserHolder>{
        private List<User> mUsers;

        public UserAdapter(List<User> users){
            mUsers = users;
        }

        @NonNull
        @Override
        public UserHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            return new UserHolder(layoutInflater, parent);
        }

        @Override
        public void onBindViewHolder(@NonNull UserHolder holder, int position) {
            User user = mUsers.get(position);
            holder.bind(user);
        }

        @Override
        public int getItemCount() {
            return mUsers.size();
        }
    }

    //方法：updateUI()：创建CrimeAdapter，设置给RecyclerView
    private void updateUI(){
        UserLab userLab = UserLab.get(getActivity());
        List<User> users = userLab.getUsers();

        mAdapter = new UserAdapter(users);
        mUserRecyclerView.setAdapter(mAdapter);

    }

}
