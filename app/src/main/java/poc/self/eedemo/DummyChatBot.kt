package poc.self.eedemo

val botKnowledgeBase: Map<String, String> = mapOf(
        Pair("Hello", "Hi"),
        Pair("How are you?", "I am fine"),
        Pair("Thank you.", "See you soon :) "))

fun sendMessageToBot(message: String) = botKnowledgeBase[message] ?: "sorry I didn't get it"
