package com.example.tranning.screen.main;

import android.content.Context;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tranning.R;
import com.example.tranning.data.userDataSourceRemote.RetrofitClient;
import com.example.tranning.model.User;

import java.util.ArrayList;
import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


@AndroidEntryPoint
public class MainFragment extends Fragment {
    private ArrayList<User> mUsers;
    private RecyclerView mRecyclerUser;
    private UserAdapter mUserAdapter;
    private MainViewModel mainViewModel;

    public MainFragment() {

    }


    public static MainFragment newInstance(String param1, String param2) {
        MainFragment fragment = new MainFragment();
        Bundle args = new Bundle();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainViewModel = new ViewModelProvider(this).get(MainViewModel.class);
        if (getArguments() != null) {

        }
        final OnBackPressedCallback callback = new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {

            }
        };
        requireActivity().getOnBackPressedDispatcher().addCallback(this, callback);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRecyclerUser = view.findViewById(R.id.recyclerViewUSer);
        mUsers = new ArrayList<>();

        mUserAdapter = new UserAdapter(getActivity(),mUsers);
        getSuperHeroes(getActivity());
        List<User> usersLocal = mainViewModel.getAll();
        //usersLocal.forEach(it -> Log.d("XXX", it.getName()));


    }

    private void getSuperHeroes(Context context) {
        Call<List<User>> call = mainViewModel.getCallApi();
        call.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                List<User> userList = response.body();
                for (int i = 0; i < userList.size(); i++) {
                    User u = new User(userList.get(i).getName(), userList.get(i).getRealName(),
                            userList.get(i).getTeam(), userList.get(i).getFirstAppearance());

                    mainViewModel.addUser(u);
                    mUsers.add(u);
                }
                mRecyclerUser.setAdapter(mUserAdapter);
                mRecyclerUser.setLayoutManager(new LinearLayoutManager(context));

            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {

            }
        });

    }
}