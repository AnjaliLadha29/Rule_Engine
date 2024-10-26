class Rules
{
    Node node1;
    Node node2;
    Node combine;
    String rule1 = "age > 30 AND department = Sales OR age < 25 AND department = Marketing AND salary > 50000 OR experience > 5";
    String rule2 = "age > 30 AND department = Marketing AND salary > 20000 OR experience > 5";
    public void rules()
    {
        API a = new API();
        node1 = a.create_rule1(rule1);
        node2 = a.create_rule2(rule2);
        combine = a.combine_rules(node1, node2);
        //System.out.println(combine.toString());
    }
}