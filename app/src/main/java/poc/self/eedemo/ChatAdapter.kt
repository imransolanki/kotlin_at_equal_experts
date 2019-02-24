package poc.self.eedemo

import android.support.v7.widget.RecyclerView
import android.view.Gravity
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView

class ChatAdapter(val chats: ArrayList<ChatMessage>) : RecyclerView.Adapter<ChatAdapter.ViewHolder>() {


    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        val view = LayoutInflater.from(p0.context).inflate(R.layout.chat_message_layout, p0, false) as TextView
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return chats.size
    }

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        when (chats[p1].userType) {
            "bot" -> p0.textView.gravity = Gravity.LEFT
            "user" -> p0.textView.gravity = Gravity.RIGHT
        }
        p0.textView.text = chats[p1].message
    }

    class ViewHolder(val textView: TextView) : RecyclerView.ViewHolder(textView)
}