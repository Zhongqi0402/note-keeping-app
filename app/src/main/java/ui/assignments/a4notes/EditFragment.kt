package ui.assignments.a4notes

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.Switch
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ui.assignments.a4notes.viewmodel.NotesViewModel

/**
 * A simple [Fragment] subclass.
 * Use the [AddFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class EditFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_edit, container, false)
        // Get the ViewModel into fragment
        val notesVM: NotesViewModel by activityViewModels()

        notesVM.getCurSelectedNote().observe(viewLifecycleOwner) { selectedNote ->
            view.findViewById<Switch>(R.id.important_switch).apply{
                isChecked = selectedNote.important
                setOnClickListener {
                    if (isChecked && view.findViewById<Switch>(R.id.archive_switch).isChecked) {
                        view.findViewById<Switch>(R.id.archive_switch).isChecked = false
                    }

                    notesVM.updateSelectedNoteLabels("important", isChecked)
                }
            }
            view.findViewById<Switch>(R.id.archive_switch).apply{
                isChecked = selectedNote.archived
                setOnClickListener {
                    if (isChecked && view.findViewById<Switch>(R.id.important_switch).isChecked) {
                        view.findViewById<Switch>(R.id.important_switch).isChecked = false
                    }
                    notesVM.updateSelectedNoteLabels("archive", isChecked)
                }
            }
            var newTitle = ""
            var newContent = ""
            view.findViewById<EditText>(R.id.edit_title).apply{
                setText(selectedNote.title)

//                do doOnTextChanged
                addTextChangedListener(
                    object: TextWatcher {
                        override fun afterTextChanged(s: Editable) {
                            newTitle = this@apply.getText().toString()
                            notesVM.updateNoteTextTitle(selectedNote.id,newTitle)
                        }
                        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
                        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
                    }


                )
            }
            view.findViewById<EditText>(R.id.edit_content).apply{
                setText(selectedNote.content)
                addTextChangedListener(
                    object: TextWatcher {
                        override fun afterTextChanged(s: Editable) {
                            newContent = this@apply.getText().toString()
                            notesVM.updateNoteTextContent(selectedNote.id, newContent)
                        }
                        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
                        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
                    }


                )
            }
        }

        return view
    }




}