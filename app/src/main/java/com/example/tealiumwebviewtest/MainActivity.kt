package com.example.tealiumwebviewtest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView
import com.tealium.core.*
import com.tealium.dispatcher.TealiumView

class MainActivity : AppCompatActivity() {

    lateinit var tealium: Tealium

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        WebView.setWebContentsDebuggingEnabled(true)

        val config = TealiumConfig(
            application,
            "myAccount", // adding real account and profile name doesn't solve the problem
            "myProfile",
            Environment.DEV
        ).apply {
            useRemoteLibrarySettings = false
        }

        tealium = Tealium.create("tealium_instance", config)
    }

    override fun onResume() {
        super.onResume()

        tealium.track(TealiumView("myViewName", mapOf("k1" to "v1")))
    }
}