package com.example.vtbapiapp

import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.Button
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
    private var slidingUpPanelStartAnchor: Float = 0.5F
    private var slidingUpPanelDepartmentInfoAnchor: Float = 0F //считается на OnCreate
    private var slidingUpPanelRouteAnchor: Float = 0F //считается на OnCreate

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

    private lateinit var departmentInfoIncludedLayout: ViewGroup

    private lateinit var customScrollView: CustomScrollView

    private var startSlidingUpPanelHeight: Int = 0 //считается на OnCreate
    private var departmentInfoSlidingUpPanelHeight: Int = 0 //считается на OnCreate
    private var routeSlidingUpPanelHeight: Int = 0 //считается на OnCreate

    private var appHeightPixels = 0
    private var appWidthPixels = 0

    private lateinit var departmentInfoReklamaImageView: ImageView
    private lateinit var departmentNameTextView:TextView
    private lateinit var cancelDepartmentInfoImageButton: ImageButton
    private lateinit var starImageView1: ImageView
    private lateinit var starImageView2: ImageView
    private lateinit var starImageView3: ImageView
    private lateinit var starImageView4: ImageView
    private lateinit var starImageView5: ImageView
    private lateinit var countsOfCommentsTextView: TextView
    private lateinit var addressConstTextView: TextView
    private lateinit var addressDataTextView: TextView
    private lateinit var contactsConstTextView: TextView
    private lateinit var contactsDataTextView: TextView
    private lateinit var telegrammImageButton: ImageButton
    private lateinit var okImageButton: ImageButton
    private lateinit var vkImageButton: ImageButton
    private lateinit var workTimesConsttextView: TextView
    private lateinit var workTimesDataTextView: TextView
    private lateinit var workSheduleImageView: ImageView
    private lateinit var workSheduleTextView: TextView
    private lateinit var routeImageButton: ImageButton
    private lateinit var favouriteImageButton: ImageButton
    private lateinit var phoneImageButton: ImageButton
    private lateinit var wwwImageButton: ImageButton
    private lateinit var shareImageButton: ImageButton
    private lateinit var goImageButton: ImageButton
    private lateinit var loadConstTextView: TextView
    private lateinit var loadDataTextView: TextView

    private lateinit var routeIncludedLayout: ViewGroup
    private lateinit var cancelConstantTextView: TextView
    private lateinit var addressFromEditText: EditText
    private lateinit var addressToEditText: EditText
    private lateinit var cancelRouteImageButton: ImageButton
    private lateinit var goButton: Button


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

        val displayMetrics = resources.displayMetrics
        val screenWidth = displayMetrics.widthPixels
        val screenHeight = displayMetrics.heightPixels
        Log.e("Размеры экрана", "screenWidth = $screenWidth, screenHeight = $screenHeight")

        val res = resources
        val pixelValue = (res.getDimension(R.dimen.heightOfSearchEditText) + (res.getDimension(R.dimen.marginTop) * 2)).toInt()
        slidingUpLayout.panelHeight = pixelValue // Динамичная высота под любой экран
        startSlidingUpPanelHeight = pixelValue

        slidingUpLayout.anchorPoint = slidingUpPanelStartAnchor
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
        val displayMetrics = resources.displayMetrics
        appWidthPixels = displayMetrics.widthPixels
        appHeightPixels = displayMetrics.heightPixels

        findViewById<ImageView>(R.id.lineImageView).setOnClickListener{
            if (slidingUpLayout.panelState != SlidingUpPanelLayout.PanelState.ANCHORED) slidingUpLayout.panelState = SlidingUpPanelLayout.PanelState.ANCHORED
        else slidingUpLayout.panelState = SlidingUpPanelLayout.PanelState.COLLAPSED }

        val res = resources
        var pixelValue = (res.getDimension(R.dimen.departmentInfoPictureHeight) + res.getDimension(R.dimen.departmentInfoTitleMarginTop)
                + res.getDimension(R.dimen.departmentInfoTitleHeight) + res.getDimension(R.dimen.departmentInfoContentUnderTitleMarginTop)
                + res.getDimension(R.dimen.departmentInfoConstantsHeight) + res.getDimension(R.dimen.departmentInfoDateMarginTop)
                + res.getDimension(R.dimen.departmentInfoDateHeight)).toInt()
        departmentInfoSlidingUpPanelHeight = pixelValue

        pixelValue = (res.getDimension(R.dimen.departmentInfoPictureHeight)
                + res.getDimension(R.dimen.departmentInfoTitleHeight)
                + (res.getDimension(R.dimen.departmentInfoConstantsHeight) * 4)
                + (res.getDimension(R.dimen.departmentInfoDateHeight) * 3)
                + (res.getDimension(R.dimen.heightOfSearchEditText) * 3)
                ).toInt()

        Log.e("pixelValue", "$pixelValue")
        Log.e("appHeightPixels", "$appHeightPixels")

        slidingUpPanelDepartmentInfoAnchor = pixelValue.toFloat()/appHeightPixels

        Log.e("slidingUpPanelDepartmentInfoAnchor", "$slidingUpPanelDepartmentInfoAnchor")

        pixelValue = (res.getDimension(R.dimen.departmentInfoTitleHeight) + res.getDimension(R.dimen.routeTitleMarginTop)
                + res.getDimension(R.dimen.heightOfSearchEditText) * 3 + res.getDimension(R.dimen.marginTop)
                ).toInt()

        slidingUpPanelRouteAnchor = pixelValue.toFloat()/appHeightPixels

        pixelValue = (res.getDimension(R.dimen.departmentInfoTitleHeight) + res.getDimension(R.dimen.routeTitleMarginTop)
                ).toInt()

        routeSlidingUpPanelHeight = pixelValue

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
            if (slidingUpLayout.panelState != SlidingUpPanelLayout.PanelState.ANCHORED) slidingUpLayout.panelState = SlidingUpPanelLayout.PanelState.ANCHORED
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

        departmentInfoIncludedLayout = findViewById(R.id.departmentInfoIncludedLayout)

        departmentInfoReklamaImageView = findViewById(R.id.departmentInfoReklamaImageView)
        departmentNameTextView  = findViewById(R.id.departmentNameTextView )
        cancelDepartmentInfoImageButton = findViewById(R.id.cancelDepartmentInfoImageButton)
        starImageView1 = findViewById(R.id.starImageView1)
        starImageView2 = findViewById(R.id.starImageView2)
        starImageView3 = findViewById(R.id.starImageView3)
        starImageView4 = findViewById(R.id.starImageView4)
        starImageView5 = findViewById(R.id.starImageView5)
        countsOfCommentsTextView = findViewById(R.id.countsOfCommentsTextView)
        addressConstTextView = findViewById(R.id.addressConstTextView)
        addressDataTextView = findViewById(R.id.addressDataTextView)
        contactsConstTextView = findViewById(R.id.contactsConstTextView)
        contactsDataTextView = findViewById(R.id.contactsDataTextView)
        telegrammImageButton = findViewById(R.id.telegrammImageButton)
        okImageButton = findViewById(R.id.okImageButton)
        vkImageButton = findViewById(R.id.vkImageButton)
        workTimesConsttextView = findViewById(R.id.workTimesConsttextView)
        workTimesDataTextView = findViewById(R.id.workTimesDataTextView)
        workSheduleImageView = findViewById(R.id.workSheduleImageView)
        workSheduleTextView = findViewById(R.id.workSheduleTextView)
        routeImageButton = findViewById(R.id.routeImageButton)
        favouriteImageButton = findViewById(R.id.favouriteImageButton)
        phoneImageButton = findViewById(R.id.phoneImageButton)
        wwwImageButton = findViewById(R.id.wwwImageButton)
        shareImageButton = findViewById(R.id.shareImageButton)
        goImageButton = findViewById(R.id.goImageButton)
        loadConstTextView = findViewById(R.id.loadConstTextView)
        loadDataTextView = findViewById(R.id.loadDataTextView)

        routeIncludedLayout = findViewById(R.id.routeIncludedLayout)
        cancelConstantTextView = findViewById(R.id.cancelConstantTextView)
        addressFromEditText = findViewById(R.id.addressFromEditText)
        addressToEditText = findViewById(R.id.addressToEditText)
        cancelRouteImageButton = findViewById(R.id.cancelRouteImageButton)
        goButton = findViewById(R.id.goButton)

        cancelDepartmentInfoImageButton.setOnClickListener { setMainLayout() }
        cancelRouteImageButton.setOnClickListener {
            routeIncludedLayout.visibility = View.GONE
            setDepartmentLayout()
        }
        routeImageButton.setOnClickListener { setRouteLayout() }
    }

    override fun onClickItem(department: DepartmentForHistory) {
        Log.e("RcView", "нажат на $department")
        departmentNameTextView.text = department.name
        addressDataTextView.text = department.address
        setDepartmentLayout()
        //TODO: подставка
    }

    override fun onClickDeleteItem(department: DepartmentForHistory) {
        adapter.deleteDepartment(department)
    }

    override fun onClickItem(department: DepartmentFavorite) {
        Log.e("RcView", "нажат на $department")
        departmentNameTextView.text = department.name
        addressDataTextView.text = department.address
        setDepartmentLayout()
        //TODO: подставка
    }

    override fun onClickDeleteItem(department: DepartmentFavorite) {
        adapterFavorite.deleteDepartment(department)
    }

    override fun onClickItem(department: Department) {
        Log.e("RcView", "нажат на $department")
        departmentNameTextView.text = department.name
        addressDataTextView.text = department.address
        loadDataTextView.text = department.load.toString()
        contactsDataTextView.text = department.phone
        workTimesDataTextView.text = department.workTime
        setDepartmentLayout()
        //TODO: подставка
    }

    override fun onClickDeleteItem(department: Department) {
        TODO("Not yet implemented")
    }

    private fun setDepartmentLayout(){
        searchEditText.visibility = View.INVISIBLE
        filterButtonWhenSearch.visibility = View.INVISIBLE
        searchedRecycleView.visibility = View.GONE

        moreButton.visibility = View.INVISIBLE
        filterButton.visibility = View.INVISIBLE
        reklamaImageView.visibility = View.INVISIBLE
        recentlySearchedTextView.visibility = View.INVISIBLE
        recentlySearchedRecycleView.visibility = View.GONE
        favouritesTextView.visibility = View.INVISIBLE
        favouritesRecycleView.visibility = View.GONE

        departmentInfoIncludedLayout.visibility = View.VISIBLE

        // параметры для высоты и ширины
        val layoutParams = ConstraintLayout.LayoutParams(
            ConstraintLayout.LayoutParams.MATCH_PARENT,  // Ширина
            appHeightPixels // Высота в пикселях или другой единице измерения
        )
        departmentInfoIncludedLayout.layoutParams = layoutParams
        slidingUpLayout.panelHeight = departmentInfoSlidingUpPanelHeight
        slidingUpLayout.anchorPoint = slidingUpPanelDepartmentInfoAnchor

        Log.e("Anchoe", "${slidingUpLayout.anchorPoint}")
        Log.e("Anchoe", "${slidingUpLayout.panelHeight}")
    }

    //TODO: Добавить анимацию кнопки

    private fun setMainLayout(){
        departmentInfoIncludedLayout.visibility = View.GONE
        searchEditText.visibility = View.VISIBLE
        if(searches){
            filterButtonWhenSearch.visibility = View.VISIBLE
            searchedRecycleView.visibility = View.VISIBLE
        }
        else{
            moreButton.visibility = View.VISIBLE
            filterButton.visibility = View.VISIBLE
            reklamaImageView.visibility = View.VISIBLE
            recentlySearchedTextView.visibility = View.VISIBLE
            recentlySearchedRecycleView.visibility = View.VISIBLE
            favouritesTextView.visibility = View.VISIBLE
            favouritesRecycleView.visibility = View.VISIBLE
        }

        slidingUpLayout.panelHeight = startSlidingUpPanelHeight
        slidingUpLayout.anchorPoint = slidingUpPanelStartAnchor

        Log.e("Anchoe", "${slidingUpLayout.anchorPoint}")
        Log.e("Anchoe", "${slidingUpLayout.panelHeight}")
    }

    private fun setRouteLayout(){
        departmentInfoIncludedLayout.visibility = View.GONE
        routeIncludedLayout.visibility = View.VISIBLE

        // параметры для высоты и ширины
        val layoutParams = ConstraintLayout.LayoutParams(
            ConstraintLayout.LayoutParams.MATCH_PARENT,  // Ширина
            appHeightPixels // Высота в пикселях или другой единице измерения
        )
        routeIncludedLayout.layoutParams = layoutParams
        slidingUpLayout.panelHeight = routeSlidingUpPanelHeight
        slidingUpLayout.anchorPoint = slidingUpPanelRouteAnchor

        Log.e("Anchoe", "${slidingUpLayout.anchorPoint}")
        Log.e("Anchoe", "${slidingUpLayout.panelHeight}")
    }
}