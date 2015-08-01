package com.shamanland.lazyfiles

import com.shamanland.lazyfiles.internal.DropBoxHelper
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.StopExecutionException
import org.gradle.api.tasks.TaskAction

class LazyFilesLoginTask extends DefaultTask {
    @TaskAction
    def actionImpl() {
        def lazyFiles = project.extensions.getByName("lazyFiles") as LazyFilesExtension
        if (lazyFiles.dropboxAccessToken == null || lazyFiles.dropboxAccessToken.isEmpty()) {
            def saved = DropBoxHelper.readAccessToken project

            if (saved != null && !saved.isEmpty()) {
                lazyFiles.dropboxAccessToken = saved
            } else {
                String token = DropBoxHelper.performAuth()
                if (token == null || token.isEmpty()) {
                    throw new StopExecutionException("Couldn't get access token")
                }

                lazyFiles.dropboxAccessToken = token
                DropBoxHelper.saveAccessToken project, token
            }
        }
    }
}
