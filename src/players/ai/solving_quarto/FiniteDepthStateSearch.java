package players.ai.solving_quarto;
import java.util.ArrayList;

import players.ai.solving_quarto.transformations.Transformation;

public class FiniteDepthStateSearch {
	StateTable s = new StateTable();
	ArrayList<Transformation> transformations = new ArrayList<Transformation>();
	
	public final static double DRAW = 0;
	public final static double WIN = 1;
	
	private int numThreads = 10;
	private int numThreadsRunning = 0;
	private Object lock = new Object();
	public void run(){
		State initialState = new State();
		double alpha = -Double.MAX_VALUE;
		double beta = Double.MAX_VALUE;
		System.out.println(search(initialState,alpha,beta,10));
	}
	private double search(State s,double alpha, double beta,int depth){
		if (s.isDraw()) return DRAW;
		if (s.isQuarto()) return WIN;
		
		if (depth <= 0) return 0;
		Action bestAction = null;
		double bestScore = 0;
		for (Action a : s.getActions()){
			State newState = s.apply(a);
			
		}
		return 0;
	}
	
	
	public static void main(String[] args)
    {
		FiniteDepthStateSearch f = new FiniteDepthStateSearch();
		f.run();
		
		
	}
}
