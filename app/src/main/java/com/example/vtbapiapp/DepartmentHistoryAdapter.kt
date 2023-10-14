package com.example.vtbapiapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.vtbapiapp.database.entitys.withEntity.RecentlyDepartmentWithDepartment
import com.example.vtbapiapp.databinding.HistoryDepartmentItemBinding


class DepartmentHistoryAdapter(val listener: Listener) : RecyclerView.Adapter<DepartmentHistoryAdapter.DepartmentHolder>() {
    val departmentList = mutableListOf<RecentlyDepartmentWithDepartment>()
    class DepartmentHolder(item: View) : RecyclerView.ViewHolder(item) {
        val binding = HistoryDepartmentItemBinding.bind(item)
        fun bind(department: RecentlyDepartmentWithDepartment, listener: Listener) = with(binding){
            addressDepartmentTextView.text = department.department.address
            nameDepartmentTextView.text = department.department.description
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

    fun addDepartment(department: RecentlyDepartmentWithDepartment){
        departmentList.add(department)
        notifyDataSetChanged()
    }

    fun addDepartmentAll(departments: List<RecentlyDepartmentWithDepartment>){
        departmentList.addAll(departments)
        notifyDataSetChanged()
    }

    fun deleteDepartmentOnPosition(position: Int){
        departmentList.remove(departmentList[position])
        notifyDataSetChanged()
    }

    fun deleteDepartment(department: RecentlyDepartmentWithDepartment){
        departmentList.remove(department)
        notifyDataSetChanged()
    }

    interface Listener{
        fun onClickItem(department: RecentlyDepartmentWithDepartment)
        fun onClickDeleteItem(department: RecentlyDepartmentWithDepartment)
    }
}