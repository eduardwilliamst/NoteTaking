package com.example.notetaking.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.contentValuesOf
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.example.notetaking.R
import com.example.notetaking.databinding.FragmentHome1Binding
import com.example.notetaking.local.LocalRepository

class Home1Fragment : DialogFragment() {
    private var binding: FragmentHome1Binding? = null
    private var viewModel: HomeViewModel? = null
    private var recyclerView: RecyclerView? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return HomeViewModel(
                    local = LocalRepository(
                        sharedPreferences = getSharedPreferences(
                            LocalRepository.PREF_NAME,
                            AppCompatActivity.MODE_PRIVATE,
                        ),
                        noteDatabase = Room.databaseBuilder(
                            context = applicationContext,
                            name = NoteDatabase.DATABASE_NAME,
                            klass = NoteDatabase::class.java,
                        ).build(),
                    )
                ) as T
            }
        }.create(HomeViewModel::class.java)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding?.lifecycleOwner = this
        binding?.viewModel = viewModel
        binding?.view = binding?.root
        binding?.adapter = NoteAdapter(
            homeViewModel = viewModel,
            updateNote = { note ->
                Home2Fragment.show(
                    viewModel = viewModel,
                    fragmentManager = supportFragmentManager,
                    title = note.title,
                    content = note.content,
                )
            },
        )
        binding?.layoutManager = LinearLayoutManager(this)

        mainViewModel?.logout?.observe(this) { isLoggedOut ->
            if (isLoggedOut) {
                startActivity(Intent(this, LoginActivity::class.java))
                this.finish()
            }
        }

        binding?.buttonCreateNote?.setOnClickListener {
            CreateNoteDialog.show(mainViewModel, supportFragmentManager)
        }

        mainViewModel?.provideData()

        return binding.root
    }
}
