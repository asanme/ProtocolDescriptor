package com.asanme.protocoldescriptor.model.enum

sealed class Routes(val route: String) {
    object TopicView : Routes("topicView")
    object AddProtocolView : Routes("protocolView")
}