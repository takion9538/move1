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
import android.widget.SeekBar
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room


import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.android.synthetic.main.setting_sound.*
import kotlinx.android.synthetic.main.start_game.*
import java.lang.Thread.sleep
import kotlin.concurrent.thread

class Setting : AppCompatActivity() {

    var day = 0
    var uid = 0
    var bgm = 0
    var effect = 0


    // 셋팅 화면
    // onCreate 함수 선언
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val db = Room.databaseBuilder(applicationContext,SoundDatabase::class.java, "database-name")
                .allowMainThreadQueries()
                .build()

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


        var soundData = db.soundDao().getAll()

        setting_bg.progress = soundData[0].bgm
        setting_other.progress = soundData[0].effect

        //백그라운드
        setting_bg.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{

            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                Log.d("bgm stop 로그", "${seekBar!!.progress}")

                bgm = seekBar.progress

                db.soundDao().update(
                        Sound(
                                uid = uid,
                                bgm = bgm,
                                effect = effect
                        )
                )

                var soundData = db.soundDao().getAll()

                Log.d("db 로그", "${soundData[0].bgm}")

            }

        })

        //그외 사운드
        setting_other.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {

            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                Log.d("effect stop 로그", "${seekBar!!.progress}")

                effect = seekBar.progress

                db.soundDao().update(
                        Sound(
                                uid = uid,
                                bgm = bgm,
                                effect = effect
                        )
                )

                var soundData = db.soundDao().getAll()

                Log.d("db 로그", "${soundData[0].effect}")

            }
        })
    }
}