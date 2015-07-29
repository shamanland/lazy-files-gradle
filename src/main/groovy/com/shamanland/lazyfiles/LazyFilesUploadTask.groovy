package com.shamanland.lazyfiles

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction

import static com.shamanland.lazyfiles.internal.DropBoxUtils.upload
import static com.shamanland.lazyfiles.internal.Utils.time

class LazyFilesUploadTask extends DefaultTask {
    @TaskAction
    def actionImpl() {
        def lazyFiles = project.extensions.getByType LazyFilesExtension
        lazyFiles.upload().each {
            LazyFilesItem item = it
            time "Upload local file " + item.dropbox + " to " + item.local, {
                upload lazyFiles.dropboxAccessToken, item
            }
        }
    }
}
