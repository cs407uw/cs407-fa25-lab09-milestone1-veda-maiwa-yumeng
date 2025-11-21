package com.cs407.lab09

/**
 * Represents a ball that can move. (No Android UI imports!)
 *
 * Constructor parameters:
 * - backgroundWidth: the width of the background, of type Float
 * - backgroundHeight: the height of the background, of type Float
 * - ballSize: the width/height of the ball, of type Float
 */
class Ball(
    private val backgroundWidth: Float,
    private val backgroundHeight: Float,
    private val ballSize: Float
) {
    var posX = 0f
    var posY = 0f
    var velocityX = 0f
    var velocityY = 0f
    private var accX = 0f
    private var accY = 0f

    private var isFirstUpdate = true

    init {
        reset()
    }

    /**
     * Updates the ball's position and velocity based on the given acceleration and time step.
     * (See lab handout for physics equations)
     */
    fun updatePositionAndVelocity(xAcc: Float, yAcc: Float, dT: Float) {
        if(isFirstUpdate) {
            isFirstUpdate = false
            accX = xAcc
            accY = yAcc
            return
        }

        val newVelX = velocityX + 0.5f * (xAcc + accX) * dT
        val newVelY = velocityY + 0.5f * (yAcc + accY) * dT
        val distX = velocityX * dT + (1.0f / 6.0f) * dT * dT * (3 * accX + xAcc)
        val distY = velocityY * dT + (1.0f / 6.0f) * dT * dT * (3 * accY + yAcc)

        posX += distX
        posY += distY
        velocityX = newVelX
        velocityY = newVelY
        accX = xAcc
        accY = yAcc

        checkBoundaries()

    }

    /**
     * Ensures the ball does not move outside the boundaries.
     * When it collides, velocity and acceleration perpendicular to the
     * boundary should be set to 0.
     */
    fun checkBoundaries() {
        // (Check all 4 walls: left, right, top, bottom)
        if (posX < 0) {
            posX = 0f
            velocityX = 0f
            accX = 0f
        }
        if (posX + ballSize > backgroundWidth) {
            posX = backgroundWidth - ballSize
            velocityX = 0f
            accX = 0f
        }
        if (posY < 0) {
            posY = 0f
            velocityY = 0f
            accY = 0f
        }
        if (posY + ballSize > backgroundHeight) {
            posY = backgroundHeight - ballSize
            velocityY = 0f
            accY = 0f
        }
    }

    /**
     * Resets the ball to the center of the screen with zero
     * velocity and acceleration.
     */
    fun reset() {
        // (Reset posX, posY, velocityX, velocityY, accX, accY, isFirstUpdate)
        posX = (backgroundWidth - ballSize) / 2f
        posY = (backgroundHeight - ballSize) / 2f
        velocityX = 0f
        velocityY = 0f
        accX = 0f
        accY = 0f
        isFirstUpdate = true
    }
}