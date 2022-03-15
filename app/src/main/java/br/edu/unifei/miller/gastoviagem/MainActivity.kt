package br.edu.unifei.miller.gastoviagem

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import br.edu.unifei.miller.gastoviagem.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener {
    /*
    * Para utilizar o binding, é necessário inserir a dependência no gradle
    * buildFeatures {
    *    viewBinding true
    * }
    * Binding só pode ser instanciado após o onCreate
    * */
    private lateinit var binding: ActivityMainBinding
    /*
    * Função responsável por criar a activity
    * É semelhante ao initState do Flutter
    * */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Instancio primeiro o binding, e depois faço o set na content view, para criar a relação entre a ViewBinding e o código
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        // Adiciona evento de click no button calcular
        binding.buttonCalculate.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        //Atributo R vem da classe mãe View
        if (view.id == R.id.button_calculate) {
            calculate()
        }
    }

    private fun isValid(): Boolean {
        return (binding.editDistance.text.toString() != "") &&
                (binding.editPrice.text.toString() != "") &&
                (binding.editAutonomy.text.toString() != "") &&
                (binding.editDistance.text.toString().toFloat() != 0f)
    }

    private fun calculate() {
        if (isValid()) {
            val distance = binding.editDistance.text.toString().toFloat()
            val price = binding.editPrice.text.toString().toFloat()
            val autonomy = binding.editAutonomy.text.toString().toFloat()

            val totalValue = (distance * price) / autonomy
            binding.textTotalValue.text = "R$ ${"%.2f".format(totalValue)}"
        }else{
            Toast.makeText(this, R.string.validation_fill_all_fields, Toast.LENGTH_SHORT).show()
        }

    }
}