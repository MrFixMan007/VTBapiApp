package com.example.vtbapiapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.vtbapiapp.databinding.SearchedDepartmentItemBinding

class DepartmentSearchedAdapter(val listener: DepartmentSearchedAdapter.Listener) : RecyclerView.Adapter<DepartmentSearchedAdapter.DepartmentHolder>() {
    val departmentList = ArrayList<Department>()
    class DepartmentHolder(item: View) : RecyclerView.ViewHolder(item) {
        val binding = SearchedDepartmentItemBinding.bind(item)
        fun bind(department: Department, listener: Listener) = with(binding){
            addressDepartmentTextView.text = department.address
            loadDepartmentTextView.text = department.load.toString()
            phoneDepartmentTextView.text = department.phone
            distDepartmentTextView.text = department.dist.toString()
            workTimesDepartmentTextView.text = department.workTime

            itemView.setOnClickListener {
                listener.onClickItem(department)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DepartmentHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.searched_department_item, parent, false)
        return DepartmentHolder(view)
    }

    override fun getItemCount(): Int {
        return departmentList.size
    }

    override fun onBindViewHolder(holder: DepartmentHolder, position: Int) {
        holder.bind(departmentList[position], listener)

    }

    fun addDepartment(department: Department){
        departmentList.add(department)
        notifyDataSetChanged()
    }

    fun addDepartmentAll(departments: List<Department>){
        departmentList.addAll(departments)
        notifyDataSetChanged()
    }

    fun deleteDepartmentOnPosition(position: Int){
        departmentList.remove(departmentList.get(position))
        notifyDataSetChanged()
    }

    fun deleteDepartment(department: Department){
        departmentList.remove(department)
        notifyDataSetChanged()
    }

    interface Listener{
        fun onClickItem(department: Department)
        fun onClickDeleteItem(department: Department)
    }
}