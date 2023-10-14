package com.example.vtbapiapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.vtbapiapp.database.entitys.withEntity.FavoriteDepartmentWithDepartment
import com.example.vtbapiapp.databinding.HistoryDepartmentItemBinding


class DepartmentFavoriteAdapter(val listener: Listener) : RecyclerView.Adapter<DepartmentFavoriteAdapter.DepartmentHolder>() {
    var departmentList = mutableListOf<FavoriteDepartmentWithDepartment>()
    class DepartmentHolder(item: View) : RecyclerView.ViewHolder(item) {
        val binding = HistoryDepartmentItemBinding.bind(item)
        fun bind(department: FavoriteDepartmentWithDepartment, listener: Listener) = with(binding){
            addressDepartmentTextView.text = department.departmentEntity.address
            nameDepartmentTextView.text = department.departmentEntity.description
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

    fun addDepartment(department: FavoriteDepartmentWithDepartment){
        departmentList.add(department)
        notifyDataSetChanged()
    }

    fun addDepartmentAll(departments: List<FavoriteDepartmentWithDepartment>){
        departmentList = departments.toMutableList()
        notifyDataSetChanged()
    }

    fun deleteDepartmentOnPosition(position: Int){
        departmentList.remove(departmentList[position])
        notifyDataSetChanged()
    }

    fun deleteDepartment(department: FavoriteDepartmentWithDepartment){
        departmentList.remove(department)
        notifyDataSetChanged()
    }

    interface Listener{
        fun onClickItem(department: FavoriteDepartmentWithDepartment)
        fun onClickDeleteItem(department: FavoriteDepartmentWithDepartment)
    }
}