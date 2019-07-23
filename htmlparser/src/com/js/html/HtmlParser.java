package com.js.html;

import com.js.html.model.HtmlTag;
import com.js.html.model.ListUtils;

import java.util.*;

/**
 * Created by Administrator on 2017-10-28.
 */
public class HtmlParser {

    String sourcecode ="";
    int sourcelen=0;
    int indexword=0;
    int index=0;
    Character lookahead=null;

    int finalnum =0;
    int final_num=0;
    int indexIndetity=0;

    int flag_error=0;

    //字符串
    List<String> wordList = new ArrayList<>();
    List<String> finalList = new ArrayList<>();
    List<String> othercharList = new ArrayList<>();
    List<String> keywordList=new ArrayList<>();

    List<Integer> finalTableIndetityList = new ArrayList<>();

    Set<String> otherwordSet = new HashSet();
    Stack<HtmlTag> htmlTagStack = new Stack<>();
    int state=0;

    String word="";

    String info="";
    int listSize=0;

    Set<String> keywordSet = new HashSet<>();

    Integer[] finaltableint=new Integer[500];

    String htmlcode="\t\n" +
            "<div class=\"panel panel-default\">\n" +
            "        <div class=\"panel-body\">\n" +
            "            管理 软件\n" +
            "        </div>\n" +
            "    </div>\n";

    String htmlslashcode="\t\n" +
            "<div class=\"panel panel-default\">\n" +
            "        <div class=\"panel-body\">\n" +
            "            管理软件 <br/>\n" +
            "        </div>\n" +
            "    </div>\n";


    public HtmlParser(){
        init();
    }

    public static void main(String[] args) {
        HtmlParser htmlParser = new HtmlParser();
        htmlParser.doCompile();
    }


    private void init() {
//        keywords.add("if");
//        keywords.add("for");
//        keywords.add("else");
//        keywords.add("while");
//        keywords.add("do");
//        keywords.add("float");
//        keywords.add("int");
//        keywords.add("break");

        keywordSet.add("if");
        keywordSet.add("for");
        keywordSet.add("else");
        keywordSet.add("while");
        keywordSet.add("do");
        keywordSet.add("float");
        keywordSet.add("int");
        keywordSet.add("break");

        otherwordSet.add("/");
        otherwordSet.add("=");
        otherwordSet.add("<");
        otherwordSet.add(">");
        otherwordSet.add("/>");
        otherwordSet.add("</");
    }

    private void doCompile(){
        sourcecode =htmlslashcode;
        info = "编译开始....";
        appendInfo();
        lex();
        HtmlTag htmlTag=htmlTag();
        String text=htmlTag.render();
        System.out.println("render text:"+text);
    }

    private boolean match(String s){
        word = wordList.get(indexword);
        boolean result = false;
        if(word.equals(s)){
            result = true;
        }
        indexword++;
        return result;
    }

    private String getToken(){
        String token =null;
        if(indexword<=listSize-1) {
             token= wordList.get(indexword);
        }
        indexword++;
        return token;
    }

    private void unGetToken(){
        //String token = wordList.get(indexword);
        indexword--;
    }

    private String currentToken(){
        return getTokenByIndex(this.indexword);
    }

    private String getTokenByIndex(int indx){
        if(indx>listSize-1) return null;
        String lastword = wordList.get(indx);
        return lastword;
    }

