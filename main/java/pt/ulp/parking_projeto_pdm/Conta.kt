package pt.ulp.parking_projeto_pdm

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog

class Conta : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_conta)
        DBHelper = DatabaseHandler(this)
        var nif3 = intent.getStringExtra("NIF").toString()
        var pass1 = findViewById<EditText>(R.id.epass1)
        var pass2 = findViewById<EditText>(R.id.epass2)
        val bt_voltar= findViewById<Button>(R.id.voltar)
        val bt_t= findViewById<Button>(R.id.balterar)
        bt_t.setOnClickListener {
            if( pass1.length() >= 9) {
                if (pass1.getText().toString() == pass2.getText().toString()) {
                    DBHelper.updatepass(pass1.getText().toString(),nif3.toInt())
                    val alertDialog = AlertDialog.Builder(this)
                    alertDialog.setTitle("Confirmação!")
                    alertDialog.setMessage("Password alterada!")
                    alertDialog.show()
                }
                else
             {
                val alertDialog = AlertDialog.Builder(this)
                alertDialog.setTitle("Erro!")
                alertDialog.setMessage("Não são iguais!")
                alertDialog.show()
                }
            }
            else
            {
                val alertDialog = AlertDialog.Builder(this)
                alertDialog.setTitle("Erro!")
                alertDialog.setMessage("Deve ter mais de 9 caracteres!")
                alertDialog.show()
            }
        }
        bt_voltar.setOnClickListener {
                val intent: Intent = Intent(this, MenuActivity::class.java)
                intent.putExtra("NIF", nif3)
                startActivity( intent )

        }
    }
}