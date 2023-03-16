package `in`.prashant.ageinminutes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.app.DatePickerDialog
import android.widget.TextView
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    private var tvselecteddate :TextView?=null
    private  var tvageinminutes :TextView?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btndatepicker:Button = findViewById(R.id.datepickerbtn)
        tvselecteddate =findViewById(R.id.tvselecteddate)
        tvageinminutes =findViewById(R.id.tvageinminutes)
        btndatepicker.setOnClickListener{
           clickdatepicker()
        }

    }
    private fun clickdatepicker(){

        val myCalendar = Calendar.getInstance()
        val year=myCalendar.get(Calendar.YEAR)
        val month=myCalendar.get(Calendar.MONTH)
        val day=myCalendar.get(Calendar.DAY_OF_MONTH)

       val dpd =  DatePickerDialog(this,
           DatePickerDialog.OnDateSetListener{ _,selectedyear,selectedmonth,selecteddayOfMonth->
               Toast.makeText(this,"Year was $selectedyear,month was ${selectedmonth+1},day was $selecteddayOfMonth",Toast.LENGTH_LONG)

               val selecteddate="$selecteddayOfMonth/${selectedmonth+1}/$selectedyear"

               tvselecteddate?.text=selecteddate

               val sdf = SimpleDateFormat("dd/MM/yyyy",Locale.ENGLISH)
               val theDate =sdf.parse(selecteddate)
                 theDate?.let {
                     val selecteddateinminutes = theDate.time/60000

                     val currentdate= sdf.parse(sdf.format(System.currentTimeMillis()))

                     currentdate?.let {
                         val currentdateinminutes=currentdate.time/60000
                         val differenceinminutes= currentdateinminutes - selecteddateinminutes

                         tvageinminutes?.text=differenceinminutes.toString()
                     }

                 }



           },
           year,
           month,
           day
       )
       dpd.datePicker.maxDate=System.currentTimeMillis() - 86400000
       dpd.show()

    }
}