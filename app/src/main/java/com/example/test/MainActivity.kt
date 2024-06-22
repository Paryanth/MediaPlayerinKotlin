package com.example.test

import android.media.MediaPlayer
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private lateinit var play:Button
    private lateinit var pause:Button
    private lateinit var stop:Button
    private lateinit var forward:Button
    private lateinit var rewind:Button
    private lateinit var myMediaPlayer:MediaPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        myMediaPlayer = MediaPlayer.create(this, R.raw.sample)
        play = findViewById(R.id.play)
        pause = findViewById(R.id.pause)
        stop = findViewById(R.id.pause)
        forward = findViewById(R.id.forward)
        rewind = findViewById(R.id.rewind)

        play.setOnClickListener {
            if(!myMediaPlayer.isPlaying){
                myMediaPlayer.start()
            }
        }

        pause.setOnClickListener {
            if(!myMediaPlayer.isPlaying){
                myMediaPlayer.pause()
            }
        }

        stop.setOnClickListener {
            if(myMediaPlayer.isPlaying){
                myMediaPlayer.stop()
                myMediaPlayer.reset()
                myMediaPlayer = MediaPlayer.create(this, R.raw.sample)
            }
        }
        forward.setOnClickListener {
            if(myMediaPlayer.isPlaying){
                val myCurrentPos = myMediaPlayer.currentPosition
                val totalDuration = myMediaPlayer.duration

                if(myCurrentPos+10000 <=totalDuration){
                    myMediaPlayer.seekTo(myCurrentPos+10000)
                }else{
                    myMediaPlayer.seekTo(totalDuration)
                }
            }
        }
        rewind.setOnClickListener {
            if(myMediaPlayer.isPlaying){
                val myCurrentPos = myMediaPlayer.currentPosition
                val totalDuration = myMediaPlayer.duration

                if(myCurrentPos-10000 >=0){
                    myMediaPlayer.seekTo(myCurrentPos-10000)
                }else{
                    myMediaPlayer.seekTo(0)
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        myMediaPlayer.release()
    }
}