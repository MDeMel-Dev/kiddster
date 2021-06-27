package com.example.kiddster.ui.main

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.kiddster.MainViewModel
import com.example.kiddster.databinding.FragmentMainBinding
import com.example.kiddster.util.SwipeFunc
import com.example.kiddster.util.WordPrint

class MainFragment : Fragment() {

    private lateinit var homeViewModel: com.example.kiddster.MainViewModel
    private var _binding: FragmentMainBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
                ViewModelProvider(this.requireActivity(), com.example.kiddster.MainViewModel.Factory(requireActivity()!!.application))
                    .get(MainViewModel::class.java)

        _binding = FragmentMainBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: WordPrint = binding.textHome
        homeViewModel.mainJoke.observe(viewLifecycleOwner, Observer {
            textView.text = it
            textView.setCharacterDelay(50);
            textView.animateText(textView.text);
        })

       root.setOnTouchListener(object: SwipeFunc(requireActivity()) {
            override fun onSwipeLeft() {
                homeViewModel.previousJoke()
            }
            override fun onSwipeRight() {
                homeViewModel.nextJoke()
            }
        })

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}