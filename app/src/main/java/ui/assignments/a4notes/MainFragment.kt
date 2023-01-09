package ui.assignments.a4notes

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Switch
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import ui.assignments.a4notes.viewmodel.NotesViewModel
/**
 * A simple [Fragment] subclass.
 * Use the [MainFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MainFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_main, container, false)

        // Get the ViewModel into fragment
        val notesVM: NotesViewModel by activityViewModels()
        notesVM.getNotes().observe(viewLifecycleOwner) { visibleNotesList ->
            view.findViewById<Switch>(R.id.main_showArch_switch).isChecked = notesVM.getViewArch().value!!
            val notesListLayout = view.findViewById<LinearLayout>(R.id.notes_list)
            notesListLayout.removeAllViews()
            visibleNotesList.forEach { note ->
                layoutInflater.inflate(R.layout.single_note, null, false).apply {
                    if (note.value!!.important) {
                        this.setBackgroundColor(Color.YELLOW)
                    }
                    else if(note.value!!.archived) {
                        this.setBackgroundColor(Color.LTGRAY)
                    }

                    findViewById<LinearLayout>(R.id.note_info).findViewById<TextView>(R.id.note_title).text = note.value!!.getNoteTitle()
                    findViewById<LinearLayout>(R.id.note_info).findViewById<TextView>(R.id.note_content).text = note.value!!.getNoteContent()
                    findViewById<Button>(R.id.archive_btn).setOnClickListener {
                        notesVM.archiveNote(note.value!!.id)
                    }
                    findViewById<Button>(R.id.delete_btn).setOnClickListener {
                        notesVM.deleteNote(note.value!!.id)
                    }

                    this.setOnClickListener {
                        notesVM.setCurSelectedNote(note)
                        findNavController().navigate(R.id.action_mainFragment2_to_editFragment)
                    }

                    notesListLayout.addView(this)
                }

            }
        }

        fun search() {
            val search_string = view.findViewById<EditText>(R.id.search_text).text.toString().trim()
            if (search_string != "") {
                notesVM.search(search_string)
            }
        }

        fun cancel_search() {
            view.findViewById<EditText>(R.id.search_text).text.clear()
            notesVM.cancel_search()
        }

        notesVM.getViewArch().observe(viewLifecycleOwner) { arch ->
            if (arch) {
                notesVM.addInArchiNotes()
            }
            else {
                notesVM.showNonArchNotes()
            }
        }
        view.findViewById<Switch>(R.id.main_showArch_switch).apply {
            setOnClickListener {
                notesVM.setViewArch()
            }
        }

        view.findViewById<Button>(R.id.add_btn).apply {
            this.setOnClickListener {
                findNavController().navigate(R.id.action_mainFragment2_to_addFragment)
            }
        }

        view.findViewById<Button>(R.id.search_btn).apply {
            setOnClickListener({
                search()
            })
        }

        view.findViewById<Button>(R.id.search_cancel_btn).apply {
            setOnClickListener ({
                cancel_search()
            })
        }

        return view
    }


}