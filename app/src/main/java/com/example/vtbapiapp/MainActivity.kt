package com.example.vtbapiapp

import android.os.Bundle
import android.util.Log
import android.util.TypedValue
import android.view.View
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.animation.slideIn
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.vtbapiapp.databinding.ActivityMainBinding
import com.sothree.slidinguppanel.SlidingUpPanelLayout


class MainActivity : AppCompatActivity(), DepartmentHistoryAdapter.Listener, DepartmentFavoriteAdapter.Listener, DepartmentSearchedAdapter.Listener {
    private lateinit var binding: ActivityMainBinding

    private var searches: Boolean = false
    private var slidingUpPanelAnchor: Float = 0.5F

    private val adapter = DepartmentHistoryAdapter(this)
    private val adapterFavorite = DepartmentFavoriteAdapter(this)
    private val adapterSearched = DepartmentSearchedAdapter(this)

    private lateinit var slidingUpLayout : SlidingUpPanelLayout

    private lateinit var searchEditText: EditText
    private lateinit var moreButton: ImageButton
    private lateinit var filterButton: ImageButton
    private lateinit var reklamaImageView: ImageView
    private lateinit var recentlySearchedTextView: TextView
    private lateinit var recycleView: RecyclerView
    private lateinit var favouritesTextView: TextView
    private lateinit var favouritesRecycleView: RecyclerView
    private lateinit var searchedRecycleView: RecyclerView
    private lateinit var filterButtonWhenSearch: ImageButton


