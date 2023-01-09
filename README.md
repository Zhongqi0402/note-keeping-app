## Setup
 * MacOS Ventura Version 13.0.1 (22A400)
 * IntelliJ IDEA 2022.2.1 (Ultimate Edition)
 * Java SDK 17.0.2
 * Kotlin 1.7.10

## Special Notes about Basic
* **A possible error**  
If during build, you see an error similar to this one:
```
> A failure occurred while executing com.android.build.gradle.internal.res.ParseLibraryResourcesTask$ParseResourcesRunnable
 > Failed file name validation for file /path_to_your_folder/a4/a4-enhancement/app/build/intermediates/packaged_res/debug/drawable/ic_launcher_background 3.xml
```
This means that the file name `ic_launcher_background 3.xml` in `build` folder is not valid because there is a space in front of "3". Somehow such file is generated automatically by gradle because this file is in `build` folder.  
But if you see this, simply delete `build` folder.

## Enhancement
A new functionality is added to the UI - Search the note!  
There will be an extra field at the top of the screen to search in notes' title and content. If either of title or content
contains such string, the notes will be displayed! Searching will automatically remove any leading or trailing whitespaces.

#### How to use?
Simply put in the string you would like to search and click on the search button. All related notes will be displayed. To cancel the search,
simply click on the "X" button or click on "show archived" button at the top. Either of these will stop the search mode and display normal notes.
Then, all notes that are currently visible will be displayed!  
The search is case sensitive, so "Note" and "note" are different.
