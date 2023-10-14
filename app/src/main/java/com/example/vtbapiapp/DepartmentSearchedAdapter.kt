package com.yandex.mapkitdemo.routing

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.vtbapiapp.R
import com.example.vtbapiapp.SearchDepartment
import com.example.vtbapiapp.databinding.SearchedDepartmentItemBinding
import kotlinx.coroutines.runBlocking


class DepartmentSearchedAdapter(val listener: Listener) : RecyclerView.Adapter<DepartmentSearchedAdapter.DepartmentHolder>() {
    val departmentList = mutableListOf<SearchDepartment>()
    class DepartmentHolder(item: View) : RecyclerView.ViewHolder(item) {
        val binding = SearchedDepartmentItemBinding.bind(item)
        fun bind(department: SearchDepartment, listener: Listener) = with(binding){
            addressDepartmentTextView.text = department.departmentDto.address
            loadDepartmentTextView.text = department.load // загруженность
            phoneDepartmentTextView.text = department.departmentDto.phone
            distDepartmentTextView.text =department.dist
            workTimesDepartmentTextView.text =department.workTime

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

    fun addDepartment(department: SearchDepartment){
        departmentList.add(department)
        notifyDataSetChanged()
    }

    fun addDepartmentAll(departments: List<SearchDepartment>){
        departmentList.addAll(departments)
        notifyDataSetChanged()
    }

    fun deleteDepartmentOnPosition(position: Int){
        departmentList.remove(departmentList[position])
        notifyDataSetChanged()
    }

    fun deleteDepartment(department: SearchDepartment){
        departmentList.remove(department)
        notifyDataSetChanged()
    }

    interface Listener{
        fun onClickItem(department: SearchDepartment)
        fun onClickDeleteItem(department: SearchDepartment)
    }
}