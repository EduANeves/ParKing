package pt.ulp.parking_projeto_pdm

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.view.forEach
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_menu.*
import kotlinx.android.synthetic.main.bottomsheet_vd.*


class MenuActivity : AppCompatActivity(), OnMapReadyCallback,
    GoogleMap.OnMarkerClickListener {

    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var listener: NavController.OnDestinationChangedListener

    private lateinit var bottomLayout: BottomNavigationView
    private lateinit var appBarConfigurationBV: AppBarConfiguration

    private lateinit var nMap: GoogleMap
    private lateinit var LastLocation: Location
    private lateinit var fusedLocationClient: FusedLocationProviderClient

    //variavel pro comportamento da bottomsheet
    private lateinit var bottomsheetBehaviour: BottomSheetBehavior<LinearLayout>


    companion object{
        private const val LOCATION_PERMISSION_REQUEST_CODE = 1
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        //Botões BottomSheet
        val bottomSheetVD = BottomSheetVD()
        val bottomSheetPRP = BottomSheetPRP()
        val bottomSheetMA = BottomSheetMA()
        val bottomSheetCalc=BottomSheetCalc()
        val bottomSheetNoti=BottomSheetNoti()
        var nif3 = intent.getStringExtra("NIF").toString()
        DBHelper.guardarnif(nif3.toInt())

        bottomsheetB.setOnClickListener {
          //  DBHelper.guardarnif(nif3.toInt())
            bottomSheetVD.show(supportFragmentManager, "BottomSheetDialog")
        }
        bottomsheetPRP.setOnClickListener {
          //  DBHelper.guardarnif(nif3.toInt())
            bottomSheetPRP.show(supportFragmentManager, "BottomSheetDialog")
        }
        bottomsheetMA.setOnClickListener {
          //  DBHelper.guardarnif(nif3.toInt())
            bottomSheetMA.show(supportFragmentManager, "BottomSheetDialog")
        }
        bottomsheetCalc.setOnClickListener {
          // DBHelper.guardarnif(nif3.toInt())
            bottomSheetCalc.show(supportFragmentManager, "BottomSheetDialog")
        }
        bottomsheetN.setOnClickListener {
          //  DBHelper.guardarnif(nif3.toInt())
            bottomSheetNoti.show(supportFragmentManager, "BottomSheetDialog")
        }

        //Fim Botões BottomSheet

        //bottomsheetBehaviour = BottomSheetBehavior.from(bottomsheet)

        /*bottomsheetB.setOnClickListener {
            if (bottomsheetBehaviour.state==BottomSheetBehavior.STATE_EXPANDED){
                bottomsheetBehaviour.state=BottomSheetBehavior.STATE_COLLAPSED
            }else{
                bottomsheetBehaviour.state=BottomSheetBehavior.STATE_EXPANDED

            }
        }*/

        /*val bmodalidadeAluguer = findViewById<Button>(R.id.modalidadeMarcacaoB)

        bmodalidadeAluguer.setOnClickListener(){
            showActivityModalidadeAluguer()
        }*/

        val navigationView: NavigationView = findViewById(R.id.navigation_view)

        val navController = findNavController(R.id.fragmentContainerView)

        drawerLayout = findViewById(R.id.drawer_layout)
        //bottomLayout = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        appBarConfiguration = AppBarConfiguration(navController.graph, drawerLayout)
        /*appBarConfigurationBV = AppBarConfiguration(setOf(R.id.veiculosDisponiveis, R.id.pontosRecolhaProx,
            R.id.modalidadeAluguer, R.id.pontosPartilhaFav, R.id.notificacoes))*/

        //bottomNavigationView.setupWithNavController(navController)
        navigation_view.setupWithNavController(navController)

        setupActionBarWithNavController(navController, appBarConfiguration)
        //setupActionBarWithNavController(navController, appBarConfigurationBV)

        /*bottomNavigationView.setOnClickListener(){
            fragmentContainerView.
        }*/
        navigationView.menu.findItem(R.id.regras).setOnMenuItemClickListener { item ->
            when(item.itemId){
                R.id.regras ->{

                    val intent = Intent(this, Regras::class.java)
                    startActivity(intent)
                    true
                }
                else -> false
            }
        }
        navigationView.menu.findItem(R.id.precario).setOnMenuItemClickListener { item ->
            when(item.itemId){
                R.id.precario ->{

                    val intent = Intent(this, Precario::class.java)
                    startActivity(intent)
                    true
                }
                else -> false
            }
        }
        navigationView.menu.findItem(R.id.Conta).setOnMenuItemClickListener { item ->
            when(item.itemId){
              R.id.Conta ->{

                  val intent = Intent(this, Conta::class.java)
                  intent.putExtra("NIF", nif3)
                  startActivity(intent)
                  true
              }
                else -> false
            }
        }
        navigationView.menu.findItem(R.id.Logout).setOnMenuItemClickListener { item ->
            when(item.itemId){
                R.id.Logout ->{

                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    true
                }
                else -> false
            }
        }

        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as? SupportMapFragment
        mapFragment?.getMapAsync(this)

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.fragmentContainerView)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    override fun onMapReady(googleMap: GoogleMap) {
        nMap = googleMap

        nMap.uiSettings.isZoomControlsEnabled = true
        nMap.setOnMarkerClickListener(this)
        setUpMap()
    }

    private fun setUpMap() {
        if (ActivityCompat.checkSelfPermission(
                this,
                android.Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION), LOCATION_PERMISSION_REQUEST_CODE)

            return
        }
        nMap.isMyLocationEnabled = true
        fusedLocationClient.lastLocation.addOnSuccessListener(this) { location ->
            if(location!=null){
                LastLocation = location
                val currentLatLng = LatLng(location.latitude, location.longitude)
                placeMakerOnMap(currentLatLng)
                nMap.moveCamera(CameraUpdateFactory.newLatLngZoom(currentLatLng, 12f))
            }
        }
    }

    private fun placeMakerOnMap(currentLatLong: LatLng) {
        val markerOptions = MarkerOptions().position(currentLatLong)
        markerOptions.title("$currentLatLong")
        nMap.addMarker(markerOptions)
    }
    /*private fun showActivityModalidadeAluguer(){
        val intent: Intent = Intent(this, Aluguer::class.java)
        startActivity(intent)
    }*/
    override fun onMarkerClick(p0: Marker?) = false

}