package question3;

import question3.tp3.PileI;
import question3.tp3.PilePleineException;
import question3.tp3.PileVideException;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

/**
 * Décrivez votre classe Controleur ici.
 * 
 * @author ZIAD DAOUD
 * @version 07/07/2021
 */
public class Controleur extends JPanel {

	private JButton push, add, sub, mul, div, clear;
	private PileModele<Integer> pile;
	private JTextField donnee;

	public Controleur(PileModele<Integer> pile) {
		super();
		this.pile = pile;
		this.donnee = new JTextField(8);

		this.push = new JButton("push");
		this.add = new JButton("+");
		this.sub = new JButton("-");
		this.mul = new JButton("*");
		this.div = new JButton("/");
		this.clear = new JButton("[]");

		setLayout(new GridLayout(2, 1));
		add(donnee);
		donnee.addActionListener(null /* null est à remplacer */);
		JPanel boutons = new JPanel();
		boutons.setLayout(new FlowLayout());

		boutons.add(push);


		push.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				Controleur.this.push(); }});
		boutons.add(add);   add.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				Controleur.this.add(); }}); 
		boutons.add(sub);   sub.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				Controleur.this.sub(); }});
		boutons.add(mul);   mul.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				Controleur.this.mul(); }});
		boutons.add(div);   div.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				Controleur.this.div(); }});
		boutons.add(clear); clear.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				Controleur.this.clear(); }});
		
		add(boutons);
		boutons.setBackground(Color.red);
		actualiserInterface();
	}

	public void actualiserInterface() {
		if(pile.estVide()){
			add.setEnabled(false);
			sub.setEnabled(false);
			mul.setEnabled(false);
			div.setEnabled(false);
			clear.setEnabled(false);
			push.setEnabled(true);
		}
		else if(pile.taille()== 1){
			add.setEnabled(false);
			sub.setEnabled(false);
			mul.setEnabled(false);
			div.setEnabled(false);
			clear.setEnabled(true);
			push.setEnabled(true);
		}
		else if(pile.taille()> 1){
			add.setEnabled(true);
			sub.setEnabled(true);
			mul.setEnabled(true);
			div.setEnabled(true);
			clear.setEnabled(true);
			push.setEnabled(true);
		}
		else if(pile.estPleine()) {
			push.setEnabled(false);
			add.setEnabled(true);
			sub.setEnabled(true);
			mul.setEnabled(true);
			div.setEnabled(true);
			clear.setEnabled(true);
		}
	}

	private Integer operande() throws NumberFormatException {
		return Integer.parseInt(donnee.getText());
	}

	//empiler dans la pile
	public void push() {
		try{
			this.pile.empiler(operande());
		}catch(Exception e){

		}
		this.actualiserInterface();
	}
	//fair l'addition
	public void add() {
		try{
			this.pile.empiler(operande());
		}catch(Exception e){

		}
		this.actualiserInterface();
	}
	//fair la soustraction 
	public void sub() {
		try{
			this.pile.depiler();
			this.pile.empiler(this.pile.depiler() - this.pile.sommet());

		}catch(Exception e){
		}
		this.actualiserInterface();
	}
	// fair la multiplication
	public void mul(){
		try{
			this.pile.empiler(this.pile.depiler() * this.pile.depiler());
		}catch(Exception e){
		}
		this.actualiserInterface();
	}

	public void div() {
		//take care of divison by zero
		try{
			if(this.pile.sommet() != 0){
				this.pile.depiler();
				this.pile.empiler(this.pile.depiler() /this.pile.sommet());
			}
		}catch(Exception e){
		}
		this.actualiserInterface();
	}

	public void clear(){
		for(int i = this.pile.taille(); i >=0 ; i--){
			try{
				this.pile.depiler();
			}catch(Exception e){
			}
		}
		this.actualiserInterface();
	}

}
