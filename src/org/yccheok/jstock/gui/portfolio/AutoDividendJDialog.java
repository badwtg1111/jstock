/*
 * JStock - Free Stock Market Software
 * Copyright (C) 2013 Yan Cheng Cheok <yccheok@yahoo.com>
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along
 * with this program; if not, write to the Free Software Foundation, Inc.,
 * 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
 */

package org.yccheok.jstock.gui.portfolio;

import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.MessageFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFormattedTextField;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.text.NumberFormatter;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.yccheok.jstock.engine.Code;
import org.yccheok.jstock.internationalization.GUIBundle;
import org.yccheok.jstock.portfolio.DecimalPlaces;
import org.yccheok.jstock.portfolio.Dividend;

/**
 *
 * @author yccheok
 */
public class AutoDividendJDialog extends javax.swing.JDialog {

    /**
     * Creates new form AutoDividendJDialog
     */
    public AutoDividendJDialog(java.awt.Frame parent, boolean modal, Map<Code, List<Dividend>> dividends) {
        super(parent, modal);
        initComponents();
        
        this.jTextArea1.setVisible(false);
        
        JPanel panel = new JPanel();
        panel.setBorder(new EmptyBorder(10, 10, 10, 10) );
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        
        TreeMap<Code, List<Dividend>> treeMap = new TreeMap<Code, List<Dividend>>(new Comparator<Code>() {
            @Override
            public int compare(Code o1, Code o2) {
                return o1.toString().compareTo(o2.toString());
            }            
        });
        treeMap.putAll(dividends);
        for (Map.Entry<Code, List<Dividend>> entry : treeMap.entrySet()) {
            AutoDividendJPanel autoDividendJPanel = new AutoDividendJPanel(this, entry.getValue());
            autoDividendJPanels.add(autoDividendJPanel);
            panel.add(autoDividendJPanel);    
            panel.add(Box.createRigidArea(new Dimension(0, 5)));
        }
        
        this.jScrollPane1.setViewportView(panel);
        
        updateTotalLabel();
    }

    public void updateInstructionLabel() {
        String template = GUIBundle.getString("AutoDividendJDialog_Intruction_template");
        double tax = (Double)jFormattedTextField1.getValue();
        double taxRate = (Double)jFormattedTextField2.getValue();        
        final String text0 = org.yccheok.jstock.portfolio.Utils.toCurrency(DecimalPlaces.Three, tax);
        final String text1 = org.yccheok.jstock.portfolio.Utils.toCurrency(DecimalPlaces.Three, taxRate);
        double value = 100.0 - tax - (100.0 * taxRate / 100.0);
        value = Math.max(value, 0.0);
        final String text2 = org.yccheok.jstock.portfolio.Utils.toCurrencyWithSymbol(DecimalPlaces.Three, value);
        String message = MessageFormat.format(template, text0, text1, text2);
        jTextArea1.setText(message);
        this.jTextArea1.setVisible(true);
    }
    
    public void updateTotalLabel() {
        int selectedStock = 0;
        int selectedDividend = 0;
        double selectedAmount = 0.0;
        for (AutoDividendJPanel autoDividendJPanel : autoDividendJPanels) {
            if (autoDividendJPanel.isSelected()) {
                selectedStock++;
                selectedDividend += autoDividendJPanel.getSelectedCount();
                selectedAmount += autoDividendJPanel.getSelectedAmount();
            }
        }
        
        String stock_text = selectedStock + " " + GUIBundle.getString(selectedStock <= 1 ? "AutoDividendJDialog_StockSingular" : "AutoDividendJDialog_StockPlural");
        String dividend_text = selectedDividend + " " + GUIBundle.getString(selectedDividend <= 1 ? "AutoDividendJDialog_DividendSingular" : "AutoDividendJDialog_DividendPlural");
        String total_text = org.yccheok.jstock.portfolio.Utils.toCurrencyWithSymbol(DecimalPlaces.Three, selectedAmount);
        String message = MessageFormat.format(GUIBundle.getString("AutoDividendJDialog_Total_template"), stock_text, dividend_text, total_text);
        this.jLabel4.setText(message);
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jXHeader1 = new org.jdesktop.swingx.JXHeader();
        jPanel2 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jFormattedTextField1 = getCurrencyJFormattedTextField();
        jLabel2 = new javax.swing.JLabel();
        jFormattedTextField2 = getCurrencyJFormattedTextField();
        jTextArea1 = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("org/yccheok/jstock/data/gui"); // NOI18N
        setTitle(bundle.getString("AutoDividendJDialog_Title")); // NOI18N
        setResizable(false);
        getContentPane().setLayout(new java.awt.BorderLayout(5, 5));

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/16x16/apply.png"))); // NOI18N
        jButton1.setText(bundle.getString("AutoDividendJDialog_Apply")); // NOI18N
        jPanel1.add(jButton1);

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/16x16/button_cancel.png"))); // NOI18N
        jButton2.setText(bundle.getString("AutoDividendJDialog_Cancel")); // NOI18N
        jPanel1.add(jButton2);

        getContentPane().add(jPanel1, java.awt.BorderLayout.SOUTH);

        jXHeader1.setDescription(bundle.getString("AutoDividendJDialog_Description")); // NOI18N
        jXHeader1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/32x32/auto-dividend.png"))); // NOI18N
        jXHeader1.setTitle(bundle.getString("AutoDividendJDialog_AutoDividend")); // NOI18N
        getContentPane().add(jXHeader1, java.awt.BorderLayout.NORTH);

        jPanel2.setLayout(new java.awt.BorderLayout(5, 5));

        jLabel4.setForeground(new java.awt.Color(0, 0, 255));
        jLabel4.setText("4 dividends selected. Total dividend is $105.65");
        jPanel4.add(jLabel4);

        jPanel2.add(jPanel4, java.awt.BorderLayout.SOUTH);

        jScrollPane1.setBorder(javax.swing.BorderFactory.createTitledBorder(bundle.getString("AutoDividendJDialog_Dividend"))); // NOI18N
        jPanel2.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(bundle.getString("AutoDividendJDialog_DividendTax"))); // NOI18N

