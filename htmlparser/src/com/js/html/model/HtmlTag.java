package com.js.html.model;

import com.js.html.Attr;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017-10-28.
 */
public class HtmlTag {

    private List<HtmlTag> children = new ArrayList<>();
    private List<Attr> attrPairList = new ArrayList<>();
    public Map<String,String> attrMap = new HashMap<>();

    private boolean isText=false;
    //半闭
    private boolean isHalfSlashClose= false;
    private boolean isMetaClose = false;
    private boolean isFullClose = true;

    private String beginOpen;
    private String beginClose;
    private String endOpen;
    private String endClose;
    private String tagId;

    private String text;


    public HtmlTag child(int i){
        return children.get(i);
    }

    public int numChild(){
        return children.size();
    }

    public int numAttr(){
        return attrPairList.size();
    }

    public Attr childAttr(int i){
        return attrPairList.get(i);
    }

    public void addChild(HtmlTag htmlTag){
        children.add(htmlTag);
    }

    public String getAttrByName(String attrName){
        return attrMap.get(attrName);
    }

    public void putAttr(String attrName,String attrValue){
        attrMap.put(attrName,attrValue);
    }

    public String render(){
        //String html = "";
        StringBuffer htmlSb = new StringBuffer();
        if(!isText){
            htmlSb.append(beginOpen).append(tagId);
            for(Attr attr:attrPairList){
                if(attr.getValue()!=null) {
                    htmlSb.append(" ").append(attr.name).append("=").append("\"").append(attr.value).append("\"");
                }else htmlSb.append(" ").append(attr.name);
            }

            if(isHalfSlashClose){
                htmlSb.append("/>");
                return htmlSb.toString();
            }
            if(isMetaClose){
                htmlSb.append(">");
                return htmlSb.toString();
            }

            htmlSb.append(beginClose);

            int numChild = numChild();
            for(HtmlTag tag:children){
                tag.render();
            }
            //属于全闭的情况
            htmlSb.append("</").append(tagId).append(">");
        }

        return htmlSb.toString();
    }


    public boolean isText() {
        return isText;
    }

    public void setText(boolean text) {
        isText = text;
    }

    public String getBeginOpen() {
        return beginOpen;
    }

    public void setBeginOpen(String beginOpen) {
        this.beginOpen = beginOpen;
    }

    public String getBeginClose() {
        return beginClose;
    }

    public void setBeginClose(String beginClose) {
        this.beginClose = beginClose;
    }

    public String getEndOpen() {
        return endOpen;
    }

    public void setEndOpen(String endOpen) {
        this.endOpen = endOpen;
    }

    public String getEndClose() {
        return endClose;
    }

    public void setEndClose(String endClose) {
        this.endClose = endClose;
    }

    public String getTagId() {
        return tagId;
    }

    public void setTagId(String tagId) {
        this.tagId = tagId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isHalfSlashClose() {
        return isHalfSlashClose;
    }

    public void setHalfSlashClose(boolean halfSlashClose) {
        isHalfSlashClose = halfSlashClose;
    }

    public boolean isMetaClose() {
        return isMetaClose;
    }

    public void setMetaClose(boolean metaClose) {
        isMetaClose = metaClose;
    }

    public List<Attr> getAttrPairList() {
        return attrPairList;
    }

    public void setAttrPairList(List<Attr> attrPairList) {
        this.attrPairList = attrPairList;
    }

    public boolean isFullClose() {
        return isFullClose;
    }

    public void setFullClose(boolean fullClose) {
        isFullClose = fullClose;
    }
}
