package com.example.otp_number.guardiandetail

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.example.otp_number.R
import com.example.otp_number.databinding.FragmentGuardianInfoBinding

class GuardianInfo : Fragment() {

    private lateinit var binding: FragmentGuardianInfoBinding
    private lateinit var model: GuardianInfoViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreate(savedInstanceState)
        binding = FragmentGuardianInfoBinding.inflate(inflater, container, false)

        // Get the view model
        model = ViewModelProvider(this)[GuardianInfoViewModel::class.java]

        // Specify layout for recycler view
        val linearLayoutManager = LinearLayoutManager(
            requireActivity(), RecyclerView.VERTICAL, false)
        binding.guardianList.layoutManager = linearLayoutManager

        // Observe the model
        model.allGuardians.observe(viewLifecycleOwner) { guardians ->
            // Data bind the recycler view
            binding.guardianList.adapter = GuardianAdapter(guardians)
        }

        binding.addGuardian.setOnClickListener { openAddGuardian() }

        model.showSnackBarEvent.observe(viewLifecycleOwner) { showSnackBar ->
            if (showSnackBar == true) {
                Snackbar.make(
                    requireActivity().findViewById(android.R.id.content),
                    getString(R.string.cleared_message),
                    Snackbar.LENGTH_LONG
                ).show()
                model.doneShowingSnackbar()
            }
        }

        setHasOptionsMenu(true)

        return binding.root
    }

    fun openAddGuardian() {
//        findNavController().navigate(GuardianInfo.actionGuardianInfoToAddGuardian())
        findNavController().navigate(R.id.action_guardianInfo2_to_addGuardian)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.options_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.delete_item -> model.onClear()
        }
        return super.onOptionsItemSelected(item)
    }
}
