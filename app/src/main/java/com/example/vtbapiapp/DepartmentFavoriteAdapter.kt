package com.example.vtbapiapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.vtbapiapp.databinding.HistoryDepartmentItemBinding

class DepartmentFavoriteAdapter(val listener: DepartmentFavoriteAdapter.Listener) : RecyclerView.Adapter<DepartmentFavoriteAdapter.DepartmentHolder>() {
    val departmentList = ArrayList<DepartmentFavorite>()
    class DepartmentHolder(item: View) : RecyclerView.ViewHolder(item) {
        val binding = HistoryDepartmentItemBinding.bind(item)
        fun bind(department: DepartmentFavorite, listener: Listener) = with(binding){
            addressDepartmentTextView.text = department.address
            nameDepartmentTextView.text = department.name
            itemView.setOnClickListener {
                listener.onClickItem(department)
            }
            deleteImageButton.setOnClickListener {
                listener.onClickDeleteItem(department)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DepartmentHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.history_department_item, parent, false)
        return DepartmentHolder(view)
    }

    override fun getItemCount(): Int {
        return departmentList.size
    }

    override fun onBindViewHolder(holder: DepartmentHolder, position: Int) {
        holder.bind(departmentList[position], listener)

    }

    fun addDepartment(department: DepartmentFavorite){
        departmentList.add(department)
        notifyDataSetChanged()
    }

    fun addDepartmentAll(departments: List<DepartmentFavorite>){
        departmentList.addAll(departments)
        notifyDataSetChanged()
    }

    fun deleteDepartmentOnPosition(position: Int){
        departmentList.remove(departmentList.get(position))
        notifyDataSetChanged()
    }

    fun deleteDepartment(department: DepartmentFavorite){
        departmentList.remove(department)
        notifyDataSetChanged()
    }

    interface Listener{
        fun onClickItem(department: DepartmentFavorite)
        fun onClickDeleteItem(department: DepartmentFavorite)
    }
}