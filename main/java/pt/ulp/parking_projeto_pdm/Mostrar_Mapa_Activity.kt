package pt.ulp.parking_projeto_pdm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.material.tabs.TabLayout
import pt.ulp.parking_projeto_pdm.databinding.ActivityMostrarMapaBinding
import pt.ulp.parking_projeto_pdm.modelos.UserMap

private const val TAG = "Mostrar_Mapa_Activity"
class Mostrar_Mapa_Activity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMostrarMapaBinding
    private lateinit var userMap : UserMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMostrarMapaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        userMap = intent.getSerializableExtra(EXTRA_USER_MAP) as UserMap

        supportActionBar?.title = userMap.titulo
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        Log.i(TAG, "Mapa para mostrar: ${userMap.titulo}") //mostar o título para verificar se esta a passar a localização para esta atividade

        val boundsBuilder = LatLngBounds.Builder() //este builder serve para não começar no equador lat = 0 e long = 0, começa centrado nos pontos
        for (local in userMap.locais) {
            val latLng = LatLng(local.latitude, local.longitude) //iniciar o local com 2 atributos lat e long
            boundsBuilder.include(latLng) //é aqui que definimos onde fica o foco, neste caso o ponto definido pelo latLng, que por sua vez, tem a informação vinda da classe Locations_Activity
            mMap.addMarker(MarkerOptions().position(latLng).title(local.titulo).snippet(local.descricao)) //display no marker
        }
        mMap.animateCamera(CameraUpdateFactory.newLatLngBounds(boundsBuilder.build(), 1000, 1000, 0)) // os parâmetros de 1000 são para o zoom e 0 para o padding
        //mMap.moveCamera(CameraUpdateFactory.newLatLngBounds(boundsBuilder.build(), 1000, 1000, 0))
        /*
        moveCamera para aparecer focado diretamente
        animateCamera para aparecer de lat=0.0 e long=0.0 e dar zoom automático
        Depois é só escolher
         */
    }
}