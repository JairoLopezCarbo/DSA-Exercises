package dsa.upc.edu.listapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import dsa.upc.edu.listapp.api.RetrofitClient;
import dsa.upc.edu.listapp.api.Track;
import dsa.upc.edu.listapp.api.TracksService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreateActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);

        EditText etId = (EditText) findViewById(R.id.etId);
        EditText etTitle = (EditText) findViewById(R.id.etTitle);
        EditText etSinger = (EditText) findViewById(R.id.etSinger);

        Button btnPost = (Button) findViewById(R.id.btnPost);
        Button btnPut = (Button) findViewById(R.id.btnPut);



        btnPost.setOnClickListener(v -> {
            TracksService tracksService = RetrofitClient.getTracksService();
            Track track = new Track(etId.getText().toString(), etTitle.getText().toString(), etSinger.getText().toString());
            tracksService.postTrack(track).enqueue(new Callback<Track>() {
                @Override
                public void onResponse(Call<Track> call, Response<Track> response) {
                    if (response.isSuccessful() && response.body() != null) {
                        Toast.makeText(CreateActivity.this,
                                "Track created", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(CreateActivity.this,
                                "Could not create Track", Toast.LENGTH_SHORT).show();
                    }

                }
                @Override
                public void onFailure(Call<Track> call, Throwable t) {
                    Toast.makeText(CreateActivity.this,
                            "Connection error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
            Intent i = new Intent(CreateActivity.this, MainActivity.class);
            startActivity(i);
        });

        btnPut.setOnClickListener(v -> {
            TracksService tracksService = RetrofitClient.getTracksService();
            Track track = new Track(etId.getText().toString(), etTitle.getText().toString(), etSinger.getText().toString());
            tracksService.putTrack(track).enqueue(new Callback<Track>() {
                @Override
                public void onResponse(Call<Track> call, Response<Track> response) {
                    if (response.isSuccessful() && response.body() != null) {
                        Toast.makeText(CreateActivity.this,
                                "Track created", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(CreateActivity.this,
                                "Could not create Track", Toast.LENGTH_SHORT).show();
                    }

                }
                @Override
                public void onFailure(Call<Track> call, Throwable t) {
                    Toast.makeText(CreateActivity.this,
                            "Connection error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
            Intent i = new Intent(CreateActivity.this, MainActivity.class);
            startActivity(i);
        });

    }
}
