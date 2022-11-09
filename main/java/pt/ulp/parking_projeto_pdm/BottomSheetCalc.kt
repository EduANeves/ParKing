package pt.ulp.parking_projeto_pdm

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.bottomsheet_calc.*

class BottomSheetCalc: BottomSheetDialogFragment(){
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.bottomsheet_calc,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //var nif2 = getIntent().getStringExtra("NIF").toString()
        val bt_calcular= view.findViewById<Button>(R.id.btcalcular)

        val t2 = view.findViewById<TextView>(R.id.textView4)
       // bt_voltar.setOnClickListener { showvoltar(nif2.toInt()) }
        val veiculos = DBHelper.getveiculo2().toList()
        val ab = ArrayAdapter(requireActivity(), android.R.layout.simple_spinner_item, veiculos)
        ab.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        svei.adapter = ab
        var horas: EditText = view.findViewById(R.id.ehoras)
        var min: EditText = view.findViewById(R.id.emin)
        val dias: EditText = view.findViewById(R.id.edias)

        bt_calcular.setOnClickListener {
            val string = svei.selectedItem.toString()

            if (horas.getText().toString().isNotEmpty() || min.getText().toString().isNotEmpty() || string != "Selecione uma opção..."
            ) {
                val hor = horas.getText().toString()
                val mi = min.getText().toString()
                val dia = dias.getText().toString()
                var codigo = DBHelper.getveiculocod3(string)
                var preco = DBHelper.getprecoh(codigo)

                var d = 0
                var preco_total = 0.0
                if(dias.getText().toString().isEmpty()) {
                    var tempo = hor.toDouble()+ ( mi.toDouble() * 0.01)
                        preco_total = preco * tempo

                    if (tempo <= 1.59)
                    {
                        preco_total = preco_total - (preco_total*0.05)
                    }
                    if(tempo<=5.59 && tempo >=2.0)
                    {
                        preco_total = preco_total - (preco_total*0.07)
                    }
                    if(tempo<=10.59 && tempo >=6.0)
                    {
                        preco_total = preco_total - (preco_total*0.10)
                    }
                    if(tempo<=16.59 && tempo >=11.0)
                    {
                        preco_total = preco_total - (preco_total*0.12)
                    }
                    if(tempo<=24.0 && tempo >=17.0)
                    {
                        preco_total = preco_total - (preco_total*0.15)
                    }

                        t2.setText("Preço total: " + preco_total + "€")


                }
                else
                {
                    if (dia.toInt() == 0) {

                        var tempo = hor.toDouble()+ (mi.toDouble() *  0.01)
                        preco_total = preco * tempo
                        if (tempo <= 1.59)
                        {
                            preco_total = preco_total - (preco_total*0.05)
                        }
                        if(tempo<=5.59 && tempo >=2.0)
                        {
                            preco_total = preco_total - (preco_total*0.07)
                        }
                        if(tempo<=10.59 && tempo >=6.0)
                        {
                            preco_total = preco_total - (preco_total*0.10)
                        }
                        if(tempo<=16.59 && tempo >=11.0)
                        {
                            preco_total = preco_total - (preco_total*0.12)
                        }
                        if(tempo<=24.0 && tempo >=17.0)
                        {
                            preco_total = preco_total - (preco_total*0.15)
                        }
                        t2.setText("Preço total: " + preco_total + "€")
                    }
                    if (dia.toInt() >= 1) {

                        var f = hor.toDouble() + (dia.toInt() * 24)
                        var tempo = f+ (mi.toDouble() * 0.01)
                        preco_total = preco * tempo
                        preco_total = preco_total - (preco_total*0.20)
                        t2.setText("Preço total: " + preco_total + "€")
                    }
                }
            }

        }
    }

}