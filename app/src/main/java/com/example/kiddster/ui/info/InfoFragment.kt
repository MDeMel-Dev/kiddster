package com.example.kiddster.ui.info

import android.animation.AnimatorInflater
import android.animation.AnimatorSet
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.kiddster.MainViewModel
import com.example.kiddster.R
import com.example.kiddster.databinding.FragmentInfoBinding

class InfoFragment : Fragment() {

    private lateinit var notificationsViewModel: MainViewModel
    private var _binding: FragmentInfoBinding? = null


    lateinit var front_anim:AnimatorSet
    lateinit var back_anim:AnimatorSet
    lateinit var button: Button
    var isFront = true

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        notificationsViewModel =
            ViewModelProvider(this.requireActivity(), com.example.kiddster.MainViewModel.Factory(requireActivity()!!.application))
                .get(MainViewModel::class.java)

        _binding = FragmentInfoBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textNotifications
        notificationsViewModel.mainJoke.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })

        val scale:Float = requireContext()!!.resources.displayMetrics.density

        var frontcard : CardView = binding.cardViewfront
        var backcard : CardView = binding.cardViewback
        var frontbutton : Button = binding.buttonfront
        var backbutton : Button = binding.buttonback
        var incomebutton : Button = binding.button4

        frontcard.cameraDistance = 8000 * scale
        backcard.cameraDistance = 8000 * scale

        front_anim = AnimatorInflater.loadAnimator(requireContext(), R.animator.front_animator) as AnimatorSet
        back_anim = AnimatorInflater.loadAnimator(requireContext(),R.animator.back_animator) as AnimatorSet

        frontcard.setOnClickListener {

            if (isFront) {
                front_anim.setTarget(frontcard)
                back_anim.setTarget(backcard)
                front_anim.start()
                back_anim.start()
                isFront = false
                frontcard.setClickable(false)
            }

        }

        backcard.setOnClickListener {

            if (!isFront) {
                front_anim.setTarget(backcard)
                back_anim.setTarget(frontcard)
                front_anim.start()
                back_anim.start()
                isFront = true
                frontcard.setClickable(true)
            }


        }



        incomebutton.setOnClickListener {

            incomebutton.setBackgroundColor(Color.parseColor("#64DD17"));

        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}