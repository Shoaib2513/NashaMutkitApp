package com.example.project_nm

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.material.bottomsheet.BottomSheetDialog

class BookmarkFragment : Fragment() {

    private lateinit var bookmarkListView: ListView
    private lateinit var adapter: RehabCenterAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_bookmark, container, false)
        bookmarkListView = view.findViewById(R.id.bookmarkListView)
        val backButton = view.findViewById<ImageView>(R.id.goback)

        backButton.setOnClickListener {
            requireActivity().supportFragmentManager.popBackStack()
        }

        adapter = RehabCenterAdapter(requireContext(), BookmarkStorage.bookmarkedCenters)
        bookmarkListView.adapter = adapter
        bookmarkListView.setOnItemClickListener { _, _, position, _ ->
            val selectedCenter = BookmarkStorage.bookmarkedCenters[position]
            showBottomDrawer(selectedCenter)
        }

        return view
    }

    override fun onResume() {
        super.onResume()
        adapter.notifyDataSetChanged()
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
