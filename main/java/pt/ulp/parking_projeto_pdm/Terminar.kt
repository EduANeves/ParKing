package pt.ulp.parking_projeto_pdm

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_aluguer.*
import kotlinx.android.synthetic.main.activity_terminar.*

class Terminar : AppCompatActivity() {
    var id_alu = 0
    var codigo = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_terminar)
        DBHelper = DatabaseHandler(this)
        var nif2 = getIntent().getStringExtra("NIF").toString()
        id_alu = DBHelper.getveiculoalu2(nif2.toInt())
        codigo = DBHelper.getveiculocod2(nif2.toInt())
        val t1= findViewById<TextView>(R.id.thoras)
        val tprecoh= findViewById<TextView>(R.id.tprecoh)
        val tprecototal= findViewById<TextView>(R.id.tprecot)
        val t2= findViewById<TextView>(R.id.prec)
        val bt_avancar = findViewById<Button>(R.id.button)
        val btp = findViewById<Button>(R.id.idPagar)
        val pontos = DBHelper.getponto().toList()
        val aa = ArrayAdapter(this, android.R.layout.simple_spinner_item, pontos)
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        sentrega.adapter = aa

        bt_avancar.setOnClickListener {
            val string = sentrega.selectedItem.toString()
            if (string != "Selecione uma opção...") {
                var id_p = DBHelper.getpt(string)
               val f = DBHelper.updatefim(id_alu, id_p,codigo)
                t1.setVisibility(View.VISIBLE)
                tprecoh.setVisibility(View.VISIBLE)
                t2.setVisibility(View.INVISIBLE)
                bt_avancar.setVisibility(View.INVISIBLE)
               sentrega.setVisibility(View.INVISIBLE)
                btp.setVisibility(View.VISIBLE)
                var precoh = DBHelper.getprecoh(codigo);
                var horas = DBHelper.gethoras(id_alu)
                var preco = precoh * horas
                tprecoh.setText("Preço por hora: "+precoh.toString() + "€") //funciona
                t1.setText("Hora: " + horas.toString() + "h") // ir buscar horas e minutos
                tprecototal.setText("Preço total: " + preco.toString()+ "€")
                // falta por isto a funcionar
            }
        }
        btp.setOnClickListener {
            showaluguer(nif2.toInt())
        }

    }
    private fun showaluguer(Nif:Int){
        var nif = Nif.toString()
        val intent: Intent = Intent(this, MenuActivity::class.java)
        intent.putExtra("NIF", nif)
        startActivity( intent )
    }
}