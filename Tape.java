public class Tape {
    TapeNode currentTapeNode;

    public Tape() {
        this.currentTapeNode = new TapeNode();
    }

    public void moveLeft() {
        if (this.currentTapeNode.left == null) {
            TapeNode newTapeNode = new TapeNode();

            this.currentTapeNode.left = newTapeNode;
            newTapeNode.right = this.currentTapeNode;
        }

        this.currentTapeNode = this.currentTapeNode.left;
    }

    public void moveRight() {
        if (this.currentTapeNode.right == null) {
            TapeNode newTapeNode = new TapeNode();

            this.currentTapeNode.right = newTapeNode;
            newTapeNode.left = this.currentTapeNode;
        }

        this.currentTapeNode = this.currentTapeNode.right;
    }

    public char read() {
        return currentTapeNode.datum;
    }

    public void write(char symbol) {
        currentTapeNode.datum = symbol;
    }

    public void writeInputString(String w) {
        this.currentTapeNode = new TapeNode();

        for (int i = 0; i < w.length(); i++, this.moveRight()) {
            this.write(w.charAt(i));
        }

        // This will remove the extra blank
        this.moveLeft();
        this.currentTapeNode.right = null;

        while (this.currentTapeNode.left != null) {
            this.moveLeft();
        }
    }

    public void printTape(String state) {
        // Go to start
        TapeNode iteratorNode = this.currentTapeNode;

        while (iteratorNode.left != null) {
            iteratorNode = iteratorNode.left;
        }

        // Print tape
        while (iteratorNode.right != null) {
            if (iteratorNode == currentTapeNode) {
                System.out.print("-> ");
            }
            System.out.print(iteratorNode.datum + " ");

            iteratorNode = iteratorNode.right;
        }
        if (iteratorNode == currentTapeNode) {
            System.out.print("-> ");
        }
        System.out.print(iteratorNode.datum);
        System.out.print("     STATE: " + state);
    }
}