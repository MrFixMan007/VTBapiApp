package com.example.vtbapiapp

import android.os.Binder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.example.vtbapiapp.databinding.ActivityMainBinding
import com.sothree.slidinguppanel.SlidingUpPanelLayout

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    private val adapter = DepartmentHistoryAdapter()
    private val departmentList = listOf(
        DepartmentForHistory("Отделение ВТБ", "11-я Московская"),
        DepartmentForHistory("Банк ВТБ", "3-я Дачная")
    )
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
//            adapter.addDepartment(DepartmentForHistory("Отделение ВТБ", "11-я Московская"))
            adapter.addDepartmentAll(departmentList)
        }
    }
}