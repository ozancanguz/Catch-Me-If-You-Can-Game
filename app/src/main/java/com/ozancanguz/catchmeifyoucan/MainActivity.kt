package com.ozancanguz.catchmeifyoucan

import android.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    var score=0
    var imageArray=ArrayList<ImageView>()

    var handler = Handler(Looper.getMainLooper())
    var runnable = Runnable {  }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // Image Array
        imageArray.add(imageView1)
        imageArray.add(imageView2)
        imageArray.add(imageView3)
        imageArray.add(imageView4)
        imageArray.add(imageView5)
        imageArray.add(imageView6)
        imageArray.add(imageView8)
        imageArray.add(imageView9)
        imageArray.add(imageView10)


        hideImages()




        // countdown timer
        object : CountDownTimer(15000, 1000) {

            override fun onTick(millisUntilFinished: Long) {
                timetext.setText("Time:   " + millisUntilFinished / 1000)
            }

            override fun onFinish() {
                val builder = AlertDialog.Builder(this@MainActivity)


                timetext.text="Time : 0"

                handler.removeCallbacks(runnable)

                for(image in imageArray){
                    image.visibility=View.INVISIBLE
                }


                builder.setTitle("Tekrar oynamak ister misin")

                builder.setPositiveButton("evet") { dialog, which ->

                    val intent=intent
                    finish()
                    startActivity(intent)

                }

                builder.setNegativeButton("hayır") { dialog, which ->
                 Toast.makeText(applicationContext,"Oyun bitti",Toast.LENGTH_LONG).show()
                }


                builder.show()


            }
        }.start()





    }
        // score u arttırma
    fun increaseScore(view:View){

        score=score+1
        scoretext.text="Score " +score
    }


     // resimleri gizleme


    fun hideImages() {

        runnable = object : Runnable {
            override fun run() {
                for (image in imageArray) {
                    image.visibility = View.INVISIBLE
                }

                val myRandomValues =  Random.nextInt(1, 10)
                imageArray[myRandomValues].visibility = View.VISIBLE

                handler.postDelayed(runnable,500)


            }

        }

        handler.post(runnable)

    }
}
