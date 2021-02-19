package com.budi.myapplication

import android.animation.ObjectAnimator
import android.content.Context
import android.graphics.Point
import android.graphics.drawable.AnimationDrawable
import android.media.*
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.provider.MediaStore
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.view.View.*
import android.view.animation.Animation
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.Thread.sleep

class DayOne : AppCompatActivity() {

    // 사운드 풀 선언
    private val soundPool = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
        SoundPool.Builder().setMaxStreams(8).build()
    }
    else {
        SoundPool(8, AudioManager.STREAM_MUSIC, 1)
    }

    // 각 버튼의 상태 저장
    private var Btn1Down = false
    private var Btn2Down = false

    // onCreate 함수 선언
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 네비게이션 바와 상태창 숨기기
        this.getWindow().getDecorView().setSystemUiVisibility(
                SYSTEM_UI_FLAG_LAYOUT_STABLE
                        or SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        or SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        or SYSTEM_UI_FLAG_HIDE_NAVIGATION // hide nav bar
                        or SYSTEM_UI_FLAG_FULLSCREEN // hide status bar
                        or SYSTEM_UI_FLAG_IMMERSIVE)

        // 버튼 1 선언 및 버튼터치 리스너 지정
        Btn1.setOnTouchListener(onBtn1TouchListener)

        // 버튼 2 선언 및 버튼터치 리스너 지정
        Btn2.setOnTouchListener(onBtn2TouchListener)

        btnAction.setOnClickListener() {
            Toast.makeText(this, "버튼 눌림", Toast.LENGTH_SHORT).show()
        }

        // 버섯 핸들러를 호출
        mushroomHandler.sendEmptyMessage(1)
    }

    override fun onStart() {

        // 배경음악 설정 -> 모든 어플리케이션 콘텐츠에서 허용
        val bgm  = MediaPlayer.create(applicationContext, R.raw.restinforest)

        sleep(5000)

        bgm.start()
        bgm.isLooping = true

        super.onStart()
    }

    // onBtnDown 작업을 처리하는 쓰레드를 생성
    private fun onBtnDown() {
        val kThread = TouchThread()
        kThread.start()
    }

    // 왼쪽으로 이동하는 동안의 핸들러
    private val touch1Handler: Handler = object : Handler() {
        override fun handleMessage(msg: Message) {

            // Handler 가 작동할 때 (Btn1 버튼을 키다운 할 때) 마다 도트의 위치를 왼쪽으로 100float 만큼 이동
            ObjectAnimator.ofFloat(dot1, "translationX", dot1.x - 100f).apply {
                duration = 700
                start()
            }
        }
    }

    // 오른쪽으로 이동하는 동안의 핸들러
    private val touch2Handler: Handler = object : Handler() {
        override fun handleMessage(msg: Message) {

            // Handler 가 작동할 때 (Btn2 버튼을 키다운 할 때) 마다 도트의 위치를 오른쪽으로 100float 만큼 이동
            ObjectAnimator.ofFloat(dot1, "translationX", dot1.x + 100f).apply {
                duration = 700
                start()
            }
        }
    }

    // 슬라임 움직임 핸들러 테스트 1
    private val slimeHandler: Handler = object : Handler() {
        override fun handleMessage(msg: Message) {


            ObjectAnimator.ofFloat(slime, "translationX", dot1.x -40f).apply {
                duration = 700
                start()
            }
        }
    }

    // 슬라임 움직임 테스트 핸들러 2
    private val slime2Handler: Handler = object : Handler() {
        override fun handleMessage(msg: Message) {

            ObjectAnimator.ofFloat(slime, "translationX", dot1.x + dot1.width + 40f).apply {
                duration = 700
                start()
            }
        }
    }

    // 현재 dot 1 이미지 뷰의 좌표를 불러와 버섯의 범위 안에 있을 경우 버튼의 상태를 visible 로 변경
    private val mushroomHandler : Handler = object : Handler() {
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            if (dot1.x > (mushroom.x - dot1.width - 100f) && dot1.x < (mushroom.x + mushroom.width)) {
                btnAction.visibility = Button.VISIBLE
            } else {
                btnAction.visibility = Button.INVISIBLE
            }
        }
    }

    // '터치쓰레드' 클래스를 생성
    private inner class TouchThread : Thread() {

        // 사운드풀 풋프린트 선언
        val footprint = soundPool.load(applicationContext, R.raw.footprint2,1)

        // run 함수 호출
        override fun run() {

            // 뷰의 크기 가져오기
            val dot1Size = dot1.width

            // 화면의 크기 가져오기
            var display = windowManager.defaultDisplay
            val size = Point()
            display.getRealSize(size)
            val dpwidth = size.x

            // 버튼 1 이 클릭된 상태라면
            while (Btn1Down) {

                // 캐릭터가 화면의 왼쪽 끝에 닿기 전까지
                if (dot1.x > 0) {

                    // 버섯 핸들러를 호출
                    mushroomHandler.sendEmptyMessage(1)

                    // 좌측으로 이동하는 함수를 호출
                    touch1Handler.sendEmptyMessage(1)

                    try {
                        // 이미지의 리소스를 변경 (좌측으로 걷기)
                        dot1.setImageResource(R.drawable.witchleft1)
                        sleep(60L)

                        // 걸음소리를 호출
                        soundPool.play(footprint, 0.2f, 0.2f, 1, 0, 1f)

                        dot1.setImageResource(R.drawable.witchleft2)
                        sleep(60L)
                        dot1.setImageResource(R.drawable.witchleft3)
                        sleep(60L)
                        dot1.setImageResource(R.drawable.witchleft4)
                        sleep(60L)
                        slime2Handler.sendEmptyMessage(1)
                        dot1.setImageResource(R.drawable.witchleft5)
                        sleep(60L)
                        dot1.setImageResource(R.drawable.witchleft4)
                        sleep(60L)
                    }
                    catch (e: Exception) {
                    }
                }
            }

            // 버튼 2 가 클릭된 상태라면
            while (Btn2Down) {

                // 캐릭터가 화면의 오른쪽 끝에 닿기 전까지
                if (dot1.x + dot1Size <= dpwidth.toFloat()) {

                    // 버섯 핸들러를 호출
                    mushroomHandler.sendEmptyMessage(1)

                    // 우측으로 이동하는 함수를 호출
                    touch2Handler.sendEmptyMessage(1)

                    try {
                        // 이미지의 리소스를 변경 (우측으로 걷기)
                        dot1.setImageResource(R.drawable.witchright1)
                        sleep(60L)

                        // 걸음소리를 호출
                        soundPool.play(footprint, 0.2f, 0.2f, 1, 0, 1f)

                        dot1.setImageResource(R.drawable.witchright2)
                        sleep(60L)
                        dot1.setImageResource(R.drawable.witchright3)
                        sleep(60L)
                        slimeHandler.sendEmptyMessage(1)
                        dot1.setImageResource(R.drawable.witchright4)
                        sleep(60L)
                        dot1.setImageResource(R.drawable.witchright5)
                        sleep(60L)
                        dot1.setImageResource(R.drawable.witchright4)
                        sleep(60L)
                    }
                    catch (e: Exception) {
                    }
                }
            }
            return dot1.setImageResource(R.drawable.witch)
            super.run()
        }
    }

    // 버튼 1 이 클릭된 상태인지 검증하는 함수
    private val onBtn1TouchListener = OnTouchListener { v, event ->
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                Btn1Down = true
                onBtnDown()
            }
            MotionEvent.ACTION_UP -> {
                Btn1Down = false
            }
            else -> {
            }
        }
        false
    }

    // 버튼 2 가 클릭된 상태인지 검증하는 함수
    private val onBtn2TouchListener = OnTouchListener { v, event ->
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                Btn2Down = true
                onBtnDown()
            }
            MotionEvent.ACTION_UP -> {
                Btn2Down = false
            }
            else -> {
            }
        }
        false
    }

    // 화면이 터치되면 화면을 전체화면으로 변경하는 함수
    override fun onTouchEvent(event: MotionEvent): Boolean {

        // 만약 터치 다운 이벤트가 발생했을 때 다음 값을 리턴
        return when (event.action) {
            MotionEvent.ACTION_DOWN -> {

                // 네비게이션 바와 상태창 숨기기
                this.getWindow().getDecorView().setSystemUiVisibility(
                        SYSTEM_UI_FLAG_LAYOUT_STABLE
                                or SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                                or SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                                or SYSTEM_UI_FLAG_HIDE_NAVIGATION // hide nav bar
                                or SYSTEM_UI_FLAG_FULLSCREEN // hide status bar
                                or SYSTEM_UI_FLAG_IMMERSIVE)
                true
            }
            else -> return super.onTouchEvent(event)
        }
    }

    private fun Loading() {
        sleep(1000)
    }
}