package com.js.compile.littlec;
/**
 * 这个类具有求值功能,目前使用的主类是这个
 */

import com.js.compile.littlec.model.*;

import java.util.*;
import java.util.logging.Logger;

/**
 * Created by Administrator on 2017-10-12.
 */
public class LittleCCommandEnv {

    Logger log = Logger.getLogger("littlec lang");
    Map envMap = new HashMap();

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


    Map<String, Object> valueMap = new HashMap();


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


    String ifcodevalue="{\n" +
            "x=1;\n" +
            "y=3;\n" +
            "if(x==1){\n" +
            " y=2;\n" +
            "}\n" +
            "}";

    String whilecodevalue="{\n" +
            "x=1;\n" +
            "y=3;\n" +
            "while(x<3){\n" +
            " y=y+1;\n" +
            "x=x+1;\n"+
            "}\n" +
            "}";


    public LittleCCommandEnv(){
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
    }

    public static void main(String[] args) {

    	LittleCCommandEnv littleCCommand = new LittleCCommandEnv();
    	littleCCommand.doCompile();
        System.out.println("end");
       
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


    public void putValue(String idname,Object value){
        valueMap.put(idname,value);
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
                    finaltableint[indexIndetity]=KEYWORD.KEY_IF;
                    indexIndetity++;
                    finalTableIndetityList.add(KEYWORD.KEY_IF);
                }
                if(word.equals("for")) {
                    finaltableint[indexIndetity] = KEYWORD.KEY_FOR;
                    indexIndetity++;
                    finalTableIndetityList.add(KEYWORD.KEY_FOR);
                }
                if(word.equals("else")) {
                    finaltableint[indexIndetity] = KEYWORD.KEY_ELSE;
                    indexIndetity++;
                    finalTableIndetityList.add(KEYWORD.KEY_ELSE);
                }
                if(word.equals("while")) {
                    finaltableint[indexIndetity] = KEYWORD.KEY_WHILE;
                    indexIndetity++;
                    finalTableIndetityList.add(KEYWORD.KEY_WHILE);
                }
                if(word.equals("do")) {
                    finaltableint[indexIndetity] = KEYWORD.KEY_DO;
                    indexIndetity++;
                    finalTableIndetityList.add(KEYWORD.KEY_DO);
                }
                if(word.equals("float")){
                    finaltableint[indexIndetity]=KEYWORD.KEY_FLOAT;
                    indexIndetity++;
                    finalTableIndetityList.add(KEYWORD.KEY_FLOAT);
                }
                if(word.equals("int")) {
                    //finaltableint[final_num++] = KEYWORD.KEY_INT;
                    finaltableint[indexIndetity]=KEYWORD.KEY_INT;
                    indexIndetity++;
                    finalTableIndetityList.add(KEYWORD.KEY_INT);
                }
                if(word.equals("break")){
                    finaltableint[indexIndetity]=KEYWORD.KEY_BREAK;
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
                finaltableint[indexIndetity]=KEYWORD.KEY_ID;
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
                //下面的一个
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
                finaltableint[indexIndetity]=KEYWORD.KEY_LEFT_PARREN;
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
                finaltableint[indexIndetity]=KEYWORD.KEY_RIGHT_PARREN;
                indexIndetity++;
                break;
            case ';':
//                strcpy(otherchartable[otherchar_num++],";");
                othercharList.add(";");
//                strcpy(finaltable[final_num],";");
                finalList.add(";");
                wordList.add(";");
                finalTableIndetityList.add(KEYWORD.KEY_SIMICOLON);
                finaltableint[indexIndetity]=KEYWORD.KEY_SIMICOLON;
                indexIndetity++;
                break;
            case '{':
//                strcpy(otherchartable[otherchar_num++],"{");
                othercharList.add("{");
//                strcpy(finaltable[final_num],"{");
                finalList.add("{");
                wordList.add("{");
                finalTableIndetityList.add(KEYWORD.KEY_LEFT_BRACKET);
                finaltableint[indexIndetity]=KEYWORD.KEY_LEFT_BRACKET;
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
                finaltableint[indexIndetity]=KEYWORD.KEY_RIGHT_BRACKET;
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
                    finaltableint[indexIndetity] = KEYWORD.KEY_OR;
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
                            finaltableint[indexIndetity] = KEYWORD.KEY_AND;
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
                finaltableint[indexIndetity]=KEYWORD.KEY_ADD;
                indexIndetity++;
                break;
            case '-':
                //strcpy(otherchartable[otherchar_num++],"-");
                othercharList.add("-");
                //strcpy(finaltable[final_num],"-");
                finalList.add("-");
                wordList.add("-");
                finalTableIndetityList.add(KEYWORD.KEY_SUB);
                finaltableint[indexIndetity++]=KEYWORD.KEY_SUB;
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
                    finaltableint[indexIndetity]=KEYWORD.KEY_GTEQ;
                    indexIndetity++;
                }  else{
//                    strcpy(otherchartable[otherchar_num++],">=");
                    othercharList.add(">=");
//                    strcpy(finaltable[final_num],">");
                    finalList.add(">");
                    wordList.add(">");
                    finalTableIndetityList.add(KEYWORD.KEY_GT);
                    finaltableint[indexIndetity]=KEYWORD.KEY_GT;
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
                    finaltableint[indexIndetity]=KEYWORD.KEY_LTEQ;
                    indexIndetity++;
                } else{
//                    strcpy(otherchartable[otherchar_num++],"<");
                    othercharList.add("<");
//                    strcpy(finaltable[final_num],"<");
                    finalList.add("<");
                    wordList.add("<");
                    finalTableIndetityList.add(KEYWORD.KEY_LT);
                    finaltableint[indexIndetity]=KEYWORD.KEY_LT;
                    indexIndetity++;
                    ungetc();
                }
            }
            break;
            case '*':
//                strcpy(finaltable[final_num],"*");
                finalList.add("*");
                wordList.add("*");
                finaltableint[indexIndetity]=KEYWORD.KEY_MUL;
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


