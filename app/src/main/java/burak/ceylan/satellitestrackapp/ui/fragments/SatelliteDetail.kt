package burak.ceylan.satellitestrackapp.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.text.bold
import androidx.core.text.buildSpannedString
import burak.ceylan.satellitestrackapp.R
import burak.ceylan.satellitestrackapp.databinding.FragmentSatelliteDetailBinding
import burak.ceylan.satellitestrackapp.networkEntities.model.PositionModel
import burak.ceylan.satellitestrackapp.utils.*
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SatelliteDetail : Fragment() {
    var satId = 1
    lateinit var binding: FragmentSatelliteDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSatelliteDetailBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.apply {
            satId = getInt(SAT_ID)
            binding.satelliteCost.text = buildSpannedString {
                bold {append( "${requireActivity().getString(R.string.cost)}: " )}
                append("${getString(COST_PER_LAUNCH)}")
            }

            binding.satelliteNameDetail.text = getString(SAT_NAME)
            binding.satelliteDate.text = getString(FIRST_FLIGHT)

            binding.satelliteHeight.text = buildSpannedString {
                bold {append( "${requireActivity().getString(R.string.height)}/${requireActivity().getString(R.string.mass)}: " )}
                append("${getString(HEIGHT)}/${getString(MASS)}")
            }
        }
        setPositions()
    }

    private fun setPositions() {
        val positionArray = getJsonFromAssets(requireContext(), "positions.json")

        val gson = Gson()

        val positions: PositionModel = gson.fromJson(positionArray, PositionModel::class.java)
        CoroutineScope(Dispatchers.Main).launch {
            for (i in positions.list!![satId - 1]!!.positions!!) {
                delay(3000)
                showLog("${i!!.posX}, ${i.posY}")
                if(activity != null && isAdded)
                    binding.satelliteLastPosition.text = buildSpannedString {
                        bold {append( "${requireActivity().getString(R.string.last_Position)}: " )}
                        append("${i!!.posX}, ${i.posY}")
                    }

            }
        }

    }


}