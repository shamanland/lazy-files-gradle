package com.shamanland.lazyfiles

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction

import static com.shamanland.lazyfiles.internal.Utils.time

class LazyFilesFetchTask extends DefaultTask {
    @TaskAction
    def actionImpl() {
        def lazyFiles = project.extensions.getByName("lazyFiles") as LazyFilesExtension
        def utils = lazyFiles.dropboxFactory.createUtils()
        lazyFiles.fetch().each {
            LazyFilesItem item = it
            time "Fetch remote file " + item.dropbox + " to " + item.local, {
                utils.fetch lazyFiles.dropboxAccessToken, item
            }
        }
    }
}
