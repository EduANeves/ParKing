package pt.ulp.parking_projeto_pdm.modelos

import java.io.Serializable

data class Local (val titulo : String, val descricao: String, val latitude : Double, val longitude : Double) : Serializable