package com.example.vtbapiapp

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Geocoder
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.app.ActivityCompat
import androidx.core.view.GravityCompat
import androidx.core.widget.addTextChangedListener
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.example.vtbapiapp.*
import com.example.vtbapiapp.common.Common
import com.example.vtbapiapp.database.AppDatabase
import com.example.vtbapiapp.database.dtos.DepartmentDto
import com.example.vtbapiapp.database.dtos.LocalityDto
import com.example.vtbapiapp.database.entitys.DepartmentEntity
import com.example.vtbapiapp.database.entitys.FavoritesEntity
import com.example.vtbapiapp.database.entitys.LocalityEntity
import com.example.vtbapiapp.database.entitys.MyLocality
import com.example.vtbapiapp.database.entitys.WorkDaysEntity
import com.example.vtbapiapp.database.entitys.withEntity.FavoriteDepartmentWithDepartment
import com.example.vtbapiapp.database.entitys.withEntity.RecentlyDepartmentWithDepartment
import com.example.vtbapiapp.databinding.ActivityMainBinding
import com.google.android.material.navigation.NavigationView
import com.sothree.slidinguppanel.SlidingUpPanelLayout
import com.yandex.mapkit.MapKitFactory
import com.yandex.mapkit.RequestPoint
import com.yandex.mapkit.RequestPointType
import com.yandex.mapkit.directions.DirectionsFactory
import com.yandex.mapkit.directions.driving.DrivingOptions
import com.yandex.mapkit.directions.driving.DrivingRoute
import com.yandex.mapkit.directions.driving.DrivingRouter
import com.yandex.mapkit.directions.driving.DrivingSession
import com.yandex.mapkit.directions.driving.VehicleOptions
import com.yandex.mapkit.geometry.Point
import com.yandex.mapkit.map.CameraPosition
import com.yandex.mapkit.map.IconStyle
import com.yandex.mapkit.map.InputListener
import com.yandex.mapkit.map.Map
import com.yandex.mapkit.map.MapObjectCollection
import com.yandex.mapkit.map.PlacemarkCreatedCallback
import com.yandex.mapkit.map.PlacemarkMapObject
import com.yandex.mapkit.map.PolylineMapObject
import com.yandex.mapkit.mapview.MapView
import com.yandex.mapkit.search.SearchFactory
import com.yandex.mapkit.search.SearchManagerType
import com.yandex.mapkit.search.SearchOptions
import com.yandex.mapkit.search.SearchType
import com.yandex.mapkit.search.Session.SearchListener
import com.yandex.mapkitdemo.routing.DepartmentSearchedAdapter
import com.yandex.runtime.Error
import com.yandex.runtime.image.ImageProvider
import com.yandex.runtime.network.NetworkError
import kotlinx.coroutines.runBlocking
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.time.DayOfWeek
import java.time.LocalDate
import java.util.Locale


class MainActivity : AppCompatActivity(), DepartmentHistoryAdapter.Listener, DepartmentFavoriteAdapter.Listener, DepartmentSearchedAdapter.Listener, ChatAdapter.Listener, CitiesAdapter.Listener {
    private lateinit var binding: ActivityMainBinding
    companion object {
        lateinit var database: AppDatabase
    }
    val retrofitServices = Common.retrofitService
    private var searches: Boolean = false
    private var slidingUpPanelStartAnchor: Float = 0.6F
    private var slidingUpPanelDepartmentInfoAnchor: Float = 0F //считается на OnCreate
    private var slidingUpPanelRouteAnchor: Float = 0F //считается на OnCreate

    private val adapter = DepartmentHistoryAdapter(this)
    private val adapterFavorite = DepartmentFavoriteAdapter(this)
    private val adapterSearched = DepartmentSearchedAdapter(this)
    private val adapterChat = ChatAdapter(this, this)
    private val citiesAdapter = CitiesAdapter(this)

    private lateinit var slidingUpLayout : SlidingUpPanelLayout

    private lateinit var addressFromEditText: EditText
    private lateinit var moreButton: ImageButton
    private lateinit var filterButton: ImageButton
    private lateinit var reklamaImageView: ImageView
    private lateinit var reklamaImageView2: ImageView
    private lateinit var recentlySearchedTextView: TextView
    private lateinit var recentlySearchedRecycleView: RecyclerView
    private lateinit var favouritesTextView: TextView
    private lateinit var favouritesRecycleView: RecyclerView
    private lateinit var searchedRecycleViewWhenSearch: RecyclerView
    private lateinit var filterButtonWhenSearch: ImageButton

    private lateinit var mainIncludedLayout: ViewGroup
    private lateinit var departmentInfoIncludedLayout: ViewGroup

    private lateinit var customScrollView: CustomScrollView

    private var startSlidingUpPanelHeight: Int = 0 //считается на OnCreate
    private var departmentInfoSlidingUpPanelHeight: Int = 0 //считается на OnCreate
    private var routeSlidingUpPanelHeight: Int = 0 //считается на OnCreate

    private var appHeightPixels = 0
    private var appWidthPixels = 0

    private lateinit var departmentInfoReklamaImageView: ImageView
    private  val chatId: String = runBlocking {
        var res:String = ""
        val call = retrofitServices.createChat()
        call.enqueue(object : Callback<String>{
        override fun onResponse(call: Call<String>, response: Response<String>) {
            if (response.isSuccessful) {
                res = response.body()?:""

                Log.e("chat", res)
            }

            else {
                res = ""
                Log.e("chat", "Данные")
            }
        }
        override fun onFailure(call: Call<String>, t: Throwable) {
            res = ""
            Log.e("Ошибка подключения", t.message.toString())
        }

    })
        return@runBlocking res
    }
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
//    private lateinit var addressFromEditText: EditText
    private lateinit var addressToEditText: EditText
    private lateinit var cancelRouteImageButton: ImageButton
    private lateinit var goButton: ImageButton

//    private lateinit var callAssistantButton: Button
    private lateinit var assistantCancelImageButton: ImageButton
    private lateinit var sendToAssistantImageButton: ImageButton
    private lateinit var assistantIncludedLayout: ViewGroup
    private lateinit var assistantEditText: EditText

