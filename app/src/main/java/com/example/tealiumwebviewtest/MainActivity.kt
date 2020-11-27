package com.example.tealiumwebviewtest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView
import com.tealium.collectdispatcher.Collect
import com.tealium.core.*
import com.tealium.dispatcher.TealiumView
import com.tealium.hosteddatalayer.HostedDataLayer
import com.tealium.lifecycle.Lifecycle
import com.tealium.remotecommanddispatcher.RemoteCommands
import com.tealium.tagmanagementdispatcher.TagManagement
import com.tealium.visitorservice.VisitorService

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
            modules = mutableSetOf(Modules.Lifecycle, Modules.VisitorService, Modules.HostedDataLayer),
            dispatchers = mutableSetOf(Dispatchers.Collect, Dispatchers.TagManagement, Dispatchers.RemoteCommands),
            environment = Environment.DEV
        ).apply {
            useRemoteLibrarySettings = true
        }

        tealium = Tealium.create("tealium_instance", config)
    }

    override fun onResume() {
        super.onResume()

        tealium.track(TealiumView("myViewName", mapOf("k1" to "v1")))
    }
}