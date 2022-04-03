package com.cases.covid.data.model

import com.cases.covid.utils.common.VIEW_TYPE_INCOMING_MESSAGE
import com.cases.covid.utils.common.VIEW_TYPE_OUTGOING_MESSAGE

/**
 * Created by Pallab Banerjee on 2/2/2021.
 */
data class Chat(
val viewType : Int,
val message : String
)