package com.js.swing.tooltip;

import java.awt.event.ActionListener;
  
import javax.swing.JButton;  
import javax.swing.JToolTip;  
  
public class TipButton extends JButton {
  
     private static final long serialVersionUID = -4212536258012660909L;  
      
     private HtmlToolTip toolTip;
     private ActionListener toolTipBtnListener;  
      
     public TipButton() {
          super();  
     }  
  
//     public JButton getToolTipButton() {
//          if (toolTip != null) {
//               return toolTip.getButton();
//          } else {
//               return null;
//          }
//     }
      
//     public void addToolTipBtnListener(ActionListener l) {
//          toolTipBtnListener = l;
//     }
      
     public JToolTip createToolTip() {  
          if (toolTip == null) {  
               toolTip = new HtmlToolTip();
               //toolTip.getButton().addActionListener(toolTipBtnListener);
          }  
           
          return toolTip;  
     }

     public JToolTip createToolTip(int width,int height) {
          if (toolTip == null) {
               toolTip = new HtmlToolTip(width, height);
               //toolTip.getButton().addActionListener(toolTipBtnListener);
          }
          return toolTip;
     }
}  