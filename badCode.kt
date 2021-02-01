public class ScheduleFragment(): CustomFragment() {
    private var bt: Button? = null
    private val scheduleClickListener = object : ScheduleClickListener() {
        override fun startFragment(fragmentClass: Class<out GOFragment>, args: Bundle) {
            this@ScheduleFragment.startFragment(this@ScheduleFragment, fragmentClass, args)
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bt = this.findViewById<Button>(R.id.myButton)
        bt.setOnClickListener(scheduleClickListener);
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val root = super.onCreateView(inflater, container, savedInstanceState)
        val adptr = ScheduleItemAdapter()
        adapter.setItems(getScheduleItems(getScheduleItemData()))
        root.recycler.adapter = adptr
        adapter.notifyOnItemChanged()
        return root
    }
    override onResume() {
        super.onResume()
    }
    override onStop() {
    }
    public fun getScheduleItems(itemData: String?): List<ScheduleItem> {
        var scheduleItemList = listOf<ScheduleItem>()
        val gson = Gson()
        val parsedItemData = Gson().fromJson(itemData, ScheduleItem.java::class)
        scheduleItemList.addAll(parsedItemData!!)
        return scheduleItemList
    }
    public fun getScheduleItemData(): String? {
        var data:String? = null
        GlobalScope.launch {
            data = FakeApiHelper().postScheduleData()
        }
        if(data != null) {
            return data
        }
        return null
    }
}
