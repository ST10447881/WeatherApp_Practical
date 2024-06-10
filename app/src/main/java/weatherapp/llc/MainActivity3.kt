package weatherapp.llc

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity3 : AppCompatActivity() {
    //Declarations for variables
    private lateinit var tvDay:TextView
    private lateinit var tvMintemp:TextView
    private lateinit var tvMaxtemp:TextView
    private lateinit var tvWeather:TextView
    private lateinit var tvResult:TextView
    private lateinit var homeButton: Button

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main3)
        //Initialize variables
        tvDay = findViewById(R.id.tvDay)
        tvMintemp = findViewById(R.id.tvMintemp)
        tvMaxtemp = findViewById(R.id.tvMaxtemp)
        tvWeather = findViewById(R.id.tvWeather)
        tvResult = findViewById(R.id.tvResult)
        homeButton = findViewById(R.id.homeButton)

        //Retrieve data arrays from the intent passed from MainActivity2
        val dateArray =  intent.getStringArrayExtra("dateArray")?.toList()?: emptyList()
        val mintempArray = intent.getFloatArrayExtra("mintempArray")?.toList()?: emptyList()
        val maxtempArray = intent.getFloatArrayExtra("maxtempArray")?.toList()?: emptyList()
        val weatherArray = intent.getStringArrayExtra("weatherArray")?.toList()?: emptyList()

        //Calculates the average temperature for the entire week
        var sumTemp = 0.0f
        var countTemp = 0
        for (temp in mintempArray){
            sumTemp += temp
            countTemp++
        }
        for (temp in maxtempArray){
            sumTemp += temp
            countTemp++
        }

        val avgTemp = if (countTemp > 0){
            sumTemp / countTemp
        }else{
            0.0f
        }

        //Displays the average temperature in in the tvResult TextView
        tvResult.text =  "Average Weekly Temp: %.2f°C".format(avgTemp)



        //Builds Strings to display day, min and max temperatures and weather conditions
        val day = StringBuilder()
        for ((index, date) in dateArray.withIndex()){
            day.append("Day ${index+1}: $date\n")
        }
        val minTemp = StringBuilder()
        for ((index,degrees) in mintempArray.withIndex()) {
            minTemp.append("Degrees Celsius $index:$degrees\n")
        }
        val maxTemp = StringBuilder()
        for ((index,degrees )in maxtempArray.withIndex()){
            maxTemp.append("Degrees Celsius $index:$degrees\n")
        }
        val weather = StringBuilder()
        for ((index,condition) in weatherArray.withIndex()){
            weather.append("Weather conditions is $index:$condition\n")
        }
        tvDay.text= day.toString()
        tvMintemp.text = minTemp.toString()
        tvMaxtemp.text = maxTemp.toString()
        tvWeather.text = weather.toString()

        //Sets up the home button to navigate back to finish the activity
        homeButton.setOnClickListener {
            finish()
        }
    }}
