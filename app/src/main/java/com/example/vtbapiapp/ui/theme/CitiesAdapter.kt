package com.example.vtbapiapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.vtbapiapp.databinding.CityItemBinding

class CitiesAdapter(val listener: CitiesAdapter.Listener) : RecyclerView.Adapter<CitiesAdapter.CityHolder>() {
    val cityList = ArrayList<City>()
    class CityHolder(item: View) : RecyclerView.ViewHolder(item) {
        val binding =  CityItemBinding.bind(item)
        fun bind(city: City, listener: Listener) = with(binding){
            cityNameTextView.setText(city.name)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.city_item, parent, false)
        return CityHolder(view)
    }

    override fun getItemCount(): Int {
        return cityList.size
    }

    override fun onBindViewHolder(holder: CityHolder, position: Int) {
        holder.bind(cityList[position], listener)

    }

    fun addCity(department: City){
        cityList.add(department)
        notifyDataSetChanged()
    }

    fun addCityAll(departments: List<City>){
        cityList.addAll(departments)
        notifyDataSetChanged()
    }

    fun deleteCityOnPosition(position: Int){
        cityList.remove(cityList.get(position))
        notifyDataSetChanged()
    }

    fun deleteCity(department: City){
        cityList.remove(department)
        notifyDataSetChanged()
    }

    interface Listener{
        fun onClickItem(department: City)
    }
}