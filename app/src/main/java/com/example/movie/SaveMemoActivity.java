package com.example.movie;
import android.app.AlertDialog;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.movie.Room.AppDatabase;
import com.example.movie.Room.User;

public class SaveMemoActivity extends Fragment{

    private final int REQUEST_CODE = 200;
    private EditText description;
    private TextView result;
    private AppDatabase db;
    Toolbar toolbar;

    public SaveMemoActivity(){

    }

    public static SaveMemoActivity newInstance() {
        return new SaveMemoActivity();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = (View)inflater.inflate(R.layout.activity_save_memo, container, false);

        description = (EditText) view.findViewById(R.id.description);
        result = (TextView) view.findViewById(R.id.result);

        db = AppDatabase.getInstance(getActivity());

        toolbar = view.findViewById(R.id.mytoolbar);

        setHasOptionsMenu(true);

        return view;
    }

    @Override
    public void onResume(){
        super.onResume();
        getActivity().invalidateOptionsMenu();
    }



    //메모저장하는 버튼
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu,inflater);
        inflater.inflate(R.menu.add_memo_list, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.save:
                EditText editText = new EditText(getActivity().getApplicationContext());
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("제목을 입력하세요");
                builder.setView(editText);

                builder.setPositiveButton("저장", (dialog, which) -> {
                    String s = editText.getText().toString();
                    // db에 저장하기
                    User memo = new User(s, description.getText().toString());
                    db.userDao().insert(memo);
                    Toast.makeText(getContext(),"저장되었습니다",Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                    //finish();
                });

                builder.setNegativeButton("취소", (dialog, which) -> {
                    dialog.dismiss();
                });

                builder.show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

}