package com.example.notetaking.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.notetaking.databinding.FragmentHome1Binding

class Home1Fragment : DialogFragment() {
    private var binding: FragmentHome1Binding? = null
    private lateinit var viewModel: HomeViewModel
    private var recyclerView: RecyclerView? = null

    private val adapter = NoteAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHome1Binding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)

        // Set ViewModel to the binding
        binding.viewModel = viewModel

        // Set RecyclerView adapter and layout manager
        val adapter = NoteAdapter() // Gantilah dengan adapter sesuai kebutuhan Anda
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())

        // Observe LiveData for empty data
        viewModel.notes.observe(viewLifecycleOwner) { notes ->
            if (notes.isEmpty()) {
                binding.emptyTextView.visibility = View.VISIBLE
            } else {
                binding.emptyTextView.visibility = View.GONE
            }
        }

        return binding.root
    }
}
