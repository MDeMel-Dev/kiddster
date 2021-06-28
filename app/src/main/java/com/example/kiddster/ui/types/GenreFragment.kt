package com.example.kiddster.ui.types

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment.findNavController
import com.example.kiddster.MainViewModel
import com.example.kiddster.Network.Joke
import com.example.kiddster.R
import com.example.kiddster.databinding.FragmentGenreBinding
import com.example.kiddster.ui.recyclerview.DataAdapter

class GenreFragment : Fragment() {

    private lateinit var dashboardViewModel: MainViewModel
    private var _binding: FragmentGenreBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    lateinit var textView: TextView

    private val onJokeClickListener: (Joke) -> Unit = { item ->
        dashboardViewModel.setJoke(item.id)
        navigateMain()
    }

    private val onTypeClickListener: (String) -> Unit = { item ->
        changeTitle()
        adapter.dataunits = dashboardViewModel.allJokes.filter{it.jktype.equals(item)}.toCollection(ArrayList())
        binding.typeList.adapter = adapter
    }

    val adapter = DataAdapter(onJokeClickListener , onTypeClickListener)

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

        textView = binding.textDashboard

        binding.typeList.adapter = adapter

        dashboardViewModel.jokesList.observe(viewLifecycleOwner, Observer {
            adapter.data = dashboardViewModel.allJokes.map { it.jktype }.toTypedArray().distinct() as ArrayList<String>
            binding.typeList.adapter = adapter
        })


        return root
    }

    fun navigateMain()
    {
        findNavController(this).navigate(R.id.action_navigation_genre_to_navigation_main)
    }

    fun changeTitle()
    {
        textView.text = "List"
    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}