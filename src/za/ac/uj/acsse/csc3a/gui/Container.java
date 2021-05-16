package za.ac.uj.acsse.csc3a.gui;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

import za.ac.uj.acsse.csc3a.otherUtilities.FormulaValidator;

/**
 * 
 */

/**
 * @author Andile Ndlovu
 *
 */
@SuppressWarnings("serial")
public class Container extends JPanel {
	private  JButton validationBtn = new JButton("Validate");
	private JLabel validationLbl = new JLabel("  Press Validate to deduce the soundness of your theorem.   ");
	private JPanel validationPnl = new JPanel();
	private JPanel inferencesPnl = new JPanel();
	private JPanel premisesFormulaListPnl = new JPanel();
	private JPanel conclusionFormulaPnl = new JPanel();
	private JTextField conclusionFormulatxtFld = new JTextField("Enter the conclusion to your propositional inference here...", 30);
	private JList<String> premisesLst;
	private DefaultListModel<String> premisesLstMdl = new DefaultListModel<>();
	private JScrollPane premisesLstScrlPne = new JScrollPane(premisesLst);
	private JTextField premiseToAddtxtFld = new JTextField("Enter the premise to your propositional inference here...", 30);
	private JButton premiseBtn = new JButton("Add to list of premises");
	private JPanel premiseAdditionPnl = new JPanel(new BorderLayout()); 
	private JPanel premiseBtnPnl = new JPanel(new BorderLayout());
			
	
	public Container() {
		super();
//		setSize(new Dimension(500, 600));
		setLayout(new BorderLayout());
		
		validationBtn.setEnabled(true);
		
		validationBtn.addActionListener((ActionEvent ae)->{
			
			if(!(conclusionFormulatxtFld.getText().equals("") || 
					conclusionFormulatxtFld.getText().equals("Enter the conclusion to your propositional inference here..."))){	
				ArrayList<String> premiseArrLst = new ArrayList<>();
				for(int i = 0; i < premisesLstMdl.size(); i++){
					premiseArrLst.add(premisesLstMdl.get(i));
				}
				String expression = buildInferenceFormula(premiseArrLst, conclusionFormulatxtFld.getText());
				premisesLstMdl.clear();
				conclusionFormulatxtFld.setText("");
				validateInferenceFormula(expression);
			}
		});
//		premiseBtn.setSize(getMinimumSize());
		premiseBtn.addActionListener((ActionEvent ae)->{
			premisesLstMdl.addElement(premiseToAddtxtFld.getText().trim());
			
		});
		
		conclusionFormulatxtFld.setForeground(Color.GRAY);
		conclusionFormulatxtFld.setEditable(true);
		conclusionFormulatxtFld.addActionListener((ActionEvent ae)->{
			
		});
		
		premiseToAddtxtFld.setForeground(Color.GRAY);
		premiseToAddtxtFld.setEditable(true);
		premiseToAddtxtFld.addActionListener((ActionEvent ae)->{
			
		});
		
		conclusionFormulatxtFld.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent arg0) {
				if(conclusionFormulatxtFld.getText().equals(""))
					conclusionFormulatxtFld.setText("Enter the conclusion to your propositional inference here...");
				conclusionFormulatxtFld.setForeground(Color.GRAY);
				
			}
			
			@Override
			public void focusGained(FocusEvent arg0) {
				conclusionFormulatxtFld.setText(null);
				conclusionFormulatxtFld.setForeground(Color.BLACK);
				
			}
		});
		
		premiseToAddtxtFld.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent arg1) {
				if(conclusionFormulatxtFld.getText().equals(""))
					conclusionFormulatxtFld.setText("Enter the premise to your propositional inference here...");
				conclusionFormulatxtFld.setForeground(Color.GRAY);
				
			}
			
			@Override
			public void focusGained(FocusEvent arg1) {
				conclusionFormulatxtFld.setText(null);
				conclusionFormulatxtFld.setForeground(Color.BLACK);
				
			}
		});
		
		premisesLst = new JList<>(premisesLstMdl);
		premisesLst.setAlignmentX(LEFT_ALIGNMENT);
		premisesLst.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		premisesLstScrlPne.setAlignmentX(LEFT_ALIGNMENT);
		premisesLstScrlPne.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		premisesLstScrlPne.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		premisesLstScrlPne.setViewportView(premisesLst);
		premisesLstScrlPne.setSize(getMaximumSize());
		
		premiseAdditionPnl.add(premiseToAddtxtFld, BorderLayout.PAGE_START);
		premiseBtnPnl.add(premiseBtn, BorderLayout.PAGE_START);
		premiseBtnPnl.add(new JLabel("Please go through the ReadMe.txt file in order to use this app appropriately..."), BorderLayout.CENTER);
		premiseAdditionPnl.add(premiseBtnPnl, BorderLayout.LINE_START);
		
		
		premisesFormulaListPnl.setLayout(new BorderLayout());
		premisesFormulaListPnl.add(premisesLstScrlPne, BorderLayout.EAST);
		premisesFormulaListPnl.add(premiseAdditionPnl, BorderLayout.WEST);
		
		conclusionFormulaPnl.add(conclusionFormulatxtFld);
		
		validationPnl.setLayout(new BorderLayout());
		validationPnl.add(validationBtn, BorderLayout.EAST);
		validationPnl.add(validationLbl, BorderLayout.WEST);
		
		conclusionFormulaPnl.setBackground(Color.DARK_GRAY);
		premisesFormulaListPnl.setBackground(Color.RED);
		
//		inferencesPnl.setSize(new Dimension(500, 600));
		inferencesPnl.setLayout(new BorderLayout());
		inferencesPnl.add(premisesFormulaListPnl, BorderLayout.CENTER);
		inferencesPnl.add(conclusionFormulaPnl, BorderLayout.SOUTH);

		add(validationPnl, BorderLayout.SOUTH);
		add(inferencesPnl, BorderLayout.CENTER);
	}


	private void validateInferenceFormula(String expression) {
		FormulaValidator validator = new FormulaValidator(expression);
		if(validator.isValid()){
			JOptionPane.showMessageDialog(null, "This inference/theorem is logically sound!", "Validity Of Theorem", JOptionPane.INFORMATION_MESSAGE);
		}else{
			JOptionPane.showMessageDialog(null, "This inference/theorem is not logically sound!", "Validity Of Theorem", JOptionPane.WARNING_MESSAGE);
		}
	}


	private String buildInferenceFormula(ArrayList<String> premiseArrLst, String conclusion) {
		String expression = "";
		if(premiseArrLst.size() > 0){
			expression = conjunct(premiseArrLst.get(0), premiseArrLst, 0)+":"+conclusion;
		}else {
			expression = conclusion;
		}
		return expression;
	}
	
	private String conjunct(String s, ArrayList<String>arrLst, int index){
		if(s.equals("") && index == arrLst.size()) return "";
		if(!s.equals("") && index == arrLst.size()) return s;
		return conjunct("("+/*arrLst.get(index)*/ s+"A"+arrLst.get(++index)+")", arrLst, ++index);
	}
}
