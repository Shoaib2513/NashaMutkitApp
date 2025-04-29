package com.example.project_nm

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment

class GetHelpFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_get_help, container, false)

        val backButton = view.findViewById<ImageView>(R.id.goback)
        backButton.setOnClickListener {
            requireActivity().supportFragmentManager.popBackStack()
        }
//
//        setupHelpline(view, R.id.helplineDrugAbuse, "1800110031")
//        setupHelpline(view, R.id.helplineWomen, "1091")
//        setupHelpline(view, R.id.helplineMental, "08046110007")
//        setupHelpline(view, R.id.helplineEmergency, "112")

        return view
    }

    private fun setupHelpline(view: View, textViewId: Int, phoneNumber: String) {
        val helplineView = view.findViewById<TextView>(textViewId)
        helplineView.setOnClickListener {
            val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:$phoneNumber"))
            startActivity(intent)
        }
    }
}
