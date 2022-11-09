package pt.ulp.parking_projeto_pdm

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.TextView
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.bottomsheet_calc.*

class BottomSheetPRP: BottomSheetDialogFragment(){
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.bottomsheet_prp,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val veiculos = DBHelper.getveiculo2().toList()
        val ab = ArrayAdapter(requireActivity(), android.R.layout.simple_spinner_item, veiculos)
        ab.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        svei.adapter = ab
        val bt_calcular= view.findViewById<Button>(R.id.button3)

        val ttipo = view.findViewById<TextView>(R.id.textView14)
        val tconsumo = view.findViewById<TextView>(R.id.textView16)
        val tpreco = view.findViewById<TextView>(R.id.textView17)
        bt_calcular.setOnClickListener {
            val string = svei.selectedItem.toString()
            if ( string != "Selecione uma opção...")
            {
                var codigo = DBHelper.getveiculocod3(string)
                var preco = DBHelper.getprecoh(codigo)
                var consumo = DBHelper.getconsumo(codigo)
                var tip = DBHelper.gettipo(codigo)
                tpreco.setText(preco.toString() + " €")
                tconsumo.setText(consumo.toString() + " %/Km")
                ttipo.setText(tip)
            }
        }
    }
}