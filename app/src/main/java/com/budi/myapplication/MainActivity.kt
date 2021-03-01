package com.budi.myapplication

import android.content.Intent
import android.media.*
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View.*
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room

class MainActivity : AppCompatActivity() {/*

    // 사운드 풀 선언
    private val soundPool = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
        SoundPool.Builder().setMaxStreams(8).build()
    }
    else {
        SoundPool(8, AudioManager.STREAM_MUSIC, 1)
    }*/

    var day = 0
    var uid = 0
    var bgm = 0
    var effect = 6

    // onCreate 함수 선언
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        MediaPlayer.create(applicationContext, R.raw.restinforest)

        val db = Room.databaseBuilder(applicationContext,SoundDatabase::class.java, "database-name")
                .allowMainThreadQueries()
                .build()

        // 네비게이션 바와 상태창 숨기기
        this.getWindow().getDecorView().setSystemUiVisibility(
                SYSTEM_UI_FLAG_LAYOUT_STABLE
                        or SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        or SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        or SYSTEM_UI_FLAG_HIDE_NAVIGATION // hide nav bar
                        or SYSTEM_UI_FLAG_FULLSCREEN // hide status bar
                        or SYSTEM_UI_FLAG_IMMERSIVE)

        if (day == 0) {
            val gameStart = Intent(this, StartGame::class.java)
            startActivity(gameStart)
        }

        else if (day == 1) {
            val dayOne = Intent(this, DayOne::class.java)
            startActivity(dayOne)
        }
    }
}