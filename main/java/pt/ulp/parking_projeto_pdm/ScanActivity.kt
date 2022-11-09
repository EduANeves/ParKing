package pt.ulp.parking_projeto_pdm

import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.budiyev.android.codescanner.*
import kotlinx.android.synthetic.main.activity_scan.*

private const val CAMERA_REQUEST_CODE = 101
class ScanActivity : AppCompatActivity() {

    private lateinit var codeScanner: CodeScanner
var codigo = 0
    var id_alu = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scan)
        DBHelper = DatabaseHandler(this)
       var nif2 = getIntent().getStringExtra("NIF").toString()
        codigo = DBHelper.getveiculocod(nif2.toInt())
        id_alu = DBHelper.getveiculoalu(nif2.toInt())
        val bt_cancelar= findViewById<Button>(R.id.bcancelar)
        bt_cancelar.setOnClickListener{
            DBHelper.updatealuguer(id_alu)
            showaluguer(nif2.toInt())
        }
        codeScanner(nif2)

        setupPermissions()
    }

    private fun codeScanner(nif1:String) {
        codeScanner = CodeScanner(this, scanner_view)

        codeScanner.apply {
            camera = CodeScanner.CAMERA_BACK
            formats = CodeScanner.ALL_FORMATS

            autoFocusMode = AutoFocusMode.SAFE
            scanMode = ScanMode.CONTINUOUS
            isAutoFocusEnabled = true
            isFlashEnabled = false

            decodeCallback = DecodeCallback {
                runOnUiThread {


                    if(codigo.toString() ==  it.text)
                    {
                        DBHelper.updatem(id_alu)
                        showterminar(nif1.toInt())
                    }
                    else
                    {
                        val toast = Toast.makeText(applicationContext, "Código errado tente outro!", Toast.LENGTH_SHORT)
                        toast.show()

                    }
                }
            }
            errorCallback = ErrorCallback {
                runOnUiThread {
                    Log.e("Main", "Erro de inicialização da câmara: ${it.message}")
                }
            }
        }

        scanner_view.setOnClickListener {
            codeScanner.startPreview()
        }

    }

    override fun onResume(){
        super.onResume()
        codeScanner.startPreview()
    }

    override fun onPause(){
        codeScanner.releaseResources()
        super.onPause()
    }

    private fun setupPermissions(){
        val permission = ContextCompat.checkSelfPermission(this, android.Manifest.permission.CAMERA)
        if (permission != PackageManager.PERMISSION_GRANTED){
            makeRequest()
        }
    }

    private fun makeRequest(){
        ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.CAMERA), CAMERA_REQUEST_CODE)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            CAMERA_REQUEST_CODE -> {
                if (grantResults.isEmpty() || grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(
                        this,
                        "Precisa de permissões de câmara para utilizar esta funcionalidade!",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {

                }
            }
        }
    }
    private fun showaluguer(Nif:Int){
        var nif = Nif.toString()
        val intent: Intent = Intent(this, MenuActivity::class.java)
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