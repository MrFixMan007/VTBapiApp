package com.example.vtbapiapp

import android.os.Binder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.example.vtbapiapp.databinding.ActivityMainBinding
import com.sothree.slidinguppanel.SlidingUpPanelLayout

class MainActivity : AppCompatActivity(), DepartmentHistoryAdapter.Listener, DepartmentFavoriteAdapter.Listener {
    private lateinit var binding: ActivityMainBinding
    private val adapter = DepartmentHistoryAdapter(this)
    private val adapterFavorite = DepartmentFavoriteAdapter(this)
    private val departmentList = listOf(
        DepartmentForHistory("Отделение ВТБ", "11-я Московская"),
        DepartmentForHistory("Банк ВТБ", "3-я Дачная")
    )//TODO: динамическое заполнение
    private val departmentFavoiriteList = listOf(
        DepartmentFavorite("ВТБ", "1-я Ленинская"),
        DepartmentFavorite("Банк ВТБ", "2-й проспект Строителей")
    )//TODO: динамическое заполнение
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.slidingUpLayout)

        val slidingUpLayout : SlidingUpPanelLayout = findViewById(R.id.slidingUpLayout)
        slidingUpLayout.panelHeight = 300
        slidingUpLayout.anchorPoint = 0.5f
        slidingUpLayout.coveredFadeColor = resources.getColor(R.color.transparent)

        init()
    }

    private fun init(){
        binding.apply {
            includedLayout.recycleView.layoutManager = LinearLayoutManager(this@MainActivity)
            includedLayout.recycleView.adapter = adapter
            adapter.addDepartmentAll(departmentList)

            includedLayout.favouritesRecycleView.layoutManager = LinearLayoutManager(this@MainActivity)
            includedLayout.favouritesRecycleView.adapter = adapterFavorite
            adapterFavorite.addDepartmentAll(departmentFavoiriteList)
        }
    }

    override fun onClickItem(department: DepartmentForHistory) {
        Log.e("RcView", "нажат на $department")
        //TODO: подставка
    }

    override fun onClickDeleteItem(department: DepartmentForHistory) {
        adapter.deleteDepartment(department)
    }

    override fun onClickItem(department: DepartmentFavorite) {
        Log.e("RcView", "нажат на $department")
        //TODO: подставка
    }

    override fun onClickDeleteItem(department: DepartmentFavorite) {
        adapterFavorite.deleteDepartment(department)
    }
}