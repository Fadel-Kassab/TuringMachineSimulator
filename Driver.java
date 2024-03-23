import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Driver {
    public static void main(String[] args) {

        // This example is of a Turing Machine that given a string tells if it is even in length or no
        char[] alphabet = { 'a', '_', 'x' };
        String[] states = { "odd", "even", "start", "accept", "reject" };
        Map<List<String>, List<String>> transitionFunction = new HashMap<>();
        transitionFunction.put(Arrays.asList("start", "_"), Arrays.asList("accept", "_", "R"));
        transitionFunction.put(Arrays.asList("start", "a"), Arrays.asList("odd", "x", "R"));
        transitionFunction.put(Arrays.asList("odd", "_"), Arrays.asList("reject", "_", "R"));
        transitionFunction.put(Arrays.asList("odd", "a"), Arrays.asList("even", "x", "R"));
        transitionFunction.put(Arrays.asList("even", "_"), Arrays.asList("accept", "_", "R"));
        transitionFunction.put(Arrays.asList("even", "a"), Arrays.asList("odd", "x", "R"));

        TuringMachine isEvenDecider = new TuringMachine(alphabet, states, transitionFunction);
        System.out.println("\nisEvenAlgorithm: ");
        System.out.println(isEvenDecider.compute("aaaaaa"));
        System.out.println();

        // This algorithm checks if every bit in a given binary string is '1'.
        char[] alphabet2 = { '0', '1', '_' };
        String[] states2 = { "CheckTrue", "start", "accept", "reject" };
        Map<List<String>, List<String>> transitionFunction2 = new HashMap<>();

        transitionFunction2.put(Arrays.asList("start", "0"), Arrays.asList("reject", "0", "R"));
        transitionFunction2.put(Arrays.asList("start", "1"), Arrays.asList("CheckTrue", "1", "R"));
        transitionFunction2.put(Arrays.asList("start", "_"), Arrays.asList("accept", "_", "R")); 
        transitionFunction2.put(Arrays.asList("CheckTrue", "0"), Arrays.asList("reject", "0", "R"));
        transitionFunction2.put(Arrays.asList("CheckTrue", "1"), Arrays.asList("CheckTrue", "1", "R"));
        transitionFunction2.put(Arrays.asList("CheckTrue", "_"), Arrays.asList("accept", "_", "R"));

        TuringMachine isTrueAllMachine = new TuringMachine(alphabet2, states2, transitionFunction2);
        System.out.println("isAllTrueAlgorithm: ");
        System.out.println(isTrueAllMachine.compute("1111101"));
        System.out.println();

        // This machine will tell if in a given string there is exactly one 1.

        char[] alphabet3 = { '0', '1', '_' };
        String[] states3 = { "FindOne", "CheckEnd", "start", "accept", "reject" };
        Map<List<String>, List<String>> transitionFunction3 = new HashMap<>();
        transitionFunction3.put(Arrays.asList("start", "0"), Arrays.asList("FindOne", "0", "R"));
        transitionFunction3.put(Arrays.asList("start", "1"), Arrays.asList("CheckEnd", "1", "R"));
        transitionFunction3.put(Arrays.asList("start", "_"), Arrays.asList("reject", "_", "R")); 
        transitionFunction3.put(Arrays.asList("FindOne", "0"), Arrays.asList("FindOne", "0", "R"));
        transitionFunction3.put(Arrays.asList("FindOne", "1"), Arrays.asList("CheckEnd", "1", "R"));
        transitionFunction3.put(Arrays.asList("FindOne", "_"), Arrays.asList("reject", "_", "R"));
        transitionFunction3.put(Arrays.asList("CheckEnd", "0"), Arrays.asList("CheckEnd", "0", "R"));
        transitionFunction3.put(Arrays.asList("CheckEnd", "1"), Arrays.asList("reject", "1", "R")); 
        transitionFunction3.put(Arrays.asList("CheckEnd", "_"), Arrays.asList("accept", "_", "R")); 

        TuringMachine hasExactlyOneOneMachine = new TuringMachine(alphabet3, states3, transitionFunction3);
        
        System.out.println("thereIsExactlyOneAlgorithm: ");
        System.out.println(hasExactlyOneOneMachine.compute("00010000"));
        System.out.println();
    }
}
