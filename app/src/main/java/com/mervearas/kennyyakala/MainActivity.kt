package com.mervearas.kennyyakala

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {
    var score = 0
    var imageArray = ArrayList<ImageView>() //İçinde image olan liste tanımlandı.
    var runnable = Runnable {  }
    var handler  = Handler(Looper.getMainLooper())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //ImageArray içine imageView ekleme
        imageArray.add(imageView1)
        imageArray.add(imageView2)
        imageArray.add(imageView3)
        imageArray.add(imageView4)
        imageArray.add(imageView5)
        imageArray.add(imageView6)
        imageArray.add(imageView7)
        imageArray.add(imageView8)
        imageArray.add(imageView9)
        imageArray.add(imageView10)
        imageArray.add(imageView11)
        imageArray.add(imageView12)
        imageArray.add(imageView13)
        imageArray.add(imageView14)
        imageArray.add(imageView15)
        imageArray.add(imageView16)

        hideImages()

        //Geri sayım sayacı
        object : CountDownTimer(16000,1000){
            override fun onTick(millisUntilFinished: Long) {
                timeText.text = "Time: " + millisUntilFinished/1000
            }
            override fun onFinish() {
                timeText.text = "Time: 0"

                handler.removeCallbacks(runnable)  //oyun bittikten sonra runnable'ı durdurur.

                for (image in imageArray){
                    image.visibility = View.INVISIBLE  //Oyun bittikten sonra resimleri görünmez yapar.
                }

                //ALERT MESSAGE
                val alert = AlertDialog.Builder(this@MainActivity)
                alert.setTitle("GAME OVER")
                alert.setMessage("Restart The Game")
                alert.setPositiveButton("YES") {dialog,which ->
                    //RESTART
                    val intent = intent
                    finish()
                    startActivity(intent)
                }
                alert.setNegativeButton("NO") {dialog, which ->
                    Toast.makeText(this@MainActivity,"GAME OVER",Toast.LENGTH_LONG).show()
                }
                alert.show()
            }
        }.start()
    }

    fun hideImages(){

        runnable = object : Runnable{

            override fun run() {

                for (image in imageArray){
                    image.visibility = View.INVISIBLE
                }
                var random = Random()
                var randomIndex = random.nextInt(16) //0 ile 16 arasında bir sayı türetir
                imageArray[randomIndex].visibility = View.VISIBLE  //türetilen random sayıyı listedeki index numarasına atanır ve rastgele image gösterilir.

                handler.postDelayed(runnable,300)
            }
        }
        handler.post(runnable)
    }

    //Görsele basınca score arttırma kodu
    fun increaseScore(view: View){
        score = score + 1
        scoreText.text = "Score: $score"

    }
}