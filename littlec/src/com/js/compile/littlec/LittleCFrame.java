package com.js.compile.littlec;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;

/**
 * Created by Administrator on 2017-10-12.
 */
public class LittleCFrame extends JFrame{

    List<String> keywords = new ArrayList<>();
    List<String> keywordList,rekeywordList;
    List<String> digitList,redigitList;
    List<String>  idList,reidList;
    List<String> noteList;
    List<String> finalList;

    String[] keywordtable=new String[500];
    String[] finaltable=new String[500];
    Integer[] finaltableint=new Integer[500];
    String[] idtable=new String[500];
    String[] digittable=new String[500];
    String[] notetable=new String[500];
    String[] otherchartable=new String[500];
    //String[] finaltableint;


    int finalListint;
    String word="";
    //char[] word={};
    String info="";
    int index=0;
    String sourcecode="";

    int digit_num=0,keyword_num=0,otherchar_num=0,id_num=0,note_num=0;
    int redigit_num=0,rekeyword_num=1,reotherchar_num=1,reid_num=1;
    int final_num=0,finalnum=0;
    int flag_error=0;
    int flagerror=0;
    Character lookahead;


    /**
     * 图形组件
     */
    JMenuBar jMenuBar;
    JMenu fileMenu;
    JMenu editMenu;
    JMenuItem fileOpenMenuItem;
    JMenuItem fileSaveMenuItem;
    JMenuItem fileSaveAsMenuItem;

    JToolBar jToolBar;
    JButton openButton;
    JButton savaButton;
    JButton clearTextButton;
    JButton clearInfoButton;
    JButton compileButton;

    JSplitPane mainSplitPane;
    JScrollPane fileScrollPane;
    JPanel rightPanel;

    RichTabbedPane contentTab;
    JScrollPane infoScrollpane;
    JTextArea infoTextArea;

    JScrollPane contentScrollPane;
    JTree leftTree;
    JTextArea textArea;


    public LittleCFrame(){
        init();
    }


