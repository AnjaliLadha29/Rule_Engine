class Node
{
    //It will use sample rule class for operating
    private Node left; // Left child
    private Node right; // Right child
    private String value; // Value for operand nodes (e.g., "age", "30")
    // Constructor for operator nodes
    public Node(String value, Node left, Node right) {
        this.value=value;
        this.left = left;
        this.right = right;
    }
    // Constructor for operand nodes
    public Node(String value) {
        this.value = value;
    }
    public String toString() {
        if (left == null && right == null) {

            return value;    //Leaf Node
        }
        return "Operator: " + value + ", Left: [" + left + "], Right: [" + right + "]";
    }
}