    private Character getchar(){
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


    private void alpha(){
        int i=1,flag;
        char ch;
        if(lookahead==null) return;
        ch= lookahead;
        //word[0]=ch;
        word+=ch;
        ch=getchar();
        //如果是字母还是数字或者中划线的话，继续读取
        while (isalpha(ch)||isdigit(ch)||ishyphen(ch)){
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
        } else{
            System.out.println("word:"+word);
            //strcpy(idtable[id_num++],word);
            //idList.add(word);
            //strcpy(finaltable[final_num],"id");
            finalList.add("id");
            wordList.add(word);
            indexIndetity++;
        }
    }

    private void ungetc(){
        index--;
    }

    private boolean isalpha(char ch){
        return Character.isAlphabetic(ch);
    }

    private void clear(){
        finalnum =0;
        final_num=0;
        indexIndetity=0;
        indexword=0;
        index=0;
    }

    private boolean isdigit(char ch){
        return Character.isDigit(ch);
    }

    /**
     * 是否中划线
     * @param ch
     * @return
     */
    private boolean ishyphen(char ch){
        return ch=='-';
    }

    private   void initialize(){
        word ="";
    }

    /**
     * 词法分析
     */
    private void lex(){
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
                if (lookahead == '>') {
                    //note();
                    //这是半闭状态的情况
                    finalList.add("/>");
                    wordList.add("/>");
                    initialize();
                }//单行注释
                else {
                    ungetc();
//                    strcpy(finaltable[final_num], "/");
                    finalList.add("/");
                    wordList.add("/");
//                    finalList.add("/");
//                    strcpy(otherchartable[otherchar_num++], "/");
                    othercharList.add("/");
                    finaltableint[final_num++] = 2;
                    initialize();
                }
                //将得到< </等内容
            }else if(lookahead=='<'){
                lookahead = getchar();
                //这是得到全闭合的情况
                if(lookahead=='/'){
                    finalList.add("</");
                    wordList.add("</");
                    initialize();
                    //finaltableint[final_num++]=2;
                }else {
                    ungetc();
                    finalList.add("<");
                    wordList.add("<");
                    othercharList.add("<");
                    finaltableint[final_num++]=2;
                    initialize();
                }
            } else{
                otherchar();
                initialize();
            }
        }
        lexResult();
    }


    private void lexResult(){
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
        listSize = wordList.size();
    }


    private void digit(){
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
            //idList.add(word);
            //strcpy(finaltable[final_num],"id");
            finalList.add("id");
            wordList.add(word);
            finaltableint[indexIndetity]=1;
            indexIndetity++;
            //finalTableIndetityList.add(KEYWORD.KEY_ID);
            final_num++;
        } else{
            //数字
//            strcpy(digittable[digit_num++],word);
            //digitList.add(word);
//            strcpy(finaltable[final_num],"num");
            finalList.add("num");
            wordList.add(word);
            finaltableint[indexIndetity]=99;
            indexIndetity++;
            //finalTableIndetityList.add(KEYWORD.KEY_NUM);
            //final_num++;
        }
    }


    private void otherchar(){
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
                    //finalTableIndetityList.add(KEYWORD.KEY_NOTEQ);
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
                    //finalTableIndetityList.add(KEYWORD.KEY_EQEQ);
                    finaltableint[indexIndetity]=4;
                    indexIndetity++;
                } else{
//                    strcpy(otherchartable[otherchar_num++],"=");
                    othercharList.add("=");
//                    strcpy(finaltable[final_num],"=");
                    finalList.add("=");
                    wordList.add("=");
                   //finalTableIndetityList.add(KEYWORD.KEY_EQ);
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
                //finalTableIndetityList.add(KEYWORD.KEY_LEFT_PARREN);
                //finaltableint[indexIndetity]=KEYWORD.KEY_LEFT_PARREN;
                indexIndetity++;
                break;
            case ')':
//                strcpy(otherchartable[otherchar_num++],")");
                System.out.println("遇到)");
                othercharList.add(")");
//                strcpy(finaltable[final_num],")");
                finalList.add(")");
                wordList.add(")");
                //finalTableIndetityList.add(KEYWORD.KEY_RIGHT_PARREN);
                //finaltableint[indexIndetity]=KEYWORD.KEY_RIGHT_PARREN;
                indexIndetity++;
                break;
            case ';':
//                strcpy(otherchartable[otherchar_num++],";");
                othercharList.add(";");
//                strcpy(finaltable[final_num],";");
                finalList.add(";");
                wordList.add(";");
                //finalTableIndetityList.add(KEYWORD.KEY_SIMICOLON);
                //finaltableint[indexIndetity]=KEYWORD.KEY_SIMICOLON;
                indexIndetity++;
                break;
            case '{':
//                strcpy(otherchartable[otherchar_num++],"{");
                othercharList.add("{");
//                strcpy(finaltable[final_num],"{");
                finalList.add("{");
                wordList.add("{");
                //finalTableIndetityList.add(KEYWORD.KEY_LEFT_BRACKET);
                //finaltableint[indexIndetity]=KEYWORD.KEY_LEFT_BRACKET;
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
                //finaltableint[indexIndetity]=KEYWORD.KEY_RIGHT_BRACKET;
                //finalTableIndetityList.add(KEYWORD.KEY_RIGHT_BRACKET);
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
                    //finaltableint[indexIndetity] = KEYWORD.KEY_OR;
                    indexIndetity++;
                    //finalTableIndetityList.add(KEYWORD.KEY_OR);

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
                    //finalTableIndetityList.add(KEYWORD.KEY_AND);
                    //finaltableint[indexIndetity] = KEYWORD.KEY_AND;
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
                //finalTableIndetityList.add(KEYWORD.KEY_ADD);
                //finaltableint[indexIndetity]=KEYWORD.KEY_ADD;
                indexIndetity++;
                break;
            case '-':
                //strcpy(otherchartable[otherchar_num++],"-");
                othercharList.add("-");
                //strcpy(finaltable[final_num],"-");
                finalList.add("-");
                wordList.add("-");
                //finalTableIndetityList.add(KEYWORD.KEY_SUB);
                //finaltableint[indexIndetity++]=KEYWORD.KEY_SUB;
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
                    //finalTableIndetityList.add(KEYWORD.KEY_GTEQ);
                    //finaltableint[indexIndetity]=KEYWORD.KEY_GTEQ;
                    indexIndetity++;
                }  else{
//                    strcpy(otherchartable[otherchar_num++],">=");
                    othercharList.add(">=");
//                    strcpy(finaltable[final_num],">");
                    finalList.add(">");
                    wordList.add(">");
                    //finalTableIndetityList.add(KEYWORD.KEY_GT);
                    //finaltableint[indexIndetity]=KEYWORD.KEY_GT;
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
                    //finalTableIndetityList.add(KEYWORD.KEY_LTEQ);
                    //finaltableint[indexIndetity]=KEYWORD.KEY_LTEQ;
                    indexIndetity++;
                } else{
//                    strcpy(otherchartable[otherchar_num++],"<");
                    othercharList.add("<");
//                    strcpy(finaltable[final_num],"<");
                    finalList.add("<");
                    wordList.add("<");
                    //finalTableIndetityList.add(KEYWORD.KEY_LT);
                    //finaltableint[indexIndetity]=KEYWORD.KEY_LT;
                    indexIndetity++;
                    ungetc();
                }
            }
            break;
            case '*':
