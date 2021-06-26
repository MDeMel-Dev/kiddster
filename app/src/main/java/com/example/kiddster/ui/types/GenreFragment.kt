package com.example.kiddster.ui.types

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.kiddster.MainViewModel
import com.example.kiddster.databinding.FragmentGenreBinding

class GenreFragment : Fragment() {

    private lateinit var dashboardViewModel: MainViewModel
    private var _binding: FragmentGenreBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        dashboardViewModel =
            ViewModelProvider(this.requireActivity(), com.example.kiddster.MainViewModel.Factory(requireActivity()!!.application))
                .get(MainViewModel::class.java)

        _binding = FragmentGenreBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textDashboard
        dashboardViewModel.mainJoke.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}