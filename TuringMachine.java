// The following is a TuringMachine class that simulates any single-tape deterministic turing machine with the illusion of infinite memory ofcourse 
// Fadel Kassab
// 3/23/2024


import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TuringMachine {
    Tape tape;
    char[] alphabet;
    String[] states; // This SHOULD be of form: {q1, q2, q3, ..., qn, qS, qA, qR} where q1, ..., qn
                     // are general states, and qS is the start state, qA the accept state and qR the
                     // reject state
    Map<List<String>, List<String>> transitionFunction = new HashMap<>(); // this SHOULD be of form {[a, b] : [x, y, z]}
                                                                          // where a is from the states, b is from the
                                                                          // alphabet, x is from the states, y is from
                                                                          // the alphabet, and z is either L or R and it
                                                                          // is expected that every possible couple [a,
                                                                          // b] is provided
    boolean showComputation;

    public void showComputation() {
        this.showComputation = true;
    }

    public void hideComputation() {
        this.showComputation = false;
    }

    public TuringMachine(char[] alphabet, String[] states, Map<List<String>, List<String>> transitionFunction) {
        this.tape = new Tape();
        this.alphabet = alphabet;
        this.states = states;
        this.transitionFunction = transitionFunction;
        this.showComputation = true;
    }

    public boolean compute(String input) {
        tape.writeInputString(input);

        List<String> keyCouple = Arrays.asList(this.states[this.states.length - 3],
                Character.toString(this.tape.read()));
        List<String> valueTriplet = new ArrayList<>();

        while (true) {
            if (showComputation) {
                this.tape.printTape(keyCouple.get(0));
                System.out.println();
            }

            valueTriplet = transitionFunction.get(keyCouple); // Execute the transition function

            if (valueTriplet.get(0).equals(this.states[this.states.length - 1])) { // Check if halts
                return false;
            } else if (valueTriplet.get(0).equals(this.states[this.states.length - 2])) {
                return true;
            }

            this.tape.write(valueTriplet.get(1).charAt(0)); // write new symbol

            if (valueTriplet.get(2).charAt(0) == 'R') { // move right or left
                this.tape.moveRight();
            } else if (valueTriplet.get(2).charAt(0) == 'L') {
                this.tape.moveLeft();
            }

            // Update keyCouple for the next iteration
            keyCouple = Arrays.asList(valueTriplet.get(0), Character.toString(this.tape.read()));
        }
    }
}