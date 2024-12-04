package com.example.omidPayTechTask.presentation.animation

import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import kotlin.math.cos
import kotlin.math.sin

@Composable
fun BallRotateIndicator(
    modifier: Modifier,
    color: Color = Color.White,
    movingBalls: Int = 2,
    diameter: Float = 30f,
    spacing: Float = 60f,
    animationDuration: Int = 1000,
    minScale: Float = 0.4f,
    maxScale: Float = 1.2f,
    rotationCycleDelay: Int = 50
) {

    val transition = rememberInfiniteTransition(label = "")

    val rotation by transition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = keyframes {
                durationMillis = animationDuration
                0f at 0 using FastOutLinearInEasing
                180f at animationDuration / 2 using LinearEasing
                360f at animationDuration using LinearOutSlowInEasing
                delayMillis = rotationCycleDelay
            },
            repeatMode = RepeatMode.Restart
        ), label = ""
    )

    val scale by transition.animateFloat(
        initialValue = minScale,
        targetValue = maxScale,
        animationSpec = infiniteRepeatable(
            animation = tween(animationDuration / 2, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        ), label = ""
    )

    Canvas(modifier = modifier) {

        val center = Offset(x = size.width / 2, y = size.height / 2)
        val angleStep = 360f / movingBalls

        drawCircle(
            color = color,
            radius = diameter / 2 * scale,
            center = Offset(
                x = center.x,
                y = center.y
            )
        )

        for (index in 0 until movingBalls) {

            val angle = index * angleStep

            drawCircle(
                color = color,
                radius = diameter / 2 * scale,
                center = Offset(
                    x = center.x + ((spacing) * cos(Math.toRadians((angle) + rotation.toDouble())).toFloat()),
                    y = center.y + ((spacing) * sin(Math.toRadians((angle) + rotation.toDouble())).toFloat()),
                )
            )
        }
    }
}