    public void init() {
        keywords.add("if");
        keywords.add("for");
        keywords.add("else");
        keywords.add("while");
        keywords.add("do");
        keywords.add("float");
        keywords.add("int");
        keywords.add("break");

        //swing init...
        jMenuBar = new JMenuBar();
        fileMenu = new JMenu("文件");
        editMenu = new JMenu("编辑");
        fileOpenMenuItem = new JMenuItem("打开文件...");
        fileSaveMenuItem = new JMenuItem("保存文件...");
        fileSaveAsMenuItem = new JMenuItem("另保为...");

        fileMenu.add(fileOpenMenuItem);
        fileMenu.add(fileSaveMenuItem);
        fileMenu.add(fileSaveAsMenuItem);

        jMenuBar.add(fileMenu);
        jMenuBar.add(editMenu);
        setJMenuBar(jMenuBar);
        jToolBar = new JToolBar();
        openButton = new JButton("打开文件");
        savaButton = new JButton("保存文件");
        compileButton = new JButton("语法分析");
        JSeparator separator = new JSeparator();
        clearTextButton = new JButton("清除程序");
        clearInfoButton = new JButton("清除信息");

        clearTextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textArea.setText("");
            }
        });

        clearInfoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                infoTextArea.setText("");
            }
        });

        compileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                doCompile();
            }
        });

        //add(jToolBar);
        jToolBar.add(openButton);
        jToolBar.add(savaButton);
        jToolBar.add(compileButton);
        //jToolBar.add(separator);
        jToolBar.add(clearTextButton);
        jToolBar.add(clearInfoButton);


        mainSplitPane = new JSplitPane();
        leftTree = new JTree();
        textArea = new JTextArea();
        fileScrollPane = new JScrollPane(leftTree);
        contentScrollPane = new JScrollPane(textArea);

        rightPanel = new JPanel();
        contentTab = new RichTabbedPane();
        contentTab.add("文件内容",contentScrollPane);
        BorderLayout rightborderLayout = new BorderLayout();
        rightPanel.setLayout(rightborderLayout);
        rightPanel.add(contentTab,BorderLayout.CENTER);

        infoTextArea = new JTextArea();
        infoScrollpane = new JScrollPane(infoTextArea);
        //infoTextArea.setPreferredSize(new Dimension());
        infoScrollpane.setPreferredSize(new Dimension(800, 300));
        rightPanel.add(infoScrollpane,BorderLayout.SOUTH);

        mainSplitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, fileScrollPane, rightPanel);

        BorderLayout borderLayout = new BorderLayout();
        getContentPane().setLayout(borderLayout);
        getContentPane().add(jToolBar, BorderLayout.NORTH);
        getContentPane().add(mainSplitPane, BorderLayout.CENTER);
    }

    public static void main(String[] args) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                LittleCFrame auto = new LittleCFrame();
                //Dimension dimension = new Dimension(800, 800);

                Dimension dimension = ScreenUtils.screenDimension();

                Point point = LocationUtils.getLocationPointTopAjust(dimension,
                        0);
                auto.setSize(dimension);
                auto.setLocation(point);
                // auto.setLocation(300, 100);
                auto.setVisible(true);
            }
        });
    }

    public  void initialize(){
        word ="";
    }

    public boolean isalpha(char ch){
        return Character.isAlphabetic(ch);
    }

    public boolean isdigit(char ch){
        return Character.isDigit(ch);
    }

    public int strcmp(String word,String bbb){
        if(word.equals(bbb)){
            //System.out.println("");
            return 0;
        }else {
            return -1;
        }
    }

    public void strcpy(String aaa,String word){
        aaa = word;
    }

    public void alpha(){
            int i=1,flag;
            char ch;
            if(lookahead==null) return;
            ch= lookahead;
            //word[0]=ch;
            word+=ch;
            ch=getchar();
            while (isalpha(ch)||isdigit(ch)){
                word+=ch;
                ch= getchar();
            }
            //ungetc(ch,stdin);
            ungetc();
            flag=0;
            for(i=0;i<8;i++){
                if(strcmp(word,keywords.get(i))==0){
                    flag=1;
                }
            }
            if(flag==1){
                strcpy(keywordtable[keyword_num++],word);
                keywordList.add(word);
                strcpy(finaltable[final_num],word);
                finalList.add(word);
                if(word.equals("if"))
                    finaltableint[final_num++]=100;
                if(word.equals("for"))
                    finaltableint[final_num++]=200;
                if(word.equals("else"))
                    finaltableint[final_num++]=300;
                if(word.equals("while"))
                    finaltableint[final_num++]=400;
                if(word.equals("do"))
                    finaltableint[final_num++]=500;
                if(word.equals("float"))
                    finaltableint[final_num++]=600;
                if(word.equals("int"))
                    finaltableint[final_num++]=700;
                if(word.equals("break"))
                    finaltableint[final_num++]=800;
            } else{
                System.out.println("word:"+word);
                strcpy(idtable[id_num++],word);
                idList.add(word);
                strcpy(finaltable[final_num],"id");
                finalList.add("id");
                finaltableint[final_num++]=1;
            }
    }

    void digit(){
        int i=1,flag;
        Character ch;
        ch=lookahead;
        word+=ch;
        System.out.println("digit word:"+word);
        ch= getchar();
        if(ch==null){
            return;
        }
        while (isalpha(ch)||isdigit(ch)){
            word+=ch;
            ch= getchar();
        }
        ungetc();
        flag=0;
        System.out.println("word:"+word+" len:"+word.length());
        int l=word.length();
        for(i=0;i<l;i++){
            if(word.charAt(i)<'0'||word.charAt(i)>'9')
                flag=1;
        }
        if(flag==1){
            strcpy(idtable[id_num++],word);
            strcpy(finaltable[final_num],"id");
            finaltableint[final_num++]=1;
        } else{
            strcpy(digittable[digit_num++],word);
            strcpy(finaltable[final_num],"num");
            finaltableint[final_num++]=99;
        }
    }

    void note(){
        char ch;
        int i=0;
        ch= getchar();
        while (true){
            if(ch=='*'){
                ch= getchar();
                if(ch=='/')
                    break;
                else{
                    ungetc();
                    word+=ch;
                }
            } else{
                word+=ch;
            }
            ch= getchar();
        }
        strcpy(notetable[note_num++],word);
    }

    void otherchar(){
        char ch;
        ch=lookahead;
        switch (ch){
            case '!':
            {
                ch= getchar();
                if(ch=='='){
                    strcpy(otherchartable[otherchar_num++],"!=");
                    strcpy(finaltable[final_num],"!=");
                    finaltableint[final_num++]=3;
                } else{
                    ungetc();
                    error();
                }
            }
            break;
            case '=':
            {
                ch= getchar();
                if(ch=='='){
                    strcpy(otherchartable[otherchar_num++],"==");
                    strcpy(finaltable[final_num],"==");
                    finaltableint[final_num++]=4;
                } else{
                    strcpy(otherchartable[otherchar_num++],"=");
                    strcpy(finaltable[final_num],"=");
                    finaltableint[final_num++]=5;
                    ungetc();
                }

            }
            break;
            case '(':
                strcpy(otherchartable[otherchar_num++],"(");
                strcpy(finaltable[final_num],"(");
                finaltableint[final_num++]=6;
                break;
            case ')':
                strcpy(otherchartable[otherchar_num++],")");
                strcpy(finaltable[final_num],")");
                finaltableint[final_num++]=7;
                break;
            case ';':
                strcpy(otherchartable[otherchar_num++],";");
                strcpy(finaltable[final_num],";");
                finaltableint[final_num++]=8;
                break;
            case '{':
                strcpy(otherchartable[otherchar_num++],"{");
                strcpy(finaltable[final_num],"{");
                finaltableint[finalnum++]=9;
                break;
            case '}':
                strcpy(otherchartable[otherchar_num++],"}");
                strcpy(finaltable[final_num],"}");
                finaltableint[finalnum++]=10;
                break;
            case '|':
                ch = getchar();
                if(ch=='|') {
                    strcpy(otherchartable[otherchar_num++], "||");
                    strcpy(finaltable[final_num], "||");
                    finaltableint[finalnum++] = 11;
                }else {
                    System.out.println("不是||,出错");
                }
                break;
            case '&':
                ch= getchar();
                if(ch=='&') {
                            strcpy(otherchartable[otherchar_num++], "&&");
                            strcpy(finaltable[final_num], "&&");
                            finaltableint[finalnum++] = 12;
                }else {
                    System.out.println("不是&&,出错");
                }
                break;
            case '+':
                strcpy(otherchartable[otherchar_num++],"+");
                strcpy(finaltable[final_num],"+");
                finaltableint[finalnum++]=13;
                break;
            case '-':
                strcpy(otherchartable[otherchar_num++],"-");
                strcpy(finaltable[final_num],"-");
                finaltableint[finalnum++]=19;
            case '>':
            {
                ch= getchar();
                if(ch=='='){
                    strcpy(otherchartable[otherchar_num++],">=");
                    strcpy(finaltable[final_num],">=");
                    finaltableint[final_num++]=14;
                }  else{
                    strcpy(otherchartable[otherchar_num++],">=");
                    strcpy(finaltable[final_num],">");
                    finaltableint[final_num++]=15;
                    ungetc();
                }
            }
            break;
            case '<':{
                ch= getchar();
                if(ch=='='){
                    strcpy(otherchartable[otherchar_num++],"<=");
                    strcpy(finaltable[final_num],"<=");
                    finaltableint[final_num++]=16;
                } else{
                    strcpy(otherchartable[otherchar_num++],"<");
                    strcpy(finaltable[final_num],"<");
                    finaltableint[final_num++]=17;
                    ungetc();
                }
            }
            break;
            case '*':
                strcpy(finaltable[final_num],"*");
                finaltableint[final_num++]=18;
                break;
            default:
                error();
                break;

        }
    }

    void error(){
        flag_error=1;
        System.out.println("出现错误，终止分析!\n");
    }

    void print(){
        int i;
        //finaltableint[final_num]="\0";
        System.out.print("词法分析结果如下:\n");
        for (i = 0; i < final_num; ++i) {
            System.out.println(finaltable[i]);
        }
        System.out.println("\n语法分析过程如下：\n");
    }


    void program(){
        System.out.print("program--->block\n");
        block();
        if(flagerror==1){
            error();
            return;
        }
    }

    void block(){
        if(flagerror==1){
            return;
        }
        System.out.print("block-->{stmts}\n");
        match("{");
        stmts();
        match("}");
    }

    void stmts(){
        if(flagerror==1){
            return;
        }
        if(finaltableint[finalnum]==10){
            System.out.print("stmts -->null\n");
            return;
        }

        System.out.print("stmts-->stmt stmts\n");
        stmt();
        stmts();
    }

    void stmt(){
        if(flagerror==1){
            return;
        }
        switch (finaltableint[finalnum]){
            case 1:
                System.out.print("stmt--<id=expr;\n");
                match("id");
                match("=");
                expr();
                match(";");
                break;
            case 100:
                match("if");
                match("(");
                boole();
                match(")");
                stmt();
                if(strcmp(finaltable[finalnum],"else")==0){
                    System.out.print("stmt-->if(bool) stmt else stmt\n");
                    match("else");
                    stmt();
                    break;
                } else{
                    System.out.print("stmt-->{if (bool)stmt\n");
                    break;
                }
            case 400:
                System.out.print("stmt-->while (bool) stmt\n");
                match("while");
                match("(");
                boole();
                match(")");
                stmt();
                break;
            case 500:
                System.out.print("stmt-->do stmt while(bool)\n");
                match("do");
                stmt();
                match("while");
                match("(");
                boole();
                match(")");
                break;
            case 800:
                System.out.print("stmt -->break\n");
                match("break");
                boole();
            default:
                System.out.print("stmt--block\n");
                block();
                break;
        }
    }

    void boole(){
        if(flagerror==1){
            return;
        }
        expr();
        switch (finaltableint[finalnum]){
            case 17:
                System.out.print("bool -->expr<expr\n");
                match("<");
                expr();
                break;
            case 16:
                System.out.print("bool --> expr<=expr\n");
                match("<=");
                expr();
                break;
            case 15:
                System.out.print("bool-->expr>expr\n");
                match(">");
                expr();
                break;
            case 14:
                System.out.print("bool -->expr>=expr\n");
                expr();
                break;
            default:
                System.out.print("bool -->expr\n");
                expr();
                break;

        }

    }

    void expr(){
        if(flagerror==1){
            return;
        }
        System.out.print("expr-->term expr1\n");
        term();
        expr1();
    }

    void expr1(){
        if(flagerror==1){
            return;
        }
        switch (finaltableint[finalnum]){
            case 13:
                System.out.print("expr1-->+term expr1\n");
                match("+");
                term();
                expr1();
                break;
            case 19:
                System.out.print("expr1-->-term expr1\n");
                match("-");
                term();
                expr1();
                break;
            default:
                System.out.print("expr1-->null\n");
                return;
        }


    }

    void term(){
        if(flagerror==1){
            return;
        }
        System.out.print("term-->factor term1\n");
        factor();
        term1();
    }

    void term1(){
        if(flagerror==1){
            return;
        }
        switch (finaltableint[finalnum]){
            case 18:
                System.out.print("term-->* factor term1 \n");
                match("*");
                factor();
                term1();
                break;
            case 2:
                System.out.print("term1-->/factor term1\n");
                match("/");
                factor();
                term1();
                break;
            default:
                System.out.print("term -->null\n");
                return;
        }
    }

    void factor(){
        if(flagerror==1){
            return;
        }
        switch (finaltableint[finalnum]){
            case 6:
                System.out.print("factor-->(expr)\n");
                match("(");
                expr();
                match(")");
                break;
            case 1:
                System.out.print("factor-->id\n");
                match("id");
                break;
            case 99:
                System.out.print("factor -->num\n");
                match("num");
                break;
            default:
                flagerror=1;
                break;
        }
    }

    void match(String t){
        if(strcmp(finaltable[finalnum],t)==0){
            System.out.println("匹配:"+t+" 成功");
        }
        else{
            flagerror=1;
            System.out.println("匹配失败:"+t+" 失败");
            return;
        }
        finalnum++;
    }



    public void doCompile(){
        //System.out.println("编译开始");
        info = "编译开始....";
        appendInfo();
        sourcecode = textArea.getText();
        int len = sourcecode.length();
        info= sourcecode;
        appendInfo();

        while (true){
            lookahead=getchar();
            if(lookahead==null) {
                System.out.println("已经分析结束");
                break;
            }
            if(isalpha(lookahead)){
                alpha();
                initialize();
            } else if(isdigit(lookahead)){
                digit();
                initialize();
            } else if(lookahead=='\t'||lookahead==' '){
                continue;
            } else if(lookahead=='\n')
                break;
            else if(lookahead=='/') {
                lookahead = getchar();
                if (lookahead == '*') {
                    note();
                    initialize();
                } else {
                    ungetc();
                    strcpy(finaltable[final_num], "/");
                    strcpy(otherchartable[otherchar_num++], "/");
                    finaltableint[final_num++] = 2;
                    initialize();
                }
            } else{
                otherchar();
                initialize();
            }
        }
        if(flag_error==0){
            print();
            program();
            if(finalnum==final_num)
                System.out.print("语法分析完成!\n");

        }


    }

    public void appendInfo(){
        infoTextArea.append("\n"+info);
    }

    public Character getchar(){
        int len = sourcecode.length();
        if(index>=len-1){
            System.out.println("已经扫描到尾部,返回为空");
            return null;
        }
        char c = sourcecode.charAt(index);
        index++;
        return c;
    }

    public void ungetc(){
        index--;
    }

}
