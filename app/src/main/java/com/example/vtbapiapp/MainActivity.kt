package com.example.vtbapiapp

import android.opengl.Visibility
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
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
    private lateinit var recentlySearchedRecycleView: RecyclerView
    private lateinit var favouritesTextView: TextView
    private lateinit var favouritesRecycleView: RecyclerView
    private lateinit var searchedRecycleView: RecyclerView
    private lateinit var filterButtonWhenSearch: ImageButton

    private lateinit var customScrollView: CustomScrollView

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
            includedLayout.searchedRecycleViewWhenSearch.isNestedScrollingEnabled = false

            includedLayout.recentlySearchedRecycleView.layoutManager = LinearLayoutManager(this@MainActivity)
            includedLayout.recentlySearchedRecycleView.adapter = adapter
            adapter.addDepartmentAll(departmentList)
            includedLayout.recentlySearchedRecycleView.isNestedScrollingEnabled = false

//            val incl: ViewGroup = findViewById(R.id.includedLayout) //TODO: ля удобства все дочерние элементы находить так и делать невидимыми в цикле
//            Log.i("child", "${incl.getChildAt(7).accessibilityClassName}")

            includedLayout.favouritesRecycleView.layoutManager = LinearLayoutManager(this@MainActivity)
            includedLayout.favouritesRecycleView.adapter = adapterFavorite
            adapterFavorite.addDepartmentAll(departmentFavoiriteList)
            includedLayout.favouritesRecycleView.isNestedScrollingEnabled = false

//            val layoutParams: ConstraintLayout.LayoutParams = ConstraintLayout.LayoutParams(
//                ConstraintLayout.LayoutParams.WRAP_CONTENT, // Ширина кнопки
//                ConstraintLayout.LayoutParams.WRAP_CONTENT  // Высота кнопки
//            )
//            layoutParams.topToBottom = R.id.includedLayout
//            //TODO: кнопки над SlidingUpPanel
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
                //TODO: неполное развёртывание
                Log.i("Slide", "onPanelSlide, offset $slideOffset")
            }

            override fun onPanelStateChanged(
                panel: View?,
                previousState: SlidingUpPanelLayout.PanelState?,
                newState: SlidingUpPanelLayout.PanelState
            ) {
                if(newState.equals(SlidingUpPanelLayout.PanelState.EXPANDED)) {
                    customScrollView.setScrollingEnabled(true)
                    Log.i("Раскрыто", "панель раскрыта, теперь customScrollView.isEnabled = ${customScrollView.isEnabled} \n" +
                            "и slidingUpLayout.isTouchEnabled = ${slidingUpLayout.isTouchEnabled}")
                }
                else {
                    customScrollView.setScrollingEnabled(false)
                    Log.i("Закрыто", "панель закрыта, теперь customScrollView.isEnabled = ${customScrollView.isEnabled} \n" +
                            "и slidingUpLayout.isTouchEnabled = ${slidingUpLayout.isTouchEnabled}")
                }
                Log.i("Состояние", "Состояние поменялось с $previousState на $newState")
            }
        })
    }

    private fun initOther(){
        searchEditText = findViewById(R.id.searchEditText)

        moreButton = findViewById(R.id.moreButton)
        filterButton = findViewById(R.id.filterButton)
        reklamaImageView = findViewById(R.id.reklamaImageView)
        recentlySearchedTextView = findViewById(R.id.recentlySearchedTextView)
        recentlySearchedRecycleView = findViewById(R.id.recentlySearchedRecycleView)
        favouritesTextView = findViewById(R.id.favouritesTextView)
        favouritesRecycleView = findViewById(R.id.favouritesRecycleView)
        searchedRecycleView = findViewById(R.id.searchedRecycleViewWhenSearch)
        filterButtonWhenSearch = findViewById(R.id.filterButtonWhenSearch)

        customScrollView = findViewById(R.id.scrollView)
        customScrollView.setScrollingEnabled(false)

        customScrollView.setOnScrollChangeListener(View.OnScrollChangeListener { view, i, i1, i2, i3 ->
            if(i1 > i3) slidingUpLayout.isTouchEnabled = false
            if(!(view.canScrollVertically(-1)) && i1 == 0) {
                slidingUpLayout.isTouchEnabled = true
            }
            Log.e("canScrollVertically?", "${view.canScrollVertically(-1)}")
            Log.i("скролинг", "i = $i,  i1 = $i1,  i2 = $i2,  i3 = $i3")
        })

        var text: String
        searchEditText.addTextChangedListener {
            text = searchEditText.text.toString()
            if (text == "" && searches){
                searches = false
                filterButtonWhenSearch.visibility = View.GONE
                searchedRecycleView.visibility = View.GONE

                moreButton.visibility = View.VISIBLE
                filterButton.visibility = View.VISIBLE
                reklamaImageView.visibility = View.VISIBLE
                recentlySearchedTextView.visibility = View.VISIBLE
                recentlySearchedRecycleView.visibility = View.VISIBLE
                favouritesTextView.visibility = View.VISIBLE
                favouritesRecycleView.visibility = View.VISIBLE
            }
            else if (text != "" && !searches){
                searches = true
                filterButtonWhenSearch.visibility = View.VISIBLE
                searchedRecycleView.visibility = View.VISIBLE

                moreButton.visibility = View.GONE
                filterButton.visibility = View.GONE
                reklamaImageView.visibility = View.GONE
                recentlySearchedTextView.visibility = View.GONE
                recentlySearchedRecycleView.visibility = View.GONE
                favouritesTextView.visibility = View.GONE
                favouritesRecycleView.visibility = View.GONE
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