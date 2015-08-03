package com.shamanland.lazyfiles.internal

import com.dropbox.core.DbxClient
import com.dropbox.core.DbxEntry
import com.dropbox.core.DbxWriteMode
import com.shamanland.lazyfiles.LazyFilesItem
import com.shamanland.lazyfiles.LazyFilesPlugin

class DropBoxUtilsImpl implements DropBoxUtils {
    @Override
    boolean fetch(String accessToken, LazyFilesItem item) {
        DbxClient client = new DbxClient(DropBoxHelper.config, accessToken)
        LazyFilesPlugin.logger.info "DropBox account: " + client.getAccountInfo().displayName

        FileOutputStream os = new FileOutputStream(item.local)

        try {
            LazyFilesPlugin.logger.info "Start fetching: " + item.dropbox
            DbxEntry.File fetchedFile = client.getFile(item.dropbox, null, os);
            if (fetchedFile == null) {
                throw new AssertionError("Couldn't fetch " + item.dropbox)
            }

            LazyFilesPlugin.logger.info "Fetched: " + fetchedFile
        } finally {
            os.close();
        }

        return false
    }

    @Override
    boolean upload(String accessToken, LazyFilesItem item) {
        DbxClient client = new DbxClient(DropBoxHelper.config, accessToken)
        LazyFilesPlugin.logger.info "DropBox account: " + client.getAccountInfo().displayName

        FileInputStream is = new FileInputStream(item.local)

        try {
            LazyFilesPlugin.logger.info "Start uploading: " + item.local.path
            DbxEntry.File uploadedFile = client.uploadFile(item.dropbox, DbxWriteMode.add(), item.local.length(), is)
            if (uploadedFile == null) {
                throw new AssertionError("Couldn't upload " + item.local)
            }

            LazyFilesPlugin.logger.info "Uploaded: " + uploadedFile
        } finally {
            is.close();
        }

        return false
    }
}
