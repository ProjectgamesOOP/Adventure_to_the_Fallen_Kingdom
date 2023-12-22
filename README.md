# **_Adventure to the Fallen Kingdom_**
<p>
<div text-align="center">
<img src="res\report\new_postergame.png" alt="">
</div>
</p>

# **_Table of content_**
1. [Background](#Background)
   - [Introduction](#introduction)
   - [Group members & Task allocation](#Group_members&Task_allocation)
   - [How to run project](#how-to-run-project)
2. [Motivation of project](#Motivation_of_project)
3. [Game](#Game)
   - [About the game](#About-the-game)
   - [Content](#Content)
   - [How to play](#how-to-play)
4. [Design & Capabilities](#Design-&-Capabilities)
5. [Challenging](#Challenging)
6. [4 principles of OOP](#Related-to-the-4-principles-of-OOP)
7. [UML Diagram](#UML-Diagram)

# **1.Background**
## a. _Introduction._
<div style = "text-align: justify"><p>
This is the game we created for the first semester of Object-Oriented Programming at VNUHCM - International University (2022–2023). This is a timeless game that is based on the 2D Platform Pixel genre and is incredibly well-liked worldwide. Our editing style is classic, and the protagonist is a Knight who must track down and vanquish the monster. The Kinght faced numerous challenges throughout the route, including slaying demons and traps. We hope you'll find our game project enjoyable.</p>
</div>

## b. _Team members and task allocation._
| Number |   Name   | Student ID | Task | Contribute|
| :----: | :------: | :--------: |:----------------------------------------------:|:------------:|
|   1    |   Duong Khanh Trang   |   ITDSIU21127  |Entities, Collision, Utilz, UML   |      25      |
|   2    |   Nguyen Quang Sang   |   ITDSIU21113  |Background, Entities, Utilz, UML  |      25      |
|   3    |   Phan Manh Son       |   ITDSIU21116  |Objects, Levels, Maps, Utilz, UML |      25      |
|   4    |   Van Phu Minh Sang   |   ITDSIU21112  |Game States, GUI, Utilz, UML      |      25      |
|   5    |   Tran Quynh Nhu      |   ITDSIU21105  |Inputs, Sound, Object, Utilz, UML |      25      |

## c. _How to run the game_

1. **Step 1:** Clone this Repository from our GitHub.
```c
git clone https://github.com/ProjectgamesOOP/ProjectgamesOOP.git
```
2. **Step 2:** Open the project source code folder in VS Code or any other IDE that supports Java language and check the file status.
```c
git status
```
3. **Step 3:** Run the game and enjoy

# **2. Motivation to Reality**
<div style = "text-align: justify">
<p>Our goal was to make a difficult 2D pixel game that would appeal to fans of the classics. Our intention is to test players' resolve and skill sets while instilling in them a sense of admirable accomplishments. With dynamic fighting, a comprehensive gameplay system, and a dark fantasy setting, the game mimics the challenge and sinister tone of the Dark Souls series. It's a hard game because every choice you make can impact the result. Just endurance, in the end, is worthy.</p>
</div>

# **3. GAME**
## a. _About the game._
- Language: [Java](https://www.java.com/en/)
- IDEs: [VSCode](https://code.visualstudio.com/), [Eclipse](https://www.eclipse.org/)
- Library: [JavaSwing](https://docs.oracle.com/javase/tutorial/uiswing/)
- Packages: [java.awt.image](https://docs.oracle.com/javase/8/docs/api/java/awt/Image.html), [java.awt.Graphics](https://docs.oracle.com/javase/7/docs/api/java/awt/Graphics.html), [java.awt.geom.Rectangle2D](https://docs.oracle.com/javase/7/docs/api/java/awt/geom/Rectangle2D.html), [java.awt.Color](https://docs.oracle.com/javase/7/docs/api/java/awt/Color.html), [java.util.ArrayList](https://docs.oracle.com/javase/8/docs/api/java/util/ArrayList.html), [java.awt.Point](https://docs.oracle.com/javase/8/docs/api/java/awt/Point.html), [java.awt.event.MouseEvent](https://docs.oracle.com/javase/8/docs/api/java/awt/event/MouseEvent.html), [java.awt.event.KeyEvent](https://docs.oracle.com/javase/8/docs/api/java/awt/event/KeyEvent.html), [java.util.Random](https://docs.oracle.com/javase/8/docs/api/java/util/Random.html), [java.util.ArrayList](https://docs.oracle.com/javase/8/docs/api/java/util/ArrayList.html), [javax.swing.JPanel](https://docs.oracle.com/javase/8/docs/api/javax/swing/JPanel.html), [java.awt.Dimension](https://docs.oracle.com/javase/8/docs/api/java/awt/Dimension.html)
- Game Engine: [Java2D](https://docs.oracle.com/javase/7/docs/api/java/awt/Graphics2D.html)
- Sound: [JavaSound](https://docs.oracle.com/javase/7/docs/api/javax/sound/sampled/Clip.html)

## b. _Content._
<div style = "text-align: justify">
<p>In Adventure to the Fallen Kingdom, set off on an exciting journey with the goal of becoming a renowned knight. Discover three different levels featuring pixel graphics and calming classical music. Gamers have to battle horrifying creatures that seek to encroach on human territory. Assume the part of the selected knight and discover your inner talent and bravery to conquer obstacles and establish your value as a respectable knight. Classic platform games serve as the foundation for Adventure to the Fallen Kingdom.</p>
</div>

<div text-align="center"><p>Level 1</p>
<img src="res\report\lvl1.png" alt="">
</div>

<div text-align="center"><p>Level 2</p>
<img src="res\report\lvl2.png" alt="">
</div>

## c. _How to play the game._

|  Key  |   Action   |
|:-----:|:----------:|
| A  |  Move Left   |
| D  | Move Right  |
| Space  | Jump  |
| Right Click Mouse  | Attack |
  
<div style = "text-align: justify">

- Use A, D, Space to move the knight.
- Use Right Click Mouse to attack the enemies.
- Watch your mana bar (yellow) below your health bar (red).
- Crack up boxes to discover mana or healing pills.
- Battle the boss with me without getting sick
</div>

# **4. Design & Capabilities**

<div style = "text-align: justify">
<p>To manipulate the color of pixels, we make use of BufferedImage(a class in Java that belongs to the java.awt.image package), an object that emulates image files. Each pixel in the game is a tile, entity, or object; distinct elements are indicated by red, green, and blue channels. The Level class uses methods like loadLevelData, loadEntities, and loadObjects to load level data from BufferedImage. These techniques generate related game elements by analyzing the colors of pixels. We maximize the amount of time and effort spent on development by obtaining game resources—such as sounds, music, and images—from Google.</p>
<p>Keyboard-based character control, a slashing technique for attacking the map until it collides with tiles, a tutorial at the first level, difficult bosses at the end level, an energy system for jumping, and potion-based recharging are just a few of the exciting updates.</p>
<p>We managed different modes and transitions in our game by using the State pattern. We designed our code to be flexible, maintainable, and readable by making separate classes for each state and using a Gamestate class to assign tasks to the current state object. Additionally, the Single Responsibility style is followed by the Constants class, which neatly keeps all game constants in one place. By guaranteeing that every class has a distinct purpose, this design principle encourages code readability and maintainability. Furthermore, constants are arranged by their respective categories (e.g., Projectiles, UI) in the Constants class.</p>
</div>
