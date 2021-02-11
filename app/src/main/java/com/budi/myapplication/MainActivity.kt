package com.budi.myapplication

import android.animation.ObjectAnimator
import android.graphics.Point
import android.media.MediaPlayer
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.util.Log
import android.view.MotionEvent
import android.view.View.*
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.Thread.sleep

class MainActivity : AppCompatActivity() {

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

//        val media1Player = MediaPlayer.create(this,R.raw.foot)
//        Btn1.setOnClickListener{ media1Player.start()
//            sleep(200L)
//        }
//        media1Player.release()
//
//        val media2Player = MediaPlayer.create(this,R.raw.foot)
//        Btn2.setOnClickListener{ media2Player.start()
//            sleep(200L)
//        }
//        media2Player.release()
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
            ObjectAnimator.ofFloat(dot1, "translationX", dot1.x - 70f).apply {
                duration = 200
                start()
            }
        }
    }

    // 오른쪽으로 이동하는 동안의 핸들러
    private val touch2Handler: Handler = object : Handler() {
        override fun handleMessage(msg: Message) {

            // Handler 가 작동할 때 (Btn2 버튼을 키다운 할 때) 마다 도트의 위치를 오른쪽으로 100float 만큼 이동
            ObjectAnimator.ofFloat(dot1, "translationX", dot1.x + 70f).apply {
                duration = 200
                start()
            }
        }
    }

    // '터치쓰레드' 클래스를 생성
    private inner class TouchThread : Thread() {

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
                if (dot1.x >= 0) {
                    
                    // 좌측으로 이동하는 함수를 호출
                    touch1Handler.sendEmptyMessage(1)
                    sleep(100L)

                    try {
                        // 이미지의 리소스를 변경 (좌측으로 걷기)
                        dot1.setImageResource(R.drawable.dot_left)
                        sleep(100L)
                        dot1.setImageResource(R.drawable.dot_left_2)
                    } 
                    catch (e: Exception) {
                        println("error")
                    }
                }
            }
            
            // 버튼 2 가 클릭된 상태라면
            while (Btn2Down) {
                
                // 캐릭터가 화면의 오른쪽 끝에 닿기 전까지
                if (dot1.x + dot1Size <= dpwidth.toFloat()) {
                    
                    // 우측으로 이동하는 함수를 호출
                    touch2Handler.sendEmptyMessage(1)
                    sleep(100L)

                    try {
                        // 이미지의 리소스를 변경 (우측으로 걷기)
                        dot1.setImageResource(R.drawable.dot_right)
                        sleep(100L)
                        dot1.setImageResource(R.drawable.dot_right_2)
                    } 
                    s
                    catch (e: Exception) {
                        println("error")
                    }
                }
            }
            sleep(100L)
            return dot1.setImageResource(R.drawable.witchtest)
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
            MotionEvent.ACTION_UP -> Btn1Down = false
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
            MotionEvent.ACTION_UP -> Btn2Down = false
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
}