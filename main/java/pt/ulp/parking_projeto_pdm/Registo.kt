package pt.ulp.parking_projeto_pdm

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog

lateinit var DBHelper : DatabaseHandler
class Registo : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registo)
        DBHelper = DatabaseHandler(this)
        val btregisto = findViewById<Button>(R.id.btr)
        val btvoltar = findViewById<Button>(R.id.btvoltar)
        var user: EditText = findViewById(R.id.Tuser)
        var pass: EditText = findViewById(R.id.Tpass)
        var nome: EditText = findViewById(R.id.tName)
        var sobre: EditText = findViewById(R.id.tSobrenome)
        var email: EditText = findViewById(R.id.tEmail)
        var nif: EditText = findViewById(R.id.tNif)
        val alertDialog = AlertDialog.Builder(this)
        btregisto.setOnClickListener()
        {
            if(nome.getText().toString().isEmpty() || sobre.getText().toString().isEmpty() ||nif.getText().toString().isEmpty() || email.getText().toString().isEmpty()||user.getText().toString().isEmpty() || pass.getText().toString().isEmpty() )
            {
                alertDialog.setTitle("Erro!")
                alertDialog.setMessage("Falta preencher um dos campos!")
                alertDialog.show()
            }
            else
            {
                if( nif.length() < 9 || nif.length()>9){
                    alertDialog.setTitle("Erro!")
                    alertDialog.setMessage("NIF incorreto")
                    alertDialog.show()}
                else{
                    if( pass.length() < 9){
                        alertDialog.setTitle("Erro!")
                        alertDialog.setMessage("Password deve conter 9 ou mais caracteres")
                        alertDialog.show()}
                    else
                    {
                        val n = nif.getText().toString()
                        if(DBHelper.addTask(n.toInt(),nome.getText().toString(),sobre.getText().toString(),email.getText().toString(),pass.getText().toString(),user.getText().toString())){
                            alertDialog.setTitle("Confirmação!")
                            alertDialog.setMessage("Registo realizado com sucesso")
                            alertDialog.show()
                            val intent: Intent = Intent(this, MainActivity::class.java)
                            startActivity( intent )
                        }
                    }
                }
            }
        }
        btvoltar.setOnClickListener()
        {
            val intent: Intent = Intent(this, MainActivity::class.java)
            startActivity( intent )
        }
    }
}