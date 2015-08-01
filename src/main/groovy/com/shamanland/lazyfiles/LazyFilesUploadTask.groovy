package com.shamanland.lazyfiles

import com.shamanland.lazyfiles.internal.DropBoxHelper
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction

import static com.shamanland.lazyfiles.internal.Utils.time

class LazyFilesUploadTask extends DefaultTask {
    @TaskAction
    def actionImpl() {
        def lazyFiles = project.extensions.getByName("lazyFiles") as LazyFilesExtension
        if (lazyFiles.dropboxAccessToken == null || lazyFiles.dropboxAccessToken.isEmpty()) {
            lazyFiles.dropboxAccessToken = DropBoxHelper.readAccessToken project
            if (lazyFiles.dropboxAccessToken == null || lazyFiles.dropboxAccessToken.isEmpty()) {
                throw new AssertionError("No dropboxAccessToken specified, try to perform task loginLazyFiles or configure it manually")
            }
        }

        def utils = lazyFiles._dropboxFactory.createUtils()
        def items = lazyFiles.uploadItems()

        LazyFilesPlugin.logger.lifecycle "Files to be uploaded: " + items.size()

        items.each {
            LazyFilesItem item = it
            time "Upload local file " + item.local + " to " + item.dropbox, {
                utils.upload lazyFiles.dropboxAccessToken, item
            }
        }
    }
}
