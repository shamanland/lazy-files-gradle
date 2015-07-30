package com.shamanland.lazyfiles

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction

import static com.shamanland.lazyfiles.internal.Utils.time

class LazyFilesUploadTask extends DefaultTask {
    @TaskAction
    def actionImpl() {
        def lazyFiles = project.extensions.getByName("lazyFiles") as LazyFilesExtension
        def utils = lazyFiles._dropboxFactory.createUtils()
        lazyFiles.upload().each {
            LazyFilesItem item = it
            time "Upload local file " + item.dropbox + " to " + item.local, {
                utils.upload lazyFiles.dropboxAccessToken, item
            }
        }
    }
}
