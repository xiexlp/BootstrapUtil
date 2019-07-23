package com.js.compile.littlec;

import com.js.builders.TreeBuilder;
import com.js.compile.littlec.model.KEYWORD;

import javax.swing.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Administrator on 2017-10-12.
 */
public class LittleNewFrame extends JFrame{

    List<String> keywords = new ArrayList<>();
    Set<String> keywordSet = new HashSet<>();
    List<String> keywordList=new ArrayList<>();
    List<String> rekeywordList=new ArrayList<>();
    List<String> digitList=new ArrayList<>();
    List<String> redigitList= new ArrayList<>();
    List<String>  idList= new ArrayList<>();
    List<String> reidList=new ArrayList<>();
    List<String> noteList=new ArrayList<>();
    List<String> finalList= new ArrayList<>();
    List<String> wordList = new ArrayList<>();

    List<Integer> finalTypeList = new ArrayList();

    List<String> othercharList = new ArrayList<>();

//    String[] keywordtable=new String[500];
//    String[] finaltable=new String[500];
    Integer[] finaltableint=new Integer[500];
    List<Integer> finalTableIndetityList = new ArrayList<>();
//    String[] idtable=new String[500];
//    String[] digittable=new String[500];
//    String[] notetable=new String[500];
//    String[] otherchartable=new String[500];
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

    int linenum=0;
    int indexword=0;

    int indexIndetity = 0;
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
    JMenuItem exitMenuItem;

    JToolBar jToolBar;
    JButton openButton;
    JButton savaButton;
    JButton clearTextButton;
    JButton clearInfoButton;
    JButton clearAllButton;
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


