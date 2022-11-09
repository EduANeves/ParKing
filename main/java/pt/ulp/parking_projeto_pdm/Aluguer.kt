package pt.ulp.parking_projeto_pdm

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_aluguer.*

class Aluguer : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_aluguer)
        DBHelper = DatabaseHandler(this)
        var nif2 = DBHelper.recebernif()

        val tipos = DBHelper.getTipoveiculo().toList()
        val pontos = DBHelper.getponto().toList()
        val bt_avancar = findViewById<Button>(R.id.bta)
        val bt_avancar2 = findViewById<Button>(R.id.btavancar)
        val bt_voltar= findViewById<Button>(R.id.btvoltar)
        val t1= findViewById<TextView>(R.id.thoras)
        val t2 = findViewById<TextView>(R.id.textView2)
        val t3 = findViewById<TextView>(R.id.textView3)
        var valor = DBHelper.getveiculo2(nif2.toInt())
        if(valor == 0 || valor == 3) {
            val aa = ArrayAdapter(this, android.R.layout.simple_spinner_item, pontos)
            aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            sponto.adapter = aa

            val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, tipos)
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            stipo.adapter = adapter
            var id_p = 0
            var id_t1 = 0
            bt_avancar.setOnClickListener {
                val string = sponto.selectedItem.toString()
                val string2 = stipo.selectedItem.toString()

                if (string != "Selecione uma opção..." && string2 != "Selecione uma opção...") {

                    sveiculo.setVisibility(View.VISIBLE)
                    bt_avancar2.setVisibility(View.VISIBLE)
                    bt_voltar.setVisibility(View.VISIBLE)
                    t3.setVisibility(View.VISIBLE)
                    stipo.setVisibility(View.INVISIBLE)
                    sponto.setVisibility(View.INVISIBLE)
                    bt_avancar.setVisibility(View.INVISIBLE)
                    t2.setVisibility(View.INVISIBLE)
                    t1.setVisibility(View.INVISIBLE)
                    id_p = DBHelper.getpt(string)
                    id_t1 = DBHelper.gett(string2)
                    val veiculos = DBHelper.getveiculo(id_t1, id_p).toList()

                    val ab = ArrayAdapter(this, android.R.layout.simple_spinner_item, veiculos)
                    ab.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                    sveiculo.adapter = ab


                } else {
                    val toast = Toast.makeText(
                        this,
                        "Faça a escolha correta",
                        Toast.LENGTH_LONG
                    )
                    toast.show()
                }

            }
            bt_voltar.setOnClickListener {
                sveiculo.setVisibility(View.INVISIBLE)
                bt_avancar2.setVisibility(View.INVISIBLE)
                bt_voltar.setVisibility(View.INVISIBLE)
                t3.setVisibility(View.INVISIBLE)
                stipo.setVisibility(View.VISIBLE)
                sponto.setVisibility(View.VISIBLE)
                bt_avancar.setVisibility(View.VISIBLE)
                t2.setVisibility(View.VISIBLE)
                t1.setVisibility(View.VISIBLE)
            }
            val alertDialog = AlertDialog.Builder(this)
            bt_avancar2.setOnClickListener {

                val string3 = sveiculo.selectedItem.toString()
                if (string3 != "Selecione uma opção..."){
                var id_t = DBHelper.getv(string3, id_t1, id_p)
                  if (DBHelper.addaluguer(nif2.toInt(), id_t, id_p)) {
//                      alertDialog.setTitle("Confirmação!")
//                      alertDialog.setMessage("Registo realizado com sucesso" + id_t.toString())
//                      alertDialog.show()
                      showScan(nif2.toInt())
                  }
                  else
                  {
                      alertDialog.setTitle("Confirmação!")
                     alertDialog.setMessage("Registo sem sucesso")
                      alertDialog.show()
                  }


            }}
        }
        if( valor == 1)
        {
            showScan(nif2.toInt())
        }
        if (valor == 2 ) {
        showterminar(nif2.toInt())
        }
    }
    private fun showScan(Nif:Int){
        var nif = Nif.toString()
        val intent: Intent = Intent(this, ScanActivity::class.java)
        intent.putExtra("NIF", nif)
        startActivity( intent )
    }
    private fun showterminar(Nif:Int){
        var nif = Nif.toString()
        val intent: Intent = Intent(this, Terminar::class.java)
        intent.putExtra("NIF", nif)
        startActivity( intent )
    }
}