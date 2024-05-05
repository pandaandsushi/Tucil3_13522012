# Tucil3 Stima - Word Ladder Solver Using UCS, Greedy Best First Search, and A* Algorithm

| Names                     | NIM      |
| ----------------------    |:--------:|
| Thea Josephine H          | 13522012 |

## Table of Contents 💫
* [The Game](#the-game)
* [The Algorithms](#the-algorithms)
* [Requirements](#requirements)
* [Setting Up](#setting-up)
* [How To Use](#how-to-use)
* [File Structure](#file-structure)

## The Game
The Word Ladder game challenges players to transform one word into another by changing one letter at a time, forming valid words at each step. Players start with a given "start" word and aim to reach the "end" word using the shortest possible sequence of valid intermediate words.

## The Algorithms
UCS Algorithm
Searching algorithm based on actual cost g(n), that we can get from totalling all the cost it takes to get to the current node. 

Greedy Best First Search Algorithm
Searching algorithm based on heuristic cost h(n), that we can get from counting different letters between start and end word. 

A* Algorithm
Searching algorithm based on actual cost g(n) + h(n), that we can get from totalling all the cost it takes to get to the current node. 
- Start from start word.
- Enqueue all neighboring words (validated by the Dictionary provided) into the prioqueue based on the cost. Neighboring words are the current word with one letter changed.
- Dequeue a word from the queue.
- Check if it's the end word, if not, enqueue it's neighbor again.
- Repeat until queue is empty or end word is found.

## Requirements
none.

## Setting Up
- Clone this repository on your terminal `git clone https://github.com/pandaandsushi/Tucil3_13522012.git`
- Option 1: use terminal and change directory to `cd src`. Type in `javac Main.java` then `java Main`.
- Option 2: use GUI and change directory to `cd src`. Type in `javac WordLadderGUI.java` then `java WordLadderGUI`.
- Option 3: run the exe  

## How to Use
- Input the start word and end word
- Choose between using UCS, GBFS, or A* algorithm
- Result will be displayed immediately including the details (num of nodes checked and algo time)

## File structure
```
.
├── README.md
├── bin
│   └── Tucil3_13522012.pdf
├── doc
│   └── Tucil3_13522012.pdf
├── src
│   ├── Dictionary.txt
│   ├── Dict.java
│   ├── Node.java
│   ├── Algorithm.java
│   ├── Funtions.java
│   ├── Result.java
│   ├── WordLadderGUI.java
│   ├── Dict.class
│   ├── Node.class
│   ├── Algorithm.class
│   ├── Funtions.class
│   ├── Result.class
│   ├── WordLadderGUI.class
│   ├── Main.class
│   └── Main.java
├── test
│   ├── tc1.txt
│   ├── tc2.txt
│   ├── tc3.txt
│   ├── tc4.txt
│   ├── tc5.txt
│   ├── tc6.txt
│   ├── tc7.txt
│   ├── tc8.txt
│   ├── tc9.txt
│   ├── tc10.txt
│   └── tc11.txt
```
## Thankyou for trying my program :>
