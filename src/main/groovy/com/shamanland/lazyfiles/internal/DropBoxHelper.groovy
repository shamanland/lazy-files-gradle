package com.shamanland.lazyfiles.internal

import com.dropbox.core.DbxAppInfo
import com.dropbox.core.DbxAuthFinish
import com.dropbox.core.DbxRequestConfig
import com.dropbox.core.DbxWebAuthNoRedirect
import org.gradle.api.Project

class DropBoxHelper {
    static final String APP_KEY = "1dfhhbib4x76lsu"
    static final String APP_SECRET = "1urkdl1scen6ir7"

    static final DbxAppInfo appInfo = new DbxAppInfo(APP_KEY, APP_SECRET)
    static final DbxRequestConfig config = new DbxRequestConfig("Lazy Files/0.0.1", Locale.getDefault().toString())

    static String performAuth() {
        DbxWebAuthNoRedirect webAuth = new DbxWebAuthNoRedirect(config, appInfo)
        String authorizeUrl = webAuth.start()


        println "1. Go to: " + authorizeUrl
        println "2. Click \"Allow\" (you might have to log in first)"
        println "3. Copy the authorization code."
        println "3. Insert code and press ENTER:"

        String code = new BufferedReader(new InputStreamReader(System.in)).readLine()
        if (code != null) {
            code = code.trim()
        }

        DbxAuthFinish authFinish = webAuth.finish(code)
        return authFinish.accessToken
    }

    static String readAccessToken(Project project) {
        def hash = Utils.hash project.rootProject.rootDir.absolutePath
        Utils.readProperty hash
    }

    static void saveAccessToken(Project project, String token) {
        def hash = Utils.hash project.rootProject.rootDir.absolutePath
        Utils.saveProperty hash, token
    }
}
