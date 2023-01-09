package ui.assignments.a4notes

import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import ui.assignments.a4notes.viewmodel.NotesViewModel

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val model : NotesViewModel by viewModels { NotesViewModel.Factory }
        model.getNotes().observe(this) {
            Log.i("MainActivity", it?.fold("Visible Note IDs:") { acc, cur -> "$acc ${cur.value?.id}" } ?: "[ERROR]")
        }
    }
//    override fun onConfigurationChanged(newConfig: Configuration) {
////        // Begin the transaction
////        val ft = getSupportFragmentManager().beginTransaction()
////        // Replace the contents of the container with the new fragment
////        ft.replace(R.id.fragmentContainerView, R.layout.fragment_main)
////        // Complete the changes added above
////        ft.commit()
//        super.onConfigurationChanged(newConfig)
//        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
//            Log.i("OrientationChange", "Landscape")
//            setContentView(R.layout.activity_main)
////            supportFragmentManager.commit {
////                setReorderingAllowed(true)
////                add<MainFragment>(R.id.fragmentContainerView)
////            }
//            val model : NotesViewModel by viewModels { NotesViewModel.Factory }
//            model.getNotes().observe(this) {
//                Log.i("MainActivity", it?.fold("Visible Note IDs:") { acc, cur -> "$acc ${cur.value?.id}" } ?: "[ERROR]")
//            }
//        }
//        else if(newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
//            Log.i("OrientationChange", "Portrait")
//            setContentView(R.layout.activity_main)
//            val model : NotesViewModel by viewModels { NotesViewModel.Factory }
//            model.getNotes().observe(this) {
//                Log.i("MainActivity", it?.fold("Visible Note IDs:") { acc, cur -> "$acc ${cur.value?.id}" } ?: "[ERROR]")
//            }
//        }
//        else{
//            Log.e("OrientationChange", "Whaaat...")
//        }
//    }
}