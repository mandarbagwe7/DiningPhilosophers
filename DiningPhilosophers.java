
import java.awt.Button;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.Semaphore;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.*;
import java.awt.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
//import javax.swing.*;

public class DiningPhilosophers {
	private JFrame frame;	
	static final int NUM_PHILOSOPHERS = 5;
	Philosopher[] philosophers = new Philosopher[NUM_PHILOSOPHERS];
	static Semaphore permissions = new Semaphore(2);
	static boolean [] philIsEating = {false, false, false, false, false};
	JLabel[] PhilsLables = new JLabel[5];
	JLabel[] comments = new JLabel[5];
	private JLabel lblNewLabel_0;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;
	private JLabel lblNewLabel_4;
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DiningPhilosophers window = new DiningPhilosophers();
					
					//DiningPhilosophers window = new DiningPhilosophers();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public DiningPhilosophers() {
		initialize();
	}
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(0, 0,600, 600);
		frame.getContentPane().setBackground(Color.WHITE);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Button start = new Button("Start");
		start.setBounds(250,500 , 77, 22);
		//frame.add(labp1);
		start.setBackground(Color.GREEN);
		
		start.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				start();
				start.setBackground(Color.RED);
				}
		});
		frame.getContentPane().setLayout(null);
		frame.getContentPane().add(start);
		lblNewLabel_0 = new JLabel("Philosopher# 0");
		lblNewLabel_0.setBounds(230, 4, 169, 14);
		frame.getContentPane().add(lblNewLabel_0);
		
		lblNewLabel_1 = new JLabel("Philosopher# 1");
		lblNewLabel_1.setBounds(415, 150, 169, 14);
		frame.getContentPane().add(lblNewLabel_1);
		
		lblNewLabel_2 = new JLabel("Philosopher# 2");
		lblNewLabel_2.setBounds(415,300, 169, 14);
		frame.getContentPane().add(lblNewLabel_2);
		
		lblNewLabel_3 = new JLabel("Philosopher# 3");
		lblNewLabel_3.setBounds(10, 300, 169, 14);
		frame.getContentPane().add(lblNewLabel_3);
		
		lblNewLabel_4 = new JLabel("Philosopher# 4");
		lblNewLabel_4.setBounds(10, 150, 169, 14);
		frame.getContentPane().add(lblNewLabel_4);
		comments[0] = lblNewLabel_0;
		comments[1] = lblNewLabel_1;
		comments[2] = lblNewLabel_2;
		comments[3] = lblNewLabel_3;
		comments[4] = lblNewLabel_4;
		ImageIcon p_1 = new ImageIcon("C://220px-Robert_W._Floyd.jpg");			
		ImageIcon p_2 = new ImageIcon("C://guru.jpg");
		ImageIcon p_3 = new ImageIcon("C://072010_CACMpg41_An-Interview.large.jpg");
		ImageIcon p_4 = new ImageIcon("C://pythogoras.jpg");
		ImageIcon p_5 = new ImageIcon("C://images.jpg");
		ImageIcon center = new ImageIcon("C://dining-philosophers-problem-small.jpg");
		JLabel labp1 = new JLabel(p_1);
		JLabel labp2 = new JLabel(p_2);
		JLabel labp3 = new JLabel(p_3);
		JLabel labp4 = new JLabel(p_4);
		JLabel labp5 = new JLabel(p_5);
		JLabel table = new JLabel(center);
		frame.getContentPane().add(labp1);
		labp1.setBounds(230, 4,169, 114);
		frame.add(labp1);
		frame.getContentPane().add(labp2);
		labp2.setBounds(415, 150, 169, 114);
		frame.add(labp2);
		frame.getContentPane().add(labp3);
		labp3.setBounds(415,300, 169, 114);
		frame.add(labp3);
		frame.getContentPane().add(labp4);
		labp4.setBounds(10, 300, 169, 114);
		frame.add(labp4);
		frame.getContentPane().add(labp5);
		labp5.setBounds(10, 150, 169, 114);
		frame.add(labp5);
		frame.getContentPane().add(table);
		table.setBounds(210, 170, 169, 169);
		frame.add(table);
		}
	
	public void start() {		
		
		Fork[] forks = new Fork[NUM_PHILOSOPHERS];
		for (int i = 0; i < NUM_PHILOSOPHERS; i++) {
			forks[i] = new Fork(i);
		}
		for (int i = 0; i < NUM_PHILOSOPHERS; i++) {
			philosophers[i] = new Philosopher(i, forks[(i + 1) % NUM_PHILOSOPHERS], forks[i], comments[i]);
			new Thread(philosophers[i]).start();
		}
	}
	public synchronized static boolean leftNeighbourPhilosopher(int id) {
		return DiningPhilosophers.philIsEating[(id + 1)
				% DiningPhilosophers.NUM_PHILOSOPHERS]; 
	}
	public synchronized static boolean rightNeighbourPhilosopher(int id) {

		if (id == 0)
			return DiningPhilosophers.philIsEating[4]; 
		else
			return DiningPhilosophers.philIsEating[(id - 1)
					% DiningPhilosophers.NUM_PHILOSOPHERS]; 
	}
}