    public LittleNewFrame(){
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

        keywordSet.add("if");
        keywordSet.add("for");
        keywordSet.add("else");
        keywordSet.add("while");
        keywordSet.add("do");
        keywordSet.add("float");
        keywordSet.add("int");
        keywordSet.add("break");

        //swing init...
        jMenuBar = new JMenuBar();
        fileMenu = new JMenu("文件");
        editMenu = new JMenu("编辑");
        fileOpenMenuItem = new JMenuItem("打开文件...");
        fileSaveMenuItem = new JMenuItem("保存文件...");
        fileSaveAsMenuItem = new JMenuItem("另保为...");
        exitMenuItem = new JMenuItem("退出..");

        fileMenu.add(fileOpenMenuItem);
        fileMenu.add(fileSaveMenuItem);
        fileMenu.add(fileSaveAsMenuItem);
        fileMenu.add(exitMenuItem);

        exitMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(-1);
            }
        });

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
        clearAllButton = new JButton("全部清除");

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

        clearAllButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textArea.setText("");
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
        jToolBar.add(clearAllButton);


        mainSplitPane = new JSplitPane();
        leftTree = TreeBuilder.GetATree();



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


        leftTree.addTreeSelectionListener(new TreeSelectionListener()
        {
            @Override
            public void valueChanged(TreeSelectionEvent e)
            {
                DefaultMutableTreeNode node = (DefaultMutableTreeNode) leftTree.getLastSelectedPathComponent();
                if (node == null){
                    return;
                }//if

                //Object object = node.getUserObject();
                //TreeNode user = (TreeNode) object;
                System.out.println("你选择了：" + node.toString());
                String select = node.toString();
                if(select.equals("赋值语句")){
                    info ="赋值语句";
                    appendInfo();
                    String codevalue= "{\n" +
                            "x=1;\n" +
                            "y=2;\n" +
                            "}";
                    textArea.setText(codevalue);
                }else if(select.equals("条件语句")){
                    info ="条件语句";
                    appendInfo();

                    String codevalue="{\n" +
                            "x=0;\n" +
                            "y=0;\n" +
                            "if(x==0){\n" +
                            " y=1;\n" +
                            "}\n" +
                            "}";
                    textArea.setText(codevalue);

                }else  if(select.equals("循环语句")){
                    info ="循环语句";
                    appendInfo();
                }
            }
        });


    }

    public static void main(String[] args) {

        EventQueue.invokeLater(new Runnable() {
            public void run() {
                LittleNewFrame auto = new LittleNewFrame();
                Dimension dimension = new Dimension(1000, 800);

                //Dimension dimension = ScreenUtils.screenDimension();

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
            //如果是字母还是数字的话，继续读取
            while (isalpha(ch)||isdigit(ch)){
                word+=ch;
                ch= getchar();
            }
            //ungetc(ch,stdin);
            ungetc();
            flag=0;
            //如果是关键字
            if(keywordSet.contains(word)){
                flag=1;
            }
            if(flag==1){
                keywordList.add(word);
                finalList.add(word);
                wordList.add(word);
                if(word.equals("if")){
                    finaltableint[indexIndetity]=100;
                    indexIndetity++;
                    finalTableIndetityList.add(KEYWORD.KEY_IF);
                }
                if(word.equals("for")) {
                    finaltableint[indexIndetity] = 200;
                    indexIndetity++;
                    finalTableIndetityList.add(KEYWORD.KEY_FOR);
                }
                if(word.equals("else")) {
                    finaltableint[indexIndetity] = 300;
                    indexIndetity++;
                    finalTableIndetityList.add(KEYWORD.KEY_ELSE);
                }
                if(word.equals("while")) {
                    finaltableint[indexIndetity] = 400;
                    indexIndetity++;
                    finalTableIndetityList.add(KEYWORD.KEY_WHILE);
                }
                if(word.equals("do")) {
                    finaltableint[indexIndetity] = 500;
                    indexIndetity++;
                    finalTableIndetityList.add(KEYWORD.KEY_DO);
                }
                if(word.equals("float")){
                    finaltableint[indexIndetity]=600;
                    indexIndetity++;
                    finalTableIndetityList.add(KEYWORD.KEY_FLOAT);
                }
                if(word.equals("int")) {
                    finaltableint[final_num++] = 700;
                    finalTableIndetityList.add(KEYWORD.KEY_INT);
                }
                if(word.equals("break")){
                    finaltableint[indexIndetity]=800;
                    indexIndetity++;
                    finalTableIndetityList.add(KEYWORD.KEY_BREAK);
                }
            } else{
                System.out.println("word:"+word);
                //strcpy(idtable[id_num++],word);
                //idList.add(word);
                idList.add(word);
                //strcpy(finaltable[final_num],"id");
                finalList.add("id");
                finalTableIndetityList.add(KEYWORD.KEY_ID);
                wordList.add(word);
                finaltableint[indexIndetity]=1;
                indexIndetity++;
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
            //说明不是数字
            if(word.charAt(i)<'0'||word.charAt(i)>'9')
                flag=1;
        }
        if(flag==1){
           // strcpy(idtable[id_num++],word);
            idList.add(word);
            //strcpy(finaltable[final_num],"id");
            finalList.add("id");
            wordList.add(word);
            finaltableint[indexIndetity]=1;
            indexIndetity++;
            finalTableIndetityList.add(KEYWORD.KEY_ID);
            final_num++;
        } else{
            //数字
//            strcpy(digittable[digit_num++],word);
            digitList.add(word);
//            strcpy(finaltable[final_num],"num");
            finalList.add("num");
            wordList.add(word);
            finaltableint[indexIndetity]=99;
            indexIndetity++;
            finalTableIndetityList.add(KEYWORD.KEY_NUM);
            //final_num++;
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
//        strcpy(notetable[note_num++],word);
        noteList.add(word);
        final_num++;
    }

    /**
     * 属于词法分析
     */
    void otherchar(){
        char ch;
        ch=lookahead;
        switch (ch){
            case '!':
            {
                ch= getchar();
                if(ch=='='){
//                    strcpy(otherchartable[otherchar_num++],"!=");
                    othercharList.add("!=");
//                    String finalword = finalList.get(final_num);
                    //finalList.set(final_num,"!=");
//                    strcpy(finaltable[final_num],"!=");
                    finalList.add("!=");
                    wordList.add("!=");
                    finalTableIndetityList.add(KEYWORD.KEY_NOTEQ);
                    finaltableint[indexIndetity]=3;
                    indexIndetity++;
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
//                    strcpy(otherchartable[otherchar_num++],"==");
                    othercharList.add("==");
//                    strcpy(finaltable[final_num],"==");
                    finalList.add("==");
                    wordList.add("==");
                    finalTableIndetityList.add(KEYWORD.KEY_EQEQ);
                    finaltableint[indexIndetity]=4;
                    indexIndetity++;
                } else{
//                    strcpy(otherchartable[otherchar_num++],"=");
                    othercharList.add("=");
//                    strcpy(finaltable[final_num],"=");
                    finalList.add("=");
                    wordList.add("=");
                    finalTableIndetityList.add(KEYWORD.KEY_EQ);
                    finaltableint[indexIndetity]=5;
                    //finalnum++;
                    indexIndetity++;
                    ungetc();
                }

            }
            break;
            case '(':
                //strcpy(otherchartable[otherchar_num++],"(");
                othercharList.add("(");
                finalList.add("(");
                wordList.add("(");

                //strcpy(finaltable[final_num],"(");
                //othercharList.add("(");
                finalTableIndetityList.add(KEYWORD.KEY_LEFT_PARREN);
                finaltableint[indexIndetity]=6;
                indexIndetity++;
                break;
            case ')':
//                strcpy(otherchartable[otherchar_num++],")");
                System.out.println("遇到)");
                othercharList.add(")");
//                strcpy(finaltable[final_num],")");
                finalList.add(")");
                wordList.add(")");
                finalTableIndetityList.add(KEYWORD.KEY_RIGHT_PARREN);
                finaltableint[indexIndetity]=7;
                indexIndetity++;
                break;
            case ';':
//                strcpy(otherchartable[otherchar_num++],";");
                othercharList.add(";");
//                strcpy(finaltable[final_num],";");
                finalList.add(";");
                wordList.add(";");
                finalTableIndetityList.add(KEYWORD.KEY_SIMICOLON);
                finaltableint[indexIndetity]=8;
                indexIndetity++;
                break;
            case '{':
//                strcpy(otherchartable[otherchar_num++],"{");
                othercharList.add("{");
//                strcpy(finaltable[final_num],"{");
                finalList.add("{");
                wordList.add("{");
                finalTableIndetityList.add(KEYWORD.KEY_LEFT_BRACKET);
                finaltableint[indexIndetity]=9;
                indexIndetity++;
                break;
            case '}':
//                strcpy(otherchartable[otherchar_num++],"}");
                System.out.println("位置上 "+index+"发现到了第 "+finalnum+" 遇到 }");
                finalList.add("}");
                wordList.add("}");
                //othercharList.add("}");
//                strcpy(finaltable[final_num],"}");
                othercharList.add("}");
                finaltableint[indexIndetity]=10;
                finalTableIndetityList.add(KEYWORD.KEY_RIGHT_BRACKET);
                indexIndetity++;
                break;
            case '|':
                ch = getchar();
                if(ch=='|') {
//                    strcpy(otherchartable[otherchar_num++], "||");
                    othercharList.add("||");
//                    strcpy(finaltable[final_num], "||");
                    finalList.add("||");
                    wordList.add("||");
                    finaltableint[indexIndetity] = 11;
                    indexIndetity++;
                    finalTableIndetityList.add(KEYWORD.KEY_OR);

                }else {
                    System.out.println("不是||,出错");
                }
                break;
            case '&':
                ch= getchar();
                if(ch=='&') {
//                            strcpy(otherchartable[otherchar_num++], "&&");
                            othercharList.add("&&");
                            //strcpy(finaltable[final_num], "&&");
                            finalList.add("&&");
                            wordList.add("&&");
                            finalTableIndetityList.add(KEYWORD.KEY_AND);
                            finaltableint[indexIndetity] = 12;
                            indexIndetity++;
                }else {
                    System.out.println("不是&&,出错");
                }
                break;
            case '+':
                //strcpy(otherchartable[otherchar_num++],"+");
                othercharList.add("+");
                //strcpy(finaltable[final_num],"+");
                finalList.add("+");
                wordList.add("+");
                finalTableIndetityList.add(KEYWORD.KEY_ADD);
                finaltableint[indexIndetity]=13;
                indexIndetity++;
                break;
            case '-':
                //strcpy(otherchartable[otherchar_num++],"-");
                othercharList.add("-");
                //strcpy(finaltable[final_num],"-");
                finalList.add("-");
                wordList.add("-");
                finalTableIndetityList.add(KEYWORD.KEY_SUB);
                finaltableint[indexIndetity++]=19;
                indexIndetity++;
                final_num++;
            case '>':
            {
                ch= getchar();
                if(ch=='='){
//                    strcpy(otherchartable[otherchar_num++],">=");
                    othercharList.add(">=");
//                    strcpy(finaltable[final_num],">=");
                    finalList.add(">=");
                    wordList.add(">=");
                    finalTableIndetityList.add(KEYWORD.KEY_GTEQ);
                    finaltableint[indexIndetity]=14;
                    indexIndetity++;
                }  else{
//                    strcpy(otherchartable[otherchar_num++],">=");
                    othercharList.add(">=");
//                    strcpy(finaltable[final_num],">");
                    finalList.add(">");
                    wordList.add(">");
                    finalTableIndetityList.add(KEYWORD.KEY_GT);
                    finaltableint[indexIndetity]=15;
                    indexIndetity++;
                    ungetc();
                }
            }
            break;
            case '<':{
                ch= getchar();
                if(ch=='='){
//                    strcpy(otherchartable[otherchar_num++],"<=");
                    othercharList.add("<=");
//                    strcpy(finaltable[final_num],"<=");
                    finalList.add("<=");
                    wordList.add("<=");
                    finalTableIndetityList.add(KEYWORD.KEY_LTEQ);
                    finaltableint[indexIndetity]=16;
                    indexIndetity++;
                } else{
//                    strcpy(otherchartable[otherchar_num++],"<");
                    othercharList.add("<");
//                    strcpy(finaltable[final_num],"<");
                    finalList.add("<");
                    wordList.add("<");
                    finalTableIndetityList.add(KEYWORD.KEY_LT);
                    finaltableint[indexIndetity]=17;
                    indexIndetity++;
                    ungetc();
                }
            }
            break;
            case '*':
//                strcpy(finaltable[final_num],"*");
                finalList.add("*");
                wordList.add("*");
                finaltableint[indexIndetity]=18;
                finalTableIndetityList.add(KEYWORD.KEY_MUL);
                indexIndetity++;
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
        final_num = finalList.size();
        System.out.print("词法分析结果如下:\n");
        for (i = 0; i < final_num; ++i) {
//            System.out.println(finaltable[i]);
            System.out.println("终结符 "+i+":"+finalList.get(i));
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
        boolean ismatch=match("{");
        if(!ismatch) {
            System.out.println("未能匹配{ ，出错");
        }
        stmts();
        ismatch = match("}");
        if(!ismatch){
            System.out.println("未能匹配},出错");
        }
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
        String lastword = getIDToken();
        switch (finaltableint[finalnum])
        //switch (lastword)
        {
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
//                if(strcmp(finaltable[finalnum],"else")==0){
                if(finalList.get(final_num).equals("else")){
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

    private void clear(){
        finalnum =0;
        final_num=0;
        indexIndetity=0;
        indexword=0;
        index=0;
    }

    private boolean match(String t){
        System.out.println("当前终结点索引:"+indexword);
        //String finalword=getToken();
        //匹配之后移动指针
        String finalword = getIDToken();
        //这个不一定指针
        //String finalword = getIDTokenByIndex(indexword);
        System.out.println("当前取出来的终结符:"+finalword);
        if(finalword.equals(t)){
            System.out.println("匹配:"+t+" 成功");
        }
        else{
            flagerror=1;
            System.out.println("匹配失败:"+t+" 失败");
            return false;
        }
        //finalnum++;
        //indexIndetity++;
        return true;
        //index++;
    }

    /***
     * 词法分析过程
     */
    public void lex(){
        sourcecode = textArea.getText();
        int len = sourcecode.length();
        System.out.println("源文总长度:"+len);
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
                continue;
            else if(lookahead=='/') {
                lookahead = getchar();
                //多行注释
                if (lookahead == '*') {
                    note();
                    initialize();
                }//单行注释
                else {
                    ungetc();
//                    strcpy(finaltable[final_num], "/");
                    finalList.add("/");
//                    finalList.add("/");
//                    strcpy(otherchartable[otherchar_num++], "/");
                    othercharList.add("/");
                    finaltableint[final_num++] = 2;
                    initialize();
                }
            } else{
                otherchar();
                initialize();
            }
        }
        lexResult();
    }


    public void lexResult(){
        //词法分析结束
        int size = finalList.size();
        System.out.println("终结符个数 size:"+size);
        System.out.println("解析的词:");
        for(String s:finalList){
            System.out.print(" "+s+" ");
        }
        System.out.println("\n 解析的原文:");
        for(String w:wordList){
            System.out.print(" "+w+" ");
        }
        System.out.println("\n");
    }


    public void doCompile(){
        //System.out.println("编译开始");
        info = "编译开始....";
        appendInfo();
        //词法分析
        lex();
        //语法分析
        //语法分析开始
        //先将指标都清为零
        clear();
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


    public String getIDToken(){
        String lastword = finalList.get(indexword);
        indexword++;
        return lastword;
    }


    public String getToken(){
        String lastword = wordList.get(indexword);
        indexword++;
        return lastword;
    }

    public void ungetToken(){
        indexword--;
    }

    public String getTokenByIndex(int indx){
        String lastword = wordList.get(indx);
        return lastword;
    }

    public String getIDTokenByIndex(int indx){
        String lastword = finalList.get(indx);
        return lastword;
    }

    /**
     * 如果是空格就跳过去，继续读，不计入终结符
     * @return
     */
    public Character getchar(){
        int len = sourcecode.length();
        if(index>=len){
            System.out.println("已经扫描到尾部,返回为空");
            return null;
        }
        char c = sourcecode.charAt(index);

//        if(c=='\t'||c==' '){
//            index++;
//            return getchar();
//            //index++;
//        }else if(c=='\n'){
//            linenum++;
//            index++;
//            return getchar();
//        }else {
//            index++;
//            return c;
//        }


        index++;
        return c;
    }

    public void ungetc(){
        index--;
    }

}
