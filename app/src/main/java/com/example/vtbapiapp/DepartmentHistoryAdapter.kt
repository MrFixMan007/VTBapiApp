package com.example.vtbapiapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.vtbapiapp.databinding.HistoryDepartmentItemBinding

class DepartmentHistoryAdapter : RecyclerView.Adapter<DepartmentHistoryAdapter.DepartmentHolder>() {
    val departmentList = ArrayList<DepartmentForHistory>()
    class DepartmentHolder(item: View) : RecyclerView.ViewHolder(item) {
        val binding = HistoryDepartmentItemBinding.bind(item)
        fun bind(department: DepartmentForHistory) = with(binding){
            textView.text = department.address
            textView2.text = department.name
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
        holder.bind(departmentList[position])
    }

    fun addDepartment(department: DepartmentForHistory){
        departmentList.add(department)
        notifyDataSetChanged()
    }

    fun addDepartmentAll(departments: List<DepartmentForHistory>){
        departmentList.addAll(departments)
        notifyDataSetChanged()
    }

    fun deleteDepartment(position: Int){
        departmentList.remove(departmentList.get(position))
        notifyDataSetChanged()
    }
}