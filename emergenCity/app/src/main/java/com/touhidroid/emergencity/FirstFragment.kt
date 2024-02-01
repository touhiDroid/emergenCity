package com.touhidroid.emergencity

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebSettings
import androidx.fragment.app.Fragment
import com.touhidroid.emergencity.databinding.FragmentFirstBinding


/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    @SuppressLint("SetJavaScriptEnabled")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val webSettings = binding.wvMap.settings
        webSettings.javaScriptEnabled = true
        webSettings.setGeolocationEnabled(true)
        // webSettings.setGeolocationDatabasePath(getFilesDir().getPath());
        webSettings.databaseEnabled = true
        webSettings.allowFileAccessFromFileURLs = true
        webSettings.allowUniversalAccessFromFileURLs = true
        webSettings.javaScriptCanOpenWindowsAutomatically = true
        webSettings.domStorageEnabled = true
        webSettings.builtInZoomControls = true
        webSettings.allowFileAccess = true
        webSettings.allowContentAccess = true
        webSettings.setSupportZoom(true)
        webSettings.pluginState = WebSettings.PluginState.ON
        webSettings.mediaPlaybackRequiresUserGesture = false

        webSettings.mixedContentMode = 0
        binding.wvMap.setLayerType(View.LAYER_TYPE_HARDWARE, null)
        binding.wvMap.loadUrl("https://airquality.googleapis.com/v1/mapTypes/US_AQI/heatmapTiles/3/1/1?key=<GAPI_KEY>")
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