    AstProgram program(){
        System.out.print("program--->block\n");
        System.out.println("begin parse programe block\n");

        AstProgram program = new AstProgram();

        AstBlock astBlock = block();
        program.addChild(astBlock);
        if(flagerror==1){
            error();
            return null;
        }
        return program;
    }

    AstBlock block(){
        AstBlock astBlock = new AstBlock();
        if(flagerror==1){
            return null;
        }
        System.out.print("block-->{stmts}\n");
        boolean ismatch=match("{");
        if(!ismatch) {
            System.out.println("未能匹配{ ，出错");
        }
        AstStmts astStmts =stmts();
        astBlock.addChild(astStmts);
        ismatch = match("}");
        if(!ismatch){
            System.out.println("未能匹配},出错");
        }
        return astBlock;
    }

    AstStmts stmts(){
        AstStmts astStmts = new AstStmts();

        if(flagerror==1){
            return null;
        }
        if(finaltableint[finalnum]==10){
            System.out.print("stmts -->null\n");
            return null;
        }

        System.out.print("stmts-->stmt stmts\n");
        //都存在
        AstStmt astStmt =stmt();
        astStmts.addChild(astStmt);

        AstStmts astStmtsNext=stmts();
        astStmts.addChild(astStmtsNext);
        return astStmts;
    }

    AstStmt stmt(){
        AstStmt astStmt = new AstStmt();
        astStmt.setStmtType(AstStmt.STMT_BLOCK);
        if(flagerror==1){
            return null;
        }
        //相当于提前读取一个值,但不改变index指针
        String lastword = getIDTokenByIndex(indexword);

        System.out.println("当前stmt类型标志:"+finaltableint[finalnum]);

        switch (finaltableint[finalnum])
        //switch (lastword)
        {
        	//赋值语句
            case 1:
                astStmt = new AstStmt();
                astStmt.setStmtType(AstStmt.STMT_EQUAL);
                System.out.print("stmt--<id=expr;\n");
                match("id");
                int indexname= indexword-1;
                String idname = wordList.get(indexname);
                AstLiteral astLiteral = new AstLiteral(idname);
                astStmt.addChild(astLiteral);
                putValue(idname,null);
                match("=");
                AstOp astOp = new AstOp("=");
                astStmt.addChild(astOp);
                AstExpr astExpr = expr();
                astStmt.addChild(astExpr);
                match(";");
                return astStmt;
                //break;
            //条件语句
            case 100:
                System.out.print("stmt--<if stmt;\n");
                match("if");
                AstIfStmt astIfStmt = new AstIfStmt();
                astIfStmt.setStmtType(AstStmt.STMT_IF);
                AstLiteral astLiteral2= new AstLiteral("if");
                astIfStmt.addChild(astLiteral2);
                match("(");
                AstBool astBool=boole();
                astIfStmt.addChild(astBool);
                match(")");
                AstStmt astStmt1=stmt();
                astIfStmt.addChild(astStmt1);
//                if(strcmp(finaltable[finalnum],"else")==0){
                if(finalList.get(indexword).equals("else")){
                    System.out.print("stmt-->if(bool) stmt else stmt\n");
                    AstLiteral astLiteral1= new AstLiteral("else");
                    match("else");
                    astIfStmt.addChild(astLiteral1);
                    AstStmt astStmt2=stmt();
                    astIfStmt.addChild(astStmt2);
                    return astIfStmt;
                    //break;
                } else{
                    System.out.print("stmt-->{if (bool)stmt\n");
                    return astIfStmt;
                    //break;
                }
            //while语句
            case 400:
                System.out.print("stmt-->while (bool) stmt\n");
                match("while");
                AstWhileStmt astWhileStmt = new AstWhileStmt();
                astWhileStmt.setStmtType(AstStmt.STMT_WHILE);
                AstLiteral astLiteral1 = new AstLiteral("while");
                astWhileStmt.addChild(astLiteral1);

                match("(");
                AstBool astBool1=boole();
                astWhileStmt.addChild(astBool1);
                match(")");
                AstStmt astStmt2=stmt();
                astWhileStmt.addChild(astStmt2);
                return astWhileStmt;
                //break;
            //do语句
            case 500:
                System.out.print("stmt-->do stmt while(bool)\n");
                match("do");
                stmt();
                match("while");
                match("(");
                boole();
                match(")");
                break;
            //break语句
            case 800:
                System.out.print("stmt -->break\n");
                match("break");
                boole();
            //block
            default:
                AstStmt astStmt3= new AstStmt();
            	System.out.println("好像没有进入这一节来");
                System.out.print("stmt--block\n");
                AstBlock astBlock=block();
                astStmt3.setStmtType(AstStmt.STMT_BLOCK);
                astStmt3.addChild(astBlock);
                return astStmt3;
                //break;
        }
        return astStmt;
    }

