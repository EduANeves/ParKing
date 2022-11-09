package pt.ulp.parking_projeto_pdm

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        DBHelper = DatabaseHandler(this)
        val blogin = findViewById<Button>(R.id.btlog)
        var user: EditText = findViewById(R.id.tUser)
        var pass: EditText = findViewById(R.id.tPassword)
        val textview = findViewById<TextView>(R.id.tregistar)
        val alertDialog = AlertDialog.Builder(this)

        blogin.setOnClickListener()
        {

            if(user.getText().toString().isEmpty() || pass.getText().toString().isEmpty()  ) {
                alertDialog.setTitle("Erro!")
                alertDialog.setMessage("Falta preencher o username ou a password!")
                alertDialog.show()
            }
            else {
                if ( DBHelper.getTask(user.getText().toString(), pass.getText().toString()) != 0)
                {
                    var NIF = DBHelper.getTask(user.getText().toString(), pass.getText().toString())
                   showMenuActivity(NIF)

                }
                else
                {
                    alertDialog.setTitle("Erro!")
                    alertDialog.setMessage("Dados inválidos")
                    alertDialog.show()
                    showActivity2()
                }}
        }
        textview.setOnClickListener()
        {
            showActivity()
        }

    }
    private fun showActivity(){
        val intent: Intent = Intent(this, Registo::class.java)
        startActivity( intent )
    }
    private fun showActivity2(){


        val intent: Intent = Intent(this, MainActivity::class.java)

        startActivity( intent )
    }
    private fun showScan(){
        val intent: Intent = Intent(this, ScanActivity::class.java)

        startActivity( intent )
    }

    private fun showMenuActivity(Nif: Int){
        var nif = Nif.toString()
        val intent : Intent = Intent(this, MenuActivity::class.java)
        intent.putExtra("NIF", nif)
        startActivity(intent)
    }
    private fun showaluguer(Nif:Int){
        var nif = Nif.toString()
        val intent = Intent(this, Aluguer::class.java)
        intent.putExtra("NIF", nif)
        startActivity( intent )
    }
}