    private lateinit var chatRecyclerView: RecyclerView
    private lateinit var editRouteImageButton: ImageButton
    private lateinit var changeRouteIncludedLayout: ViewGroup
    private lateinit var cancelChangeRouteTextView: TextView
    private lateinit var addressToChangeRouteEditText: EditText

    private lateinit var driven: DrawerLayout
    private lateinit var navigation_view: NavigationView

    private lateinit var scrollView: CustomScrollView
    private lateinit var clearSearchAddressToImageView: ImageView

    private lateinit var callChatImageButton: ImageButton
    private lateinit var showLocationImageButton: ImageButton

//
//    private val departmentList = listOf(
//        DepartmentForHistory("Отделение ВТБ", "11-я Московская"),
//        DepartmentForHistory("Банк ВТБ", "3-я Дачная"),
//    )//TODO: динамическое заполнение
//    private val departmentFavoiriteList = listOf(
//        DepartmentFavorite("ВТБ", "1-я Ленинская"),
//    )//TODO: динамическое заполнение
//    private val departmentSearchList = listOf(
//        Department("ВТБ", "2-я Троцкая", 11, 5, "+79956241379", "Круглосуточно"),
//        Department("ВТБ", "2-я Троцкая", 11, 5, "+79956241379", "Круглосуточно"),
//        Department("ВТБ", "2-я Троцкая", 11, 5, "+79956241379", "Круглосуточно"),
//        )//TODO: динамическое заполнение

    // Код для карт _____________________________________________
    private lateinit var mapView: MapView
    private lateinit var map: Map
    private lateinit var locationManager: LocationManager
    private val PERMISSIONS_REQUEST_FINE_LOCATION = 1

    private val inputListener = object : InputListener {
        override fun onMapTap(map: Map, point: Point) = Unit

        override fun onMapLongTap(map: Map, point: Point) {
            routePoints = routePoints + point
        }
    }

    private val drivingRouteListener = object : DrivingSession.DrivingRouteListener {
        override fun onDrivingRoutes(drivingRoutes: MutableList<DrivingRoute>) {
            routes = drivingRoutes
        }

        override fun onDrivingRoutesError(error: Error) {
            val errorMessage = when (error) {
                is NetworkError -> "Routes request error due to network issues"
                else -> "Routes request unknown error"
            }
            Toast.makeText(this@MainActivity, errorMessage, Toast.LENGTH_SHORT).show()
        }
    }

    private var routePoints = emptyList<Point>()
        set(value) {
            field = value
            onRoutePointsUpdated()
        }

    private var routes = emptyList<DrivingRoute>()
        set(value) {
            field = value
            onRoutesUpdated()
        }

    private lateinit var drivingRouter: DrivingRouter
    private var drivingSession: DrivingSession? = null
    private lateinit var placemarksCollection: MapObjectCollection
    private lateinit var routesCollection: MapObjectCollection
    private lateinit var listOfCitiesIncludedLayout: ViewGroup
    private lateinit var clearSearchCityImageView: ImageView
    private lateinit var searchedCityRecycleView: RecyclerView

    //_____________________________________________

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        database = Room.databaseBuilder(
            this,
            AppDatabase::class.java,
            "my_database"
        ).build()
        runBlocking {
            database.workDaysDAO().insertWorkDays(WorkDaysEntity(1, "9", "20","9", "20","9", "20","9", "20","9", "20","9", "20","9", "20"))
            database.workDaysDAO().insertWorkDays(WorkDaysEntity(2, "Круглосуточно", "Круглосуточно", "Круглосуточно", "Круглосуточно","Круглосуточно", "Круглосуточно","Круглосуточно", "Круглосуточно","Круглосуточно", "Круглосуточно","Круглосуточно", "Круглосуточно","Круглосуточно", "Круглосуточно"))
            database.departmentDAO().insertDepartments(DepartmentEntity(1, null, 1, 1, "Политехническая улица, 65/1", "55", "45", "450045", "", "+79576841346", "тип", "*", "avia", false, false, false ))
            database.departmentDAO().insertDepartments(DepartmentEntity(2, null, 2, 2, "Беговая улица, 2а к1, 1 этаж", "55", "45", "450045", "", "+79576841346", "тип", "*", "avia", false, false, false ))
            database.favoriteDepartmentDAO().insertFavorite(FavoritesEntity(1,1))
            database.favoriteDepartmentDAO().insertFavorite(FavoritesEntity(2,2))
        }

        MapKitFactory.setApiKey("1d2e8d46-8a93-4908-9d08-55f914630072")
        MapKitFactory.initialize(this)

        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.slidingUpLayout)

        initSlidingUpPanel()
        initAdapters()
        initOther()

        locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager

        // Проверяем разрешение на доступ к местоположению
        if (checkLocationPermission()) {
            // Если разрешение есть, запрашиваем местоположение пользователя
            requestLocation()
        } else {
            // Если разрешение отсутствует, запрашиваем его
            requestLocationPermission()
        }

        mapView = findViewById(R.id.mapview)
        map = mapView.mapWindow.map
        map.addInputListener(inputListener)

        // Создаем коллекции для маркеров и маршрутов на карте
        placemarksCollection = map.mapObjects.addCollection()
        routesCollection = map.mapObjects.addCollection()

        // Инициализируем DrivingRouter для построения маршрутов
        drivingRouter = DirectionsFactory.getInstance().createDrivingRouter()

