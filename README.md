# Bouncing_Ball Project
This Java application creates a simple graphical program where a red ball bounces around within a window. The user can click and drag the ball to change its position and speed.

## Demo

https://github.com/GalanRaduM24/Bouncing_Ball/assets/135463995/35ddf306-ae33-406f-9896-e00bca247441

## Features
- Bouncing Ball: The ball bounces off the edges of the window, reversing its direction upon collision.
- Mouse Interaction:
  - Click and Drag: Users can click and drag the ball to move it to a new position.
  - Release: Upon releasing the mouse button, the ball's speed is adjusted based on the drag distance.
 
## Main Components
1. BouncingBall Class: Extends JFrame and contains the main logic for the bouncing ball and mouse interactions.
2. BallPanel Class: Extends JPanel and is responsible for drawing the ball on the screen.

## Usage
To run the application, compile the BouncingBall.java file and execute the resulting class file:
```
  javac BouncingBall.java
```
```
  java BouncingBall
```
  
Upon execution, a window will appear with a bouncing red ball. You can interact with the ball using the mouse as described above.

## Dependencies
- Java SE Development Kit (JDK)
- Swing library (included in JDK)

## Dependency Management

The `JAVA PROJECTS` view allows you to manage your dependencies. More details can be found [here](https://github.com/microsoft/vscode-java-dependency#manage-dependencies).
