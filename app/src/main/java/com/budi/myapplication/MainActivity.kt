package com.budi.myapplication

import android.animation.ObjectAnimator
import android.graphics.Point
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 뷰의 선언
        val imageView1: ImageView = findViewById(R.id.dot1)

        // 버튼 선언
        val Btn1 = findViewById<Button>(R.id.Btn1)
        val Btn2 = findViewById<Button>(R.id.Btn2)

        // 뷰의 좌표값 받아오기
        fun View.getLocationOnScreen(): Point {
            val location = IntArray(2)
            this.getLocationOnScreen(location)
            return Point(location[0], location[1])
        }

        // absX 와 absY 에 뷰의 현재 절대좌표값을 저장
        val location = imageView1.getLocationOnScreen()
        val absX: Float = location.x.toFloat()
        val absY: Float = location.y.toFloat()

        // 뷰의 좌표값을 로그에 출력
        Log.d("뷰  좌표", "${absX}, ${absY}");

        // 이미지뷰1 의 Width, Height 를 받아옴
        val parentWidth = (imageView1.parent as ViewGroup).width
        val parentHeight = (imageView1.parent as ViewGroup).height

        // 이미지뷰1 의 x 와 y 위치값을 중앙으로 설정
        imageView1.x = absX - parentWidth
        imageView1.y = absY - parentHeight

        // 버튼1 이 클릭되었을 때
        Btn1.setOnClickListener() {
            // 이미지뷰1 의 위치를 이동
            ObjectAnimator.ofFloat(imageView1, "translationX", imageView1.x - 100f).apply {
                duration = 100
                start()
            }
        }

        // 버튼2 가 클릭되었을 때
        Btn2.setOnClickListener() {
            // 이미지뷰1 의 위치를 이동
            ObjectAnimator.ofFloat(imageView1, "translationX", imageView1.x + 100f).apply {
                duration = 100
                start()
            }
        }
    }
}