//                strcpy(finaltable[final_num],"*");
                finalList.add("*");
                wordList.add("*");
                //finaltableint[indexIndetity]=KEYWORD.KEY_MUL;
                //finalTableIndetityList.add(KEYWORD.KEY_MUL);
                indexIndetity++;
                break;
            case '"':
                finalList.add("\"");
                wordList.add("\"");
                indexIndetity++;
                break;
            default:
                error();
                break;
        }
    }

    private void error(){
        flag_error=1;
        System.out.println("出现错误，终止分析!\n");
    }

    /**
     * 语法分析
     */
    private HtmlTag htmlTag(){
        HtmlTag htmlTag =new HtmlTag();
        htmlTagStack.add(htmlTag);
        state =1;
        String lastword = getToken();
        unGetToken();
        //readLoop:

            boolean b=match("<");
            System.out.println("匹配开始<:"+b);
            //出错
            if(!b) return null;
            //htmlTag = new HtmlTag();

            int size = htmlTagStack.size();
            if(size>=1){
                HtmlTag parentHtmlTag = htmlTagStack.get(size-1);
                //parentHtmlTag.addChild(htmlTag);
            }

            htmlTag.setBeginOpen("<");
            //alpha();
            String tagId = getToken();
            htmlTag.setTagId(tagId);

            //List<Attr> attrs=attrs();
            List<Attr> attrs = attrs();
            //unGetToken();
            if (attrs != null) {
                htmlTag.setAttrPairList(attrs);
            }
            lastword = currentToken();
            //unGetToken();
            //lastword = getToken();
            System.out.println("attr list complete:" + lastword);
            //半封闭结束，直接结束,没有消息体
            if (lastword!=null&&lastword.equals("/>")) {
                System.out.println("直接遇到封闭符号");
                htmlTag.setHalfSlashClose(true);
                htmlTag.setEndOpen("/");
                htmlTag.setEndClose(">");
                System.out.println("此时堆栈的数目:" + htmlTagStack.size());
                htmlTagStack.pop();
                if (htmlTagStack.size() > 0) {
                    HtmlTag htmlTag1 = htmlTag();
                    htmlTag.addChild(htmlTag1);
                }
                //return htmlTag;
                //全封闭结束
            } else if (lastword!=null&&lastword.equals("</")) {
                htmlTag.setFullClose(true);
                htmlTag.setEndOpen("</");
                htmlTag.setEndClose(">");
                String endTagId = getToken();
                String endClose = getToken();
                if (!endTagId.equals(tagId)) {
                    System.out.println("开始TagId和闭合TagId不同出错");
                }
                if (!endClose.equals(">")) {
                    System.out.println("结尾符号不是>,出错");
                }
                htmlTagStack.pop();
                if (htmlTagStack.size() > 0) {
                    //continue;
                } else {
                    return htmlTag;
                }
                //return htmlTag;
            }
            //还有消息体,这一步没有进来
            else if (lastword!=null&&lastword.equals(">")) {
                //如果是meta则可以直接结束,在这里不考虑这种现象
                htmlTag.setBeginClose(">");
                lastword = getToken();
                if (!otherwordSet.contains(lastword)) {
                    System.out.println("进入文本模式:" + lastword);
                    unGetToken();
                    HtmlTag textTag = new HtmlTag();
                    textTag.setText(true);
                    textTag.setText(lastword);
                    htmlTag.addChild(textTag);
                    htmlTagStack.add(textTag);
                }
                lastword = getToken();
                while (lastword!=null&&lastword.equals("<")) {
                    System.out.println("进入下一个tag:" + lastword + " " + currentToken());
                    unGetToken();
                    HtmlTag htmlTag1 = htmlTag();
                    htmlTag.addChild(htmlTag1);
                    lastword = getToken();
                }
            }
            lastword = getToken();
       // }

        //if(htmlTagStack.size()>=1){
        //    htmlTagStack.pop();
        //}
//        htmlTagStack.pop();
        return htmlTag;
    }

    private List<Attr> attrList(){
        List<Attr> attrList = new ArrayList<>();
        String next = getToken();
        //这两种情况代表attrList的结束
        while (next.equals(">")&&next.equals("/>")){
            Attr attr =attrPair();
            if(attr!=null) {
                attrList.add(attr);
            }
            next = getToken();
        }
        unGetToken();
        if(attrList.size()>0){
            return attrList;
        }else {
            return  null;
        }
    }

    private Attr attrPair(){
        String next = seeNext();
        Attr attr = new Attr();
        if(!next.equals(">")||!next.equals("/>")){
            String name = getToken();
            attr.name =name;
            //得到value
            String lastword = getToken();
            match("\"");
            String attrValue ="";
            while (!lastword.equals("\"")){
                attrValue +=" "+lastword;
                lastword = getToken();
                System.out.println("attr value last word:"+lastword);
            }
            attr.value = attrValue;
            return attr;
        }else {
            return null;
        }
    }



    private List<Attr> attrs(){
        List<Attr> attrList = new ArrayList<>();
        Attr attr=attr();
        if(attr!=null){
            attrList.add(attr);
            //相当于没有属性的情况
        }else if(attr==null){
            if(indexword>=listSize-1) return null;
            String lastwordencount= getTokenByIndex(indexword);
            System.out.println("lastwordencount:"+lastwordencount);
            //进入死循环
            //unGetToken();
            System.out.println("属性已经获取完毕了");
            return null;
            //return null;
        }

        String lastword = getToken();

        System.out.println("第二轮属性值的获取222:"+lastword);
        if(lastword!=null&&lastword.equals(">")){
            unGetToken();
            return attrList;
        }
        //直接遇到结束符号
        else if(lastword!=null&&lastword.equals("/>")){
            System.out.println("直接遇到tag的结束符号");
            return attrList;
        }
        else {
            System.out.println("进入下一轮属性值的获取");
            List nextList = attrs();
            if(nextList!=null) {
                attrList = ListUtils.concat(attrList, nextList);
            }
        }
        return attrList;
        //return attrList;
    }

    /**
     * 查看下一个word,但不移动指针
     * @return
     */
    private String seeNext(){
        return wordList.get(indexword+1);
    }

    private Attr attr(){
        if(indexword>=listSize-1) return null;
        String lastword = getTokenByIndex(indexword);
        //String lastword = getToken();
        System.out.println("属性值获取:"+lastword);
        if(otherwordSet.contains(lastword)) {
            System.out.println("遇到非属性符号，属性值获取完毕");
            //unGetToken();
            return null;
        }
        //alpha();
        Attr attr= new Attr();
        lastword = getToken();
        attr.name = lastword;
        String nextToken = getToken();
        System.out.println("nextToken111:"+nextToken);
        if(nextToken.equals("=")){
            //alpha();
            lastword= getToken();
            if(lastword.equals("\"")){
                lastword = getToken();
                String attrValue ="";
                while (!lastword.equals("\"")){
                    attrValue +=" "+lastword;
                    lastword = getToken();
                    System.out.println("attr value last word:"+lastword);
                }
                attr.value = attrValue;
            }
            //attr.value= value;
        }
        return attr;
    }



    private void appendInfo(){
        System.out.println("\n"+info);
    }



}
