package com.asanme.protocoldescriptor.model.enum

sealed class ViewRoutes(val route: String) {
    object TopicView : ViewRoutes("topicView")
    object AddProtocolView : ViewRoutes("addProtocolView")
    object ProtocolView : ViewRoutes("protocolView")
    object AddChecklistView : ViewRoutes("addChecklistView")
}