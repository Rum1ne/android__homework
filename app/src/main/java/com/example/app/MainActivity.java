package com.example.app;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.app.databinding.ActivityMainBinding;
import com.example.app.databinding.ItemBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    ItemBinding binding1;

    public final News[] news = new News[6];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        news[0] = new News("Kоллектив Bandaloop на открытии смотровой площадки Нью-Йорка Edge", R.drawable.dancers, 712);
        news[1] = new News("3,14 способа запомнить число π с большой точностью", R.drawable.pi, 183);
        news[2] = new News("Зачем Трамп помиловал индеек и что нужно знать о Дне благодарения", R.drawable.tramp, 497);
        news[3] = new News("\"Платинa\" в рейтинге Forbes лучших работодателей России", R.drawable.forbes, 109);
        news[4] = new News("Картина в картине и еще раз в картине — художественная рекурсия!", R.drawable.picture_in_picture, 505);
        news[5] = new News("AlbertClock — часы, которые заставляют считать время", R.drawable.albert_clock, 264);

        ArrayAdapter<News> adapter = new MyNewsAdapter(this, news);
        binding.listView.setAdapter(adapter);
    }

    boolean flag = true;

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_of_like:
                if (flag) {
                    binding1.likePicture.setImageResource(R.drawable.redheart);
                    binding1.numberOfLikes.setText(R.string.liked);
                } else {
                    Log.e("like", "the hell");
                    binding1.likePicture.setImageResource(R.drawable.emptyheart);
                    binding1.numberOfLikes.setText(R.string.nolike);
                }
                flag = !flag;
                break;
            case R.id.button_of_comment:
                Intent intent = new Intent(MainActivity.this, share.class);
                startActivity(intent);
                break;
            case R.id.button_of_share:
                Toast.makeText(MainActivity.this, "nothing here", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    class MyNewsAdapter extends ArrayAdapter<News> {


        final News[] news;

        public MyNewsAdapter(@NonNull Context context, News[] news) {
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

            TextView name = convertView.findViewById(R.id.news_name);
            name.setText(n.name);

            ImageView image = convertView.findViewById(R.id.image);
            image.setImageResource(n.image);

            return convertView;
        }
    }
}



