package com.asanme.protocoldescriptor.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.asanme.protocoldescriptor.R
import com.asanme.protocoldescriptor.model.entity.ActionEntity
import com.asanme.protocoldescriptor.model.enum.Decision

@Composable
fun ActionItem(
    modifier: Modifier = Modifier,
    actionEntity: ActionEntity
) {
    Card(
        shape = RoundedCornerShape(10.dp),
        elevation = 5.dp,
        modifier = modifier.padding(vertical = 5.dp)
    ) {
        Column(
            modifier = Modifier.padding(10.dp)
        ) {
            Text(
                text = actionEntity.name,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color(3, 4, 94)
            )

            Text(
                text = actionEntity.description,
                fontSize = 16.sp,
                fontWeight = FontWeight.Normal,
                color = Color(3, 4, 94)
            )
        }
    }
}

@Composable
fun NullItem(
    modifier: Modifier = Modifier,
) {
    var wasClicked by rememberSaveable {
        mutableStateOf(false)
    }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        modifier = modifier.padding(vertical = 5.dp)
    ) {
        Card(
            shape = RoundedCornerShape(10.dp),
            elevation = 5.dp,
            modifier = modifier
                .clickable {
                    wasClicked = true
                },
            backgroundColor = if (wasClicked) Color.Yellow else Color.White
        ) {
            Row {
                Column(
                    modifier = Modifier.padding(
                        vertical = 10.dp,
                        horizontal = 20.dp
                    )
                ) {
                    Text(
                        text = Decision.NONE.text,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(3, 4, 94)
                    )
                }
            }
        }
    }
}

@Composable
fun DecisionItem(
    modifier: Modifier = Modifier,
    decision: Decision,
) {
    var wasClicked by rememberSaveable {
        mutableStateOf(false)
    }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        modifier = modifier.padding(vertical = 5.dp)
    ) {
        Box(
            modifier = Modifier
                .clip(CircleShape)
                .background(Color(3, 4, 94))
                .size(20.dp)
        )

        Card(
            shape = RoundedCornerShape(10.dp),
            elevation = 5.dp,
            modifier = Modifier
                .clickable {
                    wasClicked = true
                },
            backgroundColor = if (wasClicked) Color(224, 73, 106) else Color.White
        ) {
            Row {
                Column(
                    modifier = Modifier.padding(
                        vertical = 10.dp,
                        horizontal = 20.dp
                    )
                ) {
                    Text(
                        text = decision.text,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = if (wasClicked) Color.White else Color(3, 4, 94)
                    )
                }
            }
        }
    }
}

@Composable
fun NewElement(
    modifier: Modifier = Modifier,
) {
    var actionText by rememberSaveable {
        mutableStateOf("")
    }

    var descriptionText by rememberSaveable {
        mutableStateOf("")
    }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        modifier = modifier.padding(vertical = 5.dp)
    ) {
        Card(
            shape = RoundedCornerShape(10.dp),
            elevation = 5.dp,
            backgroundColor = Color.White,
        ) {
            Column {
                CustomEditText(
                    label = {
                        Text(
                            stringResource(R.string.action_name),
                            color = Color(3, 4, 94),
                        )
                    },
                    onValueChange = { enteredAction ->
                        actionText = enteredAction
                    },
                    text = actionText
                )

                CustomEditText(
                    label = {
                        Text(
                            stringResource(R.string.action_description),
                            color = Color(3, 4, 94),
                        )
                    },
                    onValueChange = { enteredDescription ->
                        descriptionText = enteredDescription
                    },
                    text = descriptionText
                )

                Row(
                    Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                    horizontalArrangement = Arrangement.spacedBy(20.dp)
                ) {
                    Button(
                        shape = RoundedCornerShape(10.dp),
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = Color(224, 73, 106)
                        ),
                        onClick = {

                        },
                        modifier = Modifier
                            .weight(5f)
                            .height(40.dp),
                    ) {
                        Text(
                            text = stringResource(R.string.discard),
                            color = Color.White,
                            fontWeight = FontWeight.Bold,
                        )
                    }

                    Button(
                        shape = RoundedCornerShape(10.dp),
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = Color(221, 224, 73)
                        ),
                        onClick = {

                        },
                        modifier = Modifier
                            .weight(5f)
                            .height(40.dp),
                    ) {
                        Text(
                            text = stringResource(R.string.done),
                            color = Color(3, 4, 94),
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }
    }
}
