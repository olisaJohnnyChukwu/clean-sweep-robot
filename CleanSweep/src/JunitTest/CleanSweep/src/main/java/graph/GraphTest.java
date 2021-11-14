package CleanSweep.src.main.java.graph;

import graph.Graph;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.io.*;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;

class GraphTest {

    @Test
    void neighbours() {
        Graph g = new Graph();
        g.addedge(1, 2);
        HashSet<Integer> s = g.neighbours(1);
        Object []result = s.toArray();
        assertEquals(2, result[0]);
    }




    @Test
    void getPath() {
        //path with depth first search
        Graph graph = new Graph();
        graph.addedge(1, 2);
        graph.addedge(3,4);
        graph.addedge(5,6);
        graph.addedge(2,6);
        graph.addedge(6,7);


        System.out.println(graph.getPath(2, 7).getClass().getName());
        //Last-In-First-Out
        Stack s = new Stack();
        s.push(7);
        s.push(6);
        s.push(2);



        assertEquals(s, graph.getPath(2,7));

    }
    @Test
    void shortestPathTest(){
        //path with depth first search
        Graph graph = new Graph();

        graph.addedge(3,4);
        graph.addedge(5,6);
        graph.addedge(2,6);
        graph.addedge(7,8);
        graph.addedge(6,8);

        System.out.println(graph.getPath(2, 7).getClass().getName());
        //Last-In-First-Out
        Stack s = new Stack();
        s.push(8);
        s.push(6);
        s.push(2);



        assertEquals(s, graph.getPath(2,8));
    }

}