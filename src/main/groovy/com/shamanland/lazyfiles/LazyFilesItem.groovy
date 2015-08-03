package com.shamanland.lazyfiles

import static com.shamanland.lazyfiles.internal.Utils.notNull

class LazyFilesItem {
    final File local
    final String dropbox

    protected LazyFilesItem(File local, String dropbox) {
        this.local = notNull local
        this.dropbox = notNull dropbox
    }
}
