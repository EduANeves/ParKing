package pt.ulp.parking_projeto_pdm

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_locations.*
import pt.ulp.parking_projeto_pdm.modelos.Local
import pt.ulp.parking_projeto_pdm.modelos.UserMap

private const val TAG = "Locations_Activity"
const val EXTRA_USER_MAP = "EXTRA_USER_MAP"

class Locations_Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_locations)

        val userMaps = generateSampleData()
        rv_locais.layoutManager = LinearLayoutManager(this)
        rv_locais.adapter = MapsAdapter(this, userMaps, object : MapsAdapter.OnClickListener{
            override fun onItemClick(position: Int) {
                Log.i(TAG, "onItemClick $position")
                //De seguida, levar para a atividade correspondente à posição
                val intent = Intent(this@Locations_Activity, Mostrar_Mapa_Activity::class.java)
                intent.putExtra(EXTRA_USER_MAP, userMaps[position])
                startActivity(intent)
            }
        })

        //Parte em que, ao clicar num item da recycler view, nos leva para uma nova atividade correspondente

    }

    private fun generateSampleData(): List<UserMap> {
        return listOf(
            UserMap(
                "Parque de Estacionamento Duque de Loulé",
                listOf(
                    Local("Rua de Arnaldo Gama", "Electric Vehicle Charging Point", 41.14226522054774, -8.606881767634523),
                )
            ),
            UserMap("KLC Estação de Carregamento",
                listOf(
                    Local("Rua da Pasteleira", "Electric Vehicle Charging Point", 41.15353155759788, -8.653859051487297),
                )

            ),
            UserMap("Parking w Porto",
                listOf(
                    Local("Rua Coronel Raúl Peres", "Parque de Estacionamento", 41.15139744848394, -8.677709329632028),
                )

            ),
            UserMap("Estação de carregamento para veículos elétricos",
                listOf(
                    Local("Rua de Egas Moniz", "Electric Vehicle Charging Point", 41.163060210395905, -8.623565044792741),
                )

            ),
            UserMap("Estacionamento Parque da Cidade",
                listOf(
                    Local("Estrada Nacional 12 - N12", "Parque de Estacionamento", 41.170388011148425, -8.678916738788166),
                )

            ),
            UserMap("Power Dot Estação de Carregamento",
                listOf(
                    Local("Alameda Capitães de Abril", "Electric Vehicle Charging Point", 41.15648911269395, -8.613845771583517),
                )

            ),
            UserMap("PCR - Circunvalação",
                listOf(
                    Local("Estrada da Circunvalação", "Electric Vehicle Charging Point", 41.18032298745682, -8.635011232539508),
                )

            ),


        )
    }
}