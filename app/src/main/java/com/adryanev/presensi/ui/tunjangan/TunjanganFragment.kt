package com.adryanev.presensi.ui.tunjangan

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.adryanev.presensi.R

class TunjanganFragment : Fragment() {

    companion object {
        fun newInstance() = TunjanganFragment()
    }

    private lateinit var viewModel: TunjanganViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_tunjangan, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(TunjanganViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
