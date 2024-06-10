package weatherapp.llc

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity2 : AppCompatActivity() {
    //declarations
    private lateinit var saveButton: Button
    private lateinit var clearButton: Button
    private lateinit var nextButton: Button
    private lateinit var edDay: EditText
    private lateinit var edMintemp: EditText
    private lateinit var edMaxtemp:EditText
    private lateinit var edWeather:EditText
    private lateinit var tvMsg:TextView

    //list to store the weather data
    private val dateArray = mutableListOf<String>()
    private val mintempArray = mutableListOf<Float>()
    private val maxtempArray = mutableListOf<Float>()
    private val weatherArray = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main2)
        //initializing variables
        saveButton = findViewById(R.id.saveButton)
        clearButton = findViewById(R.id.clearButton)
        nextButton = findViewById(R.id.nextButton)
        edDay = findViewById(R.id.edDay)
        edMintemp = findViewById(R.id.edMintemp)
        edMaxtemp = findViewById(R.id.edMaxtemp)
        edWeather = findViewById(R.id.edWeather)
        tvMsg = findViewById(R.id.tvMsg)

    //Setup click listeners for the clear button
    clearButton.setOnClickListener {
        edDay.setText("")
        edMintemp.setText("")
        edMaxtemp.setText("")
        edWeather.setText("")
    }

        //setup click listener for the save button
    saveButton.setOnClickListener {
        val scr3Day = edDay.text.toString()
        val scr3Mintemp = edMintemp.text.toString()
        val scr3Maxtemp = edMaxtemp.text.toString()
        val scr3Weather = edWeather.text.toString()

        if (scr3Day.isNotEmpty() && scr3Mintemp.isNotEmpty() && scr3Maxtemp.isNotEmpty() && scr3Weather.isNotEmpty()) {
            try {
                dateArray.add(scr3Day)
                mintempArray.add(scr3Mintemp.toFloat())
                maxtempArray.add(scr3Maxtemp.toFloat())
                weatherArray.add(scr3Weather)
                edDay.text.clear()
                edMintemp.text.clear()
                edMaxtemp.text.clear()
                edWeather.text.clear()
            }catch (e:NumberFormatException){
                tvMsg.text= "Enter a valid number"
            }
        }else{
            tvMsg.text= "Space cannot be left blank"
        }

    }

        //setup click listener for the next button
    nextButton.setOnClickListener {
        val intent = Intent(this,MainActivity3::class.java)
        intent.putExtra("dateArray",dateArray.toTypedArray())
        intent.putExtra("mintempArray",mintempArray.toFloatArray())
        intent.putExtra("maxtempArray",maxtempArray.toFloatArray())
        intent.putExtra("weatherArray",weatherArray.toTypedArray())
        startActivity(intent)
    }
        }
    }
