package com.example.vtbapiapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.vtbapiapp.databinding.CityItemBinding

class CitiesAdapter(val listener: CitiesAdapter.Listener) : RecyclerView.Adapter<CitiesAdapter.CityHolder>() {
    val localityList = mutableListOf<Locality>()
    class CityHolder(item: View) : RecyclerView.ViewHolder(item) {
        private val binding =  CityItemBinding.bind(item)
        fun bind(locality: Locality, listener: Listener) = with(binding){
            cityNameTextView.text = locality.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.city_item, parent, false)
        return CityHolder(view)
    }

    override fun getItemCount(): Int {
        return localityList.size
    }

    override fun onBindViewHolder(holder: CityHolder, position: Int) {
        holder.bind(localityList[position], listener)

    }

    fun addCity(department: Locality){
        localityList.add(department)
        notifyDataSetChanged()
    }

    fun addCityAll(departments: List<Locality>){
        localityList.addAll(departments)
        notifyDataSetChanged()
    }

    fun deleteCityOnPosition(position: Int){
        localityList.remove(localityList[position])
        notifyDataSetChanged()
    }

    fun deleteCity(department: Locality){
        localityList.remove(department)
        notifyDataSetChanged()
    }

    interface Listener{
        fun onClickItem(department: Locality)
    }
}