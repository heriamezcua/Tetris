# Table of Contents

- [Introduction](#introduction)  
- [Application Structure](#application-structure)  
- [Goals](#goals)  
- [Control-Flow Diagram](#control-flow-diagram)  
- [How To Play](#how-to-play)  
- [Demo](#demo)


## Introduction

This Tetris project is an implementation of the classic tile-matching puzzle game, written in Java. It features a functional game board, responsive keyboard controls, and various Tetrimino shapes with proper rotation mechanics.

The project consists of:
- A Board class that manages the game grid and piece interactions.
- Various Tetrimino classes, each representing a different shape with rotation support.
- A ConsoleKeyListener that handles user input for moving and rotating the pieces.

## Application Structure
```bash
Tetris
├── src
│   ├── tetris
│   │   ├── Board.java              # Manages the game board and Tetrimino placement
│   │   ├── ConsoleKeyListener.java # Captures and processes player input
│   │   ├── LogManager.java         # Handles logging and debugging
│   │   ├── SoundManager.java       # Handles sounds in the game
│   │   └── TetrisApp.java          # Main application class, starts the game
│   │
│   ├── tetrimino
│   │   ├── Tetrimino.java          # Abstract base class for all Tetrimino shapes
│   │   ├── IShape.java             # Represents the 'I' shaped Tetrimino
│   │   ├── OShape.java             # Represents the 'O' shaped Tetrimino
│   │   ├── JShape.java             # Represents the 'J' shaped Tetrimino
│   │   ├── LShape.java             # Represents the 'L' shaped Tetrimino
│   │   ├── SShape.java             # Represents the 'S' shaped Tetrimino
│   │   ├── ZShape.java             # Represents the 'Z' shaped Tetrimino
│   │   ├── TShape.java             # Represents the 'T' shaped Tetrimino
│   │   ├── TetriminoFactory.java   # Factory pattern for generating Tetrimino objects
│
├── resources
│   ├── sounds                      # Contains game audio files
│
├── bin/                            # Compiled .class files
├── libs/                           # External libraries (if any)
├── logs/                           # Log files for debugging
├── .gitignore                      # Specifies files ignored by Git
├── LICENSE                         # Project licensing information
└── README                          # Project overview and usage instructions

```

## Goals
My main objectives of developing this Tetris project include:

- **1. Learning and Practicing Java**
Strengthening object-oriented programming (OOP) skills.
Applying principles such as encapsulation, inheritance, and polymorphism.
Improving Java proficiency through practical implementation.

- **2. Implementing Core Tetris Mechanics**
Designing various Tetrimino shapes with correct rotation logic.
Managing piece movement and collisions within a game board.
Handling line clearing when rows are completed.

- **3. Creating an Interactive User Experience**
Capturing keyboard inputs to control the game using ConsoleKeyListener.
Ensuring smooth and responsive gameplay.

- **4. Building a Modular and Expandable Codebase**
Structuring the code in a clean and maintainable way.
Allowing future enhancements like scoring, animations, and additional game modes.

- **5. Understanding Game Development Concepts**
Learning about event-driven programming with keyboard listeners.
Managing game state updates and rendering logic.

- **6. Adding Sound to the Game**
Implementing background music and sound effects for actions like rotating, dropping, and clearing lines.
Learning how to use Java libraries (e.g., javax.sound.sampled) to handle audio playback.
Enhancing user engagement with well-integrated sound design.

## Control-Flow Diagram
![flow control diagrama](https://github.com/user-attachments/assets/2b3b265c-8991-42cd-a9a5-fc84cd325549)


## How To Play
1. Clone this github repository in your computer
 ```bash
git clone https://github.com/heriamezcua/Tetris.git
```

3. Compile the source code
```bash
javac -d .\bin .\src\tetrimino\*.java .\src\tetris\*.java
```

3. Execute the game
```bash
java -cp .\bin tetris.TetrisApp
```

4. Enjoy the game!

## Demo

### Game Starts
![Screenshot 2025-03-16 093156](https://github.com/user-attachments/assets/771ba4fa-5379-49c6-bfde-2107041fd7b2)

### Lot of tetriminoes placed and one is going to complete a row
![Screenshot 2025-03-16 093226](https://github.com/user-attachments/assets/764b0a2a-0383-4a0f-931d-1150cdf289bd)

### Row completed
![Screenshot 2025-03-16 093236](https://github.com/user-attachments/assets/8351b776-fe35-416a-9a1a-4d16d0f010e5)

### Game over
![Screenshot 2025-03-16 093305](https://github.com/user-attachments/assets/06c1bd5d-484a-4905-a540-db9b10480568)
