package com.shamanland.lazyfiles.internal

class DropBoxFactoryImpl implements DropBoxFactory {
    @Override
    DropBoxUtils createUtils() {
        return new DropBoxUtilsImpl()
    }
}
