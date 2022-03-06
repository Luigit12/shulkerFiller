package com.lambda

import com.lambda.client.LambdaMod
import com.lambda.client.plugin.api.Plugin
import com.lambda.client.util.threads.BackgroundJob
import com.lambda.managers.ExampleManager
import com.lambda.modules.ShulkerFillerModule

internal object ShulkerFiller : Plugin() {

    override fun onLoad() {
        // Load any modules, commands, or HUD elements here
        modules.add(ShulkerFillerModule)
        managers.add(ExampleManager)

        bgJobs.add(BackgroundJob("ExampleJob", 10000L) { LambdaMod.LOG.info("Hello its me the BackgroundJob of your example plugin.") })
    }

    override fun onUnload() {
        // Here you can unregister threads etc...
    }
}