//        map.mapObjects.addPlacemark().apply {
//            geometry = Point(59.935493, 30.327392)
//            setIcon(ImageProvider.fromResource(this@MainActivity, R.drawable.marker_png_48_ico_black))
//        }
//        map.mapObjects.addPlacemark().apply {
//            geometry = Point(69.935493, 30.327392)
//            setIcon(ImageProvider.fromResource(this@MainActivity, R.drawable.marker_png_48_ico_black))
//        }
//        map.mapObjects.addPlacemark().apply {
//            geometry = Point(55.935493, 22.327392)
//            setIcon(ImageProvider.fromResource(this@MainActivity, R.drawable.marker_png_48_ico_black))
//        }TODO:map

    }

    private fun initAdapters(){
        val departmentFavoriteList: List<FavoriteDepartmentWithDepartment> = runBlocking {
            database.favoriteDepartmentDAO().getAllFavorites()
        }
        val departmentList = runBlocking { database.recentlyDepartmentDAO().getAllRecentlyDepartments()}
        var departmentSearchList:List<DepartmentDto> = mutableListOf()
        runBlocking {
            val myLocalityWithDepartment = database.myLocalityDAO().getMyLocalityWithLocality()
            val call = myLocalityWithDepartment?.localityEntity?.id?.let {
                retrofitServices.getLocalityById(
                    it
                )
            }

            call?.enqueue(object : Callback<LocalityDto> {
                override fun onResponse(call: Call<LocalityDto>, response: Response<LocalityDto>) {
                    if (response.isSuccessful) {
                        departmentSearchList = response.body()?.departmentDtoList?: mutableListOf()

                    } else {
                        departmentSearchList = mutableListOf()
                        Log.e("Ошибка данных","Ошибка при получении данных с сервера")
                    }
                }

                override fun onFailure(call: Call<LocalityDto>, t: Throwable) {
                    departmentSearchList = mutableListOf()
                    Log.e("Ошибка подключегия",t.message.toString())
                }
            })

        }
        val drivingRouter = DirectionsFactory.getInstance().createDrivingRouter()
        val drivingOptions = DrivingOptions().apply {
            routesCount = 2
        }
        val vehicleOptions = VehicleOptions()
        val points = buildList {
            add(RequestPoint(Point(25.196141, 55.278543), RequestPointType.WAYPOINT, null,null))
            add(RequestPoint(Point(25.171148, 55.238034), RequestPointType.WAYPOINT, null,null))
        }

        val drivingSession = drivingRouter.requestRoutes(
            points,
            drivingOptions,
            vehicleOptions,
            drivingRouteListener
        )
//        drivingRouter.requestRoutesSummary(points,DrivingOptions(),VehicleOptions(),drivingSession.)
        val currentDayOfWeek = LocalDate.now().dayOfWeek


        val departmentSearchedAdapter = departmentSearchList.map { SearchDepartment(it,"",(getWorkLoad(it)?: mutableMapOf()).toString(),getDay(it,currentDayOfWeek)) }

        val citiesSearchList = mutableListOf<Locality>()
        citiesSearchList.addAll(getAllLocality().map { Locality(it.key,it.value) }.toMutableList())
        citiesSearchList.add(Locality(1,"Москва"))
        citiesSearchList.add(Locality(2,"Питер"))
        citiesSearchList.add(Locality(3,"Татарстан"))
        citiesSearchList.add(Locality(4,"Цех"))
        citiesSearchList.add(Locality(5,"ЦЫЦ"))

        binding.apply {
            includedLayout.searchedRecycleViewWhenSearch.layoutManager = LinearLayoutManager(this@MainActivity)
            includedLayout.searchedRecycleViewWhenSearch.adapter = adapterSearched
            adapterSearched.addDepartmentAll(departmentSearchedAdapter)
            includedLayout.searchedRecycleViewWhenSearch.isNestedScrollingEnabled = false

            includedLayout.recentlySearchedRecycleView.layoutManager = LinearLayoutManager(this@MainActivity)
            includedLayout.recentlySearchedRecycleView.adapter = adapter
            adapter.addDepartmentAll(departmentList)
            includedLayout.recentlySearchedRecycleView.isNestedScrollingEnabled = false

            includedLayout.favouritesRecycleView.layoutManager = LinearLayoutManager(this@MainActivity)
            includedLayout.favouritesRecycleView.adapter = adapterFavorite
            adapterFavorite.addDepartmentAll(departmentFavoriteList)
            includedLayout.favouritesRecycleView.isNestedScrollingEnabled = false

            chatRecyclerView = findViewById(R.id.chatRecyclerView)
            chatRecyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
            chatRecyclerView.adapter = adapterChat
            chatRecyclerView.isNestedScrollingEnabled = false

            searchedCityRecycleView = findViewById(R.id.searchedCityRecycleView)
            searchedCityRecycleView.layoutManager = LinearLayoutManager(this@MainActivity)
            searchedCityRecycleView.adapter = citiesAdapter
            val call= retrofitServices.findAllLocalityNamesOnly()
            var localityNames = mapOf<Long,String>()
            call.enqueue(object : Callback<kotlin.collections.Map<Long, String>> {
                override fun onResponse(
                    call: Call<kotlin.collections.Map<Long, String>>,
                    response: Response<kotlin.collections.Map<Long, String>>
                ) {
                    if (response.isSuccessful) {
                        localityNames  = response.body()?: mapOf<Long, String>()
                        citiesAdapter.addCityAll(localityNames.map { Locality(it.key,it.value) })
                        citiesAdapter.addCityAll(localityNames.map { Locality(it.key,it.value) })
                        citiesAdapter.addCityAll(localityNames.map { Locality(it.key,it.value) })
                        citiesAdapter.addCityAll(localityNames.map { Locality(it.key,it.value) })
                        Log.e("Locality","$localityNames")
                    } else {
                        Log.e("Locality","Ошибка запроса")
                    }
                }

                override fun onFailure(call: Call<kotlin.collections.Map<Long, String>>, t: Throwable) {
                    Log.e("Locality","$t")
                }
            })

            searchedCityRecycleView.isNestedScrollingEnabled = false

            Log.i("city", citiesAdapter.localityList.toString())

//            val layoutParams: ConstraintLayout.LayoutParams = ConstraintLayout.LayoutParams(
//                ConstraintLayout.LayoutParams.WRAP_CONTENT, // Ширина кнопки
//                ConstraintLayout.LayoutParams.WRAP_CONTENT  // Высота кнопки
//            )
//            layoutParams.topToBottom = R.id.includedLayout
//            //TODO: кнопки над SlidingUpPanel
        }
    }

    private fun getAllLocality():kotlin.collections.Map<Long, String>{
        val call= retrofitServices.findAllLocalityNamesOnly()
        var localityNames = mapOf<Long,String>()
        call.enqueue(object : Callback<kotlin.collections.Map<Long, String>> {
            override fun onResponse(
                call: Call<kotlin.collections.Map<Long, String>>,
                response: Response<kotlin.collections.Map<Long, String>>
            ) {
                if (response.isSuccessful) {
                    localityNames  = response.body()?: mapOf<Long, String>()
                    Log.e("Locality","$localityNames")
                } else {
                    Log.e("Locality","Ошибка запроса")
                }
            }

            override fun onFailure(call: Call<kotlin.collections.Map<Long, String>>, t: Throwable) {
                Log.e("Locality","$t")
            }
        })
        return localityNames
    }
    private fun getWorkLoad(departmentDto: DepartmentDto) :kotlin.collections.Map<Long,Int>?{
        val call = retrofitServices.getWorkloadOfDepartment(departmentDto.id)
        var result: kotlin.collections.Map<Long, Int>? = null
        call.enqueue(object : Callback<kotlin.collections.Map<Long, Int>>{
            override fun onResponse(call: Call<kotlin.collections.Map<Long, Int>>,
                                    response: Response<kotlin.collections.Map<Long, Int>>) {
                if (response.isSuccessful) {
                    result = response.body()
                } else {
                    Log.e("Ошибка данных", "Ошибка при получении данных с сервера")
                }
            }
            override fun onFailure(call: Call<kotlin.collections.Map<Long, Int>>, t: Throwable) {
                Log.e("Ошибка подключения", t.message.toString())
            }
        })
        return result
    }

    fun getDay(department: DepartmentDto,dayOfWeek: DayOfWeek):String{
        when (dayOfWeek) {
            DayOfWeek.MONDAY -> return "${department.workDaysFizDto?.mon_s} - ${department.workDaysFizDto?.mon_f}"
            DayOfWeek.TUESDAY -> return "${department.workDaysFizDto?.tue_s} - ${department.workDaysFizDto?.tue_f}"
            DayOfWeek.WEDNESDAY -> return "${department.workDaysFizDto?.wed_s} - ${department.workDaysFizDto?.wed_f}"
            DayOfWeek.THURSDAY -> return "${department.workDaysFizDto?.thu_s} - ${department.workDaysFizDto?.thu_f}"
            DayOfWeek.FRIDAY -> return "${department.workDaysFizDto?.fri_s} - ${department.workDaysFizDto?.fri_f}"
            DayOfWeek.SATURDAY -> return "${department.workDaysFizDto?.sat_s} - ${department.workDaysFizDto?.sat_f}"
            DayOfWeek.SUNDAY -> return "${department.workDaysFizDto?.sun_s} - ${department.workDaysFizDto?.sun_f}"
        }

    }
    private fun initSlidingUpPanel(){
        slidingUpLayout = findViewById(R.id.slidingUpLayout)

        val displayMetrics = resources.displayMetrics
        val screenWidth = displayMetrics.widthPixels
        val screenHeight = displayMetrics.heightPixels
        Log.e("Размеры экрана", "screenWidth = $screenWidth, screenHeight = $screenHeight")

        val res = resources
        val pixelValue = (res.getDimension(R.dimen.heightOfSearchEditText) + res.getDimension(R.dimen.marginTop) * 2).toInt()
        slidingUpLayout.panelHeight = pixelValue // Динамичная высота под любой экран
        startSlidingUpPanelHeight = pixelValue

        slidingUpLayout.anchorPoint = slidingUpPanelStartAnchor
        slidingUpLayout.coveredFadeColor = resources.getColor(R.color.transparent)
        slidingUpLayout.addPanelSlideListener(object : SlidingUpPanelLayout.PanelSlideListener {

            override fun onPanelSlide(panel: View, slideOffset: Float) {
                Log.i("Slide", "onPanelSlide, offset $slideOffset")
            }

            override fun onPanelStateChanged(
                panel: View?,
                previousState: SlidingUpPanelLayout.PanelState?,
                newState: SlidingUpPanelLayout.PanelState
            ) {
                if(newState == SlidingUpPanelLayout.PanelState.EXPANDED) {
                    customScrollView.setScrollingEnabled(true)
                    val layoutParams = ConstraintLayout.LayoutParams(
                        ConstraintLayout.LayoutParams.MATCH_PARENT,  // Ширина
                        ConstraintLayout.LayoutParams.WRAP_CONTENT // Высота в пикселях или другой единице измерения
                    )
                    departmentInfoReklamaImageView.layoutParams = layoutParams

                    Log.i("Раскрыто", "панель раскрыта, теперь customScrollView.isEnabled = ${customScrollView.isEnabled} \n" +
                            "и slidingUpLayout.isTouchEnabled = ${slidingUpLayout.isTouchEnabled}")
                }
                else {
                    if (newState == SlidingUpPanelLayout.PanelState.ANCHORED){
                        val layoutParams = ConstraintLayout.LayoutParams(
                            ConstraintLayout.LayoutParams.MATCH_PARENT,  // Ширина
                            500 // Высота в пикселях или другой единице измерения
                        )
                        departmentInfoReklamaImageView.layoutParams = layoutParams
                    }
                    else if(newState == SlidingUpPanelLayout.PanelState.COLLAPSED){
                        val layoutParams = ConstraintLayout.LayoutParams(
                            ConstraintLayout.LayoutParams.MATCH_PARENT,  // Ширина
                            200 // Высота в пикселях или другой единице измерения
                        )
                        departmentInfoReklamaImageView.layoutParams = layoutParams
                    }
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

        mainIncludedLayout = findViewById(R.id.includedLayout)
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

        pixelValue = (res.getDimension(R.dimen.heightOfSearchEditText) * 2
                ).toInt()

        slidingUpPanelRouteAnchor = pixelValue.toFloat()/appHeightPixels

        pixelValue = (res.getDimension(R.dimen.heightOfSearchEditText) * 2 + res.getDimension(R.dimen.marginTop) * 3
                ).toInt()

        routeSlidingUpPanelHeight = pixelValue

        addressFromEditText = findViewById(R.id.addressFromEditText)
        addressToEditText = findViewById(R.id.addressToEditText)

        moreButton = findViewById(R.id.moreButton)
        filterButton = findViewById(R.id.filterButton)
        reklamaImageView = findViewById(R.id.reklamaImageView)
        reklamaImageView2 = findViewById(R.id.reklamaImageView2)
        recentlySearchedTextView = findViewById(R.id.recentlySearchedTextView)
        recentlySearchedRecycleView = findViewById(R.id.recentlySearchedRecycleView)
        favouritesTextView = findViewById(R.id.favouritesTextView)
        favouritesRecycleView = findViewById(R.id.favouritesRecycleView)
        searchedRecycleViewWhenSearch = findViewById(R.id.searchedRecycleViewWhenSearch)
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

        addressToEditText.addTextChangedListener {
            if(slidingUpLayout.anchorPoint == slidingUpPanelRouteAnchor) slidingUpLayout.anchorPoint = slidingUpPanelStartAnchor
            if (slidingUpLayout.panelState != SlidingUpPanelLayout.PanelState.EXPANDED) slidingUpLayout.panelState = SlidingUpPanelLayout.PanelState.ANCHORED
            text = addressToEditText.text.toString()
            if (text == "" && searches){
                searches = false
                clearSearchAddressToImageView.visibility = View.INVISIBLE

                filterButtonWhenSearch.visibility = View.GONE
                searchedRecycleViewWhenSearch.visibility = View.GONE

                moreButton.visibility = View.VISIBLE
                filterButton.visibility = View.VISIBLE
                reklamaImageView.visibility = View.VISIBLE
                reklamaImageView2.visibility = View.VISIBLE
                recentlySearchedTextView.visibility = View.VISIBLE
                recentlySearchedRecycleView.visibility = View.VISIBLE
                favouritesTextView.visibility = View.VISIBLE
                favouritesRecycleView.visibility = View.VISIBLE
//                callAssistantButton.visibility = View.VISIBLE
            }
            else if (text != "" && !searches){
                searches = true
                clearSearchAddressToImageView.visibility = View.VISIBLE

                filterButtonWhenSearch.visibility = View.VISIBLE
                searchedRecycleViewWhenSearch.visibility = View.VISIBLE
//                callAssistantButton.visibility = View.GONE

                moreButton.visibility = View.INVISIBLE
                filterButton.visibility = View.GONE
                reklamaImageView.visibility = View.GONE
                reklamaImageView2.visibility = View.GONE
                clearSearchAddressToImageView.visibility = View.VISIBLE
                recentlySearchedTextView.visibility = View.GONE
                recentlySearchedRecycleView.visibility = View.GONE
                favouritesTextView.visibility = View.GONE
                favouritesRecycleView.visibility = View.GONE
            }
        }
        addressFromEditText.addTextChangedListener {
            if (slidingUpLayout.panelState != SlidingUpPanelLayout.PanelState.EXPANDED) slidingUpLayout.panelState = SlidingUpPanelLayout.PanelState.ANCHORED
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

//        callAssistantButton = findViewById(R.id.callAssistantButton)
        assistantIncludedLayout = findViewById(R.id.assistantIncludedLayout)
        assistantCancelImageButton = findViewById(R.id.assistantCancelImageButton)
        sendToAssistantImageButton = findViewById(R.id.sendToAssistantImageButton)
        assistantEditText = findViewById(R.id.assistantEditText)

        routeIncludedLayout = findViewById(R.id.routeIncludedLayout)
        cancelConstantTextView = findViewById(R.id.cancelConstantTextView)
        cancelRouteImageButton = findViewById(R.id.cancelRouteImageButton)
        goButton = findViewById(R.id.goButton)
        editRouteImageButton = findViewById(R.id.editRouteImageButton)

        cancelDepartmentInfoImageButton.setOnClickListener {
            setMainLayout()
        }
        cancelRouteImageButton.setOnClickListener {
            routeIncludedLayout.visibility = View.GONE
            setDepartmentLayout()
        }
        routeImageButton.setOnClickListener { setRoute() }

//        callAssistantButton.setOnClickListener { setAssistantLayout() }
        assistantCancelImageButton.setOnClickListener {
            assistantIncludedLayout.visibility = View.GONE
            mainIncludedLayout.visibility = View.VISIBLE
            setMainLayout()
            adapterChat.clearList()
            assistantEditText.setText("")
            clearSearchAddressToImageView.visibility = View.INVISIBLE
            slidingUpLayout.panelState = SlidingUpPanelLayout.PanelState.ANCHORED
        }
        sendToAssistantImageButton.setOnClickListener {
            val text = assistantEditText.text.toString()
            if(text != ""){
                val call = retrofitServices.writeMessage(chatId, ChatMessage(text).text)
                call.enqueue(object : Callback<String> {
                    override fun onResponse(call: Call<String>, response: Response<String>) {
                        if (response.isSuccessful) {
                            val result = response.body() ?: ""
                            adapterChat.addChatItem(ChatMessage(text))
                            adapterChat.addChatItem(ChatMessage(result, false))
                            Log.e("chat", result)
                        } else {
                            adapterChat.addChatItem(ChatMessage(text))
                            adapterChat.addChatItem(ChatMessage("Ошибка", false))
                        }
                    }

                    override fun onFailure(call: Call<String>, t: Throwable) {
                        adapterChat.addChatItem(ChatMessage(text))
                        Log.e("chat", t.toString())
                    }
                })



            }
        }
        editRouteImageButton.setOnClickListener { setChangeRouteLayout() }
        changeRouteIncludedLayout = findViewById(R.id.changeRouteIncludedLayout)
        addressToChangeRouteEditText = findViewById(R.id.addressToChangeRouteEditText)

        cancelConstantTextView.setOnClickListener{
            if(cancelConstantTextView.text.toString() == res.getString(R.string.cancel)) {
                addressFromEditText.setText("")
                addressToChangeRouteEditText.setText("")
                cancelConstantTextView.setText(res.getString(R.string.back))
            }
            else if(cancelConstantTextView.text.toString() == res.getString(R.string.back)) {
                cancelConstantTextView.setText(res.getString(R.string.cancel))
                changeRouteIncludedLayout.visibility = View.GONE
                setRoute()
            }
        }

        driven = findViewById(R.id.driven)
        driven.addDrawerListener(object : DrawerLayout.SimpleDrawerListener(){
            override fun onDrawerClosed(drawerView: View) {
                super.onDrawerClosed(drawerView)
                slidingUpLayout.panelState = SlidingUpPanelLayout.PanelState.ANCHORED
            }
        })
        listOfCitiesIncludedLayout = findViewById(R.id.listOfCitiesIncludedLayout)
        clearSearchCityImageView = findViewById(R.id.clearSearchCityImageView)

        var oldHeight = slidingUpLayout.panelHeight
        moreButton.setOnClickListener{
            oldHeight = slidingUpLayout.panelHeight
            val layoutParams: ViewGroup.LayoutParams = navigation_view.getLayoutParams()
            layoutParams.height = appHeightPixels
            navigation_view.setLayoutParams(layoutParams)
            slidingUpLayout.panelHeight = 0
            slidingUpLayout.panelState = SlidingUpPanelLayout.PanelState.COLLAPSED

            driven.openDrawer(GravityCompat.END)
        }
        clearSearchCityImageView.setOnClickListener{
            listOfCitiesIncludedLayout.visibility = View.INVISIBLE
            setMainLayout()
        }
        navigation_view = findViewById(R.id.navigation_view)
        navigation_view.setNavigationItemSelectedListener {
            when(it.itemId){
                R.id.listOfCitiesItem -> {
                    callChatImageButton.visibility = View.INVISIBLE

                    val call = retrofitServices.createChat()


                    setCitiesLayout()
                }
                R.id.settingsItem -> {
                    Toast.makeText(this, "Настройки в разработке", Toast.LENGTH_SHORT).show()
                    callChatImageButton.visibility = View.VISIBLE
                    clearSearchAddressToImageView.visibility = View.INVISIBLE
                }
            }
            slidingUpLayout.panelHeight = oldHeight
            slidingUpLayout.panelState = SlidingUpPanelLayout.PanelState.ANCHORED
            driven.closeDrawer(GravityCompat.END)
            true
        }
        scrollView = findViewById(R.id.scrollView)
        clearSearchAddressToImageView = findViewById(R.id.clearSearchAddressToImageView)

        clearSearchAddressToImageView.setOnClickListener{
            addressToEditText.setText("")
        }

        callChatImageButton = findViewById(R.id.callChatImageButton)
        callChatImageButton.setOnClickListener{
            setAssistantLayout()
        }
        showLocationImageButton = findViewById(R.id.showLocationImageButton)
        showLocationImageButton.setOnClickListener{
            if (checkLocationPermission()) {
                // Если разрешение есть, запрашиваем местоположение пользователя
                requestLocation()
            }
        }
    }

    override fun onStart() {
        super.onStart()
        MapKitFactory.getInstance().onStart()
        mapView.onStart()
    }

    override fun onStop() {
        super.onStop()
        MapKitFactory.getInstance().onStop()
        mapView.onStop()
    }

    override fun onClickItem(department: RecentlyDepartmentWithDepartment) {
        Log.e("RcView", "нажат на $department")
        departmentNameTextView.text = department.department.description
        addressDataTextView.text = department.department.address
        setDepartmentLayout()
        //TODO: подставка
    }

    override fun onClickDeleteItem(department: RecentlyDepartmentWithDepartment) {
        runBlocking { database.recentlyDepartmentDAO().deleteRecently(department.recentlyDepartment) }
        adapter.deleteDepartment(department)
    }

    override fun onClickItem(department: FavoriteDepartmentWithDepartment) {
        Log.e("RcView", "нажат на $department")
        departmentNameTextView.text = department.departmentEntity.description
        addressDataTextView.text = department.departmentEntity.address
        val currentDayOfWeek = LocalDate.now().dayOfWeek
        val workDays = runBlocking { database.departmentDAO().getDepartmentAndWorkDaysById(department.departmentEntity.id) }
        val time = when (currentDayOfWeek) {
            DayOfWeek.MONDAY ->  "${workDays.workDaysFiz.mon_s} - ${workDays.workDaysFiz.mon_f}"
            DayOfWeek.TUESDAY ->  "${workDays.workDaysFiz.tue_s} - ${workDays.workDaysFiz.tue_f}"
            DayOfWeek.WEDNESDAY ->  "${workDays.workDaysFiz.wed_s} - ${workDays.workDaysFiz.wed_f}"
            DayOfWeek.THURSDAY ->  "${workDays.workDaysFiz.thu_s} - ${workDays.workDaysFiz.thu_f}"
            DayOfWeek.FRIDAY ->  "${workDays.workDaysFiz.fri_s} - ${workDays.workDaysFiz.fri_f}"
            DayOfWeek.SATURDAY ->  "${workDays.workDaysFiz.sat_s} - ${workDays.workDaysFiz.sat_f}"
            DayOfWeek.SUNDAY ->  "${workDays.workDaysFiz.sun_s} - ${workDays.workDaysFiz.sun_f}"
        }
        workTimesDataTextView.text = time
        contactsDataTextView.text = department.departmentEntity.phone
        countsOfCommentsTextView.text = "2,5 тыс. отзывов"
        loadDataTextView.text = "25%"

        setDepartmentLayout()
        //TODO: подставка
    }
    override fun onClickDeleteItem(department: FavoriteDepartmentWithDepartment) {
        runBlocking { database.favoriteDepartmentDAO().deleteFavorite(department.favoritesEntity) }
        adapterFavorite.deleteDepartment(department)
    }

    override fun onClickItem(department: SearchDepartment) {
        //Депортаменты из поисковика
        departmentNameTextView.text = department.departmentDto.description
        addressDataTextView.text = department.departmentDto.address
//        loadDataTextView.text = department.load.toString()
//        contactsDataTextView.text = department.phone
//        workTimesDataTextView.text = department.workTime//
        setDepartmentLayout()
        Log.e("RcView", "нажат на $department")
        //TODO: подставка
    }

    override fun onClickItem(locality: Locality) {
        runBlocking {database.myLocalityDAO().insertMyLocality(MyLocality(localityId = locality.id))}

    }

    override fun onClickDeleteItem(department: SearchDepartment) {
        TODO("Not yet implemented")
    }

    private fun checkLocationPermission() =
        ActivityCompat.checkSelfPermission(
            this,
            Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED

    private fun requestLocationPermission() =
        ActivityCompat.requestPermissions(
            this,
            arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
            PERMISSIONS_REQUEST_FINE_LOCATION
        )

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            PERMISSIONS_REQUEST_FINE_LOCATION -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // Разрешение на доступ к местоположению получено, запросите местоположение пользователя
                    requestLocation()
                } else {
                    // Разрешение не получено, обработайте это по вашему усмотрению (например, показ сообщения)
                }
            }
        }
    }

    private fun requestLocation() {
        val locationListener = object : LocationListener {
            override fun onLocationChanged(location: Location) {
                // Получите координаты пользователя
                val userLocation = Point(location.latitude, location.longitude)
                // Здесь вы можете выполнить другие действия с новыми координатами пользователя.
                val geocoder = Geocoder(this@MainActivity, Locale.getDefault())
                val addresses = geocoder.getFromLocation(location.latitude, location.longitude,1)

                if (addresses != null) {
                    if (addresses.isNotEmpty()) {
                        val city = addresses[0]?.locality
                        val call = retrofitServices.findLocalityByName(city.toString())
                        call.enqueue(object : Callback<LocalityDto>{
                            override fun onResponse(call: Call<LocalityDto>,
                                                    response: Response<LocalityDto>) {
                                if (response.isSuccessful) {
                                    val result = response.body()
                                    val currentDayOfWeek = LocalDate.now().dayOfWeek
                                    adapterSearched.addDepartmentAll(result?.departmentDtoList!!.map { SearchDepartment(it,"",(getWorkLoad(it)?: mutableMapOf()).toString(),getDay(it,currentDayOfWeek)) })
                                Log.e("LocalName",result.toString())
                                }


                                else {

                                }
                            }
                            override fun onFailure(call: Call<LocalityDto>, t: Throwable) {
                                Log.e("Ошибка подключения", t.message.toString())
                            }
                        })
                        Log.e("Location",city.toString())
                    }
                }


                // Установите START_POSITION на текущие координаты
                val updatedStartPosition = CameraPosition(userLocation, 13.0f, 0f, 0f)
                map.move(updatedStartPosition)

                // Запрос маршрута только если координаты получены
                if (!locationReceived) {
                    locationReceived = true
                    requestRoute(userLocation, updatedStartPosition)

                    // Теперь, когда у нас есть координаты пользователя и маршрут, сделаем карту видимой

                }

                // Остановите обновления местоположения, если они больше не нужны
                locationManager.removeUpdates(this)
            }
        }

        // Запросите обновления местоположения пользователя
        if (checkLocationPermission()) {
            locationManager.requestLocationUpdates(
                LocationManager.NETWORK_PROVIDER, // Изменили на NETWORK_PROVIDER
                0,
                0f,
                locationListener
            )
        }
    }

    private var locationReceived = false

    private fun requestRoute(userLocation: Point, startPosition: CameraPosition) {
        val DEFAULT_POINTS = listOf(
            userLocation,
            Point(51.529153, 45.976746),
        )

        routePoints = DEFAULT_POINTS

        map.move(startPosition)
        onRoutePointsUpdated()
    }

    private fun onRoutePointsUpdated() {
        placemarksCollection.clear()

        if (routePoints.isEmpty()) {
            drivingSession?.cancel()
            routes = emptyList()
            return
        }

        val imageProvider = ImageProvider.fromResource(this, R.drawable.marker_png_48_ico)
        routePoints.forEach {
            placemarksCollection.addPlacemark(
                it,
                imageProvider,
                IconStyle().apply {
                    scale = 0.5f
                    zIndex = 20f
                }
            )
        }

        if (routePoints.size < 2) return

        val requestPoints = buildList {
            add(RequestPoint(routePoints.first(), RequestPointType.WAYPOINT, null, null))
            addAll(
                routePoints.subList(1, routePoints.size - 1)
                    .map { RequestPoint(it, RequestPointType.VIAPOINT, null, null) })
            add(RequestPoint(routePoints.last(), RequestPointType.WAYPOINT, null, null))
        }

        val drivingOptions = DrivingOptions()
        val vehicleOptions = VehicleOptions()

        drivingSession = drivingRouter.requestRoutes(
            requestPoints,
            drivingOptions,
            vehicleOptions,
            drivingRouteListener,
        )
    }

    private fun onRoutesUpdated() {
        routesCollection.clear()
        if (routes.isEmpty()) return

        routes.forEachIndexed { index, route ->
            routesCollection.addPolyline(route.geometry).apply {
                if (index == 0) styleMainRoute() else styleAlternativeRoute()
            }
        }
    }

    private fun PolylineMapObject.styleMainRoute() {
        zIndex = 10f
        setStrokeColor(R.color.primaryElementsColor)
//        setStrokeColor(ContextCompat.getColor(this@MainActivity, CommonColors.gray))
        strokeWidth = 5f
        outlineColor = (R.color.black)
//        ContextCompat.getColor(this@MainActivity, CommonColors.black)
        outlineWidth = 3f
    }

    private fun PolylineMapObject.styleAlternativeRoute() {
        zIndex = 5f
//        setStrokeColor(ContextCompat.getColor(this@MainActivity, CommonColors.light_blue))
        setStrokeColor(R.color.secondaryElementsColor)
        strokeWidth = 4f
        outlineColor = (R.color.black)
//        ContextCompat.getColor(this@MainActivity, CommonColors.black)
        outlineWidth = 2f
    }

    private fun setCitiesLayout(){
        val layoutParams = ConstraintLayout.LayoutParams(
            ConstraintLayout.LayoutParams.MATCH_PARENT,  // Ширина
            appHeightPixels // Высота в пикселях или другой единице измерения
        )
        listOfCitiesIncludedLayout.layoutParams = layoutParams
        listOfCitiesIncludedLayout.visibility = View.VISIBLE
    }

    private fun setDepartmentLayout(){
//        callAssistantButton.visibility = View.GONE
        addressFromEditText.visibility = View.INVISIBLE
        addressToEditText.visibility = View.INVISIBLE

        filterButtonWhenSearch.visibility = View.INVISIBLE
        searchedRecycleViewWhenSearch.visibility = View.GONE

        moreButton.visibility = View.INVISIBLE
        filterButton.visibility = View.INVISIBLE
        reklamaImageView.visibility = View.INVISIBLE
        reklamaImageView2.visibility = View.INVISIBLE
        clearSearchAddressToImageView.visibility = View.INVISIBLE
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
        slidingUpLayout.anchorPoint = 0.65F

        Log.e("Anchoe", "${slidingUpLayout.anchorPoint}")
        Log.e("Anchoe", "${slidingUpLayout.panelHeight}")
    }

    //TODO: Добавить анимацию кнопки

    private fun setMainLayout(){
        departmentInfoIncludedLayout.visibility = View.GONE
//        callAssistantButton.visibility = View.VISIBLE
        addressFromEditText.visibility = View.VISIBLE
        addressToEditText.visibility = View.VISIBLE
        moreButton.visibility = View.VISIBLE
        filterButton.visibility = View.VISIBLE
        clearSearchAddressToImageView.visibility = View.VISIBLE
        if(searches){
            filterButtonWhenSearch.visibility = View.VISIBLE
            searchedRecycleViewWhenSearch.visibility = View.VISIBLE
        }
        else{
            moreButton.visibility = View.VISIBLE
            filterButton.visibility = View.VISIBLE
            reklamaImageView.visibility = View.VISIBLE
            reklamaImageView2.visibility = View.VISIBLE
            recentlySearchedTextView.visibility = View.VISIBLE
            recentlySearchedRecycleView.visibility = View.VISIBLE
            favouritesTextView.visibility = View.VISIBLE
            favouritesRecycleView.visibility = View.VISIBLE
        }

        slidingUpLayout.panelHeight = startSlidingUpPanelHeight
        slidingUpLayout.anchorPoint = slidingUpPanelStartAnchor

        Log.e("Anchor", "${slidingUpLayout.anchorPoint}")
        Log.e("Anchor", "${slidingUpLayout.panelHeight}")
    }

    private fun setRoute(){
        departmentInfoIncludedLayout.visibility = View.GONE
        routeIncludedLayout.visibility = View.VISIBLE
//        callAssistantButton.visibility = View.GONE
//        departmentInfoIncludedLayout.visibility = View.GONE
//        addressFromEditText.visibility = View.VISIBLE
//        addressToEditText.visibility = View.VISIBLE
//        moreButton.visibility = View.VISIBLE
//        filterButton.visibility = View.VISIBLE
//
//        addressToEditText.setText(departmentNameTextView.text)
//        searchedRecycleViewWhenSearch.visibility = View.INVISIBLE
//        searches = false
//
//        // параметры для высоты и ширины
//        val layoutParams = ConstraintLayout.LayoutParams(
//            ConstraintLayout.LayoutParams.MATCH_PARENT,  // Ширина
//            appHeightPixels // Высота в пикселях или другой единице измерения
//        )
//        routeIncludedLayout.layoutParams = layoutParams
//
        slidingUpLayout.panelHeight = routeSlidingUpPanelHeight
        slidingUpLayout.anchorPoint = slidingUpPanelRouteAnchor
//
//        Log.e("Anchoe", "${slidingUpLayout.anchorPoint}")
//        Log.e("Anchoe", "${slidingUpLayout.panelHeight}")
    }

    private fun setChangeRouteLayout(){
        routeIncludedLayout.visibility = View.GONE
        changeRouteIncludedLayout.visibility = View.VISIBLE
        addressFromEditText.visibility = View.VISIBLE
    }

    private fun setAssistantLayout(){
        mainIncludedLayout.visibility = View.GONE

        val layoutParams = ConstraintLayout.LayoutParams(
            ConstraintLayout.LayoutParams.MATCH_PARENT,  // Ширина
            appHeightPixels // Высота в пикселях или другой единице измерения
        )
        assistantIncludedLayout.layoutParams = layoutParams
        assistantIncludedLayout.visibility = View.VISIBLE
        slidingUpLayout.panelState = SlidingUpPanelLayout.PanelState.EXPANDED
    }
}