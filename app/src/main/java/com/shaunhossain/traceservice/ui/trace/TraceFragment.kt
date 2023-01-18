package com.shaunhossain.traceservice.ui.trace

import android.Manifest
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.fragment.app.viewModels
import com.shaunhossain.traceservice.databinding.FragmentTraceBinding
import com.shaunhossain.traceservice.service.LocationService

class TraceFragment : Fragment() {

    private var _binding: FragmentTraceBinding? = null
    private val binding get() = _binding!!

    private val traceViewModel by viewModels<TraceViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTraceBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        ActivityCompat.requestPermissions(
            requireActivity(),
            arrayOf(
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION,
            ),
            0
        )

        binding.startButton.setOnClickListener {
            Intent(requireContext(), LocationService::class.java).apply {
                action = LocationService.ACTION_START
                context?.startService(this)
            }

        }

        binding.stopButton.setOnClickListener {
            Intent(requireContext(), LocationService::class.java).apply {
                action = LocationService.ACTION_STOP
                context?.startService(this)
            }
        }
    }

}