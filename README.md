Lazy Files Gradle Plugin
========================

Have a private project?

Need to upload binaries, reports from CI server?

No ability to setup dedicated server?

Use your DropBox account for any of your goals!

**Lazy Files** help you with this.

# Limitations

1. Only Gradle plugin currently available, *but you can* help this project by adding something else.
2. It's not a Maven repository over DropBox, *but you can* easily reach your goals in a similar way.
3. It's not a dependency resolving system, *but you can* simplify development with your own conventions.

# How to use it?

1. Include it like a dependency to your buildscript:

    ```
    buildscript {
        dependencies {
            classpath 'com.shamanland:lazy-files:0.1.2'
        }
    }
    ```

2. Apply plugin to any project or sub-project:

    ```
    apply plugin: 'lazy-files'
    ```

3. Configure items for uploading:

    ```
    lazyFiles {
        uploadFile new File(projectDir, 'build/libs/my-lib-0.1.jar'), '/my-project/my-lib-0.1.jar'
        // uploadFile new File('/absolute/path/to/local.file'), '/dropbox/path/to/remote.file'
    }
    ```

4. Configure items for fetching:

    ```
    lazyFiles {
        fetchFile '/my-project/my-lib-0.1.jar', new File(projectDir, 'build/libs/my-lib-0.1.jar')
        // fetchFile '/dropbox/path/to/remote.file', new File('/absolute/path/to/local.file')
    }
    ```

5. Invoke corresponding gradle tasks:

    ```
    gradlew uploadLazyFiles
    gradlew fetchLazyFiles
    ```

# How to authenticate?

1. There is built-in gradle task, invoke it and follow instructions in order to get access token:

    ```
    gradlew loginLazyFiles
    ```
    
    Once you finish this step, access token will be saved in your home directory (`~/.lazy-files/app.properties`).
    
    You can use different accounts for different projects, they will be stored using SHA1 of project absolute path. 

2. You can specify access token manually:

    At first run task from previous step with the argument `--info`, then you will see the following output:
    
    ```
    DropBox access token retrieved: AAAAAAAA
    ```
    
    Now you can place this token into `build.gradle`:
    
    ```
    lazyFiles {
        dropboxAccessToken = 'AAAAAAAA'
    }
    ```

# Security

Be careful sharing access token with someone else or saving it in repository.
Anyone can use it without any additional authentication like you.

But usually it's not a problem if your project is completely private.
You may commit this access token to VCS, then anyone from your team won't be required to authenticate again.

# Example projects

TODO

License
--------

    Copyright 2015 ShamanLand.Com
    
    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at
    
       http://www.apache.org/licenses/LICENSE-2.0
    
    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
