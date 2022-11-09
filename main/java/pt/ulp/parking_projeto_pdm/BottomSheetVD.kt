package pt.ulp.parking_projeto_pdm

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class BottomSheetVD: BottomSheetDialogFragment(){

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.bottomsheet_vd,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val bt_local = view.findViewById<Button>(R.id.bt_local)

        bt_local.setOnClickListener{
            showLocationsandMaps()
        }
    }

    private fun showLocationsandMaps(){
        val intent: Intent = Intent(requireActivity(), Locations_Activity::class.java)
        startActivity( intent )
    }
}