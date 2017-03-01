package edu.uslt.cs.thesis.gis.manager;

import java.util.Stack;

import edu.uslt.cs.thesis.gis.screen.State;

public class StateManager {

    private Stack<State> states;

    public StateManager() {
        states = new Stack<State>();
    }

    public void add(State state) {
        states.push(state);
    }

    public void set(State state) {
        states.pop();
        states.push(state);
    }

    public void remove(State state) {
        states.pop();
    }

    public void update() {
        states.peek().update();
    }

    public void render() {
        states.peek().render();
    }

    public void resize(int width, int height) {
        states.peek().resize(width, height);
    }

    public void dispose() {
        states.peek().dispose();
    }
}
