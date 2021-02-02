package com.budi.myapplication

import android.animation.ObjectAnimator
import android.graphics.Point
import android.media.MediaPlayer
import android.nfc.Tag
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    // 뷰의 선언
    val imageView1 : ImageView = findViewById(R.id.dot1)

    // 버튼 선언
    val Btn1 = findViewById<Button>(R.id.Btn1)
    val Btn2 = findViewById<Button>(R.id.Btn2)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Btn1.setOnClickListener() {
            ObjectAnimator.ofFloat(imageView1, "translationX", 100f).apply {
                duration = 100
                start()
            }
        }
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {

        if(event!!.action == MotionEvent.ACTION_DOWN) {

            // 터치 좌표값 저장
            var a : Float = event.getX()
            var b : Float = event.getY()

            // 뷰의 좌표값 받아오기
            fun View.getLocationOnScreen(): Point
            {
                val location = IntArray(2)
                this.getLocationOnScreen(location)
                return Point(location[0],location[1])
            }

            // absX 와 absY 에 뷰의 현재 절대좌표값을 저장
            val location = imageView1.getLocationOnScreen()
            val absX : Float = location.x.toFloat()
            val absY : Float = location.y.toFloat()

            // 뷰의 좌표값을 로그에 출력
            Log.d("뷰  좌표", "${absX}, ${absY}");

            // 터치 좌표값을 로그에 출력
            Log.d("터치좌표", "${a}, ${b}");

            // 이동할 거리를 연산
            val moveX : Float = a - absX
            val moveY : Float = b - absY


            // 클릭한 x 좌표로 이동
            ObjectAnimator.ofFloat(imageView1, "translationX", 100f).apply {
                duration = 100
                start()
            }
            return false
        }
        return super.onTouchEvent(event)
    }
}