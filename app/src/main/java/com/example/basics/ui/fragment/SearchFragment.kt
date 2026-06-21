package com.example.basics.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.basics.R
import com.example.basics.adapter.SearchAdapter
import com.example.basics.databinding.FragmentSearchBinding
import com.example.basics.viewmodel.HomeViewModel


class SearchFragment : Fragment() {
    private lateinit var binding: FragmentSearchBinding
    private val homeViewModel: HomeViewModel by activityViewModels()
    private lateinit var adapter: SearchAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding=FragmentSearchBinding.inflate(inflater,container,false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter= SearchAdapter()
        binding.recyclerView.layoutManager= GridLayoutManager(requireContext(),2)
        binding.recyclerView.adapter=adapter
        homeViewModel.searchResults.observe(viewLifecycleOwner) {

            adapter.submitList(it)
        }

    }



}