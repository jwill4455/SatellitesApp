package burak.ceylan.satellitestrackapp.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import burak.ceylan.satellitestrackapp.R
import burak.ceylan.satellitestrackapp.base.BaseFragment
import burak.ceylan.satellitestrackapp.networkEntities.model.FilterableAdapter
import burak.ceylan.satellitestrackapp.networkEntities.model.SatelliteModel
import burak.ceylan.satellitestrackapp.utils.getJsonFromAssets
import burak.ceylan.satellitestrackapp.utils.onDone
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_satellites.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.lang.reflect.Type

@AndroidEntryPoint
class Satellites : BaseFragment() {

    lateinit var filterableAdapter: FilterableAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_satellites, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        CoroutineScope(Dispatchers.Main).launch {
            pbSatellite.visibility = View.VISIBLE
            delay(2000)
            setAdapter()
            edText.onDone(EditorInfo.IME_ACTION_DONE) {
                filterableAdapter.filter.filter(edText.text.toString())
            }
        }
    }

    private fun setAdapter() {
        val satellitesJsonArray = getJsonFromAssets(requireContext(), "satellite-list.json")

        val gson = Gson()
        val list: Type = object : TypeToken<ArrayList<SatelliteModel>>() {}.type

        val satellitesList: ArrayList<SatelliteModel> = gson.fromJson(satellitesJsonArray, list)
        filterableAdapter = FilterableAdapter(satellitesList, requireActivity())


        rvSatellite.apply {
            rvSatellite.addItemDecoration(DividerItemDecoration(context, GridLayoutManager.VERTICAL))
            adapter = filterableAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }

        //filterableAdapter.filter.filter()
        pbSatellite.visibility = View.GONE

    }

}