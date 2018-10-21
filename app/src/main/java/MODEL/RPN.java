package MODEL;

/**
 * Created by Liang Zihong on 2018/10/16.
 */


import java.util.ArrayList;
import java.util.Stack;
import java.util.StringTokenizer;


public class RPN
{
    private Stack<String> StackOperator=new Stack<>();  //存放运算符的栈
    private ArrayList<String> ListPreExpression=new ArrayList<String>(); //存放原表达式
    private ArrayList<String> ListExpression=new ArrayList<String>();   //存放逆波兰表达式
    private Stack<String> StackOperation=new Stack<>();   //运算栈

    //分割表达式（初始化）
    public RPN (String PreExpression)
    {
        //以+-*/（）为分割符，对原始表达式（字符串）进行分割，并返回分割符
        StringTokenizer stringTokenizer=new StringTokenizer(PreExpression,"\\+|\\-|\\*|\\/|\\(|\\)",true);
        while(stringTokenizer.hasMoreTokens())
        {
            ListPreExpression.add(stringTokenizer.nextToken());
        }
    }


    //判断是否为运算符
    public boolean isOperator(String str)
    {
        if("+-*/tancossinlog()".indexOf(str)>=0)
            return true;
        else
            return false;
    }

    //获取运算符的优先级
    public int getPriority(String str)
    {
        int a;
        switch(str)
        {
            case "+":
            case "-":a=1;break;
            case "*":
            case "/":a=2;break;
            case "(":a=4;break;
            case ")":a=0;break;
            case "tan":
            case "sin":
            case "cos":
            case "log":a=3;break;
            default: a=-1;break;
        }
        return a;
    }

    //比较优先级大小
    public boolean ComparePriority(String str1,String str2)
    {
        return getPriority(str1)>getPriority(str2);
    }

    //运算符入栈
    public void OperatorToStack(String str)
    {
        if(StackOperator.isEmpty())           //判断栈是否为空
            StackOperator.push(str);
        else if("(".equals(str))              //判断传入的运算符是否为"("
            StackOperator.push(str);
        else if(")".equals(str))              //判断传入的运算符是否为")"
        {
            String string;
            while(!"(".equals(string=StackOperator.pop())) //将"("和")"之间的操作符存放到ListExpression中
                ListExpression.add(string);
        }
        else if("(".equals(StackOperator.peek()))  //若栈顶元素为（，运算符直接入栈
            StackOperator.push(str);
        else if(ComparePriority(str,StackOperator.peek()))//若str优先级大于栈顶元素则入栈
            StackOperator.push(str);
        else if(!ComparePriority(str,StackOperator.peek()))//若小于则将栈顶元素弹出，str与下一个元素比较
        {
            ListExpression.add(StackOperator.pop());
            OperatorToStack(str);
        }
    }


    //中缀表达式转换成后缀表达式
    public void ListPreExpressionToListExpression()
    {
        for(String str:ListPreExpression)         //遍历ListPreExpression，运算符加入OperatorToStack,数字加入ListExpression
        {
            if(isOperator(str))
                OperatorToStack(str);
            else
                ListExpression.add(str);
        }

        //遍历完原始表达式后，将StackOperator栈内的运算符全部添加到ListExpression中
        while(!StackOperator.isEmpty())
        {
            ListExpression.add(StackOperator.pop());
        }
    }


    //计算方法

    //双目运算
    public int open=0;//用于判断输入是否正确
    public double Operation2(String str1,String str2,String str3)
    {
        double num1,num2;
        num2=Double.valueOf(str1).doubleValue();
        num1=Double.valueOf(str2).doubleValue();
        if(str3.equals("+"))
            return num1+num2;
        else if(str3.equals("-"))
            return num1-num2;
        else if(str3.equals("*"))
            return num1*num2;
        else
        {
            if(num2==0)           //除数为零时，open=1，后面会对open=1进行输出“illegal”。
                open=1;
            return num1/num2;
        }
    }

    //单目运算
    public double Operation1(String str1,String str2)
    {
        double num1;
        num1=Double.valueOf(str1).doubleValue();
        if(str2.equals("tan"))
        {
            if(num1==90)           //tan（90）不存在，open=2，其后的代码会输出“non-existent!"
                open=2;
            return 1;
        }
        else if(str2.equals("sin"))
            return Math.sin(num1);
        else if(str2.equals("cos"))
            return Math.cos(num1);
        else
            return Math.log(num1);

    }

    //计算逆波兰表达式
    public String Opera_ListExpr()
    {
        int open2=0;         //用于判断输入是否正确
        for(String str:ListExpression)
        {
            if(str.equals("(")||str.equals(")"))      //若ListExpression中含有括号，则报错
                open2=1;
            else if(!isOperator(str))          //把数字存放进运算栈中
                StackOperation.push(str);
            else if("tansincoslog".indexOf(str)>=0)    //tan、sin、cos、log进行单目运算
                StackOperation.push(String.valueOf(Operation1(StackOperation.pop(),str)));
            else
                //其它运算符进行双目运算
                StackOperation.push(String.valueOf(Operation2(StackOperation.pop(),StackOperation.pop(),str)));
        }
        if(open==1||open2==1)
        {
            open=0;
            return "illegal!";
        }
        else if(open==2)
        {
            open=0;
            return "non-existent!";
        }
        else
            return StackOperation.pop();
    }



    public static String getAnswer(String question)
    {
        RPN rpn=new RPN(question);
        rpn.ListPreExpressionToListExpression();
        double ans = Double.valueOf(rpn.Opera_ListExpr()).doubleValue();

        return ans+"";
    }



}