    public AstBool boole(){
        AstBool astBool = new AstBool();
        if(flagerror==1){
            return null;
        }
        System.out.println("匹配bool语句---");
        AstExpr astExpr=expr();
        astBool.addChild(astExpr);
        System.out.println("注意到:< <=这些判断语句不属于 expr");
        String op;
        AstOp astOp;
        switch (finaltableint[finalnum]){
            case 17:
                System.out.print("bool -->expr<expr\n");
                match("<");
                op = getLastToken();
                astOp = new AstOp(op);
                astBool.addChild(astOp);
                AstExpr astExpr1 =expr();
                astBool.addChild(astExpr1);
                break;
            case 16:
                System.out.print("bool --> expr<=expr\n");
                match("<=");
                op = getLastToken();
                astOp = new AstOp(op);
                astBool.addChild(astOp);
                AstExpr astExpr2=expr();
                astBool.addChild(astExpr2);
                break;
            case 15:
                System.out.print("bool-->expr>expr\n");
                match(">");
                op = getLastToken();
                astOp = new AstOp(op);
                astBool.addChild(astOp);
                AstExpr astExpr3 =expr();
                astBool.addChild(astExpr3);
                break;
            case 14:
                System.out.print("bool -->expr>=expr\n");
                match(">=");
                op = getLastToken();
                astOp = new AstOp(op);
                astBool.addChild(astOp);
                AstExpr astExpr4 =expr();
                astBool.addChild(astExpr4);
                break;
            case 3:
            	System.out.print("bool -->expr!=expr\n");
            	match("!=");
                op = getLastToken();
                astOp = new AstOp(op);
                astBool.addChild(astOp);
                AstExpr astExpr5 =expr();
                astBool.addChild(astExpr5);
            	break;
            case 4:
            	System.out.print("bool -->expr==expr\n");
            	match("==");
                op = getLastToken();
                astOp = new AstOp(op);
                astBool.addChild(astOp);
                AstExpr astExpr6 =expr();
                astBool.addChild(astExpr6);
            	break;
            default:
                System.out.print("bool -->expr\n");
                AstExpr astExpr7=expr();
                astBool.addChild(astExpr7);
                break;
        }
        return  astBool;
    }

    AstExpr expr(){
        AstExpr astExpr = new AstExpr();
        if(flagerror==1){
            return null;
        }
        System.out.print("expr-->term expr1\n");
        AstTerm astTerm=term();
        astExpr.addChild(astTerm);
        AstExpr1 astExpr1=expr1();
        astExpr.addChild(astExpr1);
        return astExpr;
    }

    AstExpr1 expr1(){
        AstExpr1 astExpr1 = new AstExpr1();
        if(flagerror==1){
            return null;
        }
        switch (finaltableint[finalnum]){
            case 13:
                System.out.print("expr1-->+term expr1\n");
                match("+");
                AstOp astOp = new AstOp("+");
                AstTerm astTerm=term();
                astExpr1.addChild(astOp);
                astExpr1.addChild(astTerm);
                AstExpr1 astExpr1Next=expr1();
                astExpr1.addChild(astExpr1Next);
                break;
            case 19:
                System.out.print("expr1-->-term expr1\n");
                match("-");
                AstOp astOp2 = new AstOp("-");
                AstTerm astTerm1 =term();
                astExpr1.addChild(astOp2);
                astExpr1.addChild(astTerm1);
                AstExpr1 astExpr1Next1=expr1();
                astExpr1.addChild(astExpr1Next1);
                break;
            default:
                System.out.print("expr1-->null\n");
                return null;
        }
        return astExpr1;
    }

