Name: Lim Yi Hong
Student ID: 20215131

Work done for refactoring:
I have changed some method name to something that is much more easier to understand
such as playerBarMoveLeft or playerBarMoveTo.
I have also delete a few method that is redundant such as setSpeed, this is because
setSpeedX and setSpeedY will be able to do the same as setSpeed.
I have also added a few method to be able to reuse the same code rather than 
making a big chunk of codes that is messy.
I have also do encapsulation for public variables. 
I have also refactor the bricks by using design pattern. I have a BrickFactory to be able to call which brick is needed. 
I also have a BallFactory to be able to call which ball when further additions.
I have also organise the code by MVC in InstructionModel class, InstructionView class and InstructionController class 
which will be shown when user clicks on the Instruction Button.
I have also made class that has inner class to be split so that the SOLID principle is applied.
I have also split the Wall class into Wall and Level where wall will make the bricks wall at the top and level will do what
each level does, what bricks are use for each level. Wall class is split to apply SOLID principle.
I have also changed the background image of the home menu screen to beautify it.
I have also use the maven build file.
I have also done JUnit testing for a few meaningful method, such as Timer, where if seconds is more than 59, it becomes 0 and minute increase by 1.

Work done for addition:
I have added one new level and two new bricks. Special Brick and Slow Brick. Special Brick will invert the user movement when breaks.
For example, when user breaks the special brick, A will move right and D will move left until another special brick is broken. 
Slow Bricks will slow the user movement for 4 seconds. 
I have also added a few pictures that is related to the game such as hourglass, brick and balls.
I have also added two new features, an instruction page and a high score page that has its own background.
Instruction page will shows the information on how to play the game, what each bricks does.
High Score page will shows the top 7 scores that is stored in a permanent text file and the user current score.
Once the user finishes the game, either win or lose, the high score page will be shown and the score will be save to the file
if the scores are higher than the current permanent text file score. Then it will sort from highest score to lowest score.