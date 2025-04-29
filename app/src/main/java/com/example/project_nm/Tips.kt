package com.example.project_nm

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.activity.OnBackPressedCallback
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class   Tips : Fragment() {

    private lateinit var tipsListView: ListView
    private val recoveryTips = listOf(
        "🤝 Stay connected with supportive people.",
        "🎯 Set short and achievable goals.",
        "🥗 Maintain a healthy diet and sleep routine.",
        "🏃‍♂️ Exercise regularly to boost your mood.",
        "🚫 Avoid triggers and high-risk situations.",
        "🧘 Practice mindfulness or meditation.",
        "📓 Keep a journal of your progress.",
        "🎉 Reward yourself for milestones reached.",
        "📞 Reach out for help when needed.",
        "⏳ Recovery is a journey, not a race.",
        "👥 Attend regular support group meetings.",
        "💖 Forgive yourself for past mistakes.",
        "🏆 Celebrate even the smallest victories.",
        "🌿 Learn to manage stress in healthy ways.",
        "🌈 Replace bad habits with positive activities.",
        "📆 Stay consistent with your recovery plan.",
        "🤗 Avoid isolation — connection is key.",
        "📚 Read or listen to motivational material.",
        "🗣️ Talk to a counselor or therapist regularly.",
        "🔮 Visualize a better future and work toward it.",
        "🚷 Limit time with negative influences.",
        "🧠 Understand and accept your triggers.",
        "📅 Focus on one day at a time.",
        "👐 Volunteer or help others on the same path.",
        "📲 Always have an emergency support contact.",
        "💡 Remind yourself why you started this journey.",
        "🎨 Engage in hobbies that bring you joy.",
        "🙏 Practice gratitude every day.",
        "📏 Don’t compare your journey to others.",
        "🔍 Stay honest with yourself and others."
    )

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
        val view = inflater.inflate(R.layout.fragment_tips, container, false)
        requireActivity().window.statusBarColor =
            ContextCompat.getColor(requireContext(), R.color.appColor)

        tipsListView = view.findViewById(R.id.tipsListView)

        val adapter = ArrayAdapter(
            requireContext(),
            R.layout.list_item_tip,
            R.id.tipText,
            recoveryTips
        )
        tipsListView.adapter = adapter

        tipsListView.setOnItemClickListener { _, _, position, _ ->
            val selectedTip = recoveryTips[position]
            val explanation = getDetailedExplanation(selectedTip)

            val bundle = Bundle().apply {
                putString("tip", selectedTip)
                putString("explanation", explanation)
            }
            val detailFragment = TipDetailFragment()
            detailFragment.arguments = bundle
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.FrameLayout, detailFragment)
                .addToBackStack(null)
                .commit()
        }

        return view
    }

    private fun getDetailedExplanation(tip: String): String {
        return when (tip) {
            "🤝 Stay connected with supportive people." ->
                "Connecting with friends and family is essential! 🤗 Keep in touch with those who uplift you and provide strength during tough times."

            "🎯 Set short and achievable goals." ->
                "Break your journey into small, manageable steps. 🎯 Every little goal achieved is a victory worth celebrating!"

            "🥗 Maintain a healthy diet and sleep routine." ->
                "Fuel your body and mind with nutritious food and adequate rest. 🥗😴 A balanced life leads to a happier you!"

            "🏃‍♂️ Exercise regularly to boost your mood." ->
                "Get moving! 🏃‍♀️💪 Exercise releases feel-good hormones that help combat stress and anxiety."

            "🚫 Avoid triggers and high-risk situations." ->
                "Be mindful of your environment. 🚫 Stay away from situations or people that could set you back."

            "🧘 Practice mindfulness or meditation." ->
                "Calm your mind and center yourself. 🧘‍♂️🕊️ Even a few minutes of mindfulness daily can bring peace."

            "📓 Keep a journal of your progress." ->
                "Write it out! 📓🖊️ Reflecting on your journey helps track growth and lets you see how far you've come."

            "🎉 Reward yourself for milestones reached." ->
                "Celebrate your wins, big or small. 🎉 You’ve earned it, and it motivates you to keep going!"

            "📞 Reach out for help when needed." ->
                "You are never alone. 🤝 Don’t hesitate to talk to someone when things get tough."

            "⏳ Recovery is a journey, not a race." ->
                "Take your time and be patient with yourself. 🐢 Progress at your pace — every step is meaningful."

            "👥 Attend regular support group meetings." ->
                "Lean on your community. 👥 Shared experiences and support can be a powerful source of strength."

            "💖 Forgive yourself for past mistakes." ->
                "Let go of guilt and shame. 💗 Everyone makes mistakes — what matters is how you grow from them."

            "🏆 Celebrate even the smallest victories." ->
                "Every positive step counts. 🥳 Don’t wait for big wins — the little ones are just as important!"

            "🌿 Learn to manage stress in healthy ways." ->
                "Find what relaxes you. 🌿 Whether it’s music, walks, or hobbies, manage stress before it manages you."

            "🌈 Replace bad habits with positive activities." ->
                "Swap the harmful for the helpful. 🌈 Pick up a new hobby or dive into something that brings joy."

            "📆 Stay consistent with your recovery plan." ->
                "Stick to your path. 📆 Consistency builds momentum and builds new habits that last."

            "🤗 Avoid isolation — connection is key." ->
                "You thrive in connection. 🧩 Don’t shut the world out — let others in to walk with you."

            "📚 Read or listen to motivational material." ->
                "Feed your mind with positivity. 📚🎧 Inspirational content can keep you focused and uplifted."

            "🗣️ Talk to a counselor or therapist regularly." ->
                "Professional guidance can make a big difference. 🧑‍⚕️ Don’t hesitate to seek therapy — it’s strength, not weakness."

            "🔮 Visualize a better future and work toward it." ->
                "See your success before it happens. 🔮 Visualization is powerful — dream it, then do it."

            "🚷 Limit time with negative influences." ->
                "You become what you’re around. 🚷 Surround yourself with people and energy that fuel your growth."

            "🧠 Understand and accept your triggers." ->
                "Awareness is key. 🧠 Knowing your triggers helps you avoid or handle them wisely."

            "📅 Focus on one day at a time." ->
                "Don't overwhelm yourself with the future. 📅 Just handle today — and let tomorrow handle itself."

            "👐 Volunteer or help others on the same path." ->
                "Helping others helps you too. 🙌 Service can be healing and brings purpose to your journey."

            "📲 Always have an emergency support contact." ->
                "Keep someone on speed dial. ☎️ A lifeline can make a huge difference when you're in a rough spot."

            "💡 Remind yourself why you started this journey." ->
                "Reconnect with your 'why'. 🧭 Let your reasons be your motivation every single day."

            "🎨 Engage in hobbies that bring you joy." ->
                "Do what makes your soul happy. 🎨🎮 Whether it’s painting or playing, find your spark."

            "🙏 Practice gratitude every day." ->
                "Gratitude turns what you have into enough. 🙏 Count your blessings, no matter how small."

            "📏 Don’t compare your journey to others." ->
                "Your story is uniquely yours. 📖✨ Focus on your growth — comparison only steals joy."

            "🔍 Stay honest with yourself and others." ->
                "Truth builds trust. 🔍💬 Honesty keeps you grounded and connected with your purpose."

            else ->
                "This tip is part of your recovery journey. 🚀 Keep pushing forward and take it one day at a time!"
        }
    }

}


