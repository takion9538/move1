package com.budi.myapplication

import android.animation.ObjectAnimator
import android.content.DialogInterface
import android.content.Intent

import android.graphics.Point
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.view.MotionEvent
import android.view.View
import android.view.View.*
import android.view.WindowManager
import android.widget.SeekBar
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity


import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.android.synthetic.main.setting_sound.*
import kotlinx.android.synthetic.main.start_game.*
import java.lang.Thread.sleep
import kotlin.concurrent.thread

class Setting : AppCompatActivity() {

    // 셋팅 화면
    // onCreate 함수 선언
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.setting_sound)
        this.getWindow().getDecorView().setSystemUiVisibility(
            SYSTEM_UI_FLAG_LAYOUT_STABLE
                    or SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    or SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    or SYSTEM_UI_FLAG_HIDE_NAVIGATION // hide nav bar
                    or SYSTEM_UI_FLAG_FULLSCREEN // hide status bar
                    or SYSTEM_UI_FLAG_IMMERSIVE
        )
        setting_goHome.setOnClickListener {
            val intent3 = Intent(this, MainActivity::class.java)
            startActivity(intent3)
        }
        //백그라운드
        setting_bg.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {

                //onProgressChanged : "$progress"
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {

                //seekBar!!.progress가 현재 바 상태
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {


            }

        })

        //그외 사운드
        setting_other.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {

                //onProgressChanged : "$progress"
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {

                //seekBar!!.progress가 현재 바 상태
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {


            }

        })
    }
}