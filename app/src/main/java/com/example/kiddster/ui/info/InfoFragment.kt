package com.example.kiddster.ui.info

import android.animation.AnimatorInflater
import android.animation.AnimatorSet
import android.graphics.Color
import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.kiddster.MainViewModel
import com.example.kiddster.R
import com.example.kiddster.databinding.FragmentInfoBinding
import com.example.kiddster.util.WordPrint


class InfoFragment : Fragment() {

    private lateinit var notificationsViewModel: MainViewModel
    private var _binding: FragmentInfoBinding? = null


    lateinit var front_anim:AnimatorSet
    lateinit var back_anim:AnimatorSet
    lateinit var front_anim_x:AnimatorSet
    lateinit var back_anim_x:AnimatorSet
    lateinit var button: Button
    var isFront = true
    var isFront2 = true
    var isFront3 = true

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

        val apilabel: TextView = binding.apilabel
        val cjlabel: TextView = binding.cjlabel
        val ctlabel: TextView = binding.ctlabel
        notificationsViewModel.jokesList.observe(viewLifecycleOwner, Observer {
            apilabel.setTextColor(Color.parseColor("#2196F3"))
            cjlabel.text = it.size.toString()
            cjlabel.setTextColor(Color.parseColor("#2196F3"))
            ctlabel.text = notificationsViewModel.allJokes.map { it.jktype }.toTypedArray().distinct().size.toString()
            ctlabel.setTextColor(Color.parseColor("#2196F3"))
        })

        val scale:Float = requireContext()!!.resources.displayMetrics.density

        var frontcard : CardView = binding.appstatfront
        var backcard : CardView = binding.appstatback

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


        var frontcard2 : CardView = binding.creatorcardfront
        var backcard2 : CardView = binding.creatorcardback

        val profileLink = binding.profilelink
        profileLink.movementMethod = LinkMovementMethod.getInstance()
        profileLink.setLinkTextColor(Color.parseColor("#2196F3"))

        frontcard2.cameraDistance = 8000 * scale
        backcard2.cameraDistance = 8000 * scale

        frontcard2.setOnClickListener {

            if (isFront2) {
                front_anim.setTarget(frontcard2)
                back_anim.setTarget(backcard2)
                front_anim.start()
                back_anim.start()
                isFront2 = false
                frontcard2.setClickable(false)
            }

        }

        backcard2.setOnClickListener {

            if (!isFront2) {
                front_anim.setTarget(backcard2)
                back_anim.setTarget(frontcard2)
                front_anim.start()
                back_anim.start()
                isFront2 = true
                frontcard2.setClickable(true)
            }
        }

        front_anim_x = AnimatorInflater.loadAnimator(requireContext(), R.animator.front_animator_x) as AnimatorSet
        back_anim_x = AnimatorInflater.loadAnimator(requireContext(),R.animator.back_animator_x) as AnimatorSet

        var frontcard3 : CardView = binding.upgradescardfront
        var backcard3 : CardView = binding.upgradescardback

        val upgrText: WordPrint = binding.upgradestext

        frontcard3.cameraDistance = 8000 * scale
        backcard3.cameraDistance = 8000 * scale

        frontcard3.setOnClickListener {

            if (isFront3) {
                front_anim_x.setTarget(frontcard3)
                back_anim_x.setTarget(backcard3)
                front_anim_x.start()
                back_anim_x.start()
                isFront3 = false
                frontcard3.setClickable(false)

                upgrText.setCharacterDelay(20);
                upgrText.animateText(getString(R.string.upgrades));
            }

        }

        backcard3.setOnClickListener {

            if (!isFront3) {
                front_anim_x.setTarget(backcard3)
                back_anim_x.setTarget(frontcard3)
                front_anim_x.start()
                back_anim_x.start()
                isFront3 = true
                frontcard3.setClickable(true)
            }
        }



        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}