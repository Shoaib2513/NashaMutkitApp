package com.example.project_nm

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.activity.OnBackPressedCallback
import androidx.core.app.ActivityCompat.finishAffinity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class Profile : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    parentFragmentManager.popBackStack()
                    val bottomNav = requireActivity().findViewById<BottomNavigationView>(R.id.BottomNav)
                    bottomNav.selectedItemId = R.id.home

                }
            }
        )
        val view = inflater.inflate(R.layout.fragment_profile, container, false)

        requireActivity().window.statusBarColor =
            ContextCompat.getColor(requireContext(), R.color.appColor)

        val btnFavorites = view.findViewById<Button>(R.id.btnFavorites)
        val btnAbout = view.findViewById<Button>(R.id.btnAbout)
        val btnHelp = view.findViewById<Button>(R.id.btnHelp)
        val btnExit = view.findViewById<Button>(R.id.btnExit)




        btnFavorites.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.FrameLayout, BookmarkFragment())
                .addToBackStack(null)
                .commit()
        }
        btnAbout.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.FrameLayout, AboutFragment())
                .addToBackStack(null)
                .commit()
        }
        btnHelp.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.FrameLayout, GetHelpFragment())
                .addToBackStack(null)
                .commit()
        }
        btnExit.setOnClickListener {
            AlertDialog.Builder(requireContext())
                .setTitle("Exit App")
                .setMessage("Are you sure you want to exit?")
                .setPositiveButton("Yes") { _, _ ->
                    requireActivity().finishAffinity()
                }
                .setNegativeButton("Cancel", null)
                .show()
        }
        return view
    }
}
