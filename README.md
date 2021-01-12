# GraphEditor

This tool is an add-on for my Stipdendium from the Nassau Central Studies Fund. I develop it in the course of the TGI lecture. The program will be programmed in Java and should be able to minimize a graph and convert NEAs into DEAs.

There are only a few buttons. In the left menu you choose how you want to change the given automaton: do you want to add edges? delete nodes? etc....

In the right menu you can choose to minimize the given DEA ...Or rather load a test-DEA to save the work of entering it.

-------------------------------------------------------------

In the documentation you will find an old version on which I stopped working relatively early, because there were too many problems and the logic did not fit.

After that I only worked on Graph Edit V2 which is now also functional.
So you can find working source code only in **/GraphEditV2/src/main/java/graphEdit/files**.

If you want to run the program you can either execute the file **GraphEditor.java** directly in the initial folder or compile the project yourself.

How to interpret the Graph?
* round circles are Vertices of the graph
* straight lines with a dot at the tip are connections of Vertices from the side without the dot to the one with the dot
* accepting vertices have two round circles around them
* the start button has a smal arrow from nowhere to it

-------------------------------------------------------------

Since the amount of work was much higher than expected I decided not to implement the NEA -> DEA function (as indicated on my sketches).

--------------------------------------------------------------

What does it mean to minimize a deterministic finite automaton (DEA)?

Two DEAs are called equivalent if they recognize exactly the same language. So if for every input s it applies that the DEAs are either in a valid final state (marked in my program by two rings) or not.

However, one DEA can be much larger than another and have many unnecessary nodes and transitions. With the graph minimization, you just want the most minimal DEA that still recognizes the same language as the original one.

Here you can see two examples. Both machines recognize the same language but the right is smaller. My tool turns the left into the right Graph.

![not minimized](https://github.com/SamuelBorn/GraphEditor/blob/main/Images/not_minimized.png)
![minimized](https://github.com/SamuelBorn/GraphEditor/blob/main/Images/minimized.png)