    AstTerm term(){
        AstTerm astTerm = new AstTerm();
        if(flagerror==1){
            return null;
        }
        System.out.print("term-->factor term1\n");
        AstFactor astFactor=factor();
        astTerm.addChild(astFactor);
        AstTerm1 astTerm1 =term1();
        astTerm.addChild(astTerm1);
        return astTerm;
    }

    AstTerm1 term1(){
        AstTerm1 astTerm1 = new AstTerm1();
        if(flagerror==1){
            return null;
        }
        switch (finaltableint[finalnum]){
            case 18:
                System.out.print("term-->* factor term1 \n");
                match("*");
                AstFactor astFactor=factor();
                astTerm1.addChild(astFactor);
                AstTerm1 astTerm1Next=term1();
                astTerm1.addChild(astTerm1Next);
                break;
            case 2:
                System.out.print("term1-->/factor term1\n");
                match("/");
                AstFactor astFactorNext=factor();
                astTerm1.addChild(astFactorNext);
                AstTerm1 astTerm1Next2=term1();
                astTerm1.addChild(astTerm1Next2);
                break;
            default:
                System.out.print("term -->null\n");
                return null;
        }
        return astTerm1;
    }

    AstFactor factor(){
        AstFactor astFactor = new AstFactor();
        if(flagerror==1){
            return null;
        }
        switch (finaltableint[finalnum]){
            case 6:
                System.out.print("factor-->(expr)\n");
                astFactor.setFactorType(AstFactor.FACTOR_EXPR);
                match("(");
                AstExpr astExpr=expr();
                match(")");
                astFactor.addChild(astExpr);
                break;
            case 1:
                System.out.print("factor-->id\n");
                astFactor.setFactorType(AstFactor.FACTOR_ID);
                match("id");
                String literal = getLastToken();
                AstLiteral astLiteral= new AstLiteral(literal);
                astFactor.addChild(astLiteral);
                break;
            case 99:
                System.out.print("factor -->num\n");
                match("num");
                String nums = getLastToken();
                astFactor.setFactorType(AstFactor.FACTOR_NUM);
                AstNum astNum = new AstNum(nums);
                astFactor.addChild(astNum);
                break;
            default:
                flagerror=1;
                break;
        }
        return astFactor;
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
        finalnum++;
        //indexIndetity++;
        return true;
        //index++;
    }

    /***
     * 词法分析过程
     */
    public void lex(){

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
        System.out.println("解析的词 finalList:");
        for(String s:finalList){
            System.out.print(" "+s+" ");
        }
        System.out.println("\n 解析的原文:");
        System.out.println("解析的词 wordList:");
        for(String w:wordList){
            System.out.print(" "+w+" ");
        }
        System.out.println("\n");
    }


    public void doCompile(){
        //System.out.println("编译开始");
        sourcecode =whilecodevalue;
        info = "编译开始....";
        appendInfo();
        //词法分析
        lex();
        //语法分析
        //语法分析开始
        //先将指标都清为零
        clear();

        AstProgram astProgram=null;
        if(flag_error==0){
            print();
            astProgram=program();
            if(finalnum==final_num)
                System.out.print("语法分析完成!\n");
        }

        //打印执行后的结果
        if(astProgram!=null) {
            astProgram.eval();
            System.out.println("执行成功");
            printExecuteValues();
        }else {
            System.out.println("未执行");
        }
    }

    /**
     * 打印执行之后的结果,即打印valueMap的键和值
     */
    public void printExecuteValues(){
        System.out.println("打印执行结果");
        System.out.println("result size:"+valueMap.size());
        printMap(valueMap);

        System.out.println("打印全局变量...");
        Map<String,Object>  map = Env.envMap;
        System.out.println("全局变量大小:"+map.size());
        printMap(map);
    }

    public void printMap(Map<String,Object> map){
        Set<String> keys = map.keySet();
        for(String key:keys){
            System.out.println("key:"+key);
            Object o = map.get(key);
            System.out.println(o);
        }
    }

    public void appendInfo(){
        System.out.println("\n"+info);
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



    /**
     * 这个不移动指针，直接读取上个token
     * @return
     */
    public String getLastToken(){
        int lastIndex = indexword-1;
        String lastword = wordList.get(lastIndex);
        System.out.println("last word:"+lastword);
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
