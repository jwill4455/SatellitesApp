package burak.ceylan.satellitestrackapp.networkEntities.model

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import burak.ceylan.satellitestrackapp.R
import burak.ceylan.satellitestrackapp.databinding.RowSatelliteBinding
import burak.ceylan.satellitestrackapp.utils.*
import com.bumptech.glide.Glide

class FilterableAdapter(var list: ArrayList<SatelliteModel>, var activity: Activity) :
    RecyclerView.Adapter<FilterableAdapter.FilterableViewModel>(), Filterable {
    val controller by lazy { Navigation.findNavController(activity, R.id.hostFragment) }


    var filterList = ArrayList<SatelliteModel>()

    init {
        filterList = list
    }

    inner class FilterableViewModel(var binding: RowSatelliteBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(model: SatelliteModel) {
            binding.apply {
                model.apply {
                    if (active == false) {
                        Glide.with(imgStatus.context).load(R.color.red)
                            .placeholder(R.color.red).into(imgStatus)

                        tvActive.setTextColor(R.color.color_background)
                        tvName.setTextColor(R.color.color_background)
                        tvActive.text = "Passive"

                    } else {
                        Glide.with(imgStatus.context).load(R.color.green).placeholder(R.color.green)
                            .into(imgStatus)
                        tvActive.text = "Active"


                    }

                    tvName.text = "$name"
                }
                val bundle = Bundle()
                bundle.putInt(SAT_ID, model.id!!)
                bundle.putString(SAT_NAME, model.name)
                bundle.putString(COST_PER_LAUNCH, model.costPerLaunch.toString())
                bundle.putString(FIRST_FLIGHT, model.firstFlight)
                bundle.putString(HEIGHT, model.height.toString())
                bundle.putString(MASS, model.mass.toString())

                rootLayoutSatellite.setOnClickListener {
                    controller.navigate(R.id.action_satellites_to_satelliteDetail, bundle)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilterableViewModel {
        val binding =
            RowSatelliteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FilterableViewModel(binding)
    }

    override fun onBindViewHolder(holder: FilterableViewModel, position: Int) {
        val currentItem = filterList[position]
        holder.bind(currentItem)
    }

    override fun getItemCount(): Int {
        return filterList.size
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charSearch = constraint.toString()
                if (charSearch.isEmpty()) {
                    filterList = list
                } else {
                    val resultList = ArrayList<SatelliteModel>()
                    for (row in list) {
                        if (row.name!!.toLowerCase()
                                .contains(constraint.toString().toLowerCase())
                        ) {
                            resultList.add(row)
                        }
                    }
                    filterList = resultList
                }
                val filterResults = FilterResults()
                filterResults.values = filterList
                return filterResults
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                filterList = results?.values as ArrayList<SatelliteModel>
                notifyDataSetChanged()
            }
        }
    }
}