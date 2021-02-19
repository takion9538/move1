package com.budi.myapplication

import android.animation.ObjectAnimator
import android.content.DialogInterface
import android.content.Intent

import android.graphics.Point
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.view.View.*
import android.view.WindowManager
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat


import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.android.synthetic.main.start_game.*
import java.lang.Thread.sleep
import kotlin.concurrent.thread

class StartGame : AppCompatActivity() {

    // 시작화면 kt
    // onCreate 함수 선언
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.start_game)

        // 네비게이션 바와 상태창 숨기기
        this.getWindow().getDecorView().setSystemUiVisibility(
            SYSTEM_UI_FLAG_LAYOUT_STABLE
                    or SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    or SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    or SYSTEM_UI_FLAG_HIDE_NAVIGATION // hide nav bar
                    or SYSTEM_UI_FLAG_FULLSCREEN // hide status bar
                    or SYSTEM_UI_FLAG_IMMERSIVE
        )

        // setting 게임 셋팅
        setting.setOnClickListener {
            val intent2 = Intent(this, Setting::class.java)
            startActivity(intent2)
        }


        // startgame 게임 시작
        startgame.setOnClickListener {
            finish()
            Log.d("로그", "finish");
        }
    }
}