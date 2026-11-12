package com.example.reproductormiguelangel;

import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.media3.common.C;
import androidx.media3.common.MediaItem;
import androidx.media3.common.Player;
import androidx.media3.exoplayer.ExoPlayer;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private ExoPlayer player;
    private SeekBar seekBar;
    private TextView txtTiempoActual;
    private TextView txtDuracion;

    private Handler handler = new Handler(Looper.getMainLooper());

    private Runnable actualizarBarra = new Runnable() {
        @Override
        public void run() {
            if (player != null) {
                actualizarProgreso();

                if (player.isPlaying()) {
                    handler.postDelayed(this, 500);
                }
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnPlay = findViewById(R.id.btnPlay);
        Button btnPausa = findViewById(R.id.btnPausa);

        seekBar = findViewById(R.id.seekBar);
        txtTiempoActual = findViewById(R.id.txtTiempoActual);
        txtDuracion = findViewById(R.id.txtDuracion);

        player = new ExoPlayer.Builder(this).build();

        Uri uri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.cancion);

        player.setMediaItem(MediaItem.fromUri(uri));
        player.prepare();

        player.addListener(new Player.Listener() {
            @Override
            public void onPlaybackStateChanged(int playbackState) {
                if (playbackState == Player.STATE_READY) {
                    long duracion = player.getDuration();

                    if (duracion != C.TIME_UNSET) {
                        seekBar.setMax((int) duracion);
                        txtDuracion.setText(formatoTiempo(duracion));
                    }
                }

                if (playbackState == Player.STATE_ENDED) {
                    actualizarProgreso();
                }
            }
        });
        btnPlay.setOnClickListener(v -> {
            if (player.getPlaybackState() == Player.STATE_ENDED) {
                player.seekTo(0);
            }

            player.play();
            handler.removeCallbacks(actualizarBarra);
            handler.post(actualizarBarra);
        });

        btnPausa.setOnClickListener(v -> {
            player.pause();
            handler.removeCallbacks(actualizarBarra);
            actualizarProgreso();
        });
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                handler.removeCallbacks(actualizarBarra);
            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                player.seekTo(seekBar.getProgress());

                if (player.isPlaying()) {
                    handler.post(actualizarBarra);
                }
            }
        });
    }
    private void actualizarProgreso() {
        if (player != null) {
            long posicion = player.getCurrentPosition();

            seekBar.setProgress((int) posicion);
            txtTiempoActual.setText(formatoTiempo(posicion));
        }
    }
    private String formatoTiempo(long milisegundos) {
        long segundos = milisegundos / 1000;

        return String.format(Locale.getDefault(), "%02d:%02d",
                segundos / 60, segundos % 60);
    }
    @Override
    protected void onStart() {
        super.onStart();

        handler.removeCallbacks(actualizarBarra);
        handler.post(actualizarBarra);
    }
    @Override
    protected void onStop() {
        super.onStop();

        if (player != null) {
            player.pause();
        }

        handler.removeCallbacks(actualizarBarra);
    }
    @Override
    protected void onDestroy() {
        handler.removeCallbacks(actualizarBarra);

        if (player != null) {
            player.release();
            player = null;
        }

        super.onDestroy();
    }
}
