package com.example.apptruyen.filters
import android.widget.Filter
import com.example.apptruyen.adapters.AdapterPdfAdmin
import com.example.apptruyen.models.ModelPdf

class FilterPdfAdmin:Filter {
    var filterList: ArrayList<ModelPdf>

    var adapterPdfAdmin : AdapterPdfAdmin

    constructor(filterList: ArrayList<ModelPdf>, adapterPdfAdmin: AdapterPdfAdmin) : super() {
        this.filterList = filterList
        this.adapterPdfAdmin = adapterPdfAdmin
    }

    override fun performFiltering(constraint: CharSequence?): FilterResults {
        var constraint:CharSequence? = constraint
        val results = FilterResults()

        if(constraint !=null && constraint.isNotEmpty()){
            constraint = constraint.toString().lowercase()
            val filteredModels = ArrayList<ModelPdf>()
            for(i in filterList.indices){
                if(filterList[i].title.lowercase().contains(constraint)){
                    filteredModels.add(filterList[i])
                }
            }
            results.count = filteredModels.size
            results.values = filteredModels
        }
        else{
            results.count = filterList.size
            results.values = filterList
        }
        return results
    }

    override fun publishResults(constraint: CharSequence, results: FilterResults) {
        adapterPdfAdmin.pdfArrayList = results!!.values as ArrayList<ModelPdf> /* = java.util.ArrayList<com.example.apptruyen.models.ModelPdf> */

        adapterPdfAdmin.notifyDataSetChanged()

    }

}