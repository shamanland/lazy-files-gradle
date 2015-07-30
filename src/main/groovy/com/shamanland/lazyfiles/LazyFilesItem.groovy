package com.shamanland.lazyfiles

import static com.shamanland.lazyfiles.internal.Utils.notNull

class LazyFilesItem {
    final File local
    final File dropbox

    protected LazyFilesItem(File local, File dropbox) {
        this.local = notNull local
        this.dropbox = notNull dropbox
    }
}