    private val departmentList = listOf(
        DepartmentForHistory("Отделение ВТБ", "11-я Московская"),
        DepartmentForHistory("Банк ВТБ", "3-я Дачная"),
    )//TODO: динамическое заполнение
    private val departmentFavoiriteList = listOf(
        DepartmentFavorite("ВТБ", "1-я Ленинская"),
        DepartmentFavorite("Банк ВТБ", "2-й проспект Строителей"),
        DepartmentFavorite("ВТБ", "1-я Ленинская"),
        DepartmentFavorite("Банк ВТБ", "2-й проспект Строителей"),
        DepartmentFavorite("ВТБ", "1-я Ленинская"),
        DepartmentFavorite("Банк ВТБ", "2-й проспект Строителей"),
        DepartmentFavorite("ВТБ", "1-я Ленинская"),
        DepartmentFavorite("Банк ВТБ", "2-й проспект Строителей"),
        DepartmentFavorite("ВТБ", "1-я Ленинская"),
        DepartmentFavorite("Банк ВТБ", "2-й проспект Строителей"),
        DepartmentFavorite("ВТБ", "1-я Ленинская"),
        DepartmentFavorite("Банк ВТБ", "2-й проспект Строителей"),
    )//TODO: динамическое заполнение
    private val departmentSearchList = listOf(
        Department("ВТБ", "2-я Троцкая", 11, 5, "+79956241379", "Круглосуточно"),
        Department("Банк ВТБ", "1-я Ленина", 110, 50, "+78854689754", "12-19"),
        Department("ВТБ", "2-я Троцкая", 11, 5, "+79956241379", "Круглосуточно"),
        Department("Банк ВТБ", "1-я Ленина", 110, 50, "+78854689754", "12-19"),
        Department("ВТБ", "2-я Троцкая", 11, 5, "+79956241379", "Круглосуточно"),
        Department("Банк ВТБ", "1-я Ленина", 110, 50, "+78854689754", "12-19"),
        Department("ВТБ", "2-я Троцкая", 11, 5, "+79956241379", "Круглосуточно"),

        )//TODO: динамическое заполнение
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.slidingUpLayout)

        initSlidingUpPanel()
        initAdapters()
        initOther()
    }

    private fun initAdapters(){
        binding.apply {
            includedLayout.searchedRecycleViewWhenSearch.layoutManager = LinearLayoutManager(this@MainActivity)
            includedLayout.searchedRecycleViewWhenSearch.adapter = adapterSearched
            adapterSearched.addDepartmentAll(departmentSearchList)

            includedLayout.recycleView.layoutManager = LinearLayoutManager(this@MainActivity)
            includedLayout.recycleView.adapter = adapter
            adapter.addDepartmentAll(departmentList)

            includedLayout.favouritesRecycleView.layoutManager = LinearLayoutManager(this@MainActivity)
            includedLayout.favouritesRecycleView.adapter = adapterFavorite
            adapterFavorite.addDepartmentAll(departmentFavoiriteList)
        }
    }

    private fun initSlidingUpPanel(){
        slidingUpLayout = findViewById(R.id.slidingUpLayout)

        val res = resources
        val pixelValue = (res.getDimension(R.dimen.heightOfSearchEditText) + (res.getDimension(R.dimen.marginTop) * 2)).toInt()
        slidingUpLayout.panelHeight = pixelValue // Динамичная высота под любой экран

        slidingUpLayout.anchorPoint = slidingUpPanelAnchor
        slidingUpLayout.coveredFadeColor = resources.getColor(R.color.transparent)
        slidingUpLayout.addPanelSlideListener(object : SlidingUpPanelLayout.PanelSlideListener {

            override fun onPanelSlide(panel: View, slideOffset: Float) {
                if (slideOffset > 0.7) slidingUpLayout.scrollY = 1//TODO: неполное развёртывание
                Log.i("Slide", "onPanelSlide, offset $slideOffset")
            }

            override fun onPanelStateChanged(
                panel: View?,
                previousState: SlidingUpPanelLayout.PanelState?,
                newState: SlidingUpPanelLayout.PanelState
            ) {
                Log.i("State", "onPanelStateChanged $newState")
            }
        })
        slidingUpLayout.setFadeOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View?) {
//              slidingUpLayout.setPanelState(PanelState.COLLAPSED)
                Log.i("Click", "clicked")
            }
        })
    }

    private fun initOther(){
        searchEditText = findViewById(R.id.searchEditText)

        moreButton = findViewById(R.id.moreButton)
        filterButton = findViewById(R.id.filterButton)
        reklamaImageView = findViewById(R.id.reklamaImageView)
        recentlySearchedTextView = findViewById(R.id.recentlySearchedTextView)
        recycleView = findViewById(R.id.recycleView)
        favouritesTextView = findViewById(R.id.favouritesTextView)
        favouritesRecycleView = findViewById(R.id.favouritesRecycleView)
        searchedRecycleView = findViewById(R.id.searchedRecycleViewWhenSearch)
        filterButtonWhenSearch = findViewById(R.id.filterButtonWhenSearch)

        var text: String
        searchEditText.addTextChangedListener {
            text = searchEditText.text.toString()
            if (text == "" && searches){
                searches = false
                filterButtonWhenSearch.visibility = View.INVISIBLE
                searchedRecycleView.visibility = View.INVISIBLE

                moreButton.visibility = View.VISIBLE
                filterButton.visibility = View.VISIBLE
                reklamaImageView.visibility = View.VISIBLE
                recentlySearchedTextView.visibility = View.VISIBLE
                recycleView.visibility = View.VISIBLE
                favouritesTextView.visibility = View.VISIBLE
                favouritesRecycleView.visibility = View.VISIBLE
            }
            else if (text != "" && !searches){
                searches = true
                filterButtonWhenSearch.visibility = View.VISIBLE
                searchedRecycleView.visibility = View.VISIBLE

                moreButton.visibility = View.INVISIBLE
                filterButton.visibility = View.INVISIBLE
                reklamaImageView.visibility = View.INVISIBLE
                recentlySearchedTextView.visibility = View.INVISIBLE
                recycleView.visibility = View.INVISIBLE
                favouritesTextView.visibility = View.INVISIBLE
                favouritesRecycleView.visibility = View.INVISIBLE
            }
        }
        moreButton.setOnClickListener {
            Log.e("RcView", "нажат на ${slidingUpLayout.panelState}")
            //TODO: выдвижение настроек
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

    override fun onClickItem(department: Department) {
        Log.e("RcView", "нажат на $department")
        //TODO: подставка
    }

    override fun onClickDeleteItem(department: Department) {
        TODO("Not yet implemented")
    }
}