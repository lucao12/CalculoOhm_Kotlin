package unisanta.br

import Regras.Ohm
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private lateinit var textView4 : TextView
    private lateinit var selectOption : String
    private val ohm = Ohm()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val spinner: Spinner = findViewById(R.id.spinner)
        val textView2: TextView = findViewById(R.id.textView2)
        val textView3: TextView = findViewById(R.id.textView3)
        textView4 = findViewById(R.id.textView4)
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View,
                position: Int,
                id: Long
            ) {
                val selectedOption = parent.getItemAtPosition(position).toString()
                when (selectedOption) {
                    "voltagem" -> {textView2.text = "Insira o valor da corrente"
                        textView3.text = "Insira o valor da resistência"
                        selectOption = "voltagem"}
                    "corrente" -> {textView2.text = "Insira o valor da voltagem"
                        textView3.text = "Insira o valor da resistência"
                        selectOption = "corrente"}
                    "resistência" -> {textView2.text = "Insira o valor da corrente"
                        textView3.text = "Insira o valor da voltagem"
                        selectOption = "resistência"}
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // Do nothing
            }
        }
    }

    fun calcular(view: View){
        val editText1: EditText = findViewById(R.id.editTextNumber)
        val editText2: EditText = findViewById(R.id.editTextNumber2)

        val valor1 = editText1.text.toString().toDoubleOrNull() ?: 0.0
        val valor2 = editText2.text.toString().toDoubleOrNull() ?: 0.0

        val result = ohm.calcular(valor1, valor2, selectOption)

        var simbolo = ""
        if(selectOption == "voltagem"){
            simbolo = "V"
        }else if(selectOption == "corrente"){
            simbolo = "A"
        }else{
            simbolo = "Ω"
        }
        textView4.text = result.toString() + simbolo
        editText1.setText("")
        editText2.setText("")
    }
}