import java.util.ArrayList;
import java.util.List;
class API
{
    /*Node salary;
    Node finalAnd;
    Node age30 = new Node("operand","age > 30");
    Node departmentM = new Node("operand","department = 'Marketing'");
    Node experience = new Node("operand","experience > 5");*/
    List<String> u =new ArrayList<>();
    public Node create_rule1(String rule1)
    {
        String cleaned = rule1.replaceAll("[()]", "");
        cleaned = cleaned.replaceAll(" AND ", ", ");
        cleaned = cleaned.replaceAll(" OR ", ", ");
        String[] conditions = cleaned.split(",\\s*");
        List<Node> n = new ArrayList<>();
        for(String str : conditions)
        {
            n.add(new Node(str));
        }
        if(n.size()==1)
        {
            return n.getFirst();
        }//return single condition as leaf node
        Node root1 = new Node("AND",n.get(0),n.get(1));  //start with an OR
        Node root2 = new Node("AND",n.get(2),n.get(3));
        Node root3 = new Node("OR",root1,root2);
        Node root4 = new Node("OR",n.get(4),n.get(5));
        return new Node("AND",root3, root4);
        /*Node departmentS = new Node("operand","department = 'Sales'");
        Node age25 = new Node("operand","age < 25");
        salary = new Node("operand","salary > 50000");
        Node and1 = new Node("operator",age30, departmentS);
        Node and2 = new Node("operator",age25, departmentM);
        Node or1 = new Node("operator",and1,and2);
        Node or2 = new Node("operator",salary,experience);
        finalAnd = new Node("operator",or1,or2);
        return finalAnd;*/
    }
    public Node create_rule2(String rule2)
    {
        String cleaned = rule2.replaceAll("[()]", "");
        cleaned = cleaned.replaceAll(" AND ", ", ");
        cleaned = cleaned.replaceAll(" OR ", ", ");
        String[] conditions = cleaned.split(",\\s*");
        List<Node> n = new ArrayList<>();
        for(String str : conditions)
        {
            n.add(new Node(str));
        }
        if(n.size()==1)
        {
            return n.getFirst();
        }
        Node root1 = new Node("AND",n.get(0),n.get(1));
        Node root2 = new Node("OR",n.get(2),n.get(3));
        return new Node("AND",root1,root2);
    }
    public Node combine_rules(Node root1, Node root2)
    {
        return new Node("AND",root1,root2);
    }
    public void evaluate_rule(String jsondata, String astjson)
    {
        int i,j1,k1,l1,z,y,w,j;
        int a1,a2,sa1,sa2,e1,e2;
        String c1;
        String c2;
        String c3;
        String s1 = jsondata.replaceAll("\\[","").replaceAll("]","");
        String[] s2 = s1.split(",");
        String[] s3 = new String[s2.length];
        String[] s5 = new String[s3.length];
        for(i=0;i<=s2.length-1;i++)
        {
            s3[i] = s2[i].trim();
        }
        s1=astjson.replaceAll("[(){}]","").replaceAll(":","").replaceAll("\\[","").replaceAll("]","").replaceAll("\"","");
        s2 = s1.split(",");
        String[] s4 = new String[s2.length];
        for(i=0;i<=s2.length-1;i++)
        {
            s4[i]=s2[i].trim();
        }
        for (i = 0; i <= s4.length - 1; i = i + 3)
        {
            if(i<=s4.length-1 && i+2<= s4.length-1) {
                s4[i] = s4[i].substring(3).trim();
                if(s4[i+2].startsWith("s"))
                    s4[i+2]=s4[i+2].substring(6).trim();
                else
                    s4[i+2]=s4[i+2].substring(10).trim();
            }
        }
        for(i=0;i<=s3.length-1;i++)
        {
            s5[i] = s3[i];
            s3[i]=s3[i].replaceAll("age = ","").replaceAll("salary = ","").replaceAll("experience = ","");
        }
        for(i=0;i<=s3.length-1;i++)
        {
            for(j=0;j<=s4.length-1;j=j+3)
            {
                j1=j;       //Age iterator in s4
                k1=j+1;    //Department Iterator in s4
                l1=j+2;    //Last element Iterator in s4
                if(l1<=s4.length-1)
                {
                    a1 = Integer.parseInt(s4[j1].substring(1));    //age from condition
                    c1 = s4[j1].substring(0, 1);                              //condition
                    a2 = Integer.parseInt(s3[i].substring(0, 2));
                    if (c1.equals("<")) {
                        if (a2 < a1) {                                      //age check
                            if(s3[i].contains(s4[k1]))       //department check
                            {
                                z= s3[i].lastIndexOf("'");
                                y=z+2;
                                char c=' ';
                                w=0;
                                if(s4[l1].length()>2)                      //element is salary
                                {
                                    sa1 = Integer.parseInt(s4[l1].substring(1));  //salary from condition
                                    c2 = s4[l1].substring(0,1);
                                    while(s3[i].charAt(y)!=c)
                                    {
                                        w=y;
                                        y++;
                                    }
                                    sa2 = Integer.parseInt(s3[i].substring(z+2,w+1));  //salary from database
                                    if(c2.equals("<"))
                                    {
                                        if(sa2<sa1)                               //salary check
                                        {
                                            if(!u.contains(s5[i]))
                                            {
                                                u.add(s5[i]);
                                            }
                                        }
                                    }
                                    else if(c2.equals(">"))
                                    {
                                        if(sa2>sa1)
                                        {
                                            if(!u.contains(s5[i]))
                                            {
                                                u.add(s5[i]);
                                            }
                                        }
                                    }
                                }
                                else                //element is experience
                                {
                                    e1 = Integer.parseInt(s4[l1].substring(1));  //experience from condition
                                    c3 = s4[l1].substring(0,1);
                                    e2 = Integer.parseInt(s3[i].substring(w+2));  //experience from database
                                    if(c3.equals("<"))
                                    {
                                        if(e2<e1)
                                        {
                                            u.add(s5[i]);
                                        }
                                    }
                                    else if(c3.equals(">"))
                                    {
                                        if(e2>e1)
                                        {
                                            if(!u.contains(s5[i]))
                                            {
                                                u.add(s5[i]);
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                    else if(c1.equals(">"))
                    {
                        if(a2>a1)
                        {
                            if(s3[i].contains(s4[k1]))       //department check
                            {
                                z= s3[i].lastIndexOf("'");
                                y=z+2;
                                char c=' ';
                                w=0;
                                if(s4[l1].length()>2)                      //element is salary
                                {
                                    sa1 = Integer.parseInt(s4[l1].substring(1));  //salary from condition
                                    c2 = s4[l1].substring(0,1);
                                    while(s3[i].charAt(y)!=c)
                                    {
                                        w=y;
                                        y++;
                                    }
                                    sa2 = Integer.parseInt(s3[i].substring(z+2,w+1));  //salary from database
                                    if(c2.equals("<"))
                                    {
                                        if(sa2<sa1)                               //salary check
                                        {
                                            if(!u.contains(s5[i]))
                                            {
                                                u.add(s5[i]);
                                            }
                                        }
                                    }
                                    else if(c2.equals(">"))
                                    {
                                        if(sa2>sa1)
                                        {
                                            if(!u.contains(s5[i]))
                                            {
                                                u.add(s5[i]);
                                            }
                                        }
                                    }
                                }
                                else                //element is experience
                                {
                                    e1 = Integer.parseInt(s4[l1].substring(1));  //experience from condition
                                    c3 = s4[l1].substring(0,1);
                                    while(s3[i].charAt(y)!=c)
                                    {
                                        w=y;
                                        y++;
                                    }
                                    e2 = Integer.parseInt(s3[i].substring(z+2,w+1));  //experience from database
                                    if(c3.equals("<"))
                                    {
                                        if(e2<e1)
                                        {
                                            if(!u.contains(s5[i]))
                                            {
                                                u.add(s5[i]);
                                            }
                                        }
                                    }
                                    else if(c3.equals(">"))
                                    {
                                        if(e2>e1)
                                        {
                                            if(!u.contains(s5[i]))
                                            {
                                                u.add(s5[i]);
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        System.out.println("Eligible User: ");
        for(String str : u)
        {
            System.out.println(str);
        }
    }
}