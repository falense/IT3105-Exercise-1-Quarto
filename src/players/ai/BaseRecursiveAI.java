package players.ai;


public abstract class BaseRecursiveAI extends BaseAI{

	protected int maxDepth;
	protected NoviceAI randomizer;
	
	public BaseRecursiveAI(boolean verboseOutput, int maxDepth) {
		super(verboseOutput);
		this.maxDepth = maxDepth;
		randomizer = new NoviceAI(verboseOutput);
	}

}
