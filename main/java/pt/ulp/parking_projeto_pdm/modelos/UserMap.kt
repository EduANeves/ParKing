package pt.ulp.parking_projeto_pdm.modelos

import java.io.Serializable

data class UserMap (val titulo : String, val locais: List<Local>) : Serializable