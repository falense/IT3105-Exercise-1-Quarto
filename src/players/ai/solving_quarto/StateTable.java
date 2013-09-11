package players.ai.solving_quarto;

import java.util.concurrent.ConcurrentHashMap;

public class StateTable {
	ConcurrentHashMap<State, Integer> table = new ConcurrentHashMap<State, Integer>(2);
	
	
	boolean exists(State s){
		
		return false;
	}
	void put(State s,double value){
		
	}
	double get(State s){
		return 0;
	}
}