        jLabel1.setText(bundle.getString("AutoDividendJDialog_Tax")); // NOI18N

        jFormattedTextField1.setText("0");
        jFormattedTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jFormattedTextField1KeyTyped(evt);
            }
        });

        jLabel2.setText(bundle.getString("AutoDividendJDialog_TaxRate")); // NOI18N

        jFormattedTextField2.setText("0");
        jFormattedTextField2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jFormattedTextField2KeyTyped(evt);
            }
        });

        jTextArea1.setEditable(false);
        jTextArea1.setColumns(20);
        jTextArea1.setFont(new java.awt.Font("Tahoma", 2, 11)); // NOI18N
        jTextArea1.setLineWrap(true);
        jTextArea1.setRows(1);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextArea1, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jFormattedTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jFormattedTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(64, Short.MAX_VALUE))
        );

        jPanel3Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jFormattedTextField1, jFormattedTextField2});

        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jFormattedTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jFormattedTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTextArea1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.add(jPanel3, java.awt.BorderLayout.NORTH);

        getContentPane().add(jPanel2, java.awt.BorderLayout.CENTER);

        setBounds(0, 0, 301, 502);
    }// </editor-fold>//GEN-END:initComponents

    private void jFormattedTextField1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jFormattedTextField1KeyTyped
        update();
    }//GEN-LAST:event_jFormattedTextField1KeyTyped

    private void jFormattedTextField2KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jFormattedTextField2KeyTyped
        update();
    }//GEN-LAST:event_jFormattedTextField2KeyTyped

    private void commitEdit() {
        try {
            jFormattedTextField1.commitEdit();
            jFormattedTextField2.commitEdit();
        } catch (ParseException ex) {
            log.error(null, ex);
        }
    }
    
    private void update() {
        SwingUtilities.invokeLater(new Runnable() {@Override public void run() {
            _update();
        }});
    }
    
    private void _update() {
        commitEdit();
        double tax = (Double)jFormattedTextField1.getValue();
        double taxRate = (Double)jFormattedTextField2.getValue();
        for (AutoDividendJPanel autoDividendJPanel : autoDividendJPanels) {
            autoDividendJPanel.updateTaxInfo(tax, taxRate);
        }
        updateTotalLabel();
        updateInstructionLabel();
    }
    
    private MouseListener getJFormattedTextFieldMouseListener() {
        MouseListener ml = new MouseAdapter()
        {
            @Override
            public void mousePressed(final MouseEvent e)
            {
                if (e.getClickCount() == 2) {
                    // Ignore double click.
                    return;
                }
                
                SwingUtilities.invokeLater(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        JTextField tf = (JTextField)e.getSource();
                        int offset = tf.viewToModel(e.getPoint());
                        tf.setCaretPosition(offset);
                    }
                });
            }
        };
        return ml;
    }
    
    private JFormattedTextField getCurrencyJFormattedTextField() {
        NumberFormat format= NumberFormat.getNumberInstance();
        format.setMaximumFractionDigits(3);
        NumberFormatter formatter= new NumberFormatter(format);
        formatter.setMinimum(0.0);
        formatter.setValueClass(Double.class);
        JFormattedTextField formattedTextField = new JFormattedTextField(formatter);
        formattedTextField.addMouseListener(getJFormattedTextFieldMouseListener());
        return formattedTextField;
    }
    
    private static final Log log = LogFactory.getLog(AutoDividendJDialog.class);
    
    private final List<AutoDividendJPanel> autoDividendJPanels = new ArrayList<AutoDividendJPanel>();
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JFormattedTextField jFormattedTextField1;
    private javax.swing.JFormattedTextField jFormattedTextField2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    private org.jdesktop.swingx.JXHeader jXHeader1;
    // End of variables declaration//GEN-END:variables
}
