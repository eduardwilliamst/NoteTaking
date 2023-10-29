package com.example.notetaking.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import com.example.notetaking.R
import com.example.notetaking.databinding.FragmentHome2Binding

class Home2Fragment(private val viewModel: HomeViewModel?) : DialogFragment() {

    companion object {

        private const val EXTRA_NOTE = "note"
        private const val EXTRA_DATE = "date"

        fun newInstance(viewModel: HomeViewModel?): Home2Fragment {
            return Home2Fragment(viewModel)
        }

        fun show(viewModel: HomeViewModel?, fragmentManager: FragmentManager) {
            newInstance(viewModel).show(fragmentManager, null)
        }

        fun show(
            viewModel: HomeViewModel?,
            fragmentManager: FragmentManager,
            note: String,
            date: String,
        ) {
            val dialog = newInstance(viewModel)
            val bundle = Bundle()
            bundle.putString(EXTRA_NOTE, note)
            bundle.putString(EXTRA_DATE, date)
            dialog.arguments = bundle
            dialog.show(fragmentManager, null)
        }
    }

    private var binding: FragmentHome2Binding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHome2Binding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val title: String? = arguments?.getString(EXTRA_NOTE)
        val content: String? = arguments?.getString(EXTRA_DATE)

        binding?.buttonSubmit?.setOnClickListener {
            binding?.editTitleEditText?.text?.toString()?.let { note ->
                viewModel?.createNote(note, content.toString())
                dialog?.dismiss()
            }
        }

        binding?.editTitleEditText?.setText(title)
    }
}