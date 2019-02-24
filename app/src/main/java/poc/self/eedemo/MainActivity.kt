package poc.self.eedemo

import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.Button
import android.widget.EditText

class MainActivity : AppCompatActivity() {

    private lateinit var number: EditText
    private lateinit var message: EditText
    private lateinit var sendButton: Button
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ChatAdapter

    private val chatList: ArrayList<ChatMessage> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initUi()

        initChatListView()
    }

    private fun initChatListView() {
        adapter = ChatAdapter(chatList)
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerView.adapter = adapter
    }

    private fun initUi() {
        number = findViewById(R.id.phoneNumber)
        message = findViewById(R.id.message)
        sendButton = findViewById(R.id.sendButton)
        recyclerView = findViewById(R.id.chat_recycler_view)
        sendButton.setOnClickListener(onSendButtonClick)
    }

    private fun displayMessage(message: ChatMessage) {
        chatList.add(message)
        adapter.notifyItemInserted(chatList.size)
    }

    private fun isNull(data: String?) = data != null


    private val onSendButtonClick: View.OnClickListener = View.OnClickListener {
        when (validate()) {
            true -> {
                processUserMessage()
            }
            false ->
                displayMessage(ChatMessage("Please enter valid data.", "bot"))
        }
    }

    private fun processUserMessage() {
        val userMessage = message.text.toString().trim()
        displayMessage(ChatMessage(userMessage, "user"))

        val response = sendMessageToBot(userMessage)
        Handler().postDelayed({ displayMessage(ChatMessage(response, "bot")) }, 2000)
    }

    private fun validate(): Boolean {
        return (isNull(number.text.toString().trim())
                &&
                isNull(message.text.toString().trim()))
    }

}
// Pure function
// Testing Pyramid