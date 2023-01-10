package interfaces;

/**
 * Interface provides specific Functionality based on Repository type.
 */
public interface Profitable
{
	/**
	 * Mandatory Function used to calculate an Objects Profit independent of its Formula.
	 *
	 * @return Calculated Profit, as a Float to cover even cases of Division or Floating-Point Multiplication.
	 */
	public float calculateProfit();
}
