package com.example.project_nm

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.fragment.app.Fragment

class TipDetailFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_tip_detail, container, false)

        val tipTextView = view.findViewById<TextView>(R.id.tipText)
        val explanationTextView = view.findViewById<TextView>(R.id.tipExplanation)
        val backButton = view.findViewById<ImageButton>(R.id.backButton)
        val tip = arguments?.getString("tip") ?: "No tip available"
        val explanation = arguments?.getString("explanation") ?: "No explanation provided"

        tipTextView.text = tip
        explanationTextView.text = explanation

        backButton.setOnClickListener {
            requireActivity().supportFragmentManager.popBackStack()
        }
        return view
    }
}
