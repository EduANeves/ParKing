package pt.ulp.parking_projeto_pdm

import android.content.Intent
import android.content.Intent.getIntent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class BottomSheetMA: BottomSheetDialogFragment(){
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.bottomsheet_ma,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val bt= view.findViewById<Button>(R.id.buttonMA)
        bt.setOnClickListener() {
            var nif2 = DBHelper.recebernif()
            showaluguer(nif2)
        }
    }
    private fun showaluguer(Nif:Int){
        var nif = Nif.toString()
        val intent = Intent(requireActivity(), Aluguer::class.java)
        intent.putExtra("NIF", nif)
        startActivity( intent )
    }
}