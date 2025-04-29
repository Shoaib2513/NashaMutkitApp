package com.example.project_nm

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.*
import android.widget.*
import androidx.activity.OnBackPressedCallback
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.bottomsheet.BottomSheetDialog

class NearByLocations : Fragment() {

    private lateinit var listView: ListView
    private val rehabCentersList = mutableListOf<RehabCenter>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    val bottomNav = requireActivity().findViewById<BottomNavigationView>(R.id.BottomNav)
                    bottomNav.selectedItemId = R.id.home
                }
            }
        )
        val view = inflater.inflate(R.layout.fragment_near_by_locations, container, false)

        listView = view.findViewById(R.id.nearbyListView)
        val searchEditText = view.findViewById<EditText>(R.id.searchEditText)
        val searchIcon = view.findViewById<ImageView>(R.id.searchLocation)

        searchIcon.setOnClickListener {
            if (searchEditText.visibility == View.VISIBLE) {
                searchEditText.visibility = View.GONE
                searchEditText.text.clear()
                val controller = ViewCompat.getWindowInsetsController(searchEditText)
                controller?.hide(WindowInsetsCompat.Type.ime())
            } else {
                searchEditText.visibility = View.VISIBLE
                searchEditText.requestFocus()
            }
        }
        populateRehabCentersList()

        val adapter = RehabCenterAdapter(requireContext(), rehabCentersList)
        listView.adapter = adapter

        listView.setOnItemClickListener { _, _, position, _ ->
            val selectedCenter = rehabCentersList[position]
            showBottomDrawer(selectedCenter)
        }

        searchEditText.addTextChangedListener(object : android.text.TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val filteredList = rehabCentersList.filter {
                    it.name.contains(s ?: "", ignoreCase = true)
                }
                val filteredAdapter = RehabCenterAdapter(requireContext(), filteredList.toMutableList())
                listView.adapter = filteredAdapter
            }
            override fun afterTextChanged(s: android.text.Editable?) {}
        })

        requireActivity().window.statusBarColor =
            ContextCompat.getColor(requireContext(), R.color.appColor)
        return view
    }

    private fun populateRehabCentersList() {
        val centersAddress = mapOf(
            "Hope Recovery Center - Delhi" to "A-32, Green Avenue, New Delhi, 110001",
            "Navjeevan Foundation - Noida" to "Sector 62, Near Fortis Hospital, Noida, UP",
            "LifeCare Wellness - Chandigarh" to "Sec 21, Inner Market, Chandigarh, 160022",
            "Sanjeevani Rehab Center - Gurgaon" to "DLF Phase 3, Gurgaon, Haryana",
            "Healing Path Center - Dehradun" to "Rajpur Road, Near Clock Tower, Dehradun",
            "Amrit Kiran Foundation - Amritsar" to "GT Road, Near Bus Stand, Amritsar",
            "Nasha Mukti Kendra - Ludhiana" to "Model Town, Phase 2, Ludhiana",
            "New Hope Foundation - Jalandhar" to "Urban Estate, Phase 1, Jalandhar",
            "Safe Life Rehabilitation - Patiala" to "Leela Bhawan, Patiala",
            "Nirmal Aasra Rehab - Bathinda" to "GT Road, Opp. Civil Hospital, Bathinda",
            "Freedom from Drugs Center - Hoshiarpur" to "Main Bazar, Hoshiarpur",
            "Nav Chetna Foundation - Mohali" to "Phase 7, Mohali",
            "Sukhmani Wellness Center - Moga" to "Gill Road, Near Bus Stand, Moga",
            "Shanti Rehab House - Mumbai" to "Andheri West, Near Infinity Mall, Mumbai",
            "Peaceful Minds Rehab - Pune" to "Viman Nagar, Pune",
            "Navachetana Center - Ahmedabad" to "SG Highway, Ahmedabad",
            "New Light Wellness - Nagpur" to "Sitabuldi, Nagpur",
            "Phoenix Recovery Care - Surat" to "Citylight Road, Surat",
            "Nirmal Wellness Clinic - Bangalore" to "Indiranagar, Bangalore",
            "New Life Center - Hyderabad" to "Banjara Hills, Hyderabad",
            "Serenity Rehab - Chennai" to "T. Nagar, Chennai",
            "Wellbeing Recovery Home - Kochi" to "MG Road, Kochi",
            "Positive Path Center - Mysore" to "Vijayanagar, Mysore",
            "Jeevan Jyoti Foundation - Kolkata" to "Salt Lake, Sector 5, Kolkata",
            "Umang Wellness Center - Bhubaneswar" to "Saheed Nagar, Bhubaneswar",
            "Recovery Rays Rehab - Ranchi" to "Harmu Road, Ranchi",
            "Manav Kalyan Foundation - Guwahati" to "Paltan Bazaar, Guwahati",
            "Shubham LifeCare - Siliguri" to "Sevoke Road, Siliguri",
            "Aastha Wellness Center - Bhopal" to "MP Nagar, Bhopal",
            "Nav Jeevan Rehab Home - Indore" to "Vijay Nagar, Indore",
            "Mukti Foundation - Raipur" to "Shankar Nagar, Raipur"
        )
        val centersPhones = mapOf(
            "Hope Recovery Center - Delhi" to "9810012345",
            "Navjeevan Foundation - Noida" to "9810023456",
            "LifeCare Wellness - Chandigarh" to "9810034567",
            "Sanjeevani Rehab Center - Gurgaon" to "9810045678",
            "Healing Path Center - Dehradun" to "9810056789",
            "Amrit Kiran Foundation - Amritsar" to "9810067890",
            "Nasha Mukti Kendra - Ludhiana" to "9810078901",
            "New Hope Foundation - Jalandhar" to "9810089012",
            "Safe Life Rehabilitation - Patiala" to "9810090123",
            "Nirmal Aasra Rehab - Bathinda" to "9810101234",
            "Freedom from Drugs Center - Hoshiarpur" to "9810112345",
            "Nav Chetna Foundation - Mohali" to "9810123456",
            "Sukhmani Wellness Center - Moga" to "9810134567",
            "Shanti Rehab House - Mumbai" to "9810145678",
            "Peaceful Minds Rehab - Pune" to "9810156789",
            "Navachetana Center - Ahmedabad" to "9810167890",
            "New Light Wellness - Nagpur" to "9810178901",
            "Phoenix Recovery Care - Surat" to "9810189012",
            "Nirmal Wellness Clinic - Bangalore" to "9810190123",
            "New Life Center - Hyderabad" to "9810201234",
            "Serenity Rehab - Chennai" to "9810212345",
            "Wellbeing Recovery Home - Kochi" to "9810223456",
            "Positive Path Center - Mysore" to "9810234567",
            "Jeevan Jyoti Foundation - Kolkata" to "9810245678",
            "Umang Wellness Center - Bhubaneswar" to "9810256789",
            "Recovery Rays Rehab - Ranchi" to "9810267890",
            "Manav Kalyan Foundation - Guwahati" to "9810278901",
            "Shubham LifeCare - Siliguri" to "9810289012",
            "Aastha Wellness Center - Bhopal" to "9810290123",
            "Nav Jeevan Rehab Home - Indore" to "9810301234",
            "Mukti Foundation - Raipur" to "9810312345"
        )
        rehabCentersList.clear()
        centersAddress.forEach { (name, address) ->
            val phone = centersPhones[name] ?: ""
            rehabCentersList.add(RehabCenter(name = name, address = address, phone = phone))
        }
    }

    private fun showBottomDrawer(center: RehabCenter) {
        val dialog = BottomSheetDialog(requireContext())
        val view = layoutInflater.inflate(R.layout.bottom_sheet_address, null)

        view.findViewById<TextView>(R.id.centerName).text = center.name
        view.findViewById<TextView>(R.id.centerAddress).text = center.address

        val callImageView = view.findViewById<ImageView>(R.id.callImageView)
        callImageView.setOnClickListener {
            if (center.phone.isNotEmpty()) {
                val dialIntent = Intent(Intent.ACTION_DIAL)
                dialIntent.data = Uri.parse("tel:${center.phone}")
                startActivity(dialIntent)
            } else {
                Toast.makeText(requireContext(), "Phone number not available", Toast.LENGTH_SHORT).show()
            }
        }
        dialog.setContentView(view)
        dialog.show()
    }
}
