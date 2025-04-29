package com.example.project_nm

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView

class RehabCenterAdapter(
    private val context: Context,
    private val centers: MutableList<RehabCenter>
) : BaseAdapter() {

    override fun getCount(): Int = centers.size

    override fun getItem(position: Int): Any = centers[position]

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view: View = convertView ?: LayoutInflater.from(context)
            .inflate(R.layout.list_item_location, parent, false)

        val locationName = view.findViewById<TextView>(R.id.locationName)
        val bookmarkIcon = view.findViewById<ImageView>(R.id.bookmark)
        val center = centers[position]

        locationName.text = center.name

        center.isBookmarked = BookmarkStorage.bookmarkedCenters.any { it.name == center.name }

        bookmarkIcon.setImageResource(
            if (center.isBookmarked) R.drawable.baseline_bookmark_24
            else R.drawable.baseline_bookmark_border_24
        )
        bookmarkIcon.setOnClickListener {
            center.isBookmarked = !center.isBookmarked
            if (center.isBookmarked) {
                if (!BookmarkStorage.bookmarkedCenters.contains(center)) {
                    BookmarkStorage.bookmarkedCenters.add(center)
                }
            } else {
                BookmarkStorage.bookmarkedCenters.remove(center)
            }
            notifyDataSetChanged()
        }
        return view
    }
}
