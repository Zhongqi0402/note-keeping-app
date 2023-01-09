package ui.assignments.a4notes

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Switch
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import ui.assignments.a4notes.viewmodel.NotesViewModel


/**
 * A simple [Fragment] subclass.
 * Use the [AddFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AddFragment : Fragment() {




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_add, container, false)
        // Get the ViewModel into fragment
        val notesVM: NotesViewModel by activityViewModels()

        fun onCreateNewNote() {
            var newTitle = "Title"
            var newContent = "Content"
            if (view.findViewById<EditText>(R.id.title_add).text.toString() != "") {
                newTitle = view.findViewById<EditText>(R.id.title_add).text.toString()
            }
            if (view.findViewById<EditText>(R.id.content_add).text.toString() != "") {
                newContent = view.findViewById<EditText>(R.id.content_add).text.toString()
            }
            var important = view.findViewById<Switch>(R.id.important_switch_add).isChecked

            notesVM.addNote(newTitle, newContent, important)
        }

        view.findViewById<Button>(R.id.create_btn).apply {
            setOnClickListener(
                {
                    onCreateNewNote()
                    findNavController().navigate(R.id.action_addFragment_to_mainFragment2)
                }
            )
        }

        return view
    }

}