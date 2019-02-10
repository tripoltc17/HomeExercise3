package at.fh.swengb.homeexercise2_solution

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.item_note.view.*

class NotesAdapter(val onClickListener: (note: Note) -> Unit, val onLongClickListener: (note: Note) -> Unit): RecyclerView.Adapter<NotesViewHolder>() {

    private var notesList: List<Note> = emptyList()

    fun updateList(newList: List<Note>) {
        notesList = newList.toList()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_note, parent, false)
        return NotesViewHolder(view, onClickListener, onLongClickListener)
    }

    override fun getItemCount() = notesList.size

    override fun onBindViewHolder(viewHolder: NotesViewHolder, position: Int) {
        val item = notesList[position]
        viewHolder.bind(item)
    }
}

class NotesViewHolder(view: View, val onClickListener: (note: Note) -> Unit, val onLongClickListener: (note: Note) -> Unit): RecyclerView.ViewHolder(view) {
    fun bind(item: Note) {
        itemView.user_name.text = item.title
        itemView.note_content.text = item.content
        itemView.setOnClickListener{
            onClickListener(item)
        }
        itemView.setOnLongClickListener{
            onLongClickListener(item)
            true
        }
    }
}
