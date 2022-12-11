package com.example.app;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.example.app.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    ImageButton imageButton1;
    ImageButton imageButton2;
    ImageButton imageButton3;

    ActivityMainBinding binding;

    private final News[] news = new News[6];

    boolean flag = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        imageButton1.setImageResource(R.drawable.emptyheart);
        imageButton2.setImageResource(R.drawable.comment);
        imageButton3.setImageResource(R.drawable.share);
        EditText num = findViewById(R.id.number_of_likes);
        num.setText("0");


        imageButton1.setOnClickListener(this);

        news[0] = new News("Танцоры коллектива Bandaloop на открытии самой высокой смотровой площадки Нью-Йорка Edge", R.drawable.dancers);
        news[1] = new News("3,14 способа запомнить число π с большой точностью", R.drawable.pi);
        news[2] = new News("Зачем Трамп помиловал индеек и что нужно знать о Дне благодарения", R.drawable.tramp);
        news[3] = new News("\"Платину\" в рейтинге Forbes лучших работодателей России получили 17 компаний", R.drawable.forbes);
        news[4] = new News("Картина в картине и еще раз в картине — художественная рекурсия!", R.drawable.picture_in_picture);
        news[5] = new News("AlbertClock — часы, которые заставляют считать время", R.drawable.albert_clock);

        ArrayAdapter<News> adapter = new NewsAdapter(this, news);
        binding.list.setAdapter(adapter);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.like:
                if (!flag) {
                    imageButton1.setImageResource(R.drawable.redheart);
                    EditText num = findViewById(R.id.number_of_likes);
                    num.setText("1");
                } else {
                    imageButton1.setImageResource(R.drawable.emptyheart);
                    EditText num = findViewById(R.id.number_of_likes);
                    num.setText("0");
                }
                flag = !flag;
                break;
            case R.id.share:
                Toast.makeText(this, "nothing here", Toast.LENGTH_SHORT).show();
                break;
            case R.id.comment:
                Intent intent = new Intent(MainActivity.this, share.class);
                startActivity(intent);
                break;
        }
    }
}


class NewsAdapter extends ArrayAdapter<News> {
    final News[] news;

    public NewsAdapter(@NonNull Context context, News[] news) {
        super(context, R.layout.item);
        this.news = news;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        News n = news[position];
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item, null);
        }
        TextView news_name = convertView.findViewById(R.id.news_name);
        news_name.setText(n.name);

        ImageView news_pic = convertView.findViewById(R.id.image);
        switch (position) {
            case 0:
                news_pic.setImageResource(R.drawable.dancers);
                break;
            case 1:
                news_pic.setImageResource(R.drawable.pi);
                break;
            case 2:
                news_pic.setImageResource(R.drawable.tramp);
                break;
            case 3:
                news_pic.setImageResource(R.drawable.forbes);
                break;
            case 4:
                news_pic.setImageResource(R.drawable.picture_in_picture);
                break;
            case 5:
                news_pic.setImageResource(R.drawable.albert_clock);
                break;
        }
        return convertView;
    }
}